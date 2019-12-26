package com.junhao.yiqi.entity;

/**
 * 分页请求
 */
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

//    @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
//    @RequestParam(value = "pageSize", required = false, defaultValue = "8") int pageSize

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}