<template >
  <div>
    <component :is="comName" :userid="userid"></component>
  </div>
</template>
<script>
  import Query from './queryadoptrecord/Query.vue'
  export default {
    data() {
      return {
        comName:'Query',

      }

    },
    props:['userid'],
    components:{
      'Query':Query
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>

</style>
