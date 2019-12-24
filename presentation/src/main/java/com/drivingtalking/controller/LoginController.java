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
