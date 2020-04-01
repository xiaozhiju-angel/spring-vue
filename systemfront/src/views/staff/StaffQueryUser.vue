<template>
  <div>
    <query :staffid="staffid"></query>
  </div>
</template>

<script>
  import  query from '../test/query'
  export default {
    data(){
      return{

      }
    },
    components:{
      'query':query
    },
    props:['staffid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
