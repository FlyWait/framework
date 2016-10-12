//-----------------------------------------------------------------------
// <copyright file="ServerAttribute.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月02号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    using System;
    using System.Reflection;

    /// <summary>
    /// 类型特性类.
    /// </summary>
    public class ServerAttribute
    {
        /// <summary>
        /// 元素名称.
        /// </summary>
        public string ItemName { get; set; }

        /// <summary>
        /// 元素类型.
        /// </summary>
        public Type ItemType { get; set; }

        /// <summary>
        /// 集合名称.
        /// </summary>
        public string ListName { get; set; }

        /// <summary>
        /// 集合类型.
        /// </summary>
        public Type ListType { get; set; }

        /// <summary>
        /// 方法信息.
        /// </summary>
        public MethodInfo Method { get; set; }
    }
}
