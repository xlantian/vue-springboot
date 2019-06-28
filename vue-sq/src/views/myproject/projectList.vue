<template>
  <div class="app-container">
    <!-- 搜索条件 -->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="项目名称" v-model="listQuery.name">
      </el-input>
      <el-button class="filter-item" type="primary"  icon="search" @click="handleFilter">搜索</el-button>
      <el-button class="filter-item" v-if="listQuery.state==0" type="primary" @click="handleCreate"  icon="edit">添加一个项目</el-button>
    </div>
    <div>
        <el-tabs v-model="listQuery.state" @tab-click="handleClick">
          <el-tab-pane label="未入库项目" name="0"></el-tab-pane>
          <el-tab-pane label="已入库项目" name="1"></el-tab-pane>
        </el-tabs>
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

        <el-table-column label="状态"  align="center" width="100">
          <template scope="scope">
            <span v-if="scope.row.state==0" >未入库</span>
            <span v-if="scope.row.state==1" >已入库</span>
          </template>
        </el-table-column>>

          <el-table-column align="center" label="申请人" width="80">
              <template scope="scope">
                <span>{{scope.row.filler}}</span>
              </template>
          </el-table-column>

          <el-table-column  align="center" label="入库人" width="80">
              <template scope="scope">
                <span v-if="scope.row.state==1">{{scope.row.iner}}</span>
              </template>
          </el-table-column>

          <el-table-column align="center" width="200"  label="操作" >
              <template scope="scope">
                  <el-button :disabled="!isChecker" size="small" v-if="scope.row.state==0" @click="handleIn(scope.row.id)">入库</el-button>
                  <el-button :disabled="!isDecisionMaker" size="small" v-if="scope.row.state==1" @click="handleSure(scope.row.id)">立项</el-button>
                  <el-button size="small" @click="handleUpdate(scope.row.id,scope.row.filler)">修改</el-button>
                  <el-button :disabled="!isManager" size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
              </template>
          </el-table-column>
    </el-table>

    <el-dialog title="立项" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="checkInfo" label-position="left" label-width="100px" style='width: 500px; margin-left:50px;'>
        <el-form-item  label="机会研究">
          <el-input type="textarea" :rows="8" v-model="checkInfo.chance"></el-input>
        </el-form-item>

        <el-form-item label="设定优先级">
            <el-slider v-model="checkInfo.priority" :max="5" :step="1" show-stops></el-slider>
            <span>请您根据成本收益情况选择项目优先级，0最低，5最高</span>
        </el-form-item>
        <el-form-item label="立项人">
          <span>{{person.name}}</span>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        
        <el-button type="primary" @click="handleCheckInfo">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 分页 -->
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="listQuery.currPage"
        :page-sizes="[10]" :page-size="listQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>
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
        isChecker: false,
        isDecisionMaker: false,
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
          type: null,
          name: "",
          state: 0,
          currPage: 1,
          pageSize: 10,
        },
        checkInfo: {
          "id": null,
          "priority": null,
          "chance": "",
          "checker": ""
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
    if(vm.person.id==2)
      vm.isChecker=true;
    if(vm.person.id==3)
      vm.isDecisionMaker=true;
  },
  methods: {
    //获取列表数据
    getList() {
        let vm = this;
        vm.listLoading = true;
        global.get( api.projectList,{params: {'page':vm.listQuery.currPage,'state':vm.listQuery.state}},function(res){
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
    //要入库
    handleIn(id){
      let vm = this;
      let iner = vm.person.name; //设置入库人
       global.get( api.projectIn,{params: {'id':id,'iner':iner}},function(res){
           vm.getList();
        },function(res){
            vm.getList();
        },false)
    },
    //要立项
    handleSure(id){
       this.dialogFormVisible = true;
       this.checkInfo.id = id;
       this.checkInfo.checker = this.person.name;
    },
    //提交立项信息
    handleCheckInfo(){
        let vm = this;
        global.post(api.addProjectCheckInfo,vm.checkInfo,null,function(res){
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
        global.get(api.projectPart,{ params:{'state': vm.listQuery.state,'name': vm.listQuery.name,'p': vm.listQuery.currPage}},
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
    //新增
    handleCreate() {
        this.$router.push( { path: '/myproject/addProject'} ); 
    },
    handleClick(tab, event) {
      let vm = this;
      vm.getList();
    },
  }
};
</script>
<style>
.el-table{
  width: 753px;
}
</style>
