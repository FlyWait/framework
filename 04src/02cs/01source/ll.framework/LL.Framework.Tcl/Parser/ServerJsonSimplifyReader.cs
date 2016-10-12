//-----------------------------------------------------------------------
// <copyright file="ServerJsonSimplifyReader.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月03号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Parser
{
    using System;
    using System.Collections;
    using System.Collections.Generic;

    /// <summary>
    /// Server JSON响应读取器.
    /// </summary>
    public class ServerJsonSimplifyReader : IServerReader
    {
        #region 全局私有变量
        /// <summary>
        /// JSON数据字典.
        /// </summary>
        private IDictionary json;
        #endregion

        #region 外部服务方法
        /// <summary>
        /// Server JSON响应读取器构造方法.
        /// </summary>
        /// <param name="json">JSON数据字典.</param>
        public ServerJsonSimplifyReader(IDictionary json)
        {
            this.json = json;
        }

        /// <summary>
        /// 判断响应中是否包含指定的属性.
        /// </summary>
        /// <param name="name">属性名称.</param>
        /// <returns>true/false.</returns>
        public bool HasReturnField(object name)
        {
            return json.Contains(name);
        }

        /// <summary>
        /// 获取值类型属性的值.
        /// </summary>
        /// <param name="name">属性名称.</param>
        /// <returns>值对象.</returns>
        public object GetPrimitiveObject(object name)
        {
            return json[name];
        }

        /// <summary>
        /// 获取引用类型的值.
        /// </summary>
        /// <param name="name">属性名称.</param>
        /// <param name="type">引用类型.</param>
        /// <param name="convert">转换器.</param>
        /// <returns>引用对象.</returns>
        public object GetReferenceObject(object name, Type type, DServerConvert convert)
        {
            IDictionary dict = json[name] as IDictionary;
            if (dict != null && dict.Count > 0)
            {
                return convert(new ServerJsonReader(dict), type);
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// 获取列表类型的值.
        /// </summary>
        /// <param name="listName">列表属性名称.</param>
        /// <param name="itemName">列表项名称.</param>
        /// <param name="type">引用类型.</param>
        /// <param name="convert">转换器.</param>
        /// <returns>列表对象.</returns>
        public IList GetListObjects(string listName, string itemName, Type type, DServerConvert convert)
        {
            IList listObjs = null;

            IList jsonList = json[listName] as IList;
            if (jsonList != null && jsonList.Count > 0)
            {
                Type listType = typeof(List<>).MakeGenericType(new Type[] { type });
                listObjs = Activator.CreateInstance(listType) as IList;
                foreach (object item in jsonList)
                {
                    if (typeof(IDictionary).IsAssignableFrom(item.GetType())) // object
                    {
                        IDictionary subMap = item as IDictionary;
                        object subObj = convert(new ServerJsonSimplifyReader(subMap), type);
                        if (subObj != null)
                        {
                            listObjs.Add(subObj);
                        }
                    }
                    else if (typeof(IList).IsAssignableFrom(item.GetType())) // list or array
                    {
                        // TODO not support yet
                    }
                    else // string, bool, long, double, null, other
                    {
                        listObjs.Add(item);
                    }
                }
            }

            return listObjs;
        }
        #endregion
    }
}
