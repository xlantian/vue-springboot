<template>
  <div class="app-container">
     <div class="filter-container">
      <el-button :disabled="isDisabled" class="filter-item" type="primary" @click="handleAdd"  icon="edit">新建用户</el-button>
    </div>
    <!-- 表格 -->
    <el-table ref="multipleTable" :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit highlight-current-row>
          <el-table-column align="center" label='工号' width="150">
            <template scope="scope">
              {{scope.row.number}}
            </template>
          </el-table-column>

          <el-table-column label="真实姓名" width="120">
            <template scope="scope">
              <span>{{scope.row.name}}</span>
            </template>
          </el-table-column>

            <el-table-column align="center" label="身份" width="120">
                <template scope="scope">
                  <span v-if="scope.row.id==1">填写人员</span>
                  <span v-if="scope.row.id==0">管理员</span>
                  <span v-if="scope.row.id==2">审核人员</span>
                  <span v-if="scope.row.id==3">决策人</span>
                </template>
            </el-table-column>

            <el-table-column align="center" width="300"  label="操作" >
                <template scope="scope">
                   <el-button :disabled="isDisabled" size="small" @click="handleChange(scope.row.number)">修改权限</el-button>
                   <el-button :disabled="isDisabled" size="small" type="danger" @click="handleDelete(scope.row.number)">删除</el-button>
                </template>
            </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination  @current-change="handleCurrentChange" :current-page.sync="listQuery.currPage"
        :page-sizes="[10]" :page-size="listQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>
    <!-- 添加弹窗 -->
    <el-dialog title="添加一个用户" :visible.sync="dialogFormVisible">
          <el-form class="small-space" :model="user" label-position="left" label-width="70px" style='width: 300px; margin-left:50px;'>
            <el-form-item label="工号">
              <el-input v-model="user.number"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名">
              <el-input v-model="user.name"></el-input>
            </el-form-item>
            <el-form-item label="权限">
              <el-select clearable class="filter-item" style="width: 130px" v-model="user.id" placeholder="权限设置">
                <el-option v-for="item in  typeOptions" :key="item.key" :label="item.display_name" :value="item.key">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleCreate">确 定</el-button>
          </div>
    </el-dialog>

    <el-dialog title="修改权限" :visible.sync="dialogVisible">
          <el-form class="small-space" :model="user" label-position="left" label-width="70px" style='width: 300px; margin-left:50px;'>
            <el-form-item label="权限">
              <el-select clearable class="filter-item" style="width: 130px" v-model="user.id" placeholder="权限设置">
                <el-option v-for="item in  typeOptions" :key="item.key" :label="item.display_name" :value="item.key">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleUpdate">确 定</el-button>
          </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { Message } from 'element-ui';
import {global} from 'src/global/global';
import {api} from 'src/global/api';

export default {
  data() {
    return {
        person: {},
        list: null,
        total: null,
        listLoading: true,
        isDisabled: false,

        listQuery: {
          type: null,
          name: "",
          currPage: 1,
          pageSize: 10,
        },
        user: {
          "number": "",
          "name": "",
          "pwd": "",
          "id": null
        },
        typeOptions:[
          { key: '0', display_name: '管理员' },
          { key: '1', display_name: '填写人员' },
          { key: '2', display_name: '审核人员' },
          { key: '3', display_name: '决策人员' },
        ],
        dialogFormVisible: false,
        dialogVisible: false,
        multipleSelection: []
    }
  },
  computed: {
        ...mapGetters({
            personalInfo_init:'userInfo'
        })
    },
  mounted() {
    let vm = this;
    vm.getList();
    vm.person = JSON.parse( JSON.stringify(vm.personalInfo_init) );
    if(vm.person.id!=0)
    vm.isDisabled = true;
  },
  methods: {
    //获取列表数据
    getList() {
        let vm = this;
        vm.listLoading = true;
        global.get( api.userList,{params: {'page': vm.listQuery.currPage } },function(res){
              let data = res.body;
              console.log('列表数据：',data); 
              vm.list = data;
              vm.listLoading = false; 
        },function(res){
            vm.listLoading = false;
        },false)
    },
    handleAdd(){
       this.dialogFormVisible = true;
    },
    handleCreate(){
        let vm = this;
        global.post(api.addUser,vm.user,null,function(res){
           Message({
              showClose: true,
              message: '提交成功',
              type: 'success'
          })
          vm.getList();
      },function(){
        alert("提交失败！，可能该工号已经存在！");
      })
      this.dialogFormVisible = false;
    },
    handleChange(index){
      this.dialogVisible = true;
      this.user.number = index;
    },
    handleUpdate(index){
      let vm  = this;
      global.post(api.updateUser,this.user,null,function(res){
           Message({
              showClose: true,
              message: '提交成功',
              type: 'success'
          })
          vm.getList();
      },function(){})
      vm.dialogVisible = false;
    },
    //单个删除
    handleDelete(index){
      let vm = this;
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
          global.get( api.deleteUser,{params: {'number': index} },function(res){ 
            Message({
            showClose: true,
            message: '删除成功！',
            type: 'success'
            })
            vm.getList();
          },function(res){
              alert('删除报错');
              vm.listLoading = false;
          },false)
        }).catch(() => {
             this.$message({
               type: 'info',
               message: '已取消删除'
             });          
        });
    },
    //操作分页
    handleCurrentChange(val) {
        console.log('--------',val)
      this.listQuery.currPage = val;
      this.getList();
    },
  }
};
</script>
<style>
.el-table{
  width: 693px;
}
</style>
