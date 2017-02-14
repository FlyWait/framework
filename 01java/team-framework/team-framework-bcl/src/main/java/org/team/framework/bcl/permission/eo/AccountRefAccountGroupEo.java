package org.team.framework.bcl.permission.eo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.team.framework.tcl.EntityObject;
import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 账户与账户关系表.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_user_group_relation")
public class AccountRefAccountGroupEo extends BaseEo{
    private static final long serialVersionUID = 8540181796255481394L;
    
    @Embeddable
    static public class PK extends EntityObject {
        private static final long serialVersionUID = -2394410593880363744L;
        
        /** 账户id */
        private Long accountId;
        /** 账户组Id */
        private Long accountGroupId;
        @Column(name = "user_id",nullable = false)
        public Long getAccountId() {
            return accountId;
        }
        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }
        @Column(name = "account_group_id",nullable = false)
        public Long getAccountGroupId() {
            return accountGroupId;
        }
        public void setAccountGroupId(Long accountGroupId) {
            this.accountGroupId = accountGroupId;
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
