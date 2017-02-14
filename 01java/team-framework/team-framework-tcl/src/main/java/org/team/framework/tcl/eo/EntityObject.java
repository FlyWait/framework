package org.team.framework.tcl.eo;

import java.io.Serializable;

import org.team.framework.tcl.DataBaseObject;

/**
 * 
 * <DL>
 * <DD>数据库实体抽象对象.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public abstract class EntityObject extends DataBaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <DL>
	 * <DD>数据库实体获取主键定义.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月15日
	 * @return
	 */
	public abstract Serializable getPK();
}
