<template>
  <div>
    <div style="padding: 0px;text-align: left;color: #409EFF;font-size:16px;font-style: italic;font-weight: lighter">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp亲爱的小伙伴们,
      欢迎来到宠物管理乐园,在这里你们看到的是他人发布的宠物拾到信息,展示了他们拾到的宠物信息和自己的联系方式,有丢失宠物的小伙伴们请快快联系他/她吧!</div>
    <el-container>
      <el-header class="keywordsearch">
        <div style="display:inline">
          <el-input
            class="search"
            placeholder="编号、电话、邮箱……"
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
    <QueryPick  class="main" :headerList="headerList" :bodyInfoList="bodyInfoList"></QueryPick>
  </div>
</template>

<script>
  import QueryPick from "./QueryPick";
  export default {
    name: "myCare",
    data() {
      return {
        keywords:'',
        // 注意：逗号是英文的逗号
        /*headerList: "ID,姓名,性别,照片,身份证号,电话,邮箱,门牌号,是否租户,用户名,密码,权限,入住时间", */
        headerList: ",用户ID,宠物类型,宠物性别,拾到者姓名,拾到者电话,宠物状态,发布时间,操作",
        bodyInfoList: [],
      }
    },
    components: {
      QueryPick
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
        this.getRequest("/pick/getpickBykeywords?info=" + this.keywords).then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },
      initData(){
        var _this = this;
        this.getRequest("/pick/getpickinfo").then(resp=> {
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
    width: 1200px;
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
