<template>
  <div class="usercenter">
    <el-main class="center">
      <QuerypetReg></QuerypetReg>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import QuerypetReg from  './QuerypetReg.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QuerypetReg':QuerypetReg

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
