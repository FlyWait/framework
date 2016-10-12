//-----------------------------------------------------------------------
// <copyright file="JsonConvertTests.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Test
{
    using System;
    using NUnit.Framework;
    using System.Xml.Serialization;
    using LL.Framework.Tcl.Parser;
    using System.Text;
    using FastJSON;

    public class UserInfoCommand:ServerResponse
    {
        [XmlElement("userCode")]
        public String UserCode { get; set; }
        [XmlElement("userName")]
        public String UserName { get; set; }
        [XmlElement("accountId")]
        public String AccountId { get; set; }
        [XmlElement("userPass")]
        public String UserPass { get; set; }
        [XmlElement("salt")]
        public String Salt { get; set; }
    }

    [TestFixture]
    public class JsonConvertTests
    {
        /// <summary>
        /// Json平台Json字符串转化测试
        /// </summary>
        [Test]
        public void JavaJsonStringTest1()
        {
            StringBuilder json = new StringBuilder();
            json.Append("{ \"userCode\": \"T100000\", \"userName\": \"liul\", \"userPass\": \"123456\",\"accountId\": \"10000\",\"salt\": \"654d\"}");
            IServerParser serverParser = new ServerJsonSimplifyParser();
            UserInfoCommand test1 = new UserInfoCommand();
            test1.Body = json.ToString();
            UserInfoCommand userInfoCommand = serverParser.Parse<UserInfoCommand>(test1.Body);
            Assert.AreEqual("T100000", userInfoCommand.UserCode);
        }

        /// <summary>
        /// C#对象转化为Json字符串
        /// </summary>
        [Test]
        public void CSharpJsonStringTest1()
        {
            UserInfoCommand userInfoCommand = new UserInfoCommand();
            userInfoCommand.UserCode = "T100001";
            userInfoCommand.UserName = "123456";
            userInfoCommand.UserPass = "654321";
            userInfoCommand.Salt = "dah";
            userInfoCommand.AccountId = "29d";
            JSONParameters jsonParam = new JSONParameters();
            jsonParam.UseExtensions = false;
            Console.WriteLine(JSON.ToJSON(userInfoCommand, jsonParam));
        }
    }
}