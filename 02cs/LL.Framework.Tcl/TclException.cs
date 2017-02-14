//-----------------------------------------------------------------------
// <copyright file="TclException.cs">
// </copyright>
// <author>liul</author>
// <date>2015年11月25号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl
{
    /// <summary>
    /// Tcl异常类.
    /// </summary>
    public class TclException : System.Exception
    {
        #region 全局私有变量
        /// <summary>
        /// 异常代码.
        /// </summary>
        private string errorCode;

        /// <summary>
        /// 异常信息.
        /// </summary>
        private string errorMsg;
        #endregion

        #region 异常构造函数
        /// <summary>
        /// 不带任何异常输入的构造函数.
        /// </summary>
        public TclException() : base()
        {
        }

        /// <summary>
        /// 输入异常信息的构造函数.
        /// </summary>
        /// <param name="message">异常信息.</param>
        public TclException(string message) : base(message)
        {
        }

        /// <summary>
        /// 序列化异常构造函数.
        /// </summary>
        /// <param name="info">序列化或者反序列化的全部数据.</param>
        /// <param name="context">序列化的源和目标.</param>
        protected TclException(System.Runtime.Serialization.SerializationInfo info, System.Runtime.Serialization.StreamingContext context) : base(info, context)
        {
        }

        /// <summary>
        /// 带有异常信息和堆栈异常的构造函数.
        /// </summary>
        /// <param name="message">异常信息.</param>
        /// <param name="innerException">堆栈异常.</param>
        public TclException(string message, System.Exception innerException) : base(message, innerException)
        {
        }

        /// <summary>
        /// 使用异常代码、异常信息的异常构造函数.
        /// </summary>
        /// <param name="errorCode">异常代码.</param>
        /// <param name="errorMsg">异常信息.</param>
        public TclException(string errorCode, string errorMsg) : base(errorCode + ":" + errorMsg)
        {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }
        #endregion

        #region 外部属性
        /// <summary>
        /// 获取异常代码.
        /// </summary>
        public string ErrorCode
        {
            get { return this.errorCode; }
        }

        /// <summary>
        /// 获取异常信息.
        /// </summary>
        public string ErrorMsg
        {
            get { return this.errorMsg; }
        }
        #endregion
    }
}