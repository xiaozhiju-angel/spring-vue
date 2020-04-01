<template>
  <div class="usercenter">
    <el-main class="center">
      <LookLosePublish></LookLosePublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  LookLosePublish from './LookLosePublish'
  export default {
    data(){
      return{

      }
    },
    components:{
      LookLosePublish
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
