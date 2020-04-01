<template>
  <div class="middle" >
    <el-form class="register_message" label-width="100px" ref="ruleformuser" :model="ruleForm" :rules="rulesuser">
      <div class="middle_top">
        <div class="middle_left">
          <el-form-item class="elformitem" label="业主姓名" prop="username">
            <el-input  v-model="ruleForm.username"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item label="业主性别" prop="usersex">
            <el-radio-group v-model="ruleForm.usersex" @change="userchangesex">
              <el-radio :label="'男'">男</el-radio>
              <el-radio :label="'女'">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="业主电话" prop="usertel">
            <el-input v-model="ruleForm.usertel" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item label="业主邮箱" prop="useremail">
            <el-input v-model="ruleForm.useremail" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="middle_right">
          <el-form-item prop="userimageUrl">
            <el-upload
              class="avatar-uploader"
              list-type="picture-card"
              name="file"
              action="http://localhost:8088//upload/uploadimg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessuser"
              :before-upload="beforeAvatarUpload"
              border>
              <img v-if="userimageUrl" :src="userimageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button class="imgdes" type="plain" size="mini">业主头像</el-button>
          </el-form-item>
        </div>
      </div>
      <div class="middle_bottom">
        <div class="bottomtop">
          <el-form-item  class="bottomleft" label="门牌号码" prop="userdoor">
            <el-input v-model="ruleForm.userdoor" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="bottomright" label="用户名" prop="userloginame">
            <el-input v-model="ruleForm.userloginame" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="bottomcenter">
          <el-form-item class="bottomcenterleft"  label="身份证号" prop="useridcard">
            <el-input v-model="ruleForm.useridcard" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="bottomcenterright" label="密码" prop="userpass">
            <el-input v-model="ruleForm.userpass" show-password clearable size="mini"></el-input>
          </el-form-item>
        </div>
        <div class="bottombottom">
          <el-form-item  class="bottomfooterleft" label="是否租户" prop="useristenant">
            <el-radio-group  v-model="ruleForm.useristenant" @change="userchangeTenantorYezhu">
              <el-radio class="iszuhuornot" :label="'是'">是</el-radio>
              <el-radio :label="'否'">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item class="bottomfooterright" label="入住时间" prop="useregtime">
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
        <div class="addition">
          <el-form-item class="left"  label="用户身份" prop="userprop">
            <el-input class="userauthor" v-model="ruleForm.userprop" size="mini" disabled></el-input>
          </el-form-item>
        </div>
      </div>
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="small" type="primary" @click="onSubmituser()">添加</el-button>
          <el-button class="btn" size="small" plain  @click="resetFormuser()">重置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        fileList: [],
        userimageUrl: '',
        options: [{
          value: '业主',
          label: '业主'
        }],
        ruleForm: {
          userprop:'业主',
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
        rulesuser: {
          username: [
            {
              required: true, message: '必填:姓名', trigger: 'blur'
            }
          ],
          useridcard:[{ required: true, message: '必填:身份证号',trigger: 'blur'}],
          usersex: [{required: true, message: '必填:性别', trigger: 'blur'}],
          usertel: [{required: true, message: '必填:电话', trigger: 'blur'}],
          userloginame: [{required: true, message: '必填:用户名', trigger: 'blur'}],
          useristenant: [{required: true, message: '必填:是否租户', trigger: 'blur'}],
          userdoor: [{required: true, message: '必填:门牌号', trigger: 'blur'}],
          useremail: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          userpass: [
            {
              required: true, message: '必填:密码', trigger: 'blur',
              min: 6, message: '最少6位数', trigger: 'blur'
            }
          ],
        },
      }
    },
    methods: {
      /*用户*/
      /*handleChange(value){
        this.ruleForm.userprop=value;
        console.log(this.ruleForm.userprop);
      },*/
      formatTimeUser(time){
        this.ruleForm.useregtime=time;
        console.log(time)
      },
      userchangesex(value){
        /*console.log(value);*/
        this.ruleForm.usersex=value;
        console.log(this.ruleForm.userprop);
      },
      userchangeTenantorYezhu(value){
        this.ruleForm.useristenant=value;
        console.log(value);
      },
      handleAvatarSuccessuser(res, file,filesList) {
        this.userimageUrl = URL.createObjectURL(file.raw);
        console.log( this.userimageUrl)
        /*console.log( filesList);*/
        this.ruleForm.userimageUrl = res.filename; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.userimageUrl);
      },
      resetFormuser(){
        this.$refs.ruleformuser.resetFields();
        this.userimageUrl='';
      },
      onSubmituser(){
        var that=this;
        this.$refs.ruleformuser.validate((valid) => {
          if (valid) {
            this.postRequest("/user/saveuser",{
              username:this.ruleForm.username,
              usersex: this.ruleForm.usersex,
              userimg: this.ruleForm.userimageUrl,
              useridcard:this.ruleForm.useridcard,
              usertel:this.ruleForm.usertel,
              useremail:this.ruleForm.useremail,
              userdoor: this.ruleForm.userdoor,
              useristenant:this.ruleForm.useristenant,
              userloginame:this.ruleForm.userloginame,
              userpass: this.ruleForm.userpass,
              userprop:this.ruleForm.userprop,
              useregtime: this.ruleForm.useregtime
            }).then(response=>{
              if(response.data.useridcard||response.data.staffidcard||response.data.adminidcard){
                alert(response.data.idcard);
              }else if(response.data.loginamerepeat){
                alert(response.data.repeat);
              }else if (response.data.success) {
                alert(response.data.saveuser);
                that.resetFormuser();
              }
            })
              .catch(err=>{

              })
          }
          else {
            console.log('信息有误！');
            return false;
          }
        });
      },

      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';
        const isLt1M = file.size / 1024 / 1024 < 1;

        if (!isJPG && !isGIF && !isPNG && !isBMP) {
          this.common.errorTip('上传图片必须是JPG/GIF/PNG/BMP 格式!');
        }
        if (!isLt1M) {
          this.common.errorTip('上传图片大小不能超过 2MB!');
        }
        return (isJPG || isBMP || isGIF || isPNG) && isLt1M;
      },
    },
    /* props:['useridentity'],*/
  }
</script>

<style scoped>
  @import '../../../static/css/admin/adduser.css';
</style>
