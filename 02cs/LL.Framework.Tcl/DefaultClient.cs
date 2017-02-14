//-----------------------------------------------------------------------
// <copyright file="DefaultClient.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl
{
    using FastJSON;
    using LL.Framework.Tcl.Parser;
    using LL.Framework.Tcl.Request;
    using LL.Framework.Tcl.Tcl.Util;
    using System;
    using System.Collections.Generic;
    using System.Text;
    using System.Xml;

    /// <summary>
    /// 基于REST(表述性状态转移:Representational State Transfer)客户端.
    /// </summary>
    public class DefaultClient : IClient
    {
        #region 公共常量
        /// <summary>
        /// 协议级Api参数名.
        /// </summary>
        public const string METHOD = "method";

        /// <summary>
        /// 鞋以及Appkey参数名.
        /// </summary>
        public const string APP_KEY = "app_key";

        /// <summary>
        /// 协议级返回结果格式.
        /// </summary>
        public const string FORMAT = "format";

        /// <summary>
        /// 协议级会话参数名.
        /// </summary>
        public const string SESSION = "session";

        /// <summary>
        /// 协议级JSON转化参数名称.
        /// </summary>
        public const string SIMPLIFY = "simplify";

        /// <summary>
        /// SDK版本.
        /// </summary>
        public const string SDK_VERSION_CLUSTER = "sdk-net--20151201";

        /// <summary>
        /// HTTP响应解析格式.
        /// </summary>
        public const string FORMAT_XML = "xml";
        #endregion

        #region 全局私有变量
        /// <summary>
        /// 请求的URL地址.
        /// </summary>
        private string serverUrl;

        /// <summary>
        /// 应用Key值.
        /// </summary>
        private string appKey;

        /// <summary>
        /// 通讯类.
        /// </summary>
        private WebUtils webUtils;

        /// <summary>
        /// 是否采用精简化的JSON返回.
        /// </summary>
        private bool useSimplifyJson = false;

        /// <summary>
        /// HTTP响应格式.
        /// </summary>
        private string format = FORMAT_XML;

        /// <summary>
        /// 禁用响应结果解释.
        /// </summary>
        private bool disableParser = false;

        /// <summary>
        /// 所有请求共享的系统级参数.
        /// </summary>
        private IDictionary<string, string> systemParameters;
        #endregion

        #region 客户端通讯参数设置
        /// <summary>
        /// 设置连接超时时间.
        /// </summary>
        /// <param name="timeout">超时等待时间(ms).</param>
        public void SetTimeout(int timeout)
        {
            this.webUtils.Timeout = timeout;
        }

        /// <summary>
        /// 设置等待数据读取完成超时时间.
        /// </summary>
        /// <param name="readWriteTimeout">数据读取完成超时时间(ms).</param>
        public void SetReadWriteTimeout(int readWriteTimeout)
        {
            this.webUtils.ReadWriteTimeout = readWriteTimeout;
        }

        /// <summary>
        /// 设置是否禁用响应结果解释.
        /// </summary>
        /// <param name="disableParser">禁用(TRUE)开启(FALSE).</param>
        public void SetDisableParser(bool disableParser)
        {
            this.disableParser = disableParser;
        }

        /// <summary>
        /// 设置是否使用精简化的JSON响应值.
        /// </summary>
        /// <param name="useSimplifyJson">使用(TRUE)不使用(FALSE).</param>
        public void SetUseSimplifyJson(bool useSimplifyJson)
        {
            this.useSimplifyJson = useSimplifyJson;
        }

        /// <summary>
        /// 设置所有请求系统级共享参数.
        /// </summary>
        /// <param name="systemParameters">共享参数.</param>
        public void SetSystemParameters(IDictionary<string, string> systemParameters)
        {
            this.systemParameters = systemParameters;
        }
        #endregion

        #region 默认构造器
        /// <summary>
        /// 默认构造器.
        /// </summary>
        /// <param name="serverUrl">服务器根地址.</param>
        /// <param name="appKey">应用Key值.</param>
        public DefaultClient(string serverUrl, string appKey)
        {
            this.serverUrl = serverUrl;
            this.appKey = appKey;
            this.webUtils = new WebUtils();
        }
        
        /// <summary>
        /// 默认构造器.
        /// </summary>
        /// <param name="serverUrl">服务器根地址.</param>
        /// <param name="appKey">应用Key值.</param>
        /// <param name="format">HTTP响应解析格式.</param>
        public DefaultClient(string serverUrl, string appKey, string format)
            : this(serverUrl, appKey)
        {
            this.format = format;
        }
        #endregion

        #region 外部执行请求方法
        /// <summary>ss
        /// 执行领域请求.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="request">领域请求.</param>
        /// <returns>请求的领域对象.</returns>
        public T Execute<T>(IServerRequest<T> request) where T : ServerResponse
        {
            return Execute<T>(request, null);
        }

        /// <summary>
        /// 使用指定的用户Seesion执行领域请求.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="request">领域请求.</param>
        /// <param name="session">用户Seesion.</param>
        /// <returns>请求的领域对象.</returns>
        public T Execute<T>(IServerRequest<T> request, string session) where T : ServerResponse
        {
            return DoExecute<T>(request, session);
        }
        #endregion

        #region 内部核心私有方法
        /// <summary>
        /// 执行请求.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="request">领域请求.</param>
        /// <param name="session">用户会话.</param>
        /// <returns></returns>
        private T DoExecute<T>(Request.IServerRequest<T> request, string session) where T : ServerResponse
        {
            // 提前检查业务参数
            try
            {
                request.Validate();
            }
            catch (TclException e)
            {
                return CreateErrorResponse<T>(e.ErrorCode, e.ErrorMsg);
            }

            //持久化Seesion
            this.webUtils.Session = session;

            // 添加协议级请求参数
            ServerDictionary txtParams = new ServerDictionary(request.GetParameters());
            txtParams.Add(METHOD, request.GetApiName());
            txtParams.Add(APP_KEY, appKey);
            txtParams.Add(FORMAT, format);
            txtParams.AddAll(this.systemParameters);

            if (this.useSimplifyJson)
            {
                txtParams.Add(SIMPLIFY, "true");
            }

            string realServerUrl = this.GetServerUrl(this.serverUrl, request.GetApiName(), session);
            string requestUrl = this.webUtils.BuildGetUrl(realServerUrl, txtParams);

            // 解释响应结果
            T response = null;
            try
            {
                string body;
                if (request is IServerUploadRequest<T>) // 是否需要上传文件
                {
                    IServerUploadRequest<T> uRequest = (IServerUploadRequest<T>)request;
                    IDictionary<string, FileItem> fileParams = TclUtils.CleanupDictionary(uRequest.GetFileParameters());
                    body = this.webUtils.DoPost(realServerUrl, txtParams, fileParams, request.GetHeaderParameters());
                }
                else
                {
                    body = webUtils.DoPost(realServerUrl, txtParams, request.GetHeaderParameters());
                }

                if (disableParser)
                {
                    response = Activator.CreateInstance<T>();
                    response.Body = body;
                }
                else
                {
                    if (FORMAT_XML.Equals(format))
                    {
                        IServerParser serverParser = new ServerXmlParser();
                        response = serverParser.Parse<T>(body);
                    }
                    else
                    {
                        IServerParser serverParser;
                        if (useSimplifyJson)
                        {
                            serverParser = new ServerJsonSimplifyParser();
                        }
                        else
                        {
                            serverParser = new ServerJsonParser();
                        }
                        response = serverParser.Parse<T>(body);
                    }
                }

                return response;
            }
            catch (Exception e)
            {
                if(response == null)
                {
                    response = Activator.CreateInstance<T>();
                }
                response.ErrCode = Convert.ToString(e.HResult);
                response.ErrMsg = e.Message;
                response.SubErrCode = Convert.ToString(e.InnerException.HResult);
                response.SubErrMsg = e.InnerException.Message;

                return response;
            }
        }

        /// <summary>
        /// 获取请求Url地址.
        /// </summary>
        /// <param name="serverUrl">服务器地址.</param>
        /// <param name="apiName">请求API.</param>
        /// <param name="session">用户会话.</param>
        /// <returns>完整的请求Url地址.</returns>
        internal virtual string GetServerUrl(string serverUrl, string apiName, string session)
        {
            StringBuilder url = new StringBuilder();
            url.Append(string.IsNullOrEmpty(serverUrl) ? string.Empty : serverUrl.TrimEnd('/'));
            url.Append("/");
            url.Append(string.IsNullOrEmpty(apiName) ? string.Empty : apiName.TrimEnd('/'));
            return url.ToString();
        }

        /// <summary>
        /// 创建响应错误消息.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="errCode">错误代码.</param>
        /// <param name="errMsg">错误信息.</param>
        /// <returns>带有错误信息的领域对象.</returns>
        private T CreateErrorResponse<T>(string errCode, string errMsg) where T : ServerResponse
        {
            T response = Activator.CreateInstance<T>();
            response.ErrCode = errCode;
            response.ErrMsg = errMsg;

            if (FORMAT_XML.Equals(format))
            {
                XmlDocument root = new XmlDocument();
                XmlElement bodyE = root.CreateElement(Constants.ERROR_RESPONSE);
                XmlElement codeE = root.CreateElement(Constants.ERROR_CODE);
                codeE.InnerText = errCode;
                bodyE.AppendChild(codeE);
                XmlElement msgE = root.CreateElement(Constants.ERROR_MSG);
                msgE.InnerText = errMsg;
                bodyE.AppendChild(msgE);
                root.AppendChild(bodyE);
                response.Body = root.OuterXml;
            }
            else
            {
                IDictionary<string, object> errObj = new Dictionary<string, object>();
                errObj.Add(Constants.ERROR_CODE, errCode);
                errObj.Add(Constants.ERROR_MSG, errMsg);
                IDictionary<string, object> root = new Dictionary<string, object>();
                root.Add(Constants.ERROR_RESPONSE, errObj);

                string body = JSON.ToJSON(root);
                response.Body = body;
            }
            return response;
        }
        #endregion
    }
}
