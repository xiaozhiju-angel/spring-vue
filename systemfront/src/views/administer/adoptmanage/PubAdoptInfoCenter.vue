<template>
  <div class="usercenter">
    <el-main class="center">
      <PubAdoptInfo></PubAdoptInfo>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import PubAdoptInfo from './PubAdoptInfo.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'PubAdoptInfo': PubAdoptInfo

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
