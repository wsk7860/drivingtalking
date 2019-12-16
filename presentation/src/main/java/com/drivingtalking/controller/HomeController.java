package com.drivingtalking.controller;

import com.drivingtalking.model.token.AgoraToken;
import com.drivingtalking.service.IAgoraTokenService;
import com.drivingtalking.util.Config;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.util.SignalingToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
   public ResponseModel<String> getAgoraToken(String userId) throws NoSuchAlgorithmException {
       AgoraToken agoraToken = agoraTokenService.getBeforeEffectDate(new Date());
       String tokenString;
       if (agoraToken == null) {
           tokenString  = SignalingToken.getToken(config.getAgora().getAppId(),config.getAgora().getCertificate(),userId,3600);
           agoraToken = new AgoraToken();
           agoraToken.setCreateDate(new Date());
           agoraToken.setEffectDate(DateUtils.addSeconds(agoraToken.getCreateDate(),3600));
           agoraToken.setCreateMemberId(ContextManager.getSessionMember().getId());
           agoraToken.setValue(tokenString);
           agoraTokenService.save(agoraToken);
       } else {
           tokenString = agoraToken.getValue();
       }
       return new ResponseModel<>(tokenString);
   }
}
