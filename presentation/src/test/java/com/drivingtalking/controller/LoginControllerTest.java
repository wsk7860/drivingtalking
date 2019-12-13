package com.drivingtalking.controller;

import com.drivingtalking.configure.MessageProducer;
import com.drivingtalking.test.BaseControllerTest;
import com.drivingtalking.util.RedisUtils;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.vo.member.MemberVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class LoginControllerTest extends BaseControllerTest {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void test() {
        Map<String,Object> params = new HashMap<>();
        params.put("userId","123");
        ResponseModel responseModel = requestForGet("/home/getAgoraToken?userId={userId}",ResponseModel.class,params);
        System.out.println(responseModel.getResult());

        MemberVO memberVO = new MemberVO();
        memberVO.setLoginName("12314123");
        memberVO.setNickName("ssss");
        ResponseModel responseModel1 = requestForPostJson("/register",memberVO,ResponseModel.class);
        System.out.println(responseModel1.getErrorMessage());
    }

    @Test
    public void test2(){
        redisUtils.set("1","1");
        System.out.println(redisUtils.get("1"));
        messageProducer.send("this is message");
    }

}
