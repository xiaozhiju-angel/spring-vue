<template>
  <div class="usercenter">
    <el-main class="center">
      <LookPickupPublish></LookPickupPublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  LookPickupPublish from './LookPickupPublish'
  export default {
    data(){
      return{

      }
    },
    components:{
      LookPickupPublish
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
