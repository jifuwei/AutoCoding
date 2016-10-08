package com.jifuwei.ac.foundation.data;

/**
 * 分页消息模板
 * Created by JFW on 2016/10/6.
 */
public class ACPageInfo {
    private int page; //当前页数
    private int rows; //显示行数
    private int total; //总数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartRecord() {
        if ((this.page - 1) * this.rows > this.total) {
            return this.rows;
        }
        return ((this.page - 1) * this.rows);
    }
}
