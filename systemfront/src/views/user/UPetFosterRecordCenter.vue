<template>
  <div class="usercenter">
    <el-main class="center">
      <UPetFosterRecord :userid="userid"></UPetFosterRecord>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import   UPetFosterRecord from './UPetFosterRecord.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UPetFosterRecord':UPetFosterRecord
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
