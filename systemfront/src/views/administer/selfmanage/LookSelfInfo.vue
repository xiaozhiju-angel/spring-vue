<template>
  <div>
    <QueryAdmin :adminid="adminid"></QueryAdmin>
  </div>
</template>

<script>
  import  QueryAdmin from '../queryadminself/QueryAdmin'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QueryAdmin':QueryAdmin
    },
    props:['adminid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
