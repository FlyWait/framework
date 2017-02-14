//-----------------------------------------------------------------------
// <copyright file="DataDelegateCommand.cs">
// </copyright>
// <author>liul</author>
// <date>2015年12月30号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.MVVM
{
    using Microsoft.Practices.Prism.Commands;
    using System;

    /// <summary>
    /// 数据操作类.
    /// </summary>
    public class DataDelegateCommand<T> : DelegateCommand<T>
    {
        public DataDelegateCommand(Action<T> action) : base(action) { }

        public DataDelegateCommand(Action<T> action, Func<T,bool> func) : base(action, func) { }
    }
}
