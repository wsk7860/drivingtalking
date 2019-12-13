package com.drivingtalking.vo.room;

import com.drivingtalking.vo.base.BaseVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoomOnlineVO extends BaseVO implements Serializable {

    private List<String> memberIds;

    private Integer limit;


}
