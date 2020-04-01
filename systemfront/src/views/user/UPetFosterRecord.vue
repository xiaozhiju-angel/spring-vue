<template >
  <div>
    <component :is="comName" :userid="userid"></component>
  </div>
</template>
<script>
  import QueryFoster from './queryfosterecord/QueryFoster.vue'
  export default {
    data() {
      return {
        comName:'QueryFoster',

      }

    },
    props:['userid'],
    components:{
      'QueryFoster':QueryFoster
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>

</style>
