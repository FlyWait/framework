//-----------------------------------------------------------------------
// <copyright file="TclUtils.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月25号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Tcl.Util
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using System.Text;
    using System.Text.RegularExpressions;
    using System.Threading;
    using System.Xml;
    using System.Xml.Serialization;

    /// <summary>
    /// Tcl工具类.
    /// </summary>
    public static class TclUtils
    {
        #region 全局私有变量
        #endregion

        #region 字典操作工具
        /// <summary>
        /// 清除字典中值为空的项.
        /// </summary>
        /// <param name="dict">待清除的字典.</param>
        /// <returns>清除后的字典.</returns>
        public static IDictionary<string, T> CleanupDictionary<T>(IDictionary<string, T> dict)
        {
            IDictionary<string, T> newDict = new Dictionary<string, T>(dict.Count);
            IEnumerator<KeyValuePair<string, T>> dem = dict.GetEnumerator();

            while (dem.MoveNext())
            {
                string name = dem.Current.Key;
                T value = dem.Current.Value;
                if (value != null)
                {
                    newDict.Add(name, value);
                }
            }

            return newDict;
        }
        #endregion

        #region 解析文本类型
        /// <summary>
        /// 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
        /// </summary>
        /// <param name="fileData">文件字节流</param>
        /// <returns>JPG, GIF, PNG or null</returns>
        public static string GetFileSuffix(byte[] fileData)
        {
            /** 文件流数据后十位为文件类型*/
            if (fileData == null || fileData.Length < 10)
            {
                return null;
            }
            if (fileData[0] == 'D' && fileData[1] == 'L' && fileData[2] == 'L')
            {
                return "DLL";
            }
            else if (fileData[0] == 'E' && fileData[1] == 'X' && fileData[2] == 'E')
            {
                return "EXE";
            }
            else if (fileData[0] == 'X' && fileData[1] == 'M' && fileData[2] == 'L')
            {
                return "XML";
            }
            else if (fileData[0] == 'T' && fileData[1] == 'X' && fileData[2] == 'T')
            {
                return "TXT";
            }
            else if (fileData[0] == 'G' && fileData[1] == 'I' && fileData[2] == 'F')
            {
                return "GIF";
            }
            else if (fileData[1] == 'P' && fileData[2] == 'N' && fileData[3] == 'G')
            {
                return "PNG";
            }
            else if (fileData[6] == 'J' && fileData[7] == 'F' && fileData[8] == 'I' && fileData[9] == 'F')
            {
                return "JPG";
            }
            else if (fileData[0] == 'B' && fileData[1] == 'M')
            {
                return "BMP";
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
        /// </summary>
        /// <param name="fileData">文件字节流</param>
        /// <returns>媒体类型</returns>
        public static string GetMimeType(byte[] fileData)
        {
            string suffix = GetFileSuffix(fileData);
            string mimeType;

            switch (suffix)
            {
                case "DLL": mimeType = "application/x-msdownload"; break;
                case "EXE": mimeType = "application/octet-stream"; break;
                case "XML": mimeType = "application/vnd.ms-excel"; break;
                case "TXT": mimeType = "text/plain"; break;
                case "JPG": mimeType = "image/jpeg"; break;
                case "GIF": mimeType = "image/gif"; break;
                case "PNG": mimeType = "image/png"; break;
                case "BMP": mimeType = "image/bmp"; break;
                default: mimeType = "application/octet-stream"; break;
            }

            return mimeType;
        }
        #endregion

        #region 解析XML文件
        /// <summary>
        /// 将对象转化为XML字符串.
        /// </summary>
        /// <param name="target">目标对象.</param>
        /// <returns>对象XML字符串.</returns>
        public static string ObjectToXMLString(object target)
        {
            return TclUtils.ObjectToXMLString(target, null);
        }

        /// <summary>
        /// 将对象转化为XML字符串.
        /// </summary>
        /// <param name="target">目标对象.</param>
        /// <param name="xmlNamespaces">XML命名空间.</param>
        /// <returns>对象XML字符串.</returns>
        public static string ObjectToXMLString(object target, ServerDictionary xmlNamespaces)
        {
            string xmlString = string.Empty;

            if (target != null)
            {
                XmlSerializer xmlSerializer = new XmlSerializer(target.GetType());
                using (MemoryStream memoryStream = new MemoryStream())
                { 
                    XmlTextWriter xmlTextWriter = new XmlTextWriter(memoryStream, Encoding.UTF8);
                    xmlTextWriter.Formatting = Formatting.Indented;
                    XmlSerializerNamespaces xmlSerializerNamespaces = new XmlSerializerNamespaces();
                    if (xmlNamespaces != null)
                    {
                        foreach (KeyValuePair<string, string> xmlNamespace in xmlNamespaces)
                        {
                            xmlSerializerNamespaces.Add(xmlNamespace.Key, xmlNamespace.Value);
                        }
                    }
                    else 
                    {
                        xmlSerializerNamespaces.Add("", "");//默认不需要命名空间
                    }

                    xmlSerializer.Serialize(xmlTextWriter, target, xmlSerializerNamespaces);
                    memoryStream.Seek(0, SeekOrigin.Begin);
                    StreamReader reader = new StreamReader(memoryStream);
                    xmlString = reader.ReadToEnd();
                }
            }

            return xmlString;
        }

        /// <summary>
        /// 将数据写入指定路径的文件中.
        /// </summary>
        /// <param name="filePath">文件路径(包含文件名称).</param>
        /// <param name="fileData">文件数据.</param>
        /// <returns>文件是否写入成功.</returns>
        public static bool WriteFile(string filePath, string fileData)
        {
            bool writeFlag = false;
            try
            {
                using (FileStream fileStream = new FileStream(filePath, FileMode.Create, FileAccess.Write))
                {
                    StreamWriter streamWriter = new StreamWriter(fileStream, System.Text.Encoding.UTF8);
                    streamWriter.Flush();//清理
                    streamWriter.Write(fileData);
                    streamWriter.Flush();//刷入数据
                    writeFlag = true;
                }
            }
            catch (Exception ex)
            {
                throw new TclException(ex.Message, ex);
            }

            return writeFlag;
        }

        /// <summary>
        /// 使用UTF8读取文件数据.
        /// </summary>
        /// <param name="filePath">文件路径.</param>
        /// <returns>UTF8文件数据.</returns>
        public static string ReadFileString(string filePath)
        {
            return TclUtils.ReadFileString(filePath, Encoding.UTF8);
        }
        /// <summary>
        /// 读取XML文件数据.
        /// </summary>
        /// <param name="filePath">XML路径.</param>
        /// <param name="encoding">文件流文件格式.</param>
        /// <returns>XML字符串.</returns>
        public static string ReadFileString(string filePath, Encoding encoding)
        {
            if (!File.Exists(filePath))
            {
                throw new TclException("XML File Not Found");
            }

            string fileData = string.Empty;
            using (StreamReader reader = new StreamReader(new FileStream(filePath, FileMode.Open, FileAccess.Read), encoding))
            {
                fileData = reader.ReadToEnd();
            }

            return fileData;
        }

        /// <summary>
        /// XML字符串反序列化为对象.
        /// </summary>
        /// <typeparam name="T">对象类型.</typeparam>
        /// <param name="xml">XML字符串.</param>
        /// <returns>XML字符串反序列化后的对象.</returns>
        public static T XmlDeserialize<T>(string xml) where T : new()
        {
            Type type = typeof(T);
            string rootTagName = GetRootElement(xml);
            string key = type.FullName;
            XmlSerializer serializer = null;
            bool incl = false;
            ReaderWriterLock rwLock = new ReaderWriterLock();
            Dictionary<string, XmlSerializer> parsers = new Dictionary<string, XmlSerializer>();

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
            using (System.IO.Stream stream = new MemoryStream(Encoding.UTF8.GetBytes(xml)))
            {
                obj = serializer.Deserialize(stream);
            }

            T result;
            try
            {
                result = (T)obj;
            }
            catch (Exception ex)
            {
                System.Console.WriteLine(ex.Message);
                result = default(T);
            }
            return result;
        }
        #endregion

        #region 私有方法
        /// <summary>
        /// 获取XML响应的根节点名称.
        /// </summary>
        private static string GetRootElement(string body)
        {
            //截取XML字符串中的根节点的正则表达式.
            Regex regex = new Regex("<(\\w+?)[ >]", RegexOptions.Compiled);
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
