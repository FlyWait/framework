package org.team.framework.tcl.listener;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;

/**
 * 
 * <DL>
 * <DD>资源文件监听器.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class ConfigListener implements ApplicationListener<ConfigEvent>{
	Logger logger = LoggerFactory.getLogger(ConfigListener.class);
	
	  /**数据库驱动类*/
    @Value("${connection.driver_class}")
    private String driverClass;
    
    /**数据库连接地址*/
    @Value("${connection.url}")
    private String url;
    /**用户名*/
    @Value("${connection.username}")
    private String dbUsername;
    /**数据库方言*/
    @Value("${hibernate.dialect}")
    private String dialect;
    
    /**连接池中保留的最小连接数*/
    @Value("${c3p0.min_pool_size}")
    private String minPoolSize;
    
    /**接池中保留的最大连接数*/
    @Value("${c3p0.max_pool_size}")
    private String maxPoolSize;
    
    /**初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间*/
    @Value("${c3p0.initial_pool_size}")
    private String initialPoolSize;
    
    /**最大空闲时间，60秒内未使用则连接被丢弃，若为0则永不回收*/
    @Value("${c3p0.max_idle_time}")
    private String maxIdleTime;
    
    /**当连接池中的连接耗尽的时候c3p0一次同时获取的连接数*/
    @Value("${c3p0.acquire_increment}")
    private String acuireIncrement;
    
    /**JDBC的标准参数，用以控制数据源内加载的PreparedStatements数量*/
    @Value("${c3p0.max_statements}")
    private String maxStatements;
    
    /**每60秒检查所有连接池中的空闲连接*/
    @Value("${c3p0.idle_connection_test_period}")
    private String idleConnectionTestPeriod;
    
    /**定义在从数据库获取新连接失败后重复尝试的次数*/
    @Value("${c3p0.acquire_retry_attempts}")
    private String acquireRetryAttempts;
    
    /**获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常，但是数据源仍有效保留， 并在下次调用getConnection()的时候继续尝试获取连接*/
    @Value("${c3p0.break_after_acquire_failure}")
    private String breakAfterAcquireFailure;     
    
    /**true表示每次把连接checkin到pool里的时候测试其有效性，因为是个事后操作，所以是异步的，应用端不需要等待测试结果，但同样会造成至少多一倍的数据库调用*/
    @Value("${c3p0.test_connection_on_checkin}")
    private String testConnectionOnCheckin;
    
    /**因性能消耗大请只在需要的时候使用它.如果设为true那么在每个connection提交的时候都将效验其有效性*/
    @Value("${c3p0.test_connection_on_checkout}")
    private String testConnectionOnCheckout;

    /**连接数据库时是否使用Unicode编码*/
    @Value("${hibernate.connection.useUnicode}")
    private String useUnicode;
    
    /**字节编码*/
    @Value("${hibernate.connection.characterEncoding}")
    private String characterEncoding;
    /**连接池数量*/
    @Value("${hibernate.connection.pool_size}")
    private String poolSize;
    
    /**缓存类*/
    @Value("${hibernate.cache.provider_class}")
    private String providerClass;
    
    /**显示SQL*/
    @Value("${hibernate.show_sql}")
    private String showSql;
    
    /**sql格式化*/
    @Value("${hibernate.format_sql}")
    private String formatSql;
    
    /**SQL注入注解*/
    @Value("${hibernate.use_sql_comments}")
    private String useSqlComments;
    
    /**自动创建|更新|验证数据库表结构*/
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dllAuto;
    
    /**延迟加载*/
    @Value("${hibernate.default_batch_fetch_size}")
    private String defBatchFetchSize;
    
    /**外连接抓取树的最大深度*/
    @Value("${hibernate.max_fetch_depth}")
    private String maxFetchDepth;
    
    /**批处理大小*/
    @Value("${hibernate.jdbc.batch_size}")
    private String jdbcBatchSize;
    
    /**查询编译器*/
    @Value("${hibernate.query.factory_class}")
    private String queryFactoryClass;
    
    /**
     * 
     * <DL>
     * <DD>打印所有配置文件信息.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年3月10日
     */
    public void print() {
        URL appPropURL    = this.getClass().getClassLoader().getResource("application.properties");
        URL dbPropURL     = this.getClass().getClassLoader().getResource("database.properties");
        URL log4jPropURL  = this.getClass().getClassLoader().getResource("log4j.properties");
        logger.info("*******************************************************************************");        
        logger.info("******************** System Properties Configration Info **********************");        
        logger.info("-------------------------------------------------------------------------------");
        logger.info("properties files' location:");
        logger.info("[{}]",appPropURL.getPath() );
        logger.info("[{}]",dbPropURL.getPath());
        logger.info("[{}]",log4jPropURL.getPath());
        logger.info("-------------------------------------------------------------------------------");
        logger.info("database configration:");
        logger.info("connection.driver_class={}",driverClass);
        logger.info("connection.url={}" ,url);
        logger.info("connection.username={}" , dbUsername);
        logger.info("hibernate.dialect={}" ,dialect);
        logger.info("-------------------------------------------------------------------------------");
        logger.info("database connection pool configration:");
        logger.info("c3p0.min_pool_size={}",minPoolSize);
        logger.info("c3p0.max_pool_size={}", maxPoolSize);
        logger.info("c3p0.initial_pool_size={}",initialPoolSize);
        logger.info("c3p0.max_idle_time={}",maxIdleTime);
        logger.info("c3p0.acquire_increment={}",acuireIncrement);
        logger.info("c3p0.max_statements={}",maxStatements);
        logger.info("c3p0.idle_connection_test_period={}",idleConnectionTestPeriod);
        logger.info("c3p0.acquire_retry_attempts={}",acquireRetryAttempts);
        logger.info("c3p0.break_after_acquire_failure={}",breakAfterAcquireFailure);            
        logger.info("c3p0.test_connection_on_checkin={}",testConnectionOnCheckin);
        logger.info("c3p0.test_connection_on_checkout={}",testConnectionOnCheckout);
        logger.info("-------------------------------------------------------------------------------");
        logger.info("hibernate configration:");
        logger.info("hibernate.connection.useUnicode={}" ,useUnicode);
        logger.info("hibernate.connection.characterEncoding={}" , characterEncoding);
        logger.info("hibernate.connection.pool_size=," , poolSize);
        logger.info("hibernate.cache.provider_class={}", providerClass);
        logger.info("hibernate.show_sql={}", showSql);
        logger.info("hibernate.format_sql={}",formatSql);
        logger.info("hibernate.use_sql_comments={}" ,useSqlComments);
        logger.info("hibernate.hbm2ddl.auto={}", hbm2dllAuto);
        logger.info("hibernate.default_batch_fetch_size={}",defBatchFetchSize);
        logger.info("hibernate.max_fetch_depth={}",maxFetchDepth);
        logger.info("hibernate.jdbc.batch_size={}",jdbcBatchSize);
        logger.info("hibernate.query.factory_class={}",queryFactoryClass);
        logger.info("******************** System Properties Configration Info **********************");        
        logger.info("*******************************************************************************");        
    }
    
    @Override
    public void onApplicationEvent(ConfigEvent event) {
        if (event != null) {
            logger.info("****************************** Config Event Start *************************************");
            print();
            logger.info("****************************** Config Event End   *************************************");
        }
    }
}
