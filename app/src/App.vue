<template>
  <div id="app">
    <router-view />
  </div>
</template>
<style lang="stylus">
@import './assets/css/iconfont.css'
// /* * 主题由外部配置引入，此处引入非主题公共变量、基础样式 */
// @require './base.styl'

#app
  -webkit-font-smoothing: antialiased
  -moz-osx-font-smoothing: grayscale
</style>
