package org.team.framework.tcl.db;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

	private static final long serialVersionUID = 2053067812797410016L;
	private long pageSize = 20; //每页大小
	private long pageIndex = 0; //当前页码
	private long total = 0; //总记录大小
	private String sortField; //排序字段
	private String sortOrder; //排序方向
	private List<?> data;//数据
	
	private Class<?> returnclass;
	
	private boolean ignorecount = false;//忽略总数统计，紧取分页数据
	
	/**
	 * @param returnclass the returnclass to set
	 */
	public void setReturnclass(Class<?> returnclass) {
		this.returnclass = returnclass;
	}
	
	/**
	 * @return the returnclass
	 */
	Class<?> getReturnclass() {
		return returnclass;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public long getPageCount() {
		if (total % pageSize == 0) {
            return total / pageSize;
        } else {
        	return (total / pageSize) + 1;
        }
	}
	
	/**
	 * 获取当前数据集大小
	 * @return
	 */
	public int getSize() {
		if(data!=null)return data.size();
		return 0;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isIgnorecount() {
		return ignorecount;
	}

	public void setIgnorecount(boolean ignorecount) {
		this.ignorecount = ignorecount;
	}
}
