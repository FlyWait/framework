//-----------------------------------------------------------------------
// <copyright file="ServerDictionary.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl
{
    using System;
    using System.Collections.Generic;

    /// <summary>
    /// 纯字符串的字典类.
    /// </summary>
    public class ServerDictionary : Dictionary<string, string>
    {
        #region 构造函数
        /// <summary>
        /// 无参构造函数.
        /// </summary>
        public ServerDictionary() { }

        /// <summary>
        /// 实例化纯字符串字典类.
        /// </summary>
        /// <param name="dictionary">字符串字典类.</param>
        public ServerDictionary(IDictionary<string, string> dictionary) : base(dictionary) { }
        #endregion

        /// <summary>
        /// 添加一个新的键值对。空键或者空值的键值对将会被忽略.
        /// </summary>
        /// <param name="key">键名称.</param>
        /// <param name="value">键对应的值，目前支持：string, int, long, double, bool, DateTime类型.</param>
        public void Add(string key, object value)
        {
            string strValue;

            if (value == null)
            {
                strValue = null;
            }
            else if (value is string)
            {
                strValue = (string)value;
            }
            else if (value is Nullable<DateTime>)
            {
                Nullable<DateTime> dateTime = value as Nullable<DateTime>;
                strValue = dateTime.Value.ToString(Constants.DATE_TIME_FORMAT);
            }
            else if (value is Nullable<int>)
            {
                strValue = (value as Nullable<int>).Value.ToString();
            }
            else if (value is Nullable<long>)
            {
                strValue = (value as Nullable<long>).Value.ToString();
            }
            else if (value is Nullable<double>)
            {
                strValue = (value as Nullable<double>).Value.ToString();
            }
            else if (value is Nullable<bool>)
            {
                strValue = (value as Nullable<bool>).Value.ToString().ToLower();
            }
            else
            {
                strValue = value.ToString();
            }

            this.Add(key, strValue);
        }

        /// <summary>
        /// 添加一个Key和Value不为空的组合.
        /// </summary>
        /// <param name="key">键.</param>
        /// <param name="value">值.</param>
        public new void Add(string key, string value)
        {
            if (!string.IsNullOrEmpty(key) && !string.IsNullOrEmpty(value))
            {
                base.Add(key, value);
            }
        }

        /// <summary>
        /// 将另一个字符字典类的所有Key和Value不为空的组合加入当前字典.
        /// </summary>
        /// <param name="dict">需要加入的字典类.</param>
        public void AddAll(IDictionary<string, string> dict)
        {
            if (dict != null && dict.Count > 0)
            {
                IEnumerator<KeyValuePair<string, string>> kvps = dict.GetEnumerator();
                while (kvps.MoveNext())
                {
                    KeyValuePair<string, string> kvp = kvps.Current;
                    Add(kvp.Key, kvp.Value);
                }
            }
        }
    }
}
