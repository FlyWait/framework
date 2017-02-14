package org.team.framework.tcl.eo.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.team.framework.tcl.EntityObject;

@MappedSuperclass
public class BaseEo extends EntityObject{
    private static final long serialVersionUID = -4152712879431759097L;
    private Date createTime;
    private Long createUser;
    private Date modifyTime;
    private Long modifyUser;
    @Column(name = "createTime",nullable=false)
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name = "createUser",nullable=false)
    public Long getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
    @Column(name = "modifyTime",nullable=false)
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    @Column(name = "modifyUser",nullable=false)
    public Long getModifyUser() {
        return modifyUser;
    }
    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }
}
