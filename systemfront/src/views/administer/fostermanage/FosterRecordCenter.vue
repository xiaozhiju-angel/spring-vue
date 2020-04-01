<template>
  <div class="usercenter">
    <el-main class="center">
      <FosterRecord></FosterRecord>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import FosterRecord from './FosterRecord'
  export default {
    data(){
      return{


      }
    },
    components:{
      'FosterRecord':FosterRecord

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
