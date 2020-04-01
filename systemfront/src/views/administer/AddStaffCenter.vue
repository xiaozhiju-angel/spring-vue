<template>
  <div class="usercenter">
    <el-main class="center">
      <admin-addstaff></admin-addstaff>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  AddStaff from './AddStaff.vue'
  export default {
    data(){
      return{

      }
    },
    methods:{

    },
    components:{
      'admin-addstaff':AddStaff,
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
