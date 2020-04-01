<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetLookLoseInfo></UpetLookLoseInfo>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UpetLookLoseInfo from './UpetLookLoseInfo.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'UpetLookLoseInfo':UpetLookLoseInfo

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
