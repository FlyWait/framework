//-----------------------------------------------------------------------
// <copyright file="IServerReader.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月02号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    using System;
    using System.Collections;

    /// <summary>
    /// 转化器委托.
    /// </summary>
    /// <param name="reader">转化器.</param>
    /// <param name="type">属性类型.</param>
    /// <returns>转化后的对象.</returns>
    public delegate object DServerConvert(IServerReader reader, Type type);

    /// <summary>
    /// Server API响应读取器接口.响应格式可以是XML, JSON.
    /// </summary>
    public interface IServerReader
    {
        /// <summary>
        /// 判断响应中是否包含指定的属性.
        /// </summary>
        /// <param name="name">属性名称.</param>
        /// <returns>true/false.</returns>
        bool HasReturnField(object name);

        /// <summary>
        /// 获取值类型属性的值.
        /// </summary>
        /// <param name="name">属性名称.</param>
        /// <returns>值对象.</returns>
        object GetPrimitiveObject(object name);

        /// <summary>
        /// 获取引用类型的值.
        /// </summary>
        /// <param name="name">属性名称.</param>
        /// <param name="type">引用类型.</param>
        /// <param name="convert">转换器.</param>
        /// <returns>引用对象.</returns>
        object GetReferenceObject(object name, Type type, DServerConvert convert);

        /// <summary>
        /// 获取列表类型的值.
        /// </summary>
        /// <param name="listName">列表属性名称.</param>
        /// <param name="itemName">列表项名称.</param>
        /// <param name="type">引用类型.</param>
        /// <param name="convert">转换器.</param>
        /// <returns>列表对象.</returns>
        IList GetListObjects(string listName, string itemName, Type type, DServerConvert convert);
    }
}
