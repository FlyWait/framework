//-----------------------------------------------------------------------
// <copyright file="ServerXmlParser.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月03号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using System.Text;
    using System.Text.RegularExpressions;
    using System.Threading;
    using System.Xml.Serialization;

    /// <summary>
    /// Server XML响应通用解释器.
    /// </summary>
    public class ServerXmlParser : IServerParser
    {
        #region 全局私有只读变量
        /// <summary>
        /// 截取XML字符串中的根节点的正则表达式.
        /// </summary>
        private static readonly Regex regex = new Regex("<(\\w+?)[ >]", RegexOptions.Compiled);

        /// <summary>
        /// 锁定文件的读操作但允许读取.
        /// </summary>
        private static readonly ReaderWriterLock rwLock = new ReaderWriterLock();

        /// <summary>
        /// 对象序列化信息字典.
        /// </summary>
        private static readonly Dictionary<string, XmlSerializer> parsers = new Dictionary<string, XmlSerializer>();
        #endregion

        #region 外部服务方法
        /// <summary>
        /// XML响应解释.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="body">XML字符串.</param>
        /// <returns>装载XML数据的领域对象.</returns>
        public T Parse<T>(string body) where T : ServerResponse
        {
            Type type = typeof(T);
            string rootTagName = GetRootElement(body);

            string key = type.FullName;
            if (Constants.ERROR_RESPONSE.Equals(rootTagName))
            {
                key += ("_" + Constants.ERROR_RESPONSE);
            }

            XmlSerializer serializer = null;
            bool incl = false;

            rwLock.AcquireReaderLock(50);
            try
            {
                if (rwLock.IsReaderLockHeld)
                {
                    incl = parsers.TryGetValue(key, out serializer);
                }
            }
            finally
            {
                if (rwLock.IsReaderLockHeld)
                {
                    rwLock.ReleaseReaderLock();
                }
            }

            if (!incl || serializer == null)
            {
                XmlAttributes rootAttrs = new XmlAttributes();
                rootAttrs.XmlRoot = new XmlRootAttribute(rootTagName);

                XmlAttributeOverrides attrOvrs = new XmlAttributeOverrides();
                attrOvrs.Add(type, rootAttrs);

                serializer = new XmlSerializer(type, attrOvrs);

                rwLock.AcquireWriterLock(50);
                try
                {
                    if (rwLock.IsWriterLockHeld)
                    {
                        parsers[key] = serializer;
                    }
                }
                finally
                {
                    if (rwLock.IsWriterLockHeld)
                    {
                        rwLock.ReleaseWriterLock();
                    }
                }
            }

            object obj = null;
            using (System.IO.Stream stream = new MemoryStream(Encoding.UTF8.GetBytes(body)))
            {
                obj = serializer.Deserialize(stream);
            }

            T rsp = obj as T;
            if (rsp != null)
            {
                rsp.Body = body;
            }
            return rsp;
        }
        #endregion

        #region 私有方法
        /// <summary>
        /// 获取XML响应的根节点名称.
        /// </summary>
        private string GetRootElement(string body)
        {
            Match match = regex.Match(body);
            if (match.Success)
            {
                return match.Groups[1].ToString();
            }
            else
            {
                throw new TclException("Invalid XML response format!");
            }
        }
        #endregion
    }
}
