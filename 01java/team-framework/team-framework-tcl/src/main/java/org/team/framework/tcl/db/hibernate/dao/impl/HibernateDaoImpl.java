package org.team.framework.tcl.db.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.team.framework.tcl.EntityObject;
import org.team.framework.tcl.db.Page;
import org.team.framework.tcl.db.hibernate.dao.HibernateDao;

public abstract class HibernateDaoImpl<T extends EntityObject, ID extends Serializable>
        extends HibernateDaoSupport implements HibernateDao<T, ID> {

    @Override
    public T execute(HibernateCallback<T> action) throws DataAccessException {
        return getHibernateTemplate().execute(action);
    }

    @Override
    public T get(Class<T> entityClass, ID id) throws DataAccessException {
        return getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public T get(Class<T> entityClass, ID id, LockMode lockMode) throws DataAccessException {
        return getHibernateTemplate().get(entityClass, id, lockMode);
    }

    @Override
    public T load(Class<T> entityClass, ID id) throws DataAccessException {
        return getHibernateTemplate().load(entityClass, id);
    }

    @Override
    public T load(Class<T> entityClass, ID id, LockMode lockMode) throws DataAccessException {
        return getHibernateTemplate().load(entityClass, id, lockMode);
    }

    @Override
    public List<T> loadAll(Class<T> entityClass) throws DataAccessException {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public void load(T entity, ID id) throws DataAccessException {
        getHibernateTemplate().load(entity, id);
    }

    @Override
    public void refresh(T entity) throws DataAccessException {
        getHibernateTemplate().refresh(entity);
    }

    @Override
    public void refresh(T entity, LockMode lockMode) throws DataAccessException {
        getHibernateTemplate().refresh(entity, lockMode);
    }

    @Override
    public boolean contains(T entity) throws DataAccessException {
        return getHibernateTemplate().contains(entity);
    }

    @Override
    public void evict(T entity) throws DataAccessException {
        getHibernateTemplate().evict(entity);
    }

    @Override
    public void initialize(T proxy) throws DataAccessException {
        getHibernateTemplate().initialize(proxy);
    }

    @Override
    public Filter enableFilter(String filterName) throws IllegalStateException {
        return getHibernateTemplate().enableFilter(filterName);
    }

    @Override
    public void lock(T entity, LockMode lockMode) throws DataAccessException {
        getHibernateTemplate().lock(entity, lockMode);
    }

    @Override
    public void lock(String entityName, T entity, LockMode lockMode) throws DataAccessException {
        getHibernateTemplate().lock(entityName, entity, lockMode);
    }

    @Override
    public T save(T entity) throws DataAccessException {
        getHibernateTemplate().save(entity);
        
        return entity;
    }

    @Override
    public void update(T entity) throws DataAccessException {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void update(T entity, LockMode lockMode) throws DataAccessException {
        getHibernateTemplate().update(entity, lockMode);
    }

    @Override
    public void saveOrUpdate(T entity) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void replicate(T entity, ReplicationMode replicationMode) throws DataAccessException {
        getHibernateTemplate().replicate(entity, replicationMode);
    }

    @Override
    public void replicate(String entityName, T entity, ReplicationMode replicationMode)
            throws DataAccessException {
        getHibernateTemplate().replicate(entityName, entity, replicationMode);
    }

    @Override
    public void persist(T entity) throws DataAccessException {
        getHibernateTemplate().persist(entity);
    }

    @Override
    public T merge(T entity) throws DataAccessException {
        return getHibernateTemplate().merge(entity);
    }

    @Override
    public T merge(String entityName, T entity) throws DataAccessException {
        return getHibernateTemplate().merge(entityName, entity);
    }

    @Override
    public void delete(T entity) throws DataAccessException {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void delete(T entity, LockMode lockMode) throws DataAccessException {
        getHibernateTemplate().delete(entity,lockMode);
    }

    @Override
    public void delete(String entityName, T entity) throws DataAccessException {
        getHibernateTemplate().delete(entityName, entity);
    }

    @Override
    public void delete(String entityName, T entity, LockMode lockMode) throws DataAccessException {
        getHibernateTemplate().delete(entityName, entity, lockMode);
    }

    @Override
    public void deleteAll(Collection<T> entities) throws DataAccessException {
        getHibernateTemplate().deleteAll(entities);
    }

    @Override
    public void flush() throws DataAccessException {
        getHibernateTemplate().flush();
    }

    @Override
    public void clear() throws DataAccessException {
        getHibernateTemplate().clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String queryString, Object... values) throws DataAccessException {
        List<?> result = getHibernateTemplate().find(queryString, values);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedParam(String queryString, String paramName, Object value)
            throws DataAccessException {
        List<?> result = getHibernateTemplate().findByNamedParam(queryString, paramName, value);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedParam(String queryString, String[] paramNames, Object[] values)
            throws DataAccessException {
        List<?> result = getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByValueBean(String queryString, Object valueBean)
            throws DataAccessException {
        List<?> result = getHibernateTemplate().findByValueBean(queryString, valueBean);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String queryName, Object... values) throws DataAccessException {
        List<?> result = getHibernateTemplate().findByNamedQuery(queryName, values);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
            throws DataAccessException {
        List<?> result = getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramName,value);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQueryAndNamedParam(String queryName, String[] paramNames,
            Object[] values) throws DataAccessException {
        List<?> result = getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames,values);
        if(result == null){
            return null;
        }else{
            return (List<T>)result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterate(String queryString, Object... values) throws DataAccessException {
        Iterator<?> result = getHibernateTemplate().iterate(queryString, values);
        if(result == null){
            return null;
        }else{
            return (Iterator<T>)result;
        }
    }

    @Override
    public void closeIterator(Iterator<T> it) throws DataAccessException {
        getHibernateTemplate().closeIterator(it);
    }

    @Override
    public int bulkUpdate(String queryString, Object... values) throws DataAccessException {
        return getHibernateTemplate().bulkUpdate(queryString, values);
    }
    
    @Override
    public void findPageByHql(String queryString,Page page,Object... params){
        long total = this.countByHql(queryString, params);
        page.setTotal(total);
       getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query<T> query = session.createQuery(queryString,getEntityClass());
                if(params != null){
                    for(int i=0; i<params.length;i++){
                        query.setParameter(i, params[i]);
                    }
                }
                Long firstResult =  new Long(page.getSize() * page.getPageIndex());
                Long maxResult = new Long(firstResult + page.getSize());
                query.setFirstResult(firstResult.intValue());
                query.setMaxResults(maxResult.intValue());
                List<T> results = query.getResultList();
                page.setData(results);
                page.setReturnclass(getEntityClass());
                page.setPageIndex(page.getPageIndex() + 1L);
                
                return results;
            }
            
        });
    }
    
    private long countByHql(String queryString,Object... params){
        Long count = getHibernateTemplate().execute(new HibernateCallback<Long>(){
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                StringBuffer countString = new StringBuffer("select count(1) ");
                countString.append(queryString);
                Query<Long> query = session.createQuery(countString.toString(), Long.class);
                if(params != null){
                    for(int i=0; i<params.length;i++){
                        query.setParameter(i, params[i]);
                    }
                }
                
                return query.uniqueResultOptional().get();
            }
            
        });
        
        if(count == null){
            return 0L;
        }else{
            return count;
        }
    }
    
    private Class<T> getEntityClass(){
        @SuppressWarnings("unchecked")
        Class < T >  entityClass  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
        
        return entityClass;
    }
}
