<template>
  <div class="middle">
    <div style="font-size:18px;padding: 50px 0px;margin-right: 50px">宠物丢失信息发布</div>
    <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
      <div class="middle_bottom">
        <div class="topaddition">
          <el-form-item class="left"  label="宠物ID" prop="petid">
            <el-input    v-model="ruleForm.petid"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="right"  label="用户ID" prop="petuserid">
            <el-input    v-model="ruleForm.petuserid"  size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="secondaddition">
          <el-form-item class="left"  label="丢失时间" prop="losetime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.losetime"
              size="mini"
              type="date"
              @change="formatTimepublish"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          </el-form-item>
          <el-form-item class="right" label="丢失地点" >
            <el-input  v-model="ruleForm.loseplace"  size="mini" clearable></el-input>
          </el-form-item>

        </div>
        <div class="bottomtop">
          <el-form-item class="bottomleft" label="详情描述" prop="losedetail">
            <el-input type="textarea" v-model="ruleForm.losedetail" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="bottomright" label="发布时间" prop="publosetime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.publosetime"
              size="mini"
              type="date"
              @change="formatTimepublish"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </div>
      </div>
      <div>
        <el-form-item class="btn_bottom">
          <el-button class="btn" size="small" type="primary" @click="publish()">发 布</el-button>
          <el-button class="btn" size="small" plain @click="reset()">重 置</el-button>
        </el-form-item>
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
        adopt:true,
        ruleForm: {
          petid: '',
          petuserid:'',
          losetime:'',
          loseplace:'',
          losedetail: '',
          publosetime: '',
        },
        rulespet:{
          petid:[{required: true, message: '必填:宠物ID', trigger: 'blur'}],
          petuserid: [{required: true, message: '必填:丢失宠物的用户ID', trigger: 'blur'}],
          losetime:[{required: true, message: '必填:丢失宠物时间', trigger: 'blur'}],
          publosetime:[{required: true, message: '必填:发布时间', trigger: 'blur'}],
        },
      }
    },
    methods: {
      /*changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },*/
      /*宠物leixing*/
      formatTimepublish(time) {
        this.ruleForm.publosetime = time;
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

      reset() {
        this.$refs.ruleformpet.resetFields();
        this.petimageUrl = '';
        this.ruleForm.loseplace='';
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
      publish() {
        var that = this;
        var m=this.datesize(this.ruleForm.losetime,this.ruleForm.publosetime);
        var n=this.datesize(this.ruleForm.losetime,this.getNowFormatDate());
        var k=this.datesize(this.ruleForm.publosetime,this.getNowFormatDate());
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if(m==0&&n==0&&k==0){
              this.postRequest("/lose/losepublish", {
                petid: this.ruleForm.petid,
                petuserid: this.ruleForm.petuserid,
                losetime: this.ruleForm.losetime,
                loseplace: this.ruleForm.loseplace,
                losedetail: this.ruleForm.losedetail,
                publosetime: this.ruleForm.publosetime,
              }).then(response => {
                if (response.data.success) {
                  alert(response.data.data);
                  that.reset();
                }
                if (response.data.update){
                  alert(response.data.data);
                  that.reset();
                }
                if(response.data.notreg)
                  alert(response.data.data);
              })
            }
            else if(m==1)
              alert("注意时间先后");
            else  if(n==1||k==1)
              alert("时间不符合实际");

          }
        })

      },

    }
  }
</script>
<style scoped>
  @import '../../../../static/css/admin/losepublish.css';
</style>
