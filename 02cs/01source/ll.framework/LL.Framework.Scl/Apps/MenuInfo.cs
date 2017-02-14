//-----------------------------------------------------------------------
// <copyright file="MenuInfo.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月28号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Scl.Apps
{
    using System.Xml.Serialization;
    using LL.Framework.Scl.Response;
    using System.Collections.Generic;

    /// <summary>
    /// 菜单功能信息.
    /// </summary>
    public class MenuInfo
    {
        /// <summary>
        /// 是否锁定当前数据不允许修改.
        /// </summary>
        [XmlIgnore]
        internal bool _lock = false;

        /// <summary>
        /// 菜单ID.
        /// </summary>
        public string MenuId
        {
            get { return MenuId; }
            set { if (!_lock) { MenuId = value; };}
        }

        /// <summary>
        /// 菜单代码.
        /// </summary>
        public string MenuCode
        {
            get { return MenuCode; }
            set { if (!_lock) { MenuCode = value; };}
        }

        /// <summary>
        /// 菜单名称.
        /// </summary>
        public string MenuName
        {
            get { return MenuName; }
            set { if (!_lock) { MenuName = value; };}
        }

        /// <summary>
        /// 父级ID.
        /// </summary>
        public string ParentId
        {
            get { return ParentId; }
            set { if (!_lock) { ParentId = value; };}
        }

        /// <summary>
        /// 菜单子功能.
        /// </summary>
        public Dictionary<string, FuncResponse> Funcs
        {
            get { return Funcs; }
            set { if (!_lock) { Funcs = value; };}
        }
    }
}
