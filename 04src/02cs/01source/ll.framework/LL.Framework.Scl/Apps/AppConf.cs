//-----------------------------------------------------------------------
// <copyright file="AppConf.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月28号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Apps
{
    using System.Xml.Serialization;

    /// <summary>
    /// 系统应用配置文件.
    /// </summary>
    public class AppConf
    {
        /// <summary>
        /// 服务器地址.
        /// </summary>
        public string Host { get; set; }

        /// <summary>
        /// 端口号
        /// </summary>
        public string Port { get; set; }

        /// <summary>
        /// WEB服务路径.
        /// </summary>
        public string Path { get; set; }

        /// <summary>
        /// 获取完整的服务器路径.
        /// </summary>
        public string GetUrl()
        {
            return string.Format(@"http://{0}:{1}/{2}", Host, Port, Path);
        }
    }
}
