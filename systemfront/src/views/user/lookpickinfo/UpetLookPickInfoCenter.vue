<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetLookPickInfo></UpetLookPickInfo>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UpetLookPickInfo from './UpetLookPickInfo.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'UpetLookPickInfo':UpetLookPickInfo

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
