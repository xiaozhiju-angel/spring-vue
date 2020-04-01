<template >
  <div>
    <component :is="comName"></component>
  </div>
</template>
<script>
  import Foster from '../replyfoster/Foster.vue'
  export default {
    data() {
      return {
        comName:'Foster',

      }

    },
    components:{
      'Foster':Foster
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>

</style>
