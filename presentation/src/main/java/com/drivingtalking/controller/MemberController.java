package com.drivingtalking.controller;

import com.drivingtalking.exception.ControllerException;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RestController
public class MemberController extends BaseController {


    @Autowired
    private IMemberService memberService;

    @GetMapping("/getMember/{id}")
    public ResponseModel<MemberVO> getMemberById(@PathVariable String id) {
            Member member = memberService.getById(Member.class,id);
            if (member == null) {
            throw  new ControllerException("所在会员信息不存在");
        }
            return new ResponseModel<>(map(member,MemberVO.class));
    }
}
