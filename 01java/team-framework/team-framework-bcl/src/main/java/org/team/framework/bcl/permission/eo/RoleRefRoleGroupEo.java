package org.team.framework.bcl.permission.eo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.team.framework.tcl.EntityObject;
import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 角色与角色组关系.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_role_group_relation")
public class RoleRefRoleGroupEo extends BaseEo{
    private static final long serialVersionUID = 7071719929062681245L;

    @Embeddable
    static public class PK extends EntityObject {
        private static final long serialVersionUID = -2394410593880363744L;
        
        /** 角色id */
        private Long roleId;
        /** 角色组Id */
        private Long roleGroupId;
        @Column(name = "role_id",nullable = false)
        public Long getRoleId() {
            return roleId;
        }
        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }
        @Column(name = "role_group_id",nullable = false)
        public Long getRoleGroupId() {
            return roleGroupId;
        }
        public void setRoleGroupId(Long roleGroupId) {
            this.roleGroupId = roleGroupId;
        }
    }
    
    @EmbeddedId
    private PK pk = new PK();

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }
}
