package org.team.framework.tcl.db.dao.impl;

import org.springframework.stereotype.Repository;
import org.team.framework.tcl.db.dao.UserDao;
import org.team.framework.tcl.db.eo.User;
import org.team.framework.tcl.db.hibernate.dao.impl.HibernateDaoImpl;

@Repository
public class UserDaoImpl extends HibernateDaoImpl<User, Long> implements UserDao {

    @Override
    public User saveUser(User user) {
        return this.save(user);
    }

}
