<template >
  <div>
    <component :is="comName"></component>
  </div>
</template>
<script>
  import Query from '../test/query.vue'
  export default {
    data() {
      return {
        comName:'query',

        }

    },
    components:{
      'query':Query
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>

</style>
