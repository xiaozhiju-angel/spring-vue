<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetQueryAccept :userid="userid"></UpetQueryAccept>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UpetQueryAccept from './UpetQueryAccept.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UpetQueryAccept':UpetQueryAccept

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
