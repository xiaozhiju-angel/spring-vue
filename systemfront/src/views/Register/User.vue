<template>
  <div class="middle">
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
            <el-input type="email" v-model="ruleForm.useremail" size="mini" clearable></el-input>
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
          <el-form-item class="bottomfooterright" label="注册时间" prop="useregtime">
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
            <el-input v-model="ruleForm.userprop" size="mini" disabled></el-input>
          </el-form-item>
        </div>
      </div>
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="mini" type="primary" @click="onSubmituser()">注 册</el-button>
          <el-button class="btn" size="mini" plain  @click="resetFormuser()">重 置</el-button>
          <el-button class="btn" size="mini" plain @click="backlogin()">登 录</el-button>
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
          username: [{required: true, message: '必填:姓名', trigger: 'blur'}],
          useridcard: [{
            required: true,
            message: '必填:身份证号码',
            trigger: 'blur'
          }, {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '身份证号码格式不正确',
            trigger: 'blur'
          }],
          useremail: [
            {required: true, message: '必填:电子邮箱', trigger: 'blur'},
            {validator: this.validator.emailValue, trigger: 'blur'}
          ],
          usersex: [{required: true, message: '必填:性别', trigger: 'blur'}],
          usertel: [
            {required: true, message: '必填:手机号码', trigger: 'blur'},
            {min: 11, message: '最少11位数', trigger: 'blur'},
            {validator: this.validator.phoneNumber, trigger: 'blur'}
          ],
          userloginame: [{required: true, message: '必填:用户名', trigger: 'blur'}],
          useristenant: [{required: true, message: '必填:是否租户', trigger: 'blur'}],
          userdoor: [{required: true, message: '必填:门牌号', trigger: 'blur'}],
          userpass: [
             {required: true, message: '必填:密码', trigger: 'blur'},
             {min: 6, message: '最少6位数', trigger: 'blur'},
            {validator: this.validator.enOrnunText, trigger: 'blur'}
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
      },
      datesize(date1,date2) {
        var start = new Date(date1.replace("-", "/").replace("-", "/"));
        console.log(start.getTime());
        var end = new Date(date2.replace("-", "/").replace("-", "/"));
        console.log(end.getTime());
        if (end.getTime() < start.getTime()){
          return 1;
        }else return 0;
      },
      getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
          month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
          strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return this.dateFormat(currentdate);

      },
      dateFormat:function(time) {
        var date=new Date(time);
        var year=date.getFullYear();
        /* 在日期格式中，月份是从0开始的，因此要加0
         * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
         * */
        var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
        var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
        var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
        var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
        var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
        // 拼接
        /*return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;*/
        return year+"-"+month+"-"+day
      },

      onSubmituser(){
        var that=this;
        var m=this.datesize(this.ruleForm.useregtime,this.getNowFormatDate());
        this.$refs.ruleformuser.validate((valid) => {
          if (valid) {
            if(m==0){
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
                if (response.data.success) {
                  that.$router.push({
                    path:'/login'
                  });
                  alert("注册成功,请登录");
                }
                else if(response.data.useridcard||response.data.staffidcard||response.data.adminidcard){
                  alert(response.data.idcard);
                }
                else if(response.data.loginamerepeat){
                  alert(response.data.repeat);
                }
              })
            }
            else  if(m==1)
              alert("时间不符合实际");
          }
          else {
            console.log('请检查是否填写正确');
            return false;
          }
        });
      },
      backlogin(){
        this.$router.push({
          path:'/login'
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
  @import '../../../static/css/register.css';
</style>
