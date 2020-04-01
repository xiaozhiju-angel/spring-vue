<template>
  <div class="middle">

    <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
      <div class="middle_top">
        <div class="middle_left">
          <div style="padding-bottom: 50px;font-size: 18px;margin-right: 20px">宠物信息发布</div>
          <el-form-item class="input" label="领养类型" prop="petsex">
            <el-radio-group  v-model="ruleForm.petype" @change="adoptypechange">
              <el-radio  class="adopt" :label="'领养'">领养</el-radio>
              <el-radio :label="'弃养'">弃养</el-radio>
            </el-radio-group>
          </el-form-item>
          <div v-if="abandon">
            <el-form-item class="input"  label="宠物ID" prop="petid">
              <el-input class="info"  v-model="ruleForm.petid"  size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="input" label="弃养原因" prop="petsex">
              <el-input  v-model="ruleForm.petname"  size="mini" clearable></el-input>
            </el-form-item>
          </div>
          <el-form-item class="input" label="宠物主人ID" prop="petuserid">
            <el-input  v-model="ruleForm.petuserid" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="input" label="发布时间" prop="useregtime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.publishtime"
              size="mini"
              type="date"
              @change="formatTimepublish"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>

          <el-form-item>
            <el-button class="btn" size="small" type="primary" @click="onSubmit()">发布</el-button>
            <el-button  size="small" plain  @click="resetForm()">重置</el-button>
          </el-form-item>

        </div>

      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        fileList: [],
        abandon:false,
        ruleForm: {
          petname: '',
          petsex: '',
          petype: [],
          petuserid:'',
          petimageUrl: '',
          petprevaccin:'',
          petnextvaccin:'',
          petregtime: '',
          petisuserapply:''
        },
        rulespet:{
          petname:[{required: true, message: '必填:姓名', trigger: 'blur'}],
          petype: [{required: true, message: '必填:类型', trigger: 'blur'}],
          petuserid: [{required: true, message: '必填:宠物主人ID', trigger: 'blur'}],
          petprevaccin: [{required: true, message: '必填:上次打疫苗时间', trigger: 'blur'}]
        },
      }
    },
    methods: {
      /*宠物登记*/
      handleChange(value) {
        this.ruleForm.petype = value;
        console.log(this.ruleForm.petype);
      },
      applychange(value) {
        /*console.log(value);*/
        this.ruleForm.petisuserapply = value;
      },
      formatTimePet(time) {
        this.ruleForm.petregtime = time;
        console.log(time)
      },
      /*formatTimepetPrevaccin(time){
        this.ruleForm.petprevaccin=time;
        console.log(time)
      },*/
      /*formatTimepetNextvaccin(time){
        this.ruleForm.nextvaccin=time;
        console.log(time)
      },*/
      adoptypechange(value) {
        /*console.log(value);*/
        this.ruleForm.adoptype = value;
        if (value == "弃养") {
          this.abandon = true;
        }
        if (value == "领养") {
          this.abandon = false;
        }
        console.log(this.ruleForm.petsex);
      },
      resetForm() {
        this.$refs.ruleformpet.resetFields();
        this.ruleForm.petnextvaccin = '';
        this.petimageUrl = '';
      },
      onSubmitpet() {
        var that = this;
        /* localStorage.setItem('petname',JSON.stringify(this.ruleForm));
         var p = JSON.parse(localStorage.getItem('petname'));
         console.log( p);*/
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if (this.ruleForm.petisuserapply == '是') {
              this.postRequest("/pet/savepet", {
                petname: this.ruleForm.petname,
                petsex: this.ruleForm.petsex,
                petuserid: this.ruleForm.petuserid,
                petimg: this.ruleForm.petimageUrl,
                petype: this.ruleForm.petype,
                petprevaccin: this.ruleForm.petprevaccin,
                petnextvaccin: this.ruleForm.petnextvaccin,
                petisuserapply: this.ruleForm.petisuserapply,
                petregtime: this.ruleForm.petregtime,
              }).then(response => {
                if (response.data.success) {
                  alert(response.data.savepet);
                  that.resetFormpet();
                } else {
                  alert(response.data.data);
                  that.$router.push({
                    path: '/urg'
                  });
                }
              })
            } else {
              alert("请确认申请!");
            }

          }
        })

      },

    }
  }
</script>
<style scoped>
  @import '../../../static/css/user/upetadoptpublish.css';
</style>
