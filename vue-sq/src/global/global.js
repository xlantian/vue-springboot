import Vue from 'vue';
import VueResource from 'vue-resource';
Vue.use(VueResource);

import { Loading } from 'element-ui';
import config from '../../config';
//封装一些全局元素。如全站通用功能函数和http请求等

export  const global = {
        staticPath: process.env.NODE_ENV !== 'development' ?  config.build.staticPath: config.dev.staticPath,//静态资源路径
         // 全局ID计数器，保证返回的是一个全局的id标识（数字）
        globalId: function () {
            return window._idCounter_ ? window._idCounter_ += 1 : window._idCounter_ = 1;
        },
        /**
         * 统一接口处理：get请求方法封装。this.$http.get(url, [options])
         * @param url { String } -必选 接口url
         * @param options { Object } -必选 含官方的所有options对象。传参为{params:{key:11}}
         * @param sucCb { Function } -必选 成功回调
         * @param errorCb { Function } -可选 失败回调
         * @param isLoading { Boolean } -可选 是否显示加载状态
         * @param isLogin { Boolean } -可选 是否登陆信息（移动端使用得较多，设置头部信息）
         */
        get:function( url,options,sucCb,errorCb,isLoading = true,isLogin ){
            if(!url){
                console.log('接口url不能为空！');
                return false ;
            }
            if(isLoading){
                var loadingInstance = Loading.service({text:"sq为你拼命加载"});
            }
            Vue.http.get(url, options).then((response) => {
                setTimeout(function(){
                    sucCb(response);
                    if(isLoading){
                        loadingInstance.close();
                    }
                },1000)
            }, (response) => {
                errorCb(response);
                if(isLoading){
                   loadingInstance.close();
                }
            })
        },
        /**
         * 统一接口处理：post请求方法封装。this.$http.get(url, [options])
         * @param url { String } -必选 接口url
         * @param body { Object } -必选 含官方的所有body对象，可为null。传参时不需要参数名，例如body为{key:11}
         * @param options { Object } -必选 含官方的所有options对象，可为null。传参为{params:{key:11}}
         * @param sucCb { Function } -必选 成功回调
         * @param errorCb { Function } -可选 失败回调
         * @param isLoading { Boolean } -可选 是否显示加载状态
         * @param isLogin { Boolean } -可选 是否登陆信息（移动端使用得较多，设置头部信息）
         */
        post:function( url,body,options,sucCb,errorCb,isLoading = true,isLogin ){
            if(!url){
                console.log('接口url不能为空！');
                return false ;
            }
            if(isLoading){
                var loadingInstance = Loading.service();
            }
            Vue.http.post(url,body,options).then((response) => {
                sucCb(response);
                if(isLoading){
                   loadingInstance.close();
                }
                 
            }, (response) => {
                errorCb(response);
                if(isLoading){
                   loadingInstance.close();
                }
            })
        },
};
