<template>
  <a-layout class="layout">
    <a-layout-sider
      :trigger="null"
      collapsible
      v-model="collapsed"
    >
      <div class="logo">Driving Talking</div>
      <a-menu theme="dark" mode="inline" :defaultSelectedKeys="['1']">
        <a-menu-item key="1">
          <a-icon type="user"/>
          <span>菜单一</span>
        </a-menu-item>
        <a-menu-item key="2">
          <a-icon type="video-camera"/>
          <span>菜单二</span>
        </a-menu-item>
        <a-menu-item key="3">
          <a-icon type="upload"/>
          <span>菜单三</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header class="layout-right-header">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="()=> collapsed = !collapsed"
        />
      </a-layout-header>
      <a-layout-content class="layout-right-content">
        <router-view/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      collapsed: false
    }
  }
}
</script>
<style lang="less" scoped>
  .layout {
    width: 100%;
    height: 100%;
  }

  .layout .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color .3s;
  }

  .layout .trigger:hover {
    color: #1890ff;
  }

  .layout .logo {
    text-align: center;
    line-height: 32px;
    height: 32px;
    background: rgba(255, 255, 255, .2);
    margin: 16px;
  }

  .layout-right-header{
    background: #fff;
    padding: 0;
  }

  .layout-right-content{
    margin: 24px 16px;
    padding: 24px;
    background: #fff;
    minHeight: 500px;
  }
</style>
