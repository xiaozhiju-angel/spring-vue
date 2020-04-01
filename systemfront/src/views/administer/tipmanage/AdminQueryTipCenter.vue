<template>
  <div class="usercenter">
    <el-main class="center">
      <AdminQueryTip ></AdminQueryTip>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  AdminQueryTip from './AdminQueryTip.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    components:{
      'AdminQueryTip':AdminQueryTip,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
