<template >
  <div id="register">
    <div id="top">
      <div class="top_left">
        <img src="../../../static/petimg/034.jpg" />
        <span class="des">注册登录更精彩</span>
      </div>
      <div class=" top_right">
        <span class="exit" @click="exithome()">退出</span>
      </div>
    </div>
    <div id="header">
      <p class="header_title">登记宠物</p>
    </div>
    <div >
      <!--用户模块-->
      <div>
        <component class="regcenter" :is="comName" ></component>
      </div>
      <!--结束-->
      <div class="end"></div>

    </div>
  </div>

</template>

<script>
  import PetRegister from './PetRegister.vue'
  export default {
    data() {
      return {
        comName:'reg-pet',
      };
    },
    components:{
      'reg-pet':PetRegister,
    },
    methods: {
      exithome(){
        this.$router.push({
          path:'/home'
        });
      }
    }
  }
</script>

<style scoped>
  @import '../../../static/css/pet/pet.css';
</style>

