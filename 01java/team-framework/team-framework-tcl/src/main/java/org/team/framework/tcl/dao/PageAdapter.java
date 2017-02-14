package org.team.framework.tcl.dao;

import java.util.List;

/**
 * 
 * <DL>
 * <DD>说明.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年2月16日
 * 修改记录:
 * 初始化
 */
public abstract class PageAdapter<T> {
	/**
	 * 
	 * <DL>
	 * <DD>设置数据总数.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年2月16日
	 * @param totalCount
	 */
    public final void setDaoTotalCount(int totalCount) {
        setBizTotalCount(totalCount);
        int totalPages = (int)Math.ceil(Double.valueOf(totalCount) / getBizSize());
        setBizTotalPages(totalPages);
        if (getBizNumber() > totalPages) {
            setBizNumber(totalPages);
        }
    }
    
    /**
     * 
     * <DL>
     * <DD>设置数据.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param results 数据.
     */
    public final void setDaoContent(List<T> results) {
        setBizContent(results);
    }
    
    /**
     * 
     * <DL>
     * <DD>获取请求页码.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @return
     */
    public final int getDaoNumber() {
        if (getBizNumber() < 1) {
            setBizNumber(1);
        }
        return getBizNumber();
    }
    
    /**
     * 
     * <DL>
     * <DD>获取分页大小.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @return
     */
    public final int getDaoSize() {
        return getBizSize();
    }
    
    /**
     * <DL>
     * <DD>获取请求页码.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @return    请求页码
     */
    protected abstract int getBizNumber();

    /**
     * <DL>
     * <DD>获取分页大小.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @return    分页大小
     */
    protected abstract int getBizSize();

    /**
     * <DL>
     * <DD>设置请求页码.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param pageNumber    请求页码
     */
    protected abstract void setBizNumber(int pageNumber);

    /**
     * <DL>
     * <DD>设置分页大小.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param pageSize    分页大小
     */
    protected abstract void setBizSize(int pageSize);

    /**
     * <DL>
     * <DD>设置数据总数.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param totalCount    数据总数
     */
    protected abstract void setBizTotalCount(int totalCount);

    /**
     * <DL>
     * <DD>设置数据总页数.</DD><BR>
     * </DL>
     * @author Team <liul>
     * @date 2016年2月16日
     * @param totalPages    总页数
     */
    protected abstract void setBizTotalPages(int totalPages);

   /**
    * 
    * <DL>
    * <DD>设置数据.</DD><BR>
    * </DL>
    * @author Team <liul>
    * @date 2016年2月16日
    * @param results 数据.
    */
    protected abstract void setBizContent(List<T> results);
}
