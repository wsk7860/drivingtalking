package com.drivingtalking.model.directive;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

@Data
public class Directive extends BaseModel {

    private String expression;

    private String webFunction;


}
