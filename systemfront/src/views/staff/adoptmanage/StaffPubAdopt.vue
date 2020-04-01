<template>
  <div class="middle">
    <div style="font-size:18px;padding: 30px 0px">宠物领养信息发布</div>
    <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
      <div class="middle_bottom">
        <div class="topaddition">
          <el-form-item class="left" label="领养类型" prop="adoptype">
            <el-radio-group  v-model="ruleForm.adoptype" @change="adoptypetchange">
              <el-radio  class="adopt" :label="'领养'">领养</el-radio>
              <el-radio :label="'弃养'">弃养</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="宠物类型" class="right" prop="petype">
            <el-select v-model="ruleForm.petype" placeholder="请选择" size="mini" @change="changepet">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="secondaddition">
          <el-form-item class="left" label="用户ID" prop="petuserid">
            <el-input  v-model="ruleForm.petuserid" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="right" label="发布时间" prop="publishtime">
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
        </div>
        <div class="bottomtop">
          <div v-if="abandon">
            <el-form-item class="left"  label="宠物ID">
              <el-input class="info"  v-model="ruleForm.petid"  size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="right" label="弃养原因" >
              <el-input type="textarea" v-model="ruleForm.abandonreason"  size="mini" clearable></el-input>
            </el-form-item>
          </div>
        </div>
      </div>
      <div>
        <el-form-item class="btn_bottom">
          <el-button class="btn" size="small" type="primary" @click="publish()">发布</el-button>
          <el-button class="btn"  size="small" plain  @click="reset()">重置</el-button>
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
          adoptype:'',
          petid: '',
          petype: '',
          petuserid:'',
          publishtime: '',
          abandonreason:'',
        },
        rulespet:{
          adoptype:[{required: true, message: '必填:领养类型', trigger: 'blur'}],
          petype: [{required: true, message: '必填:宠物类型', trigger: 'blur'}],
          petuserid: [{required: true, message: '必填:用户ID', trigger: 'blur'}],
          publishtime:[{required: true, message: '必填:发布时间', trigger: 'blur'}],
        },
        options: [
          {
            value: '猫类',
            label: '猫类',
          },
          {
            value: '狸花猫',
            label: '狸花猫',
          },
          {
            value: '缅因猫',
            label: '缅因猫'
          },
          {
            value: '暹罗猫',
            label: '暹罗猫'
          },
          {
            value: '折耳猫',
            label: '折耳猫'
          },
          {
            value: '波斯猫',
            label: '波斯猫'
          },
          {
            value: '索马里',
            label: '索马里'
          },
          {
            value: '金吉拉猫',
            label: '金吉拉猫'
          } ,
          {
            value: '犬类',
            label: '犬类',
          },
          {
            value: '土佐犬 ',
            label: '土佐犬'
          },
          {
            value: '大麦町',
            label: '大麦町'
          },
          {
            value: '四国犬 ',
            label: '四国犬'
          },
          {
            value: '杜宾狗',
            label: '杜宾狗'
          },
          {
            value: '狮子狗  ',
            label: '狮子狗 '
          },
          {
            value: '哈士奇',
            label: '哈士奇'
          },
          {
            value: '哈巴狗 ',
            label: '哈巴狗'
          },
          {
            value: '泰迪',
            label: '泰迪'
          },
          {
            value: '鸟类',
            label: '鸟类',
          },
          {
            value: '百灵鸟',
            label: '百灵鸟',
          },{
            value: '金丝雀',
            label: '金丝雀'
          }, {
            value: '八哥乌',
            label: '八哥乌'
          }, {
            value: '太平鸟',
            label: '太平鸟'
          }, {
            value: '相思鸟',
            label: '相思鸟'
          }, {
            value: '蜡嘴雀',
            label: '蜡嘴雀'
          },
          {
            value: '鹦鹉',
            label: '鹦鹉'
          },
          {
            value: '兔类',
            label: '兔类',
          },
          {
            value: '伊拉兔',
            label: '伊拉兔'
          }, {
            value: '荷兰兔',
            label: '荷兰兔'
          }, {
            value: '迷你兔',
            label: '迷你兔'
          }, {
            value: '海棠兔',
            label: '海棠兔'
          }, {
            value: '波兰兔',
            label: '波兰兔'
          }, {
            value: '银狐兔',
            label: '银狐兔'
          }],

      }
    },
    methods: {
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },

      formatTimepublish(time) {
        this.ruleForm.publishtime = time;
        console.log(time)
      },
      adoptypetchange(value) {
        console.log(value);
        this.ruleForm.adoptype = value;
        if (value == "弃养") {
          this.abandon = true;
        }
        if (value == "领养") {
          this.abandon = false;
        }
      },
      reset() {
        this.$refs.ruleformpet.resetFields();
        this.ruleForm.publishtime = '';
        this.ruleForm.petid='';
        this.ruleForm.abandonreason='';
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
        var m=this.datesize(this.ruleForm.publishtime,this.getNowFormatDate());
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if(m==0){
              this.postRequest("/adopt/adoptpublish", {
                adoptype:this.ruleForm.adoptype,
                petid: this.ruleForm.petid,
                petype: this.ruleForm.petype,
                petuserid:this.ruleForm.petuserid,
                publishtime: this.ruleForm.publishtime,
                abandonreason:this.ruleForm.abandonreason,
              }).then(response => {
                if (response.data.success) {
                  alert(response.data.data);
                  that.reset();
                }
                else if (response.data.update){
                  alert(response.data.data);
                }
                else if (response.data.notreg){
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
  @import '../../../../static/css/user/upetadoptpublish.css';
</style>
