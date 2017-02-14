package org.team.framework.tcl.log;

import org.aopalliance.intercept.Interceptor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <DL>
 * <DD>Log拦截器，获取调用方法的堆栈并写入日志..</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月29日
 * 修改记录:
 * 初始化
 * @Copyright 重庆昂码信息科技有限公司 2012
 */
public class LoggerInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	private static final LoggerFacility LOGGERFACILITY = LoggerFacility.getInstance();	
    /**
     * 
     * <DL>
     * <DD>日志开始本次拦截时调用的方法.</DD><BR>
     * </DL>
     * @author Angma <liul>
     * @date 2016年2月29日
     * @param joinPoint
     */
	public void beforeInvoke(JoinPoint joinPoint){
		for (Object o : joinPoint.getArgs() ) {
            ToStringBuilder.reflectionToString(o, ToStringStyle.SIMPLE_STYLE); 
        }
		String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName() );
		LOGGERFACILITY.beforeInvoke(methodName);
        logger.info(joinPoint.getSignature().toString() );
        for (int i = 0; i < joinPoint.getArgs().length; ++i) {
            Object o = joinPoint.getArgs()[i];
            logger.info(String.format("args[%d]:[%s]", i, ToStringBuilder.reflectionToString(o, ToStringStyle.SIMPLE_STYLE) ) ); 
        }
        logger.info("");
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>日志结束本次拦截时调用的方法.</DD><BR>
	 * </DL>
	 * @author Angma <liul>
	 * @date 2016年2月29日
	 * @param joinPoint
	 */
	public void afterInvoke(JoinPoint joinPoint){
        String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName() );
        LOGGERFACILITY.afterInvoke(methodName);
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>异常拦截执行方法.</DD><BR>
	 * </DL>
	 * @author Angma <liul>
	 * @date 2016年3月2日
	 * @param joinPoint 切入点信息.
	 * @param raisedEx 异常信息.
	 */
	public void raisedExceptionNotice(JoinPoint joinPoint, Throwable raisedEx) {
	    String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName() );
	    
	    logger.warn("------------------------> RAISED EXCEPTION NOTICING <-----------------------------");
	    StackTraceElement traceElement = raisedEx.getStackTrace()[0];
	    logger.warn("throwed exception method: {}",methodName); 
	    logger.warn("throwed exception location: ({}:{})",traceElement.getFileName(),traceElement.getLineNumber() );
	    logger.warn("throwed exception: {}: {}" ,raisedEx.getClass().getName(),raisedEx.getMessage());
	    logger.warn("------------------------> RAISED EXCEPTION NOTICED  <-----------------------------");
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>未拦截异常拦截执行方法.</DD><BR>
	 * </DL>
	 * @author Angma <liul>
	 * @date 2016年3月2日
	 * @param joinPoint 切入点信息.
	 * @param raisedEx 异常信息.
	 */
	public void unhandledExceptionNotice(JoinPoint joinPoint, Throwable raisedEx) {
        String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName() );
        
        logger.error("------------------------> UNHANDLED EXCEPTION NOTICING <-----------------------------");
        logger.error("unhanded exception catched in {} {}",methodName,raisedEx);
        logger.error("------------------------> UNHANDLED EXCEPTION NOTICED  <-----------------------------");
    }
}
