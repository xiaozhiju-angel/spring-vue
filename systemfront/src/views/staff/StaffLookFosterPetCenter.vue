<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffLookFosterPet :staffid="staffid"></StaffLookFosterPet>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffLookFosterPet from './StaffLookFosterPet.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffLookFosterPet':StaffLookFosterPet,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
