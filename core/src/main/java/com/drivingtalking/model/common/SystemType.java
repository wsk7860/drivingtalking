package com.drivingtalking.model.common;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

@Data
public class SystemType extends BaseModel {


    private String code;

    private String name;

    private int isTop;

    private String pid;

}
