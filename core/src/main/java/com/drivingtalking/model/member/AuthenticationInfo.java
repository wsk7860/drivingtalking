package com.drivingtalking.model.member;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

@Data
public class AuthenticationInfo extends BaseModel {

    /**
     * 驾驶证
     */
    private String  drivingLicenceNo;
    /**
     * 行驶证
     */
    private String  drivingPermitNo;
    /**
     * 声音认证
     */
    private String  authVoiceId;
    /**
     * 图像认证
     */
    private String  authPicId;

    /**
     * 驾驶证正面图片
     */
    private String drivingLicencePositivePicId;
    /**
     * 驾驶证反面图片
     */
    private String drivingLicenceNegativePicId;
    /**
     * 行驶证正面图片
     */
    private String drivingPermitPositivePicId;
    /**
     * 行驶证反面图片
     */
    private String drivingPermitNegativePicId;

}
