<template>
  <div>
    <QueryAdminSelf  class="main" :adminid="adminid" :headerList="headerList" :bodyInfoList="bodyInfoList"></QueryAdminSelf>
  </div>
</template>
<script>
  import QueryAdminSelf from "./QueryAdminSelf";
  export default {
    data() {
      return {
        headerList: ",个人ID,姓名,性别,身份证号,电话号码,电子邮箱,注册时间,操作",
        bodyInfoList: [],
      }
    },
    components: {
      QueryAdminSelf
    },
    props:['adminid'],
    methods: {
      initData(){
        var _this = this;
        this.getRequest("/self/getAdminSelfInfo?info=" + this.adminid).then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      } ,

    },
    mounted() {
      this.initData();
    }
  }
</script>
<style scoped>
  .main{
    width: 1260px;
    text-align: center;
    background: #f2fffa;
    margin-top: 10px;
    font-size: 14px;
  }
  .keywordsearch{
    padding: 0px;
    display:flex;
    justify-content:space-between;
    align-items: center;
  }
  .search{
    width: 300px;
    margin: 0px;
    padding: 0px;
  }
</style>
