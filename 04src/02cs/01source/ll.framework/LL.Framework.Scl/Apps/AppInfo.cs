//-----------------------------------------------------------------------
// <copyright file="AppInfo.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Apps
{
    using LL.Framework.Tcl;
    using LL.Framework.Tcl.Tcl.Util;
    using System.IO;

    /// <summary>
    /// 应用信息.
    /// </summary>
    public sealed class AppInfo
    {
        #region 外部属性
        /// <summary>
        /// 应用配置文件信息.
        /// </summary>
        public static AppConf AppConfInfo { get; private set; }

        /// <summary>
        /// 获取应用程序的运行目录.
        /// </summary>
        public static readonly string AppRunPath = System.IO.Directory.GetCurrentDirectory();

        /// <summary>
        /// 应用程序默认通讯类.
        /// </summary>
        public static IClient AppDefaultCommunicat { get; set; }
        #endregion

        #region 外部接口方法
        /// <summary>
        /// 初始化读取应用配置信息.
        /// </summary>
        /// <returns>配置文件是否读取成功.</returns>
        public static bool InitAppConf()
        {
            string configPath = AppRunPath + @"\Config\AppConf.xml";
            if (!File.Exists(configPath))
            {
                return false;
            }

            string xmlString = TclUtils.ReadFileString(configPath);
            if (string.IsNullOrEmpty(xmlString))
            {
                return false;
            }
            AppConf appConf = TclUtils.XmlDeserialize<AppConf>(xmlString);
            AppConfInfo = appConf;

            return true;
        }
        #endregion
    }
}
