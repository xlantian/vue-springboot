<template>
  <div class="components-container">
    <div>
      <h3>修改预算名称并选择预算范围</h3>
      <el-input style="width: 200px;" class="filter-item" placeholder="预算名称" v-model="budget.name"></el-input>
      <span>&nbsp&nbsp&nbsp</span>
      <el-select clearable class="filter-item" style="width: 130px" v-model="budget.type" placeholder="预算范围">
        <el-option v-for="item in  typeOptions" :key="item.key" :label="item.display_name" :value="item.key"></el-option>
      </el-select>
    </div>

    <div class="box">
       <h3>修改预算内容</h3>
      <Tinymce :width="300" :height="300" ref="editor" v-model="budget.content"></Tinymce>
      <h3>修改预算执行情况</h3>
      <el-input :rows="8" type="textarea" v-model="budget.excute"></el-input>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitPlan()">确 定</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
    </div>
  </div>
</template>

<script>
import Tinymce from "components/Tinymce";
import { Message } from 'element-ui';
import {global} from 'src/global/global';
import {api} from 'src/global/api';
export default {
  components: { Tinymce },
  data() {
    return {
      budget: {
        name: "",
        content: "",
        excute: "",
        type: null,
      },
      typeOptions: [
        { key: "1", display_name: "经营预算" },
        { key: "2", display_name: "人资预算" },
        { key: "3", display_name: "投资预算" },
        { key: "4", display_name: "现金流预算" },
        { key: "5", display_name: "税费预算" }
      ]
    };
  },
  mounted() {
    let vm = this;
    vm.listLoading = true;
    console.log("路由传参是："+this.$route.query.id);
    global.get( api.oneBudget,{params:{'id': vm.$route.query.id}},function(res){
          let data = res.body;
          vm.budget = data;
          vm.listLoading = false; 
    },function(res){
        vm.listLoading = false;
    },false)
  },
  methods: {
    submitPlan(){
      let vm = this;
      vm.budget.id = this.$route.query.id;
      global.post(api.updateBudget,vm.budget,null,function(res){
           Message({
              showClose: true,
              message: '提交成功，正在跳转页面……',
              type: 'success'
          })
          setTimeout(()=>{
              vm.$router.push( { path: '/budget/budgetList'} );
          },2000)
      },function(){
        Message({
              showClose: true,
              message: '提交失败',
          })
      })
    }
  }
};
</script>
<style>
.box {
  width: 1200px;
}
</style>