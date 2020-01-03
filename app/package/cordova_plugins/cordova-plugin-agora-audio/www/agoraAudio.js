var exec = require('cordova/exec');

module.exports = {
  // 创建实例
  create: function (success, error) {
    exec(success, error, 'agoraAudio', 'create', [null]);
  },
  // 加入频道
  joinChannel: function (token, channelName, optionalUid, success, error) {
    exec(success, error, 'agoraAudio', 'joinChannel', [token, channelName, optionalUid]);
  },
  // 离开频道
  leaveChannel: function (success, error) {
    exec(success, error, 'agoraAudio', 'leaveChannel', [null]);
  },
  // 更换token
  renewToken: function (token, success, error) {
    exec(success, error, 'agoraAudio', 'renewToken', [token]);
  },
  // 停止/恢复发送本地音频流
  muteLocalAudioStream: function (muted, success, error) {
    exec(success, error, 'agoraAudio', 'muteLocalAudioStream', [muted]);
  },
  // 停止/恢复接收所有音频流
  muteAllRemoteAudioStreams: function (muted, success, error) {
    exec(success, error, 'agoraAudio', 'muteAllRemoteAudioStreams', [muted]);
  },
  // 设置音量
  adjustPlaybackSignalVolume: function (volume, success, error) {
    exec(success, error, 'agoraAudio', 'adjustPlaybackSignalVolume', [volume]);
  }
}
