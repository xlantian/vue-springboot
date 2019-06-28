<template>
  <div class="components-container">
    <div>
      <h3>修改项目名称</h3>
      <el-input style="width: 200px;" class="filter-item" placeholder="项目名称" v-model="project.name"></el-input>
    </div>

    <div class="box">
       <h3>修改项目内容</h3>
      <Tinymce :width="300" :height="300" ref="editor" v-model="project.content"></Tinymce>
      <h3>修改项目执行情况</h3>
      <el-input :rows="8" type="textarea" v-model="project.excute"></el-input>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitPlan()">确 定</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
    </div>
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
      project: {
        name: "",
        content: "",
        excute: "",
      },
    };
  },
mounted() {
    let vm = this;
    vm.listLoading = true;
    console.log("路由传参是："+this.$route.query.id);
    global.get( api.oneProject,{params:{'id': vm.$route.query.id}},function(res){
          let data = res.body;
          vm.project = data;
          vm.listLoading = false; 
    },function(res){
        vm.listLoading = false;
    },false)
  },
  methods: {
    submitPlan(){
      let vm = this;
      vm.project.id = this.$route.query.id;
      global.post(api.updateProject,vm.project,null,function(res){
           Message({
              showClose: true,
              message: '提交成功，正在跳转页面……',
              type: 'success'
          })
          setTimeout(()=>{
              vm.$router.push( { path: '/myproject/projectList'} );
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