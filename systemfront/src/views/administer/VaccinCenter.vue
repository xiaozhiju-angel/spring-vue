<template>
  <div class="usercenter">
    <el-main class="center">
      <Vaccin></Vaccin>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  Vaccin from './Vaccin.vue'
  export default {
    data(){
      return{
        comName:'Vaccin',
      }
    },
    methods:{

    },
    components:{
      'Vaccin':Vaccin,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
