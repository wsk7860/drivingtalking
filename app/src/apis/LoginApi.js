import { get, post, postForm } from '@/utils/HttpUtil'

const URL = '/'

/**
 * 登录接口
 * @param {loginName} model
 */
export const login = (model) => {
  return postForm(URL + 'login', model)
}

/**
 * 登出接口
 */
export const loginOut = () => {
  return get(URL + 'loginOut')
}

/**
 * 会员注册
 * @param {*} model
 */
export const register = (model) => {
  return post(URL + 'register', model)
}
