<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffSelf :staffid="staffid"></StaffSelf>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffSelf from './StaffSelf.vue'
  export default {
    data(){
      return{ 
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffSelf':StaffSelf,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
