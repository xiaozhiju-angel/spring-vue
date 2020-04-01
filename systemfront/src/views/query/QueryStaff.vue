<template >
  <div class="message">
      <el-container>
        <el-header class="keywordsearch">
          <div style="display:inline">
            <el-input
              class="search"
              placeholder="编号、电话、身份证号、邮箱……"
              clearable
              @change="keywordsChange"
              size="mini"
              @keyup.enter.native="searchUserInfo"
              prefix-icon="el-icon-search"
              v-model="keywords">
            </el-input>
            <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchUserInfo">搜索
            </el-button>
          </div>
        </el-header>
      </el-container>
      <el-table :data="Data" class="title" ref="multipleTable"  tooltip-effect="dark"  height="500"
                 @selection-change="handleSelectionChange" stripe  >
        <el-table-column  align="center" type="selection" width="55" > </el-table-column>
        <el-table-column  align="center" min-width  :label="index"   v-for="(item,index) in Data[0]" :key="index"  show-overflow-tooltip >
          <template slot-scope="scope" width="260px">{{ scope.row[index] }}</template>
        </el-table-column>
        <el-table-column  align="center" label="操作"  width="170px">
          <template slot-scope="scope">
            <el-button size="mini"  @click="handleEdit(scope.$index, scope.row)" >编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

       <el-dialog  class="dialog"  :visible.sync="dialogFormVisible">
         <div style="color: #bc2a19;font-size: 14px;margin-bottom: 30px;text-align: center">
           {{ruleForm.staffname}}<span style="color: dodgerblue">的基本资料</span></div>
         <div class="middle">
           <el-form :model="ruleForm"   ref="ruleformstaff" label-width="80px"  :rules="rulestaff">
             <div class="middle_top">
               <div class="middle_left">
                 <el-form-item  label="员工ID" prop="staffid">
                   <el-input  v-model="ruleForm.staffid"  size="mini" clearable disabled></el-input>
                 </el-form-item>
                 <el-form-item  label="员工姓名" prop="staffname">
                   <el-input  v-model="ruleForm.staffname"  size="mini" clearable></el-input>
                 </el-form-item>
                 <el-form-item label="员工性别" prop="staffsex">
                   <el-radio-group v-model="ruleForm.staffsex" @change="staffchangesex">
                     <el-radio :label="'男'">男</el-radio>
                     <el-radio :label="'女'">女</el-radio>
                   </el-radio-group>
                 </el-form-item>
               </div>
               <div class="middle_right">
                 <img  class="avatar" :src="host+'/'+ruleForm.staffimageUrl" alt=""/>
                 <el-button class="userimg" size="mini">员工头像</el-button>
               </div>
             </div>
             <div class="middle_bottom">
               <div class="bottomtop">
                 <el-form-item class="bottomleft" label="员工电话" prop="stafftel">
                   <el-input    v-model="ruleForm.stafftel" size="mini" clearable></el-input>
                 </el-form-item>
                 <el-form-item class="bottomright" label="员工邮箱" prop="staffemail">
                   <el-input v-model="ruleForm.staffemail" size="mini" clearable></el-input>
                 </el-form-item>
               </div>
               <div class="bottomcenter">
                 <el-form-item class="left" label="用户名" prop="staffloginame">
                   <el-input v-model="ruleForm.staffloginame" size="mini" clearable></el-input>
                 </el-form-item>
                 <el-form-item class="right" label="密码" prop="staffpass">
                   <el-input v-model="ruleForm.staffpass" show-password clearable size="mini"></el-input>
                 </el-form-item>
               </div>
               <div class="bottombottom">
                 <el-form-item class="left"  label="身份证号"  >
                   <el-input v-model="ruleForm.staffidcard" size="mini" clearable disabled></el-input>
                 </el-form-item>
                 <el-form-item class="right" label="入职时间" >
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
               </div>
             </div>
           </el-form>
         </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="updateinfo()"  size="mini">更 新</el-button>
          <el-button type="plain" @click="reset()"  size="mini">重 置</el-button>
          <el-button   @click="dialogFormVisible = false"  size="mini">取 消</el-button>
        </div>
      </el-dialog>
  </div>
</template>
<script>
  /*import store from '../../store/store.js'*/
  export default {
    data() {
      return {
        keywords:'',
        id:'',
        host:'http://localhost:8088/we',
        multipleSelection: [],
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
        dialogFormVisible: false,
        Data:[],
        multipleSelection:[]
      }
    },
    components:{

    },
    methods: {
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteMany(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDelete(ids);
        }).catch(() => {
        });
      },
      doDelete(ids){
        var _this = this;
        this.deleteRequest("/admin/delete?ids=" + ids).then(resp=> {
          if (resp.data.delete) {
            var data = resp.data;
            _this.initData();
          }
        })
      },
      see(e){
       window.location.href= e;
      },
       staffchangesex(value){
        /*console.log(value);*/
        this.ruleForm.staffsex=value;
        console.log(this.ruleForm.staffsex);
      },
      formatTimeStaff(time){
        this.ruleForm.staffregtime=time;
        console.log(time);
      },
      keywordsChange(val){
        if(val=='')
          this.initData();
        this.keywords=val;
        console.log(val);
      },
     /* searchStaffByenter(){
        var _this = this;
        this.getRequest("/admin/getStaffInfoBykeywords?info=" + this.keywords).then(resp=> {
          if (resp.data.getinfo) {
            _this.Data = resp.data.info;
          }
        })
      },*/
      searchUserInfo(){
        var _this = this;
        this.getRequest("/admin/getStaffInfoBykeywords?info=" + this.keywords).then(resp=> {
          if (resp.data.getinfo) {
            _this.Data = resp.data.info;
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
        console.log( this.multipleSelection.length);
      },
      handleEdit(index, row) {
        this.dialogFormVisible=true;
        var _this = this;
        this.getRequest("/admin/getStaffInfoById?index=" + row.staffId).then(resp=> {
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
      /*可用dateFormat（row.staffLoginame）转换成date类型*/
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

      updateinfo(){
          var that=this;
          this.$refs.ruleformstaff.validate((valid) => {
            if (valid) {
              var that=this;
              this.putRequest("/admin/updatestaff",{
                staffid:this.ruleForm.staffid,
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

      reset(){
        this.$refs.ruleformstaff.resetFields();
      },
      handleDelete(index, row) {
        var _this = this;
        console.log(index)
        this.$confirm('此操作将永久删除员工[' + row.staffName + ']的信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.DeleteStaff(row.staffId);
        }).catch(() => {
        });
      },
      DeleteStaff(ids){
         var _this = this;
         this.deleteRequest("/admin/deleteStaff?ids="+ids).then(resp=> {
            if (resp.data.delete) {
              alert(resp.data.back);
              _this.initData();
              console.log(resp.data.data)
            }
          })
      },
      initData(){
        var _this = this;
        this.getRequest("/admin/getStaffInfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.Data = resp.data.staffinfo;
            /*_this.ruleForm=resp.data.staffinfo;*/
          }
          else{

          }
        })
      },

    },
    mounted(){
         this.initData();
    }
  }
</script>
<style>
  @import "../../../static/css/admin/querystaff.css";
  @import "../../../static/css/dialog/querystaff.css";
</style>
