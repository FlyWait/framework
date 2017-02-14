//-----------------------------------------------------------------------
// <copyright file="MD5Encode.cs" company="Angma">
//  Copyright (c) 重庆昂码信息科技有限公司 2012
// </copyright>
// <author>Angma(wangzl)</author>
// <date>2015年11月20号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Tools.Encryption
{
    /// <summary>
    /// MD5加密服务.
    /// auther Angma(liul)
    /// date 2015年11月20号
    /// </summary>
    internal sealed class MD5Encode : IEncryption
    {
        /// <summary>
        /// MD5加密核心方法.
        /// auther Angma(liul)
        /// date 2015年11月20号
        /// </summary>
        /// <exception cref="System.ArgumentException">加密字符串或盐值不能为空或者NULL.</exception>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <param name="salt">盐值.</param>
        /// <param name="useSalt">是否使用盐值混合加密.</param>
        /// <returns>加密结果.</returns>
        public string Encode(string rawPass, string salt, bool useSalt)
        {
            if (string.IsNullOrEmpty(rawPass))
            {
                throw new System.ArgumentException("加密字符串rawPass不能为空或NULL");
            }

            if (useSalt && string.IsNullOrEmpty(salt))
            {
                throw new System.ArgumentException("盐值salt不能为不能为空或NULL");
            }

            System.Text.StringBuilder rawPassAndSalt = new System.Text.StringBuilder(rawPass);

            if (useSalt)
            {
                rawPassAndSalt.Append("{");
                rawPassAndSalt.Append(salt);
                rawPassAndSalt.Append("}");
            }

            System.Security.Cryptography.MD5 md5 = System.Security.Cryptography.MD5.Create();
            byte[] bs = System.Text.Encoding.UTF8.GetBytes(rawPassAndSalt.ToString());
            byte[] hs = md5.ComputeHash(bs);
            System.Text.StringBuilder md5EncodeSB = new System.Text.StringBuilder();
            foreach (byte b in hs)
            {
                ////以十六进制格式化
                md5EncodeSB.Append(b.ToString("x2"));
            }

            return md5EncodeSB.ToString();
        }

        /// <summary>
        /// 比较密文与当前字符串与盐值混合加密后的结果是否相同.
        /// </summary>
        /// <exception cref="System.ArgumentException">参数encPass不能为空或者NULL</exception>
        /// <param name="encPass">待比较已加密过的密文.</param>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <param name="salt">盐值.</param>
        /// <param name="useSalt">是否使用盐值.</param>
        /// <returns>验证结果(若相同则返回TRUE,不同返回FALSE).</returns>
        public bool Valid(string encPass, string rawPass, string salt, bool useSalt)
        {
            if (string.IsNullOrEmpty(encPass))
            {
                throw new System.ArgumentException("参数encPass不能为空或者NULL");
            }

            string rawPassEncode = this.Encode(rawPass, salt, useSalt);

            return string.Compare(encPass, rawPassEncode).Equals(0) ? true : false;
        }
    }
}
