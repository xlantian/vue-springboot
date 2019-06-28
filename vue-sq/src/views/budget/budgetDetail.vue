<template>
  <div class="components-container">
    <div slot="footer" class="dialog-footer">
       <h4>预算内容</h4>
       <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp{{budget.name}}</p>
       <p>{{budget.content}}</p>
       <hr>
       <h4>预算执行情况</h4>
       <p>{{budget.excute}}</p>
       <hr>
       <h4>预算审核意见</h4>
        <p>{{budget.checkadvice}}</p>
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
      budget: {
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
    global.get( api.oneBudget,{params:{'id': vm.$route.query.id}},function(res){
        let data = res.body;
        vm.budget = data;
        vm.listLoading = false; 
   },function(res){
      vm.listLoading = false;
   },false)
  },

  methods: {
  }
};
</script>