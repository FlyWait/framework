package org.team.framework.tcl.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.team.framework.tcl.exception.TclException;
import org.team.framework.tcl.tool.CamelCaseTool;

/**
 * 
 * <DL>
 * <DD>Hibernate查询结果转换器.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月16日
 * 修改记录:
 * 初始化
 */
public class CamelCaseTransformer implements ResultTransformer{
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(CamelCaseTransformer.class);
	
	/**转化的目标类型*/
	private final Class<?> resultClass;
	
	/**
	 * 
	 * <DL>
	 * <DD>默认构造函数..</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月16日
	 * @param cls
	 */
	public CamelCaseTransformer(Class<?> cls) {
        this.resultClass = cls;
    }

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;
        try {
            result = resultClass.newInstance();
            for (int i = 0; i < aliases.length; i++) {
                String fieldName = aliases[i];
                Object value = tuple[i];
                Field field = null;
                try {
                    try {
                        field = resultClass.getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        // DO NOTHING
                    } catch (SecurityException e){
                        //DO NOTHING
                    }
                    
                    if (field == null) {
                        fieldName = CamelCaseTool.toCamelCase(fieldName);
                        try {
                            field = resultClass.getDeclaredField(fieldName);
                        } catch (NoSuchFieldException e) {
                            logger.info("1 --- canno find field [" + fieldName + "] in [" + resultClass.getName() + "]");
                        } catch (SecurityException e){
                            logger.info("1 --- canno find field [" + fieldName + "] in [" + resultClass.getName() + "]");
                        }
                    }
                    if (field != null) {
                        String methodName = CamelCaseTool.setterMethodName(fieldName);
                        Method setterMethod = null;
                        try {
                            setterMethod = resultClass.getMethod(methodName, field.getType());
                        } catch (NoSuchMethodException e) {
                            throw new TransformToVoException("字段[" + fieldName + "]没有找到对应的set方法[" + methodName + "]");
                        } catch (SecurityException e){
                            throw new TransformToVoException("字段[" + fieldName + "]没有找到对应的set方法[" + methodName + "]");
                        }
                        Class<?> paramterType = setterMethod.getParameterTypes()[0];
                        Object paramterValue = FieldConverters.objectToTargetClass(paramterType, value);
                        if (paramterValue != null) {
                            setterMethod.invoke(result, paramterValue);
                        }
                    }
                } catch (TclException tclEx) {
                    throw tclEx;
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            throw new TransformToVoException("transform to vo failed!" + e.getMessage());
        }
        return result;
	}

}
