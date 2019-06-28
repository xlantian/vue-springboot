<template>
  <div class="app-container">
    <!-- 搜索条件 -->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="预算名称" v-model="listQuery.name">
      </el-input>
      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.type" placeholder="预算范围">
        <el-option v-for="item in  typeOptions" :key="item.key" :label="item.display_name" :value="item.key">
        </el-option>
      </el-select>

      <el-button class="filter-item" type="primary"  icon="search" @click="handleFilter()">搜索</el-button>
      <el-button class="filter-item" type="primary" @click="handleCreate"  icon="edit">添加一个预算</el-button>
    </div>
    <!-- 表格 -->
    <el-table ref="multipleTable" :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit highlight-current-row>
          <el-table-column align="center" label='预算id' width="100">
            <template scope="scope">
              {{scope.row.id}}
            </template>
          </el-table-column>

          <el-table-column label="所属计划"  align="center" width="100">
            <template scope="scope">
              {{scope.row.pid}}
            </template>
          </el-table-column>

          <el-table-column label="预算名称" width="190">
            <template scope="scope">
              <el-button type="text" @click="handleDetail(scope.$index)">{{scope.row.name}}</el-button>
            </template>
          </el-table-column>

          <el-table-column  label="预算类型" width="100">
            <template scope="scope">
              <span v-if="scope.row.type==1" >经营预算</span>
              <span v-if="scope.row.type==2" >人资预算</span>
              <span v-if="scope.row.type==3" >投资预算</span>
              <span v-if="scope.row.type==4" >现金流预算</span>
              <span v-if="scope.row.type==5" >税费预算</span>
            </template>
          </el-table-column>


          <el-table-column label="审核状态"  align="center" width="100">
            <template scope="scope">
              <span v-if="scope.row.checkdecision==0" >待审核</span>
              <span style="color: green" v-if="scope.row.checkdecision==1" >已通过</span>
              <span style="color: red" v-if="scope.row.checkdecision==2" >未通过</span>
            </template>
          </el-table-column>

          <el-table-column align="center" prop="created_at" label="审核人" width="100">
            <template scope="scope">
              <span>{{scope.row.checker}}</span>
            </template>
          </el-table-column>

            <el-table-column align="center" label="申请人" width="100">
                <template scope="scope">
                  <span>{{scope.row.filler}}</span>
                </template>
            </el-table-column>

            <el-table-column align="center" width="200"  label="操作" >
                <template scope="scope">
                   <el-button :disabled="!isChecker" size="small" @click="handleCheck(scope.row.id)">审核</el-button>
                   <el-button size="small" @click="handleUpdate(scope.row.id,scope.row.filler)">修改</el-button>
                   <el-button :disabled="!isManager" size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination  @current-change="handleCurrentChange" :current-page.sync="listQuery.currPage"
        :page-sizes="[10]" :page-size="listQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>
    <!-- 审核弹窗 -->
    <el-dialog title="审核" :visible.sync="dialogFormVisible">
          <el-form class="small-space" :model="checkInfo" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
            <el-form-item  label="审核意见">
              <el-input :rows="5" type="textarea" v-model="checkInfo.checkadvice"></el-input>
            </el-form-item>

            <el-form-item label="决定">
              <el-radio v-model="checkInfo.checkdecision" label="1">批准</el-radio>
              <el-radio v-model="checkInfo.checkdecision" label="2">不予通过</el-radio>
            </el-form-item>
            <el-form-item label="审核人">
              <span>{{person.name}}</span>
            </el-form-item>
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
           
            <el-button type="primary" @click="handleCheckInfo">确 定</el-button>
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
        isChecker: false,
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
          type: null,
          name: "",
          currPage: 1,
          pageSize: 10,
        },
        checkInfo: {
          "id": null,
          "checkadvice": "",
          "checkdecision": null,
          "checker": ""
        },
        typeOptions:[
          { key: '6', display_name: '不限' },
          { key: '1', display_name: '经营预算' },
          { key: '2', display_name: '人资预算' },
          { key: '3', display_name: '投资预算' },
          { key: '4', display_name: '现金流预算' },
          { key: '5', display_name: '税费预算' }
        ],
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
  },
  methods: {
    //获取列表数据
    getList() {
        let vm = this;
        vm.listLoading = true;
        global.get( api.budgetList,{params: {'page': vm.listQuery.currPage } },function(res){
              let data = res.body;
              console.log('列表数据：',data); 
              vm.list = data;
              vm.listLoading = false; 
        },function(res){
            vm.listLoading = false;
        },false)
    },
    handleDetail(index){
       this.$router.push( { path: '/budget/budgetDetail',query: {id: index}} );
    },
    //要审核
    handleCheck(id){
       this.dialogFormVisible = true;
       this.checkInfo.id = id;
       this.checkInfo.checker = this.person.name;
    },
    //提交checkInfo
    handleCheckInfo(){
        let vm = this;
        global.post(api.addBudgetCheckInfo,this.checkInfo,null,function(res){
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
        this.$router.push( { path: '/budget/updateBudget',query: {id: index}} );
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
          global.get( api.deleteBudget,{params: {'id': index} },function(res){ 
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
        global.get(api.budgetPart,{ params:{'type': vm.listQuery.type,'name': vm.listQuery.name,'p': vm.listQuery.currPage}},
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
        console.log('查找的页数是：',val)
      this.listQuery.currPage = val;
      this.getList();
    },
    //新增
    handleCreate() {
        this.$router.push( { path: '/budget/addBudget'} ); 
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    }
  }
};
</script>
<style>
.el-table{
  width: 993px;
}
</style>
