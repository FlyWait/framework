package org.team.framework.tcl.test;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.team.framework.tcl.listener.ConfigEvent;

/**
 * 
 * <DL>
 * <DD>单元测试用例基类.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@TestExecutionListeners( {DependencyInjectionListener.class
                        , DirtiesContextListener.class
                        , TransactionalListener.class} )
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCase extends ApplicationObjectSupport{
	  public TestCase(){
	    }
	  
	    @Override
	    protected void initApplicationContext() throws BeansException {
	        super.initApplicationContext();
	        this.getApplicationContext().publishEvent(new ConfigEvent());
	    }
}
