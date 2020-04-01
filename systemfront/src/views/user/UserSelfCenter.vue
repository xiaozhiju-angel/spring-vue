<template>
  <div class="usercenter">
    <el-main class="center">
      <UserSelf :userid="userid"></UserSelf>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import UserSelf from './UserSelf.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'UserSelf':UserSelf
    },
    methods:{

    },
    props:['userid'],
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
