<template>
  <div class="total">
  <div class="Infodetaile" >
    <el-form :model="ruleForm" label-width="80px" ref="ruleformstaff">
      <div class="top">
        <div class="header">
          <el-form-item  class="info" label="员工姓名" prop="staffname">
            <el-input  v-model="ruleForm.staffname"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="info" label="员工性别" prop="staffsex">
            <el-input  v-model="ruleForm.staffsex"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item  class="info" label="员工电话" prop="stafftel">
            <el-input  v-model="ruleForm.stafftel" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="info" label="员工邮箱" prop="staffemail">
            <el-input v-model="ruleForm.staffemail" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="picture">
          <!--:src="ruleForm.staffimageUrl"-->
          <div><img  src="../../../static/petimg/034.jpg" alt="未上传照片"/></div>
          <div class="userimg">员工头像</div>
        </div>
      </div>
      <div class="main">
        <div class="center">
          <el-form-item class="centerleft"  label="身份证号" prop="staffidcard">
            <el-input v-model="ruleForm.staffidcard" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="centerright" label="用户名" prop="staffloginame">
            <el-input v-model="ruleForm.staffloginame" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="login">
          <el-form-item class="loginleft" label="入职时间" prop="staffregtime">
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
          <el-form-item class="loginright" label="密码" prop="staffpass">
            <el-input v-model="ruleForm.staffpass" show-password clearable size="mini"></el-input>
          </el-form-item>
        </div>
      </div>
    </el-form>
     <div slot="footer" class="dialog-footer">
      <el-button type="primary" class="update"  size="small">修 改</el-button>
      <el-button type="primary" class="reset" plain size="small" @click="resetFormstaff()">重 置</el-button>
      <el-button  class="cancel" size="small">取 消</el-button>
     </div>
    </div>
   </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        rulesstaff: {
          username: [
            {
              required: true, message: '必填:姓名', trigger: 'blur'
            }
          ],
          useridcard:[{ required: true, min:18,max:18,message: '身份证必须为18个字符',trigger: 'blur'}],
          usersex: [{required: true, message: '必填:性别', trigger: 'blur'},],
          usertel: [{required: true, message: '必填:电话', trigger: 'blur'},],
          userloginame: [{required: true, message: '必填:用户名', trigger: 'blur'},],
          useristenant: [{required: true, message: '必填:是否租户', trigger: 'blur'},],
          userdoor: [{required: true, message: '必填:门牌号', trigger: 'blur'},],
          userpass: [
            {
              required: true, message: '必填:密码', trigger: 'blur',
              min: 6, message: '最少6位数', trigger: 'blur'
            }
          ],
        },
        id:'',
        ruleForm: {
          userprop:['员工'],
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
      }
    },
    components:{

    },
    methods: {
      formatTimeStaff(time){
        this.ruleForm.staffregtime=time;
        console.log(time);
      },
      resetFormstaff(){
        this.$refs.ruleformstaff.resetFields();
      },


    },
    mounted(){

    }
  }
</script>

<style scoped>
  @import "../../../static/css/update/updatestaff.css";
</style>
