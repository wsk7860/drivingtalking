package com.drivingtalking.util;


import io.agora.media.RtcTokenBuilder;



public class SignalingToken {

    public static String getToken(String appId, String certificate, String account,String channelName,int expiredTsInSeconds){
        RtcTokenBuilder token = new RtcTokenBuilder();
        int timestamp = (int)(System.currentTimeMillis() / 1000 + expiredTsInSeconds);
        String result = token.buildTokenWithUid(appId, certificate,channelName, Integer.valueOf(account), RtcTokenBuilder.Role.Role_Publisher, timestamp);
        return result;
    }
}
