package com.drivingtalking.controller;

import com.drivingtalking.util.Config;
import com.drivingtalking.util.ResponseModel;
import com.drivingtalking.util.SignalingToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController{

    @Autowired
    private Config  config;

   @RequestMapping("/pair")
    public ResponseModel pair(){
       return  null;
   }

   @RequestMapping("/call/{memberId}")
    public ResponseModel call(@PathVariable("memberId") String memberId){
       return  null;
   }

   @RequestMapping(value = "/getAgoraToken",method = RequestMethod.GET)
   public ResponseModel getAgoraToken(String userId) throws NoSuchAlgorithmException {
       String tokenString = SignalingToken.getToken(config.getAgora().getAppId(),config.getAgora().getCertificate(),userId,3600);
       return new ResponseModel<>(tokenString);
   }
}
