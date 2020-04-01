<template>
  <div class="userhome">
    <el-container>
      <el-header>
        <div  class="header">
          <div class="header_left">
            <img src="../../../static/petimg/034.jpg"/>
            <div class="right">
              <div style="margin-left: 5px;font-size: 12px;color:brown;padding-top: 8px">{{username}}</div>
              <div style="margin-left: 5px;font-size: 13px;color: #ff5d0e;">欢迎来到宠物管理中心</div>
            </div>
          </div>
         <span @click="message()" style="position:absolute;left: 1184px;top:68px;font-size: 12px;width: 50px;color: #1e12ff;font-style: italic">系统公告</span>
          <div class="header_right">
            <img @click="message()" class="system_message" src="../../../static/petimg/公告.png"/>
            <div class="user">
              <div style="font-size: 14px;text-align: right">{{userprop}}</div>
              <div style="font-size: 12px;color:brown;text-align: right">ID:<span>{{userid}}</span></div>
            </div>
            <el-dropdown @command="handleCommand">
            <!-- <img  class="usercenter" src="../../../static/homeimg/健康百科-10.png" />-->
              <img  class="usercenter" :src="host+'/'+this.userimageUrl" alt=""/>
              <el-dropdown-menu slot="dropdown" style="width: 70px;text-align: right;font-size: 13px">
                <el-dropdown-item>设置</el-dropdown-item>
                <el-dropdown-item>帮助</el-dropdown-item>
                <el-dropdown-item command="logout" divided>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <!--@click.prevent="comName='user-child'"-->
      <user-child :userid="userid"></user-child>
    </el-container>
    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 18px;margin-bottom: 30px;text-align: center;font-weight: bold">系统公告</div>
      <div class="middle">
       <el-container>
         <div class="top">
           <ul class="topul">
             <li class="title"><el-button :class="{ 'red':isred1}" size="small" @click="acceptinfo()">宠物领养信息</el-button></li>
             <li class="title"><el-button :class="{ 'red':isred2}" size="small" @click="abandoninfo()">宠物弃养信息</el-button></li>
             <li class="title"><el-button :class="{ 'red':isred3}" size="small" @click="loseinfo()">宠物丢失信息</el-button></li>
             <li class="title"><el-button :class="{ 'red':isred4}" size="small" @click="pickinfo()">宠物拾到信息</el-button></li>
           </ul>
         </div>
         <el-main>
           <component :is="comName"></component>
         </el-main>
         <el-footer style="background: #409EFF;height: 40px"></el-footer>
       </el-container>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false" size="mini">确 定</el-button>
        <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  /*import '../assets/js/iconfont.js'*/
  import QueryAccept from '../systemnews/queryacceptinfo/Query'
  import QueryAbandon from '../systemnews/queryabandoninfo/Query'
  import QueryLose from '../systemnews/queryloseinfo/Query'
  import QueryPick from '../systemnews/querypickinfo/Query'
  import  UserChild from './UserHomeChild.vue'
  export default {
    data() {
      return {
        comName:'QueryAccept',
        userprop:'',
        userid:'',
        username:'',
        userimageUrl:'',
        host:'http://localhost:8088/we',
        isred1:true,
        isred2:false,
        isred3:false,
        isred4:false,
        dialogFormVisible:true,
       /* comName:'user-child',*/

      }
    },
    components:{
      'user-child':UserChild,
      'QueryAccept':QueryAccept,
      'QueryAbandon':QueryAbandon,
      'QueryLose':QueryLose,
      'QueryPick':QueryPick
    },
    methods: {
      acceptinfo(){
        this.comName='QueryAccept';
        this.isred1=true;
        this.isred2=false;
        this.isred3=false;
        this.isred4=false;
      },
      abandoninfo(){
        this.comName='QueryAbandon';
        this.isred1=false;
        this.isred2=true;
        this.isred3=false;
        this.isred4=false;
      },
      loseinfo(){
        this.comName='QueryLose';
        this.isred1=false;
        this.isred2=false;
        this.isred3=true;
        this.isred4=false;
      },
      pickinfo(){
        this.comName='QueryPick';
        this.isred1=false;
        this.isred2=false;
        this.isred3=false;
        this.isred4=true;
      },
      message(){
        this.dialogFormVisible=true;
      },
      handleCommand(cmd){
        var _this = this;
        console.log(cmd);
        if (cmd == 'logout') {
          this.$confirm('注销登录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.$router.replace({path: '/'});
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '取消'
            });
          });
        }
      },
      getParams () {
        var routerParams=this.$route.query.userprop;
        var routerParamsuserid=this.$route.query.userid;
        var name=this.$route.query.username;
        var userimg=this.$route.query.userimg;
        // 将数据放在当前组件的数据内
        this.userprop = routerParams;
        this.userid = routerParamsuserid;
        this.username=name;
        this.userimageUrl=userimg;
      }
    },
    watch: {
      // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
      '$route': 'getParams'
    },
    //pcontent?aid=123用query接收
    mounted(){
      this.getParams();
      /*console.log(this.$route.query);*/
    }

  }
</script>

<style scoped>
  @import "../../../static/css/user/userhome.css";
  @import "../../../static/css/dialog/systemnews.css";
  .red{
    background: brown;
    color: white;
  }
</style>

