<template>
  <div class="components-container">
    <div>
      <h3>请填写预算名称并选择预算范围</h3>
      <el-input style="width: 200px;" class="filter-item" placeholder="预算名称" v-model="budget.name"></el-input>
      <el-input style="width: 100px;" class="filter-item" placeholder="所属计划id" v-model="budget.pid"></el-input>
      <el-select clearable class="filter-item" style="width: 130px" v-model="budget.type" placeholder="预算范围">
        <el-option v-for="item in  typeOptions" :key="item.key" :label="item.display_name" :value="item.key"></el-option>
      </el-select>
    </div>

    <div>
      <h3>请编制预算内容</h3>
      <Tinymce :height="300" ref="editor" v-model="budget.content"></Tinymce>
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
      budget: {
        id: null,
        pid: null,
        name: "",
        content: "",
        filler: "沈一",
        type: ""
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
  computed: {
        ...mapGetters({
            personalInfo_init:'userInfo'
        })
    },  
  mounted() {
     let vm = this;
     global.get( api.maxBudgetId,{},function(res){
                vm.budget.id = res.body+1;
                console.log("下个计划ID"+vm.budget.id);
        },function(res){
        },false)
     vm.person = JSON.parse( JSON.stringify(vm.personalInfo_init) );
     vm.budget.filler = vm.person.name;
  },
  methods: {
    submitPlan(){
        let that = this;
        global.post(api.addBudget,this.budget,null,function(res){
           Message({
              showClose: true,
              message: '提交成功，正在跳转页面……',
              type: 'success'
          })
          setTimeout(()=>{
              that.$router.push( { path: '/budget/budgetList'} );
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




