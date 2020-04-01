<template>
  <div>
    <Foster :staffid="staffid"></Foster>
  </div>
</template>

<script>
  import  Foster from '../administer/replyfoster/Foster'
  export default {
    data(){
      return{

      }
    },
    components:{
      'Foster':Foster
    },
    props:['staffid'],
    methods:{

    }
  }
</script>

<style scoped>

</style>
