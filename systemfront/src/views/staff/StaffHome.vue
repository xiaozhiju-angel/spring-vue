<template>
  <div class="staffhome">
    <el-container>
      <el-header>
        <div  class="header">
          <div class="header_left">
            <img src="../../../static/petimg/034.jpg"/>
            <div class="right">
              <div style="margin-left: 5px;font-size: 12px;color:brown;padding-top: 8px">{{staffname}}</div>
              <div style="margin-left: 5px;font-size: 13px;color: #ff5d0e;">欢迎来到宠物管理中心</div>
            </div>
          </div>
          <span  @click="message()" style="position:absolute;left: 1144px;top:68px;font-size: 12px;width: 50px;color: #1e12ff;font-style: italic">系统公告</span>
          <div class="header_right">
            <img @click="message()" class="system_message" src="../../../static/petimg/公告.png"/>
            <div class="user">
              <div style="font-size: 14px;text-align: center">{{staffprop}}</div>
              <div style="font-size: 12px;color:brown;text-align: right">ID:<span>{{staffid}}</span></div>
            </div>
            <el-dropdown @command="handleCommand">
             <!-- <img  class="usercenter" src="../../../static/homeimg/健康百科-10.png" />-->
              <img  class="usercenter" :src="host+'/'+this.staffimageUrl" alt=""/>
              <el-dropdown-menu slot="dropdown" style="width: 70px;text-align: right;font-size: 13px">
                <el-dropdown-item>设置</el-dropdown-item>
                <el-dropdown-item>帮助</el-dropdown-item>
                <el-dropdown-item command="logout" divided>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </el-header>
     <staff-child :staffid="staffid"></staff-child>
    </el-container>

    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 18px;margin-bottom: 30px;text-align: center;font-weight: bold">系统公告</div>
      <div class="middle">
        <el-container>
          <div class="top">
            <ul class="topul">
              <li class="title"><el-button :class="{ 'red':isred1}"  size="small" @click="acceptinfo()">宠物领养信息</el-button></li>
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

  import  StaffHomeChild from './StaffHomeChild.vue'
  import QueryAccept from '../administer/systemnews/queryaccept/Query'
  import QueryAbandon from '../administer/systemnews/queryabandon/Query'
  import QueryLose from '../administer/systemnews/querylose/Query'
  import QueryPick from '../administer/systemnews/querypick/Query'
  export default {
    data() {
      return {
        staffprop:'',
        staffid:'',
        staffname:'',
        staffimageUrl:'',
        host:'http://localhost:8088/we',
        isred1:true,
        isred2:false,
        isred3:false,
        isred4:false,
        dialogFormVisible:false,
        comName:'QueryAccept'
      }
    },
    components:{
      'staff-child':StaffHomeChild,
      'QueryAccept':QueryAccept,
      'QueryAbandon':QueryAbandon,
      'QueryLose':QueryLose,
      'QueryPick':QueryPick
    },
    methods: {
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
      back(){
        this.$router.push({
          path:'/home'
        });
      },
      acceptinfo(){
        this.isred1=true;
        this.isred2=false;
        this.isred3=false;
        this.isred4=false;
        this.comName='QueryAccept';
      },
      abandoninfo(){
        this.isred1=false;
        this.isred2=true;
        this.isred3=false;
        this.isred4=false;
        this.comName='QueryAbandon'
      },
      loseinfo(){
        this.isred1=false;
        this.isred2=false;
        this.isred3=true;
        this.isred4=false;
        this.comName='QueryLose'
      },
      pickinfo(){
        this.isred1=false;
        this.isred2=false;
        this.isred3=false;
        this.isred4=true;
        this.comName='QueryPick'
      },
      message(){
        this.dialogFormVisible=true;
      },
      getParams () {
        var routerParams=this.$route.query.userprop;
        var routerParamstaffid=this.$route.query.staffid;
        var name=this.$route.query.staffname;
        var staffimg=this.$route.query.staffimg;
        // 将数据放在当前组件的数据内
        this.staffprop = routerParams;
        this.staffid = routerParamstaffid;
        this.staffname=name;
        this.staffimageUrl=staffimg;
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
  @import "../../../static/css/staff/staffhome.css";
  @import "../../../static/css/dialog/systemnews.css";
  .red{
    background: brown;
    color: white;
  }
</style>

