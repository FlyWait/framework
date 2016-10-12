package org.team.framework.tcl.dao;

import java.io.Serializable;

/**
 * 
 * <DL>
 * <DD>说明.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年6月2日
 * 修改记录:
 * 初始化
 */
public interface BaseHibernateDao {
	/**
	 * 
	 * <DL>
	 * <DD>删除对象.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 需要删除的对象.
	 */
	public <T> void delete(T target);

	/**
	 * 
	 * <DL>
	 * <DD>合并一个对象.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 需要合并的对象.
	 */
	public <T> void merge(T target);

	/**
	 * 
	 * <DL>
	 * <DD>刷新缓存.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 需要刷新缓存的对象.
	 */
	public <T> void refresh(T target);

	/**
	 * 
	 * <DL>
	 * <DD>保存对象..</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 需要保存的对象.
	 */
	public <T> void save(T target);

	/**
	 * 
	 * <DL>
	 * <DD>保存或更新对象.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 需要保存或更新的对象
	 */
	public <T> void saveOrUpdate(T target);

	/**
	 * 
	 * <DL>
	 * <DD>更新对象.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 需要更新的对象.
	 */
	public <T> void update(T target);
	
	/**
	 * 
	 * <DL>
	 * <DD>根据ID加载实体代理对象,如果实体不存在抛出异常.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年6月2日
	 * @param target 实体对象
	 * @param pk
	 * @return
	 */
	public <T> T load(Class<T extends EntityObject> target, Serializable pk);

	/**
	 * 根据ID值获取实体
	 * @param c
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> c, Serializable id);

	/**
	 * 将session的缓存中的数据与数据库同步
	 */
	public void flush();

	/**
	 * 清除将session的缓存
	 */
	public void clear();
	

	/**
	 * 可变参数方式获取计数
	 * 
	 * @param hql
	 * @param params
	 * @return Long
	 */
	public Long count(String hql, Object... params);

	/**
	 * 命名参数方式获取计数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 原生SQL可变参数方式获取计数
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long countBySql(String sql, Object... params);

	/**
	 * 原生SQL可变参数方式获取计数
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long countBySql(String sql, Map<String, Object> params);
	
	/**
	 * 执行HQL语句
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer executeHql(String hql, Object... params);
	
	/**
	 * 执行HQL语句
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer executeHql(String hql, Map<String, Object> params);

	/**
	 * 执行SQL,原生SQL
	 * @param sql
	 * @param params
	 * @return
	 */
	public Integer executeSql(String sql,Object... params);

	/**
	 * 执行SQL,原生SQL
	 * @param sql
	 * @param params
	 * @return
	 */
	public Integer executeSql(String sql, Map<String, Object> params);

	/* 通用范型方法 */
	
	/**
	 * 计数，总数
	 * @param c 实体类
	 * @return
	 */
	public <T> Long count(Class<T> c);

	/**
	 * 删除实体
	 * @param c 实体类
	 * @param id
	 */
	public <T> void deleteById(Class<T> c, Serializable id);

	/**
	 * 批量删除
	 * @param c 实体类
	 * @param idList
	 */
	public <T> void deleteByIdList(Class<T> c, Collection<Serializable> idList, String idFieldName);

	/**
	 * HQL查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public <T> List<T> findByHql(String hql, Object... params);

	/**
	 * HQL查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public <T> List<T> findByHql(String hql, Map<String, Object> params);

	/**
	 * HQL查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public <T> List<T> findPartByHql(String hql,int first,int count, Object... params);

	/**
	 * HQL查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public <T> List<T> findPartByHql(String hql,int first,int count, Map<String, Object> params);

	/**
	 * SQL查询,原生SQL
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> findBySql(Class<T> clazz, String sql, Object... params);

	/**
	 * SQL查询,原生SQL
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> findBySql(Class<T> clazz, String sql, Map<String, Object> params);

	/**
	 * SQL查询,原生SQL
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> findPartBySql(Class<T> clazz, String sql,int first,int count, Object... params);

	/**
	 * SQL查询,原生SQL
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> findPartBySql(Class<T> clazz, String sql,int first,int count, Map<String, Object> params);
	/**
	 * HQL分页查询
	 * @param hql
	 * @param page
	 * @param rows
	 * @param params
	 * @return
	 */
	public <T> List<T> findPageByHql(String hql, int page, int rows,
			Map<String, Object> params);

	/**
	 * HQL分页查询
	 * @param hql
	 * @param page
	 * @param rows
	 * @param params
	 * @return
	 */
	public <T> List<T> findPageByHql(String hql, int page, int rows,
			Object... params);


	/**
	 * 根据字段值返回列表
	 * @param c
	 * @param field
	 * @param fieldName
	 * @return
	 */
	public <T> List<T> findByField(Class<T> c,Object fieldValue, String fieldName);

	/**
	 * 根据字段值返回实体
	 * @param c
	 * @param field
	 * @param fieldName
	 * @param unique
	 * @return
	 */
	public <T> T getByField(Class<T> c,Object fieldValue, String fieldName, boolean unique);

	/**
	 * 根据字段值计数
	 * @param c
	 * @param field
	 * @param fieldName
	 * @return
	 */
	public <T> Long countByField(Class<T> c,Object fieldValue, String fieldName);

	/**
	 * 根据对象和指定对象字段名查找列表
	 * @param c
	 * @param object
	 * @param fieldNames
	 * @return
	 */
	public <T> List<T> findByObjectField(Class<T> c,Object o, String... fieldNames);

	/**
	 * 根据对象和指定对象字段名统计
	 * @param c
	 * @param object
	 * @param fieldNames
	 * @return
	 */
	public <T> Long countByObjectField(Class<T> c,Object o, String... fieldNames);

	/**
	 * 根据HQL获取分页
	 * @param hql
	 * @param countHql
	 * @param page
	 * @param rows
	 * @param params
	 * @return
	 */
	public <T> Page<T> getPageByHql(String hql, String countHql,
			int page, int rows, Map<String, Object> params);

	/**
	 * 根据HQL获取分页
	 * @param hql
	 * @param countHql
	 * @param page
	 * @param rows
	 * @param params
	 * @return
	 */
	public <T> Page<T> getPageByHql(String hql, String countHql,
			int page, int rows, Object... params);
}
