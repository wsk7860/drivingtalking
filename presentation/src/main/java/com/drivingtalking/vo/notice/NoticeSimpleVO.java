package com.drivingtalking.vo.notice;

import com.drivingtalking.vo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("首页消息")
@Data
public class NoticeSimpleVO extends BaseVO {

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 信息
     */
    @ApiModelProperty("内容")
    private String info;

    @ApiModelProperty("是否已读")
    private Integer isRead;

}
