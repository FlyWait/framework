//-----------------------------------------------------------------------
// <copyright file="WebUtils.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月25号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Tcl.Util
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using System.Net;
    using System.Net.Security;
    using System.Security.Cryptography.X509Certificates;
    using System.Text;

    /// <summary>
    /// 简单通讯类.
    /// <remark>
    /// author liul
    /// date 2015-11-25
    /// version:
    /// 1.0 create
    /// </remark>
    /// </summary>
    public class WebUtils
    {
        #region 内部私有变量
        /// <summary>
        /// Cookie值.
        /// </summary>
        private string _cookie = string.Empty;

        /// <summary>
        /// 请求超时时间.
        /// </summary>
        private int _timeout = 30000;

        /// <summary>
        /// 等待读取数据完成的超时时间.
        /// </summary>
        private int _readWriteTimeout = 60000;

        /// <summary>
        /// 是否保持用户会话.
        /// </summary>
        private bool _keepSession = true;
        #endregion

        #region 外部属性
        /// <summary>
        /// 获取或者设置请求超时时间.
        /// </summary>
        public int Timeout
        {
            get { return this._timeout; }
            set { this._timeout = value; }
        }

        /// <summary>
        /// 获取或者设置等待读取数据完成的超时时间.
        /// </summary>
        public int ReadWriteTimeout
        {
            get { return this._readWriteTimeout; }
            set { this._readWriteTimeout = value; }
        }

        /// <summary>
        /// 获取或者设置用户会话.
        /// </summary>
        public string Session
        {
            get { return this._cookie; }
            set { this._cookie = value; }
        }

        /// <summary>
        /// 获取或者设置用户会话是否持久化.
        /// </summary>
        public bool KeepSeesion
        {
            get { return this._keepSession; }
            set { this._keepSession = value; }
        }
        #endregion

        #region 外部服务方法
        /// <summary>
        /// 执行HTTP GET请求.
        /// </summary>
        /// <param name="url">请求地址.</param>
        /// <param name="textParams">请求文本参数.</param>
        /// <returns>HTTP响应.</returns>
        public string DoGet(string url, IDictionary<string, string> textParams)
        {
            return DoGet(url, textParams, null);
        }

        /// <summary>
        /// 执行HTTP GET请求.
        /// </summary>
        /// <param name="url">请求地址.</param>
        /// <param name="textParams">请求文本参数.</param>
        /// <param name="headerParams">请求头部参数.</param>
        /// <returns>HTTP响应.</returns>
        public string DoGet(string url, IDictionary<string, string> textParams, IDictionary<string, string> headerParams)
        {
            if (textParams != null && textParams.Count > 0)
            {
                this.BuildGetUrl(url, textParams);
            }

            HttpWebRequest request = GetWebRequest(url, "GET", headerParams);
            request.ContentType = "application/x-www-form-urlencoded;charset=utf-8";

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            this.SaveSeesion(response);
            Encoding encoding = Encoding.GetEncoding(response.CharacterSet);
            return GetResponseAsString(response, encoding);
        }

        /// <summary>
        /// 执行HTTP POST请求.
        /// </summary>
        /// <param name="url">请求地址.</param>
        /// <param name="textParams">请求文本参数.</param>
        /// <param name="headerParams">请求头部参数.</param>
        /// <returns>HTTP响应.</returns>
        public string DoPost(string url, IDictionary<string, string> textParams, IDictionary<string, string> headerParams)
        {
            HttpWebRequest request = GetWebRequest(url, "POST", headerParams);
            request.ContentType = "application/x-www-form-urlencoded;charset=utf-8";

            byte[] postData = Encoding.UTF8.GetBytes(BuildQuery(textParams));
            System.IO.Stream reqStream = request.GetRequestStream();
            reqStream.Write(postData, 0, postData.Length);
            reqStream.Close();

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            this.SaveSeesion(response);
            Encoding encoding = Encoding.GetEncoding(response.CharacterSet);
            return GetResponseAsString(response, encoding);
        }

        /// <summary>
        /// 执行HTTP POST请求.
        /// </summary>
        /// <param name="url">请求地址.</param>
        /// <param name="textParams">请求文本参数.</param>
        /// <returns>HTTP响应.</returns>
        public string DoPost(string url, IDictionary<string, string> textParams)
        {
            return DoPost(url, textParams, null);
        }

        /// <summary>
        /// 执行带文件上传的HTTP POST请求.
        /// </summary>
        /// <param name="url">请求地址.</param>
        /// <param name="textParams">请求文本参数.</param>
        /// <param name="fileParams">请求文件参数.</param>
        /// <param name="headerParams">请求头部参数.</param>
        /// <returns>HTTP响应.</returns>
        public string DoPost(string url, IDictionary<string, string> textParams, IDictionary<string, FileItem> fileParams, IDictionary<string, string> headerParams)
        {
            // 如果没有文件参数，则走普通POST请求
            if (fileParams == null || fileParams.Count == 0)
            {
                return DoPost(url, textParams, headerParams);
            }

            string boundary = DateTime.Now.Ticks.ToString("X"); // 随机分隔线

            HttpWebRequest request = GetWebRequest(url, "POST", headerParams);
            request.ContentType = "multipart/form-data;charset=utf-8;boundary=" + boundary;

            System.IO.Stream reqStream = request.GetRequestStream();
            byte[] itemBoundaryBytes = Encoding.UTF8.GetBytes("\r\n--" + boundary + "\r\n");
            byte[] endBoundaryBytes = Encoding.UTF8.GetBytes("\r\n--" + boundary + "--\r\n");

            // 组装文本请求参数
            string textTemplate = "Content-Disposition:form-data;name=\"{0}\"\r\nContent-Type:text/plain\r\n\r\n{1}";
            IEnumerator<KeyValuePair<string, string>> textEnum = textParams.GetEnumerator();
            while (textEnum.MoveNext())
            {
                string textEntry = string.Format(textTemplate, textEnum.Current.Key, textEnum.Current.Value);
                byte[] itemBytes = Encoding.UTF8.GetBytes(textEntry);
                reqStream.Write(itemBoundaryBytes, 0, itemBoundaryBytes.Length);
                reqStream.Write(itemBytes, 0, itemBytes.Length);
            }

            // 组装文件请求参数
            string fileTemplate = "Content-Disposition:form-data;name=\"{0}\";filename=\"{1}\"\r\nContent-Type:{2}\r\n\r\n";
            IEnumerator<KeyValuePair<string, FileItem>> fileEnum = fileParams.GetEnumerator();
            while (fileEnum.MoveNext())
            {
                string key = fileEnum.Current.Key;
                FileItem fileItem = fileEnum.Current.Value;
                string fileEntry = string.Format(fileTemplate, key, fileItem.GetFileName(), fileItem.GetMimeType());
                byte[] itemBytes = Encoding.UTF8.GetBytes(fileEntry);
                reqStream.Write(itemBoundaryBytes, 0, itemBoundaryBytes.Length);
                reqStream.Write(itemBytes, 0, itemBytes.Length);

                byte[] fileBytes = fileItem.GetContent();
                reqStream.Write(fileBytes, 0, fileBytes.Length);
            }

            reqStream.Write(endBoundaryBytes, 0, endBoundaryBytes.Length);
            reqStream.Close();

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            this.SaveSeesion(response);
            Encoding encoding = Encoding.GetEncoding(response.CharacterSet);
            return GetResponseAsString(response, encoding);
        }

        /// <summary>
        /// Https安全认证方法.
        /// </summary>
        /// <param name="sender">调用者.</param>
        /// <param name="certificate"></param>
        /// <param name="chain"></param>
        /// <param name="errors"></param>
        /// <returns></returns>
        public bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors errors)
        { //直接确认，否则打不开
            return true;
        }

        /// <summary>
        /// 获取Http连接类.
        /// </summary>
        /// <param name="url">URL地址.</param>
        /// <param name="method">请求方法(如Get、Post).</param>
        /// <param name="headerParams">请求头部参数.</param>
        /// <returns>HttpWebRequest类.</returns>
        public HttpWebRequest GetWebRequest(string url, string method, IDictionary<string, string> headerParams)
        {
            HttpWebRequest request = null;  
            if (url.Contains("https"))
            {
                ServicePointManager.ServerCertificateValidationCallback = new RemoteCertificateValidationCallback(CheckValidationResult);
                request = (HttpWebRequest)WebRequest.CreateDefault(new Uri(url));
            }
            else
            {
                request = (HttpWebRequest)WebRequest.Create(url);
            }

            if (headerParams != null && headerParams.Count > 0)
            {
                foreach (string key in headerParams.Keys)
                {
                    request.Headers.Add(key, headerParams[key]);
                }
            }

            /*用户会话持久化*/
            if (!string.IsNullOrEmpty(this._cookie) && this._keepSession)
            {
                request.Headers.Set("Cookie", this._cookie);
            }

            request.ServicePoint.Expect100Continue = false;
            request.Method = method;
            request.KeepAlive = true;
            request.UserAgent = "llNet";
            request.Timeout = this._timeout;
            request.ReadWriteTimeout = this.ReadWriteTimeout;

            return request;
        }

        /// <summary>
        /// 把响应流转换为文本.
        /// </summary>
        /// <param name="response">响应流对象.</param>
        /// <param name="encoding">编码方式.</param>
        /// <returns>响应文本.</returns>
        public string GetResponseAsString(HttpWebResponse response, Encoding encoding)
        {
            System.IO.Stream stream = null;
            StreamReader reader = null;

            try
            {
                // 以字符流的方式读取HTTP响应
                stream = response.GetResponseStream();
                reader = new StreamReader(stream, encoding);
                return reader.ReadToEnd();
            }
            finally
            {
                // 释放资源
                if (stream != null) stream.Close();
                if (response != null) response.Close();
            }
        }

        /// <summary>
        /// 组装普通文本请求参数.
        /// </summary>
        /// <param name="parameters">Key-Value形式请求参数字典.</param>
        /// <returns>URL编码后的请求数据.</returns>
        public static string BuildQuery(IDictionary<string, string> parameters)
        {
            StringBuilder postData = new StringBuilder();
            bool hasParam = false;

            IEnumerator<KeyValuePair<string, string>> dem = parameters.GetEnumerator();
            while (dem.MoveNext())
            {
                string name = dem.Current.Key;
                string value = dem.Current.Value;

                // 忽略参数名或参数值为空的参数
                if (!string.IsNullOrEmpty(name) && !string.IsNullOrEmpty(value))
                {
                    if (hasParam)
                    {
                        postData.Append("&");
                    }

                    postData.Append(name);
                    postData.Append("=");
                    postData.Append(System.Web.HttpUtility.UrlEncode(value, Encoding.UTF8));
                    hasParam = true;
                }
            }

            return postData.ToString();
        }

        /// <summary>
        /// 组装GET请求URL.
        /// </summary>
        /// <param name="url">请求地址</param>
        /// <param name="parameters">请求参数</param>
        /// <returns>带参数的GET请求URL</returns>
        public string BuildGetUrl(string url, IDictionary<string, string> parameters)
        {
            StringBuilder realUrl = new StringBuilder(url);
            if (parameters != null && parameters.Count > 0)
            {
                if (realUrl.ToString().Contains("?"))
                {
                    realUrl.Append("&");
                }
                else
                {
                    realUrl.Append("?");
                }
                realUrl.Append(BuildQuery(parameters));
            }
            return realUrl.ToString();
        }
        #endregion

        #region 内部私有方法
        /// <summary>
        /// 保存用户会话ID.
        /// </summary>
        /// <param name="response">请求响应.</param>
        private void SaveSeesion(HttpWebResponse response)
        {
            if (this._keepSession)
            {
                //若返回新的Cookie值则保存
                string cookie = response.Headers.Get("Set-Cookie");
                if (!string.IsNullOrEmpty(cookie))
                {
                    this._cookie = cookie;
                }
            }
        }
        #endregion
    }
}
