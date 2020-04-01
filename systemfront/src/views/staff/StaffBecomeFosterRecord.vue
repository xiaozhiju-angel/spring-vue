<template>
  <div>
    <QueryFoster :staffid="staffid"></QueryFoster>
  </div>
</template>

<script>
  import  QueryFoster from '../administer/fostermanage/fosterecordbegin/QueryFoster'
  export default {
    data(){
      return{

      }
    },
    components:{
      'QueryFoster':QueryFoster
    },
    props:['staffid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
