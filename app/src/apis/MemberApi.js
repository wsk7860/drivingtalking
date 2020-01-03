import { get, post } from '@/utils/HttpUtil'

const URL = '/member/'

/**
 * 根据ID获取成员个人信息
 * @param {*} id
 */
export const getMember = (id) => {
  return get(URL + 'getMember/' + id)
}

/**
 * 保存个人信息
 * @param {*} model
 */
export const saveMember = (model) => {
  return post(URL + 'saveMember', model)
}
