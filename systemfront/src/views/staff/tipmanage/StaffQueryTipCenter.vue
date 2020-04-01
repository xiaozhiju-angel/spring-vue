<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffQueryTip ></StaffQueryTip>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffQueryTip from './StaffQueryTip.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    components:{
      'StaffQueryTip':StaffQueryTip,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
