package com.drivingtalking.agoraaudio;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;

/**
 * Created by basaka.c on 2019/8/9.
 */
public class RtcEngineEventHandler extends IRtcEngineEventHandler {

    /**
     * 状态信息
     */
    public enum Status {
        /**
         * 正常，不内容提示
         */
        OK,
        /**
         * 错误，进行内容提示
         */
        ERROR,
        /**
         * 警告，进行内容提示
         */
        WARN,
        /**
         * 提示内容
         */
        INFO
    }

    /**
     * 错误操作
     */
    public enum Operate {
        /**
         * 什么都不做
         */
        NOTHING,
        /**
         * 更新token
         */
        RENEW_TOKEN,
        /**
         * 加入成功
         */
        JOIN_SUCCESS,
        /**
         * 用户加入
         */
        USER_JOINED,
        /**
         * 用户离线
         */
        USER_OFFLINE,
        /**
         * token已过期
         */
        REQUEST_TOKEN
    }

    /**
     * 发送回调信息
     *
     * @param status  状态
     * @param uid     用户id
     * @param message 信息
     * @throws JSONException
     */
    private void sendResult(Status status, int uid, Operate operate, String message) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", status.ordinal());
            jsonObject.put("uid", uid);
            jsonObject.put("operate", operate.ordinal());
            jsonObject.put("message", message);
            PluginResult dataResult = new PluginResult(PluginResult.Status.OK, jsonObject);
            dataResult.setKeepCallback(true);
            if (RtcEngineCreator.getInstance().getCallbackContext() != null) {
                RtcEngineCreator.getInstance().getCallbackContext().sendPluginResult(dataResult);
            }
        } catch (JSONException ex) {
            Log.e("JsonObject", ex.getMessage());
        }
    }

    @Override
    public void onError(int err) {
        // 通常情况下，SDK 上报的错误意味着 SDK 无法自动恢复，需要 App 干预或提示用户
        // TODO: 2019/8/9 未抛出该异常到前端
        switch (err) {
        case Constants.ERR_NOT_READY:
            // 3：SDK 初始化失败。Agora 建议尝试以下处理方法：检查音频设备状态
            break;
        case Constants.ERR_BUFFER_TOO_SMALL:
            // 6：传入的缓冲区大小不足以存放返回的数据。
            break;
        case Constants.ERR_TIMEDOUT:
            // 10：API 调用超时。有些 API 调用需要 SDK 返回结果，如果 SDK 处理时间过长，超过 10 秒没有返回，会出现此错误。
            break;
        case Constants.ERR_JOIN_CHANNEL_REJECTED:
            // 17：加入频道被拒绝。一般有以下原因：
            // 用户已进入频道，再次调用加入频道的 API，例如 joinChannel，会返回此错误。停止调用该方法即可。
            // 用户在做 Echo 测试时尝试加入频道。等待 Echo test 结束后再加入频道即可。
            break;
        case Constants.ERR_LEAVE_CHANNEL_REJECTED:
            // 18：离开频道失败。一般有以下原因：
            // 用户已离开频道，再次调用退出频道的 API，例如 leaveChannel，会返回此错误。停止调用该方法即可。
            // 用户尚未加入频道，就调用退出频道的 API。这种情况下无需额外操作。
            break;
        case Constants.ERR_CLIENT_IS_BANNED_BY_SERVER:
            // 123：此用户被服务器禁止。
            break;
        case Constants.ERR_ADM_GENERAL_ERROR:
            // 1005：音频设备模块：音频设备出现错误（未明确指明为何种错误）。请检查音频设备是否被其他应用占用，或者尝试重新进入频道。
            break;
        case Constants.ERR_ADM_JAVA_RESOURCE:
            // 1006：音频设备模块：使用 java 资源出现错误。
            break;
        case Constants.ERR_ADM_SAMPLE_RATE:
            // 1007：音频设备模块：设置的采样频率出现错误。
            break;
        case Constants.ERR_ADM_INIT_PLAYOUT:
            // 1008：音频设备模块：初始化播放设备出现错误。请检查播放设备是否被其他应用占用，或者尝试重新进入频道。
            break;
        case Constants.ERR_ADM_START_PLAYOUT:
            // 1009：音频设备模块：启动播放设备出现错误。请检查播放设备是否正常，或者尝试重新进入频道。
            break;
        case Constants.ERR_ADM_STOP_PLAYOUT:
            // 1010：音频设备模块：停止播放设备出现错误。
            break;
        case Constants.ERR_ADM_RUNTIME_PLAYOUT_ERROR:
            // 1015：音频设备模块：运行时播放出现错误。请检查录音设备是否正常，或者尝试重新进入频道。
            break;
        }
    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        // 加入频道回调
        this.sendResult(Status.OK, uid, Operate.JOIN_SUCCESS, "加入频道成功");
    }

    @Override
    public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
        // 重新加入频道回调
        this.sendResult(Status.OK, uid, Operate.JOIN_SUCCESS, "重新加入频道成功");
    }

    @Override
    public void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats) {
        // 离开频道回调
        this.sendResult(Status.OK, 0, Operate.NOTHING, "离开频道成功");
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {
        // 远端用户/主播加入当前频道回调。
        this.sendResult(Status.OK, uid, Operate.USER_JOINED, "新用户加入频道");
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        // 远端用户（通信模式）/主播（直播模式）离开当前频道回调。
        this.sendResult(Status.OK, uid, Operate.USER_OFFLINE, "用户离线");
    }

    public void onConnectionStateChanged(int state, int reason) {
        switch (state) {
        case Constants.CONNECTION_STATE_DISCONNECTED:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "网络连接断开");
            break;
        case Constants.CONNECTION_STATE_CONNECTING:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "建立网络连接中");
            break;
        case Constants.CONNECTION_STATE_CONNECTED:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "网络已连接");
            break;
        case Constants.CONNECTION_STATE_RECONNECTING:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "重新建立网络连接中");
            break;
        case Constants.CONNECTION_STATE_FAILED:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "网络连接失败");
            break;
        case Constants.CONNECTION_CHANGED_TOKEN_EXPIRED:
            this.sendResult(Status.OK, 0, Operate.RENEW_TOKEN, "当前使用的 Token 过期，不再有效");
            break;
        }

        // CONNECTION_STATE_DISCONNECTED(1)：网络连接断开
        // CONNECTION_STATE_CONNECTING(2)：建立网络连接中
        // CONNECTION_STATE_CONNECTED(3)：网络已连接
        // CONNECTION_STATE_RECONNECTING(4)：重新建立网络连接中
        // CONNECTION_STATE_FAILED(5)：网络连接失败
        //
        //
        // CONNECTION_CHANGED_CONNECTING(0)：建立网络连接中
        // CONNECTION_CHANGED_JOIN_SUCCESS(1)：成功加入频道
        // CONNECTION_CHANGED_INTERRUPTED(2)：网络连接中断
        // CONNECTION_CHANGED_BANNED_BY_SERVER(3)：网络连接被服务器禁止
        // CONNECTION_CHANGED_JOIN_FAILED(4)：加入频道失败
        // CONNECTION_CHANGED_LEAVE_CHANNEL(5)：离开频道
        // CONNECTION_CHANGED_INVALID_APP_ID(6)：不是有效的 APP ID。请更换有效的 APP ID 重新加入频道
        // CONNECTION_CHANGED_INVALID_CHANNEL_NAME(7)：不是有效的频道名。请更换有效的频道名重新加入频道
        // CONNECTION_CHANGED_INVALID_TOKEN(8)：生成的 Token 无效。一般有以下原因：
        // 在 Dashboard 上启用了 App Certificate，但加入频道未使用 Token。当启用了 App Certificate，必须使用
        // Token
        // 在调用 joinChannel 加入频道时指定的 uid 与生成 Token 时传入的 uid 不一致
        // CONNECTION_CHANGED_TOKEN_EXPIRED(9)：当前使用的 Token 过期，不再有效，需要重新在你的服务端申请生成 Token
        // CONNECTION_CHANGED_REJECTED_BY_SERVER(10)：此用户被服务器禁止
        // CONNECTION_CHANGED_SETTING_PROXY_SERVER(11)：由于设置了代理服务器，SDK 尝试重连
        // CONNECTION_CHANGED_RENEW_TOKEN(12)：更新 Token 引起网络连接状态改变
        // CONNECTION_CHANGED_CLIENT_IP_ADDRESS_CHANGED(13)：客户端 IP
        // 地址变更，可能是由于网络类型，或网络运营商的 IP 或端口发生改变引起
        // CONNECTION_CHANGED_KEEP_ALIVE_TIMEOUT(14)：SDK 和服务器连接保活超时，进入自动重连状态
    }

    public void onConnectionLost() {
        // 网络连接中断，且 SDK 无法在 10 秒内连接服务器回调。
        this.sendResult(Status.WARN, 0, Operate.NOTHING, "网络连接中断");
    }

    public void onTokenPrivilegeWillExpire(String token) {
        // Token 服务即将过期回调。
        this.sendResult(Status.OK, 0, Operate.RENEW_TOKEN, "Token即将过期");
    }

    public void onRequestToken() {
        // Token 过期回调。
        this.sendResult(Status.OK, 0, Operate.REQUEST_TOKEN, "Token已过期");
    }

    public void onMicrophoneEnabled(boolean enabled) {
        // 麦克风状态已改变回调。
        // true：麦克风已启用
        // false：麦克风已禁用
        this.sendResult(Status.OK, 0, Operate.NOTHING, enabled == true ? "麦克风已启用" : "麦克风已禁用");
    }

    public void onAudioVolumeIndication(IRtcEngineEventHandler.AudioVolumeInfo[] speakers, int totalVolume) {
        // 提示频道内谁正在说话以及说话者音量的回调。 0-255
    }

    public void onActiveSpeaker(int uid) {
        // 监测到活跃用户回调。
    }

    public void onFirstLocalAudioFrame(int elapsed) {
        // 已发送本地音频首帧回调
    }

    public void onFirstRemoteAudioFrame(int uid, int elapsed) {
        // 已接收远端音频首帧回调。
    }

    public void onUserMuteAudio(int uid, boolean muted) {
        // 提示有其他用户将他的音频流静音/取消静音
        // true: 该用户已静音音频
        // false: 该用户已取消音频静音
        this.sendResult(Status.OK, uid, Operate.NOTHING, muted == true ? "该用户已静音" : "该用户已取消静音");
    }

    public void onAudioRouteChanged(int routing) {
        switch (routing) {
        case Constants.AUDIO_ROUTE_DEFAULT:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "");
            break;
        case Constants.AUDIO_ROUTE_HEADSET:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "使用耳机播放");
            break;
        case Constants.AUDIO_ROUTE_EARPIECE:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "使用听筒播放");
            break;
        case Constants.AUDIO_ROUTE_HEADSETNOMIC:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "使用耳机播放");
            break;
        case Constants.AUDIO_ROUTE_SPEAKERPHONE:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "使用手机扬声器播放");
            break;
        case Constants.AUDIO_ROUTE_LOUDSPEAKER:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "使用外接扬声器播放");
            break;
        case Constants.AUDIO_ROUTE_HEADSETBLUETOOTH:
            this.sendResult(Status.WARN, 0, Operate.NOTHING, "使用蓝牙耳机播放");
            break;
        }

        // 语音路由已变更回调。
        // 当调用 setEnableSpeakerphone 成功时， SDK 会通过该回调通知 App
        // 语音路由状态已发生变化。该回调返回当前的语音路由已切换至听筒，外放(扬声器)，耳机或蓝牙。 其中 routing 定义如下:
        //
        // AUDIO_ROUTE_DEFAULT(-1)：使用默认的音频路由。
        // AUDIO_ROUTE_HEADSET(0)：使用耳机为语音路由。
        // AUDIO_ROUTE_EARPIECE(1)：使用听筒为语音路由。
        // AUDIO_ROUTE_HEADSETNOMIC(2)：使用不带麦的耳机为语音路由。
        // AUDIO_ROUTE_SPEAKERPHONE(3)：使用手机的扬声器为语音路由。
        // AUDIO_ROUTE_LOUDSPEAKER(4)：使用外接的扬声器为语音路由。
        // AUDIO_ROUTE_HEADSETBLUETOOTH(5)：使用蓝牙耳机为语音路由。
    }

    public void onNetworkTypeChanged(int type) {
        // 网络连接类型：
        // NETWORK_TYPE_UNKNOWN(-1)：网络连接类型未知
        // NETWORK_TYPE_DISCONNECTED(0)：网络连接已断开
        // NETWORK_TYPE_LAN(1)：网络类型为 LAN
        // NETWORK_TYPE_WIFI(2)：网络类型为 Wi-Fi（包含热点）
        // NETWORK_TYPE_MOBILE_2G(3)：网络类型为 2G 移动网络
        // NETWORK_TYPE_MOBILE_3G(4)：网络类型为 3G 移动网络
        // NETWORK_TYPE_MOBILE_4G(5)：网络类型为 4G 移动网络
    }
}
