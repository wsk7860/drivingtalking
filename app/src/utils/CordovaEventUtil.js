/**
 * cordova基础事件,方法仅绑定一次
 * 需在cordova环境下
 */

import { text } from './ToastUtil'

/**
 * cordova是否已启动
 * @param onDeviceReady
 */
export const deviceready = (onDeviceReady) => {
  document.addEventListener('deviceready', onDeviceReady, false)
}
/**
 * 挂起
 * @param onPause
 */
export const pause = (onPause) => {
  document.addEventListener('pause', onPause, false)
}
/**
 * 复位
 * @param onResume
 */
export const resume = (onResume) => {
  document.addEventListener('resume', () => {
    setTimeout(function () {
      onResume()
    }, 50)
  }, false)
}

/**
 * 后退按钮android
 * @param onBackButton
 */
let onBackEvent
export const backbutton = (onBackButton) => {
  if (onBackEvent) {
    document.removeEventListener('backbutton', onBackEvent, false)
  }
  onBackEvent = onBackButton
  document.addEventListener('backbutton', onBackButton, false)
}
/**
 * 后退按钮退出系统android
 */
export const backexitapp = () => {
  text('再点击一次退出')
  document.removeEventListener('backbutton', onBackEvent, false)
  document.addEventListener('backbutton', navigator.app.exitApp, false)
  setTimeout(function () {
    document.removeEventListener('backbutton', navigator.app.exitApp, false)
    document.addEventListener('backbutton', onBackEvent, false)
  }, 2000)
}
/**
 * 退出系统android
 */
export const exitapp = () => {
  navigator.app.exitApp()
}
