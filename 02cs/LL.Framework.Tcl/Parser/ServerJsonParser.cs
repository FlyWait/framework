//-----------------------------------------------------------------------
// <copyright file="ServerJsonParser.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月02号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    using FastJSON;
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Reflection;
    using System.Threading;
    using System.Xml.Serialization;

    /// <summary>
    /// Server JSON通用解释器.
    /// </summary>
    public class ServerJsonParser : IServerParser
    {
        #region 全局私有变量
        /// <summary>
        /// 锁定资源只允许一个线程写入,但是可以多个线程读取.
        /// </summary>
        private static readonly ReaderWriterLock rwLock = new ReaderWriterLock();

        /// <summary>
        /// JSON 字段类型信息字典.
        /// </summary>
        private static readonly Dictionary<string, Dictionary<string, ServerAttribute>> attrs = new Dictionary<string, Dictionary<string, ServerAttribute>>();
        #endregion

        #region 外部服务方法
        /// <summary>
        /// JSON响应解释.
        /// </summary>
        /// <typeparam name="T">领域对象.</typeparam>
        /// <param name="body">JSON字符串.</param>
        /// <returns>JSON反序列化后的领域对象.</returns>
        public virtual T Parse<T>(string body) where T : ServerResponse
        {
            T rsp = null;

            IDictionary json = JSON.Parse(body) as IDictionary;
            if (json != null)
            {
                IDictionary data = null;

                // 忽略根节点的名称
                foreach (object key in json.Keys)
                {
                    data = json[key] as IDictionary;
                    break;
                }

                if (data != null)
                {
                    IServerReader reader = new ServerJsonReader(data);
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
        #endregion

        #region 私有方法
        /// <summary>
        /// 获取Server返回属性信息.
        /// </summary>
        /// <param name="type"><属性类型./param>
        /// <returns>属性类型信息字典.</returns>
        private Dictionary<string, ServerAttribute> GetServerAttributes(Type type)
        {
            Dictionary<string, ServerAttribute> tas = null;
            bool inc = false;

            rwLock.AcquireReaderLock(50);
            try
            {
                if (rwLock.IsReaderLockHeld)
                {
                    inc = attrs.TryGetValue(type.FullName, out tas);
                }
            }
            finally
            {
                if (rwLock.IsReaderLockHeld)
                {
                    rwLock.ReleaseReaderLock();
                }
            }

            if (inc && tas != null) // 从缓存中获取类属性元数据
            {
                return tas;
            }
            else // 创建新的类属性元数据缓存
            {
                tas = new Dictionary<string, ServerAttribute>();
            }

            PropertyInfo[] pis = type.GetProperties();
            foreach (PropertyInfo pi in pis)
            {
                ServerAttribute ta = new ServerAttribute();
                ta.Method = pi.GetSetMethod();

                // 获取对象属性名称
                XmlElementAttribute[] xeas = pi.GetCustomAttributes(typeof(XmlElementAttribute), true) as XmlElementAttribute[];
                if (xeas != null && xeas.Length > 0)
                {
                    ta.ItemName = xeas[0].ElementName;
                }

                // 获取列表属性名称
                if (ta.ItemName == null)
                {
                    XmlArrayItemAttribute[] xaias = pi.GetCustomAttributes(typeof(XmlArrayItemAttribute), true) as XmlArrayItemAttribute[];
                    if (xaias != null && xaias.Length > 0)
                    {
                        ta.ItemName = xaias[0].ElementName;
                    }
                    XmlArrayAttribute[] xaas = pi.GetCustomAttributes(typeof(XmlArrayAttribute), true) as XmlArrayAttribute[];
                    if (xaas != null && xaas.Length > 0)
                    {
                        ta.ListName = xaas[0].ElementName;
                    }
                    if (ta.ListName == null)
                    {
                        continue;
                    }
                }

                // 获取属性类型
                if (pi.PropertyType.IsGenericType)
                {
                    Type[] types = pi.PropertyType.GetGenericArguments();
                    ta.ListType = types[0];
                }
                else
                {
                    ta.ItemType = pi.PropertyType;
                }

                tas.Add(pi.Name, ta);
            }

            rwLock.AcquireWriterLock(50);
            try
            {
                if (rwLock.IsWriterLockHeld)
                {
                    attrs[type.FullName] = tas;
                }
            }
            finally
            {
                if (rwLock.IsWriterLockHeld)
                {
                    rwLock.ReleaseWriterLock();
                }
            }
            return tas;
        }

        /// <summary>
        /// JSON响应解释为领域对象.
        /// </summary>
        /// <param name="reader">响应读取器.</param>
        /// <param name="type">响应属性类型.</param>
        /// <returns>响应对象.</returns>
        public object FromJson(IServerReader reader, Type type)
        {
            object rsp = null;
            Dictionary<string, ServerAttribute> pas = GetServerAttributes(type);

            Dictionary<string, ServerAttribute>.Enumerator em = pas.GetEnumerator();
            while (em.MoveNext())
            {
                KeyValuePair<string, ServerAttribute> kvp = em.Current;
                ServerAttribute ta = kvp.Value;
                string itemName = ta.ItemName;
                string listName = ta.ListName;

                if (!reader.HasReturnField(itemName) && (string.IsNullOrEmpty(listName) || !reader.HasReturnField(listName)))
                {
                    continue;
                }

                object value = null;
                if (ta.ListType != null)
                {
                    value = reader.GetListObjects(listName, itemName, ta.ListType, FromJson);
                }
                else
                {
                    if (typeof(string) == ta.ItemType)
                    {
                        object tmp = reader.GetPrimitiveObject(itemName);
                        if (typeof(string) == tmp.GetType())
                        {
                            value = tmp;
                        }
                        else
                        {
                            if (tmp != null)
                            {
                                value = tmp.ToString();
                            }
                        }
                    }
                    else if (typeof(long) == ta.ItemType)
                    {
                        object tmp = reader.GetPrimitiveObject(itemName);
                        if (typeof(long) == tmp.GetType())
                        {
                            value = tmp;
                        }
                        else
                        {
                            if (tmp != null)
                            {
                                value = long.Parse(tmp.ToString());
                            }
                        }
                    }
                    else if (typeof(bool) == ta.ItemType)
                    {
                        object tmp = reader.GetPrimitiveObject(itemName);
                        if (typeof(bool) == tmp.GetType())
                        {
                            value = tmp;
                        }
                        else
                        {
                            if (tmp != null)
                            {
                                value = bool.Parse(tmp.ToString());
                            }
                        }
                    }
                    else
                    {
                        value = reader.GetReferenceObject(itemName, ta.ItemType, FromJson);
                    }
                }

                if (value != null)
                {
                    if (rsp == null)
                    {
                        rsp = Activator.CreateInstance(type);
                    }
                    ta.Method.Invoke(rsp, new object[] { value });
                }
            }

            return rsp;
        }
        #endregion
    }
}
