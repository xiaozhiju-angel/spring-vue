<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffPubLose :staffid="staffid"></StaffPubLose>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffPubLose from './StaffPubLose.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffPubLose':StaffPubLose,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
