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
        <td  class="itemgutter" >{{item.userId}}</td>
        <td  class="itemgutter" >{{item.userName}}</td>
        <td  class="itemgutter" >{{item.userTel}}</td>
        <td  class="itemgutter" >{{item.time}}</td>
        <td  class="itemgutter" >{{item.place}}</td>
        <td  class="itemgutter" >{{item.tipTime}}</td>
        <td  class="opera"><el-button type="primary" size="mini" @click="edit(index)" >查 看</el-button></td>
        <td  class="del" ><el-button type="danger" size="mini" @click="del(index)" >删 除</el-button></td>
      </tr>
      </tbody>
    </table>

    <el-dialog class="dialog" :visible.sync="dialogFormVisible">
      <div style="color: #bc2a19;font-size: 14px;margin-bottom: 15px;text-align: center">
        {{ruleForm.username}}<span style="color: dodgerblue">发布的投诉信息</span></div>
      <div class="middle">
      <el-form class="register_message" label-width="100px" ref="ruleformpet" :model="ruleForm" >
        <div class="middle_bottom">
          <div class="topaddition">
            <el-form-item class="left"  label="用户ID" >
              <el-input    v-model="ruleForm.userid"  size="mini" clearable ></el-input>
            </el-form-item>
            <el-form-item class="right"  label="投诉ID" >
              <el-input    v-model="ruleForm.tipid"  size="mini" clearable></el-input>
            </el-form-item>
          </div>
          <div class="secondaddition">
            <el-form-item class="left"  label="用户姓名" >
              <el-input    v-model="ruleForm.username"  size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="right"  label="举报门牌号" >
              <el-input    v-model="ruleForm.tipdoor"  size="mini" clearable></el-input>
            </el-form-item>
          </div>
          <div class="bottomtop">
            <el-form-item class="left"  label="用户电话" >
              <el-input    v-model="ruleForm.usertel"  size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="right"  label="用户邮箱">
              <el-input    v-model="ruleForm.useremail"  size="mini" clearable></el-input>
            </el-form-item>
          </div>
          <div class="bottomcenter">
            <el-form-item class="left"  label="发生时间" >
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
            <el-form-item class="right" label="发生地点" >
              <el-input  v-model="ruleForm.place"  size="mini" clearable></el-input>
            </el-form-item>
          </div>

          <div class="bottombottom">
            <el-form-item class="left" label="详情描述" >
              <el-input type="textarea" v-model="ruleForm.detail" size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="right" label="投诉时间" >
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
      </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false"  size="mini">确 定</el-button>
        <el-button   @click="dialogFormVisible = false"  size="mini">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        dialogFormVisible:false,
        ruleForm: {
          tipid: '',
          userid: '',
          username: '',
          usertel: '',
          useremail: '',
          tipdoor: '',
          time: '',
          place: '',
          detail: '',
          tiptime: '',
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
      staffid:{
        default: "staffid"
      }
    },
    methods: {
      formatTimehappen(time){
        this.ruleForm.time=time;
        console.log(time);
      },
      formatTimetip(time){
        this.ruleForm.tiptime=time;
        console.log(time);
      },
      edit(index) {
        this.dialogFormVisible = true;
        var _this = this;
        this.getRequest("/user/getipinfoById?index=" + this.bodyInfoList[index].tipId).then(resp=> {
          if (resp.data.indexinfo) {
            _this.ruleForm[0] = resp.data.info;
            _this.ruleForm.tipid=_this.ruleForm[0][0].tipId;
            _this.ruleForm.userid=_this.ruleForm[0][0].userId;
            _this.ruleForm.username=_this.ruleForm[0][0].userName;
            _this.ruleForm.usertel=_this.ruleForm[0][0].userTel;
            _this.ruleForm.useremail=_this.ruleForm[0][0].userEmail;
            _this.ruleForm.tipdoor=_this.ruleForm[0][0].tipDoor;
            _this.ruleForm.time=_this.ruleForm[0][0].time;
            _this.ruleForm.place=_this.ruleForm[0][0].place;
            _this.ruleForm.detail=_this.ruleForm[0][0].detail;
            _this.ruleForm.tiptime=_this.ruleForm[0][0].tipTime;

          }
        })
      },
      del(index){
        var _this = this;
        console.log(index)
        /* console.log(this.bodyInfoList[index].adminId);*/
        this.$confirm('此操作将永久删除业主[' + this.bodyInfoList[index].userName + ']的投诉信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/user/deleteTip?id="+this.bodyInfoList[index].tipId).then(resp=> {
            if (resp.data.delete) {
              alert(resp.data.back);
              _this.initData();
            }
          })
        }).catch(() => {
        });
      },
      initData(){
        var _this = this;
        this.getRequest("/user/getipinfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.bodyInfoList = resp.data.info;
          }
        })
      },

    }
  }
</script>




<style scoped>
  @import "../../../../static/css/dialog/tipdialog.css";
  .itemgutter{
    padding: 30px;
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

