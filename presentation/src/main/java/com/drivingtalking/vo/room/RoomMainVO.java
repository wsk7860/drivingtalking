package com.drivingtalking.vo.room;

import com.drivingtalking.vo.base.BaseVO;
import com.drivingtalking.vo.member.MemberPicVO;
import lombok.Data;

import java.util.List;

@Data
public class RoomMainVO extends BaseVO {
    private List<MemberPicVO> members;
}
