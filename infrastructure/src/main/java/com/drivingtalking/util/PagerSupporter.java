package com.drivingtalking.util;

import lombok.Data;

import java.util.List;


@Data
public class PagerSupporter {

    private int pageNo = 1;

    private int pageSize;

    private int total;

    private List<OrderParam> sorts;

    public PagerSupporter(int pageNo,int pageSize,List<OrderParam>  sorts){
        this.pageNo = pageNo;
        this.pageSize  = pageSize;
        this.sorts = sorts;
    }
}
