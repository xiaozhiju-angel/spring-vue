<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffBecomeAdoptRecord :staffid="staffid"></StaffBecomeAdoptRecord>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffBecomeAdoptRecord from './StaffBecomeAdoptRecord.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffBecomeAdoptRecord':StaffBecomeAdoptRecord,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
