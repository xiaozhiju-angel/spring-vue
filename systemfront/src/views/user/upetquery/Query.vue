<template>
  <div>
    <Querypet :userid="userid" class="main" :headerList="headerList" :bodyInfoList="bodyInfoList"></Querypet>
  </div>
</template>
<script>
  import Querypet from "./Querypet";
  export default {
    data() {
      return {
        keywords:'',
        // 注意：逗号是英文的逗号
        /*headerList: "ID,姓名,性别,照片,身份证号,电话,邮箱,门牌号,是否租户,用户名,密码,权限,入住时间", */
        headerList: ",宠物ID,宠物姓名,宠物性别,宠物类型,上次打疫苗时间,下次应打疫苗时间,登记时间,操作",
        bodyInfoList: [],
      }
    },
    components: {
      Querypet
    },
    props:['userid'],
    methods: {
      initData(){
        var _this = this;
        this.getRequest("/user/getselfpetInfoById?info="+this.userid).then(resp=> {
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
