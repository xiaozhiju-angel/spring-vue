<template>
  <div class="usercenter">
    <el-main class="center">
      <UPetFoster :userid="userid"></UPetFoster>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import   UPetFoster from './UPetFosterApply.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UPetFoster':UPetFoster
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
