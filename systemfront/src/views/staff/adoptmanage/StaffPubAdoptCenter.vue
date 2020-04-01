<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffPubAdopt :staffid="staffid"></StaffPubAdopt>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffPubAdopt from './StaffPubAdopt.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffPubAdopt':StaffPubAdopt,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
