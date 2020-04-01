<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffReplyPetFoster :staffid="staffid"></StaffReplyPetFoster>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffReplyPetFoster from './StaffReplyPetFoster.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffReplyPetFoster':StaffReplyPetFoster,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
