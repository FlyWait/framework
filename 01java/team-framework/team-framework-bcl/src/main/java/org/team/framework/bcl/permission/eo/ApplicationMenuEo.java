package org.team.framework.bcl.permission.eo;

import javax.persistence.*;

import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 系统菜单.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_application_menu")
public class ApplicationMenuEo extends BaseEo {
    private static final long serialVersionUID = -2637384687086223216L;
    /** 应用系统菜单ID */
    private Long id;
    /** 应用系统ID */
    private Long applicationId;
    /** 菜单代码 */
    private String menuCode;
    /** 菜单名称 */
    private String menuName;
    /** 菜单描述 */
    private String menuDescription;
    /** 菜单URL */
    private String menuUrl;
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
    @Column(name = "application_menu_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "application_id",nullable = false)
    public Long getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    @Column(name = "menu_code",length=64,nullable = false)
    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    @Column(name = "menu_name",length=32)
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    @Column(name = "menu_description",length=256)
    public String getMenuDescription() {
        return menuDescription;
    }
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }
    @Column(name = "menu_url",length=256)
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    @Column(name = "menu_left",nullable = false)
    public Long getLeft() {
        return left;
    }
    public void setLeft(Long left) {
        this.left = left;
    }
    @Column(name = "menu_right",nullable = false)
    public Long getRight() {
        return right;
    }
    public void setRight(Long right) {
        this.right = right;
    }
    @Column(name = "menu_level",nullable = false)
    public Long getLevel() {
        return level;
    }
    public void setLevel(Long level) {
        this.level = level;
    }
    @Column(name = "menu_enabled",nullable = false)
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
