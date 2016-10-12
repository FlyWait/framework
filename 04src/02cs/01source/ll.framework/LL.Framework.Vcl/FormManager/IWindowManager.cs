//-----------------------------------------------------------------------
// <copyright file="IWindowManager.cs">
// </copyright>
// <author>liul</author>
// <date>2016年1月27号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Vcl.FormManager
{
    /// <summary>
    /// 窗体管理接口
    /// </summary>
    public interface IWindowManager
    {
        #region 一级菜单
        /// <summary>
        /// 添加一级菜单.
        /// </summary>
        /// <param name="windowMenu">窗体对象.</param>
        /// <param name="menu">菜单对象.</param>
        /// <returns>添加结果.</returns>
        bool AddFirstMenu(object windowMenu, object menu);

        /// <summary>
        /// 移除一级菜单.
        /// </summary>
        /// <param name="windowMenu">窗体对象.</param>
        /// <param name="menu">菜单对象.</param>
        /// <returns>移除结果.</returns>
        bool RemoveFirstMenu(object windowMenu, object menu);
        #endregion
    }
}
