package com.drivingtalking.model.menu;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

@Data
public class Menu extends BaseModel {

    private String name;

    private String icon;

    private String url;

}
