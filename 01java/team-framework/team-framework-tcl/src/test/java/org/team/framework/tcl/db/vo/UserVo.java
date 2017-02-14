package org.team.framework.tcl.db.vo;

import org.team.framework.tcl.ValueObject;

public class UserVo extends ValueObject{
    private static final long serialVersionUID = 9092495484372219839L;

    private String code;
    private String name;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
