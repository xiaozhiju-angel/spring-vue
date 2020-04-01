<template >
  <div>
    <component :is="comName"></component>
  </div>
</template>
<script>
  import Query from './querypetreg/Query.vue'
  export default {
    data() {
      return {
        comName:'Query',

      }

    },
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
