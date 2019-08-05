package com.drivingtalking.model.member;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class Permission extends BaseModel {

    private String name;

    private List<String>  menuIds;



}
