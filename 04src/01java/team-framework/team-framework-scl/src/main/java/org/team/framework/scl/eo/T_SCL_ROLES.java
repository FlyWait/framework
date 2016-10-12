package org.team.framework.scl.eo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.team.framework.tcl.eo.EntityObject;

/**
 * 
 * <DL>
 * <DD>角色基础信息表.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Entity
@Table
public class T_SCL_ROLES extends EntityObject{

	private static final long serialVersionUID = 1L;
	
	@Override
	public Serializable getPK() {
		return null;
	}
	
	/**角色ID*/
    @Id
    @Column(length=32,nullable=false)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String ROLE_ID;
    /**角色描述*/
    @Column(length=32,nullable=false)
    private String ROLE_DESCR;
    /**创建时间*/
    @Column(nullable=false,updatable=false)
    private Timestamp CREATE_TIME;
    /**修改时间*/
    @Column(nullable=false,insertable=false)
    private Timestamp UPDATE_TIME;
    /**创建用户*/
    @Column(length=32,nullable=false)
    private String CREATE_USER;
    /**更新用户*/
    @Column(length=32,nullable=false)
    private String UPDATE_USER;

	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getROLE_DESCR() {
		return ROLE_DESCR;
	}
	public void setROLE_DESCR(String rOLE_DESCR) {
		ROLE_DESCR = rOLE_DESCR;
	}
	public Timestamp getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(Timestamp cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public Timestamp getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(Timestamp uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public String getCREATE_USER() {
		return CREATE_USER;
	}
	public void setCREATE_USER(String cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}
	public String getUPDATE_USER() {
		return UPDATE_USER;
	}
	public void setUPDATE_USER(String uPDATE_USER) {
		UPDATE_USER = uPDATE_USER;
	}
}
