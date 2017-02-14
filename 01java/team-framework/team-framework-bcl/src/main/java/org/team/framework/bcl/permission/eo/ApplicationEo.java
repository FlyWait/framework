package org.team.framework.bcl.permission.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 应用系统.
 * @author liuliang
 *
 */
@Entity
@Table(name="uam_application")
public class ApplicationEo extends BaseEo{
    private static final long serialVersionUID = 6580591780738073980L;
    /** 应用系统ID */
    private Long id;
    /** 系统代码UUID唯一 */
    private String applicationCode;
    /** 系统名称 */
    private String applicationName;
    /** 系统描述 */
    private String applicationDescription;
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
    @Column(name = "application_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "application_code",unique=true,length=64,nullable = false)
    public String getApplicationCode() {
        return applicationCode;
    }
    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }
    @Column(name = "application_name",length=64,nullable = false)
    public String getApplicationName() {
        return applicationName;
    }
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    @Column(name = "application_description",length=128)
    public String getApplicationDescription() {
        return applicationDescription;
    }
    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }
    @Column(name = "application_left",nullable = false)
    public Long getLeft() {
        return left;
    }
    public void setLeft(Long left) {
        this.left = left;
    }
    @Column(name = "application_left",nullable = false)
    public Long getRight() {
        return right;
    }
    public void setRight(Long right) {
        this.right = right;
    }
    @Column(name = "application_left",nullable = false)
    public Long getLevel() {
        return level;
    }
    public void setLevel(Long level) {
        this.level = level;
    }
    @Column(name = "application_enabled",nullable = false)
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
