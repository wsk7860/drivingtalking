package com.drivingtalking.agoraaudio;

import android.content.Context;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.IRtcEngineEventHandler;

/**
 * Created by basaka.c on 2019/8/9.
 */
public class RtcEngineCreator {
    private RtcEngine rtcEngine;
    private CallbackContext callbackContext;
    private Context context;
    private IRtcEngineEventHandler rtcEngineEventHandler;
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public RtcEngine getRtcEngine() {
        return rtcEngine;
    }

    public void setRtcEngine() {
        if (this.rtcEngine == null) {
            try {
                this.rtcEngine = RtcEngine.create(context, appId, rtcEngineEventHandler);
                this.rtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
            } catch (Exception ex) {
                LOG.e(this.getClass().getSimpleName(), ex.getMessage());
            }
        }
    }

    public CallbackContext getCallbackContext() {
        return callbackContext;
    }

    public void setCallbackContext(CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IRtcEngineEventHandler getRtcEngineEventHandler() {
        return rtcEngineEventHandler;
    }

    public void setRtcEngineEventHandler(IRtcEngineEventHandler rtcEngineEventHandler) {
        this.rtcEngineEventHandler = rtcEngineEventHandler;
    }

    private static final RtcEngineCreator creator = new RtcEngineCreator();

    public static RtcEngineCreator getInstance() {
        return creator;
    }
}
