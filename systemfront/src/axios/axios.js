import axios from 'axios'
import {Message} from 'element-ui'
axios.interceptors.request.use(config => {
  return config;
}, err => {
  Message.error({message: '请求超时!'});
  // return Promise.resolve(err);
})
axios.interceptors.response.use(data => {
  if (data.status && data.status == 200 && data.data.status == 500) {
    Message.error({message: data.data.msg});
    return;
  }
  if (data.data.msg) {
    Message.success({message: data.data.msg});
  }
  return data;
}, err => {
  if (err.response.status == 504 || err.response.status == 404) {
    Message.error({message: '服务器被吃了⊙﹏⊙∥'});
  } else if (err.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  } else if (err.response.status == 401) {
    Message.error({message: err.response.data.msg});
  } else {
    if (err.response.data.msg) {
      Message.error({message: err.response.data.msg});
    }else{
      Message.error({message: '未知错误!'});
    }
  }
  // return Promise.resolve(err);
})
let base = "http://localhost:8088/";
export const postRequest = (url, params) => {
  return axios({
    // `method` 是创建请求时使用的方法
    method: 'post',
    // `url` 是用于请求的服务器 URL
    // `baseURL` 将自动加在 `url` 前面，除非 `url` 是一个绝对 URL。
    url: `${base}${url}`,
    // `data` 是作为请求主体被发送的数据
    data: params,
    // `transformRequest` 允许在向服务器发送前，修改请求数据
    // `transformResponse` 在传递给 then/catch 前，允许修改响应数据
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}
export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
  });
}
 /*export const uploadjsonRequest = (url, params) => {
   return axios({
     method: 'post',
     url: `${base}${url}`,
     data: params,
     headers: {
       'Content-Type': 'application/json'
     },
   });
 }*/

 export const uploadVideoRequest = (url, params) => {
   return axios({
     method: 'post',
     url: `${base}${url}`,
     data: params,
     headers: {
       'Content-Type': 'video/mp4'
     },
   });
 }

export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  });
}
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  });
}
export const validator = {
   /**
    * 手机号码校验
    */
   userTel(rule, value, callback) {
     if (value && (!(/^[1][34578]\d{9}$/).test(value) || !(/^[1-9]\d*$/).test(value) || value.length !== 11)) {
       callback(new Error('手机号码不符合规范'))
     } else {
       callback()
     }
   },
   /**
    * 电话号码校验
    */
   telephoneNumber(rule, value, callback) {
     if (value && (!(/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/).test(value))) {
       callback(new Error('电话号码不符合规范'))
     } else {
       callback()
     }
   },

   /**
    * 邮箱校验
    */
   emailP(rule, value, callback) {
     let temp = /^[\w.\-]+@(?:[a-z0-9]+(?:-[a-z0-9]+)*\.)+[a-z]{2,3}$/
     let tempOne = /^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/
     if (value && (!(temp).test(value))) {
       callback(new Error('邮箱格式不符合规范'))
     } else {
       callback()
     }
   },

   /**
    * 身份证号码校验
    */
   idCard(rule, value, callback) {
     if (value && (!(/\d{17}[\d|x]|\d{15}/).test(value) || (value.length !== 15 && value.length !== 18))) {
       callback(new Error('身份证号码不符合规范'))
     } else {
       callback()
     }
   },
   /**
    * 正整数校验
    */
   integerP(rule, value, callback) {
     if (value && !(/^[1-9]\d*$/).test(value)) {
       callback(new Error('只能填写正整数'))
     } else {
       callback()
     }
   },

   /**
    * 英文字符校验
    */
   enText(rule, value, callback) {
     // let a = '',
     //   arr = value.split(" ")
     // for (let i = 0; i < arr.length; i++) { //删除行内空格
     //   a += arr[i];
     // }
     if (value && !(/^[A-Za-z]+$/).test(value)) {
       callback(new Error('只能填写英文字符'))
     } else {
       callback()
     }
   },
   /**
    * 只能输入英文或者数字
    */
   enOrnunText(rule, value, callback) {
     if (value && !(/^[A-Za-z0-9]+$/).test(value)) {
       callback(new Error('只能填写英文或者数字'))
     } else {
       callback()
     }
   },
   /**
    * 20位数字国标编码校验，且为正整数
    */
   validateDeviceNo(rule, value, callback) {
     if (value && !(/^[1-9]\d*$/).test(value)) {
       callback(new Error('只能填写正整数'))
     } else {
       if (!/^[0-9]{20}$/.test(value)) {
         callback(new Error('请输入20位数字的编码！'));
       } else {
         callback();
       }
     }
   },


 }
