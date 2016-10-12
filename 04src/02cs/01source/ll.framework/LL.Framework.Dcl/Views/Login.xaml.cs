using FirstFloor.ModernUI.Windows.Controls;
using LL.Framework.dcl.ViewModels;
using LL.Framework.Scl.Apps;
using LL.Framework.Tcl;
using System.Windows;

namespace LL.Framework.dcl.Views
{
    /// <summary>
    /// Login.xaml 的交互逻辑.
    /// </summary>
    public partial class Login : ModernWindow,IHavePassword
    {
        /// <summary>
        /// 构造函数.
        /// </summary>
        public Login()
        {
            InitializeComponent();
            //初始化应用配置信息.
            if (!AppInfo.InitAppConf())
            {
                ModernDialog.ShowMessage("加载系统应用配置文件失败!","错误",MessageBoxButton.OK);
                Application.Current.Shutdown();
                return;
            }
            //初始化应用通讯类
            AppInfo.AppDefaultCommunicat = new DefaultClient(AppInfo.AppConfInfo.GetUrl(), "WPF");
            //绑定登陆窗体VM类.
            this.DataContext = new UserInfoVM();
        }

        /// <summary>
        /// 获取密码.
        /// </summary>
        public System.Security.SecureString Password
        {
            get
            {
                return UserPass.SecurePassword;
            }
        }
    }
}
