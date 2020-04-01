<template>
  <div class="usercenter">
    <el-main class="center">
      <upet-reg :userid="userid"></upet-reg>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import UpetReg from './UpetReg.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'upet-reg':UpetReg
    },
    methods:{

    },
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
