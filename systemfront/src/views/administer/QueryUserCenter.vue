<template>
  <div class="usercenter">
    <el-main class="center">
      <admin-queryuser></admin-queryuser>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  QueryUser from './QueryUser.vue'
  export default {
    data(){
      return{
        comName:'admin-queryuser',
      }
    },
    methods:{

    },
    components:{
      'admin-queryuser':QueryUser,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
