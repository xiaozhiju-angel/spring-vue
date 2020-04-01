<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetQueryVaccin :userid="userid"></UpetQueryVaccin>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  UpetQueryVaccin from './UpetQueryVaccin.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UpetQueryVaccin':UpetQueryVaccin

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
