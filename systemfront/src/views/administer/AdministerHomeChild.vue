<template>
  <div class="main">
    <el-container>
      <el-aside class="aside" width="230px">
        <el-menu :default-openeds="['1','2','3','4','5', '6','7','8','9','10']" >
          <el-submenu index="1">
            <template slot="title" ><i class="el-icon-message"></i><span class="titmessage" @click="showusermanage()" >业主管理</span></template>
            <el-menu-item class="langmessage" @click="adduser()" >添加业主信息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryuser()" >查询业主信息</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title" ><i class="el-icon-message"></i><span class="titmessage" @click="showstaffmanage()" >员工管理</span></template>
            <el-menu-item class="langmessage" @click="addstaff()" >添加员工信息</el-menu-item>
            <el-menu-item class="langmessage" @click="querystaff()" >查询员工信息</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title" ><i class="el-icon-message"></i><span class="titmessage" >后台管理</span></template>
            <el-menu-item class="langmessage" @click="addadmin()" >添加管理员信息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryadmin()" >查询管理员信息</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title" ><i class="el-icon-message"></i><span class="titmessage"  >宠物管理</span></template>
            <el-menu-item class="langmessage" @click="addpet()">添加宠物信息</el-menu-item>
            <el-menu-item class="langmessage" @click="replyupetReg()"  >批复宠物登记</el-menu-item>
            <el-menu-item class="langmessage" @click="querypetinfo()" >查询宠物信息</el-menu-item>
          </el-submenu>

          <el-submenu index="5">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage" >寄养管理</span></template>
            <el-menu-item class="langmessage" @click="replyupetFoster()">批复宠物寄养</el-menu-item>
            <el-menu-item class="langmessage" @click="fosterecord()">生成寄养记录</el-menu-item>
            <el-menu-item class="langmessage" @click="lookfoster()">查看寄养宠物</el-menu-item>
            <el-menu-item class="langmessage" @click="lookrecord()">查看寄养记录</el-menu-item>
          </el-submenu>
          <el-submenu index="6">
            <template slot="title" ><i class="el-icon-message" ></i><span class="titmessage">领养管理</span></template>
            <el-menu-item class="langmessage" @click="pubadoptinfo()">发布领养消息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryaccept()">查看领养消息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryabandon()">查看弃养消息</el-menu-item>
            <el-menu-item class="langmessage" @click="adoptrecord()">生成领养记录</el-menu-item>
            <el-menu-item class="langmessage" @click="queryadoptrecord()">查询领养记录</el-menu-item>
          </el-submenu>
          <el-submenu index="7">
            <template slot="title"><i class="el-icon-message"></i> <span class="titmessage">疫苗管理</span></template>
            <el-menu-item class="shortmessage"  @click="invalidvaccin()">超期信息</el-menu-item>
          </el-submenu>
          <el-submenu index="8">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage">寻宠管理</span></template>
            <el-menu-item class="langmessage" @click="loseinfopublish()">发布丢失信息</el-menu-item>
            <el-menu-item class="langmessage" @click="pickuppublish()">发布拾到信息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryloseinfo()">查看丢失消息</el-menu-item>
            <el-menu-item class="langmessage" @click="querypickup()">查看拾到消息</el-menu-item>
          </el-submenu>
          <el-submenu index="9">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage">个人管理</span></template>
            <el-menu-item class="shortmessage" @click="selfinfo()">个人信息</el-menu-item>
          </el-submenu>
          <el-submenu index="10">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage">投诉管理</span></template>
            <el-menu-item class="shortmessage"  @click="looktip()">投诉信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <component :is="comName" :adminid="adminid"></component>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import  AdduserCenter from './AdduserCenter.vue'
  import  QueryCenter from './QueryUserCenter.vue'
  import  AddstaffCenter from  './AddStaffCenter.vue'
  import  QueryStaffCenter from  './QueryStaffCenter.vue'
  import  UPetAdoptRecordCenter from  './adoptmanage/UPetAdoptRecordCenter.vue'
  import  PubAdoptInfoCenter from  './adoptmanage/PubAdoptInfoCenter.vue'
  import  LookPublishAdoptCenter from './adoptmanage/LookPublishAbandonCenter.vue'
  import  UpetQueryAdoptCenter from  './adoptmanage/UpetQueryAdoptCenter.vue'
  import  LookPubAcceptCenter from  './adoptmanage/LookPubAcceptCenter.vue'
  import  ReplyUPetApplyCenter from  './petmanage/ReplyUPetApplyCenter.vue'
  import LookSelfInfoCenter from  './selfmanage/LookSelfInfoCenter.vue'
  import QuerypetRegCenter from './petmanage/QuerypetRegCenter'
  import AddPetCenter from './petmanage/AddPetCenter'
  import  VaccinCenter from './VaccinCenter'
  import  AdminQueryTipCenter from './tipmanage/AdminQueryTipCenter'
  /*找寻宠物*/
  import PickupPublishCenter from './searchmanage/PickupPublishCenter'
  import LosePublishCenter from './searchmanage/LosePublishCenter'
  import LookPickupPublishCenter from './searchmanage/LookPickupPublishCenter'
  import LookLosePublishCenter from './searchmanage/LookLosePublishCenter'
  /*查询寄养信息*/
  import LookFosterCenter from './fostermanage/FosterBeginRecordCenter.vue'
  import ReplyUPetFosterCenter from  './fostermanage/ReplyUPetFosterCenter.vue'
  import FosterRecordCenter from  './fostermanage/FosterRecordCenter.vue'
  import QueryFosterRecordCenter from  './fostermanage/QueryFosterRecordCenter.vue'
  import QueryFosterPetCenter from  './fostermanage/QueryFosterPetCenter.vue'

  import AddAdminCenter from './AddAdminCenter'
  import QueryAdminCenter from './QueryAdminCenter'
  export default {
    data(){
      return{
        comName:'',
      }
    },
    methods:{
      /*增加业主*/
      adduser(){
        this.comName='adduser-center';
      },
      /*查询业主*/
      queryuser(){
        this.comName='queryuser-center';
      },
      /*增加后台管理员*/
      addadmin(){
        this.comName='AddAdminCenter'
      },
      /*查询后台管理员*/
      queryadmin(){
        this.comName='QueryAdminCenter'
      },
      /*批复用户宠物登记申请*/
      replyupetReg(){
        this.comName='ReplyUPetApplyCenter'
      },
      /*添加宠物*/
      addpet(){
        this.comName='AddPetCenter'
      },
      /*查询已登记的宠物信息*/
      querypetinfo(){
        this.comName='QuerypetRegCenter'
      },
      /*增加员工*/
      addstaff(){
        this.comName='addstaff-center';
      },
      /*查询员工*/
      querystaff(){
        this.comName='querystaff-center'
      },
      /*批复用户宠物寄养申请*/
      replyupetFoster(){
        this.comName='ReplyUPetFosterCenter'

      },
      /*生成宠物寄养记录*/
      fosterecord(){
        this.comName='LookFosterCenter'

      },
      /*查询寄养宠物信息*/
      lookfoster(){
        this.comName='QueryFosterPetCenter'
      },
      /*查看寄养记录*/
      lookrecord(){
        this.comName='QueryFosterRecordCenter'
      },
      /*可以帮助用户发布领养信息*/
      pubadoptinfo(){
        this.comName='PubAdoptInfoCenter'
      },
      queryaccept(){
        this.comName='LookPubAcceptCenter'
      },
      /*查看别人发布的领养信息*/
      queryabandon(){
        this.comName='LookPublishAdoptCenter'
      },
      /*生成领养记录*/
      adoptrecord(){
        this.comName='UPetAdoptRecordCenter'
      },
      /*查询领养记录*/
      queryadoptrecord(){
        this.comName='UpetQueryAdoptCenter'
      },
      /*查看寄养的宠物并生成记录*/
      queryupetFoster(){
        this.comName='LookFosterCenter'
      },
      /*疫苗超期信息*/
      invalidvaccin(){
        this.comName='VaccinCenter'
      },
      /*发布丢失信息*/
      loseinfopublish(){
        this.comName='LosePublishCenter'
      },
      /*发布捡到信息*/
      pickuppublish(){
        this.comName='PickupPublishCenter'
      },
      /*查看丢失信息*/
      queryloseinfo(){
        this.comName='LookLosePublishCenter'
      },
      /*查看捡到信息*/
      querypickup(){
        this.comName='LookPickupPublishCenter'
      },
      /*查看个人信息*/
      selfinfo(){
        this.comName='LookSelfInfoCenter'
      },
      looktip(){
        this.comName='AdminQueryTipCenter'
      }

    },
    props:['adminid'],
    components:{
      'adduser-center':AdduserCenter,
      'queryuser-center':QueryCenter,
      'addstaff-center':AddstaffCenter,
      'querystaff-center':QueryStaffCenter,
      'UPetAdoptRecordCenter':UPetAdoptRecordCenter,
      'AddAdminCenter':AddAdminCenter,
      'QueryAdminCenter':QueryAdminCenter,
      'FosterRecordCenter':FosterRecordCenter,
      'ReplyUPetFosterCenter':ReplyUPetFosterCenter,
      'ReplyUPetApplyCenter':ReplyUPetApplyCenter,
      'LookSelfInfoCenter':LookSelfInfoCenter,
      'LookFosterCenter':LookFosterCenter,
      'VaccinCenter':VaccinCenter,
      'AddPetCenter':AddPetCenter,
      'QuerypetRegCenter':QuerypetRegCenter,
      'QueryFosterPetCenter':QueryFosterPetCenter,
      'QueryFosterRecordCenter':QueryFosterRecordCenter,
      'PubAdoptInfoCenter':PubAdoptInfoCenter,
      'LookPublishAdoptCenter':LookPublishAdoptCenter,
      'UpetQueryAdoptCenter':UpetQueryAdoptCenter,
      'PickupPublishCenter':PickupPublishCenter,
      'LosePublishCenter':LosePublishCenter,
      'LookPickupPublishCenter':LookPickupPublishCenter,
      'LookLosePublishCenter':LookLosePublishCenter,
      'LookPubAcceptCenter':LookPubAcceptCenter,
      'AdminQueryTipCenter':AdminQueryTipCenter
    },
    mounted(){
      this.comName='LookSelfInfoCenter'
    }
  }
</script>

<style scoped>
  @import "../../../static/css/admin/adminhomechild.css";
</style>
