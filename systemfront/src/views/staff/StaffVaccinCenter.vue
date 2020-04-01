<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffVaccin :staffid="staffid"></StaffVaccin>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffVaccin from './StaffVaccin.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffVaccin':StaffVaccin,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
