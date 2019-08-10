package com.drivingtalking.vo.member;

import com.drivingtalking.vo.base.BaseVO;
import lombok.Data;


import javax.validation.constraints.NotNull;

@Data
public class MemberVO extends BaseVO {

    @NotNull(message = "登录名称不能为空")
    private String loginName;

    private String nickName;

    @NotNull(message = "手机号码不能为空")
    private String mobile;

    private String picId;

    private int isAuthentication;

    private int sex;

}
