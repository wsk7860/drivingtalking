package com.drivingtalking.vo.notice;

import com.drivingtalking.vo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel("提醒消息VO")
public class NoticeVO extends BaseVO {

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    @NotNull(message = "标题不能为空")
    private String title;
    /**
     * 信息
     */
    @ApiModelProperty("内容")
    @NotNull(message = "内容不能为空")
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
