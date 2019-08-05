package com.drivingtalking.controller;

import com.drivingtalking.model.member.Member;
import com.drivingtalking.service.IMemberService;
import com.drivingtalking.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private IMemberService memberService;

    @RequestMapping("/login")
    public ResponseModel login(String loginName){
        Member member =memberService.getByLoginName(loginName);
         return  new ResponseModel(null);
    }

    @RequestMapping("/loginOut")
    public ResponseModel loginOut() {
        return  new ResponseModel(0,"退出成功");
    }

    @RequestMapping("/register")
    public ResponseModel register(){
        return  new ResponseModel(0,"注册成功");
    }

}
