package org.team.framework.bcl.permission.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 账户表.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_account")
public class AccountEo extends BaseEo{
    private static final long serialVersionUID = 4884780079648917406L;

    /** 用户ID */
    private Long id;
    /** 登陆代码 */
    private String loginCode;
    /** 登陆显示名称 */
    private String loginName;
    /** 是否锁定 */
    private boolean locked = false;
    /** 用户是否可用 */
    private boolean  enabled = true;
    /** 用户有效开始时间 */
    private Date effectiveStartTime;
    /** 用户失效时间 */
    private Date effectiveEndTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accoun_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "login_code",unique=true,length=32,nullable = false)
    public String getLoginCode() {
        return loginCode;
    }
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
    @Column(name = "login_name",length=32,nullable = false)
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    @Column(name = "account_locked",nullable = false)
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    @Column(name = "account_enabled",nullable = false)
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Column(name = "effective_start_time",nullable = false)
    public Date getEffectiveStartTime() {
        return effectiveStartTime;
    }
    public void setEffectiveStartTime(Date effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }
    @Column(name = "effective_end_time")
    public Date getEffectiveEndTime() {
        return effectiveEndTime;
    }
    public void setEffectiveEndTime(Date effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }
}
