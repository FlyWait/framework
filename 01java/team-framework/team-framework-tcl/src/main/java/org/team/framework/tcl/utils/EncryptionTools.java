package org.team.framework.tcl.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class EncryptionTools {
	private static final Md5PasswordEncoder md5encoder = new Md5PasswordEncoder();
	
	/**
	 * MD5加密
	 */
	public static final String md5Encrypt(String rawPass){
		return md5encoder.encodePassword(rawPass, null);
	}
	
	/**
	 * MD5盐值加密
	 */
	public static final String md5Encrypt(String rawPass,String salt){
		return md5encoder.encodePassword(rawPass, salt);
	}
	
	/**
	 * MD5密码验证
	 */
	public static final Boolean isMD5PasswordValid(String encPass,String rawPass,String salt){
		return md5encoder.isPasswordValid(encPass, rawPass, salt);
	}
	
	/**
	 * 加密字符串比较
	 */
	public static final Boolean match(String encryptText1,String encryptText2){
		
		return StringUtils.equals(encryptText1, encryptText2);
	}
}
