<template >

    <div>
      <component :is="comName"></component>
    </div>

</template>
<script>
  import QueryStaff from '../query/QueryStaff.vue'
  export default {
     data() {
      return {
        comName: 'querystaff',
      }
    },
    components:{
      'querystaff':QueryStaff
    },
    mounted: function () {

    },
    methods: {}
  }
</script>
<style>
  @import "../../../static/css/admin/querystaff.css";
  @import "../../../static/css/dialog/userinfodialog.css";
</style>
