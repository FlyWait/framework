package org.team.framework.tcl.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * 
 * <DL>
 * <DD>依赖注入监听器.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class DependencyInjectionListener extends DependencyInjectionTestExecutionListener {
	   @Override
	    public void beforeTestMethod(TestContext testContext) throws Exception {
	        super.beforeTestMethod(testContext);
	    }

	    @Override
	    protected void injectDependencies(TestContext testContext) throws Exception {
	        super.injectDependencies(testContext);
	    }

	    @Override
	    public void prepareTestInstance(TestContext testContext) throws Exception {
	        super.prepareTestInstance(testContext);
	    }
}
