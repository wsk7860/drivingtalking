<template>
  <div>
    <header class="header">
      <div class="left">
        <i class="iconfont icon-mine" @click="showMine"></i>
      </div>
      <span>即时在线</span>
      <div class="right"><i class="iconfont icon-message"></i></div>
    </header>
    <div class="member-list">
      <img v-for="member in members" :key="member.id" src="@/assets/images/icon.png" :title="member.picId" />
    </div>
    <div class="audio-beat">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div class="right-toolbar">
      <div><i class="iconfont icon-mute" :class="{'muted':muted}" @click="mute"></i></div>
      <div><i class="iconfont icon-switch" @click="switchRoom"></i></div>
      <div><i class="iconfont icon-signout" @click="signout"></i></div>
    </div>
    <!-- <div class="bottom-toolbar"> -->
      <!-- <div><i class="iconfont icon-connect"></i></div> -->
      <!-- <div><i class="iconfont icon-voice"></i></div> -->
      <!-- <div><i class="iconfont icon-call"></i></div> -->
    <!-- </div> -->
  </div>
</template>

<script>
import { deviceready, exitapp } from '@/utils/CordovaEventUtil'
import { eventBus } from '@/utils/EventBusUtil'
import { agoraCreate, agoraJoinChannel, agoraMuteLocalAudioStream, agoraRenewToken } from '@/utils/AgoraUtil'
import { warn } from '@/utils/ToastUtil'
import { getRandomRoom, getRoom, joinRoom, leaveRoom } from '@/apis/RoomApi'
import { getAgoraToken } from '@/apis/HomeApi'
import { STATUS, OPERATE } from '@/constants/AgoraConstant'
export default {
  data () {
    return {
      muted: false,
      members: []
    }
  },
  methods: {
    showMine () {},
    async getRoom  () {
      // 获取房间号
      let roomId = await getRandomRoom()
      this.$store.commit('setRoom', roomId)
    },
    async joinRoom () {
      // 加入房间
      // 获取token
      let token = await getAgoraToken(this.$store.getters['room/id'])
      if (this.$store.getters['cordova/enabled']) {
        agoraJoinChannel(
          token,
          this.$store.getters['room/id'],
          this.$store.getters['user/id']
        )
      }
    },
    async leaveRoom () {
      // 退出房间，仅通知服务
      await leaveRoom(this.$store.getters['room/id'])
    },
    mute () {
      // 禁用本地麦克风
      this.muted = !this.muted
      if (this.muted && this.$store.getters['cordova/enabled']) {
        agoraMuteLocalAudioStream(this.muted)
      }
    },
    switchRoom () {
      // 切换房间
      this.leaveRoom()

      this.getRoom()
      this.joinRoom()
    },
    signout () {
      console.log('cordova下退出app')
      this.$createDialog({
        type: 'confirm',
        icon: 'cubeic-danger',
        title: '退出应用',
        confirmBtn: {
          text: '退出',
          active: true,
          disabled: false,
          href: 'javascript:;'
        },
        onConfirm: () => {
          if (this.$store.getters['cordova/enabled']) {
            exitapp()
          }
        }
      }).show()
    }
  },
  async created () {
    let that = this
    eventBus.$on('agoraEvent', async function (result) {
      console.log(result)
      if (result.status === STATUS.OK) {
        switch (result.operate) {
          case OPERATE.JOIN_SUCCESS:
            // 加入房间成功，回调服务
            await joinRoom(that.$store.getters['room/id'])
            // 获取房间成员
            that.$nextTick(async () => {
              let roomMember = await getRoom(that.$store.getters['room/id'])
              that.members = roomMember.members
            })
            break
          case OPERATE.USER_JOINED:
          case OPERATE.USER_OFFLINE:
            // 其他人加入房间，重新获取房间信息
            that.$nextTick(async () => {
              let roomMember = await getRoom(that.$store.getters['room/id'])
              that.members = roomMember.members
            })
            break
          case OPERATE.RENEW_TOKEN:
            // 重新获取token
            // 获取token
            let token = await getAgoraToken(that.$store.getters['room/id'])
            agoraRenewToken(token)
            break
          case OPERATE.REQUEST_TOKEN:
            // token已过期
            that.joinRoom()
            break
        }
      } else if (result.status === STATUS.WARN && !!result.message) {
        // 警告信息
        warn(result.message)
      }
    })

    // 创建agora实例
    if (this.$store.getters['cordova/enabled']) {
      deviceready(() => {
        agoraCreate((result) => {
          console.log(result)
          eventBus.$emit('agoraEvent', result)
        })
      })
    }

    // 获取房间号
    this.getRoom()

    // 加入房间
    this.joinRoom()
  }
}
</script>
<style lang="stylus" scoped>
.header
  display: flex
  // position relative
  height: 44px
  line-height: 44px
  text-align: center
  // background-color #edf0f4
  // box-shadow 0 1px 6px #ccc
  -webkit-backface-visibility: hidden
  backface-visibility: hidden
  z-index: 5

  & span
    flex: 1
    font-weight: 400

    @extend .fs20

  & .left
    width: 30px

  & .right
    width: 30px

