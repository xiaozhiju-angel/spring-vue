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
        <td  class="itemgutter" >{{item.staffId}}</td>
        <td  class="itemgutter" >{{item.staffName}}</td>
        <td  class="itemgutter" >{{item.staffSex}}</td>
        <td  class="itemgutter" >{{item.staffIdcard}}</td>
        <td  class="itemgutter" >{{item.staffTel}}</td>
        <td  class="itemgutter" >{{item.staffEmail}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)" >编辑</el-button></td>
      </tr>
      </tbody>
    </table>

    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 16px;margin-bottom: 20px;text-align: center">个人信息</div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleformstaff" :model="ruleForm" :rules="rulestaff">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item class="elformitem" label="姓名" prop="staffname">
                <el-input class="info" v-model="ruleForm.staffname"  size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="staffsex">
                <el-radio-group v-model="ruleForm.staffsex" @change="staffchangesex">
                  <el-radio :label="'男'">男</el-radio>
                  <el-radio :label="'女'">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="电话号码" prop="stafftel">
                <el-input v-model="ruleForm.stafftel" size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="电子邮箱" prop="staffemail">
                <el-input v-model="ruleForm.staffemail" size="mini" clearable></el-input>
              </el-form-item>
            </div>
            <div class="middle_right">
              <div> <img  class="img" :src="host+'/'+ruleForm.staffimageUrl" alt=""/></div>
              <div class="userimg">用户头像</div>
            </div>
          </div>
          <div class="middle_bottom">
            <div class="bottomtop">
              <el-form-item class="bottomleft"  label="身份证号" prop="staffidcard">
                <el-input v-model="ruleForm.staffidcard" size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item class="bottomright" label="用户名" prop="staffloginame">
                <el-input v-model="ruleForm.staffloginame" size="mini" clearable></el-input>
              </el-form-item>
            </div>
            <div class="bottomcenter">
              <el-form-item class="bottomcenterleft" label="入职时间" prop="staffregtime">
                <el-date-picker
                  disabled
                  class="datetime"
                  v-model="ruleForm.staffregtime"
                  size="mini"
                  type="date"
                  @change="formatTimeStaff"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item class="bottomcenterright" label="密码" prop="staffpass">
                <el-input v-model="ruleForm.staffpass" show-password clearable size="mini"></el-input>
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateinfo()"  size="mini">更 新</el-button>
        <el-button   @click="dialogFormVisible = false"  size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        dialogFormVisible:false,
        host:'http://localhost:8088/we',
        fileList: [],
        petimageUrl: '',
        ruleForm: {
          staffId:'',
          staffname: '',
          staffsex: '',
          staffimageUrl: '',
          staffidcard: '',
          stafftel: '',
          staffemail: '',
          staffloginame: '',
          staffpass: '',
          staffregtime: '',
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
          stafftel:[
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
      }
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
      staffid:{
        default: "staffid"
      }
    },
    methods: {
      staffchangesex(value){
        /*console.log(value);*/
        this.ruleForm.staffsex=value;
        console.log(this.ruleForm.staffsex);
      },
      formatTimeStaff(time){
        this.ruleForm.staffregtime=time;
        console.log(time);
      },
      edit(index) {
        this.dialogFormVisible = true;
        var _this = this;
        this.getRequest("/admin/getStaffInfoById?index=" + this.bodyInfoList[index].staffId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            _this.ruleForm.staffid=_this.ruleForm[0][0].staffId;
            _this.ruleForm.staffname=_this.ruleForm[0][0].staffName;
            _this.ruleForm.staffsex=_this.ruleForm[0][0].staffSex;
            _this.ruleForm.staffidcard=_this.ruleForm[0][0].staffIdcard;
            _this.ruleForm.stafftel=_this.ruleForm[0][0].staffTel;
            _this.ruleForm.staffemail=_this.ruleForm[0][0].staffEmail;
            _this.ruleForm.staffloginame=_this.ruleForm[0][0].staffLoginame;
            _this.ruleForm.staffpass=_this.ruleForm[0][0].staffPass;
            _this.ruleForm.staffimageUrl=_this.ruleForm[0][0].staffImg;
            _this.ruleForm.staffregtime=_this.ruleForm[0][0].staffRegtime;
          }
        })

      },
      updateinfo(){
        var that=this;
        this.$refs.ruleformstaff.validate((valid) => {
          if (valid) {
            var that=this;
            this.putRequest("/admin/updatestaff",{
              staffid: this.ruleForm.staffid,
              staffname:this.ruleForm.staffname,
              staffsex: this.ruleForm.staffsex,
              staffimg: this.ruleForm.staffimageUrl,
              staffidcard:this.ruleForm.staffidcard,
              stafftel:this.ruleForm.stafftel,
              staffemail:this.ruleForm.staffemail,
              staffloginame: this.ruleForm.staffloginame,
              staffpass: this.ruleForm.staffpass,
              staffregtime: this.ruleForm.staffregtime
            }).then(response=>{
              if (response.data.updatestaff) {
                that.initData();
                alert("更新成功!");
                that.dialogFormVisible=false;
              }
              if(response.data.repeat){
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
      reset() {
        this.$refs.ruleformstaff.resetFields();
      },
      initData(){
        var _this = this;
        var m=this.$store.state.staffid;
        console.log(this.staffid);
        this.getRequest("/self/getStaffSelfInfo?info=" + this.staffid).then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
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
  @import "../../../../static/css/dialog/addadminself.css";
  .itemgutter{
    padding: 30px;
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

