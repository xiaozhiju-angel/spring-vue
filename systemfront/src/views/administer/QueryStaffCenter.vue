<template>
  <div class="usercenter">
    <el-main class="center">
      <admin-querystaff></admin-querystaff>
    </el-main>
    <el-footer class="footer">
    </el-footer>
  </div>
</template>

<script>
  import  QueryStaff from './QueryStaff.vue'
  export default {
    data(){
      return{

      }
    },
    methods:{

    },
    components:{
      'admin-querystaff':QueryStaff,
    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
