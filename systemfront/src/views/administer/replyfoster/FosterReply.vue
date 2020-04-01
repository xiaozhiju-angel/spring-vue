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
        <td  class="itemgutter" >{{item.fosterpetId}}</td>
        <td  class="itemgutter" >{{item.fosteruserId}}</td>
        <td  class="itemgutter" >{{item.fosterpetType}}</td>
        <td  class="itemgutter" >{{item.fosterPrice}}</td>
        <td  class="itemgutter" >{{item.fosterpetbeginTime}}</td>
        <td  class="itemgutter" >{{item.fosterpetendTime}}</td>
        <td  class="itemgutter" >{{item.fostertime}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)" >同意</el-button></td>
        <td  class="del"><el-button type="danger"  size="mini" @click="refuse(index)">拒绝</el-button></td>
      </tr>
      </tbody>
    </table>
    <!--<div v-if="showEdit">
      <edit-table></edit-table>
    </div>-->

    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #0bbbff;font-size: 16px;margin-bottom: 20px;text-align: center">批复宠物寄养</div>
      <div class="middle">
        <el-form class="register_message" label-width="100px" ref="ruleformfosterpet" :model="ruleForm" :rules="rulesfosterpet">
          <div class="middle_top">
            <div class="middle_left">
              <el-form-item class="elformitem" label="寄养ID" >
                <el-input  v-model="ruleForm.fosterid"  size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item class="elformitem" label="宠物ID" >
                <el-input  v-model="ruleForm.fosterpetid"  size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item label="用户ID" >
                <el-input v-model="ruleForm.fosteruserid" size="mini" clearable disabled></el-input>
              </el-form-item>
              <el-form-item label="宠物类型" prop="fosterpetype">
                <el-select v-model="ruleForm.fosterpetype" placeholder="请选择" size="mini" @change="changepet">
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
              <el-form-item class="left" label="寄养时间" prop="fostertime">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.fostertime"
                  size="mini"
                  type="date"
                  @change="formatTimefoster"
                  value-format="yyyy-MM-dd"
                  placeholder="申请寄养时间">
                </el-date-picker>
              </el-form-item>
            </div>
            <div class="middle_right">
              <el-form-item class="" label="起始日期" prop="fosterbegintime">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.fosterbegintime"
                  size="mini"
                  type="date"
                  @change="formatTimepetBeginfoster"
                  value-format="yyyy-MM-dd"
                  placeholder="寄养宠物起始时间">
                </el-date-picker>
              </el-form-item>
              <el-form-item class="" label="结束日期"  prop="fosterendtime">
                <el-date-picker
                  class="datetime"
                  v-model="ruleForm.fosterendtime"
                  size="mini"
                  type="date"
                  @change="formatTimepetEndfoster"
                  value-format="yyyy-MM-dd"
                  placeholder="寄养宠物结束时间">
                </el-date-picker>
              </el-form-item>
              <el-form-item class=" " label="价钱规格" prop="fosterpricescale">
                <el-select v-model="ruleForm.fosterpricescale" placeholder="请选择" size="mini" @change="changeprice">
                  <el-option
                    v-for="item in prices"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    <span style="float: left">{{ item.label }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item class=""  label="申请寄养" prop="fosterisuserapply">
                <el-radio-group v-model="ruleForm.fosterisuserapply" @change="applychange" >
                  <el-radio class="applyornot" :label="'是'">是</el-radio>
                  <el-radio :label="'否'">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateinfo()" size="mini">寄 养</el-button>
        <el-button  @click="reset()"  plain size="mini">重 置</el-button>
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
        fileList: [],
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
          fosterid:'',
          fosterpetid: '',
          fosterpetype: '',
          fosteruserid:'',
          fosterbegintime:'',
          fosterendtime:'',
          fosterpricescale:'',
          fosterisuserapply:'',
          fostertime: '',
        },
        prices: [{
          value: '40',
          label: '早餐'
        }, {
          value: '70',
          label: '早中餐'
        }, {
          value: '90',
          label: '早中晚餐'
        }],
        rulesfosterpet: {
          fosterpetype:[{required: true, message: '必填:宠物类型', trigger: 'blur'}],
          fosterid:[{required: true, message: '必填:寄养编号', trigger: 'blur'}],
          fosterpetid: [{required: true, message: '必填:宠物编号', trigger: 'blur'}],
          fosteruserid: [{required: true, message: '必填:宠物主人编号', trigger: 'blur'}],
          fosterbegintime: [{required: true, message: '必填:开始寄养宠物时间', trigger: 'blur'}],
          fosterendtime: [{required: true, message: '必填:结束寄养宠物时间', trigger: 'blur'}],
          fosterpricescale: [{required: true, message: '必填:宠物套餐', trigger: 'blur'}],
          fosterisuserapply: [{required: true, message: '必填:确认寄养申请', trigger: 'blur'}],
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
        this.ruleForm.fosterpetype=val;
        console.log(val);
      },
      edit(index){
        this.dialogFormVisible=true;
        var _this = this;
        /* console.log(this.bodyInfoList[index].userId);*/
        this.getRequest("/foster/getfosterInfoById?index=" + this.bodyInfoList[index].fosterId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            console.log(resp.data.info);
            _this.ruleForm.fosterid=_this.ruleForm[0][0].fosterId;
            _this.ruleForm.fosterpetid=_this.ruleForm[0][0].fosterpetId;
            _this.ruleForm.fosterpetype=_this.ruleForm[0][0].fosterpetType;
            _this.ruleForm.fosteruserid=_this.ruleForm[0][0].fosteruserId;
            _this.ruleForm.fosterbegintime=_this.ruleForm[0][0].fosterpetbeginTime;
            _this.ruleForm.fosterendtime=_this.ruleForm[0][0].fosterpetendTime;
            _this.ruleForm.fosterpricescale=_this.ruleForm[0][0].fosterPrice;
            _this.ruleForm.fosterisuserapply=_this.ruleForm[0][0].fosterpetisuserApply;
            _this.ruleForm.fostertime=_this.ruleForm[0][0].fostertime;
          }
        })
      },
      refuse(index){
        var _this = this;
        console.log(index)
        console.log(this.bodyInfoList[index].fosterId);
        this.$confirm('此操作将删除该宠物的寄养申请, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/foster/refusefosterapplyById?id="+this.bodyInfoList[index].fosterId).then(resp=> {
            if (resp.data.refuse) {
              alert(resp.data.back);
              _this.initData();
            }
            if (resp.data.fail) {
              alert(resp.data.back);
              _this.initData();
            }

          })
        }).catch(() => {
        });
      },
      datesize(date1,date2,date3) {
        var start = new Date(date1.replace("-", "/").replace("-", "/"));
        console.log(start.getTime());
        var end = new Date(date2.replace("-", "/").replace("-", "/"));
        console.log(end.getTime());
        var apply = new Date(date3.replace("-", "/").replace("-", "/"));
        console.log(apply.getTime());
        if(start.getTime() < apply.getTime()){
          return 2;
        }
        if (end.getTime() < start.getTime()){
          return 1;
        }else return 0;
      },
      datesize1(date1,date2) {
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
        var m=this.datesize(this.ruleForm.fosterbegintime,this.ruleForm.fosterendtime,this.ruleForm.fostertime);
        /*var n=this.datesize1(this.ruleForm.fosterbegintime,this.getNowFormatDate());
        var k=this.datesize1(this.ruleForm.fosterendtime,this.getNowFormatDate());*/
        var w=this.datesize1(this.ruleForm.fostertime,this.getNowFormatDate());
        console.log(m);
        this.$refs.ruleformfosterpet.validate((valid) => {
          if (valid) {
              if (this.ruleForm.fosterisuserapply == "否"&&m==0&&w==0) {
                this.putRequest("/foster/admitapplyfoster", {
                  fosterid: this.ruleForm.fosterid,
                  fosterpetid: this.ruleForm.fosterpetid,
                  fosterpetype: this.ruleForm.fosterpetype,
                  fosteruserid: this.ruleForm.fosteruserid,
                  fosterbegintime: this.ruleForm.fosterbegintime,
                  fosterendtime: this.ruleForm.fosterendtime,
                  fosterpricescale: this.ruleForm.fosterpricescale,
                  fosterisuserapply: this.ruleForm.fosterisuserapply,
                  fostertime: this.ruleForm.fostertime
                }).then(response => {
                  if (response.data.foster) {
                    that.initData();
                    alert(response.data.data);
                    that.dialogFormVisible = false;
                  }
                })
              }
              else if(m==2||m==1){
                alert("请注意时间先后");
              }
              else if(w==1)
                alert("时间不符合实际");
              else
                alert("请确定同意寄养.");

          }
          else {
            alert("请检查填写是否正确.")
          }
        })
      },

      initData(){
        var _this = this;
        this.getRequest("/foster/getApplyfosterInfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },
      changeprice(val){
        this.ruleForm.fosterpricescale=val;
        console.log(val);
      },
      /*宠物寄养申请*/
      /*changepayornot(value){
        /!*console.log(value);*!/
        this.ruleForm.payornot=value;
        console.log(this.ruleForm.payornot);
      },*/
      handleChange(value){
        this.ruleForm.fosterpetype=value;
        console.log(this.ruleForm.petype);
      },
      formatTimefoster(val){
        this.ruleForm.fostertime=val;
        console.log(val);
      },
      formatTimepetBeginfoster(val){
        this.ruleForm.fosterbegintime=val;
        console.log(val)
      },
      formatTimepetEndfoster(val){
        this.ruleForm.fosterendtime=val;
        console.log(val)
      },
      applychange(value){
        this.ruleForm.fosterisuserapply=value;
        console.log(value);
      },
      /*  userchangesex(value){
          /!*console.log(value);*!/
          this.ruleForm.petsex=value;
          console.log(this.ruleForm.petsex);
        },*/
      /*handleAvatarSuccessfosterpet(res, file,filesList) {
        this.petimageUrl = URL.createObjectURL(file.raw);
        console.log( this.petimageUrl)
        this.ruleForm.petimageUrl = res; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.petimageUrl);
      },*/
      reset(){
        this.$refs.ruleformfosterpet.resetFields();
       /* this.ruleForm.fosterpricescale='';
        this.ruleForm.fostertime='';*/
      },


      /*计算相差填天数*/
      daycompute(){
        let start=this.ruleForm.begintime;
        let end=this.resetForm.endtime;
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
      /*MP4视频*/
      UploadVideo(param){
        const formData = new FormData()
        formData.append('file', param.file)
        this.uploadVideoRequest("/upload/uploaduser", formData).then(response => {
          console.log('上传图片成功')
          param.onSuccess()  // 上传成功的图片会显示绿色的对勾
          // 但是我们上传成功了图片， fileList 里面的值却没有改变，还好有on-change指令可以使用
        }).catch(response => {
          console.log('图片上传失败')
          param.onError()
        })
      },
      /* fileChange(file){
         this.$refs.petrecordvideo.clearFiles() //清除文件对象
         this.fostervideo = file.raw // 取出上传文件的对象，在其它地方也可以使用
         this.fileList = [{name: file.name, url: file.url}] // 重新手动赋值filstList， 免得自定义上传成功了, 而fileList并没有动态改变， 这样每次都是上传一个对象
       },*/

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
  @import '../../../../static/css/dialog/addapplyfosterdialog.css';
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

