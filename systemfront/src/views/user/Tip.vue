<template>
  <div class="middle">
    <div style="font-size:18px;padding: 50px 0px">发表投诉</div>
    <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
      <div class="middle_bottom">
        <div class="topaddition">
          <el-form-item class="left"  label="用户ID" prop="userid">
            <el-input    v-model="ruleForm.userid"  size="mini" clearable disabled></el-input>
          </el-form-item>
          <el-form-item class="right"  label="举报门牌号" prop="tipdoor">
            <el-input    v-model="ruleForm.tipdoor"  size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="secondaddition">
          <el-form-item class="left"  label="发生时间" prop="time">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.time"
              size="mini"
              type="date"
              @change="formatTimehappen"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          </el-form-item>
          <el-form-item class="right" label="发生地点" prop="place">
            <el-input  v-model="ruleForm.place"  size="mini" clearable></el-input>
          </el-form-item>

        </div>
        <div class="bottomtop">
          <el-form-item class="bottomleft" label="详情描述" prop="detail">
            <el-input type="textarea" v-model="ruleForm.detail" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="bottomright" label="投诉时间" prop="tiptime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.tiptime"
              size="mini"
              type="date"
              @change="formatTimetip"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </div>
      </div>
      <div>
        <el-form-item class="btn_bottom">
          <el-button class="btn" size="small" type="primary" @click="publish()">投 诉</el-button>
          <el-button class="btn"  size="small" plain  @click="reset()">重 置</el-button>
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
          userid: this.userid,
          tipdoor:'',
          time:'',
          place:'',
          detail: '',
          tiptime: '',
        },
        rulespet:{
          userid:[{required: true, message: '必填:用户ID', trigger: 'blur'}],
          time:[{required: true, message: '必填:何时发生', trigger: 'blur'}],
          place:[{required: true, message: '必填:何处发生', trigger: 'blur'}],
          detail: [{required: true, message: '必填:事件详情', trigger: 'blur'}],
          tiptime: [{required: true, message: '必填:投诉时间', trigger: 'blur'}],
        },
      }
    },
    props:['userid'],
    methods: {
      formatTimehappen(time) {
        this.ruleForm.time = time;
        console.log(time)
      },
      formatTimetip(time){
        this.ruleForm.tiptime=time;
        console.log(time)
      },
      reset() {
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
      publish() {
        var that = this;
        var m=this.datesize(this.ruleForm.time,this.ruleForm.tiptime);
        var n=this.datesize(this.ruleForm.time,this.getNowFormatDate());
        var k=this.datesize(this.ruleForm.tiptime,this.getNowFormatDate());
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if(m==0&&n==0&&k==0){
              this.postRequest("/user/tipinfo", {
                userid: this.ruleForm.userid,
                tipdoor: this.ruleForm.tipdoor,
                time: this.ruleForm.time,
                place: this.ruleForm.place,
                detail: this.ruleForm.detail,
                tiptime: this.ruleForm.tiptime,
              }).then(response => {
                if (response.data.success) {
                  alert(response.data.data);
                  that.reset();
                }
                if (response.data.update)
                  alert(response.data.data);
                if(response.data.notreg)
                  alert(response.data.data);
              })
            }
            else if(m==1)
              alert("注意时间先后");
            else if(n==1||k==1)
              alert("时间不符合实际");

          }
        })

      },

    }
  }
</script>
<style scoped>
  @import '../../../static/css/user/tip.css';
</style>
