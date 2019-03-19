package com.bjsxt.pojo;

import java.util.List;

/**
 * Create by Administrator on 2019/3/19
 * 分页信息
 */
public class PageInfo {

    // 这两个参数是前端传来的
    // 第几页
    private int pageNum;
    // 每页几条
    private int pageSize;

    // 存放分页数据
    private List<?> dataList;
    // 总共几条
    private int total;

    // 需要通过计算得到
    // 从第几条开始
    private int pageStart;
    // 分成几页
    private int pageCount;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public int getPageStart() {
        return (pageNum - 1) * pageSize;
    }

    public int getPageCount() {
        return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }
}
