<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetQueryAbandon :userid="userid"></UpetQueryAbandon>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UpetQueryAbandon from './UpetQueryAbandon.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UpetQueryAbandon':UpetQueryAbandon

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
