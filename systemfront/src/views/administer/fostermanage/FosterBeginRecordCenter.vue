<template>
  <div class="usercenter">
    <el-main class="center">
      <LookFoster></LookFoster>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  LookFoster from './FosterBeginRecord'
  export default {
    data(){
      return{

      }
    },
    components:{
      LookFoster
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
