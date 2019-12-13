package com.drivingtalking.vo.member;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class LoginVO  implements Serializable {
    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp="^1[3|4|5|7|8][0-9]\\\\d{4,8}$",message = "请确认手机号码格式")
    private String loginName;

}
