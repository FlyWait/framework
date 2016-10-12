package org.team.framework.tcl.dao;

import org.team.framework.tcl.exception.TclException;

/**
 * 
 * <DL>
 * <DD>无效锁异常.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月16日
 * 修改记录:
 * 初始化
 */
public class InvalidLockException extends TclException{
	private static final long serialVersionUID = 1L;

	/**
     * <DL>
     * <DD>构造函数.</DD><BR>
     * </DL>
     * @author Team<liul>
     * @date 2016年2月16日
     * @param message    异常消息
     */
    public InvalidLockException(String message) {
        super(message);
    }

    /**
     * <DL>
     * <DD>构造函数.</DD><BR>
     * </DL>
     * @author Team<liul>
     * @date 2016年2月16日
     * @param cause    异常起因
     */
    public InvalidLockException(Throwable cause) {
        super(cause);
    }

    /**
     * <DL>
     * <DD>构造函数.</DD><BR>
     * </DL>
     * @author Team<liul>
     * @date 2016年2月16日
     * @param message  异常消息
     * @param cause    异常起因
     */
    public InvalidLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
