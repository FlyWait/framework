package org.team.framework.scl.dao;

import org.springframework.stereotype.Repository;
import org.team.framework.scl.eo.T_SCL_MENU;
import org.team.framework.tcl.dao.EntityDao;

/**
 * 
 * <DL>
 * <DD>系统菜单基础信息DML.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Repository
public class SclMenuDao extends EntityDao<T_SCL_MENU>{

	@Override
	protected Class<T_SCL_MENU> getEntityClass() {
		return T_SCL_MENU.class;
	}

}
