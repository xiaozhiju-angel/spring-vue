<template>
  <div class="middle">
    <div style="font-size:18px;padding-top: 30px">宠物寄养申请</div>
    <el-form class="register_message" label-width="100px" ref="ruleformfosterpet" :model="ruleForm" :rules="rulesfosterpet">
      <div class="middle_top">
        <div class="middle_left">
          <el-form-item class="elformitem" label="宠物ID" prop="petid">
            <el-input  v-model="ruleForm.petid"  size="mini" clearable></el-input>
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
            <el-input v-model="ruleForm.petuserid" size="mini" clearable disabled></el-input>
          </el-form-item>
          <el-form-item class="left" label="申请时间" >
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
          <el-form-item class="" label="起始日期" prop="begintime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.begintime"
              size="mini"
              type="date"
              @change="formatTimepetBeginfoster"
              value-format="yyyy-MM-dd"
              placeholder="寄养宠物起始时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item class="" label="结束日期"  prop="endtime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.endtime"
              size="mini"
              type="date"
              @change="formatTimepetEndfoster"
              value-format="yyyy-MM-dd"
              placeholder="寄养宠物结束时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item class=" " label="价钱规格" prop=" pricescale">
            <el-select v-model="ruleForm.pricescale" placeholder="请选择" size="mini" @change="changeprice">
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
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="small" type="primary" @click="onSubmitpet()">申 请</el-button>
          <el-button class="btn" size="small" plain  @click="resetFormpet()">重 置</el-button>
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
          petid: '',
          petype: '',
          petuserid:this.userid,
          begintime:'',
          endtime:'',
          pricescale:'',
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
          petid: [{required: true, message: '必填:宠物编号', trigger: 'blur'}],
          petuserid: [{required: true, message: '必填:宠物主人编号', trigger: 'blur'}],
          begintime: [{required: true, message: '必填:开始寄养宠物时间', trigger: 'blur'}],
          endtime: [{required: true, message: '必填:结束寄养宠物时间', trigger: 'blur'}],
          pricescale: [{required: true, message: '必填:宠物套餐', trigger: 'blur'}],
          fosterisuserapply: [{required: true, message: '必填:确认寄养申请', trigger: 'blur'}],
        },
      }
    },
    props:['userid'],
    methods: {
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

      changeprice(val){
        this.ruleForm.pricescale=val;
        console.log(val);
      },
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      /*宠物寄养申请*/
      /*changepayornot(value){
        /!*console.log(value);*!/
        this.ruleForm.payornot=value;
        console.log(this.ruleForm.payornot);
      },*/
      handleChange(value){
        this.ruleForm.petype=value;
        console.log(this.ruleForm.petype);
      },
      formatTimefoster(val){
        this.ruleForm.fostertime=val;
        console.log(val);
      },
      formatTimepetBeginfoster(val){
        this.ruleForm.begintime=val;
        console.log(val)
      },
      formatTimepetEndfoster(val){
        this.ruleForm.endtime=val;
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
      resetFormpet(){
        this.$refs.ruleformfosterpet.resetFields();
        this.ruleForm.pricescale='';
        this.ruleForm.fostertime='';
      },
      onSubmitpet(){
        var that=this;
        var m=this.datesize(this.ruleForm.begintime,this.ruleForm.endtime,this.ruleForm.fostertime);
        /*var n=this.datesize1(this.ruleForm.begintime,this.getNowFormatDate());
        var k=this.datesize1(this.ruleForm.endtime,this.getNowFormatDate());*/
        var w=this.datesize1(this.ruleForm.fostertime,this.getNowFormatDate());
       /* console.log(m);*/
        this.$refs.ruleformfosterpet.validate((valid) => {
          if (valid) {
            if(this.ruleForm.fosterisuserapply=='是'&&m==0&&w==0){
              this.postRequest("/pet/fosterpet",{
                petid:this.ruleForm.petid,
                petype: this.ruleForm.petype,
                petuserid:this.ruleForm.petuserid,
                begintime: this.ruleForm.begintime,
                endtime:this.ruleForm.endtime,
                pricescale:this.ruleForm.pricescale,
                fosterisuserapply:this.ruleForm.fosterisuserapply,
                fostertime:this.ruleForm.fostertime

              }).then(response=>{
                if (response.data.success) {
                  alert(response.data.fosterpet);
                  that.resetFormpet();
                }
                else if(response.data.notreg){
                  alert(response.data.data);
                }
                else if(response.data.repeat){
                  alert(response.data.data);
                }
                else if(response.data.foster){
                  alert(response.data.data);
                }
              })
            }
            else if(m==2){
              alert("请提前一点提交申请哦!");
            }
            else if(m==1){
              alert("请注意时间先后!");
            }
            else if(w==1)
              alert("时间不符合实际");
            else{
              alert("请确认申请!");
            }

          }
        })
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
     /* /!*MP4视频*!/
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
      },*/
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
  @import '../../../static/css/user/upetfoster.css';
</style>
