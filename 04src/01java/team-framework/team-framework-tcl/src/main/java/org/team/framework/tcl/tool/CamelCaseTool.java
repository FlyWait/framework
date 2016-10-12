package org.team.framework.tcl.tool;

/**
 * 
 * <DL>
 * <DD>单词驼峰格式转换工具.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月17日
 * 修改记录:
 * 初始化
 */
public class CamelCaseTool {
	/**分隔符*/
	private static final char SEPARATOR = '_';
	
	/**
	 * 
	 * <DL>
	 * <DD>为驼峰名称添加分隔符.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param word 单词.
	 * @return 转化结果.
	 */
	public static String toUnderlineName(String word) {
        if (word == null) {
            return null;
        }
 
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
 
            boolean nextUpperCase = true;
 
            if (i < (word.length() - 1)) {
                nextUpperCase = Character.isUpperCase(word.charAt(i + 1));
            }
 
            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) {
                        sb.append(SEPARATOR);
                    }
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
 
            sb.append(Character.toLowerCase(c));
        }
 
        return sb.toString();
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>驼峰格式转化.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param word 单词.
	 * @return 转化结果.
	 */
	public static String toCamelCase(String word) {
        if (word == null) {
            return null;
        }
 
        word = word.toLowerCase();
 
        StringBuilder sb = new StringBuilder(word.length());
        boolean upperCase = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
 
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
 
        return sb.toString();
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>全驼峰格式转化.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param word 单词.
	 * @return 转化结果.
	 */
	public static String toCapitalizeCamelCase(String word) {
        if (word == null) {
            return null;
        }
        word = toCamelCase(word);
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>获取字段get方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param fieldName 字段名称.
	 * @return get方法名称.
	 */
	public static String getterMethodName(String fieldName){
    	if (fieldName != null && fieldName.length()>1){
    		String secondStr = String.valueOf(fieldName.charAt(1));
    		if (secondStr.matches("^[A-Z]$")){
    			return String.format("gets%", fieldName);
    		} else {
    			String methodName = String.format("gets%",fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), (String.valueOf((fieldName.charAt(0))).toUpperCase())));
        		return methodName;
    		}
    	} else {
    		String methodName = String.format("gets%",fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), (String.valueOf((fieldName.charAt(0))).toUpperCase())));
    		return methodName;
    	}
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>获取字段set方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param fieldName 字段名称.
	 * @return set方法名称.
	 */
	public static String setterMethodName(String fieldName){
    	if (fieldName != null && fieldName.length()>1){
    		String secondStr = String.valueOf(fieldName.charAt(1));
    		if (secondStr.matches("^[A-Z]$")){
    			return String.format("sets%",fieldName);
    		} else {
    			String methodName = String.format("sets%",fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), (String.valueOf((fieldName.charAt(0))).toUpperCase())));
        		return methodName;
    		}
    	} else {
    		String methodName = String.format("sets%",fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), (String.valueOf((fieldName.charAt(0))).toUpperCase())));
    		return methodName;
    	}
    }
}