.member-list
  display: flex
  justify-content: center
  flex-direction: row-reverse

  & img
    height: 60px
    width: 60px
    border-radius: 50%
    -webkit-border-radius: 50%
    margin-top: 15px
    border-color: 2px solid #fff
    margin-left: -20px

  & img:hover
    z-index: 1

.audio-beat
  margin-top: 20px
  height: 43px
  display: flex
  justify-content: center

  & span
    display: block
    background: #515151
    width: 2px
    height: 10%
    border-radius: 1px
    margin-right: 1px
    float: left
    margin-top: 5%

    &:last-child
      margin-right: 0px

    &:nth-child(1)
      animation: beat 1.5s 1.4s infinite linear

    &:nth-child(2)
      animation: beat 1.5s 1.2s infinite linear

    &:nth-child(3)
      animation: beat 1.5s 1s infinite linear

    &:nth-child(4)
      animation: beat 1.5s 0.8s infinite linear

    &:nth-child(5)
      animation: beat 1.5s 0.6s infinite linear

    &:nth-child(6)
      animation: beat 1.5s 0.4s infinite linear

    &:nth-child(7)
      animation: beat 1.5s 0.2s infinite linear

    &:nth-child(8)
      animation: beat 1.5s 0s infinite linear

    &:nth-child(9)
      animation: beat 1.5s 0.2s infinite linear

    &:nth-child(10)
      animation: beat 1.5s 0.4s infinite linear

    &:nth-child(11)
      animation: beat 1.5s 0.6s infinite linear

    &:nth-child(12)
      animation: beat 1.5s 0.8s infinite linear

    &:nth-child(13)
      animation: beat 1.5s 1s infinite linear

    &:nth-child(14)
      animation: beat 1.5s 1.2s infinite linear

    &:nth-child(15)
      animation: beat 1.5s 1.4s infinite linear

    &:nth-child(16)
      animation: beat 1.5s 1.2s infinite linear

    &:nth-child(17)
      animation: beat 1.5s 1s infinite linear

    &:nth-child(18)
      animation: beat 1.5s 0.8s infinite linear

    &:nth-child(19)
      animation: beat 1.5s 0.6s infinite linear

    &:nth-child(20)
      animation: beat 1.5s 0.4s infinite linear

    &:nth-child(21)
      animation: beat 1.5s 0.2s infinite linear

@keyframes beat
  0%
    background: #ccc
    height: 10%
    margin-top: 5%

  50%
    background: #515151
    height: 100%
    margin-top: 0%

  100%
    background: #ccc
    height: 10%
    margin-top: 5%

.right-toolbar
  position: fixed
  right: 0

  & div
    margin-top: 5px
    margin-right: 5px
    border-radius: 50%
    height: 70px
    width: 70px
    background-color: #f2f2f2
    text-align: center
    line-height: 70px

    & i
      font-size: 30px

.bottom-toolbar
  width: 100%
  position: fixed
  bottom: 0
  display: flex
  justify-content: center

  & div
    height: 70px
    width: 70px
    text-align: center

    & i
      font-size: 30px
.muted
  color $color-item-primary
</style>
