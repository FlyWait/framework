//-----------------------------------------------------------------------
// <copyright file="IEncryption.cs" company="Angma">
//  Copyright (c) 重庆昂码信息科技有限公司 2012
// </copyright>
// <author>Angma(wangzl)</author>
// <date>2015年11月20号</date>
//-----------------------------------------------------------------------
namespace LL.Framework.Tcl.Tools.Encryption
{
    /// <summary>
    /// 加密接口服务.
    /// auther Angma(liul)
    /// date 2015年11月20号
    /// </summary>
    public interface IEncryption
    {
        /// <summary>
        /// 使用盐值混合加密方法.
        /// auther Angma(liul)
        /// date 2015年11月20号
        /// </summary>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <param name="salt">盐值.</param>
        /// <param name="useSalt">是否使用盐值.</param>
        /// <returns>加密结果字符串</returns>
        string Encode(string rawPass, string salt,bool useSalt);

        /// <summary>
        /// 比较密文与当前字符串加密后的密码是否相同.
        /// </summary>
        /// <param name="encPass">加密的密文.</param>
        /// <param name="rawPass">需要加密的字符串.</param>
        /// <param name="salt">盐值.</param>
        /// <param name="useSalt">是否使用盐值.</param>
        /// <returns>验证结果(若相同则返回TRUE,不同返回FALSE).</returns>
        bool Valid(string encPass, string rawPass,string salt,bool useSalt);
    }
}
