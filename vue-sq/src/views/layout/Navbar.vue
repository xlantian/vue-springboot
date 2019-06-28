<template>
    <div class="">
        <el-menu class="navbar" mode="horizontal">
            <el-dropdown class="avatar-container" trigger="click">
                <div class="avatar-wrapper">
                    <span class="user-name">{{userInfo.name}}</span>
                    <span v-if="userInfo.id==1">填写员</span>
                    <span v-if="userInfo.id==0">管理员</span>
                    <span v-if="userInfo.id==2">审核员</span>
                    <span v-if="userInfo.id==3">决策人</span>
                    <i class="el-icon-caret-bottom"></i>
                </div>
                <el-dropdown-menu class="user-dropdown" slot="dropdown">
                    <router-link class='inlineBlock' to="/index/personalInfo">
                        <el-dropdown-item>
                            首页
                        </el-dropdown-item>
                    </router-link>
                    <el-dropdown-item >
                        <span @click="dialogFormVisible = true">修改密码</span>
                        </el-dropdown-item>
                    <el-dropdown-item divided>
                        <span @click="logout" style="display:block;">退出登录</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-menu>

        <!-- 修改密码弹窗 -->
        <el-dialog title="密码修改" :visible.sync="dialogFormVisible">

            <el-form class="small-space" :model="passwordForm"  :rules="passwordFormRules" ref="passwordForm" label-position="right" label-width="100px" style='width: 400px; margin-left:50px;'>      
                <el-form-item label="原密码" prop="oldPassword" >
                  <el-input type="password" v-model="passwordForm.oldPassword" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="新密码" prop="newPassword">
                  <el-input type="password" v-model="passwordForm.newPassword" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="重复新密码" prop="newPassword2">
                  <el-input type="password" v-model="passwordForm.newPassword2" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>

              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handlePwdModifySubmit(passwordForm)">确 定</el-button>
              </div>
        </el-dialog>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex';
    import TabsView from './TabsView';
    import { Message } from 'element-ui';
    import {global} from 'src/global/global';
    import Cookies from 'js-cookie';
    import md5 from 'blueimp-md5';
    import {api} from 'src/global/api';

    export default {
      components: {
        TabsView,
      },
      data() {
        const validateNewPassword2 = (rule, value, callback) => {
                if (value !== this.passwordForm.newPassword) {
                  callback(new Error('两次输入密码不一致!'));
                } else {
                  callback();
                }
        };
        return {
            user: {
                "number": "",
                "pwd": "",
                "npwd":"",
            },
            dialogVisible:false,
            dialogFormVisible:false,
            themeValue: localStorage.getItem("themeValue") ? localStorage.getItem("themeValue") : 'blue',
            passwordForm: {
                "oldPassword":'',
                "newPassword":'',
                "newPassword2":'',
            },
            passwordFormRules: {
                oldPassword: [
                    { required: true, trigger: 'blur', message: '旧密码不能为空！'},                   
                ],
                newPassword: [
                    { required: true, trigger: 'blur', message: '新密码不能为空！'},
                ],
                newPassword2: [
                    { required: true, trigger: 'blur', message: '重复密码不能为空！'},
                    { required: true, trigger: 'blur' , validator: validateNewPassword2}
                ]
            },
        }
      },
      computed: {
        ...mapGetters([
          'sidebar',
          'userInfo',
        ])
      },
      methods: {
        handlePwdModifySubmit(formName){
            var vm = this;
            if(vm.passwordForm.newPassword != vm.passwordForm.newPassword2)
              alert("两次密码不一致！！");
            else {
                vm.user.number = vm.userInfo.number;
                vm.user.pwd = vm.passwordForm.oldPassword;
                 vm.user.npwd = vm.passwordForm.newPassword;
                console.log(" "+ vm.user.number );
                global.post(api.updatePwd,this.user,null,function(res){
                    Message({
                        showClose: true,
                        message: '修改成功！',
                        type: 'success'
                    })
                },function(){
                Message({
                        showClose: true,
                        message: '修改失败，请输入正确密码！',
                    })
                })
            }
        },
        logout() {
          this.$store.dispatch('LogOut').then(() => {
                location.reload();
          });
        }
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss" >
    .navbar {
        height: 50px;
        right: 0px;
        line-height: 50px;
        border-radius: 0px !important;
        .fa-bars{
            cursor: pointer;
            line-height: 50px;
            height: 50px;
            float: left;
            padding: 0 15px;
        }
        .fa-bars[isactive] {
            transform: rotate(90deg);
        }
        .hamburger-container {
            line-height: 50px;
            height: 50px;
            float: left;
            padding: 0 10px;
        }
        .errLog-container {
            display: inline-block;
            position: absolute;
            right: 150px;
        }
        .screenfull{
             position: absolute;
             right: 90px;
             top: 16px;
             color: red;
        }
        .avatar-container {
            height: 50px;
            display: inline-block;
            position: absolute;
            right: 35px;
            .avatar-wrapper {
                cursor: pointer;
                padding: 5px ;
                position: relative;
                height: 40px;
                line-height: 40px;
                .user-name{
                    float: left;
                    margin-right: 5px;
                }
                
                .user-avatar {
                    width: 40px;
                    height: 40px;
                    border-radius: 50%;
                }
                .el-icon-caret-bottom {
                    position: absolute;
                    right: -20px;
                    top: 25px;
                    font-size: 12px;
                }
            }
        }
    }
</style>



