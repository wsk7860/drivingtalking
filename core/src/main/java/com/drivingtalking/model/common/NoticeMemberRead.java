package com.drivingtalking.model.common;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NoticeMemberRead extends BaseModel implements Serializable {

    public enum Status {
        /**
         * 未读
         */
        UNREAD,
        /**
         * 已读
         */
        ALREADY_READ
    }

    /**
     * 消息ID
     */
    private String noticeId;
    /**
     * 成员ID
     */
    private String memberId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 未读状态
     */
    private Integer status;
    /**
     * 已读时间
     */
    private Date readDate;
}
