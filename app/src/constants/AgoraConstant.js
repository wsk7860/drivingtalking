export const STATUS = {
  /**
   * 正常，不内容提示
   */
  OK: 0,
  /**
   * 错误，进行内容提示
   */
  ERROR: 1,
  /**
   * 警告，进行内容提示
   */
  WARN: 2,
  /**
   * 提示内容
   */
  INFO: 3
}

export const OPERATE = {
  /**
   * 什么都不做
   */
  NOTHING: 0,
  /**
   * 更新token
   */
  RENEW_TOKEN: 1,
  /**
   * 加入成功
   */
  JOIN_SUCCESS: 2,
  /**
   * 用户加入
   */
  USER_JOINED: 3,
  /**
   * 用户离线
   */
  USER_OFFLINE: 4,
  /**
   * token已过期
   */
  REQUEST_TOKEN: 5
}
