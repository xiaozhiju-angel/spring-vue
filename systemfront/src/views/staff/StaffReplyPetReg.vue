<template>
  <div>
    <Upet :staffid="staffid"></Upet>
  </div>
</template>

<script>
  import  Upet from '../administer/replypetapply/Upet'
  export default {
    data(){
      return{

      }
    },
    components:{
      'Upet':Upet
    },
    props:['staffid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
