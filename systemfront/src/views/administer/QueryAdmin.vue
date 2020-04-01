<template >
  <div>
    <component :is="comName"></component>
  </div>
</template>
<script>
    import QueryAdmin from '../query/Query.vue'
  export default {
    data() {
      return {
        comName:'QueryAdmin',

      }

    },
    components:{
      'QueryAdmin':QueryAdmin
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>

</style>
