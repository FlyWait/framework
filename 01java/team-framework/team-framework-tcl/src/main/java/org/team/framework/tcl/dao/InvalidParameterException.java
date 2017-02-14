package org.team.framework.tcl.dao;

import org.team.framework.tcl.exception.TclException;
/**
 * 
 * <DL>
 * <DD>查询参数无效异常类.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public class InvalidParameterException extends TclException{
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
	public InvalidParameterException(String message){
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
	public InvalidParameterException(Throwable cause){
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
	public InvalidParameterException(String message,Throwable cause){
		super(message,cause);
	}
}
