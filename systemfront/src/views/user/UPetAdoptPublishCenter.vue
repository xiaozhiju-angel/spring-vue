<template>
  <div class="usercenter">
    <el-main class="center">
      <UPetAdoptPublish :userid="userid"></UPetAdoptPublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UPetAdoptPublish from './UPetAdoptPublish.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UPetAdoptPublish':UPetAdoptPublish

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
