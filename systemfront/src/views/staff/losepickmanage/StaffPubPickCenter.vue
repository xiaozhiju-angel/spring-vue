<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffPubPick :staffid="staffid"></StaffPubPick>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffPubPick from './StaffPubPick.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffPubPick':StaffPubPick,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
