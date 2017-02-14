//-----------------------------------------------------------------------
// <copyright file="UserInfo.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月28号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Users
{
    using LL.Framework.Scl.Response;
    using LL.Framework.Tcl;
    using LL.Framework.Tcl.Tools.Encryption;
    using System;

    /// <summary>
    /// 系统用户信息一旦设置后为只读.
    /// </summary>
    public sealed class UserInfos
    {
        #region 外部属性
        /// <summary>
        /// 用户信息.
        /// </summary>
        public static UserInfoResponse UserInfo{get;private set;}
        #endregion

        #region 外部接口
        /// <summary>
        /// 用户注册接口,用户输入的密码与正确的用户密码信息相同则将用户注册到系统.
        /// </summary>
        /// <param name="userInfoJson">用户Json信息字符串.</param>
        /// <param name="userPass">用户密码.</param>
        /// <returns>注册结果.</returns>
        public static bool UserRegister(string userInfoJson, string userPass)
        {
            if(string.IsNullOrEmpty(userInfoJson))
            {
                return false;
            }

            UserInfoResponse userInfoCommand = null;
            try
            {
                userInfoCommand = FastJSON.JSON.ToObject<UserInfoResponse>(userInfoJson);
            }
            catch (Exception ex)
            {
                //throw new TclException(ex.Message, ex);
                System.Console.WriteLine(ex.Message);

                return false;
            }

            return UserAuthentication(userInfoCommand, userPass);
        }

        /// <summary>
        /// 用户认证成功后设置用户值.
        /// 若初始设置用户不用校验用户密码
        /// 若当前系统有用户信息则必须校验之前用户密码.
        /// </summary>
        /// <param name="userInfoCommand">用户信息类.</param>
        /// <param name="userPass">用户明文密码.</param>
        /// <returns></returns>
        private static bool UserAuthentication(UserInfoResponse userInfoCommand, string userPass)
        {
            //传入的用户信息为NULL则认为设置系统用户信息失败.
            if (userInfoCommand == null)
            {
                return false;
            }

            //未传入需要校验的用户密码则认为设置系统用户信息失败.
            if (string.IsNullOrEmpty(userInfoCommand.UserPass))
            {
                return false;
            }

            //若当前系统用户未设置则不需要校验之前用户的密码
            if (UserInfo == null)
            {
                if (EncryptionTools.MD5Valid(userInfoCommand.UserPass, userPass, userInfoCommand.Salt))
                {
                    UserInfo = userInfoCommand;
                    UserInfo._lock = true;//锁定数据.
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if (EncryptionTools.MD5Valid(UserInfo.UserPass, userPass, UserInfo.Salt))//校验之前用户的密码
                {
                    UserInfo = userInfoCommand;
                    UserInfo._lock = true;//锁定数据
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        #endregion
    }
}
