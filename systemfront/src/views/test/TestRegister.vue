<template >
  <div id="register">
    <div id="top">
      <div class="top_left">
        <img  />
        <span class="des">注册登录更精彩</span>
      </div>
      <div class=" top_right">
        <span class="exit">退出</span>
      </div>
    </div>
    <div id="header">
      <p class="header_title">注册界面</p>
    </div>
    <div>
      <el-form :model="ruleForm" ref="ruleformvalidate" :rules="rulesAuthority">
        <el-form-item class="block" label="用户属性" prop="userprop">
          <!--v-model="selectedOptions"-->
          <el-cascader
            size="mini"
            :options="options"
            v-model="ruleForm.userprop"
            @change="handleChange">
          </el-cascader>
        </el-form-item>
        <!--业主模块-->
        <div class="middle" v-if="showusermessage">
          <el-form class="register_message" label-width="100px" ref="ruleformuser" :rules="rulesuser">
            <div class="middle_top">
              <div class="middle_left">
                <el-form-item class="elformitem" label="用户姓名" prop="username">
                  <el-input  v-model="ruleForm.user.username"  size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item label="用户性别" prop="usersex">
                  <el-radio-group v-model="ruleForm.user.usersex" @change="userchangesex">
                    <el-radio :label="'boy'">男</el-radio>
                    <el-radio :label="'girl'">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="用户电话" prop="usertel">
                  <el-input v-model="ruleForm.user.usertel" size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item label="用户邮件" prop="useremail">
                  <el-input v-model="ruleForm.user.useremail" size="mini" clearable></el-input>
                </el-form-item>
              </div>
              <div class="middle_right">
                <el-form-item prop="userimageUrl">
                  <el-upload
                    class="avatar-uploader"
                    list-type="picture-card"
                    action="''"
                    :auto-upload=false
                    :on-change="onchangeuser"
                    :show-file-list="false"
                    :file-list="fileList"
                    border>
                    <img v-if="userimageUrl" :src="userimageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                  <el-button class="imgdes" type="plain" size="mini">用户头像</el-button>
                </el-form-item>
              </div>
            </div>
            <div class="middle_bottom">
              <div class="bottomtop">
                <el-form-item  class="bottomleft" label="门牌号码" prop="userdoor">
                  <el-input v-model="ruleForm.user.userdoor" size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="bottomright" label="用户名" prop="userloginame">
                  <el-input v-model="ruleForm.user.userloginame" size="mini" clearable></el-input>
                </el-form-item>
              </div>
              <div class="bottomcenter">
                <el-form-item class="bottomcenterleft"  label="身份证号" prop="useridcard">
                  <el-input v-model="ruleForm.user.useridcard" size="mini" clearable></el-input>
                </el-form-item>
                <el-form-item class="bottomcenterright" label="密码" prop="userpass">
                  <el-input v-model="ruleForm.user.userpass" show-password clearable size="mini"></el-input>
                </el-form-item>
              </div>
              <div class="bottombottom">
                <el-form-item  class="bottomfooterleft" label="是否租户" prop="useristenant">
                  <el-radio-group  v-model="ruleForm.user.useristenant" @change="userchangeTenantorYezhu">
                    <el-radio class="iszuhuornot" :label="'是'">是</el-radio>
                    <el-radio :label="'否'">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item class="bottomfooterright" label="入住时间" prop="useregtime">
                  <el-date-picker
                    class="datetime"
                    v-model="ruleForm.user.useregtime"
                    size="mini"
                    type="date"
                    @change="formatTimeUser"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
              </div>
            </div>
            <div class="btn_bottom">
              <el-form-item>
                <el-button class="btn" size="small" type="primary" @click="onSubmituser()">注册</el-button>
                <el-button class="btn" size="small" plain  @click="resetFormuser()">重置</el-button>
                <el-button class="btn" size="small" plain>取消</el-button>
              </el-form-item>
            </div>
          </el-form>
        </div>
        <!--物业管理员模块-->
        <div class="middle" v-if="showstaffmessage">
          <el-form  class="register_message"  label-width="100px" ref="ruleformstaff" :rules="rulesstaff" >
            <div class="middle_top">
              <div class="middle_left">
                <el-form-item class="elformitem" label="员工姓名" prop="ruleForm.staff.staffname">
                  <el-input  v-model="ruleForm.staff.staffname"  size="mini"></el-input>
                </el-form-item>
                <el-form-item label="员工性别" prop="ruleForm.staff.staffsex">
                  <el-radio-group v-model="ruleForm.staff.staffsex">
                    <el-radio :label="1">男</el-radio>
                    <el-radio :label="2">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="员工电话" prop="ruleForm.staff.stafftel">
                  <el-input v-model="ruleForm.staff.stafftel" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="员工邮件" prop="ruleForm.staff.staffemail">
                  <el-input v-model="ruleForm.staff.staffemail" size="mini"></el-input>
                </el-form-item>
              </div>
              <div class="middle_right">
                <el-form-item prop="ruleForm.staff.staffimageUrl">
                  <el-upload
                    class="avatar-uploader"
                    list-type="picture-card"
                    name="file"
                    action="http://localhost:8088/upload/uploadimg"
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
                <el-form-item class="bottomleft"  label="身份证号" prop="ruleForm.staff.staffidcard">
                  <el-input v-model="ruleForm.staff.staffidcard" size="mini"></el-input>
                </el-form-item>
                <el-form-item class="bottomright" label="用户名" prop="ruleForm.staff.staffloginame">
                  <el-input  v-model="ruleForm.staff.staffloginame"  size="mini"> </el-input>
                </el-form-item>
              </div>
              <div class="bottomcenter">
                <el-form-item class="bottomcenterleft" label="入职时间" prop="ruleForm.staff.staffregtime">
                  <el-date-picker
                    class="datetime"
                    v-model="ruleForm.staff.staffregtime"
                    size="mini"
                    type="date"
                    @change="formatTimeStaff"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
                <el-form-item  class="pass" label="密码" prop="ruleForm.staff.staffpass">
                  <el-input v-model="ruleForm.staff.staffpass" size="mini" show-password></el-input>
                </el-form-item>
              </div>
            </div>
            <div class="btn_bottom">
              <el-form-item>
                <el-button class="btn" size="small" type="primary" @click="onsubmitstaff()">注册</el-button>
                <el-button class="btn" size="small" plain>重置</el-button>
                <el-button class="btn" size="small" plain>取消</el-button>
              </el-form-item>
            </div>
          </el-form>
        </div>

        <!--后台管理员模块-->
        <div class="middle" v-if="showadministratormessage">
          <el-form  class="register_message"  label-width="100px" ref="ruleformadmin" :rules="rulesadmin" >
            <div class="middle_top">
              <div class="middle_left">
                <el-form-item class="elformitem" label="管理员姓名" prop="adminname">
                  <el-input  v-model="ruleForm.admin.adminname"  size="mini"></el-input>
                </el-form-item>
                <el-form-item label="管理员性别" prop="ruleForm.admin.adminsex">
                  <el-radio-group v-model="ruleForm.admin.adminsex" @change="adminchangesex">
                    <el-radio :label="1">男</el-radio>
                    <el-radio :label="2">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="管理员电话" prop="ruleForm.admin.admintel">
                  <el-input v-model="ruleForm.admin.admintel" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="管理员邮件" prop="ruleForm.admin.adminemail">
                  <el-input v-model="ruleForm.admin.adminemail" size="mini"></el-input>
                </el-form-item>
              </div>
              <div class="middle_right">
                <el-form-item prop="ruleForm.admin.adminimageUrl">
                  <el-upload
                    class="avatar-uploader"
                    list-type="picture-card"
                    ref="adminuploadvideo"
                    action="''"
                    :before-upload="newVideo"
                    :on-change="onchangeadmin"
                    accept=".mp4"
                    :auto-upload=false
                    :show-file-list="false"
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
                <el-form-item class="bottomleft"  label="身份证号" prop="ruleForm.admin.adminidcard">
                  <el-input v-model="ruleForm.admin.adminidcard" size="mini"></el-input>
                </el-form-item>
                <el-form-item class="bottomright" label="用户名" prop="ruleForm.admin.adminloginame">
                  <el-input  v-model="ruleForm.admin.adminloginame"  size="mini"> </el-input>
                </el-form-item>
              </div>
              <div class="bottomcenter">
                <el-form-item class="bottomcenterleft" label="入职时间" prop="ruleForm.admin.adminregtime">
                  <el-date-picker
                    class="datetime"
                    v-model="ruleForm.admin.adminregtime"
                    size="mini"
                    type="date"
                    @change="formatTimeAdmin"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
                <el-form-item  class="pass" label="密码" prop="ruleForm.admin.adminpass">
                  <el-input v-model="ruleForm.admin.adminpass" size="mini" show-password></el-input>
                </el-form-item>
              </div>
            </div>
            <div class="btn_bottom">
              <el-form-item>
                <el-button class="btn" size="small" type="primary" @click="onSubmitadmin()">注册</el-button>
                <el-button class="btn" size="small" plain @>重置</el-button>
                <el-button class="btn" size="small" plain>取消</el-button>
              </el-form-item>
            </div>
          </el-form>
        </div>
        <!--结束-->
        <div class="end"></div>
      </el-form>
    </div>
  </div>

