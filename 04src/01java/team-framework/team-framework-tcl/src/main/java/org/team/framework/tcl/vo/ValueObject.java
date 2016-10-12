package org.team.framework.tcl.vo;

import java.io.Serializable;

import org.team.framework.tcl.DataBaseObject;

/**
 * 
 * <DL>
* <DD>Value Object base class, simple POJO, used to store complex data structure, Vo DOES NOT associate with DataBase,
 *    All Vo object must inherit from this base class, Vo object must not register in Spring Context, 
 *    All Vo object mush have suffix 'Vo'.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月16日
 * 修改记录:
 * 初始化
 */
public class ValueObject extends DataBaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
}
