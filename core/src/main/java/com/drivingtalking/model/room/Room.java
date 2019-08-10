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

    private String longitude;

    private String  latitude;

    private String  position;

    private List<String> memberIds;

    private int isFull;

    private Type type;

    private Date createDate;


}
