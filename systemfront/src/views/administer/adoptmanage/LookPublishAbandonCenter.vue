<template>
  <div class="usercenter">
    <el-main class="center">
      <LookPublishAdopt></LookPublishAdopt>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>
<script>
  import LookPublishAdopt from './LookPublishAbandon.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'LookPublishAdopt': LookPublishAdopt

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
