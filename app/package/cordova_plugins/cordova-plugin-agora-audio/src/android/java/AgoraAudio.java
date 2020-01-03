package com.drivingtalking.agoraaudio;

import android.app.Activity;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import io.agora.rtc.IRtcEngineEventHandler;

/**
 * Created by basaka.c on 2019/8/9.
 */
public class AgoraAudio extends CordovaPlugin {

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    LOG.i("Join channel success, uid: " + (uid & 0xFFFFFFFFL),"Join channel success");
                }
            });
        }
    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("create")) {
            checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID);
            if (RtcEngineCreator.getInstance().getRtcEngine() == null) {
                Activity activity = cordova.getActivity();

                RtcEngineCreator.getInstance().setAppId(activity.getString(activity.getApplication().getResources()
                        .getIdentifier("agora_app_id", "string", activity.getApplication().getPackageName())));
                RtcEngineCreator.getInstance().setRtcEngineEventHandler(mRtcEventHandler);
                RtcEngineCreator.getInstance().setContext(activity.getBaseContext());
                RtcEngineCreator.getInstance().setCallbackContext(callbackContext);
                RtcEngineCreator.getInstance().setRtcEngine();
            }

            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "初始化成功");
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        } else if (action.equals("joinChannel")) {
            String token = args.getString(0);
            String channelName = args.getString(1);
            int optionalUid = args.getInt(2);
            if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID)) {
                RtcEngineCreator.getInstance().getRtcEngine().leaveChannel();
                RtcEngineCreator.getInstance().getRtcEngine().joinChannel(token, channelName, null, optionalUid);
            }
            callbackContext.success();
        } else if (action.equals("leaveChannel")) {
            RtcEngineCreator.getInstance().getRtcEngine().leaveChannel();
            callbackContext.success();
        } else if (action.equals("renewToken")) {
            // 新的token
            String token = args.getString(0);
            RtcEngineCreator.getInstance().getRtcEngine().renewToken(token);
            callbackContext.success();
        } else if (action.equals("muteLocalAudioStream")) {
            boolean muted = Boolean.valueOf(args.getString(0));
            RtcEngineCreator.getInstance().getRtcEngine().muteLocalAudioStream(muted);
            callbackContext.success();
        } else if (action.equals("muteAllRemoteAudioStreams")) {
            boolean muted = Boolean.valueOf(args.getString(0));
            RtcEngineCreator.getInstance().getRtcEngine().muteAllRemoteAudioStreams(muted);
            callbackContext.success();
        } else if (action.equals("adjustPlaybackSignalVolume")) {
            // 播放信号音量，可在 0~400 范围内进行调节： 0：静音 100：原始音量 400：最大可为原始音量的 4 倍（自带溢出保护）
            int volume = args.getInt(0);
            RtcEngineCreator.getInstance().getRtcEngine().adjustPlaybackSignalVolume(volume);
            callbackContext.success();
        }
        return false;
    }

    private static final int PERMISSION_REQ_ID = 22;

    // App 运行时确认麦克风和摄像头设备的使用权限。
    private static final String[] REQUESTED_PERMISSIONS = { Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    private boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(cordova.getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(cordova.getActivity(), REQUESTED_PERMISSIONS, requestCode);
            return false;
        }

        return true;
    }
}
