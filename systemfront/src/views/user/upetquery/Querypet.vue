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
        <td  class="itemgutter" >{{item.petrabidPrevaccin}}</td>
        <td  class="itemgutter" >{{item.petrabidNextvaccin}}</td>
        <td  class="itemgutter" >{{item.petrecodTime}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)" >查 看</el-button></td>
        <td  class="del"><el-button type="danger"  size="mini" @click="del(index)">删除</el-button></td>
      </tr>
      </tbody>
    </table>

    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 16px;margin-bottom: 20px;text-align: center">登记成功的宠物信息</div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" :rules="rulespet">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item class="elformitem" label="宠物姓名" prop="petname">
                <el-input  v-model="ruleForm.petname"  size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="宠物性别" prop="petsex">
                <el-radio-group v-model="ruleForm.petsex" @change="changesex">
                  <el-radio :label="'公'">公</el-radio>
                  <el-radio :label="'母'">母</el-radio>
                </el-radio-group>
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
              <el-form-item label="用户ID" prop="petuserid">
                <el-input v-model="ruleForm.petuserid" size="mini" clearable ></el-input>
              </el-form-item>
            </div>
            <div class="middle_right">
              <el-form-item>

                <img  class="avatar" :src="host+'/'+ruleForm.petimageUrl" alt=""/>
                <el-button class="imgdes" type="plain" size="mini">宠物照片</el-button>
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
              <el-form-item class="bottomright" label="下次打疫苗" >
                <!-- @change="formatTimepetNextvaccin"-->
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.petnextvaccin"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="下次应打疫苗的时间"
                  >
                </el-date-picker>
              </el-form-item>
            </div>
            <div class="bottomcenter">
              <el-form-item class="bottomcenterleft" label="宠物ID" prop="petid">
                <el-input v-model="ruleForm.petid" size="mini" clearable ></el-input>
              </el-form-item>
              <el-form-item class="bottomcenterright" label="登记时间" prop="useregtime">
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
          petname: '',
          petsex: '',
          petype: '',
          petuserid:'',
          petimageUrl: '',
          petprevaccin:'',
          petnextvaccin:'',
          petregtime: '',
          petid:''
        },
        rulespet:{
          petname:[{required: true, message: '必填:宠物姓名', trigger: 'blur'}],
          petsex:[{required: true, message: '必填:宠物性别', trigger: 'blur'}],
          petype: [{required: true, message: '必填:类型', trigger: 'blur'}],
          petuserid: [{required: true, message: '必填:宠物主人ID', trigger: 'blur'}],
          petprevaccin: [{required: true, message: '必填:上次打疫苗时间', trigger: 'blur'}]
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
      },
      userid:{
        default: "userid"
      }
    },
    methods: {
      edit(index){
        this.dialogFormVisible=true;
        var _this = this;
        /* console.log(this.bodyInfoList[index].userId);*/
        this.getRequest("/pet/getpetInfoById?index=" + this.bodyInfoList[index].petId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
            _this.ruleForm.petid=_this.ruleForm[0][0].petId;
            _this.ruleForm.petname=_this.ruleForm[0][0].petName;
            _this.ruleForm.petsex=_this.ruleForm[0][0].petSex;
            _this.ruleForm.petype=_this.ruleForm[0][0].petType;
            _this.ruleForm.petuserid=_this.ruleForm[0][0].petuserId;
            _this.ruleForm.petimageUrl=_this.ruleForm[0][0].petImg;
            _this.ruleForm.petprevaccin=_this.ruleForm[0][0].petrabidPrevaccin;
            _this.ruleForm.petnextvaccin=_this.ruleForm[0][0].petrabidNextvaccin;
            _this.ruleForm.petregtime=_this.ruleForm[0][0].petrecodTime;
          }
        })
      },
      del(index){
        var _this = this;
        console.log(this.bodyInfoList[index].petId);
        this.$confirm('此操作将删除宠物['+this.bodyInfoList[index].petName+']的信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pet/deletepetById?id="+this.bodyInfoList[index].petId).then(resp=> {
            if (resp.data.success) {
              alert(resp.data.data);
              _this.initData();
            }
          })
        }).catch(() => {
        });
      },
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      /*宠物登记*/
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
      reset(){
        this.$refs.ruleformpet.resetFields();
        this.ruleForm.petnextvaccin='';
        this.ruleForm.petregtime='';
        this.petimageUrl='';
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
      vaccinrecord(){
        var that=this;
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            var that = this;
            this.postRequest("/pet/vaccinrecord", {
              petid:this.ruleForm.petid,
              petname: this.ruleForm.petname,
              petsex: this.ruleForm.petsex,
              petype: this.ruleForm.petype,
              petuserid: this.ruleForm.petuserid,
              petimg: this.ruleForm.petimageUrl,
              petprevaccin: this.ruleForm.petprevaccin,
              petnextvaccin:this.ruleForm.petnextvaccin,
              petregtime:this.ruleForm.petregtime,
            }).then(response => {
              if (response.data.success) {
                alert(response.data.data);
                that.dialogFormVisible = false;
              }
              if(response.data.update){
                alert(response.data.data);
              }
            })
          }
          else {
            alert("请检查填写是否正确.")
          }
        })
      },
      updateinfo(){
        var that=this;
        this.$refs.ruleformpet.validate((valid) => {
          if (valid) {
            var that = this;
            this.postRequest("/pet/updatepetinfo", {
              petid:this.ruleForm.petid,
              petname: this.ruleForm.petname,
              petsex: this.ruleForm.petsex,
              petype: this.ruleForm.petype,
              petuserid: this.ruleForm.petuserid,
              petimg: this.ruleForm.petimageUrl,
              petprevaccin: this.ruleForm.petprevaccin,
              petnextvaccin:this.ruleForm.petnextvaccin,
              petregtime:this.ruleForm.petregtime,
            }).then(response => {
              if (response.data.update) {
                that.initData();
                alert(response.data.data);
                that.dialogFormVisible = false;
              }
              if(response.data.msg){
                alert(response.data.data);
                that.reset();
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
        this.getRequest("/user/getselfpetInfoById?info="+this.userid).then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
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

    },
  }
</script>

<style scoped>
  @import '../../../../static/css/dialog/queryregpet.css';
  .itemgutter{
    padding: 25px;
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

