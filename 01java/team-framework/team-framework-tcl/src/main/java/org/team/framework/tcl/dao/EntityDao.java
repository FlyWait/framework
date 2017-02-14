package org.team.framework.tcl.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.team.framework.tcl.eo.EntityObject;

/**
 * 
 * <DL>
 * <DD>数据库表实体对象.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public abstract class EntityDao<T extends EntityObject> extends DaoSupport{
	Logger logger = LoggerFactory.getLogger(EntityDao.class);
	
	/**
	 * 
	 * <DL>
	 * <DD>获取数据库表实体类对象.</DD><BR>
	 * </DL>
	 * @author Angma <liul>
	 * @date 2016年2月15日
	 * @return
	 */
	protected abstract Class<T> getEntityClass();
	
	/**
	 * 
	 * <DL>
	 * <DD>根据主键查询数据库表实体对象.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月15日
	 * @param pk 数据表实体主键
	 * @return 数据库表实体
	 */
    public T get(Serializable pk) {
        return getHibernateTemplate().get(this.getEntityClass(), pk);
    }
    
    /**
     * 
     * <DL>
     * <DD>根据主键删除数据库表实体对象.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param pk 数据表实体主键
     * @return 数据库表实体
     */
    public T delete(Serializable pk){
    	T entity = getHibernateTemplate().load(this.getEntityClass(), pk);
        
    	return this.delete(entity);
    }
    
    /**
     * 
     * <DL>
     * <DD>根据数据库表实体删除实体.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 数据库表实体对象
     * @return 数据库表实体对象
     */
    public T delete (T entity){
    	if(entity == null){
    		logger.info("entity is deleted can not be null");
    		
    		return null;
    	}
    	 try {
             getHibernateTemplate().delete(entity);
             
             return entity;
         } catch (HibernateObjectRetrievalFailureException e) {
             logger.info(e.getRootCause().getMessage());
             
             return null;
         }
    }
    
    /**
     * 
     * <DL>
     * <DD>根据数据库表实体对象移除实体对象.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 需要移除的数据库表实体对象
     */
    public T update(T entity){
    	getHibernateTemplate().update(entity);
    	
    	return entity;
    }
    
    /**
     * 
     * <DL>
     * <DD>根据自由态对象主键将对象值拷贝到数据库表实体中.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 自由态对象
     * @return 持久化数据库表实体
     */
    public T merge(T entity) {
        getHibernateTemplate().merge(entity);
        
        return entity;
    }
    
    /**
     * 
     * <DL>
     * <DD>保存数据库表实体.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 自由态的数据库表实体
     * @return 持久化态的数据库表实体
     */
    public T save(T entity){
    	getHibernateTemplate().save(entity);
    	
    	return entity;
    }
    
    /**
     * 
     * <DL>
     * <DD>保存或者更新数据库表实体.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 自由态数据库表实体
     * @return 持久化态的数据库表实体
     */
    public T saveOrUpdate(T entity){
    	getHibernateTemplate().saveOrUpdate(entity);
    	
    	return entity;
    }
    
    /**
     * 
     * <DL>
     * <DD>批量保存或者更新数据库表实体.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entitys 自由态数据库表实体集合
     */
    public void saveOrUpdate(Collection<T> entitys){
    	if(entitys == null || entitys.isEmpty()){
    		return;
    	}
    	
    	for(T entity : entitys){
    		this.saveOrUpdate(entity);
    	}
    }
    
    /**
     * 
     * <DL>
     * <DD>根据主键获取数据库表数据并锁定数据行.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param pk 数据行主键
     * @return 数据行
     */
    public T getWithLock(Serializable pk){
    	return getHibernateTemplate().get(this.getEntityClass(), pk, LockMode.PESSIMISTIC_WRITE);
    }
    
    /**
     * 
     * <DL>
     * <DD>根据数据库表实体中非主键、非关联性字段、非NULL字段进行查询所有符合的数据行.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 查询条件
     * @return 符合查询条件的查询结果集
     */
    public List<T> findByExample(T entity) {
        return getHibernateTemplate().findByExample(entity);
    }
    
    /**
     * 
     * <DL>
     * <DD>数据库表实体对象与数据库进行同步.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param entity 数据库表实体
     * @return 同步后的数据库表实体
     */
    public T refresh(T entity){
    	getHibernateTemplate().refresh(entity);
    	
    	return entity;
    }
    
    /**
     * 
     * <DL>
     * <DD>通过HQL查询实体集合.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param hql HQL查询语句.
     * @param firstResult 数据行开始位置
     * @param maxResults 数据行结束位置
     * @param paras HQL查询条件.
     * @return 符合条件的实体集合.
     */
    public List<T> queryListHQL(final String hql, final int firstResult, final int maxResults, final Object... paras) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

            @SuppressWarnings("unchecked")
			@Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setQueryParameters(query, paras);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return (List<T>) query.list();
            }
        });
    }
    
    /**
     * 
     * <DL>
     * <DD>根据HQL语句查询符合条件的数据,最大支持10000行数据.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param hql HQL查询语句.
     * @param paras HQL查询条件.
     * @return 符合条件的数据实体集合.
     */
    public List<T> queryListAllHQL(String hql, Object... paras) {
        return this.queryListHQL(hql, 0, 10000, paras);
    }
    
    /**
     * 
     * <DL>
     * <DD>通过HQL查询符合条件的第一条数据.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param hql HQL查询语句.
     * @param paras HQL查询条件.
     * @return 符合条件数据实体.
     */
    public T querySingleHQL(String hql, Object... paras) {
        List<T> list = this.queryListHQL(hql, 0, 1, paras);
        if ( (list == null) || (list.size() < 1)) {
            return null;
        }
        return list.get(0);
    }
    
    /**
     * 
     * <DL>
     * <DD>根据HQL语句查询单个字段.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月15日
     * @param field 字段类型. 
     * @param hql HQL语句.
     * @param paras HQL查询条件.
     * @return 符合查询条件的字段值.
     */
    public <A> A queryOneFieldHQL(Class<A> field, final String hql, final Object... paras) {
        Object result = getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setQueryParameters(query, paras);
                
                return query.uniqueResult();
            }
        });
        
        return FieldConverters.objectToTargetClass(field, result);
    }
    
    /**
     * 
     * <DL>
     * <DD>使用HQL分页查询数据.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param hql HQL查询语句.
     * @param page 数据分页类.
     * @param paras 查询条件参数.
     * @return
     */
    public PageAdapter<T> queryPageHQL(final String hql, final PageAdapter<T> page, final Object... paras) {
        // 查询数据总量
        String countSql = makeCountSql(hql);
        int totalCount = queryOneFieldHQL(Integer.class, countSql, paras);
        page.setDaoTotalCount(totalCount);

        // 查询分页数据
        int firstResult = page.getDaoSize() * (page.getDaoNumber() - 1);
        int maxResults = page.getDaoSize();
        List<T> content = queryListHQL(hql, firstResult, maxResults, paras);
        page.setDaoContent(content);

        return page;
    }
    
    /**
     * 
     * <DL>
     * <DD>锁定记录行数.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param hql HQL语句.
     * @param maxResults 最大结果集.
     * @param paras HQL查询条件参数.
     * @return
     */
    public List<T> lockByHQLWithLimit(final String hql, final int maxResults, final Object... paras) {
        if (paras.length < 2) {
            throw new InvalidLockException("don't lock all rows");
        }
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @SuppressWarnings("unchecked")
			@Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setQueryParameters(query, paras);
                query.setMaxResults(maxResults);
                query.setLockOptions(new LockOptions(LockMode.UPGRADE_NOWAIT));
                return query.list();
            }
        });
    }
}
