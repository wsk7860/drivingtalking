import { get, post } from '@/utils/HttpUtil'

const URL = '/room/'

/**
 * 随机获取房间ID
 * @param {*} latitude 纬度
 * @param {*} longitude 经度
 */
export const getRandomRoom = (latitude, longitude) => {
  return get(URL + 'getRandomRoom', { latitude: latitude, longitude: longitude })
}

/**
 * 获取房间信息
 * @param {*} id
 */
export const getRoom = (id) => {
  return get(URL + 'getRoom/' + id)
}

/**
 * 加入房间
 * @param {*} id
 */
export const joinRoom = (id) => {
  return get(URL + 'joinRoom/' + id)
}

/**
 * 离开房间
 * @param {*} id
 */
export const leaveRoom = (id) => {
  return get(URL + 'leaveRoom/' + id)
}

/**
 * 保存房间
 * @param {*} model
 */
export const saveOrUpdateRoom = (model) => {
  return post(URL + 'saveOrUpdateRoom', model)
}
