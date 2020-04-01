<template>
  <div class="usercenter">
    <el-main class="center">
      <LosePublish></LosePublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  LosePublish from './LosePublish'
  export default {
    data(){
      return{

      }
    },
    components:{
      LosePublish
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
