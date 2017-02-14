package org.team.framework.bcl.permission.eo;

import org.team.framework.tcl.eo.base.BaseEo;

import javax.persistence.*;

/**
 * The type Application menu operation eo.
 * @author liuliang
 */
@Entity
@Table(name="uam_application_menu_operation")
public class ApplicationMenuOperationEo extends BaseEo{
    private static final long serialVersionUID = -3225191620151004608L;
    /** 应用菜单操作ID */
    private Long id;
    /** 应用系统菜单ID */
    private Long menuId;
    /** 操作代码 */
    private String operationCode;
    /** 操作名称 */
    private String operationName;
    /** 操作描述 */
    private String operationDescription;
    /** 是否可用 */
    private boolean enabled = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_operation_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "menu_id",nullable = false)
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    @Column(name = "operation_code",length=64,nullable = false)
    public String getOperationCode() {
        return operationCode;
    }
    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }
    @Column(name = "operation_name",length=64)
    public String getOperationName() {
        return operationName;
    }
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    @Column(name = "operation_description",length=128)
    public String getOperationDescription() {
        return operationDescription;
    }
    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }
    @Column(name = "operation_enabled",nullable = false)
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
