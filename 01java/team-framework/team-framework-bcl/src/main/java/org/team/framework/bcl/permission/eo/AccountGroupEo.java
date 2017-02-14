package org.team.framework.bcl.permission.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 账户组.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_account_group")
public class AccountGroupEo extends BaseEo{

    private static final long serialVersionUID = 3071698460035688729L;
    /** 账户组ID */
    private Long id;
    /** 账户组代码 */
    private String groupCode;
    /** 账户组名称 */
    private String groupName;
    /** 账户组描述 */
    private String groupDescription;
    /** 左值 */
    private Long left; 
    /** 右值 */
    private Long right;
    /** 层级 */
    private Long level;
    /** 是否可用 */
    private boolean enabled = true;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_group_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "group_code",unique=true,length=32,nullable = false)
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    @Column(name = "group_name",length=64,nullable = false)
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @Column(name = "group_description",length=128)
    public String getGroupDescription() {
        return groupDescription;
    }
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
    @Column(name = "group_left",nullable = false)
    public Long getLeft() {
        return left;
    }
    public void setLeft(Long left) {
        this.left = left;
    }
    @Column(name = "group_right",nullable = false)
    public Long getRight() {
        return right;
    }
    public void setRight(Long right) {
        this.right = right;
    }
    @Column(name = "group_level",nullable = false)
    public Long getLevel() {
        return level;
    }
    public void setLevel(Long level) {
        this.level = level;
    }
    @Column(name = "group_enabled",nullable = false)
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
