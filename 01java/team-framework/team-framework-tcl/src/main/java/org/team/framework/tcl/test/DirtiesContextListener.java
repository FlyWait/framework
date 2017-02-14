package org.team.framework.tcl.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * 
 * <DL>
 * <DD>测试完成监听器.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class DirtiesContextListener extends DirtiesContextTestExecutionListener{
	   @Override
	    public void afterTestClass(TestContext testContext) throws Exception {
	        super.afterTestClass(testContext);
	    }

	    @Override
	    public void afterTestMethod(TestContext testContext) throws Exception {
	        super.afterTestMethod(testContext);
	    }
}
