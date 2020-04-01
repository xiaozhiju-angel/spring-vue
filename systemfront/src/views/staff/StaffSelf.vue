<template>
  <div>
    <QuerySelf :staffid="staffid"></QuerySelf>
  </div>
</template>

<script>
  import  QuerySelf from './querystaffself/QuerySelf'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QuerySelf':QuerySelf
    },
    props:['staffid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
