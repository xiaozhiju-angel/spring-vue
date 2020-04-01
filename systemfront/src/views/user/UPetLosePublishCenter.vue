<template>
  <div class="usercenter">
    <el-main class="center">
      <UPetLosePublish :userid="userid"></UPetLosePublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import   UPetLosePublish from './UPetLosePublish.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UPetLosePublish':UPetLosePublish
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
