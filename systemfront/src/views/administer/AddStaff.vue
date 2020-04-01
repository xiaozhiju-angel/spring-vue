<template>
  <!--物业管理员模块-->
  <div class="middle">
    <div style="font-size:18px;padding-top: 30px">添加员工</div>
    <el-form  class="register_message"  label-width="100px" ref="ruleformstaff"  :model="ruleForm"  :rules="rulestaff" >
      <div class="middle_top">
        <div class="middle_left">
          <el-form-item class="elformitem" label="员工姓名" prop="staffname">
            <el-input  v-model="ruleForm.staffname"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item label="员工性别" prop="staffsex">
            <el-radio-group v-model="ruleForm.staffsex">
              <el-radio :label="'男'">男</el-radio>
              <el-radio :label="'女'">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="员工电话" prop="stafftel">
            <el-input v-model="ruleForm.stafftel" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item label="员工邮件" prop="staffemail">
            <el-input v-model="ruleForm.staffemail" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="middle_right">
          <el-form-item prop="staffimageUrl">
            <el-upload
              class="avatar-uploader"
              list-type="picture-card"
              name="file"
              action="http://localhost:8088//upload/uploadimg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessstaff"
              :before-upload="beforeAvatarUpload"
              border>
              <img v-if="staffimageUrl" :src="staffimageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button class="imgdes" size="mini" type="plain">员工头像</el-button>
          </el-form-item>
        </div>
      </div>
      <div class="middle_bottom">
        <div class="bottomtop">
          <el-form-item class="bottomleft"  label="身份证号" prop="staffidcard">
            <el-input v-model="ruleForm.staffidcard" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="bottomright" label="用户名" prop="staffloginame">
            <el-input  v-model="ruleForm.staffloginame"  size="mini" clearable> </el-input>
          </el-form-item>
        </div>
        <div class="bottomcenter">
          <el-form-item class="bottomcenterleft" label="入职时间" prop="staffregtime" >
            <el-date-picker
              class="datetime"
              v-model="ruleForm.staffregtime"
              size="mini"
              type="date"
              @change="formatTimeStaff"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item  class="bottomcenterright" label="密码" prop="staffpass">
            <el-input v-model="ruleForm.staffpass" size="mini" show-password clearable></el-input>
          </el-form-item>
        </div>
        <div class="addition">
          <el-form-item class="left"  label="用户身份" prop="userprop">
            <el-input  userauthor v-model="ruleForm.staffprop" size="mini" disabled></el-input>
          </el-form-item>
        </div>
      </div>
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="mini" type="primary" @click="onsubmitstaff()">添 加</el-button>
          <el-button class="btn" size="mini" plain @click="resetFormstaff()">重 置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
  /* import store from '../.././store/store.js'*/
  export default {
    data() {
      return {
        fileList: [],
        staffimageUrl:'',
        options: [{
          value: '物业管理员',
          label: '物业管理员'
        }],
        img:'',
        ruleForm: {
          staffprop:'物业管理员',
          staffname:'',
          staffsex:'',
          staffimageUrl:'',
          staffidcard:'',
          stafftel:'',
          staffemail:'',
          staffloginame:'',
          staffpass:'',
          staffregtime:'',
        },
        rulestaff:{
          staffname: [
            { required: true, message: '必填:姓名',trigger: 'blur'}
          ],
          staffidcard: [{
            required: true,
            message: '必填:身份证号码',
            trigger: 'blur'
          }, {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '身份证号码格式不正确',
            trigger: 'blur'
          }],
          staffemail: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          staffsex:[{ required: true, message: '必填:性别', trigger: 'blur' }],
          stafftel: [
            {required: true, message: '必填:手机号码', trigger: 'blur'},
            {min: 11, message: '最少11位数', trigger: 'blur'},
            {validator: this.validator.phoneNumber, trigger: 'blur'}
          ],
          staffloginame:[{ required: true, message: '必填:用户名', trigger: 'blur' }],
          staffpass: [
            {required: true, message: '必填:密码', trigger: 'blur'},
            {min: 6, message: '最少6位数', trigger: 'blur'},
            {validator: this.validator.enOrnunText, trigger: 'blur'}
          ],
        },
      };
    },

    components:{

    },
    methods: {
      /*员工*/
      handleChange(value){
        this.ruleForm.staffprop=value;
        console.log(this.ruleForm.staffprop);
      },
      formatTimeStaff(time){
        this.ruleForm.staffregtime=time;
        console.log(time)
      },
      staffchangesex(value){
        this.ruleForm.staffsex=value;
        console.log( this.ruleForm.staffsex);
      },
      handleAvatarSuccessstaff(res, file,filesList) {
        this.staffimageUrl = URL.createObjectURL(file.raw);
        /*console.log( this.staffimageUrl)*/
        /*console.log( filesList);*/
        /*this.img = res.filepath;
        console.log(this.img);*/
        this.ruleForm.staffimageUrl = res.filename; //将返回的文件储存路径赋值picture字段
        /* console.log(this.ruleForm.staffimageUrl);*/
        this.$store.state.imgurl=res.filepath;
        /*console.log(this.$store.state.imgurl);*/
      },
      resetFormstaff(){
        this.$refs.ruleformstaff.resetFields();
        this.staffimageUrl='';
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
      onsubmitstaff(){
        var that=this;
        var m=this.datesize(this.ruleForm.staffregtime,this.getNowFormatDate());
        this.$refs.ruleformstaff.validate((valid) => {
          if (valid) {
            if(m==0){
              this.postRequest("/staff/savestaff",{
                staffname:this.ruleForm.staffname,
                staffsex: this.ruleForm.staffsex,
                staffimg: this.ruleForm.staffimageUrl,
                staffidcard:this.ruleForm.staffidcard,
                stafftel:this.ruleForm.stafftel,
                staffemail:this.ruleForm.staffemail,
                staffloginame: this.ruleForm.staffloginame,
                staffpass: this.ruleForm.staffpass,
                staffprop: this.ruleForm.staffprop,
                staffregtime: this.ruleForm.staffregtime
              }).then(response=>{
                if (response.data.success) {
                  alert(response.data.save);
                  that.resetFormstaff();
                }
                else if(response.data.useridcard||response.data.staffidcard||response.data.adminidcard){
                  alert(response.data.idcard);
                }
                else if(response.data.loginamerepeat){
                  alert(response.data.repeat);
                }
              })
            }
            else if(m==1)
              alert("时间与实际不符");
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
  @import '../../../static/css/staff/addstaff.css';
</style>
