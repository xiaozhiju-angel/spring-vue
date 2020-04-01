<template>
    <div>
      <QuerySelf :userid="userid"></QuerySelf>
    </div>
</template>

<script>
  import  QuerySelf from './queryuserself/QuerySelf'
    export default {
        data(){
          return{

          }
        },
      components:{
          'QuerySelf':QuerySelf
      },
      props:['userid'],
      methods:{

      }
    }
</script>

<style scoped>

</style>
