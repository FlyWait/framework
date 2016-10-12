//-----------------------------------------------------------------------
// <copyright file="UserInfoCommand.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月28号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Response
{
    using LL.Framework.Tcl;
    using System.Xml.Serialization;

    /// <summary>
    /// 用户信息领域对象.
    /// </summary>
    public class UserInfoResponse:ServerResponse
    {
        /// <summary>
        /// 是否锁定当前数据不允许修改.
        /// </summary>
        [XmlIgnore]
        internal bool _lock = false;

        /// <summary>
        /// 用户代码
        /// </summary>
        [XmlElement("userCode")]
        public string UserCode
        {
            get { return UserCode; }
            set { if (!_lock) { UserCode = value; };} 
        }

        /// <summary>
        /// 用户名称
        /// </summary>
        [XmlElement("userName")]
        public string UserName 
        {
            get { return UserName; }
            set { if (!_lock) { UserName = value; };} 
        }

        /// <summary>
        /// 用户密码
        /// </summary>
        [XmlElement("userPass")]
        public string UserPass
        {
            get { return UserPass; }
            set { if (!_lock) { UserPass = value; };}
        }

        /// <summary>
        /// 盐值
        /// </summary>
        [XmlElement("salt")]
        public string Salt
        {
            get { return Salt; }
            set { if (!_lock) { Salt = value; };}
        }

        /// <summary>
        /// 系统账户Id
        /// </summary>
        [XmlElement("accountId")]
        public string AccountId
        {
            get { return AccountId; }
            set { if (!_lock) { AccountId = value; };}
        }
    }
}
