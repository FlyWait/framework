package org.team.framework.tcl.dao;

import java.util.Arrays;
import java.util.Iterator;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * 
 * <DL>
 * <DD>数据库DML操作通用方法提供类.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public abstract class DaoSupport extends HibernateDaoSupport{
	Logger logger = LoggerFactory.getLogger(DaoSupport.class);
	/**
	 * 
	 * <DL>
	 * <DD>设置查询对象查询条件.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月15日
	 * @param query 查询对象
	 * @param paras 查询参数
	 * @throws InvalidParameterException 无效的查询参数
	 */
    protected void setQueryParameters(Query query, Object...paras) throws InvalidParameterException {
        Iterator<Object> itParas = Arrays.asList(paras).iterator();
        while (itParas.hasNext() ) {
            String paraName = (String)itParas.next();
            if (!itParas.hasNext() ) {
                throw new InvalidParameterException(String.format("parameter [%s] has no corresponding values", paraName));
            }
            Object paraValue = itParas.next();
            
            logger.info("critirion---->[{}]====>[{}]",paraName,paraValue);
            query.setParameter(paraName, paraValue);
        }
    }
    
    /**
     * 
     * <DL>
     * <DD>构建SQL总数查询SQL.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param querySql 查询语句
     * @return
     */
    protected String makeCountSql(final String querySql){
        String temp = querySql.toUpperCase();
        String countSql = null;
        int beginIndex = temp.indexOf("SELECT");
        int endIndex = temp.indexOf("FROM");
        int orderByIndex = temp.indexOf("ORDER BY");
        
        if(beginIndex>endIndex){
            beginIndex = -1;
        }
        
        if(beginIndex==-1){//HQL
            countSql = "SELECT COUNT(1) ";
            countSql += querySql.substring(endIndex);
        }else{//SQL
            countSql = querySql.substring(0, beginIndex+6);
            countSql += " COUNT(1) ";
            countSql += querySql.substring(endIndex);
        }
        temp = countSql.toUpperCase();
        if (orderByIndex > endIndex){
            orderByIndex = temp.indexOf("ORDER BY");
            countSql = countSql.substring(0, orderByIndex);
        }
        
        return countSql;
    }
}
