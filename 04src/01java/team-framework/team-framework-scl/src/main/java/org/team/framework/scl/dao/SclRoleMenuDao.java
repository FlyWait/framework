package org.team.framework.scl.dao;

import org.springframework.stereotype.Repository;
import org.team.framework.scl.eo.T_SCL_ROLE_MENU;
import org.team.framework.tcl.dao.EntityDao;

/**
 * 
 * <DL>
 * <DD>角色与菜单关系DML.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Repository
public class SclRoleMenuDao extends EntityDao<T_SCL_ROLE_MENU>{

	@Override
	protected Class<T_SCL_ROLE_MENU> getEntityClass() {
		return null;
	}

}
