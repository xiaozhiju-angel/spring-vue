<template>
  <div class="usercenter">
    <el-main class="center">
      <UPetQuery :userid="userid"></UPetQuery>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import UPetQuery from './UPetQuery.vue'
  export default {
    data(){
      return{

      }
    },
    props:['userid'],
    components:{
      'UPetQuery':UPetQuery
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
