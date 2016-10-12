//-----------------------------------------------------------------------
// <copyright file="FuncCommand.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月28号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Response
{
    using System.Xml.Serialization;

    /// <summary>
    /// 菜单功能领域对象.
    /// </summary>
    public class FuncResponse
    {
        /// <summary>
        /// 是否锁定当前数据不允许修改.
        /// </summary>
        [XmlIgnore]
        internal bool _lock = false;

        /// <summary>
        /// 功能ID.
        /// </summary>
        [XmlElement("funcId")]
        public string FuncId
        {
            get { return FuncId; }
            set { if (!_lock) { FuncId = value; };}
        }

        /// <summary>
        /// 功能代码.
        /// </summary>
        [XmlElement("funcCode")]
        public string FuncCode
        {
            get { return FuncCode; }
            set { if (!_lock) { FuncCode = value; };}
        }

        /// <summary>
        /// 功能名称.
        /// </summary>
        [XmlElement("funcName")]
        public string FuncName
        {
            get { return FuncName; }
            set { if (!_lock) { FuncName = value; };}
        }

        /// <summary>
        /// 父级ID.
        /// </summary>
        [XmlElement("parentId")]
        public string ParentId
        {
            get { return ParentId; }
            set { if (!_lock) { ParentId = value; };}
        }
        /// <summary>
        /// 子功能数.
        /// </summary>
        [XmlElement("subFuncNum")]
        public int SubFuncNum
        {
            get { return SubFuncNum; }
            set { if (!_lock) { SubFuncNum = value; };}
        }
    }
}
