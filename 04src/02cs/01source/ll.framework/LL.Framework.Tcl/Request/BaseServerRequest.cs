//-----------------------------------------------------------------------
// <copyright file="BaseServerRequest.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Request
{
    using System.Collections.Generic;

    /// <summary>
    /// 基础Server请求类，存放一些通用的请求参数.
    /// </summary>
    public abstract  class BaseServerRequest<T> : IServerRequest<T> where T : ServerResponse
    {
        /// <summary>
        /// HTTP请求头参数
        /// </summary>
        public ServerDictionary HeaderParams { get; set; }
        /// <summary>
        /// 自定义表单参数
        /// </summary>
        public ServerDictionary OtherParams { get; set; }
        /// <summary>
        /// 请求目标AppKey
        /// </summary>
        public string TargetAppKey { get; set; }
        /// <summary>
        /// 指定哪个入参是混淆参数
        /// </summary>
        public string ServerMixParams { get; set; }

        /// <summary>
        /// 添加自定义表单参数.
        /// </summary>
        /// <param name="key">Key值.</param>
        /// <param name="value">Values值.</param>
        public void AddOtherParameter(string key, string value)
        {
            if (this.OtherParams == null)
            {
                this.OtherParams = new ServerDictionary();
            }
            this.OtherParams.Add(key, value);
        }

        /// <summary>
        /// 添加HTTP请求头参数.
        /// </summary>
        /// <param name="key">参数名称.</param>
        /// <param name="value">参数值.</param>
        public void AddHeaderParameter(string key, string value)
        {
            GetHeaderParameters().Add(key, value);
        }

        /// <summary>
        /// 获取HTTP头参数.
        /// </summary>
        /// <returns>HTTP头参数字典.</returns>
        public IDictionary<string, string> GetHeaderParameters()
        {
            if (this.HeaderParams == null)
            {
                this.HeaderParams = new ServerDictionary();
            }
            return this.HeaderParams;
        }

        /// <summary>
        /// 获取请求APP的Key值.
        /// </summary>
        /// <returns>目标APPKey值.</returns>
        public string GetTargetAppKey()
        {
            return this.TargetAppKey;
        }

        /// <summary>
        /// 获取API名称.
        /// </summary>
        /// <returns></returns>
        public abstract string GetApiName();

        /// <summary>
        /// 数据验证方法.
        /// </summary>
        public abstract void Validate();

        /// <summary>
        /// 获取请求参数字典.
        /// </summary>
        /// <returns>请求参数字典.</returns>
        public abstract IDictionary<string, string> GetParameters();
    }
}
