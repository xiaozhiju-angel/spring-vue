<template>
  <div class="main">
    <el-container>
      <el-aside class="aside" width="230px">
        <el-menu :default-openeds="['1','2','3','4','5', '6','7','8']" >
          <el-submenu index="1">
            <template slot="title" ><i class="el-icon-message"></i><span class="titmessage" @click="showusermanage()" >业主管理</span></template>
            <el-menu-item class="langmessage" @click="adduser()" >添加业主信息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryuser()" >查询业主信息</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title" ><i class="el-icon-message"></i><span class="titmessage"  >宠物管理</span></template>
            <el-menu-item class="langmessage" @click="addpet()">添加宠物信息</el-menu-item>
            <el-menu-item class="langmessage" @click="replyupetReg()"  >批复宠物登记</el-menu-item>
            <el-menu-item class="langmessage" @click="querypetinfo()" >查询宠物信息</el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage" >寄养管理</span></template>
            <el-menu-item class="langmessage" @click="replyupetFoster()">批复宠物寄养</el-menu-item>
            <el-menu-item class="langmessage" @click="fosterecord()">生成寄养记录</el-menu-item>
            <el-menu-item class="langmessage" @click="lookfoster()">查看寄养宠物</el-menu-item>
            <el-menu-item class="langmessage" @click="lookrecord()">查看寄养记录</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title" ><i class="el-icon-message" ></i><span class="titmessage">领养管理</span></template>
            <el-menu-item class="langmessage" @click="pubadoptinfo()">发布领养消息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryaccept()">查看领养消息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryabandon()">查看弃养消息</el-menu-item>
            <el-menu-item class="langmessage" @click="adoptrecord()">生成领养记录</el-menu-item>
            <el-menu-item class="langmessage" @click="queryadoptrecord()">查询领养记录</el-menu-item>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title"><i class="el-icon-message"></i> <span class="titmessage">疫苗管理</span></template>
            <el-menu-item class="shortmessage"  @click="invalidvaccin()">超期信息</el-menu-item>
          </el-submenu>
          <el-submenu index="6">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage">寻宠管理</span></template>
            <el-menu-item class="langmessage" @click="loseinfopublish()">发布丢失信息</el-menu-item>
            <el-menu-item class="langmessage" @click="pickuppublish()">发布拾到信息</el-menu-item>
            <el-menu-item class="langmessage" @click="queryloseinfo()">查看丢失消息</el-menu-item>
            <el-menu-item class="langmessage" @click="querypickup()">查看拾到消息</el-menu-item>
          </el-submenu>
          <el-submenu index="7">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage">个人管理</span></template>
            <el-menu-item class="shortmessage" @click="staffself()">个人信息</el-menu-item>
          </el-submenu>
          <el-submenu index="8">
            <template slot="title"><i class="el-icon-message"></i><span class="titmessage">投诉管理</span></template>
            <el-menu-item class="shortmessage"  @click="looktip()">投诉信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <component :is="comName" :staffid="staffid"></component>
      </el-container>
    </el-container>
  </div>
</template>


