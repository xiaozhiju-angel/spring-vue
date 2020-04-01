<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffLookFosterRecord :staffid="staffid"></StaffLookFosterRecord>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffLookFosterRecord from './StaffLookFosterRecord.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffLookFosterRecord':StaffLookFosterRecord,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
