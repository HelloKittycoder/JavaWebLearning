package com.kittycoder.po;

import java.util.List;

/**
 * Created by shucheng on 2020/9/11 17:27
 */
public class WindPage<K> {

    private int pageIndex;
    private int pageNum;
    private List<K> list;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<K> getList() {
        return list;
    }

    public void setList(List<K> list) {
        this.list = list;
    }
}
