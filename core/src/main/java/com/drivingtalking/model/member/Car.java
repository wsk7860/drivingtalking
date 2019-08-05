package com.drivingtalking.model.member;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

@Data
public class Car extends BaseModel {

    /**
     * 车颜色
     */
    private String color;

    /**
     * 型号
     */
    private String model;

    /**
     * 级别
     */
    private String level;
}
