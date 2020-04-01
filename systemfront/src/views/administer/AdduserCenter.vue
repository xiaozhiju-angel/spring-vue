<template>
  <div class="usercenter">
    <el-main class="center">
      <admin-adduser></admin-adduser>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  AddUser from './AddUser.vue'
  export default {
    data(){
      return{

      }
    },
    methods:{

    },
    components:{
      'admin-adduser':AddUser,
    }
  }
</script>

<style scoped>
  .usercenter{
    width: 1000px;
  }
  .center{
    height: 700px;
  }
  .footer{
    background: #0bbbff;
  }
</style>
