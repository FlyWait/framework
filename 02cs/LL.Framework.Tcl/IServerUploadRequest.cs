//-----------------------------------------------------------------------
// <copyright file="IServerUploadRequest.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月25号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl
{
    using LL.Framework.Tcl.Request;
    using LL.Framework.Tcl.Tcl.Util;
    using System.Collections.Generic;

    /// <summary>
    /// 上传请求接口，支持同时上传多个文件.
    /// </summary>
    public interface IServerUploadRequest<T> : IServerRequest<T> where T : ServerResponse
    {
        /// <summary>
        /// 获取所有的Key-Value形式的文件请求参数字典.其中：
        /// Key: 请求参数名
        /// Value: 文件对象
        /// </summary>
        /// <returns>文件请求参数字典.</returns>
        IDictionary<string, FileItem> GetFileParameters();
    }
}
