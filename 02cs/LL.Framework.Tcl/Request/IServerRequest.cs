//-----------------------------------------------------------------------
// <copyright file="IServerRequest.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Request
{
    using System.Collections.Generic;

    /// <summary>
    /// Server请求接口.
    /// </summary>
    public interface IServerRequest<T> where T : ServerResponse
    {
        /// <summary>
        /// 获取TOP的API名称.
        /// </summary>
        string GetApiName();

        /// <summary>
        /// 获取被调用的目标AppKey.
        /// 该方法在一般项目不适用.
        /// </summary>
        string GetTargetAppKey();

        /// <summary>
        /// 获取所有的Key-Value形式的文本请求参数字典.
        /// </summary>
        IDictionary<string, string> GetParameters();

        /// <summary>
        /// 获取自定义HTTP请求头参数.
        /// </summary>
        IDictionary<string, string> GetHeaderParameters();

        /// <summary>
        /// 客户端参数检查，减少服务端无效调用.
        /// </summary>
        void Validate();
    }
}
