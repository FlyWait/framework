//-----------------------------------------------------------------------
// <copyright file="IServerParser.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月25号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    /// <summary>
    /// Server API响应解释器接口.响应格式可以是XML、JSON.
    /// </summary>
    public interface IServerParser
    {
        /// <summary>
        /// 把响应字符串解释成相应的领域对象.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="body">响应字符串.</param>
        /// <returns>领域对象.</returns>
        T Parse<T>(string body) where T : ServerResponse;
    }
}
