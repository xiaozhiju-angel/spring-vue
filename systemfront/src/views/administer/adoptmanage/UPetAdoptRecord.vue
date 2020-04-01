<template>
  <div class="middle">
    <div style="font-size:18px;padding: 30px 0px">生成领养记录</div>
    <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
      <div class="middle_bottom">
        <div class="topaddition">
          <el-form-item class="left"  label="宠物ID" prop="petid">
            <el-input   v-model="ruleForm.petid"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="right" label="原主人ID" prop="preuserid">
            <el-input  v-model="ruleForm.preuserid"  size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="secondaddition">
          <el-form-item class="left" label="弃养原因" prop="abandonreason">
            <el-input type="textarea" v-model="ruleForm.abandonreason" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="right" label="现主人ID" prop="nowuserid">
            <el-input  v-model="ruleForm.nowuserid" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="bottomtop">
          <el-form-item class="bottomright" label="领养时间" prop="adoptime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.adoptime"
              size="mini"
              type="date"
              @change="formatTimeadopt"
              value-format="yyyy-MM-dd"
              placeholder="登记领养时间">
            </el-date-picker>
          </el-form-item>
        </div>
      </div>
      <div>
        <el-form-item class="btn_bottom">
          <el-button class="btn" size="small" type="primary" @click="onSubmit()">提交</el-button>
          <el-button class="btn"  size="small" plain  @click="resetForm()">重置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        ruleForm: {
          petid:'',
          preuserid: '',
          nowuserid: '',
          abandonreason:'',
          adoptime: '',
        },
        rulespet:{
          petid:[{required: true, message: '必填:宠物ID', trigger: 'blur'}],
          preuserid: [{required: true, message: '必填:原主人ID', trigger: 'blur'}],
          nowuserid: [{required: true, message: '必填:现主人ID', trigger: 'blur'}],
        },
      }
    },
    methods: {
      formatTimeadopt(time) {
        this.ruleForm.adoptime = time;
        console.log(time)
      },
      resetForm() {
        this.$refs.ruleformpet.resetFields();
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
      onSubmit() {
        var that = this;
        var m=this.datesize(this.ruleForm.adoptime,this.getNowFormatDate());
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if(m==0){
              this.postRequest("/adopt/adoptrecord", {
                petid: this.ruleForm.petid,
                preuserid: this.ruleForm.preuserid,
                nowuserid: this.ruleForm.nowuserid,
                abandonreason: this.ruleForm.abandonreason,
                adoptime: this.ruleForm.adoptime,
              }).then(response => {
                if (response.data.success) {
                  alert(response.data.data);
                  that.resetForm();
                }
                if(response.data.update){
                  alert(response.data.data);
                }
                if (response.data.notreg){
                  alert(response.data.data);
                }
              })
            }
            else if(m==1)
              alert("时间与实际不符");
          }
        })
      },

    }
  }
</script>
<style scoped>
  @import '../../../../static/css/admin/upetadoptrecord.css';
</style>
