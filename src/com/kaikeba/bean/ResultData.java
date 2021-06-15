package com.kaikeba.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 负责分页查询显示的BootStrap—table控件所需要的一种数据类型
 * @author: southwindow
 * @create: 2020-11-29 20:49
 **/
public class ResultData<T> {
    //显示的数据的集合
    private List<T> rows = new ArrayList<>();
    //总数
    private int total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
