//-----------------------------------------------------------------------
// <copyright file="IClient.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl
{
    using LL.Framework.Tcl.Request;

    /// <summary>
    /// 通讯客户端.
    /// </summary>
    public interface IClient
    {
        /// <summary>
        /// 执行公开API请求.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="request">具体的API请求.</param>
        /// <returns>领域对象.</returns>
        T Execute<T>(IServerRequest<T> request) where T : ServerResponse;

        /// <summary>
        /// 执行TOP隐私API请求.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="request">具体的API请求.</param>
        /// <param name="session">用户会话码.</param>
        /// <returns>领域对象.</returns>
        T Execute<T>(IServerRequest<T> request, string session) where T : ServerResponse;
    }
}