</template>

<script>
  export default {
    data() {
      return {
        editor:[{
          picture:'',
        }],
        fileList: [],
        cascaderAddr:'',
        datared:false,
        datablue:false,
        'showusermessage':true,
        'showstaffmessage':false,
        'showadministratormessage':false,
        /* selectedOptions:[],*/
        options: [{
          value: '用户',
          label: '用户'
        }, {
          value: '物业管理员',
          label: '物业管理员'
        }, {
          value: '后台管理员',
          label: '后台管理员'
        }],
        value: '',
        /* paramuser:{}也对，加上 this.paramuser=new FormData(), */
        paramuser:new FormData(),
        paramadmin:new FormData(),
        userimageUrl:'',
        staffimageUrl:'',
        adminimageUrl:'',
        ruleForm: {
          userprop:[],
          user:[{
            username:'',
            usersex:'',
            userimageUrl:'',
            useridcard:'',
            usertel:'',
            useremail:'',
            userdoor:'',
            useristenant:'',
            userloginame:'',
            userpass:'',
            useregtime:'',
          }],
          staff:[{
            staffname:'',
            staffsex:'',
            staffimageUrl:'',
            staffidcard:'',
            stafftel:'',
            staffemail:'',
            staffloginame:'',
            staffpass:'',
            staffregtime:''
          }],
          admin:[{
            adminname:'',
            adminsex:'',
            adminimageUrl:'',
            adminidcard:'',
            admintel:'',
            adminemail:'',
            adminloginame:'',
            adminpass:'',
            adminregtime:''
          }]
        },
        rulesAuthority:{
          userprop:[
            { required: true, message: '用户权限不能为空', trigger: 'blur' },
          ]
        },
        rulesuser: {
          username: [
            { required: true, message: '用户名字不能为空', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          userpass: [
            { required: true, message: '用户姓名不能为空', trigger: 'blur' },
            { min: 6,message: '最少6位数', trigger: 'change' }
          ],
        },
        rulesstaff:{

        },
        rulesadmin:{

        },
      };
    },
    methods: {
      handleChange(value){
        /*console.log(value[0]);
         value[0]=user
         value[1]=yezhu或zuhu*/
        if(value=='用户'){
          this.showusermessage=true;
          this.showstaffmessage=false;
          this.showadministratormessage=false;
          this.ruleForm.userprop=value;
          /*
          this.selectedOptions[0]=user,
          this.selectedOptions[1]=yezhu
          */
        }
        if(value=='物业管理员'){
          this.showusermessage=false;
          this.showstaffmessage=true;
          this.showadministratormessage=false;
          this.ruleForm.userprop=value;
        }
        if(value=='后台管理员'){
          this.showusermessage=false;
          this.showstaffmessage=false;
          this.showadministratormessage=true;
          this.ruleForm.userprop=value;
        }
        /*console.log(this.ruleForm.userprop[0]);*/
      },
      /*用户*/
      formatTimeUser(time){
        this.ruleForm.user.userregtime=time;
        console.log(time)
      },
      /* handleSuccessuser(res, file) {
         this.$message({
           type: 'info',
           message: '图片上传成功',
           duration: 6000
         });
         if (file.response.success) {
           this.ruleForm.user.userimageUrl = file.response.message; //将返回的文件储存路径赋值picture字段
         }
       },*/
      userchangesex(value){
        /*console.log(value);*/
        this.ruleForm.user.usersex=value;
      },
      userchangeTenantorYezhu(value){
        this.ruleForm.user.useristenant=value;
        console.log(value);
      },
      onchangeuser(file,filesList){
        //创建临时的路径来展示图片
        var windowURL = window.URL || window.webkitURL;
        this.userimageUrl=windowURL.createObjectURL(file.raw);
        console.log(file);
        //重新写一个表单上传的方法
        this.paramuser.append('file', file.raw,file.name);
        /* this.paramuser.append('userimg', file.name);*/
        /* console.log(file.name);*/
      },
      onSubmituser(){
        this.$refs.ruleformvalidate.validate((valid) => {
          if (valid) {
            this.paramuser.append('username', this.ruleForm.user.username);
            this.paramuser.append('usersex', this.ruleForm.user.usersex);
            this.paramuser.append('useridcard', this.ruleForm.user.useridcard);
            this.paramuser.append('usertel', this.ruleForm.user.usertel);
            this.paramuser.append('useremail', this.ruleForm.user.useremail);
            this.paramuser.append('userdoor', this.ruleForm.user.userdoor);
            this.paramuser.append('useristenant', this.ruleForm.user.useristenant);
            this.paramuser.append('userloginame', this.ruleForm.user.userloginame);
            this.paramuser.append('userpass', this.ruleForm.user.userpass);
            this.paramuser.append('userprop', this.ruleForm.userprop);
            this.paramuser.append('useregtime', this.ruleForm.user.useregtime);
            //然后通过下面的方式把内容通过axios来传到后台
            //下面的this.uploadFileRequest 是在主js中通过Vue.prototype.uploadFileRequest = uploadFileRequest 来把axios赋给它;
            if(this.ruleForm.user.username!=''){
              this.uploadFileRequest("/upload/singlefile", this.paramuser).then(function(response){
                console.log(response);
                /*response.data[1].userName*/
              })
            }
            else{
              console.log('用户姓名不能为空');
            }
          } else {
            console.log('请检查是否填写正确');
            return false;
          }
        });

      },
      resetFormuser(){
        /*this.$refs.ruleformuser.resetFields();*/
        this.ruleForm.user.username='';
        this.ruleForm.user.usersex='';
        this.userimageUrl=null;
        this.ruleForm.user.userimageUrl=null;
        this.ruleForm.user.useridcard='';
        this.ruleForm.user.usertel='';
        this.ruleForm.user.useremail='';
        this.ruleForm.user.userdoor='';
        this.ruleForm.user.useristenant='';
        this.ruleForm.user.userloginame='';
        this.ruleForm.user.userpass='';
        this.ruleForm.user.useregtime='';
      },
      /*handleAvatarSuccessuser(res, file) {
        this.userimageUrl = URL.createObjectURL(file.raw);
      },*/
      /*员工*/

      formatTimeStaff(time){
        this.ruleForm.staff.staffregtime=time;
        console.log(time)
      },
      staffchangesex(value){
        this.ruleForm.staff.staffsex=value;
        console.log( this.ruleForm.staff.staffsex);
      },
      handleAvatarSuccessstaff(res, file,filesList) {
        this.staffimageUrl = URL.createObjectURL(file.raw);
        console.log( this.staffimageUrl)
        /*console.log( filesList);*/
        this.ruleForm.staff.staffimageUrl = res; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.staff.staffimageUrl);
      },
      onsubmitstaff(){
        var that=this;
        this.$refs.ruleformvalidate.validate((valid) => {
          if (valid) {
            var that=this;
            this.postRequest("http://localhost:8088//user/savestaff",{
              staffname:this.ruleForm.staff.staffname,
              staffsex: this.ruleForm.staff.staffsex,
              staffimg: this.ruleForm.staff.staffimageUrl,
              staffidcard:this.ruleForm.staff.staffidcard,
              stafftel:this.ruleForm.staff.stafftel,
              staffemail:this.ruleForm.staff.staffemail,
              staffloginame: this.ruleForm.staff.staffloginame,
              staffpass: this.ruleForm.staff.staffpass,
              staffprop: this.ruleForm.userprop,
              staffregtime: this.ruleForm.staff.staffregtime
            }).then(response=>{
              if (response && response.status == 200) {
                this.$router.push({
                  path:'/login'
                });
              }
              console.log(response);
              /*response.data[1].userName*/
            })
          }
          else {
            console.log('请检查是否填写正确');
            return false;
          }
        });
      },

      /*后台*/
      formatTimeAdmin(time){
        this.ruleForm.admin.adminregtime=time;
        console.log(time)
      },
      /* handleAvatarSuccessadmin(res, file) {
         this.adminimageUrl = URL.createObjectURL(file.raw);
       },*/
      adminchangesex(value){
        this.ruleForm.admin.adminsex=value;
      },
      onchangeadmin(file,filesList){
        var windowURL = window.URL || window.webkitURL;
        this.adminimageUrl = URL.createObjectURL(file.raw);
      },
      newVideo (file) {
        this.paramadmin.append('file', file);
        return false;
      },
      onSubmitadmin(){
        this.$refs.ruleformvalidate.validate((valid) => {
          if (valid){
            var name=this.ruleForm.admin.adminname;
            var sex=this.ruleForm.admin.adminsex;
            this.paramadmin.append("username",name);
            this.paramadmin.append("usersex",sex);
            this.uploadVideoRequest("/upload/uploadimg", this.paramadmin).then(function(response){
              console.log(response);
            })
            this.$refs.adminuploadvideo.submit();
          }
          else {
            console.log('error submit!!')
            return false
          }
        })
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
  .blue{
    background: #d9d9d9;
  }
</style>

