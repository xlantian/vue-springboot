<template>
  <div class="components-container">
    <div slot="footer" class="dialog-footer">
       <h4>计划内容</h4>
       <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp{{plan.name}}</p>
       <p>{{plan.content}}</p>
       <hr>
       <h4>计划执行情况</h4>
       <p>{{plan.excute}}</p>
       <hr>
       <h4>计划审核意见</h4>
        <p>{{plan.checkadvice}}</p>
        <hr>
       <h4>计划调整记录</h4>
       <ul>
        <li v-for="recorde in recordes">
            {{recorde.content}}
        </li>
        </ul>
    </div>
    <el-button class="filter-item" type="primary"  icon="edit" @click="handleRecorde()">添加一条记录</el-button>
    <el-dialog title="添加事件记录" :visible.sync="dialogFormVisible">
          <el-form class="small-space" :model="recorde" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
            <el-form-item  label="事件内容">
              <el-input :rows="5" type="textarea" v-model="recorde.content"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleRecordeInfo">记录</el-button>
          </div>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui';
import Tinymce from "components/Tinymce";
import {global} from 'src/global/global';
import {api} from 'src/global/api';
export default {
  components: { Tinymce },
  data() {
    return {
      recordes:null,
      recorde:{
        id: null,
        pid: null,
        content: ""
      },
      plan: {
        type: "",
        content: "",
        excute: "",
        checkadvice: "",
        name: "",
      },
      dialogFormVisible: false,
    };
  },
  mounted() {
    let vm = this;
    vm.listLoading = true;
    console.log("路由传参计划id是："+this.$route.query.id);
    //获取计划的信息
    global.get( api.onePlan,{params:{'id': vm.$route.query.id}},function(res){
        let data = res.body;
        vm.plan = data;
        vm.listLoading = false; 
   },function(res){
      vm.listLoading = false;
   },false)

   //获取所有这个计划的记录
   global.get( api.allRecorde,{params:{'pid': vm.$route.query.id}},function(res){
        let data = res.body;
        vm.recordes = data;
        vm.listLoading = false; 
   },function(res){
      vm.listLoading = false;
   },false)
  },

  methods: {
    //要提交记录先初始记录的属性
     handleRecorde(){
       let vm = this;
       vm.recorde.pid = vm.$route.query.id;
        global.get( api.maxRecordeId,{},function(res){
                vm.recorde.id = res.body+1;
                console.log("下条记录的ID："+vm.recorde.id);
        },function(res){
        },false)
       this.dialogFormVisible = true;
    },
    //确认提交记录
    handleRecordeInfo(){
        let vm = this;
        global.post(api.addPlanRecorde,vm.recorde,null,function(res){
           Message({
              showClose: true,
              message: '记录提交成功！',
              type: 'success'
          })
      },function(){})
      this.dialogFormVisible = false;
    },
  }
};
</script>