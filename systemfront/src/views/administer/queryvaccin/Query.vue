<template>
  <div>
    <QueryVaccin  class="main" :headerList="headerList" :bodyInfoList="bodyInfoList"></QueryVaccin>
  </div>
</template>
<script>
  import QueryVaccin from "./QueryVaccin";
  export default {
    data() {
      return {
        /*headerList: "ID,姓名,性别,照片,身份证号,电话,邮箱,门牌号,是否租户,用户名,密码,权限,入住时间", */
        headerList: ",疫苗ID,宠物姓名,宠物类型,用户姓名,用户电话,上次打疫苗时间,下次打疫苗时间,健康状态,操作",
        bodyInfoList: [],
      }
    },
    components: {
      QueryVaccin
    },
    methods: {
      initData(){
        var _this = this;
        this.getRequest("/foster/vaccinInfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },
      // 页面加载就执行
      created() {},
      // 页面加载完成后执行

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
