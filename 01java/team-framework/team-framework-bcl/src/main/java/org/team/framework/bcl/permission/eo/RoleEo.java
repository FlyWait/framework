package org.team.framework.bcl.permission.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 角色表.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_role")
public class RoleEo extends BaseEo{
    private static final long serialVersionUID = -8431954716732936256L;
    /** 角色ID */
    private Long id;
    /** 角色代码 */
    private String roleCode;
    /** 角色名称 */
    private String roleName;
    /** 角色描述 */
    private String roleDescription;
    /** 是否启用角色 */
    private boolean enabled = true;
    /** 是否允许授权 */
    private boolean permissionGranted = false;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "role_code",unique=true,length=32,nullable = false)
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    @Column(name = "role_name",length=64,nullable = false)
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    @Column(name = "role_description",length=128)
    public String getRoleDescription() {
        return roleDescription;
    }
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    @Column(name = "role_enabled",nullable = false)
    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Column(name = "permission_granted",nullable = false)
    public boolean isPermissionGranted() {
        return permissionGranted;
    }
    public void setPermissionGranted(boolean permissionGranted) {
        this.permissionGranted = permissionGranted;
    }
}
