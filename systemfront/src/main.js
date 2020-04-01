// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import md5 from 'js-md5'
import element from './elementui/elementui'
import {getRequest} from './axios/axios'
import {postRequest} from './axios/axios'
import {deleteRequest} from './axios/axios'
import {putRequest} from './axios/axios'
import {uploadFileRequest} from './axios/axios'
import {uploadVideoRequest} from './axios/axios'
/*import {validator} from  './axios/axios'*/
import vuex from 'vuex'
import store from './store/store'
import validator from  './validator/validator.js'

import crypto from 'crypto'
/*import { formatDate } from './formatdate/format.js'*/

import  Moment from 'moment'

Vue.config.productionTip = false

Vue.prototype.validator=validator,
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.uploadFileRequest = uploadFileRequest;
Vue.prototype.uploadVideoRequest = uploadVideoRequest;
Vue.prototype.moment=Moment
/*Vue.prototype.formatDate=formatDate*/
Vue.prototype.md5=md5
new Vue({
  el: '#app',
  router,
  element,
  Moment,
  vuex,
  store,
  validator,
  md5,
  crypto,
  /*Vueicon,*/
  components: { App },
  template: '<App/>'
})
