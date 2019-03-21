package com.bjsxt.pojo;

import java.util.List;

/**
 * Create by Administrator on 2019/3/21
 */
public class PageInfo {

    // 当前第几页
    private int pageNum;
    // 每页几条
    private int pageSize;
    // 总页数（通过计算set进去的）
    private int total;
    // 当前页的数据（通过查询set进去的）
    private List<?> list;

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

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
