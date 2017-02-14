package org.team.framework.tcl.db;

public enum DataBaseType {
    NOTSUPPORT(0),MYSQL(1),ORACLE(2);
    
    private int value = 1;
    
    private DataBaseType(int value){
        this.value = value;
    }
    
    public int value(){
        return this.value;
    }
    
    public static DataBaseType format(int value){
        switch (value) {
        case 0:
            return DataBaseType.NOTSUPPORT;
        case 1:
            return DataBaseType.MYSQL;
        case 2:
            return DataBaseType.ORACLE;
        default:
            return null;
        }
    }
}
