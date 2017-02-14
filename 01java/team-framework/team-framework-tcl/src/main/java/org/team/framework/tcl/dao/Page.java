package org.team.framework.tcl.dao;

import java.util.List;

/**
 * 
 * <DL>
 * <DD>数据分页类.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月16日
 * 修改记录:
 * 初始化
 */
public class Page<T> extends PageAdapter<T>  {
    /** 请求页码 .*/
    private int pageNumber;
    /** 分页大小 .*/
    private int pageSize;
    /** 数据总数 .*/
    private int totalCount;
    /** 总页数. */
    private int totalPages;
    /** 数据 .*/
    private List<T> results;
    
    /**
     * 
     * <DL>
     * <DD>默认构造函数.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     */
    public Page(){
    }
    
    /**
     * 
     * <DL>
     * <DD>带参构造函数.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param pageNumber 请求开始页码
     * @param pageSize 分页数
     */
    public Page(int pageNumber,int pageSize){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

	@Override
	protected int getBizNumber() {
		return this.pageNumber;
	}

	@Override
	protected int getBizSize() {
		return this.pageSize;
	}

	@Override
	protected void setBizNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	protected void setBizSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	protected void setBizTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	protected void setBizTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	protected void setBizContent(List<T> results) {
		this.results = results;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

}
