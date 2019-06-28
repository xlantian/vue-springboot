<template>
  <div class="app-container">
    <!-- 搜索条件 -->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="项目名称" v-model="listQuery.name">
      </el-input>
      <el-button class="filter-item" type="primary"  icon="search" @click="handleFilter">搜索</el-button>
    </div>
    <!-- 表格 -->
    <el-table ref="multipleTable" :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit highlight-current-row>
          <el-table-column align="center" label='项目id' width="80">
            <template scope="scope">
              {{scope.row.id}}
            </template>
          </el-table-column>

          <el-table-column label="项目名称" width="210">
            <template scope="scope">
              <el-button type="text" @click="handleDetail(scope.row.id)">{{scope.row.name}}</el-button>
            </template>
          </el-table-column>

          <el-table-column  label="状态" width="140">
            <template scope="scope">
              <span>已立项，执行中</span>
            </template>
          </el-table-column>

          <el-table-column label="优先级"  align="center" width="100">
            <template scope="scope">
              {{scope.row.priority}}
            </template>
          </el-table-column>

          <el-table-column align="center" prop="created_at" label="立项人" width="80">
            <template scope="scope">
              <span>{{scope.row.checker}}</span>
            </template>
          </el-table-column>

          <el-table-column label="入库人" width="100">
            <template scope="scope">
              <span>{{scope.row.iner}}</span>
            </template>
          </el-table-column>

            <el-table-column align="center" label="申请人" width="80">
                <template scope="scope">
                  <span>{{scope.row.filler}}</span>
                </template>
            </el-table-column>

            <el-table-column align="center" width="200"  label="操作" >
                <template scope="scope">
                   <el-button size="small" @click="handleEvent(scope.row.id)">事件</el-button>
                   <el-button size="small" @click="handleUpdate(scope.row.id,scope.row.filler)">修改</el-button>
                   <el-button :disabled="!isManager" size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="listQuery.currPage"
        :page-sizes="[10]" :page-size="listQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>
    <!-- 审核弹窗 -->
    <el-dialog title="记录事件" :visible.sync="dialogFormVisible">
          <el-form class="small-space" :model="event" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>

            <el-form-item label="事件时间">
              <el-date-picker @change="dateChange" v-model="event.date" align="right" type="date" placeholder="选择日期"  value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
            <el-form-item  label="事件内容">
              <el-input :rows="5" type="textarea" v-model="event.content"></el-input>
            </el-form-item>
            <el-form-item label="记录人">
              <span>{{person.name}}</span>
            </el-form-item>
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
           
            <el-button type="primary" @click="handleEventSubmit">确 定</el-button>
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
        isManager:false,
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
            currPage: 1,
            pageSize: 10,
            name: '',
        },
        event: {
          "pid":null,
          "id": null,
          "date": "2019-12-27",
          "content": "",
          "recorder": ""
        },
        dialogFormVisible: false,
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
    if(vm.person.id==0)
      vm.isManager=true;
  },
  methods: {
    getList() {
        let vm = this;
        vm.listLoading = true;
        global.get( api.projectList,{params: {'page': vm.listQuery.currPage ,'state':2} },function(res){
            let data = res.body;
            console.log('列表数据：',data); 
            vm.list = data;
            vm.listLoading = false; 
        },function(res){
            vm.listLoading = false;
        },false)
    },
    handleDetail(index){
       this.$router.push( { path: '/myproject/projectDetail',query: {id: index}} );
    },
    dateChange(val){
      this.event.date = val;
    },
    //要记录事件
    handleEvent(index){
      let vm = this;
      vm.event.pid = index;
      vm.event.recorder = vm.person.name;
      global.get( api.maxEventId,{},function(res){
                vm.event.id = res.body+1;
                console.log("下条事件的ID："+vm.event.id);
        },function(res){
        },false)
      this.dialogFormVisible = true;
    },
    //提交事件
    handleEventSubmit(){
        let vm = this;
        global.post(api.addProjectEvent,vm.event,null,function(res){
           Message({
              showClose: true,
              message: '提交成功',
              type: 'success'
          })
          vm.getList();
      },function(){})
      this.dialogFormVisible = false;
    },
    handleUpdate(index,fname){
      if(this.person.name == fname)
        this.$router.push( { path: '/myproject/updateProject',query: {id: index}} );
      else
        alert("只能修改你自己的哦！");
    },
    //单个删除
    handleDelete(index){
      let vm = this;
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
          global.get( api.deleteProjectEvent,{params: {'pid': index} },function(res){
            console.log("已删除项目"+index+"的所有事件");
          },function(res){},false)
          global.get( api.deleteProject,{params: {'id': index} },function(res){ 
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
    //搜索
    handleFilter() {
      let vm = this;
        vm.listLoading = true;
        global.get(api.projectPart,{ params:{'state': 2,'name': vm.listQuery.name,'p': vm.listQuery.currPage}},
        function(res){
              let data = res.body;
              vm.list = data;
              vm.listLoading = false; 
        },function(res){
            vm.listLoading = false;
        },false)
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
  width: 993px;
}
</style>
