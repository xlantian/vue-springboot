<template>
    <div class="app-wrapper" :class="{hideSidebar:!sidebar.opened}">
        <div class="sidebar-wrapper">     <!-- 左边-->
            <Sidebar class="sidebar-container"/>
        </div>
        <div class="main-container">
            <Navbar/>   <!-- 最顶上-->
            <App-main/>   <!-- 内容展现区-->
        </div>
        <div class="right-border"></div>
    </div>
</template>

<script>
    import { Navbar, Sidebar, AppMain } from 'views/layout';

    export default {
      name: 'layout',
      components: {
        Navbar,
        Sidebar,
        AppMain
      },
      computed: {
        sidebar() {
          return this.$store.state.app.sidebar;
        }
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    @import "src/assets/css/mixin.scss";
    .app-wrapper {
        @include clearfix;
        position: relative;
        height: 100%;
        width: 100%;
       
        .sidebar-wrapper {
            width: 230px;
            height: 730px;
            position:absolute;
            z-index: 1001;
            overflow: hidden;
            transition: all .28s ease-out;
        }
        .sidebar-container {
            transition: all .28s ease-out;
            position: absolute;
            width: 230px;
            overflow-y: scroll;
        }
        .main-container {
            min-height: 730px;
            transition: all .28s ease-out;
            margin-left: 240px;
            width: 1040px;
            background-color:rgb(255, 255, 245);
        }
        .right-border {
                position: absolute;
                background-color: rgb(255, 255, 250);
                width: 240px;
                height: 730px;
                z-index: 1001;
                top: 0px;
                left: 1280px;
        }
    }
</style>
