<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetQueryFosterpet :userid="userid"></UpetQueryFosterpet>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UpetQueryFosterpet from './UpetQueryFosterpet.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UpetQueryFosterpet':UpetQueryFosterpet

    },
    methods:{

    },
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
