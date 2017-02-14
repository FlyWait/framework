package org.team.framework.scl.eo;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * <DD>系统菜单基础信息表.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Entity
@Table
public class T_SCL_MENU extends EntityObject{

	private static final long serialVersionUID = 1L;
	@Override
	public Serializable getPK() {
		return MENU_ID;
	}

	/**菜单ID*/
    @Id
    @Column(length=32,nullable=false)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String MENU_ID;
    /**菜单描述*/
    @Column(length=16,nullable=false)
    private String MENU_DESCR;
    /**菜单URL地址*/
    @Column(length=256,nullable=false)
    private String MENU_URL;
    /**菜单左值*/
    @Column(length=16,nullable=false,precision=19, scale=0)
    private BigDecimal MENU_LFT = new BigDecimal(-1);
    /**菜单右值*/
    @Column(length=16,nullable=false,precision=19, scale=0)
    private BigDecimal MENU_RGT = new BigDecimal(0);
    /**系统名称*/
    @Column(length=16,nullable=false)
    private String MENU_APP;
    /**菜单类型0页面菜单;1页面功能*/
    @Column(length=1,nullable=false)
    private char MENU_TYPE = '0';
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
	public String getMENU_ID() {
		return MENU_ID;
	}
	public String getMENU_DESCR() {
		return MENU_DESCR;
	}
	public void setMENU_DESCR(String mENU_DESCR) {
		MENU_DESCR = mENU_DESCR;
	}
	public String getMENU_URL() {
		return MENU_URL;
	}
	public void setMENU_URL(String mENU_URL) {
		MENU_URL = mENU_URL;
	}
	public BigDecimal getMENU_LFT() {
		return MENU_LFT;
	}
	public void setMENU_LFT(BigDecimal mENU_LFT) {
		MENU_LFT = mENU_LFT;
	}
	public BigDecimal getMENU_RGT() {
		return MENU_RGT;
	}
	public void setMENU_RGT(BigDecimal mENU_RGT) {
		MENU_RGT = mENU_RGT;
	}
	public String getMENU_APP() {
		return MENU_APP;
	}
	public void setMENU_APP(String mENU_APP) {
		MENU_APP = mENU_APP;
	}
	public char getMENU_TYPE() {
		return MENU_TYPE;
	}
	public void setMENU_TYPE(char mENU_TYPE) {
		MENU_TYPE = mENU_TYPE;
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
