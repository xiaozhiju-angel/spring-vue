<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffLookPubAbandon :staffid="staffid"></StaffLookPubAbandon>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffLookPubAbandon from './StaffLookPubAbandon.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffLookPubAbandon':StaffLookPubAbandon,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
