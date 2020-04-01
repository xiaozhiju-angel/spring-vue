<template>
  <!--后台管理员模块-->
  <div class="middle">
    <div style="font-size:18px;padding-top: 30px;padding-bottom:20px;">宠物拾到信息发布</div>
    <el-form  class="register_message"  label-width="100px" ref="ruleform" :model="ruleForm" :rules="rules"  >
      <div class="middle_top">
        <div class="middle_left">
          <el-form-item class="elformitem" label="用户ID" prop="userid">
            <el-input  v-model="ruleForm.userid"  size="mini" clearable disabled></el-input>
          </el-form-item>
          <el-form-item label="宠物性别" prop="petsex">
            <el-radio-group v-model="ruleForm.petsex" @change="changesex">
              <el-radio :label="'公'">公</el-radio>
              <el-radio :label="'母'">母</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="宠物状态" prop="petstatus">
            <el-select v-model="ruleForm.petstatus" placeholder="请选择"   size="mini" @change="changestatus">
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
          <el-form-item label="宠物类型" prop="petype">
            <el-select v-model="ruleForm.petype" placeholder="请选择" size="mini" @change="changepet" >
              <el-option
                v-for="item in types"
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
          <el-form-item prop="petimageUrl">
            <el-upload
              class="avatar-uploader"
              list-type="picture-card"
              name="file"
              action="http://localhost:8088//upload/uploadimg"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              border>
              <img v-if="petimageUrl" :src="petimageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-button class="imgdes" size="mini" type="plain">宠物照片</el-button>
          </el-form-item>
        </div>
      </div>
      <div class="middle_bottom">
        <div class="bottomtop">
          <el-form-item class="bottomleft"  label="拾到时间" prop="picktime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.picktime"
              size="mini"
              type="date"
              @change="pickupetime"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          </el-form-item>
          <el-form-item class="bottomright" label="拾到地点" prop="pickplace">
            <el-input  v-model="ruleForm.pickplace"  clearable size="mini"> </el-input>
          </el-form-item>
        </div>
        <div class="bottomcenter">
          <el-form-item class="bottomcenterleft"  label="详情描述" prop="pickdetail">
            <el-input type="textarea" v-model="ruleForm.pickdetail" clearable size="mini"></el-input>
          </el-form-item>
          <el-form-item class="bottomcenterright" label="发布时间"  prop="pubpicktime">
            <el-date-picker
              class="datetime"
              v-model="ruleForm.pubpicktime"
              size="mini"
              type="date"
              @change="formatTime"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
        </div>
      </div>
      <div class="btn_bottom">
        <el-form-item>
          <el-button class="btn" size="small" type="primary" @click="publishuser()">发 布</el-button>
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
        petimageUrl:'',
        types: [
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
        options: [
          {
            value: '业主家',
            label: '业主家',
          },
          {
            value: '物业处',
            label: '物业处',
          },
          {
            value: '爱心机构',
            label: '爱心机构'
          }],
        ruleForm: {
          userid:this.userid,
          petsex:'',
          picktime:'',
          petype:'',
          petstatus:'',
          petimageUrl:'',
          pickplace:'',
          pubpicktime:'',
          pickdetail:'',
        },
        rules:{
          userid: [{required: true, message: '必填:拾到者ID', trigger: 'blur'}],
          petsex:[{ required: true, message: '必填:宠物性别', trigger: 'blur' }],
          petstatus:[{ required: true, message: '必填:宠物现在何处', trigger: 'blur' }],
          petype:[{ required: true, message: '必填:宠物类型', trigger: 'blur' }],
        },
      };
    },
    props:['userid'],
    methods: {
      changepet(val){
        this.ruleForm.petype=val;
        console.log(val);
      },
      changestatus(val){
        this.ruleForm.petstatus=val;
        console.log(val);
      },

      formatTime(time){
        this.ruleForm.pubpicktime=time;
        console.log(time)
      },
      pickupetime(time){
        this.ruleForm.picktime=time;
        console.log(time)
      },
      changesex(value){
        this.ruleForm.petsex=value;
        console.log( this.ruleForm.petsex);
      },
      handleAvatarSuccess(res, file,filesList) {
        this.petimageUrl = URL.createObjectURL(file.raw);
        console.log( this.petimageUrl)
        console.log( filesList);
        this.ruleForm.petimageUrl = res.filename; //将返回的文件储存路径赋值picture字段
        console.log(this.ruleForm.petimageUrl);
      },
      reset(){
        this.$refs.ruleform.resetFields();
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
      publishuser(){
        var that=this;
        var m=this.datesize(this.ruleForm.picktime,this.ruleForm.pubpicktime);
        var n=this.datesize(this.ruleForm.picktime,this.getNowFormatDate());
        var k=this.datesize(this.ruleForm.pubpicktime,this.getNowFormatDate());
        this.$refs.ruleform.validate((valid) => {
          if (valid) {
            if(m==0&&n==0&&k==0){
              this.postRequest("/pick/userpubpick",{
                userid:this.ruleForm.userid,
                petsex:this.ruleForm.petsex,
                petype:this.ruleForm.petype,
                petstatus:this.ruleForm.petstatus,
                petimg:this.ruleForm.petimageUrl,
                picktime:this.ruleForm.picktime,
                pickplace:this.ruleForm.pickplace,
                pickdetail:this.ruleForm.pickdetail,
                pubpicktime:this.ruleForm.pubpicktime,
              }).then(response=>{
                if (response.data.success) {
                  alert(response.data.data);
                  that.reset();
                }
                if(response.data.notreg){
                  alert(response.data.data);
                }
              })
            }
            else if(m==1)
              alert("注意时间先后");
            else  if(n==1||k==1)
              alert("时间不符合实际");
          }
          else {
            console.log('请检查是否填写正确');
            return false;
          }
        });
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
  @import '../../../static/css/user/upetpickpublish.css';
</style>
