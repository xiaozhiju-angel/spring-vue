<template>
  <div class="messageloginpage">
    <div class="top">
      <i class="el-icon-close" @click="back()"></i>
      <span class="register">注册</span>
    </div>
    <div class="header">
      <img  src="../../static/petimg/009.jpg" />
      <span class="header_title">欢迎来到社区宠物管理系统</span>
    </div>
    <div class="main">
      <el-form :model="ruleForm" :rules="rules" ref="ruleValidate" label-width="100px" class="demo-ruleForm">
        <el-form-item label="手机号" prop="tel" >
          <el-input class="tel" placeholder="请输入手机号" v-model="ruleForm.tel" size="mini">
            <template slot="prepend">+86 <i class="el-icon-arrow-down"></i></template>
          </el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="pass">
          <el-input class="tel" v-model="ruleForm.pass" placeholder="密码" size="mini" clearable>
            <el-button slot="append" size="mini">获取验证码</el-button>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="btn_login" type="primary" @click="submitForm('ruleForm')" size="small">登录</el-button>
        </el-form-item>
        <div>
        </div>
      </el-form>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          tel: '',
          pass: '',
        },
        rules: {
          tel: [
            { required: true, message: '请输入手机号', trigger: 'blur' },
          ],
          pass: [
            { required: true, message: '请输入验证码', trigger: 'blur' },
            { min: 4,message: '最少4位数', trigger: 'change' }
          ],
        }
      };
    },
    methods: {
      submitForm() {
        this.$refs.ruleValidate.validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      send_note(tel,code){
        //tel:电话号码，code：自定义的验证码
        //短信内容模板，已经在sms平台绑定此内容，所以会比普通的更快到达用户手机。
        const text='验证码：'+code+',您正在使用登陆功能,该验证码仅用于身份验证,在五分钟之内有效，请勿泄露给其他人使用。'
        let param = new URLSearchParams();
        param.append('Uid',用户名);
        param.append('Key',密钥);
        param.append('smsMob',tel);
        param.append('smsText',text);
        this.$http.post('/apis/',param,{//post请求，在请求时会自动把param拼接再Param后面
          headers:{
            'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'//必须要加头
          },
        }).then(function (response) {
          console.log(response)}
        )
      },


      back(){
        this.$router.push({
          path:'/login'
        });
      },
    }
  }
</script>

<style scoped>
  @import "../../static/css/messagelogin.css";
</style>

