package org.team.framework.tcl.db.jdbc.dao;

import java.util.List;
import java.util.Map;

import org.team.framework.tcl.ValueObject;
import org.team.framework.tcl.db.Page;

public interface CommonDao{
    /**
     * 分页查询.
     * @param sql Sql语句.
     * @param elementType 分页返回的对象.
     * @param page 分页器.
     * @param params 查询参数.
     */
    public <T extends ValueObject> void queryPage(String sql,Class<T> elementType,Page page,Object... params);
    
    /**
     * 列表查询.
     * @param sql sql语句.
     * @param elementType 返回List类型.
     * @param params 查询参数.
     * @return
     */
    public <T extends ValueObject> List<T> queryList(String sql,Class<T> elementType,Object... params);
    
    /**
     * 单对象查询.
     * @param sql sql语句.
     * @param elementType 返回类型.
     * @param params 查询参数.
     * @return
     */
    public <T extends ValueObject> T query(String sql,Class<T> elementType,Object... params);
    
    /**
     * 单字段查询.
     * @param sql sql语句.
     * @param elementType 返回类型.
     * @param params 查询参数.
     * @return
     */
    public <T> T getValue(String sql,Class<T> elementType,Object... params);
    
    /**
     * Map结果集查询.
     * @param sql sql语句.
     * @param params 查询参数.
     * @return
     */
    public Map<String, Object> queryForMap(String sql, Object... params);
    
    /**
     * 列表Map结果集查询.
     * @param sql sql语句.
     * @param params 查询参数.
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql, Object... params);
}
