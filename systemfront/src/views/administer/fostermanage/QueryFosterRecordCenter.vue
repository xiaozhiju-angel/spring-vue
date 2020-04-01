<template>
  <div class="usercenter">
    <el-main class="center">
      <QueryFosterRecord></QueryFosterRecord>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import QueryFosterRecord from './QueryFosterRecord'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QueryFosterRecord':QueryFosterRecord

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
