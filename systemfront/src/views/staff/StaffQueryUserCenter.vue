<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffQueryUser :staffid="staffid"></StaffQueryUser>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffQueryUser from './StaffQueryUser.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffQueryUser':StaffQueryUser,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
