<template>
  <div class="components-container">
    <div>
      <h3>请填写项目名称</h3>
      <el-input style="width: 200px;" class="filter-item" placeholder="项目名称" v-model="project.name"></el-input>
    </div>

    <div>
      <h3>请编制项目内容</h3>
      <Tinymce :height="300" ref="editor" v-model="project.content"></Tinymce>
    </div>

    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitPlan()">确 定</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { Message } from 'element-ui';
import Tinymce from "components/Tinymce";
import {global} from 'src/global/global';
import {api} from 'src/global/api';
export default {
  components: { Tinymce },
  data() {
    return {
      person: {},
      project: {
        id: "",
        name: "",
        state: 0,
        filler:"",
        content: "",
      },
    };
  },
computed: {
      ...mapGetters({
          personalInfo_init:'userInfo'
      })
  },
mounted() {
     let vm = this;
     global.get( api.maxProjectId,{},function(res){
                vm.project.id = res.body+1;
                console.log("下个计划ID"+vm.project.id);
        },function(res){
        },false)
     vm.person = JSON.parse( JSON.stringify(vm.personalInfo_init) );
     vm.project.filler = vm.person.name;
  },
  methods: {
    submitPlan(){
        let that = this;
        global.post(api.addProject,this.project,null,function(res){
           Message({
              showClose: true,
              message: '提交成功，正在跳转页面……',
              type: 'success'
          })
          setTimeout(()=>{
              that.$router.push( { path: '/myproject/projectList'} );
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


