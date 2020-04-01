<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffBecomeFosterRecord :staffid="staffid"></StaffBecomeFosterRecord>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffBecomeFosterRecord from './StaffBecomeFosterRecord.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffBecomeFosterRecord':StaffBecomeFosterRecord,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
