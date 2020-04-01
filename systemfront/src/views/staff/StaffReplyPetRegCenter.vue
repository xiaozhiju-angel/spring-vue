<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffReplyPetReg :staffid="staffid"></StaffReplyPetReg>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffReplyPetReg from './StaffReplyPetReg.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffReplyPetReg':StaffReplyPetReg,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
