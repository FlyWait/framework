//-----------------------------------------------------------------------
// <copyright file="ServerJsonSimplifyParser.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月03号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    using FastJSON;
    using System;
    using System.Collections;

    /// <summary>
    /// Server JSON响应通用解释器.
    /// </summary>
    public class ServerJsonSimplifyParser : ServerJsonParser
    {
        /// <summary>
        /// JSON响应解释.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="body">JSON字符串.</param>
        /// <returns>JSON反序列话后的领域对象.</returns>
        public override T Parse<T>(string body) 
        {
            T rsp = null;

            IDictionary rootJson = JSON.Parse(body) as IDictionary;
            if (rootJson != null)
            {
                IDictionary data = rootJson;
                if (rootJson.Contains(Constants.ERROR_RESPONSE))
                {
                    data = rootJson[Constants.ERROR_RESPONSE] as IDictionary;
                }

                if (data != null)
                {
                    IServerReader reader = new ServerJsonSimplifyReader(data);
                    rsp = FromJson(reader, typeof(T)) as T;
                }
            }

            if (rsp == null)
            {
                rsp = Activator.CreateInstance<T>();
            }

            if (rsp != null)
            {
                rsp.Body = body;
            }

            return rsp;
        }
    }
}
