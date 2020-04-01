<template>
  <div>
  <section>
    <!--工具条-->

      <el-form :inline="true" >
        <el-form-item>
          <el-input v-model="keywords" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsers()">查询</el-button>
        </el-form-item>
      </el-form>
    <table :data="Data">
      <tr v-for="(item,index) in Data">
        <td>{{item[index].staffName}}</td>
        <td>{{item.staffSex}}</td>
        <td>{{item.staffTel}}</td>
      </tr>
    </table>


    <!--列表-->
    <!--<el-table :data="Data" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column type="index" width="60">
      </el-table-column>
      <el-table-column prop="staffname" label="姓名" width="120" sortable>
      </el-table-column>
      <el-table-column prop="staffsex" label="性别" width="120" :formatter="formatSex" sortable>
      </el-table-column>
      <el-table-column prop="staffimg" label="照片" width="120" sortable>
      </el-table-column>
      <el-table-column prop="stafftel" label="电话" width="120" sortable>
      </el-table-column>
      <el-table-column prop="staffidcard" label="身份证号" width="180" sortable>
      </el-table-column>
      <el-table-column prop="staffloginame" label="用户名" width="150" sortable>
      </el-table-column>
      <el-table-column prop="staffpass" label="密码" width="120" sortable>
      </el-table-column>
      <el-table-column prop="staffemail" label="邮箱" width="100" sortable>
      </el-table-column>
      <el-table-column prop="staffprop" label="权限" width="120" sortable>
      </el-table-column>
      <el-table-column prop="staffregtime" label="入住时间" width="180" sortable>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    &lt;!&ndash;工具条&ndash;&gt;
    <el-col :span="24" class="toolbar">
      <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
      </el-pagination>
    </el-col>
-->
    <!--编辑界面-->
   <!-- <el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-form-item label="姓名" prop="staffname">
          <el-input v-model="editForm.staffname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="editForm.staffsex">
            <el-radio class="radio" :label="1">男</el-radio>
            <el-radio class="radio" :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="editForm.age" :min="0" :max="200"></el-input-number>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birth"></el-date-picker>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="editForm.addr"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>
    -->

    <el-dialog class="Infodetaile" v-model="editFormVisible" :close-on-click-modal="false">
      <el-form :model="ruleForm" label-width="80px" ref="ruleformstaff">
        <div class="top">
          <div class="header">
            <el-form-item  label="员工姓名" prop="staffname">
              <el-input  v-model="ruleForm.staffname"  size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="" label="员工性别" prop="staffsex">
              <el-input  v-model="ruleForm.staffsex"  size="mini" clearable></el-input>
            </el-form-item>
          </div>
          <div class="picture">
            <div><img :src="ruleForm.staffimageUrl"/></div>
            <div class="userimg">员工头像</div>
          </div>
        </div>
        <div class="main">
          <div class="center">
            <el-form-item class="centerleft" label="员工电话" prop="stafftel">
              <el-input  v-model="ruleForm.stafftel" size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="centerright" label="员工邮箱" prop="staffemail">
              <el-input v-model="ruleForm.staffemail" size="mini" clearable></el-input>
            </el-form-item>
          </div>
          <div class="login">
            <el-form-item class="loginleft" label="用户名" prop="staffloginame">
              <el-input v-model="ruleForm.staffloginame" size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="loginright" label="密码" prop="staffpass">
              <el-input v-model="ruleForm.staffpass" show-password clearable size="mini"></el-input>
            </el-form-item>
          </div>
          <div class="authority">
            <el-form-item class="authorityleft"  label="身份证号" prop="staffidcard">
              <el-input v-model="ruleForm.staffidcard" size="mini" clearable></el-input>
            </el-form-item>
            <el-form-item class="authorityright" label="入职时间" prop="staffregtime">
              <el-date-picker
                class="datetime"
                v-model="ruleForm.staffregtime"
                size="mini"
                type="date"
                @change="formatTimeStaff"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">修 改</el-button>
        <el-button type="primary" plain size="small" @click="resetFormstaff()">重 置</el-button>
        <el-button @click.native="editFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </section>
  </div>
</template>

<script>
 /* import util from '../../common/js/util'
  //import NProgress from 'nprogress'
  import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';
*/
  export default {
    data() {
      return {
        keywords:'',
        Data: [],
        total: 0,
        page: 1,
        listLoading: false,
        sels: [],//列表选中列

        editFormVisible: false,//编辑界面是否显示
        editLoading: false,
        //编辑界面数据
        ruleForm: {
          staffname: '',
          staffsex: '',
          staffimageUrl: '',
          staffidcard: '',
          stafftel: '',
          staffemail: '',
          staffloginame: '',
          staffpass: '',
          staffregtime: '',
        },
      }
    },
    methods: {
      handleCurrentChange(val) {
        this.page = val;
        this.getUsers();
      },
      //获取用户列表
      getUsers() {
        this.listLoading = true;
        var _this = this;
        this.getRequest("/admin/getStaffInfoBykeywords?info=" + this.keywords).then(resp => {
          if (resp.data.getinfo) {
            _this.Data = resp.data.info;
            this.listLoading = false;
          } else {
            alert(resp.data.msg);
          }
        })
      },
      //删除
      handleDel: function (index, row) {
        var _this = this;
        console.log(index)
        this.$confirm('此操作将永久删除员工[' + row.staffName + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.listLoading = true;
          this.DeleteStaff(row.staffId);
        }).catch(() => {
        });
      },
      DeleteStaff(ids) {
        var _this = this;
        this.deleteRequest("/admin/deleteStaff?ids=" + ids).then(resp => {
          if (resp.data.delete) {
            this.listLoading = false;
            alert(resp.data.back);
            _this.initData();
            console.log(resp.data.data)
          } else {
            alert("操作失败.");
          }
        })
      },
      //显示编辑界面
      handleEdit: function (index, row) {
        this.editFormVisible = true;
        this.editForm = Object.assign({}, row);
      },

      //编辑
      editSubmit: function () {
        this.$refs.ruleformstaff.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true;
              //NProgress.start();
              let para = Object.assign({}, this.editForm);
              this.$refs['ruleformstaff'].resetFields();
              this.editFormVisible = false;
              this.getUsers();

            });
          }
        });
      },
      initData(){
        var _this = this;
        this.getRequest("/admin/getStaffInfo").then(resp=> {
          if (resp.data.getinfo) {
            _this.Data = resp.data.staffinfo;
            /*_this.ruleForm=resp.data.staffinfo;*/
          }
          else{

          }
        })
      },

      selsChange: function (sels) {
        this.sels = sels;
      },
      /*//批量删除
      batchRemove: function () {
        var ids = this.sels.map(item => item.id).toString();
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true;
          //NProgress.start();
          let para = { ids: ids };
          batchRemoveUser(para).then((res) => {
            this.listLoading = false;
            //NProgress.done();
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getUsers();
          });
        }).catch(() => {

        });
      }
    },*/
      mounted() {
        this.initData();
      }
    }
  }

</script>

<style scoped>
  @import "../../../static/css/dialog/userinfodialog.css";
</style>
