<template>
  <div class="usercenter">
    <el-main class="center">
      <StaffLookPubAccept :staffid="staffid"></StaffLookPubAccept>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  StaffLookPubAccept from './StaffLookPubAccept.vue'
  export default {
    data(){
      return{
      }
    },
    methods:{

    },
    props:['staffid'],
    components:{
      'StaffLookPubAccept':StaffLookPubAccept,
    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
