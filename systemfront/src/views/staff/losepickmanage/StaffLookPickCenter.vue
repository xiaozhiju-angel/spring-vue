<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffLookPick :staffid="staffid"></StaffLookPick>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffLookPick from './StaffLookPick.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffLookPick':StaffLookPick,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
