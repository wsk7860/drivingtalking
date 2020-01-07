import axios from 'axios'
import qs from 'qs'
import router from '@/router'
import { text, warn } from './ToastUtil'

axios.defaults.timeout = 30000

const SERVICE_URL = 'http://47.107.98.186:8081'
// const SERVICE_URL = 'http://10.0.2.4:8095'

let toUtf8 = (input) => {
  if (typeof input !== 'string') {
    return input
  }
  let patt = /[\ud800-\udbff][\udc00-\udfff]/g
  input = input.replace(patt, (char) => {
    let H, L, code
    if (char.length === 2) {
      H = char.charCodeAt(0)
      L = char.charCodeAt(1)

      code = (H - 0xD800) * 0x400 + 0x10000 + L - 0xDc00
      return '#&' + code + ';'
    } else {
      return char
    }
  })
  return input
}

let toUnicode = (input) => {
  if (typeof input !== 'string') {
    return input
  }
  let patt = /#&\d+;/g
  let H, L, code
  let arr = input.match(patt) || []
  for (let i = 0; i < arr.length; i++) {
    let strCode = arr[i]
    code = strCode.replace('#&', '').replace(';', '')

    H = Math.floor((code - 0x10000) / 0x400) + 0xD800
    L = (code - 0x10000) % 0x400 + 0xDC00
    let str = String.fromCharCode(H, L)
    input = input.replace(strCode, str)
  }
  return input
}

let evil = (fn) => {
  let Fn = Function
  return new Fn('return ' + fn)()
}

let toLogin = () => {
  router.replace({
    path: '/login',
    query: { redirect: router.currentRoute.fullPath }
  })
}

// 请求参数，用于重复请求判断，用于【POST】方法过滤
let pending = []
let CancelToken = axios.CancelToken
let removePending = (config) => {
  let url = config.url
  for (let p in pending) {
    if (pending[p].url === url) {
      pending[p].func()
      pending.splice(p, 1)
    }
  }
}

// 请求成功后，延迟300毫秒第二个请求才可以继续，用于【POST】方法过滤
let done = []
let cancelRequest = (config, cancel) => {
  let url = config.url
  let now = new Date()
  for (let d in done) {
    if ((now.getTime() - done[d].date.getTime()) > 300) {
      done.splice(d, 1)
    } else if (done[d].url === url) {
      cancel()
      done[d].date = new Date()
    }
  }
}

/**
 * 请求拦截
 */
axios.interceptors.request.use((config) => {
  if (config.method === 'post') {
    let dataString = JSON.stringify(config.data)
    dataString = toUtf8(dataString)
    config.data = evil(dataString)

    removePending(config)
    let self = {}
    config.cancelToken = new CancelToken((c) => {
      pending.push({ url: config.url, func: c })
      self.cacel = c
    })
    cancelRequest(config, self.cacel)
  }
  if (config.params) {
    for (let p in config.params) {
      config.params[p] = toUtf8(config.params[p])
    }
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

/**
 * 响应拦截
 */
axios.interceptors.response.use((response) => {
  if (response.config.method === 'post') {
    removePending(response.config)
    done.push({ url: response.config.url, date: new Date() })
  }
  if (response.data && response.config.responseType !== 'arraybuffer') {
    let dataString = JSON.stringify(response.data)
    dataString = toUnicode(dataString)
    response.data = evil(dataString)
  }
  return response
}, (error) => {
  if (axios.isCancel(error)) {
    // 取消请求的情况下，终止Promise调用链
    return new Promise(() => {
    })
  }
  if (error.response) {
    switch (error.response.status) {
      case 401:
        warn('长时间未操作，请重新登录系统', toLogin)
        break
      case 404:
        text('请求的资源不存在')
        break
      case 502:
        text('服务在维护')
        break
    }
  } else {
    warn('网络异常，请检查')
  }

  return Promise.reject(error)
})

const resultHandler = (data, resolve, reject) => {
  if (data.status) {
    resolve(data.result)
  } else {
    warn(data.errorMessage)
    reject(data.errorMessage)
  }
}

/// ////////////////////////////////////////////////////////////////////

/**
 * 基础路径
 */
export const baseUrl = SERVICE_URL

/**
 * 以Form形式POST
 * @param {*} url
 * @param {*} model
 */
export const postForm = (url, model) => {
  return new Promise(function (resolve, reject) {
    axios({
      method: 'post',
      url: SERVICE_URL + url,
      headers: { 'Content-type': 'application/x-www-form-urlencoded;charset=utf-8' },
      data: qs.stringify(model)
    }).then(function (response) {
      resultHandler(response.data, resolve, reject)
    }).catch(function (err) {
      reject(err)
    })
  })
}

/**
 * 以json形式POST
 * @param url
 * @param model
 * @param config 请求头参数
 * @returns {Promise<any>}
 * @constructor
 */
export const post = (url, model, config) => {
  return new Promise((resolve, reject) => {
    axios(Object.assign({
      method: 'post',
      url: SERVICE_URL + url,
      data: model
    }, config)).then(function (response) {
      resultHandler(response.data, resolve, reject)
    }).catch(function (err) {
      reject(err)
    })
  })
}

/**
 * GET获取数据
 * @param url
 * @param model
 * @param config
 * @returns {Promise<any>}
 * @constructor
 */
export const get = (url, model, config) => {
  return new Promise((resolve, reject) => {
    axios.get(SERVICE_URL + url, Object.assign({
      params: model
    }, config)).then(function (response) {
      resultHandler(response.data, resolve, reject)
    }).catch(function (err) {
      reject(err)
    })
  })
}
