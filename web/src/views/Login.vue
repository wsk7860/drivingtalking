<template>
  <div class="login-body">
    <div class="login-container">
      <h3 class="login-title">欢迎登录</h3>
      <h3 class="login-title">Driving Talking</h3>
      <div class="login-data">
        <a-form :model="loginForm" status-icon ref="loginForm"
                @submit.native.prevent="login('loginForm')">
          <a-form-item prop="username">
            <a-input type="text" v-model.trim="loginForm.username" placeholder="请输入账号"></a-input>
          </a-form-item>
          <a-form-item label="" prop="password">
            <a-input type="password" v-model.trim="loginForm.password" placeholder="请输入密码"></a-input>
          </a-form-item>
          <a-form-item>
             <a-button type="primary" html-type="submit">登录</a-button>
          </a-form-item>
        </a-form>
      </div>
    </div>
  </div>
</template>

<script>
// import { login } from '@/apis/LoginApi'
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {}
    }
  },
  methods: {
    login: function (form) {
      // login({})
      this.$router.push('PageOne')
    }
  }
}
</script>

<style lang="less" scoped>
  .ant-form .ant-input {
    max-width: 100%;
  }

  .login-body {
    overflow: hidden;
    height: 100%;
    width: 100%;
    position: relative;
  }

  .login-container {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    margin: auto;
    width: 400px;
    height: 350px;
    background: #fff;
    padding: 30px 30px 0;
    box-shadow: 0 0 12px 1px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    -webkit-transition: all 0.5s ease 0s;
    transition: all 0.5s ease 0s;
  }

  .login-title {
    text-align: center;
    color: #435395;
    font-size: 18px;
    margin-top: 24px;
  }

  .login-data {
    margin-top: 28px;
  }

  .login-btn-area {
    margin-top: 26px;
  }
</style>
