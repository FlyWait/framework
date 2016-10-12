//-----------------------------------------------------------------------
// <copyright file="IHavePassword.cs">
// </copyright>
// <author>liul</author>
// <date>2016年1月13号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.dcl.ViewModels
{
    /// <summary>
    /// 密码接口类.
    /// </summary>
    public interface IHavePassword
    {
        /// <summary>
        /// 用户密码字段.
        /// </summary>
        System.Security.SecureString Password { get; }
    }
}
