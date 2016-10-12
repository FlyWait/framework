package org.team.framework.tcl.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * <DL>
 * <DD>Object类型字段转化为指定的类型.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public class FieldConverters {
	   /**
     * 
     * <DL>
     * <DD>Object对象转化为指定的目标对象.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param paramterType 目标对象类型
     * @param value Object对象
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <A> A objectToTargetClass(Class<A> paramterType, Object value){
    	try{
            Object methodParamter = null;
            String temp = value.toString();
            if(paramterType.equals(String.class)){
                methodParamter = temp;
            }else if(paramterType.equals(char.class)||paramterType.equals(Character.class)){
                if(temp.length()==1){
                    methodParamter = temp.charAt(0);
                }
            }else if(paramterType.equals(int.class)||paramterType.equals(Integer.class)){
                methodParamter = Integer.valueOf(temp);
            }else if(paramterType.equals(long.class)||paramterType.equals(Long.class)){
                methodParamter = Long.valueOf(temp);
            }else if(paramterType.equals(float.class)||paramterType.equals(Float.class)){
                methodParamter = Float.valueOf(temp);
            }else if(paramterType.equals(double.class)||paramterType.equals(Double.class)){
                methodParamter = Double.valueOf(temp);
            }else if(paramterType.equals(BigDecimal.class)){
                methodParamter = BigDecimal.valueOf(Double.valueOf(temp));
            }else if(paramterType.equals(Timestamp.class)){
                if(value.getClass().equals(Timestamp.class)){
                    methodParamter = value;
                }
            }else if(paramterType.equals(java.util.Date.class)){
                if(value.getClass().equals(Timestamp.class)){
                    methodParamter = value;
                }
            }
            return (A)methodParamter;
        }catch(Exception e){
            return null;
        }
    }
}
