<template>
  <div class="usercenter">
    <el-main class="center">
      <ReplyUPetFoster></ReplyUPetFoster>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import ReplyUPetFoster from './ReplyUPetFoster'
  export default {
    data(){
      return{

      }
    },
    components:{
      'ReplyUPetFoster':ReplyUPetFoster

    },
    methods:{

    },
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../../static/css/center.css";
</style>
