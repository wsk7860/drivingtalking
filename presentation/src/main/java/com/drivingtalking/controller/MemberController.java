package com.drivingtalking.controller;

import com.drivingtalking.exception.ControllerException;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.member.MemberDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
@Api(tags = "成员相关接口信息")
public class MemberController extends BaseController {


    @Autowired
    private IMemberService memberService;

    @GetMapping("/getMember/{id}")
    @ApiOperation(value = "根据ID获取成员个人信息")
    public ResponseModel<MemberDetailVO> getMemberById(@PathVariable String id) {
            Member member = memberService.getById(Member.class,id);
            if (member == null) {
            throw  new ControllerException("所在会员信息不存在");
        }
            return new ResponseModel<>(map(member,MemberDetailVO.class));
    }

    @PostMapping("/saveMember")
    @ApiOperation(value = "保存个人信息",httpMethod = "POST")
    public ResponseModel<MemberDetailVO> saveMember(@RequestBody MemberDetailVO memberVO){
        Member member = map(memberVO,Member.class);
        memberService.saveMember(member);
        return  new ResponseModel<>(memberVO);
    }
}