<script>
  import  StaffAddUserCenter from './StaffAddUserCenter.vue'
  import StaffSelfCenter from './StaffSelfCenter.vue';
  import StaffQueryUserCenter from './StaffQueryUserCenter.vue';
  import StaffAddPetCenter from './StaffAddPetCenter.vue';
  import StaffReplyPetRegCenter from './StaffReplyPetRegCenter.vue';
  import StaffQueryRegPetCenter from './StaffQueryRegPetCenter.vue';
  import StaffReplyPetFosterCenter from './StaffReplyPetFosterCenter.vue';
  import StaffBecomeFosterRecordCenter from './StaffBecomeFosterRecordCenter.vue';
  import StaffLookFosterPetCenter from './StaffLookFosterPetCenter.vue';
  import StaffLookFosterRecordCenter from './StaffLookFosterRecordCenter.vue';
  import StaffBecomeAdoptRecordCenter from './adoptmanage/StaffBecomeAdoptRecordCenter';
  import StaffLookPubAbandonCenter from './adoptmanage/StaffLookPubAbandonCenter';
  import StaffLookPubAcceptCenter from './adoptmanage/StaffLookPubAcceptCenter';
  import StaffPubAdoptCenter from './adoptmanage/StaffPubAdoptCenter';
  import StaffQueryAdoptRecordCenter from './adoptmanage/StaffQueryAdoptRecordCenter';
  import StaffVaccinCenter from './StaffVaccinCenter.vue';
  import StaffPubLoseCenter from './losepickmanage/StaffPubLoseCenter';
  import StaffPubPickCenter from './losepickmanage/StaffPubPickCenter';
  import StaffLookLoseCenter from './losepickmanage/StaffLookLoseCenter';
  import StaffLookPickCenter from './losepickmanage/StaffLookPickCenter';
  import StaffQueryTipCenter from './tipmanage/StaffQueryTipCenter';

  export default {
    data(){
      return{
        comName:''
      }
    },
    components:{
      'StaffSelfCenter':StaffSelfCenter,
      'StaffAddUserCenter':StaffAddUserCenter,
      'StaffQueryUserCenter':StaffQueryUserCenter,
      'StaffAddPetCenter':StaffAddPetCenter,
      'StaffReplyPetRegCenter':StaffReplyPetRegCenter,
      'StaffQueryRegPetCenter':StaffQueryRegPetCenter,
      'StaffReplyPetFosterCenter':StaffReplyPetFosterCenter,
      'StaffBecomeFosterRecordCenter':StaffBecomeFosterRecordCenter,
      'StaffLookFosterPetCenter':StaffLookFosterPetCenter,
      'StaffLookFosterRecordCenter':StaffLookFosterRecordCenter,
      'StaffPubAdoptCenter':StaffPubAdoptCenter,
      'StaffLookPubAcceptCenter':StaffLookPubAcceptCenter,
      'StaffLookPubAbandonCenter':StaffLookPubAbandonCenter,
      'StaffBecomeAdoptRecordCenter':StaffBecomeAdoptRecordCenter,
      'StaffQueryAdoptRecordCenter':StaffQueryAdoptRecordCenter,
      'StaffVaccinCenter':StaffVaccinCenter,
      'StaffPubLoseCenter':StaffPubLoseCenter,
      'StaffPubPickCenter':StaffPubPickCenter,
      'StaffLookLoseCenter':StaffLookLoseCenter,
      'StaffLookPickCenter':StaffLookPickCenter,
      'StaffQueryTipCenter':StaffQueryTipCenter

    },
    props:['staffid'],
    methods:{
      adduser(){
        this.comName='StaffAddUserCenter'
      },
      staffself(){
        this.comName='StaffSelfCenter'
      },
      queryuser(){
        this.comName='StaffQueryUserCenter'
      },
      addpet(){
        this.comName='StaffAddPetCenter'
      },
      replyupetReg(){
        this.comName='StaffReplyPetRegCenter'
      },
      querypetinfo(){
        this.comName='StaffQueryRegPetCenter'
      },
      replyupetFoster(){
        this.comName='StaffReplyPetFosterCenter'
      },
      fosterecord(){
        this.comName='StaffBecomeFosterRecordCenter'
      },
      lookfoster(){
        this.comName='StaffLookFosterPetCenter'
      },
      lookrecord(){
        this.comName='StaffLookFosterRecordCenter'
      },
      pubadoptinfo(){
        this.comName='StaffPubAdoptCenter'
      },
      queryaccept(){
        this.comName='StaffLookPubAcceptCenter'
      },
      queryabandon(){
        this.comName='StaffLookPubAbandonCenter'
      },
      adoptrecord(){
        this.comName='StaffBecomeAdoptRecordCenter'
      },
      queryadoptrecord(){
        this.comName='StaffQueryAdoptRecordCenter'
      },
      invalidvaccin(){
        this.comName='StaffVaccinCenter'
      },
      loseinfopublish(){
        this.comName='StaffPubLoseCenter'
      },
      pickuppublish(){
        this.comName='StaffPubPickCenter'
      },
      queryloseinfo(){
        this.comName='StaffLookLoseCenter'
      },
      querypickup(){
        this.comName='StaffLookPickCenter'
      },
      looktip(){
        this.comName='StaffQueryTipCenter'
      }

    },


    mounted(){
      this.comName='StaffSelfCenter'

    }
  }
</script>

<style scoped>
  @import "../../../static/css/admin/adminhomechild.css";
</style>
