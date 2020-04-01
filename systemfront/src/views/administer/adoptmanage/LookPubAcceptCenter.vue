<template>
  <div class="usercenter">
    <el-main class="center">
      <LookPubAccept></LookPubAccept>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>
<script>
  import LookPubAccept from './LookPubAccept.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'LookPubAccept': LookPubAccept

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
