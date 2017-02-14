package org.team.framework.tcl.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.team.framework.tcl.vo.ValueObject;

/**
 * 
 * <DL>
 * <DD>CommonDao is the base class for all Daos, some common DBMS operations which is regardless of object type has been defined in this class.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月16日
 * 修改记录:
 * 初始化
 */
@Repository
public class CommonDao extends DaoSupport{
	/**
	 * 
	 * <DL>
	 * <DD>execute update or delete DML by HQL.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月16日
	 * @param hql HQL语句.
	 * @param paras HQL查询条件参数.
	 * @return 影响行数.
	 */
	public int executeHQL(final String hql, final Object... paras) {

        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                checkWriteOperationAllowed(session);
                Query query = session.createQuery(hql);
                setQueryParameters(query, paras);
                return query.executeUpdate();
            }
        });
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>execute update or delete DML by native SQL.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月16日
	 * @param sql sql语句.
	 * @param paras sql查询条件参数.
	 * @return 影响行数.
	 */
	public int executeSQL(final String sql, final Object... paras) {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                checkWriteOperationAllowed(session);
                SQLQuery query = session.createSQLQuery(sql);
                setQueryParameters(query, paras);
                return query.executeUpdate();
            }

        });
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>execute query statement for non-managed entities/objects, can set up the max result count.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月16日
	 * @param clazz 结果类型.
	 * @param sql SQL语句.
	 * @param firstResult 结果开始行数.
	 * @param maxResults 结果结束行数.
	 * @param paras 查询条件参数.
	 * @return 查询结果集.
	 */
	public <A extends ValueObject> List<A> queryListSQL(final Class<A> clazz, final String sql, final int firstResult, final int maxResults, final Object... paras) {

        return getHibernateTemplate().execute(new HibernateCallback<List<A>>() {
            @Override
            public List<A> doInHibernate(Session session) throws HibernateException {
                // 1. set query parameter
                SQLQuery query = session.createSQLQuery(sql);
                setQueryParameters(query, paras);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);

                // 2. result transformer
                query.setResultTransformer(new CamelCaseTransformer(clazz));

                @SuppressWarnings("unchecked")
                List<A> list = (List<A>) query.list();
                return list;
            }
        });
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>execute query statement for non-managed entities/objects(max 10000 rows).</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param clazz ValueObject结果映射类型.
	 * @param sql SQL查询语句.
	 * @param paras SQL查询条件参数.
	 * @return SQL查询结果集.
	 */
	public <A extends ValueObject> List<A> queryListAllSQL(Class<A> clazz, String sql, Object... paras) {
        return this.queryListSQL(clazz, sql, 0, 10000, paras);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>execute query statement for non-managed entities/objects and return single record & field.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param clazz 映射结果类型.
	 * @param sql SQL查询语句.
	 * @param paras SQL查询条件参数.
	 * @return 映射结果.
	 */
	public <A extends ValueObject> A querySingleSQL(Class<A> clazz, String sql, Object... paras) {
        List<A> list = this.queryListSQL(clazz, sql, 0, 1, paras);
        if ( list == null || list.size() < 1) {
            return null;
        }
        return list.get(0);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>execute query statement for object and return single record & field.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param cls 结果类型.
	 * @param sql SQL查询语句.
	 * @param paras SQL查询条件参数.
	 * @return 查询结果.
	 */
	public <A> A queryOneFieldSQL(Class<A> cls, final String sql, final Object... paras) {
        Object result = getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                setQueryParameters(query, paras);
                return query.uniqueResult();
            }
        });
        return FieldConverters.objectToTargetClass(cls, result);
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>execute query statement for non-managed entities/objects and return paging.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月17日
	 * @param clazz
	 * @param sql
	 * @param page
	 * @param paras
	 * @return
	 */
	public <A extends ValueObject> PageAdapter<A> queryPageSQL(final Class<A> clazz, final String sql, final PageAdapter<A> page, final Object... paras) {
        // 查询数据总量
        String countSql = makeCountSql(sql);
        
        //容null的错误
        Object obj = queryOneFieldSQL(Integer.class, countSql, paras);
        int totalCount = 0;
        if (obj != null){
            totalCount = (int) obj;
        }
        page.setDaoTotalCount(totalCount);

        // 查询分页数据
        int firstResult = page.getDaoSize() * (page.getDaoNumber() - 1);
        int maxResults = page.getDaoSize();
        List<A> content = this.queryListSQL(clazz, sql, firstResult, maxResults, paras);
        page.setDaoContent(content);

        return page;
    }
	
	/**
	 * 
	 * <DL>
	 * <DD>检查是否启用事务.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月16日
	 * @param session
	 * @throws InvalidDataAccessApiUsageException
	 */
    private void checkWriteOperationAllowed(Session session) throws InvalidDataAccessApiUsageException {
        if (session.getFlushMode().lessThan(FlushMode.COMMIT)) {
            throw new InvalidDataAccessApiUsageException("Write operations are not allowed in read-only mode (FlushMode.MANUAL): " + "Turn your Session into FlushMode.COMMIT/AUTO or remove 'readOnly' marker from transaction definition.");
        }
    }
}
