package com.drivingtalking.controller;

import com.drivingtalking.exception.ControllerException;
import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import com.drivingtalking.service.IRoomService;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.member.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Api(tags = "用户登录相关接口")
@RestController
public class LoginController extends BaseController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IRoomService roomService;


    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName",value = "登录名",defaultValue = "13800000000")
    })
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    public ResponseModel<MemberVO> login(String loginName){

        if (StringUtils.isEmpty(loginName)) {
            throw new ControllerException("车牌号码不能为空");
        }
        String regex = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";
        if (!Pattern.matches(regex,loginName)) {
            throw  new ControllerException("车牌号码格式不对");
        }
        Member member =memberService.getByLoginName(loginName);
        if (member == null) {
          member = Member.buildForLoginName(loginName);
          int rowCount = memberService.count(Member.class);
          member.setId(String.valueOf(++rowCount));
          memberService.save(member);
        }
        ContextManager.setSessionMember(member);
         return  new ResponseModel<>(map(member,MemberVO.class));
    }

    @GetMapping("/loginOut")
    @ApiOperation(value = "登出接口",httpMethod = "GET")
    public ResponseModel<String> loginOut() {
        String roomId = ContextManager.getSessionRoomId();
        String memberId = ContextManager.getSessionMember().getId();
        roomService.handleRoomMember(roomId,memberId);
        ContextManager.setSessionMember(null);
        ContextManager.getSession().invalidate();
        return new ResponseModel<>("退出成功");
    }

    @PostMapping("/register")
    @ApiOperation(value = "会员注册",httpMethod = "POST")
    public ResponseModel<Member> register(@RequestBody @Validated MemberVO member){
        Member perMember = map(member,Member.class);
        memberService.save(perMember);
      return  new ResponseModel<>(perMember);
    }

}
