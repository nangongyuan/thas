package com.yuan.dto;

import java.util.List;

public class PageResult<T> {
    private long total;
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
