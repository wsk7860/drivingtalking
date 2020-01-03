/* eslint-disable */
/**
 * 创建实例
 * @param {*} onSuccess 持续接收事件回调
 */
export const agoraCreate = (onSuccess) => {
  cordova.plugins.agoraAudio.create(onSuccess, null)
}

/**
 * 加入频道
 * @param {*} token 
 * @param {*} channelName 
 * @param {*} optionalUid 
 */
export const agoraJoinChannel = (token, channelName, optionalUid) => {
  cordova.plugins.agoraAudio.joinChannel(token, channelName, optionalUid, null, null)
}

/**
 * 离开频道
 */
export const agoraLeaveChannel = () => {
  cordova.plugins.agoraAudio.leaveChannel(null, null)
}

/**
 * 更换token
 * @param {*} token 
 */
export const agoraRenewToken = (token) => {
  cordova.plugins.agoraAudio.renewToken(token, null, null)
}

/**
 * 停止/恢复发送本地音频流
 * @param {*} muted true/false
 */
export const agoraMuteLocalAudioStream = (muted) => {
  cordova.plugins.agoraAudio.muteLocalAudioStream(muted, null, null)
}

/**
 * 停止/恢复接收所有音频流
 * @param {*} muted true/false
 */
export const agoraMuteAllRemoteAudioStreams = (muted) => {
  cordova.plugins.agoraAudio.muteAllRemoteAudioStreams(muted, null, null)
}

/**
 * 设置音量
 * @param {*} volume 播放信号音量，可在 0~400 范围内进行调节： 0：静音 100：原始音量 400：最大可为原始音量的 4 倍（自带溢出保护）
 */
export const agoraAdjustPlaybackSignalVolume = (volume) => {
  cordova.plugins.agoraAudio.adjustPlaybackSignalVolume(volume, null, null)
}
