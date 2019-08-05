package com.drivingtalking.model.member;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

@Data
public class Member extends BaseModel {

    private String loginName;

    private String nickName;

    private String mobile;

    private String picId;

    private int isAuthentication;

    private int sex;



}
