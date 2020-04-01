<template>
  <div class="usercenter">
    <el-main class="center">
      <ReplyUPetApply></ReplyUPetApply>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import ReplyUPetApply from  './ReplyUPetApply.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'ReplyUPetApply':ReplyUPetApply

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
