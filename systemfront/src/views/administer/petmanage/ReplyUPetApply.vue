<template >
  <div>
    <component :is="comName"></component>
  </div>
</template>
<script>
  import Upet from '../replypetapply/Upet.vue'
  export default {
    data() {
      return {
        comName:'Upet',

      }

    },
    components:{
      'Upet':Upet
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>

</style>
