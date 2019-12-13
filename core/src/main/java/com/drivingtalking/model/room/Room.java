package com.drivingtalking.model.room;

import com.drivingtalking.model.base.BaseModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Room extends BaseModel {

    public enum Type {
        /**
         * 单人
         */
        SINGLE,
        /**
         * 多人
         */
        MULTIPLE
    }

    private String createMemberId;

    private String name;

    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String  latitude;
    /**
     * 地理位置
     */
    private String  position;

    private Integer type;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 人员限制
     */
    private Integer limit;


}
