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
         <td  class="itemgutter" v-for="(val,index) in item" :key="index">{{val}}</td>
       </tr>-->
      <!--headerList: "ID,姓名,性别,身份证号,电话,邮箱,门牌号,是否租户,用户名,密码",-->

      <tr v-for="(item,index) in bodyInfoList" :key="index" border>
        <td  class="itemgutter" ><el-checkbox></el-checkbox></td>
        <td  class="itemgutter" >{{item.adminId}}</td>
        <td  class="itemgutter" >{{item.adminName}}</td>
        <td  class="itemgutter" >{{item.adminIdcard}}</td>
        <td  class="itemgutter" >{{item.adminTel}}</td>
        <td  class="itemgutter" >{{item.adminEmail}}</td>
        <td  class="itemgutter" >{{item.adminLoginame}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)">编辑</el-button></td>
        <td  class="del"><el-button type="danger"  size="mini" @click="clear(index)">删除</el-button></td>
      </tr>
      </tbody>
    </table>
    <!--<div v-if="showEdit">
      <edit-table></edit-table>
    </div>-->

    <el-dialog  class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #bc2a19;font-size: 14px;margin-bottom: 30px;text-align: center">
        {{ruleForm.adminame}}<span style="color: dodgerblue">的基本资料</span></div>
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

              <div>
                <img  :src="host+'/'+ruleForm.adminimageUrl" class="img" >
              </div>
                <el-button class="imgdes" size="mini" type="plain">管理员头像</el-button>
              </el-form-item>
            </div>
          </div>
          <div class="middle_bottom">
            <div class="bottomtop">
              <el-form-item class="bottomleft"  label="身份证号">
                <el-input v-model="ruleForm.adminidcard" size="mini" disabled></el-input>
              </el-form-item>
              <el-form-item class="bottomright" label="用户名" prop="adminloginame">
                <el-input  v-model="ruleForm.adminloginame"  size="mini"> </el-input>
              </el-form-item>
            </div>
            <div class="bottomcenter">
              <el-form-item class="bottomcenterleft" label="入职时间" >
                <el-date-picker
                  disabled
                  class="datetime"
                  v-model="ruleForm.adminregtime"
                  size="mini"
                  type="date"
                  @change="formatTimeAdmin"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item  class="bottomcenterright" label="密码" prop="adminpass">
                <el-input v-model="ruleForm.adminpass" size="mini" show-password></el-input>
              </el-form-item>
            </div>

          </div>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateinfo()" size="mini">更 新</el-button>
        <el-button  @click="reset()"  plain size="mini">重 置</el-button>
        <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        fileList: [],
        host:'http://localhost:8088/we',
        dialogFormVisible:false,
        adminimageUrl:'',
        options: [{
          value: '后台管理员',
          label: '后台管理员'
        }],
        ruleForm: {
          adminprop:'后台管理员',
          adminid:'',
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
          admintel: [
            {required: true, message: '必填:手机号码', trigger: 'blur'},
            {min: 11, message: '最少11位数', trigger: 'blur'},
            {validator: this.validator.phoneNumber, trigger: 'blur'}
          ],
          adminloginame:[{ required: true, message: '必填:用户名', trigger: 'blur' }],
          adminpass: [
            {required: true, message: '必填:密码', trigger: 'blur'},
            {min: 6, message: '最少6位数', trigger: 'blur'},
            {validator: this.validator.enOrnunText, trigger: 'blur'}
          ],

        },
      };
    },
    props: {
      headerList: {
        type: String, // 亦可接收 Object 类型参数
        default: "headerList"
      },
      bodyInfoList: {
        type: Array,
        default: "bodyInfoList"
      }
    },
    methods: {
      edit(index){
        this.dialogFormVisible=true;
        var _this = this;
        /* console.log(this.bodyInfoList[index].userId);*/
        this.getRequest("/admin/getAdminInfoById?index=" + this.bodyInfoList[index].adminId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
              _this.ruleForm.adminid=_this.ruleForm[0][0].adminId;
            _this.ruleForm.adminame=_this.ruleForm[0][0].adminName;
            _this.ruleForm.adminsex=_this.ruleForm[0][0].adminSex;
            _this.ruleForm.adminidcard=_this.ruleForm[0][0].adminIdcard;
            _this.ruleForm.admintel=_this.ruleForm[0][0].adminTel;
            _this.ruleForm.adminemail=_this.ruleForm[0][0].adminEmail;
            _this.ruleForm.adminloginame=_this.ruleForm[0][0].adminLoginame;
            _this.ruleForm.adminpass=_this.ruleForm[0][0].adminPass;
            _this.ruleForm.adminimageUrl=_this.ruleForm[0][0].adminImg;
            _this.ruleForm.adminregtime=_this.ruleForm[0][0].adminRegtime;
          }
        })
      },
      clear(index){
        var _this = this;
        console.log(index)
       /* console.log(this.bodyInfoList[index].adminId);*/
        this.$confirm('此操作将永久删除业主[' + this.bodyInfoList[index].adminName + ']的信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/admin/deleteAdmin?id="+this.bodyInfoList[index].adminId).then(resp=> {
            console.log("no");
            if (resp.data.delete) {
              alert(resp.data.back);
              _this.initData();
              console.log(resp.data.data)
            }
          })
        }).catch(() => {
        });
      },
      updateinfo(){
        var that=this;
        this.$refs.ruleformadmin.validate((valid) => {
          if (valid) {
            var that=this;
            this.putRequest("/admin/updateadmin",{
              adminid:this.ruleForm.adminid,
              adminame:this.ruleForm.adminame,
              adminsex: this.ruleForm.adminsex,
              adminidcard:this.ruleForm.adminidcard,
              admintel:this.ruleForm.admintel,
              adminemail:this.ruleForm.adminemail,
              adminloginame:this.ruleForm.adminloginame,
              adminpass: this.ruleForm.adminpass,
              adminimg:this.ruleForm.adminimageUrl,
              adminregtime: this.ruleForm.adminregtime

            }).then(response=>{
              if (response.data.updateadmin) {
                that.initData();
                alert(response.data.data);
                that.dialogFormVisible=false;
              }
              else{
                alert(response.data.err);
              }
            })
          }
          else {
            console.log('请检查是否填写正确');
            return false;
          }
        });
      },


      /*后台*/
     /* handleChange(value){
        this.ruleForm.adminprop=value;
        console.log(this.ruleForm.adminprop);
      },*/
      formatTimeAdmin(time){
        this.ruleForm.adminregtime=time;
        console.log(time)
      },
      adminchangesex(value){
        this.ruleForm.adminsex=value;
        console.log( this.ruleForm.adminsex);
      },
      /*handleAvatarSuccessadmin(res, file,filesList) {
        this.adminimageUrl = URL.createObjectURL(file.raw);
        console.log( this.adminimageUrl)
        console.log( filesList);
        this.ruleForm.adminimageUrl = res.filename; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.adminimageUrl);
      },*/
      initData() {
        var _this = this;
        this.getRequest("/admin/getAdminInfo").then(resp => {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.admininfo;
          }
        })
      },
      reset(){
        this.$refs.ruleformadmin.resetFields();
        this.ruleForm.adminpass="";
        this.ruleForm.adminloginame="";

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
  @import '../../../static/css/dialog/addadminself.css';
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

