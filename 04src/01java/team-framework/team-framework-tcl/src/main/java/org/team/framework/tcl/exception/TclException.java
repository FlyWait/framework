package org.team.framework.tcl.exception;

/**
 * 
 * <DL>
 * <DD>Technical components library exception.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public class TclException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <DL>
	 * <DD>构造函数.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月15日
	 * @param message 异常信息
	 */
	public TclException(String message){
		super(message);
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>构造函数.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月15日
	 * @param cause 异常原因
	 */
	public TclException(Throwable cause){
		super(cause);
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>构造函数.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月15日
	 * @param message 异常信息
	 * @param cause 异常原因
	 */
	public TclException(String message,Throwable cause){
		super(message,cause);
	}
}
