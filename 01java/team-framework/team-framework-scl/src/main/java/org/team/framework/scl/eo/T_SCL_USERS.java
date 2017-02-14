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
 * <DD>用户基本信息表.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Entity
@Table
public class T_SCL_USERS extends EntityObject{

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return USER_ID;
	}

	/**用户ID*/
    @Id
    @Column(length=32,nullable=false)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String USER_ID;
    /**用户代码*/
    @Column(length=16,nullable=false)
    private String USER_CODE;
    /**用户姓名*/
    @Column(length=16,nullable=false)
    private String USER_NAME;
    /**用户密码*/
    @Column(length=32,nullable=false)
    private String USER_PWD;
    /**用户姓名*/
    @Column(length=16,nullable=false)
    private String USER_SALT;
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

	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_CODE() {
		return USER_CODE;
	}
	public void setUSER_CODE(String uSER_CODE) {
		USER_CODE = uSER_CODE;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getUSER_PWD() {
		return USER_PWD;
	}
	public void setUSER_PWD(String uSER_PWD) {
		USER_PWD = uSER_PWD;
	}
	public String getUSER_SALT() {
		return USER_SALT;
	}
	public void setUSER_SALT(String uSER_SALT) {
		USER_SALT = uSER_SALT;
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
