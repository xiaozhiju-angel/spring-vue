<template>
  <div class="usercenter">
    <el-main class="center">
      <UpetPickPublish :userid="userid"></UpetPickPublish>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import   UpetPickPublish from './UpetPickPublish.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UpetPickPublish':UpetPickPublish
    },
    methods:{

    },
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../static/css/center.css";
</style>
