import { Toast } from 'cube-ui'
/** 集中使用toast */

let toast = (text, type, func) => {
  Toast.$create({
    // time: 1000,
    mask: true,
    type: 'txt',
    maskClosable: true,
    txt: text,
    $events: {
      timeout: () => {
        if (func) {
          func()
        }
      }
    }
  }).show()
}

export const text = (text, func) => {
  toast(text, 'txt', func)
}

export const success = (text, func) => {
  toast(text, 'correct', func)
}

export const error = (text, func) => {
  toast(text, 'error', func)
}

export const warn = (text, func) => {
  toast(text, 'warn', func)
}

export const loading = (text, func) => {
  toast(text, 'loading', func)
}
