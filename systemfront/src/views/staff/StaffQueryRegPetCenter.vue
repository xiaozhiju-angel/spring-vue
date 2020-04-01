<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffQueryRegPet :staffid="staffid"></StaffQueryRegPet>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffQueryRegPet from './StaffQueryRegPet.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffQueryRegPet':StaffQueryRegPet,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
