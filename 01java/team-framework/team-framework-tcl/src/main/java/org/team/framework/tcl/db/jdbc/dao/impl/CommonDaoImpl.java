package org.team.framework.tcl.db.jdbc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.team.framework.tcl.ValueObject;
import org.team.framework.tcl.db.DataBaseType;
import org.team.framework.tcl.db.Page;
import org.team.framework.tcl.db.jdbc.dao.CommonDao;
import org.team.framework.tcl.db.jdbc.dao.JdbcDaoCommonSupport;

public class CommonDaoImpl extends JdbcDaoCommonSupport  implements CommonDao{
    @Override
    public <T extends ValueObject> void queryPage(String sql,Class<T> elementType,Page page,Object... params){
        page.setReturnclass(elementType);
        StringBuffer totalSql = new StringBuffer("select count(0) from (");
        totalSql.append(sql);
        totalSql.append(")");
        long total = this.getValue(totalSql.toString(), long.class, params);
        long startRow = page.getPageIndex() * page.getPageSize();
        long endRow = startRow + page.getPageSize();
        String querySql = builderPageSql(sql,startRow,endRow);
        List<T> result = queryList(querySql,elementType,params);
        page.setData(result);
        page.setPageIndex(page.getPageIndex() + 1);
        page.setTotal(total);
    }

    @Override
    public <T extends ValueObject> List<T> queryList(String sql,Class<T> elementType,Object... params) {
        return getJdbcTemplate().queryForList(sql, params, elementType);
    }

    @Override
    public <T extends ValueObject> T query(String sql, Class<T> elementType,Object... params) {
        return getJdbcTemplate().queryForObject(sql, params,BeanPropertyRowMapper.newInstance(elementType));
    }
    
    @Override
    public <T> T getValue(String sql, Class<T> elementType, Object... params) {
        return getJdbcTemplate().queryForObject(sql, elementType,params);
    }
    
    private String builderPageSql(String sql,long startRow,long endRow){
        StringBuffer pageSql = new StringBuffer();
        if(getDataBaseType() == DataBaseType.MYSQL){
            pageSql.append("select * from (");
            pageSql.append(sql);
            pageSql.append(") as mysql_page limit ");
            startRow  = (startRow < 0) ? 0:startRow;
            pageSql.append(startRow);
            pageSql.append(",");
            pageSql.append(endRow);
            
            return pageSql.toString();
        }else if(getDataBaseType() == DataBaseType.ORACLE){
            if (startRow > 0) {
                pageSql.append("select * from ( select row_.*, rownum rownum_ from ( ");
                pageSql.append(sql);
                pageSql.append(" ) row_ ) where rownum_ <= ");
                pageSql.append(startRow + endRow);
                pageSql.append(" and rownum_ > ").append(startRow);
            } else {
                pageSql.append("select * from ( ");
                pageSql.append(sql);
                pageSql.append(" ) where rownum <= ");
                pageSql.append(endRow);
            }
            
            return pageSql.toString();
        }else{
            throw new RuntimeException("未获取到数据库类型无法解析分页SQL");
        }
    }

    @Override
    public Map<String, Object> queryForMap(String sql, Object... params) {
        return getJdbcTemplate().queryForMap(sql, params);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... params) {
        return getJdbcTemplate().queryForList(sql, params);
    }
}
