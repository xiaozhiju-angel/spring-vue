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
        <td  class="itemgutter" >{{item.petuserId}}</td>
        <td  class="itemgutter" >{{item.petType}}</td>
        <td  class="itemgutter" >{{item.petrabidPrevaccin}}</td>
        <td  class="itemgutter" >{{item.petrabidNextvaccin}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)" >同意</el-button></td>
        <td  class="del"><el-button type="danger"  size="mini" @click="refuse(index)">拒绝</el-button></td>
      </tr>
      </tbody>
    </table>
    <!--<div v-if="showEdit">
      <edit-table></edit-table>
    </div>-->

    <el-dialog class="dialog"  :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 16px;margin-bottom: 30px;text-align: center">批复宠物登记</div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item class="elformitem" label="宠物ID" >
                <el-input  v-model="ruleForm.petid"  size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item label="用户ID" >
                <el-input v-model="ruleForm.petuserid" size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item class="elformitem" label="宠物姓名" prop="petname">
                <el-input  v-model="ruleForm.petname"  size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="宠物类型" prop="petype">
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
            <div class="middle_right">
              <el-form-item>
                <img  class="img" :src="host+'/'+ruleForm.petimageUrl" alt=""/>
                <div><el-button class="imgdes" type="plain" size="mini">宠物照片</el-button></div>
              </el-form-item>
            </div>
          </div>
          <div class="middle_bottom">
            <div class="bottomtop">
              <el-form-item class="bottomleft" label="上次打疫苗" prop="petprevaccin">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.petprevaccin"
                  size="mini"
                  type="date"
                  @change="formatTimepetPrevaccin"
                  value-format="yyyy-MM-dd"
                  placeholder="上次打疫苗的时间">
                </el-date-picker>
              </el-form-item>
              <el-form-item  class="bottomright" label="宠物性别" prop="petsex">
                <el-radio-group v-model="ruleForm.petsex" @change="changesex">
                  <el-radio class="applyornot" :label="'公'">公</el-radio>
                  <el-radio :label="'母'">母</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
            <div class="bottomcenter">
              <el-form-item class="bottomcenterleft" label="下次打疫苗" >
                <!-- @change="formatTimepetNextvaccin"-->
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.petnextvaccin"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="下次应打疫苗的时间"
                  disabled>
                </el-date-picker>
              </el-form-item>
              <el-form-item  class="bottomcenterright" label="申请登记" prop="petisuserapply">
                <el-radio-group v-model="ruleForm.petisuserapply" @change="applychange">
                  <el-radio class="applyornot" :label="'是'">是</el-radio>
                  <el-radio :label="'否'">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
            <div class="inadition">
              <el-form-item class="left" label="登记时间" prop="petregtime">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.petregtime"
                  size="mini"
                  type="date"
                  @change="formatTimePet"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </div>
          </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="updateinfo()" size="mini">登 记</el-button>
          <el-button  @click="reset()"  plain size="mini">重 置</el-button>
          <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
        </div>
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
        petimageUrl: '',
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
          petid:'',
          petname: '',
          petsex: '',
          petype: '',
          petuserid:'',
          petimageUrl: '',
          petprevaccin:'',
          petnextvaccin:'',
          petregtime: '',
          petisuserapply:''
        },
        rulespet:{
          petid:[{required: true, message: '必填:宠物ID', trigger: 'blur'}],
          petname:[{required: true, message: '必填:姓名', trigger: 'blur'}],
          petype: [{required: true, message: '必填:类型', trigger: 'blur'}],
          petsex:[{required: true, message: '必填:性别', trigger: 'blur'}],
          petuserid: [{required: true, message: '必填:宠物主人ID', trigger: 'blur'}],
          petprevaccin: [{required: true, message: '必填:上次打疫苗时间', trigger: 'blur'}],
          petisuserapply:[{required: true, message: '必填:是否同意用户注册', trigger: 'blur'}]
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
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      edit(index) {
        this.dialogFormVisible = true;
        var _this = this;
        /* console.log(this.bodyInfoList[index].userId);*/
        this.getRequest("/foster/getApplypetInfoById?index=" + this.bodyInfoList[index].petId).then(resp => {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
            _this.ruleForm.petid = _this.ruleForm[0][0].petId;
            _this.ruleForm.petname = _this.ruleForm[0][0].petName;
            _this.ruleForm.petsex = _this.ruleForm[0][0].petSex;
            _this.ruleForm.petuserid = _this.ruleForm[0][0].petuserId;
            _this.ruleForm.petimageUrl = _this.ruleForm[0][0].petImg;
            _this.ruleForm.petype = _this.ruleForm[0][0].petType;
            _this.ruleForm.petprevaccin = _this.ruleForm[0][0].petrabidPrevaccin;
            _this.ruleForm.petnextvaccin = _this.ruleForm[0][0].petrabidNextvaccin;
            _this.ruleForm.petisuserapply = _this.ruleForm[0][0].petisuserApply;
            _this.ruleForm.petregtime = _this.ruleForm[0][0].petrecodTime;
          }
        })
      },
      refuse(index) {
        var that = this;
       /* console.log(index)
        console.log(this.bodyInfoList[index].petId);*/
        this.$confirm('此操作将拒绝该业主登记宠物, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/foster/refuseUpetapplyById?id=" + this.bodyInfoList[index].petId).then(resp => {
            if (resp.data.refuse) {
              alert(resp.data.back);
              that.initData();
            }
          })
        })
      },
      datesize(date1,date2) {
        var start = new Date(date1.replace("-", "/").replace("-", "/"));
        console.log(start.getTime());
        var end = new Date(date2.replace("-", "/").replace("-", "/"));
        console.log(end.getTime());
        if (end.getTime() <= start.getTime()){
          return 1;
        }else return 0;
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
      updateinfo() {
        var that = this;
        var m=this.datesize(this.ruleForm.petprevaccin,this.ruleForm.petregtime);
        var n=this.datesize(this.ruleForm.petprevaccin,this.getNowFormatDate());
        var k=this.datesize(this.ruleForm.petregtime,this.getNowFormatDate());
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            if (this.ruleForm.petisuserapply == "否"&&m==0&&n==0&&k==0) {
              this.putRequest("/foster/admitapplyReg", {
                petid: this.ruleForm.petid,
                petname: this.ruleForm.petname,
                petsex: this.ruleForm.petsex,
                petuserid: this.ruleForm.petuserid,
                petimg: this.ruleForm.petimageUrl,
                petype: this.ruleForm.petype,
                petprevaccin: this.ruleForm.petprevaccin,
                petnextvaccin: this.ruleForm.petnextvaccin,
                petisuserapply: this.ruleForm.petisuserapply,
                petregtime: this.ruleForm.petregtime
              }).then(response => {
                if (response.data.reg) {
                  that.initData();
                  alert("登记成功!");
                  that.dialogFormVisible = false;
                }
              })
            }
            else if(m==1)
              alert("注意时间先后");
            else if(n==1||k==1)
              alert("时间与实际不符");
            else {
              alert("请确定同意用户登记宠物.")
            }
          } else {
            alert("请检查填写是否正确.")
          }
        })
      },
      reset() {
        this.$refs.ruleformpet.resetFields();
        this.ruleForm.fosterpricescale = '';
        this.ruleForm.petregtime = '';
        this.ruleForm.petnextvaccin='';
      },
      initData(){
        var _this = this;
        this.getRequest("/foster/getUpetReplyInfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },
      applychange(value){
        /*console.log(value);*/
        this.ruleForm.petisuserapply=value;
      },
      formatTimePet(time){
        this.ruleForm.petregtime=time;
        console.log(time)
      },
      changesex(value){
        /*console.log(value);*/
        this.ruleForm.petsex=value;
        console.log(this.ruleForm.petsex);
      },
      handleAvatarSuccesspet(res, file,filesList) {
        this.petimageUrl = URL.createObjectURL(file.raw);
        console.log( this.petimageUrl)
        this.ruleForm.petimageUrl = res.filename; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.petimageUrl);
      },
      formatTimepetPrevaccin(value){
        this.ruleForm.petprevaccin=value;
        let str=this.ruleForm.petprevaccin;
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
        this.ruleForm.petnextvaccin=this.dateFormat(newMessage);
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
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';
        const isLt1M = file.size / 1024 / 1024 < 1;
        if (!isJPG && !isGIF && !isPNG && !isBMP) {
          this.common.errorTip('上传图片必须是JPG/GIF/PNG/BMP 格式!');
        }
        if (!isLt1M) {
          this.common.errorTip('上传图片大小不能超过 2MB!');
        }
        return (isJPG || isBMP || isGIF || isPNG) && isLt1M;
      },
    }
  }
</script>




<style scoped>
  @import '../../../../static/css/dialog/upetreply.css';
  .itemgutter{
    padding: 30px;
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

