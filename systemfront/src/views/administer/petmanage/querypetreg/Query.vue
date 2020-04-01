<template>
  <div>
    <el-container>
      <el-header class="keywordsearch">
        <div style="display:inline">
          <el-input
            class="search"
            placeholder="编号,性别,类型,价格 ……"
            clearable
            @change="keywordsChange"
            size="mini"
            @keyup.enter.native="searchUserInfo"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchUserInfo">搜索
          </el-button>
        </div>
      </el-header>
    </el-container>
    <QueryRegpet  class="main" :headerList="headerList" :bodyInfoList="bodyInfoList"></QueryRegpet>
  </div>
</template>
<script>
  import QueryRegpet from "./QueryRegpet";
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
      QueryRegpet
    },
    methods: {
      keywordsChange(val){
        if(val=='')
          this.initData();
        this.keywords=val;
        console.log(val);
      },
      searchUserInfo(){
        var _this = this;
        this.getRequest("/pet/querypetInfoBykeywords?info=" + this.keywords).then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },
      initData(){
        var _this = this;
        this.getRequest("/pet/getpetInfo").then(resp=> {
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
