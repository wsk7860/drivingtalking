package com.drivingtalking.vo.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("消息主体")
@Data
public class NoticeMainVO {

    /**
     * 未读数量
     */
    @ApiModelProperty("未读数量")
    private int unReadCounts;

    @ApiModelProperty("消息条数")
    private List<NoticeSimpleVO> notices;

}
