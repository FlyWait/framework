package org.team.framework.tcl.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team.framework.tcl.db.dao.UserDao;
import org.team.framework.tcl.db.eo.User;
import org.team.framework.tcl.db.jdbc.dao.CommonDao;
import org.team.framework.tcl.db.service.UserService;
import org.team.framework.tcl.db.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommonDao commonDao;
    
    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public UserVo queryUser(long id) {
        String sql = "select code,name from admin_user where id = ?";
        UserVo userVo = commonDao.query(sql, UserVo.class, id);
        
        return userVo;
    }

    @Override
    public String getUserName(long id) {
        String sql = "select name from admin_user where id = ?";
        return commonDao.getValue(sql, String.class, id);
    }

}
