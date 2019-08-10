package com.drivingtalking.controller;

import com.drivingtalking.exception.ControllerException;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController extends BaseController {

    @Autowired
    private IMemberService memberService;

    @RequestMapping("/login")
    public ResponseModel<MemberVO> login(String loginName){
        Member member =memberService.getByLoginName(loginName);
        if(member == null) {
            throw  new ControllerException("用户不存在");
        }
        ContextManager.setSessionMember(member);
         return  new ResponseModel<>(map(member,MemberVO.class));
    }

    @RequestMapping("/loginOut")
    public ResponseModel<String> loginOut() {
        ContextManager.setSessionMember(null);
        return new ResponseModel<>("退出成功");
    }

    @PostMapping("/register")
    public ResponseModel<Member> register(@RequestBody @Validated MemberVO member){
        Member perMember = map(member,Member.class);
        memberService.save(perMember);
      return  new ResponseModel<>(perMember);
    }

}
