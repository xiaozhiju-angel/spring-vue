<template>
  <div class="usercenter">
    <el-main class="center">
      <QueryAdmin></QueryAdmin>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import QueryAdmin from './QueryAdmin'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QueryAdmin':QueryAdmin

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
