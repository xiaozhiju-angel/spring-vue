<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffAddPet :staffid="staffid"></StaffAddPet>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffAddPet from './StaffAddPet.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffAddPet':StaffAddPet,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
