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
        <td  class="itemgutter" >{{item.petId}}</td>
        <td  class="itemgutter" >{{item.petName}}</td>
        <td  class="itemgutter" >{{item.petSex}}</td>
        <td  class="itemgutter" >{{item.petType}}</td>
        <td  class="itemgutter" >{{item.petpreuserName}}</td>
        <td  class="itemgutter" >{{item.petpreuserTel}}</td>
        <td  class="itemgutter" >{{item.petnowuserName}}</td>
        <td  class="itemgutter" >{{item.petnowuserTel}}</td>
        <td  class="itemgutter" >{{item.adoptTime}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)">编辑</el-button></td>
        <td  class="del"><el-button type="danger"  size="mini" @click="clear(index)">删除</el-button></td>
      </tr>
      </tbody>
    </table>
    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #bc2a19;font-size: 14px;margin-bottom: 30px;text-align: center">
        {{ruleForm.preusername}}<span style="color: dodgerblue">与 </span>{{ruleForm.nowusername}}<span style="color: dodgerblue">之间的宠物领养信息</span></div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item class="elformitem" label="领养ID">
                <el-input  v-model="ruleForm.id"  size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item class="elformitem" label="宠物ID" >
                <el-input  v-model="ruleForm.petid"  size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item class="elformitem" label="宠物姓名" prop="petname">
                <el-input  v-model="ruleForm.petname"  size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="宠物性别" prop="petsex">
                <el-radio-group v-model="ruleForm.petsex" @change="changesex">
                  <el-radio :label="'公'">公</el-radio>
                  <el-radio :label="'母'">母</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
            <div class="middle_right">
              <img  class="avatar" :src="host+'/'+ruleForm.petimageUrl" alt=""/>
                <el-button class="imgdes" type="plain" size="mini">宠物照片</el-button>
              </el-form-item>
            </div>
          </div>
          <div class="middle_bottom">
            <div class="firstaddition">
              <el-form-item class="left" label="原业主ID" >
                <el-input v-model="ruleForm.preuserid" size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item class="right" label="现业主ID" >
                <el-input v-model="ruleForm.nowuserid" size="mini" clearable disabled></el-input>
              </el-form-item>
            </div>
            <div class="topaddition">
              <el-form-item class="left" label="原业主姓名" prop="preusername">
                <el-input v-model="ruleForm.preusername" size="mini" clearable ></el-input>
              </el-form-item>
              <el-form-item class="right" label="现业主姓名" prop="nowusername">
                <el-input v-model="ruleForm.nowusername" size="mini" clearable ></el-input>
              </el-form-item>
            </div>
            <div class="secondaddition">
              <el-form-item class="left" label="原业主电话" prop="preusertel">
                <el-input v-model="ruleForm.preusertel" size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item class="right" label="现业主电话" prop="nowusertel">
                <el-input v-model="ruleForm.nowusertel" size="mini" clearable></el-input>
              </el-form-item>
            </div>
            <div class="bottomtop">
              <el-form-item class="left"  label="原业主邮箱" prop="preuseremail">
                <el-input class="info"  v-model="ruleForm.preuseremail"  size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item class="right" label="现业主邮箱" prop="nowuseremail">
                <el-input  v-model="ruleForm.nowuseremail"  size="mini" clearable></el-input>
              </el-form-item>
            </div>
            <div class="bottomcenter">
              <el-form-item class="left" label="弃养原因" prop="abandonreason">
                <el-input type="textarea" v-model="ruleForm.abandonreason" size="mini" clearable></el-input>
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
            <div class="bottombottom">
              <el-form-item class="right" label="领养时间" prop="adoptime">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.adoptime"
                  size="mini"
                  type="date"
                  @change="formatTimepublish"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateinfo()" size="mini">更 新</el-button>
        <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        host:'http://localhost:8088/we',
        dialogFormVisible:false,
        fileList: [],
        abandon:false,
        adopt:true,
        petimageUrl:'',
        ruleForm: {
          id:'',
          petid: '',
          preuserid:'',
          nowuserid:'',
          petname:'',
          petsex:'',
          petype: '',
          petimageUrl:'',
          preusername: '',
          preusertel:'',
          preuseremail:'',
          nowusername: '',
          nowusertel:'',
          nowuseremail:'',
          abandonreason:'',
          adoptime:'',
        },
        rulespet:{
          petname:[{required: true, message: '必填:宠物姓名', trigger: 'blur'}],
          petsex:[{required: true, message: '必填:宠物性别', trigger: 'blur'}],
          petype: [{required: true, message: '必填:类型', trigger: 'blur'}],
          preusername:[{required: true, message: '必填:原业主姓名', trigger: 'blur'}],
          preusertel: [
            {required: true, message: '必填:手机号码', trigger: 'blur'},
            {min: 11, message: '最少11位数', trigger: 'blur'},
            {validator: this.validator.phoneNumber, trigger: 'blur'}
          ],
          preuseremail: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          nowusername:[{required: true, message: '必填:现业主姓名', trigger: 'blur'}],
          nowusertel: [
            {required: true, message: '请输入手机号码', trigger: 'blur'},
            {min: 11, message: '最少11位数', trigger: 'blur'},
            {validator: this.validator.phoneNumber, trigger: 'blur'}
          ],
          nowuseremail: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
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
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      changesex(val){
        this.ruleForm.petsex=val;
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
      edit(index){
        this.dialogFormVisible=true;
        var _this = this;
        this.getRequest("/adopt/getadoptById?index=" + this.bodyInfoList[index].id).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
            _this.ruleForm.id=_this.ruleForm[0][0].id;
            _this.ruleForm.petid=_this.ruleForm[0][0].petId;
            _this.ruleForm.preuserid=_this.ruleForm[0][0].preuserId;
            _this.ruleForm.nowuserid=_this.ruleForm[0][0].nowuserId;
            _this.ruleForm.petname=_this.ruleForm[0][0].petName;
            _this.ruleForm.petsex=_this.ruleForm[0][0].petSex;
            _this.ruleForm.petype=_this.ruleForm[0][0].petType;
            _this.ruleForm.petimageUrl=_this.ruleForm[0][0].petImg;
            _this.ruleForm.preusername=_this.ruleForm[0][0].petpreuserName;
            _this.ruleForm.preusertel=_this.ruleForm[0][0].petpreuserTel;
            _this.ruleForm.preuseremail=_this.ruleForm[0][0].petpreuserEmail;
            _this.ruleForm.nowusername=_this.ruleForm[0][0].petnowuserName;
            _this.ruleForm.nowusertel=_this.ruleForm[0][0].petnowuserTel;
            _this.ruleForm.nowuseremail=_this.ruleForm[0][0].petnowuserEmail;
            _this.ruleForm.abandonreason=_this.ruleForm[0][0].abandonReason;
            _this.ruleForm.adoptime=_this.ruleForm[0][0].adoptTime;
          }
        })
      },
      clear(index){
        var _this = this;
        console.log(index)
        /* console.log(this.bodyInfoList[index].adminId);*/
        this.$confirm('此操作将永久删除宠物[' + this.bodyInfoList[index].petName + ']的领养信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/adopt/deleteadopt?id="+this.bodyInfoList[index].id).then(resp=> {
            if (resp.data.delete) {
              alert(resp.data.data);
              _this.initData();
            }
          })
        }).catch(() => {
        });
      },
      initData(){
        var _this = this;
        this.getRequest("/adopt/getadoptinfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },
      reset(){
        this.$refs.ruleformpet.resetFields();
        this.petimageUrl='';
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
      updateinfo(){
        var that=this;
        var m=this.datesize(this.ruleForm.adoptime,this.getNowFormatDate());
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if(m==0){
              this.postRequest("/adopt/updateadoptinfo", {
                id:this.ruleForm.id,
                petid: this.ruleForm.petid,
                preuserid:this.ruleForm.preuserid,
                nowuserid:this.ruleForm.nowuserid,
                petname: this.ruleForm.petname,
                petsex: this.ruleForm.petsex,
                petype: this.ruleForm.petype,
                petimg: this.ruleForm.petimageUrl,
                preusername: this.ruleForm.preusername,
                preusertel:this.ruleForm.preusertel,
                preuseremail:this.ruleForm.preuseremail,
                nowusername:this.ruleForm.nowusername,
                nowusertel: this.ruleForm.nowusertel,
                nowuseremail:this.ruleForm.nowuseremail,
                abandonreason: this.ruleForm.abandonreason,
                adoptime:this.ruleForm.adoptime
              }).then(response => {
                if (response.data.update) {
                  that.initData();
                  alert(response.data.data);
                  that.dialogFormVisible = false;
                }
              })
            }
            else if(m==1)
              alert("时间与实际不符");
          }
          else {
            alert("请检查填写是否正确.")
          }
        })
      },





    }
  }
</script>

<style scoped>
  @import '../../../../../static/css/dialog/queryadopt.css';
  .itemgutter{
    padding: 20px;
    font-size: 14px;
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

