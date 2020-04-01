<template>
  <div class="usercenter">
    <el-main class="center">
      <Tip :userid="userid"></Tip>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import  Tip from './Tip.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'Tip':Tip

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
