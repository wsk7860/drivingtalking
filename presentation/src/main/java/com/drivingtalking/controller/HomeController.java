package com.drivingtalking.controller;

import com.drivingtalking.service.IAgoraTokenService;
import com.drivingtalking.util.Config;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.util.SignalingToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/home")
@Api(tags = "首页相关接口")
public class HomeController extends BaseController{

    @Autowired
    private IAgoraTokenService agoraTokenService;

    @Autowired
    private Config  config;

//   @GetMapping("/pair")
//   public ResponseModel pair(){
//       return  null;
//   }
//
//   @GetMapping("/call/{memberId}")
//    public ResponseModel call(@PathVariable("memberId") String memberId){
//       return  null;
//   }

    @ApiOperation(value = "获取AgoraToken",httpMethod = "GET")
   @GetMapping(value = "/getAgoraToken")
   public ResponseModel<String> getAgoraToken(String  channelId) {
       String  tokenString  = SignalingToken.getToken(config.getAgora().getAppId(),config.getAgora().getCertificate(),ContextManager.getSessionMember().getId(),channelId,3600);
       return new ResponseModel<>(tokenString);
   }
}
