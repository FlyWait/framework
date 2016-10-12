package org.team.framework.scl.dao;

import org.springframework.stereotype.Repository;
import org.team.framework.scl.eo.T_SCL_USER_ROLE;
import org.team.framework.tcl.dao.EntityDao;

/**
 * 
 * <DL>
 * <DD>用户与角色关系DML.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Repository
public class SclUserRoleDao extends EntityDao<T_SCL_USER_ROLE>{

	@Override
	protected Class<T_SCL_USER_ROLE> getEntityClass() {
		return T_SCL_USER_ROLE.class;
	}

}
