package com.drivingtalking.controller;

import com.drivingtalking.exception.ControllerException;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
public class LoginController extends BaseController {

    @Autowired
    private IMemberService memberService;


    @RequestMapping("/login")
    public ResponseModel<MemberVO> login(String loginName){

        if (StringUtils.isEmpty(loginName)) {
            throw new ControllerException("手机号码不能为空");
        }
        String regex = "1[3|4|5|7|8][0-9]\\d{4,8}$";
        if (!Pattern.matches(regex,loginName)) {
            throw  new ControllerException("手机号码格式不对");
        }
        Member member =memberService.getByLoginName(loginName);
        if (member == null) {
          member = Member.buildForLoginName(loginName);
          memberService.save(member);
        }
        ContextManager.setSessionMember(member);
         return  new ResponseModel<>(map(member,MemberVO.class));
    }

    @RequestMapping("/loginOut")
    public ResponseModel<String> loginOut() {
        ContextManager.setSessionMember(null);
        ContextManager.getSession().invalidate();
        return new ResponseModel<>("退出成功");
    }

    @PostMapping("/register")
    public ResponseModel<Member> register(@RequestBody @Validated MemberVO member){
        Member perMember = map(member,Member.class);
        memberService.save(perMember);
      return  new ResponseModel<>(perMember);
    }

}
