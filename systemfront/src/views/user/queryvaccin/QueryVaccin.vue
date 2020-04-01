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
        <td  class="itemgutter" >{{item.vaccinId}}</td>
        <td  class="itemgutter" >{{item.petName}}</td>
        <td  class="itemgutter" >{{item.petType}}</td>
        <td  class="itemgutter" >{{item.userName}}</td>
        <td  class="itemgutter" >{{item.userTel}}</td>
        <td  class="itemgutter" >{{item.petpreVaccin}}</td>
        <td  class="itemgutter" >{{item.petnextVaccin}}</td>
        <td  style="padding: 25px;color: red">不健康</td>
      </tr>
      </tbody>
    </table>

    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 16px;margin-bottom: 20px;text-align: center">宠物疫苗信息</div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleform" :model="ruleForm" :rules="rules">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item  label="疫苗ID" >
                <el-input  v-model="ruleForm.vaccinid"  size="mini" clearable ></el-input>
              </el-form-item>
              <el-form-item  label="宠物姓名" >
                <el-input v-model="ruleForm.petname" size="mini" clearable ></el-input>
              </el-form-item>
            </div>
            <div class="middle_right">
              <el-form-item  label="业主姓名">
                <el-input v-model="ruleForm.username" size="mini" clearable ></el-input>
              </el-form-item>
              <el-form-item class="right" label="业主性别" >
                <el-radio-group  v-model="ruleForm.usersex" @change="changeusersex" >
                  <el-radio class="payornot" :label="'男'">男</el-radio>
                  <el-radio :label="'女'">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
          </div>
          <div class="middle_bottom">
            <div class="firstaddition">
              <el-form-item class="left" label="宠物ID" >
                <el-input  v-model="ruleForm.petid"  size="mini" clearable ></el-input>
              </el-form-item>
              <el-form-item class="right" label="用户ID" >
                <el-input  v-model="ruleForm.userid"  size="mini" clearable ></el-input>
              </el-form-item>
            </div>
            <div class="topaddition">
              <el-form-item class="left" label="宠物性别"  >
                <el-radio-group  v-model="ruleForm.petsex" @change="changepetsex" >
                  <el-radio class="payornot" :label="'公'">公</el-radio>
                  <el-radio :label="'母'">母</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item  class="right" label="业主电话" >
                <el-input v-model="ruleForm.usertel" size="mini" clearable ></el-input>
              </el-form-item>
            </div>
            <div class="secondaddition">
              <el-form-item label="宠物类型" class="left" >
                <el-select v-model="ruleForm.petype" placeholder="请选择" size="mini" @change="changepet" >
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
              <el-form-item  class="right" label="业主邮箱" >
                <el-input v-model="ruleForm.useremail" size="mini" clearable ></el-input>
              </el-form-item>
            </div>
            <div class="bottomtop">
              <el-form-item class="bottomleft" label="上次打疫苗">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.prevaccin"
                  size="mini"
                  type="date"
                  @change="formatTimeprevaccin"
                  value-format="yyyy-MM-dd"
                  placeholder="上次打疫苗时间" >
                </el-date-picker>
              </el-form-item>
              <el-form-item class="bottomright" label="下次打疫苗" >
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.nextvaccin"
                  size="mini"
                  type="date"
                  @change="formatTimepetnextvaccin"
                  value-format="yyyy-MM-dd"
                  placeholder="下次打疫苗时间"
                  >
                </el-date-picker>
              </el-form-item>
            </div>
            <div class="bottomcenter">
              <el-form-item class="bottomcenterleft" label="登记时间" >
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.petregtime"
                  size="mini"
                  type="date"
                  @change="formatTimepetregtime"
                  value-format="yyyy-MM-dd"
                  placeholder="宠物登记时间"
                  >
                </el-date-picker>
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false" size="mini">确 定</el-button>
        <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        dialogFormVisible:false,
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
        ruleForm: {
          vaccinid:'',
          petid:'',
          userid:'',
          petname:'',
          petsex:'',
          petype: '',
          username:'',
          usersex: '',
          usertel:'',
          useremail:'',
          prevaccin:'',
          nextvaccin:'',
          petregtime: '',
        },
        rules: {
          fosterdetail: [{required: true, message: '必填:寄养情况', trigger: 'blur'}],
          fosterpayornot:[{required: true, message: '必填:是否付费', trigger: 'blur'}],
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
      }
    },
    methods: {
      changepetsex(value){
        /*console.log(value);*/
        this.ruleForm.petsex=value;
        console.log(this.ruleForm.petsex);
      },
      changeusersex(value){
        /*console.log(value);*/
        this.ruleForm.usersex=value;
        console.log(this.ruleForm.usersex);
      },
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      formatTimepetregtime(val){
        this.ruleForm.petregtime=val;
        console.log(val);
      },
      formatTimepetnextvaccin(val){
        this.ruleForm.nextvaccin=val
      },
      formatTimeprevaccin(value){
        this.ruleForm.prevaccin=value;
        let str=this.ruleForm.prevaccin;
        let newstr = str.split('-');
        var year=parseInt(newstr[0]);
        var month= (parseInt(newstr[1])+1) + 11;
        if (month>12) {
          year++;
          month -= 12;
        }
        if(month<10) {
          month = "0" + month;
        }
        var date2=new Date(year,month,0);//新的年月
        var day1=parseInt(newstr[2]);
        var day2=date2.getDate();
        if(day1>day2){//11月份其实不需要，只有30天
          day1=day2;
        }
        var newMessage = year + '-'
          + month + '-'
          + day1;
        this.ruleForm.nextvaccin=this.dateFormat(newMessage);
        console.log(newMessage);
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
      edit(index){
        this.dialogFormVisible=true;
        var _this = this;
        this.getRequest("/foster/getvaccinInfoById?index=" + this.bodyInfoList[index].vaccinId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
            _this.ruleForm.vaccinid=_this.ruleForm[0][0].vaccinId;
            _this.ruleForm.petid=_this.ruleForm[0][0].petId;
            _this.ruleForm.userid=_this.ruleForm[0][0].userId;
            _this.ruleForm.petname=_this.ruleForm[0][0].petName;
            _this.ruleForm.petsex=_this.ruleForm[0][0].petSex;
            _this.ruleForm.petype=_this.ruleForm[0][0].petType;
            _this.ruleForm.username=_this.ruleForm[0][0].userName;
            _this.ruleForm.usersex=_this.ruleForm[0][0].userSex;
            _this.ruleForm.usertel=_this.ruleForm[0][0].userTel;
            _this.ruleForm.useremail=_this.ruleForm[0][0].userEmail;
            _this.ruleForm.prevaccin=_this.ruleForm[0][0].petpreVaccin;
            _this.ruleForm.nextvaccin=_this.ruleForm[0][0].petnextVaccin;
            _this.ruleForm.petregtime=_this.ruleForm[0][0].petRegtime;
          }
        })
      },
      del(index){
        var _this = this;
        console.log(index)
        console.log(this.bodyInfoList[index].vaccinId);
        this.$confirm('此操作将删除宠物[' + this.bodyInfoList[index].petName + ']疫苗信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/foster/delvaccinById?id="+this.bodyInfoList[index].vaccinId).then(resp=> {
            if (resp.data.del) {
              alert(resp.data.back);
              _this.initData();
              console.log(resp.data.data)
            }
            if(resp.data.exist)
              alert(resp.data.data);
          })
        }).catch(() => {
        });
      },
      updateinfo(){
        var that=this;
        this.$refs.ruleform.validate((valid) => {
          if (valid) {
            var that = this;
            this.putRequest("/foster/updatevaccin", {
              vaccinid:this.ruleForm.vaccinid,
              petid:this.ruleForm.petid,
              userid:this.ruleForm.userid,
              petname: this.ruleForm.petname,
              petsex: this.ruleForm.petsex,
              petype: this.ruleForm.petype,
              username: this.ruleForm.username,
              usersex: this.ruleForm.usersex,
              usertel: this.ruleForm.usertel,
              useremail:this.ruleForm.useremail,
              prevaccin:this.ruleForm.prevaccin,
              nextvaccin:this.ruleForm.nextvaccin,
              petregtime: this.ruleForm.petregtime
            }).then(response => {
              if (response.data.update) {
                that.initData();
                alert(response.data.data);
                that.dialogFormVisible = false;
              }
              if(response.data.msg){
                alert(response.data.data);
              }
            })
          }
          else {
            alert("请检查填写是否正确.")
          }
        })
      },
      initData(){
        var _this = this;
        this.getRequest("/foster/vaccinInfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },


      /*计算相差天数*/
      initday(){
        this.daycompute();
        console.log(this.day);
      },
      daycompute(){
        let start=this.ruleForm.fosterbegintime;
        let end=this.ruleForm.fosterendtime;
        this.dateday(start,end);
      },
      dateday(startDateString, endDateString){
        var separator = "-"; //日期分隔符
        var startDates = startDateString.split(separator);
        var endDates = endDateString.split(separator);
        var startDate = new Date(startDates[0], startDates[1]-1, startDates[2]);
        var endDate = new Date(endDates[0], endDates[1]-1, endDates[2]);
        var m=parseInt(Math.abs(endDate - startDate ) / 1000 / 60 / 60 /24) + 1;//把相差的毫秒数转换为天数
        this.day=m;
      },
    },
  }
</script>

<style scoped>
  @import '../../../../static/css/dialog/queryvaccin.css';
  .itemgutter{
    padding: 25px;
    font-size:14px;
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

