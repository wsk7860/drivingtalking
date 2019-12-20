package com.drivingtalking.vo.member;

import com.drivingtalking.vo.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "会员详细信息")
public class MemberDetailVO extends BaseVO implements Serializable {

    @ApiModelProperty(value = "车牌号码")
    private String carNumber;
    @ApiModelProperty(value = "会员标签")
    private List<String> labelNames;
    @ApiModelProperty(value = "车信息")
    private String carInfo;
}
