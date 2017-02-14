package org.team.framework.tcl.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.team.framework.tcl.eo.base.BaseEo;

/**
 * 参数表
 * @author liuliang
 *
 */
@Entity
@Table(name="ust_parameter")
public class ParametersEo  extends BaseEo{
    private static final long serialVersionUID = 1653215272854882351L;
    /** 参数ID */
    private Long id;
    /** 参数类型 */
    private String parameterType;
    /** 参数值 */
    private String parameterValue;
    /** 参数显示名称 */
    private String parameterLabel;
    /** 参数是否可用 */
    private boolean enable  = true;
    /** 参数显示顺序 */
    private long parameterOrder = 0L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "parameter_type",unique=true,length=32,nullable = false)
    public String getParameterType() {
        return parameterType;
    }
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
    @Column(name = "parameter_value",length=32)
    public String getParameterValue() {
        return parameterValue;
    }
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
    @Column(name = "parameter_label",length=32)
    public String getParameterLabel() {
        return parameterLabel;
    }
    public void setParameterLabel(String parameterLabel) {
        this.parameterLabel = parameterLabel;
    }
    @Column(name = "parameter_enable",nullable=false)
    public boolean isEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    @Column(name = "parameter_order",nullable=false)
    public long getParameterOrder() {
        return parameterOrder;
    }
    public void setParameterOrder(long parameterOrder) {
        this.parameterOrder = parameterOrder;
    }
}
