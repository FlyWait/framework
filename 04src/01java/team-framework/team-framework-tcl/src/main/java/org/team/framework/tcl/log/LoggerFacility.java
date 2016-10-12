package org.team.framework.tcl.log;

import java.util.Date;
import java.util.Stack;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <DL>
 * <DD>日志分类处理器.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月29日
 * 修改记录:
 * 初始化
 */
public class LoggerFacility {
	private Logger logger = LoggerFactory.getLogger(LoggerFacility.class);
	/**MDC键 值*/
	private static final String MDCKEY = "MDCKey";
	/**日志分类器实例化*/
	private static LoggerFacility instance = new LoggerFacility();
	/**高级LOG日志Key值.*/
	private static final String PERFORMANCE_LOG_KEY = "PERFORMANCE_LOG_KEY";
	
	/**
	 * 
	 * <DL>
	 * <DD>不允许new.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 */
	private LoggerFacility() {
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>获取日志分类处理器实例.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @return 日志分类处理器实例.
	 */
	public static LoggerFacility getInstance() {
        return instance;
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>日志处理实例是否为空.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @return 日志处理器在MDC中是否存在.
	 */
	public boolean isEmpty() {
        Object o = MDC.get(MDCKEY);
        return o == null;
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>获取方法名.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param throwable 方法堆栈信息.
	 * @return 方法名.
	 */
	private String getMethodName(Throwable throwable) {
		String methodName = "";
        if (throwable != null && throwable.getStackTrace().length > 0) {
            StackTraceElement element = throwable.getStackTrace()[0];
            methodName = String.format("%s.%s", element.getClassName(),element.getMethodName());
        }

        return methodName;
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>开始处理方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param bizName 处理方法名.
	 * @param mdcValue mdc Key值.
	 */
	public void startBizProcess(String bizName, String mdcValue) {
        @SuppressWarnings("unchecked")
        Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC.get(LoggerFacility.class.getSimpleName());

        invokeInfoStack = new Stack<InvokeInfo>();
        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setDate(new Date());
        invokeInfo.setInTransaction(true);
        invokeInfo.setInvokerName(bizName);
        invokeInfo.setLevel(0);
        invokeInfoStack.push(invokeInfo);
        MDC.put(LoggerFacility.class.getSimpleName(), invokeInfoStack);
        MDC.put(MDCKEY, mdcValue);

        logger.info("********************************************************************************");
        logger.info("Business Process Name: [{}]",invokeInfo.getInvokerName() );
        logger.info("Thread ID: {}, Thread name: {}",Thread.currentThread().getId(),Thread.currentThread().getName());
        logger.info("Call Time: {}",invokeInfo.getDate());
        logger.info("--------------------- detail log info is below ---------------------------------");
        logger.info("");
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>开始业务处理.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param bizName 处理方法名.
	 */
	public void startBizProcess(String bizName) {
        String mdCValue = String.valueOf(Thread.currentThread().getId());
        startBizProcess(bizName, mdCValue);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>结束业务处理.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 */
	public void endBizProcess() {
        try {
            @SuppressWarnings("unchecked")
            Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC.get(LoggerFacility.class.getSimpleName());

            InvokeInfo invokeInfo = invokeInfoStack.pop();

            Date now = new Date();
            logger.info("");
            logger.info("--------------------- detail log info is finished ------------------------------");
            logger.info("Thread ID: {}, Thread name: {}",Thread.currentThread().getId(),Thread.currentThread().getName());
            logger.info("End Time: {}" ,now);
            logger.info("Business Process [{}] is completed, elapsed time: {}",invokeInfo.getInvokerName(),now.getTime() - invokeInfo.getDate().getTime());
            logger.info("********************************************************************************");
        } finally {
            MDC.remove(LoggerFacility.class.getSimpleName());
            MDC.remove(MDCKEY);
            MDC.remove(PERFORMANCE_LOG_KEY);
            MDC.clear();
        }
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>方法调用之前执行的方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param methodName 执行的方法名.
	 */
	public void beforeInvoke(String methodName) {
        @SuppressWarnings("unchecked")
        Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC.get(LoggerFacility.class.getSimpleName());
        if (null == invokeInfoStack) {
            return;
        }

        InvokeInfo invokeInfoParent = invokeInfoStack.peek();

        int level = invokeInfoStack.size();
        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setDate(new Date());
        invokeInfo.setInTransaction(invokeInfoParent.isInTransaction());
        invokeInfo.setInvokerName(methodName);
        invokeInfo.setLevel(level);
        invokeInfoStack.push(invokeInfo);

        logger.info(String.format("[Start Local API Invoked][+][lvl:%-2d]-----%s()", level, methodName));
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>方法调用结束执行的方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param methodName 执行的方法名.
	 */
	public void afterInvoke(String methodName) {
        @SuppressWarnings("unchecked")
        Stack<InvokeInfo> invokeInfoStack = (Stack<InvokeInfo>) MDC.get(LoggerFacility.class.getSimpleName());
        if (null == invokeInfoStack) {
            return;
        }

        InvokeInfo invokeInfo = invokeInfoStack.pop();

        logger.info(String.format("[End   Local API Invoked][-][lvl:%-2d]-----%s(), elapsed time: %d", invokeInfo.getLevel(), methodName, new Date().getTime() - invokeInfo.getDate().getTime()));
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>方法调用之前执行的方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param throwable 方法堆栈.
	 */
	public void beforeInvoke(Throwable throwable) {
        String methodName = getMethodName(throwable);
        beforeInvoke(methodName);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>方法调用结束执行的方法.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param t
	 */
	public void afterInvoke(Throwable t) {
        String methodName = getMethodName(t);
        afterInvoke(methodName);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>开始执行高级日志.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 * @param apiName API名称.
	 */
	public void startPerformanceLog(String apiName) {
        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setDate(new Date());
        invokeInfo.setInTransaction(true);
        invokeInfo.setInvokerName(apiName);
        invokeInfo.setLevel(0);
        MDC.put(PERFORMANCE_LOG_KEY, invokeInfo);

        String info = String.format("[Performance Log]---->[%s]---->Start", apiName);
        logger.info(info);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>结束执行高级日志.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月2日
	 */
	public void endPerformanceLog() {
        InvokeInfo invokeInfo = (InvokeInfo) MDC.get(PERFORMANCE_LOG_KEY);
        MDC.remove(PERFORMANCE_LOG_KEY);

        String info = String.format("[Performance Log]---->[%s]---->End, elapsed time: %d", invokeInfo.getInvokerName(), new Date().getTime() - invokeInfo.getDate().getTime());
        logger.info(info);
    }
}
