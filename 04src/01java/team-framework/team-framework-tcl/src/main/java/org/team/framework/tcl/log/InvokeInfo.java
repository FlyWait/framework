package org.team.framework.tcl.log;

import java.util.Date;

/**
 * 
 * <DL>
 * <DD>调用信息类.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月29日
 * 修改记录:
 * 初始化
 */
public class InvokeInfo {
	/**调用方法*/
    private String    invokerName;
    /**开始调用日期*/
    private Date    date;
    /** 嵌套等级*/
    private int        level;
    /**是否有事务*/
    private boolean    inTransaction;
	public String getInvokerName() {
		return invokerName;
	}
	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isInTransaction() {
		return inTransaction;
	}
	public void setInTransaction(boolean inTransaction) {
		this.inTransaction = inTransaction;
	}
}
