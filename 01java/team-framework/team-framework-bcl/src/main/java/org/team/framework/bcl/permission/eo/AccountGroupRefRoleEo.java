package org.team.framework.bcl.permission.eo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.team.framework.tcl.EntityObject;
import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 账户角色关系
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_user_role_relation")
public class AccountGroupRefRoleEo extends BaseEo{
    private static final long serialVersionUID = -7618703147546357035L;

    @Embeddable
    static public class PK extends EntityObject {
        private static final long serialVersionUID = -2394410593880363744L;
        
        /** 账户id */
        private Long accountGroupId;
        /** 角色Id */
        private Long roleId;
        @Column(name = "account_group_id",nullable = false)
        public Long getAccountGroupId() {
            return accountGroupId;
        }
        public void setAccountGroupId(Long accountGroupId) {
            this.accountGroupId = accountGroupId;
        }
        @Column(name = "role_id",nullable = false)
        public Long getRoleId() {
            return roleId;
        }
        public void setRoleId(Long roleId) {
            this.roleId = roleId;
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
