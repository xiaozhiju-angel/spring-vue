<template>
  <div class="usercenter">
    <el-main class="center">
      <QueryFosterPet></QueryFosterPet>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import QueryFosterPet from './QueryFosterPet'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QueryFosterPet':QueryFosterPet

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
