package org.team.framework.tcl.tool;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.Assert;

/**
 * 
 * <DL>
 * <DD>说明.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月29日
 * 修改记录:
 * 初始化
 */
public final class EncryptionTool {
	/**
	 * 
	 * <DL>
	 * <DD>MD5加密.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月29日
	 * @param plaintext 需要加密的数据.
	 * @return MD5加密后的字符集.
	 */
	public static String MD5Encode(String plaintext) {
        return EncryptionTool.MD5Encode(plaintext, null);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>MD5加密.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月29日
	 * @param plaintext 需要加密的数据.
	 * @param salt 盐值.
	 * @return MD5盐值加密后的数据.
	 */
	public static String MD5Encode(String plaintext, String salt) {
        return new Md5PasswordEncoder().encodePassword(plaintext, salt);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>安全哈希加密算法..</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月29日
	 * @param plaintext 需要加密的数据.
	 * @return 安全哈希加密算法加密结果.
	 */
	public static String SHAEncode(String plaintext) {
        return EncryptionTool.SHAEncode(1, plaintext, null);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>安全哈希加密算法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月29日
	 * @param plaintext 需要加密的数据.
	 * @param salt 盐值.
	 * @return 安全哈希算法加密结果.
	 */
	public static String SHAEncode(String plaintext, String salt) {
        return EncryptionTool.SHAEncode(1, plaintext, salt);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>安全哈希算法加密.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月29日
	 * @param strength 长度 1/256/384/512.
	 * @param plaintext 需要加密的数据.
	 * @param salt 盐值.
	 * @return 安全哈希算法加密结果.
	 */
	public static String SHAEncode(int strength, String plaintext, String salt) {
        Assert.isTrue(strength == 1 || strength == 256 || strength == 384 || strength == 512, "strength must be 1/256/384/512");
        return new ShaPasswordEncoder(strength).encodePassword(plaintext, salt);
    }
}
