package com.drivingtalking.vo.member;

import lombok.Data;

@Data
public class RegisterVO {

    private String loginName;

    private String drivingLicenceNo;

    private String drivingPermitNo;

    private String  authVoiceId;
}
