package org.team.framework.tcl.db.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.team.framework.tcl.eo.base.BaseEo;

@Entity
@Table(name="admin_user")
public class User extends BaseEo{

    private static final long serialVersionUID = -8920791613068950097L;
    
    private Long id;
    private String userCode;
    private String userName;
    private String userDescription;
    private int userType = 0;
    private String userPass;
    private String salt;
    private String idCard;
    private String phoneNumber;
    private boolean enable = true;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "code",unique=true,length=32,nullable = false)
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    @Column(name = "name",length=32,nullable = false)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name = "description",length=256)
    public String getUserDescription() {
        return userDescription;
    }
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
    @Column(name = "type",length=1,nullable=false)
    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }
    @Column(name = "pass",length=32,nullable=false)
    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    @Column(name = "salt",length=16,nullable=false)
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    @Column(name = "idCard")
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Column(name = "isEnable",nullable=false)
    public boolean isEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
