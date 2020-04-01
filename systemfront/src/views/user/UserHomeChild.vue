<template>
    <div class="main">
      <el-container>
        <el-aside class="aside" width="230px">
          <el-menu :default-openeds="['1','2','3','4','5', '6','7']" >
            <el-submenu index="1" >
              <template slot="title" ><i class="el-icon-message"></i><span class="titmessage" >宠物管理</span></template>
              <el-menu-item class="shortmessage" @click="applypetReg()"  index="1-1" >登记申请</el-menu-item>
              <el-menu-item class="shortmessage" @click="querypetinfo()"  index="1-2">宠物查询</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-message"></i><span class="titmessage" >寄养管理</span></template>
              <el-menu-item class="shortmessage" @click="applypetfoster()" index="2-1">寄养申请</el-menu-item>
              <el-menu-item class="shortmessage" @click="fosterecord()" index="2-2">寄养记录</el-menu-item>
              <el-menu-item class="langmessage" @click="queryfosterpet()" index="2-3">查看寄养宠物</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title" ><i class="el-icon-message" ></i><span class="titmessage">领养管理</span></template>
              <el-menu-item class="shortmessage" @click="adoptpublish()" index="3-1">发布信息</el-menu-item>
              <el-menu-item class="shortmessage" @click="accept()" index="3-2">领养消息</el-menu-item>
              <el-menu-item class="shortmessage" @click="abandon()" index="3-3">弃养消息</el-menu-item>
              <el-menu-item class="shortmessage" @click="adoptrecord()" index="3-4">领养记录</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title"><i class="el-icon-message"></i> <span class="titmessage">疫苗管理</span></template>
              <el-menu-item class="shortmessage" @click="vaccin()">超期信息</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title"><i class="el-icon-message"></i><span class="titmessage">寻宠管理</span></template>
              <el-menu-item class="langmessage" @click="losepublish()">发布丢失信息</el-menu-item>
              <el-menu-item class="langmessage" @click="pickpublish()">发布拾到信息</el-menu-item>
              <el-menu-item class="langmessage" @click="looklose()">查看丢失消息</el-menu-item>
              <el-menu-item class="langmessage" @click="lookpick()">查看拾到消息</el-menu-item>
            </el-submenu>
            <el-submenu index="6">
              <template slot="title"><i class="el-icon-message"></i><span class="titmessage">个人管理</span></template>
              <el-menu-item class="shortmessage"  @click="userself()">个人信息</el-menu-item>
            </el-submenu>
            <el-submenu index="7">
              <template slot="title"><i class="el-icon-message"></i><span class="titmessage">投诉管理</span></template>
              <el-menu-item class="shortmessage"  @click="looktip()">发表投诉</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-container>
          <component :is="comName" :userid="userid"></component>
        </el-container>
      </el-container>
    </div>
</template>

<script>
    import  UPetRegCenter from './UPetRegCenter.vue'
    import  UPetFosterRecordCenter from './UPetFosterRecordCenter.vue'
    import  UPetQueryCenter from './UPetQueryCenter.vue'
    import  UPetFosterCenter from './UPetFosterApplyCenter.vue'
    import  UPetAdoptPublishCenter from './UPetAdoptPublishCenter.vue'
    import  UPetAdoptRecordCenter from './UPetAdoptRecordCenter.vue'
    import  UPetLosePublishCenter from './UPetLosePublishCenter.vue'
    import  UserSelfCenter from './UserSelfCenter'
    import  UpetPickPublishCenter from './UpetPickPublishCenter.vue'
    import  UpetQueryAcceptCenter from './queryaccept/UpetQueryAcceptCenter.vue'
    import  UpetQueryAbandonCenter from './queryabandon/UpetQueryAbandonCenter.vue'
    import  UpetQueryVaccinCenter from './queryvaccin/UpetQueryVaccinCenter.vue'
    import  UpetQueryFosterpetCenter from './queryfosterpet/UpetQueryFosterpetCenter.vue'
    import  UpetLookLoseInfoCenter from './lookloseinfo/UpetLookLoseInfoCenter'
    import  UpetLookPickInfoCenter from './lookpickinfo/UpetLookPickInfoCenter'
    import  TipCenter from './TipCenter'

    export default {
        data(){
          return{
              comName:'',
          }
        },
      methods:{
        applypetReg(){
          this.comName='UPetRegCenter';
        },
        querypetinfo(){
          this.comName='UPetQueryCenter';
        },
        applypetfoster(){
          this.comName='UPetFosterCenter';
        },
        fosterecord(){
          this.comName='UPetFosterRecordCenter';
        },
        adoptpublish(){
          this.comName='UPetAdoptPublishCenter';
        },
        adoptrecord(){
          this.comName='UPetAdoptRecordCenter';
        },
        /*个人信息*/
        userself(){
          this.comName='UserSelfCenter';
        },
        losepublish(){
          this.comName='UPetLosePublishCenter';
        },
        pickpublish(){
          this.comName='UpetPickPublishCenter';
        },
        accept(){
          this.comName='UpetQueryAcceptCenter'
        },
        abandon(){
          this.comName='UpetQueryAbandonCenter'
        },
        vaccin(){
          this.comName='UpetQueryVaccinCenter'
        },
        queryfosterpet(){
          this.comName='UpetQueryFosterpetCenter'
        },
        looklose(){
          this.comName='UpetLookLoseInfoCenter'
        },
        lookpick(){
          this.comName='UpetLookPickInfoCenter'
        },
        looktip(){
          this.comName='TipCenter'
        }
      },
      props:['userid'],
      components:{
        /*宠物登记*/
        'UPetRegCenter':UPetRegCenter,
        'UPetQueryCenter':UPetQueryCenter,
        'UPetFosterCenter':UPetFosterCenter,
        'UPetFosterRecordCenter':UPetFosterRecordCenter,
        'UPetAdoptPublishCenter':UPetAdoptPublishCenter,
        'UPetAdoptRecordCenter':UPetAdoptRecordCenter,
        'UserSelfCenter':UserSelfCenter,
        'UPetLosePublishCenter':UPetLosePublishCenter,
        'UpetPickPublishCenter':UpetPickPublishCenter,
        'UpetQueryAcceptCenter':UpetQueryAcceptCenter,
        'UpetQueryAbandonCenter':UpetQueryAbandonCenter,
        'UpetQueryVaccinCenter':UpetQueryVaccinCenter,
        'UpetQueryFosterpetCenter':UpetQueryFosterpetCenter,
        'UpetLookLoseInfoCenter':UpetLookLoseInfoCenter,
        'UpetLookPickInfoCenter':UpetLookPickInfoCenter,
        'TipCenter':TipCenter
      },
      mounted(){
        this.comName='UserSelfCenter';
      }
    }
</script>

<style scoped>
  @import "../../../static/css/user/userhomechild.css";
  .red{
    background: #ff710a;
  }
</style>
