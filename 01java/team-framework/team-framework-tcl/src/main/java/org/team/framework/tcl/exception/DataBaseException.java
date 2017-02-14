package org.team.framework.tcl.exception;

/**
 * 
 * <DL>
 * <DD>Database Exception.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年5月4日
 * 修改记录:
 * 初始化
 */
public class DataBaseException extends TclException{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <DL>
	 * <DD>constructor stub.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年5月4日
	 * @param cause
	 */
	public DataBaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * <DL>
	 * <DD>constructor stub.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年5月4日
	 * @param message
	 * @param cause
	 */
	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * <DL>
	 * <DD>constructor stub.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年5月4日
	 * @param message
	 */
	public DataBaseException(String message) {
		super(message);
	}

}
