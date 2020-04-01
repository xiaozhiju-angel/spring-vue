<template>
  <div class="middle">
    <div style="font-size:18px;padding: 30px 0px">宠物寄养记录</div>
    <el-form class="register_message" label-width="100px" ref="ruleformfosterpet" :model="ruleForm" :rules="rulesfosterpet">
      <div class="middle_top">
        <div class="middle_left">
          <el-form-item class="elformitem" label="宠物姓名" prop="petname">
            <el-input  v-model="ruleForm.petname"  size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item label="宠物性别" prop="petsex">
            <el-radio-group v-model="ruleForm.petsex">
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
        </div>
        <div class="middle_right">
          <el-form-item>
            <el-upload
              class="avatar-uploader"
              ref="petrecordvideo"
              action="string"
              accept=".mp4"
              list-type="picture-card"
              :http-request="UploadVideo"
              :on-change="fileChange"
              :file-list="fileList"
              border
              disabled>
              <img v-if="petvideo" :src="petvideo" class="avatar">
               <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button class="imgdes" type="plain" size="mini">视频记录</el-button>
          </el-form-item>
        </div>
      </div>
      <div class="middle_bottom">
        <div class="topaddition">
          <el-form-item class="left" label="用户姓名" prop="username">
            <el-input v-model="ruleForm.username" size="mini" clearable></el-input>
          </el-form-item>
          <el-form-item class="right" label="用户电话" >
            <el-input v-model="ruleForm.usertel" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="secondaddition">
          <el-form-item class="left"  label="用户性别">
            <el-radio-group v-model="ruleForm.usersex" @change="applychange" >
              <el-radio class="applyornot" :label="'男'">男</el-radio>
              <el-radio :label="'女'">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item class="right" label="用户邮箱" >
            <el-input v-model="ruleForm.usertel" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="bottomtop">
          <el-form-item class="bottomleft" label="起始日期" >
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
          <el-form-item class="bottomright" label="结束日期" >
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
        </div>
        <div class="bottomcenter">
          <el-form-item class="bottomcenterleft" label="价钱规格" prop="useregtime">
            <el-select v-model="ruleForm.pricescale" placeholder="请选择" size="mini" >
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
          <el-form-item  class="bottomcenterright" label="应付费用" prop="userid">
            <el-input v-model="ruleForm.payprice" size="mini" clearable></el-input>
          </el-form-item>
        </div>
        <div class="addition">
          <el-form-item class="left" label="寄养时间" >
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
          <el-form-item class="right" label="是否付费">
            <el-radio-group  v-model="ruleForm.payornot" @change="changepayornot">
              <el-radio class="payornot" :label="'已付'">已付费</el-radio>
              <el-radio :label="'未付'">未付费</el-radio>
            </el-radio-group>
          </el-form-item>
        </div>
      </div>
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="small" type="primary" @click="onSubmitpet()">申请</el-button>
          <el-button class="btn" size="small" plain  @click="resetFormpet()">重置</el-button>
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
        petvideo:'',
        ruleForm: {
          petname: '',
          petsex:'',
          petype: '',
          username:'',
          usersex:'',
          usertel:'',
          useremail:'',
          petvideo: '',
          begintime:'',
          endtime:'',
          pricescale: '',
          payprice:'',
          payornot:''
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
        ruleForm: {
          petid: '',
          petype: [],
          petuserid:'',
          petvideo: '',
          beginame:'',
          endtime:'',
          pricescale:'',
          fosterisuserapply:'',
          fostertime: '',
          payornot:''
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

        },

      }
    },
    methods: {
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      /*宠物登记*/
      changepayornot(value){
        /*console.log(value);*/
        this.ruleForm.payornot=value;
        console.log(this.ruleForm.payornot);
      },
      handleChange(value){
        this.ruleForm.petype=value;
        console.log(this.ruleForm.petype);
      },
      formatTimefoster(){
        this.ruleForm.fostertime=time;
      },
      formatTimepetBeginfoster(time){
        this.ruleForm.petprevaccin=time;
        console.log(time)
      },
      formatTimepetEndfoster(time){
        this.ruleForm.nextvaccin=time;
        console.log(time)
      },
      userchangesex(value){
        /*console.log(value);*/
        this.ruleForm.petsex=value;
        console.log(this.ruleForm.petsex);
      },
      handleAvatarSuccessfosterpet(res, file,filesList) {
        this.petimageUrl = URL.createObjectURL(file.raw);
        console.log( this.petimageUrl)
        this.ruleForm.petimageUrl = res; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.petimageUrl);
      },
      resetFormpet(){
        this.$refs.ruleformfosterpet.resetFields();
      },
      onSubmitpet(){
        var that=this;
        this.$refs.ruleformfosterpet.validate((valid) => {
          if (valid) {
            this.postRequest("/user/savepet",{
              petname:this.ruleForm.petname,
              petsex: this.ruleForm.petsex,
              userid:this.ruleForm.userid,
              petimg: this.ruleForm.petimageUrl,
              petype:this.ruleForm.petype,
              petprevaccin:this.ruleForm.petprevaccin,
              petnextvaccin:this.ruleForm.petnextvaccin,
              petregtime: this.ruleForm.petregtime,
            }).then(response=>{
              if (response.data.success) {
                that.$router.push({
                  path:'/login'
                });
                alert("注册成功,请登录");
              }
              else{
                alert(response.data.repeat);
              }
            })
          }
          else {
            console.log('请检查是否填写正确');
            return false;
          }
        });
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
      fileChange(file){
        this.$refs.petrecordvideo.clearFiles() //清除文件对象
        this.fostervideo = file.raw // 取出上传文件的对象，在其它地方也可以使用
        this.fileList = [{name: file.name, url: file.url}] // 重新手动赋值filstList， 免得自定义上传成功了, 而fileList并没有动态改变， 这样每次都是上传一个对象
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
  @import '../../../../static/css/admin/fosterecord.css';
</style>
