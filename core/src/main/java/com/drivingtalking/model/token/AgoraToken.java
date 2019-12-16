package com.drivingtalking.model.token;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AgoraToken extends BaseModel implements Serializable {

    /**
     *  token 的值
     */
    private String value;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 失效时间
     */
    private Date effectDate;
    /**
     * 创建人ID
     */
    private String createMemberId;
    /**
     * 失效标识
     */
    private  Integer inValidFlag;
}
