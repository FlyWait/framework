package org.team.framework.tcl.db.jdbc.dao;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.team.framework.tcl.db.DataBaseType;

public abstract class JdbcDaoCommonSupport extends JdbcDaoSupport {
    private static DataBaseType DATABASE_TYPE = DataBaseType.NOTSUPPORT;
    public DataBaseType getDataBaseType(){
        if(DATABASE_TYPE == DataBaseType.NOTSUPPORT){
            try {
                DatabaseMetaData dataBaseMetaData  = getJdbcTemplate().getDataSource().getConnection().getMetaData();
                String dataType = dataBaseMetaData.getDatabaseProductName().toLowerCase();
                if(dataType.indexOf("mysql") > 0){
                    DATABASE_TYPE = DataBaseType.MYSQL;
                }else if(dataType.indexOf("oracle") > 0){
                    DATABASE_TYPE = DataBaseType.ORACLE;
                }else{
                    DATABASE_TYPE = DataBaseType.NOTSUPPORT;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return DATABASE_TYPE;
    }
}
