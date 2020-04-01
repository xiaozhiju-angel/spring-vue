import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state={
  count:1,
  imgurl:'',
  adminid:'',
  staffid:'',
  userid:''
}

const mutations={
  increment () {
    // 变更状态
    ++state.count
  }
}

const store = new Vuex.Store({
  state,
  mutations
})
export  default store;

