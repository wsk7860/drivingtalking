package com.drivingtalking.model.member;

import com.drivingtalking.model.base.BaseModel;
import com.drivingtalking.model.base.UUIDGenerator;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Member extends BaseModel  implements Serializable {

    private String loginName;

    private String nickName;

    private String mobile;

    private String picId;

    private int isAuthentication;

    private int sex;

    private List<String> labelIds;

    private String carNumber;

    private String carInfo;

    private Date createDate;

    public static Member buildForLoginName(String loginName){
        Member member = new Member();
        member.setCreateDate(new Date());
        member.setLoginName(loginName);
        member.setMobile(loginName);
        return member;
    }



}
