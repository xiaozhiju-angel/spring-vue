<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffQueryAdoptRecord :staffid="staffid"></StaffQueryAdoptRecord>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffQueryAdoptRecord from './StaffQueryAdoptRecord.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffQueryAdoptRecord':StaffQueryAdoptRecord,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
