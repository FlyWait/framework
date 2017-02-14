package org.team.framework.tcl.exception;

/**
 * 
 * <DL>
 * <DD>Business Exception.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年5月4日
 * 修改记录:
 * 初始化
 */
public class BusinessException extends TclException{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <DL>
	 * <DD>Default Constructor.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年5月4日
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>Default Constructor.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年5月4日
	 * @param cause
	 */
	public BusinessException(Throwable cause){
		super(cause);
	}
	
	/**
	 * 
	 * <DL>
	 * <DD>Default Constructor.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年5月4日
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message,Throwable cause){
		super(message,cause);
	}

}
