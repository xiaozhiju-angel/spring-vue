<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffAddUser :staffid="staffid"></StaffAddUser>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffAddUser from './StaffAddUser.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffAddUser':StaffAddUser,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
