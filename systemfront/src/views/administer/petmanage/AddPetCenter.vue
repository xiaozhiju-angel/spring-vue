<template>
  <div class="usercenter">
    <el-main class="center">
      <AddPet></AddPet>
    </el-main>
    <el-footer class="footer"></el-footer>
  </div>
</template>

<script>
  import AddPet from  './AddPet.vue'
  export default {
    data(){
      return{

      }
    },
    components:{
      'AddPet':AddPet

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
