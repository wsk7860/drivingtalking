package com.drivingtalking.util;

import lombok.Data;

@Data
public class OrderParam {

    public enum Sequence{
        ASC,
        DESC
    }

    /**
     * 字段属性
     */
    private String  property;
    /**
     * 0：降序 1：升序
     */
    private Sequence sequence;

}
