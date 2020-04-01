<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffLookLose :staffid="staffid"></StaffLookLose>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffLookLose from './StaffLookLose.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffLookLose':StaffLookLose,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
