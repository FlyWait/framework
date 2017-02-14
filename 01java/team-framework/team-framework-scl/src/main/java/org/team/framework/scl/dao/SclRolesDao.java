package org.team.framework.scl.dao;

import org.springframework.stereotype.Repository;
import org.team.framework.scl.eo.T_SCL_ROLES;
import org.team.framework.tcl.dao.EntityDao;

/**
 * 
 * <DL>
 * <DD>角色基础数据DML.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Repository
public class SclRolesDao extends EntityDao<T_SCL_ROLES>{

	@Override
	protected Class<T_SCL_ROLES> getEntityClass() {
		return T_SCL_ROLES.class;
	}

}
