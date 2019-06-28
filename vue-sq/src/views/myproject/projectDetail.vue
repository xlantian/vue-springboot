<template>
  <div class="components-container">
    <div slot="footer" class="dialog-footer">
       <h4>项目内容</h4>
       <p>{{id}}号项目：{{project.name}}</p>
       <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp{{project.content}}</p>
       <hr>
       <h4>项目执行情况</h4>
       <p>{{project.excute}}</p>
       <hr>
       <h4>项目机会研究</h4>
        <p>优先级：{{project.priority}}</p>
        <p>{{project.chance}}</p>
        <hr>
       <h4>项目执行动态</h4>
       <ul>
        <li v-for="event in events">
            {{event.date}}:{{event.content}}
        </li>
        </ul>
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
      id:null,
      events:null,
      event:{
        id: null,
        pid: null,
        content: ""
      },
      project: {
        content: "",
        excute: "",
        chance:"",
        name: "",
        priority:null
      },
      dialogFormVisible: false,
    };
  },
  mounted() {
    let vm = this;
    vm.listLoading = true;

    vm.id = this.$route.query.id;
    console.log("路由传参计划id是："+this.$route.query.id);
    global.get( api.oneProject,{params:{'id': vm.$route.query.id}},function(res){
        let data = res.body;
        vm.project = data;
        vm.listLoading = false; 
   },function(res){
      vm.listLoading = false;
   },false)

   global.get( api.allEvent,{params:{'pid': vm.$route.query.id}},function(res){
        let data = res.body;
        vm.events = data;
        vm.listLoading = false; 
    },function(res){
      vm.listLoading = false;
    },false)
  },

  methods: {
  }
};
</script>