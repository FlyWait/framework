package org.team.framework.tcl.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.team.framework.tcl.log.LoggerFacility;


/**
 * 
 * <DL>
 * <DD>事务监听器.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class TransactionalListener extends TransactionalTestExecutionListener {
    /**Log facility*/
    private static final LoggerFacility LOGGERFACILITY = LoggerFacility.getInstance();
    
    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        LOGGERFACILITY.endBizProcess();
        super.afterTestMethod(testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);
        LOGGERFACILITY.startBizProcess(testContext.getTestMethod().getName() );
    }

    @Override
    protected void runAfterTransactionMethods(TestContext testContext) throws Exception {
        super.runAfterTransactionMethods(testContext);
    }

    @Override
    protected void runBeforeTransactionMethods(TestContext testContext) throws Exception {
        super.runBeforeTransactionMethods(testContext);
    }
}
