<template>
  <!--后台管理员模块-->
  <div class="middle">
    <el-form  class="register_message"  label-width="100px" ref="ruleformadmin" :model="ruleForm" :rules="rulesadmin"  >
      <div class="middle_top">
        <div class="middle_left">
          <el-form-item class="elformitem" label="管理员姓名" prop="adminame">
            <el-input  v-model="ruleForm.adminame"  size="mini"></el-input>
          </el-form-item>
          <el-form-item label="管理员性别" prop="adminsex">
            <el-radio-group v-model="ruleForm.adminsex" @change="adminchangesex">
              <el-radio :label="'男'">男</el-radio>
              <el-radio :label="'女'">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="管理员电话" prop="admintel">
            <el-input v-model="ruleForm.admintel" size="mini"></el-input>
          </el-form-item>
          <el-form-item label="管理员邮箱" prop="adminemail">
            <el-input v-model="ruleForm.adminemail" size="mini"></el-input>
          </el-form-item>
        </div>
        <div class="middle_right">
          <el-form-item prop="adminimageUrl">
            <el-upload
              class="avatar-uploader"
              list-type="picture-card"
              name="file"
              action="http://localhost:8088//upload/uploadimg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessadmin"
              :before-upload="beforeAvatarUpload"
              border>
              <img v-if="adminimageUrl" :src="adminimageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button class="imgdes" size="mini" type="plain">管理员头像</el-button>
          </el-form-item>
        </div>
      </div>
      <div class="middle_bottom">
        <div class="bottomtop">
          <el-form-item class="bottomleft"  label="身份证号" prop="adminidcard">
            <el-input v-model="ruleForm.adminidcard" size="mini"></el-input>
          </el-form-item>
          <el-form-item class="bottomright" label="用户名" prop="adminloginame">
            <el-input  v-model="ruleForm.adminloginame"  size="mini"> </el-input>
          </el-form-item>
        </div>
        <div class="bottomcenter">
          <el-form-item class="bottomcenterleft" label="入职时间" prop="adminregtime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.adminregtime"
              size="mini"
              type="date"
              @change="formatTimeAdmin"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item  class="pass" label="密码" prop="adminpass">
            <el-input v-model="ruleForm.adminpass" size="mini" show-password></el-input>
          </el-form-item>
        </div>
        <div class="addition">
          <el-form-item class="left"  label="用户身份" prop="userprop">
            <el-input v-model="ruleForm.adminprop" size="mini" disabled></el-input>
          </el-form-item>
        </div>
      </div>
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="small" type="primary" @click="onSubmitadmin()">注册</el-button>
          <el-button class="btn" size="small" plain @click="resetFormadmin()">重置</el-button>
          <el-button class="btn" size="small" plain>取消</el-button>
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
        adminimageUrl:'',
        options: [{
          value: '后台管理员',
          label: '后台管理员'
        }],
        ruleForm: {
          adminprop:'后台管理员',
          adminame:'',
          adminsex:'',
          adminimageUrl:'',
          adminidcard:'',
          admintel:'',
          adminemail:'',
          adminloginame:'',
          adminpass:'',
          adminregtime:''
        },
        rulesadmin:{
          adminame: [
            {required: true, message: '必填:姓名', trigger: 'blur'}
          ],
          adminidcard: [{
            required: true,
            message: '必填:身份证号码',
            trigger: 'blur'
          }, {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '身份证号码格式不正确',
            trigger: 'blur'
          }],
          adminemail: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          adminsex:[{ required: true, message: '必填:性别', trigger: 'blur' }],
          admintel:[{ required: true, message: '必填:电话', trigger: 'blur' }],
          adminloginame:[{ required: true, message: '必填:用户名', trigger: 'blur' }],
          adminpass: [
            { required: true,min: 6,message: '最少6位数', trigger: 'blur' }
          ],

        },
      };
    },
    methods: {
      /*后台*/
      handleChange(value){
        this.ruleForm.adminprop=value;
        console.log(this.ruleForm.adminprop);
      },
      formatTimeAdmin(time){
        this.ruleForm.adminregtime=time;
        console.log(time)
      },
      adminchangesex(value){
        this.ruleForm.adminsex=value;
        console.log( this.ruleForm.adminsex);
      },
      handleAvatarSuccessadmin(res, file,filesList) {
        this.adminimageUrl = URL.createObjectURL(file.raw);
        console.log( this.adminimageUrl)
        console.log( filesList);
        this.ruleForm.adminimageUrl = res.filename; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.adminimageUrl);
      },
      resetFormadmin(){
        this.$refs.ruleformadmin.resetFields();
      },
      onSubmitadmin(){
        var that=this;
        this.$refs.ruleformadmin.validate((valid) => {
          if (valid) {
            var that=this;
            this.postRequest("/admin/saveadmin",{
              adminame:this.ruleForm.adminame,
              adminsex: this.ruleForm.adminsex,
              adminimg:  this.ruleForm.adminimageUrl,
              adminidcard:this.ruleForm.adminidcard,
              admintel:this.ruleForm.admintel,
              adminemail:this.ruleForm.adminemail,
              adminloginame: this.ruleForm.adminloginame,
              adminpass: this.ruleForm.adminpass,
              adminprop: this.ruleForm.adminprop,
              adminregtime: this.ruleForm.adminregtime
            }).then(response=>{
              if (response.data.success) {
                that.$router.push({
                  path:'/login'
                });
                alert("注册成功,请登录");
              }
              else{
                alert(response.data.repeat);
              }
            })
          }
          else {
            console.log('请检查是否填写正确');
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
    }
  }
</script>
<style scoped>
  @import '../../../static/css/register.css';
</style>
