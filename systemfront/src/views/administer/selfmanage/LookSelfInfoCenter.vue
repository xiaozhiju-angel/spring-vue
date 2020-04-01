<template>
  <div class="usercenter">
    <el-main class="center">
      <LookSelfInfo :adminid="adminid"></LookSelfInfo>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import LookSelfInfo from './LookSelfInfo'
  export default {
    data(){
      return{

      }
    },
    components:{
      LookSelfInfo
    },
    methods:{

    },
    props:['adminid'],
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
