<template>
  <div>
    <QueryUserSelf  class="main" :userid="userid" :headerList="headerList" :bodyInfoList="bodyInfoList"></QueryUserSelf>
  </div>
</template>
<script>
  import QueryUserSelf from "./QuerUserSelf";
  export default {
    data() {
      return {
        headerList: ",个人ID,姓名,性别,身份证号,电话号码,电子邮箱,操作",
        bodyInfoList: [],
      }
    },
    components: {
      QueryUserSelf
    },
    props:['userid'],
    methods: {
      initData(){
        var _this = this;
        this.getRequest("/self/getUserSelfInfo?info=" + this.userid).then(resp=> {
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
