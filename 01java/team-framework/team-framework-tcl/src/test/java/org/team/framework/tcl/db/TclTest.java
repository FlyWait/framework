package org.team.framework.tcl.db;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.team.framework.tcl.db.eo.User;
import org.team.framework.tcl.db.service.UserService;
import org.team.framework.tcl.db.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TclTest {
    @Autowired
    private UserService userService;
    
    @Test
    public void hibernateSaveTest(){
        User user = new User();
        user.setUserCode("T001");
        user.setUserName("SaveTest");
        user.setUserDescription("hibernateSaveTest");
        user.setUserPass("111");
        user.setSalt("123456");
        user.setPhoneNumber("023");
        user.setIdCard("1111");
        user.setCreateUser(2L);
        user.setModifyUser(2L);
        Date date = new Date();
        user.setCreateTime(date);
        user.setModifyTime(date);
        
        userService.saveUser(user);
        
        UserVo userVo = userService.queryUser(user.getId());
        userVo.getCode();
    }
    
    @Test
    public void jdbcQueryTest(){
        UserVo userVo = userService.queryUser(2);
        userVo.getCode();
    }
    
    @Test
    public void jdbcGetValueTest(){
        String userName = userService.getUserName(2);
        System.out.println(userName);
    }
}
