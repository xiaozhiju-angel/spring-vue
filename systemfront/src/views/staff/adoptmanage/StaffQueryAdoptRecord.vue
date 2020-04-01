<template>
  <div>
    <Query :staffid="staffid"></Query>
  </div>
</template>

<script>
  import  Query from '../../administer/adoptmanage/queryadopt/Query'
  export default {
    data(){
      return{

      }
    },
    components:{
      'Query':Query
    },
    props:['staffid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
