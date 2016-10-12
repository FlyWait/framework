//-----------------------------------------------------------------------
// <copyright file="UserInfoRequest.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月31号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Request
{
    using LL.Framework.Tcl;
    using LL.Framework.Tcl.Request;

    /// <summary>
    /// 获取用户信息.
    /// </summary>
    public class UserInfoRequest : BaseServerRequest<LL.Framework.Scl.Response.UserInfoResponse>
    {
        #region API请求属性.
        /// <summary>
        /// 用户代码.
        /// </summary>
        public string UserCode { get; set; }
        #endregion

        #region API请求固定方法
        /// <summary>
        /// API请求地址.
        /// </summary>
        /// <returns></returns>
        public override string GetApiName()
        {
            return "";
        }

        /// <summary>
        /// 请求前校验方法.
        /// </summary>
        public override void Validate()
        {
            return;
        }

        /// <summary>
        /// API请求参数构建.
        /// </summary>
        /// <returns></returns>
        public override System.Collections.Generic.IDictionary<string, string> GetParameters()
        {
            ServerDictionary serverDictionary = new ServerDictionary();
            serverDictionary.Add("userCode", UserCode);

            return serverDictionary;
        }
        #endregion
    }
}
