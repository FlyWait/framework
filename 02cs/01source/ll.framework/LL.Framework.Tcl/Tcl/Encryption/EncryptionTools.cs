//-----------------------------------------------------------------------
// <copyright file="EncryptionTools.cs" company="Angma">
//  Copyright (c) 重庆昂码信息科技有限公司 2012
// </copyright>
// <author>Angma(wangzl)</author>
// <date>2015年11月20号</date>
//-----------------------------------------------------------------------
using System;
namespace LL.Framework.Tcl.Tools.Encryption
{
    /// <summary>
    /// 加密静态工具类.
    /// auther Angma(liul)
    /// date 2015年11月20号
    /// </summary>
    public static class EncryptionTools
    {
        #region 保密文本处理工具
        /// <summary>
        /// 加密的文本字符串转化为普通字符串.
        /// </summary>
        /// <param name="securePassword">加密的字符串.</param>
        /// <returns>普通文本字符串.</returns>
        public static string ConvertToUnsecureString(System.Security.SecureString securePassword)
        {
            if (securePassword == null)
            {
                return string.Empty;
            }

            System.IntPtr unmanagedString = IntPtr.Zero;
            try
            {
                unmanagedString = System.Runtime.InteropServices.Marshal.SecureStringToGlobalAllocUnicode(securePassword);
                return System.Runtime.InteropServices.Marshal.PtrToStringUni(unmanagedString);
            }
            finally
            {
                System.Runtime.InteropServices.Marshal.ZeroFreeGlobalAllocUnicode(unmanagedString);
            }
        }
        #endregion

        #region MD5
        /// <summary>
        /// 不使用盐值混合加密.
        /// </summary>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <returns>加密后的字符串.</returns>
        public static string MD5Encode(string rawPass)
        {
            return new MD5Encode().Encode(rawPass, null, false);
        }

        /// <summary>
        /// MD5加上盐值混合加密.
        /// </summary>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <param name="salt">盐值.</param>
        /// <returns>加密后的字符串.</returns>
        public static string MD5Encode(string rawPass, string salt)
        {
            return new MD5Encode().Encode(rawPass, salt, true);
        }

        /// <summary>
        /// 比较密文与当前字符串使用MD5加密后的结果是否相同
        /// </summary>
        /// <param name="encPass">待比较已加密过的密文.</param>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <returns>验证结果(若相同则返回TRUE,不同返回FALSE).</returns>
        public static bool MD5Valid(string encPass, string rawPass)
        {
            return new MD5Encode().Valid(encPass, rawPass, null, false);
        }

        /// <summary>
        /// 比较密文与当前字符串与盐值混合加密后的结果是否相同.
        /// </summary>
        /// <param name="encPass">待比较已加密过的密文.</param>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <param name="salt">盐值.</param>
        /// <returns>验证结果(若相同则返回TRUE,不同返回FALSE).</returns>
        public static bool MD5Valid(string encPass, string rawPass, string salt)
        {
            return new MD5Encode().Valid(encPass, rawPass, salt, true);
        }
        #endregion
    }
}
