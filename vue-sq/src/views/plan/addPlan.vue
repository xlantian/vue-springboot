<template>
  <div class="components-container">
    <div>
      <h3>请填写计划名称并选择计划范围</h3>
      <el-input style="width: 200px;" class="filter-item" placeholder="计划名称" v-model="plan.name"></el-input>
      <span>&nbsp&nbsp&nbsp</span>
      <el-select clearable class="filter-item" style="width: 130px" v-model="plan.type" placeholder="计划范围">
        <el-option v-for="item in  typeOptions" :key="item.key" :label="item.display_name" :value="item.key"></el-option>
      </el-select>
    </div>

    <div>
      <h3>请编制计划内容</h3>
      <Tinymce :height="300" ref="editor" v-model="plan.content"></Tinymce>
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
      plan: {
        id: null,
        name: "",
        content: "",
        filler: "",
        type: ""
      },
      typeOptions: [
        { key: "1", display_name: "生产经营计划" },
        { key: "2", display_name: "年度投资计划" },
        { key: "3", display_name: "年度专项计划" },
        { key: "4", display_name: "费用计划" },
        { key: "5", display_name: "工作计划" }
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
     global.get( api.maxId,{},function(res){
                vm.plan.id = res.body+1;
                console.log("下个计划ID"+vm.plan.id);
        },function(res){
        },false)
    vm.person = JSON.parse( JSON.stringify(vm.personalInfo_init) );
    vm.plan.filler = vm.person.name;
  },
  methods: {
    submitPlan(){
        let that = this;
        global.post(api.addPlan,this.plan,null,function(res){
           Message({
              showClose: true,
              message: '提交成功，正在跳转页面……',
              type: 'success'
          })
          setTimeout(()=>{
              that.$router.push( { path: '/plan/planList'} );
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


