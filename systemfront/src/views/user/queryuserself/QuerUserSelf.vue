<template>
  <div>
    <table class="table table-hover" style="table-layout: fixed;">
      <thead>
      <tr>
        <!-- 循环出表头，用英文的逗号拆分字串 -->
        <th class="title" v-for="(item,index) in headerList.split(',')" :key="index">{{item}}</th>
      </tr>
      </thead>
      <tbody>
      <!-- 循环出有多少行数据，即 list 中有多少条数据，得到 list 中的每个元素 -->
      <!-- <tr v-for="(item,index) in bodyInfoList" :key="index">
         &lt;!&ndash; 循环取到元素的每个属性，并展示 &ndash;&gt;
         <td  class="itemgutter" v-for="(val,index) in item" :key="index">{{val}}</td>
       </tr>-->
      <!--headerList: "ID,姓名,性别,身份证号,电话,邮箱,门牌号,是否租户,用户名,密码",-->
      <!-- 循环取到元素的每个属性，并展示 -->

      <tr v-for="(item,index) in bodyInfoList" :key="index" border>
        <td  class="itemgutter" ><el-checkbox></el-checkbox></td>
        <td  class="itemgutter" >{{item.userId}}</td>
        <td  class="itemgutter" >{{item.userName}}</td>
        <td  class="itemgutter" >{{item.userSex}}</td>
        <td  class="itemgutter" >{{item.userIdcard}}</td>
        <td  class="itemgutter" >{{item.userTel}}</td>
        <td  class="itemgutter" >{{item.userEmail}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)" >编辑</el-button></td>
      </tr>
      </tbody>
    </table>


    <el-dialog  class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 16px;margin-bottom: 20px;text-align: center">个人信息</div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleformuser" :model="ruleForm" :rules="rulesuser">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item class="elformitem" label="姓名" prop="username">
                <el-input class="info" v-model="ruleForm.username"  size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="usersex">
                <el-radio-group v-model="ruleForm.usersex" @change="userchangesex">
                  <el-radio :label="'男'">男</el-radio>
                  <el-radio :label="'女'">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="电话号码" prop="usertel">
                <el-input v-model="ruleForm.usertel" size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="电子邮箱" prop="useremail">
                <el-input v-model="ruleForm.useremail" size="mini" clearable></el-input>
              </el-form-item>
            </div>
            <div class="middle_right">
              <div>    <img  class="img" :src="host+'/'+ruleForm.userimageUrl" alt=""/></div>
              <!-- <img  class="img" :src="ruleForm.userimageUrl" alt="未上传"/>-->
              <el-button class="userimg" size="mini">用户头像</el-button>
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
                <el-input v-model="ruleForm.useridcard" size="mini" clearable disabled></el-input>
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
                  disabled
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
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateinfo()" size="mini">更 新</el-button>
        <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        host:'http://localhost:8088/we',
        dialogFormVisible:false,
        fileList: [],
        userimageUrl: '',
        options: [{
          value: '业主',
          label: '业主'
        }],
        ruleForm: {
          userprop:'业主',
          userid:'',
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
          useremail: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
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
    components:{

    },
    props: {
      headerList: {
        type: String, // 亦可接收 Object 类型参数
        default: "headerList"
      },
      bodyInfoList: {
        type: Array,
        default: "bodyInfoList"
      },
      userid:{
        default: "userid"
      }
    },
    methods: {
      edit(index){
        this.dialogFormVisible=true;
        var _this = this;
        /* console.log(this.bodyInfoList[index].userId);*/
        this.getRequest("/admin/getUserInfoById?index=" + this.bodyInfoList[index].userId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
            _this.ruleForm.userid=_this.ruleForm[0][0].userId;
            _this.ruleForm.username=_this.ruleForm[0][0].userName;
            _this.ruleForm.usersex=_this.ruleForm[0][0].userSex;
            _this.ruleForm.useridcard=_this.ruleForm[0][0].userIdcard;
            _this.ruleForm.usertel=_this.ruleForm[0][0].userTel;
            _this.ruleForm.useremail=_this.ruleForm[0][0].userEmail;
            _this.ruleForm.userdoor=_this.ruleForm[0][0].userDoor;
            _this.ruleForm.useristenant=_this.ruleForm[0][0].userisTenant;
            _this.ruleForm.userloginame=_this.ruleForm[0][0].userLoginame;
            _this.ruleForm.userpass=_this.ruleForm[0][0].userPass;
            _this.ruleForm.userimageUrl=_this.ruleForm[0][0].userImg;
            _this.ruleForm.useregtime=_this.ruleForm[0][0].userRegtime;
          }
        })
      },
      updateinfo(){
        var that=this;
        this.$refs.ruleformuser.validate((valid) => {
          if (valid) {
            var that=this;
            this.putRequest("/admin/updateuser",{
              userid:this.ruleForm.userid,
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
              useregtime: this.ruleForm.useregtime
            }).then(response=>{
              if (response.data.updateuser) {
                that.initData();
                alert("更新成功!");
                that.dialogFormVisible=false;
              }
              if (response.data.repeat){
                alert(response.data.data);
              }
            })
          }
          else {
            console.log('请检查是否填写正确');
            return false;
          }
        });
      },
      reset(){
        this.$refs.ruleformuser.resetFields();
      },
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
      resetFormuser(){
        this.$refs.ruleformuser.resetFields();
        this.userimageUrl='';
      },

      initData(){
        var _this = this;
        this.getRequest("/self/getUserSelfInfo?info=" + this.userid).then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      } ,
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
  @import '../../../../static/css/dialog/adduserself.css';
  .itemgutter{
    padding: 20px;
  }
  .title{
    padding-top:20px;
    padding-bottom: 20px;
  }
  .opera{
    padding: 20px 8px 20px 20px;
  }
  td {
    white-space:nowrap;
    overflow:hidden;
    text-overflow: ellipsis;
  }
</style>

