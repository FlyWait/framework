//-----------------------------------------------------------------------
// <copyright file="UserInfoVM.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月29号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.dcl.ViewModels
{
    using FirstFloor.ModernUI.Windows.Controls;
    using LL.Framework.Scl.Apps;
    using LL.Framework.Scl.Request;
    using LL.Framework.Scl.Response;
    using LL.Framework.Tcl.MVVM;
    using LL.Framework.Tcl.Tools.Encryption;
    using System;
    using System.Windows;

    /// <summary>
    /// 用户信息对象.
    /// </summary>
    public class UserInfoVM : DataNotification
    {
        #region 构造函数
        /// <summary>
        /// 对象创建时将绑定数据操作事件.
        /// </summary>
        public UserInfoVM()
        {
            //注册用户认证命令
            this.AuthenticationCommand = new DataDelegateCommand<IHavePassword>(this.Authentication);
            //系统退出命令
            this.ExitCommand = new DataDelegateCommand<UserInfoVM>(new Action<UserInfoVM>(this.Exit));
        }
        #endregion

        #region 数据属性定义
        private string userCode;

        /// <summary>
        /// 用户代码.
        /// </summary>
        public string UserCode
        {
            get { return userCode; }
            set
            {
                userCode = value;
                this.OnPropertyChanged("UserCode");
            }
        }

        private string userPass;

        /// <summary>
        /// 用户密码.
        /// </summary>
        public string UserPass
        {
            get { return userPass; }
            set
            {
                userPass = value;
                this.OnPropertyChanged("UserPass");
            }
        }

        private string title = "登录";

        /// <summary>
        /// 登录窗体名称.
        /// </summary>
        public string Title
        {
            get { return title; }
        }
        
        #endregion

        #region 数据操作定义
        /// <summary>
        /// 用户认证命令.
        /// </summary>
        public DataDelegateCommand<IHavePassword> AuthenticationCommand { get; private set; }

        /// <summary>
        /// 系统推出命令.
        /// </summary>
        public DataDelegateCommand<UserInfoVM> ExitCommand { get; private set; }

        /// <summary>
        /// 用户认证方法.
        /// </summary>
        /// <param name="password"></param>
        private void Authentication(IHavePassword password)
        {
            if (password != null)
            {
                userPass = EncryptionTools.ConvertToUnsecureString(password.Password);
            }
            UserInfoResponse userInfoResponse = AppInfo.AppDefaultCommunicat.Execute<UserInfoResponse>(new UserInfoRequest());
            if (userInfoResponse.IsError)
            {
                ModernDialog.ShowMessage(userInfoResponse.ErrCode + "{" + userInfoResponse.ErrMsg + "}", "错误", MessageBoxButton.OK);
            }else
            {
                ModernDialog.ShowMessage("验证成功!","成功",MessageBoxButton.OK);
            }
        }

        /// <summary>
        /// 是否可以进行用户认证方法.
        /// </summary>
        /// <param name="secureString">参数.</param>
        /// <returns>是否可以继续执行认证.</returns>
        private bool CanAuthentication(IHavePassword password)
        {
            if (password != null)
            {
                userPass = EncryptionTools.ConvertToUnsecureString(password.Password);

                if(!string.IsNullOrEmpty(userPass))
                {

                    return true;
                }
            }
            ModernDialog.ShowMessage("密码不能为空!", "提示", MessageBoxButton.OK);

            return false;
        }

        /// <summary>
        /// 系统退出方法.
        /// </summary>
        /// <param name="parameter">参数.</param>
        private void Exit(object parameter)
        {
            if(MessageBoxResult.OK.Equals(ModernDialog.ShowMessage("是否退出系统!","警告",MessageBoxButton.OKCancel)))
            {
                Application.Current.Shutdown();
            }
        }
        #endregion
    }
}
