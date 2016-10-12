//-----------------------------------------------------------------------
// <copyright file="TclUtilsTests.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月29号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Test
{
    using NUnit.Framework;
    using System.Xml.Serialization;
    using LL.Framework.Tcl.Tcl.Util;

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
    }

    /// <summary>
    /// TCL工具类测试用例.
    /// </summary>
    [TestFixture]
    public class TclUtilsTests
    {
        /// <summary>
        /// 读取XML文件测试.
        /// </summary>
        [Test]
        public void ReadXmlTest()
        {
            string xmlFile = @"C:\Users\ligl\Desktop\AppConf.xml";
            System.Console.WriteLine(TclUtils.ReadFileString(xmlFile));
        }

        /// <summary>
        /// XML文件反序列化测试.
        /// </summary>
        [Test]
        public void XmlDeserializeTest()
        {
            string xmlFile = @"C:\Users\ligl\Desktop\AppConf.xml";
            string xmlString = TclUtils.ReadFileString(xmlFile);
            AppConf appConf = TclUtils.XmlDeserialize<AppConf>(xmlString);
            System.Console.WriteLine(appConf.Host);
            System.Console.WriteLine(appConf.Port);
            System.Console.WriteLine(appConf.Path);
        }

        /// <summary>
        /// 将对象转化为XML字符串.
        /// </summary>
        [Test]
        public void ObjectToXMLStringTest()
        {
            AppConf appConf = new AppConf();
            appConf.Host = "127.0.0.1";
            appConf.Port = "8080";
            appConf.Path = "test";
           System.Console.WriteLine(TclUtils.ObjectToXMLString(appConf));
        }

        /// <summary>
        /// 将对象转化为XML字符串并输出到硬盘.
        /// </summary>
        [Test]
        public void WriteXmlFile()
        {
            AppConf appConf = new AppConf();
            appConf.Host = "127.0.0.1";
            appConf.Port = "8080";
            appConf.Path = "test";
            TclUtils.WriteFile(@"C:\Users\ligl\Desktop\AppConf.xml", TclUtils.ObjectToXMLString(appConf));
        }
    }
}
