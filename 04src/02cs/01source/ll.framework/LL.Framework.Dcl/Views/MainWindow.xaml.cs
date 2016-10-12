using FirstFloor.ModernUI.Windows.Controls;
using LL.Framework.Vcl.FormManager;

namespace LL.Framework.dcl
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : ModernWindow, IWindowManager
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        #region 窗口管理接口实现
        #region 外部服务接口
        /// <summary>
        /// 添加一级菜单.
        /// </summary>
        /// <param name="windowMenu">窗体对象.</param>
        /// <param name="menu">菜单对象.</param>
        /// <returns>添加结果.</returns>
        public bool AddFirstMenu(object windowMenu, object menu)
        {
            throw new System.NotImplementedException();
        }

        /// <summary>
        /// 移除一级菜单.
        /// </summary>
        /// <param name="windowMenu">窗体对象.</param>
        /// <param name="menu">菜单对象.</param>
        /// <returns>移除结果.</returns>
        public bool RemoveFirstMenu(object windowMenu, object menu)
        {
            throw new System.NotImplementedException();
        }
        #endregion

        #region 内部核心方法
        #endregion
        #endregion
    }
}
