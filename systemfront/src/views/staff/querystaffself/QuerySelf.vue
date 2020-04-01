<template>
  <div>
    <QueryStaffSelf  class="main" :staffid="staffid" :headerList="headerList" :bodyInfoList="bodyInfoList"></QueryStaffSelf>
  </div>
</template>
<script>
  import QueryStaffSelf from "./QueryStaffSelf";
  export default {
    data() {
      return {
        keywords:'',
        // 注意：逗号是英文的逗号
        /*headerList: "ID,姓名,性别,照片,身份证号,电话,邮箱,门牌号,是否租户,用户名,密码,权限,入住时间", */
        headerList: ",个人ID,姓名,性别,身份证号,电话号码,电子邮箱,操作",
        bodyInfoList: [],
      }
    },
    components: {
      QueryStaffSelf
    },
    props:['staffid'],
    methods: {
      initData(){
        var _this = this;
        this.getRequest("/self/getStaffSelfInfo?info=" + this.staffid).then(resp=> {
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
