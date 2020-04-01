<template>
  <div class="loginpage">
    <div class="top">
     <!-- <i class="el-icon-close" @click="back()"></i>-->
      <span class="goback" type="info" size="mini" @click="back()">返回</span>
      <span class="register" type="info" size="mini" @click="register()">注册</span>
    </div>
    <div class="header">
      <img  src="../../static/petimg/009.jpg" alt="这是一张宠物图片"/>
      <span class="header_title">宠物管理系统</span>
    </div>
    <div class="main">
      <el-form :model="ruleForm" :rules="rules" ref="ruleFormlogin" label-width="100px" class="demo-ruleForm">
        <el-form-item prop="userprop" label="用户权限" class="authority">
          <el-select v-model="ruleForm.userprop" placeholder="userauthority" @change="selectuserprop" size="mini" style="width: 300px">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户名" prop="userloginame" >
          <el-input class="user" v-model="ruleForm.userloginame"  placeholder="username" size="mini" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userpass">
          <el-input class="user" v-model="ruleForm.userpass" placeholder="userpassword" size="mini" show-password clearable></el-input>
        </el-form-item>
        <el-form-item style="margin-top: 30px">
          <el-button class="btn_login" type="primary" @click="submitForm()" size="small">登 录</el-button>
          <el-button class="reset" @click="resetForm('ruleForm')" size="small" plain>重 置</el-button>
        </el-form-item>
      </el-form>
    </div>
   <!-- <div class="end">
     &lt;!&ndash; <button class="message_login" @click="messagelogin()">短信验证码登录</button>&ndash;&gt;
      <button class="forget_pass" @click="forgetpass()">返回首页</button>
    </div>-->
  </div>
</template>

<script>
  /*var md5 = require('js-md5');*/
  export default {
    data() {
      return {
        userauthority:'',
        options:[{
          value: '用户',
          label: '用户'
        }, {
          value: '物业管理员',
          label: '物业管理员'
        }, {
          value: '后台管理员',
          label: '后台管理员'
        }],
        ruleForm: {
          userloginame: '',
          userpass: '',
          userprop:'',
          userid:'',
          staffid:'',
          adminid:''
        },
        rules: {
          userloginame: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          userpass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6,message: '最少6位数', trigger: 'change' }
          ],
        }
      };
    },
    methods: {
      submitForm() {
        this.$refs.ruleFormlogin.validate((valid) => {
          if (valid) {
            var that=this;
           /* md5(this.ruleForm.userpass);
            var hash = md5.create();
            hash.update(this.ruleForm.userpass);
            hash.hex();
            console.log(hash);*/
           console.log(this.md5(this.ruleForm.userpass));
            this.getRequest("/admin/login?userloginame="+this.ruleForm.userloginame+"&userpass="+ this.md5(this.ruleForm.userpass)+"&userprop="+this.ruleForm.userprop
            ).then(response=>{
                console.log(response.data.userauthority);
                if(response.data.keyuser){
                  that.$router.push({
                    path:'/user?userprop='+response.data.userauthority+"&userid="+response.data.userid+"&username="
                      +response.data.username+"&userimg="+response.data.userimg,
                  });
                }
                else if(response.data.keystaff){
                  this.$store.state.staffid=response.data.staffid;
                  console.log( this.$store.state.staffid);
                  that.$router.push({
                    path:'/staff?userprop='+response.data.staffauthority+"&staffid="
                      +response.data.staffid+"&staffname="+response.data.staffname+"&staffimg="+response.data.staffimg,
                  });
                }
               else if(response.data.keyadmin){
                  this.$store.state.adminid=response.data.adminid;
                  console.log( this.$store.state.adminid);
                  that.$router.push({
                    path:'/admin?userprop='+response.data.adminauthority+"&adminid="
                      +response.data.adminid+"&adminame="+response.data.adminame+"&adminimg="+response.data.adminimg,
                  });
                }
               else{
                 alert("用户名或密码有误!")
                }
              /*response.data[1].userName*/
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      selectuserprop(vid){
        this.ruleForm.userprop=vid;
        console.log(this.ruleForm.userprop);
      },
      resetForm() {
        this.$refs.ruleFormlogin.resetFields();
      },
      getParams () {
        var prop=this.$route.query.userprop;
        var loginame=this.$route.query.userloginame;
        // 将数据放在当前组件的数据内
        this.userloginame = loginame;
        this.userprop = prop;
      },
     /* messagelogin(){
        this.$router.push({
          path:'/msg'
        });
      },*/
      forgetpass(){
        this.$router.push({
          path:'/home'
        });
      },
      back(){
        this.$router.push({
          path:'/home'
        });
      },
      register(){
        this.$router.push({
          path:'/urg'
        });
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
  @import "../../static/css/login.css";
</style>

