package com.drivingtalking.vo.room;


import com.drivingtalking.vo.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class RoomVO extends BaseVO {

    /**
     * 创建者
     */
    private String createMemberId;
    /**
     * 名称
     */
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
     * 地理位置名称
     */
    private String  position;
    /**
     * 参与人员
     */
    private List<String> memberIds;

    private int isFull;

    private Integer type;
}
