package com.drivingtalking.model.common;

import com.drivingtalking.model.base.BaseModel;

import java.util.Date;

public class Notice extends BaseModel {

    public enum  Type {
        /**
         * 文本
         */
        TEXT,
        /**
         * 语音
         */
        VOICE,
        /**
         * 视频
         */
        VIDEO,
        /**
         * 超链接
         */
        URL
    }

    public enum Audience {
        /**
         * 全平台
         */
        ALL,
        /**
         * 群组
         */
        GROUP,
        /**
         * 区域投送
         */
        AREA
    }

    public enum  Status {
        /**
         * 默认
         */
        DEFAULT,
        /**
         * 显示
         */
        DISPLAY,
        /**
         * 不显示
         */
        UN_DISPLAY
    }

    /**
     * 标题
     */
    private String title;
    /**
     * 信息
     */
    private String info;
    /**
     * 通知类型
     */
    private Integer type;
    /**
     * 受众类型
     */
    private Integer audience;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 推送时间
     */
    private Date  pushDate;

    private Integer status;


}
