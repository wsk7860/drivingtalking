<template>
  <div>
    <div class="logo">
      <img src="@/assets/images/logo.png" />
      <!-- <p>登录</p> -->
    </div>
    <div class="login">
      <div>
        <cube-validator
          ref="validator"
          :model="mobile"
          :rules="rules"
          v-model="valid"
        >
          <cube-input
            v-model="mobile"
            type="tel"
            :maxlength="maxlength"
            placeholder="请输入手机号码"
          ></cube-input>
        </cube-validator>
      </div>
      <div>
        <cube-button
          class="login-button"
          :primary="false"
          :inline="true"
          @click="login"
          >登录</cube-button
        >
      </div>
    </div>
  </div>
</template>
<script>
import { login } from '@/apis/LoginApi'
import { deviceready } from '@/utils/CordovaEventUtil'
import { hideSplash } from '@/utils/NativeCallUtil'
export default {
  data () {
    return {
      mobile: '',
      maxlength: 11,
      valid: undefined,
      rules: {
        required: true,
        type: 'tel'
      }
    }
  },
  methods: {
    login () {
      this.$refs.validator.validate().then(async () => {
        if (this.valid) {
          // 登录
          const result = await login({ loginName: this.mobile })
          this.$store.commit('setLoginUser', result)

          // 跳转到主页
          this.$router.replace({ name: 'HomePage' })
        }
      })
    }
  },
  created () {
    this.mobile = this.$store.getters['user/loginName'] || ''
    if (this.$store.getters['cordova/enabled']) {
      deviceready(() => {
        // 隐藏启动页，启动页默认15秒
        setTimeout(() => {
          hideSplash()
        }, 3000)
      })
    }
  }
}
</script>
<style lang="stylus" scoped>
.logo
  margin-top: 150px
  text-align: center

  & img
    width: 66px
    height: 136px

  & p
    @extend .fs36

    padding-top: 20px
    font-weight: bold

.login
  margin-top: 40%
  text-align: center

  & div
    padding: 5px 20px

.login-button
  width: 50%

>>> .cube-validator-msg
  text-align: left

.cube-validator_warn, input
  border: solid 1px yellow
</style>
