package org.team.framework.tcl.db.service;

import org.team.framework.tcl.db.eo.User;
import org.team.framework.tcl.db.vo.UserVo;

public interface UserService {
    public User saveUser(User user);
    public UserVo queryUser(long id);
    public String getUserName(long id);
}
