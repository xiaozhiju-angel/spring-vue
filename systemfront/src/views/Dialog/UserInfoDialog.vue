<template>
    <div>
      <el-dialog class="Infodetaile" :visible.sync="dialogFormVisible">
          <el-form :model="ruleForm" label-width="80px">
            <div class="top">
              <div class="header">
                <el-form-item  label="用户姓名" prop="username">
                  <el-input  v-model="ruleForm.username"  size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="" label="用户性别" prop="usersex">
                  <el-input  v-model="ruleForm.usersex"  size="mini" clearable></el-input>
                </el-form-item>
              </div>
              <div class="picture">
                <div> <img src="../../../static/petimg/1.jpg"/></div>
                <div class="userimg">用户头像</div>
              </div>
            </div>
            <div class="main">
              <div class="center">
                <el-form-item class="centerleft" label="用户电话" prop="usertel">
                  <el-input  v-model="ruleForm.usertel" size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="centerright" label="用户邮件" prop="useremail">
                  <el-input v-model="ruleForm.useremail" size="mini" clearable></el-input>
                </el-form-item>
              </div>
              <div class="middle">
                <el-form-item  class="middleleft" label="门牌号码" prop="userdoor">
                  <el-input v-model="ruleForm.userdoor" size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="middleright"  label="身份证号" prop="useridcard">
                  <el-input v-model="ruleForm.useridcard" size="mini" clearable></el-input>
                </el-form-item>
              </div>
              <div class="login">
                <el-form-item class="loginleft" label="用户名" prop="userloginame">
                  <el-input v-model="ruleForm.userloginame" size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="loginright" label="密码" prop="userpass">
                  <el-input v-model="ruleForm.userpass" show-password clearable size="mini"></el-input>
                </el-form-item>
              </div>
              <div class="authority">
                <el-form-item  class="authorityleft" label="是否租户" prop="useristenant">
                  <el-input  v-model="ruleForm.useristenant"  size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="authorityright" label="入住时间" prop="useregtime">
                  <el-date-picker
                    class="datetime"
                    v-model="ruleForm.useregtime"
                    size="mini"
                    type="date"
                    @change="formatTimeUser"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
              </div>
            </div>
           </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      data(){
        return{
          ruleForm: {
            userprop:['用户'],
            username: '',
            usersex: '',
            userimageUrl: '',
            useridcard: '',
            usertel: '',
            useremail: '',
            userdoor: '',
            useristenant: '',
            userloginame: '',
            userpass: '',
            useregtime: '',
          },
          dialogFormVisible: true,
        }
      }
    }
</script>

<style scoped>
  @import '../../../static/css/dialog/userinfodialog.css';
</style>
