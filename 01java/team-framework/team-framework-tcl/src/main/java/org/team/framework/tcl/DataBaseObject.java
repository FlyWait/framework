package org.team.framework.tcl;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

/**
 * 
 * <DL>
 * <DD>数据库数据基础对象.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月15日
 * 修改记录:
 * 初始化
 */
public abstract class DataBaseObject {
	   @Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        return EqualsBuilder.reflectionEquals(this, obj);
	    }
	    
	    @Override
	    public int hashCode() {
	        return HashCodeBuilder.reflectionHashCode(this);
	    }
	    
	    /**
	     * 
	     * <DL>
	     * <DD>浅拷贝当前数据库实体对象.</DD><BR>
	     * </DL>
	     * @author Team <liul>
	     * @date 2016年2月15日
	     * @param orig 浅拷贝后的数据库对象
	     */
	    public void copy(Object cloneObject) {
	    	BeanUtils.copyProperties(cloneObject, this);
	    }    
}
