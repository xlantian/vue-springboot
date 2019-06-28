<template>
    <section class="app-main" style="min-height: 100%">
        <Levelbar></Levelbar>       <!-- 内容展现区的 导航部分-->
        <transition name="fade" mode="out-in">
            <router-view :key="key"></router-view>  <!-- 根据当前路由的值，展现不同页面，真正的内容-->
        </transition>
    </section>
</template>

<script>
    //使用组件步骤：
    //1.通过import语法导入组件
    //2.在当前vue实例注册组件（全局、局部注册），以下components就是局部组件名称Levelbar
    //3.注册完，如何使用？直接在当前模板中通过<>中放组件名的形式使用组件。
    import Levelbar from './Levelbar';
    import g from 'src/global/global';
    
    export default {
      name: 'AppMain',
      components: {
        Levelbar,
      },
      mounted() {
      },
      computed: {
        key() {
          return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
           //添加日期保证每次key不一样
        }
      }
    }
</script>
