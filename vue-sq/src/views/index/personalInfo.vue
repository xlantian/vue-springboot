<template>
  <div class="user_info">
    <h2>个人信息 </h2>
    <el-form label-position="right" label-width="100px" :model="personalInfo">
      <el-form-item label="您的身份是: ">
        <span v-if="personalInfo.id==1">填写员</span>
        <span v-if="personalInfo.id==0">管理员</span>
        <span v-if="personalInfo.id==2">审核员</span>
        <span v-if="personalInfo.id==3">决策人</span>
      </el-form-item>
      <el-form-item label="真实姓名: ">
        {{ personalInfo.name }}
      </el-form-item>
      <el-form-item label="工号:">
        {{ personalInfo.number }}
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import {global} from 'src/global/global';
import {api} from 'src/global/api';

var echarts = require('echarts');

export default {
  data() {
    return {
        personalInfo:{},
    }
  },
  //html加载后就执行
  computed: {
        ...mapGetters({
            // 映射 this.personalInfo_init 为 store.getters.userInfo
            personalInfo_init:'userInfo'
        })
    },
  mounted() {
        var vm = this;
        vm.resetForm();
   },
  methods: {
    resetForm() {
        var vm = this;
        vm.personalInfo = JSON.parse( JSON.stringify(vm.personalInfo_init) ) ;
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .user_info{
        /*padding-bottom: 20px;*/
        border: 1px solid #dee1e2;
        h2{
            margin: 0;
            font-weight: normal;
            padding: 10px 20px;
            border-bottom: 1px solid #dee1e2;
            .i_edit{
                float: right;
                font-size: 16px;
                color: #7ab8ed;
            }
        }
    }
    .el-form-item{
      margin-bottom: 0;
    }
</style>
