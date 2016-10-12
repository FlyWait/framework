package org.team.framework.tcl.test;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <DL>
 * <DD>Tomcat启动插件.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class TomcatBootstrapHelper {
	private Logger logger = LoggerFactory.getLogger(TomcatBootstrapHelper.class);
	
	/**端口*/
    private int port = 8080;
    /**容器路径*/
    private String contextPath = "/";
    /**应用路径*/
    private String webappPath = ".";
    
    /**
     * 
     * <DL>
     * <DD>构造函数.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年3月10日
     * @param port 端口.
     * @param contextPath 容器路径.
     * @param webappPath 应用路径.
     */
    public TomcatBootstrapHelper(int port, String contextPath, String webappPath) {
        this.port = port;
        this.contextPath = contextPath;
        this.webappPath = webappPath;
    }
    
    /**
     * 
     * <DL>
     * <DD>Tomcat容器启动.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年3月10日
     */
    public void start() {
        try {
            long begin = System.currentTimeMillis();
            Tomcat tomcat = new Tomcat();
            configTomcat(tomcat);
            tomcat.start();
            long end = System.currentTimeMillis();
            logger.info("*******************************************************************************");
            logger.info("tomcat启动成功: http://127.0.0.1:{} in: {}ms", this.port + this.contextPath,(end - begin) );
            logger.info("*******************************************************************************");
            while (true) {
                // daemon
            }
        } catch (Exception e) {
            logger.error("tomcat启动失败...: {}",e.getMessage(), e);
        }
    }
    
    /**
     * 
     * <DL>
     * <DD>配置Tomcat容器..</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年3月10日
     * @param tomcat Tomcat容器实例.
     * @throws ServletException Servlet异常信息.
     */
    private void configTomcat(Tomcat tomcat) throws ServletException {
        tomcat.setBaseDir("target");
        tomcat.setPort(this.port);
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(this.port);
        connector.setURIEncoding("utf-8");
        tomcat.setConnector(connector);
        tomcat.getService().addConnector(connector);
        logger.info("webapp目录：[{}]",this.webappPath );
        Context ctx = tomcat.addWebapp(contextPath, this.webappPath);
        StandardJarScanner scanner = (StandardJarScanner)ctx.getJarScanner();
        scanner.setScanAllDirectories(true);
        scanner.setScanClassPath(true);
        tomcat.setSilent(true);
        System.setProperty("org.apache.catalina.SESSION_COOKIE_NAME", String.format("JSESSIONID%s",this.port));
    }
}
