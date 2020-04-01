<template>
  <div class="usercenter">
    <el-main class="center">
      <PickupPublish></PickupPublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  PickupPublish from './PickupPublish'
  export default {
    data(){
      return{

      }
    },
    components:{
      PickupPublish
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
