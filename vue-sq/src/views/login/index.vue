<template>
    <div class="login-container">
        <el-form autoComplete="on" :model="loginForm"  ref="loginForm" label-position="left"
                 label-width="0px"
                 class="card-box login-form">
            <h3 class="title">系统登录<i class="fa fa-tripadvisor" aria-hidden="true"></i></h3>
            <el-form-item prop="number">
                <span class="svg-container"><i class="fa fa-user-circle-o" aria-hidden="true"></i></span>
                <el-input name="number" type="text" v-model="loginForm.number" autoComplete="off"
                          placeholder="工号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <span class="svg-container"><i class="fa fa-lock" aria-hidden="true"></i></span>
                <el-input name="password" type="password" @keyup.enter.native="handleLogin" v-model="loginForm.pwd"
                          autoComplete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleLogin">
                    登录
                </el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import md5 from 'blueimp-md5';
    import {api} from 'src/global/api';
    import {global} from 'src/global/global';
    export default {
      name: 'login',
      data() {
        return {
            loginForm: {
                number: '08153436',
                pwd: ''
            },
            loading: false,
            showDialog: false
        }
      },
      methods: {
        handleLogin() {
          let vm = this;
          var num =  vm.loginForm.number;
          global.post(api.loginUser,vm.loginForm,null,
           function(res){
             let n = res.bodyText;
             if(n == num) {
               console.log("工号"+n+"即将登录！");
               vm.$store.dispatch('Login', n);//保存登录状态
                vm.$router.push('/' );
             }
             else {
                alert("登录出错，可能是账号或密码错误!");
             }
          },function(){
           alert("登录出错，可能是服务器炸了");
          })
        },
      },
    }
</script>

<style rel="stylesheet/scss" lang="scss">
    @import "src/assets/css/mixin.scss";
    .tips{
      font-size: 14px;
      color: #fff;
      margin-bottom: 5px;
    }
    .login-container {
        @include relative;
        height: 100vh;
        background: url('../../assets/img/bg_sky.jpg') no-repeat;
        background-size: cover;
        input:-webkit-autofill {
            -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
            -webkit-text-fill-color: #fff !important;
        }
        input {
            background: transparent;
            border: 0px;
            -webkit-appearance: none;
            border-radius: 0px;
            padding: 12px 5px 12px 15px;
            color: #eeeeee;
            height: 47px;
        }
        .el-input {
            display: inline-block;
            height: 47px;
            width: 85%;
        }
        .svg-container {
            padding: 6px 5px 6px 15px;
            width: 40px;
            float: left;
            text-align: center;
            color: #889aa4;
            font-size: 20px;
        }
        .title {
            font-size: 26px;
            font-weight: 400;
            color: #eeeeee;
            margin: 0px auto 40px auto;
            text-align: center;
            font-weight: bold;
        }
        .login-form {
            position: absolute;
            left: 0;
            right: 0;
            width: 400px;
            padding: 35px 35px 15px 35px;
            margin: 120px auto;
        }
        .el-form-item {
            border: 1px solid rgba(255, 255, 255, 0.1);
            background: rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            color: #454545;
        }
    }

</style>
