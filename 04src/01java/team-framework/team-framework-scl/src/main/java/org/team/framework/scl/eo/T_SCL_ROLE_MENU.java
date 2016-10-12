package org.team.framework.scl.eo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.team.framework.tcl.eo.EntityObject;

/**
 * 
 * <DL>
 * <DD>角色与菜单关系表.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年4月19日
 * 修改记录:
 * 初始化
 */
@Entity
@Table
public class T_SCL_ROLE_MENU extends EntityObject{

	private static final long serialVersionUID = 1L;
	@Override
	public Serializable getPK() {
		return pk;
	}

	/**
	 * 
	 * <DL>
	 * <DD>角色与菜单唯一关系.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年4月19日
	 * 修改记录:
	 * 初始化
	 */
	@Embeddable
    static public class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		/**角色外键*/
		@ManyToOne
		@JoinColumn(name="ROLE_ID")
		private T_SCL_ROLES SCL_ROLES;
		/**菜单外键*/
		@ManyToOne
		@JoinColumn(name="MENU_ID")
		private T_SCL_MENU SCL_MENU;
		public T_SCL_ROLES getSCL_ROLES() {
			return SCL_ROLES;
		}
		public void setSCL_ROLES(T_SCL_ROLES sCL_ROLES) {
			SCL_ROLES = sCL_ROLES;
		}
		public T_SCL_MENU getSCL_MENU() {
			return SCL_MENU;
		}
		public void setSCL_MENU(T_SCL_MENU sCL_MENU) {
			SCL_MENU = sCL_MENU;
		}
	}
	/**角色与菜单唯一关系*/
    @EmbeddedId
    private PK pk = new PK();
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
