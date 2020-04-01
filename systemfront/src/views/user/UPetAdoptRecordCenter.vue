<template>
  <div class="usercenter">
    <el-main class="center">
      <UPetAdoptRecord :userid="userid"></UPetAdoptRecord>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import UPetAdoptRecord from './UPetAdoptRecord.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UPetAdoptRecord': UPetAdoptRecord

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
