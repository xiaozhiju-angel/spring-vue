<template>
  <div class="usercenter">
    <el-main class="center">
      <AddAdmin></AddAdmin>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import AddAdmin from './AddAdmin'
  export default {
    data(){
      return{

      }
    },
    components:{
      'AddAdmin':AddAdmin

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
