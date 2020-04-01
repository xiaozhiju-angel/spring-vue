<template >
  <div id="register">
    <div id="top">
      <div class="top_left">
        <img src="../../static/petimg/034.jpg" />
        <span class="des">注册登录更精彩</span>
      </div>
      <div class=" top_right">
        <span class="exit" @click="exithome()">退出</span>
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
        <!--用户模块-->
        <div>
          <component class="regcenter" :is="comName" :useridentity="ruleForm.userprop"></component>
        </div>
        <!--结束-->
        <div class="end"></div>
      </el-form>
    </div>
  </div>

</template>

<script>
  import User from './Register/User.vue'
  import Staff from './Register/Staff.vue'
  import Admin from './Register/Admin.vue'
  export default {
    data() {
      return {
        cascaderAddr:'',
        comName:'reg-user',
        datared:false,
        datablue:false,
        /* selectedOptions:[],*/
        options: [{
          value: '业主',
          label: '业主'
        }, {
          value: '物业管理员',
          label: '物业管理员'
        }, {
          value: '后台管理员',
          label: '后台管理员'
        }],
        value: '',
        ruleForm: {
          userprop:['业主'],
        },
        rulesAuthority:{
          userprop:[
            { required: true, message: '必填:用戶权限', trigger: 'blur' },
          ],
        },
      };
    },
    components:{
      'reg-user':User,
      'reg-staff':Staff,
      'reg-admin':Admin
    },
    methods: {
      handleChange(value){
        if(value=='业主'){
          this.comName='reg-user'
        }
        if(value=='物业管理员'){
          this.comName='reg-staff'
        }
        if(value=='后台管理员'){
          this.comName='reg-admin'
        }
      },
      exithome(){
        this.$router.push({
          path:'/home'
        });
      }
    }
  }
</script>

<style scoped>
  @import "../../../static/css/admin_staff_user.css";
</style>

