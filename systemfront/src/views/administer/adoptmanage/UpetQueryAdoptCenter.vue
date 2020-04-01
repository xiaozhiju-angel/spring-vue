<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetQueryAdopt></UpetQueryAdopt>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import UpetQueryAdopt from './UpetQueryAdopt.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'UpetQueryAdopt': UpetQueryAdopt
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
