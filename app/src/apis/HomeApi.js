import { get } from '@/utils/HttpUtil'

const URL = '/home/'

/**
 * 获取AgoraToken
 * @param {*} channelId
 */
export const getAgoraToken = (channelId) => {
  return get(URL + 'getAgoraToken', { channelId: channelId })
}
