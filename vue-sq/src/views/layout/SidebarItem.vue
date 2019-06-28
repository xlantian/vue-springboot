<template>
    <div>
        <template v-if="routes">
            <router-link  to="/index/personalInfo"  v-if="routes['/index/personalInfo']" >
                <el-menu-item index="/index/personalInfo">
                    <i class="fa fa-home" aria-hidden="true"></i> 首页
                </el-menu-item>
            </router-link>

            <router-link  to="/plan/planList" >
                <el-menu-item index="/plan/planList">
                    <i class="fa fa-table" aria-hidden="true"></i> 计划管理
                </el-menu-item>
            </router-link>

            <router-link  to="/budget/budgetList" >
                <el-menu-item index="/budget/budgetList">
                    <i class="fa fa-table" aria-hidden="true"></i> 预算管理
                </el-menu-item>
            </router-link>

            <router-link  to="/myproject/projectList" >
                <el-menu-item index="/myproject/projectList">
                    <i class="fa fa-table" aria-hidden="true"></i> 项目审批管理
                </el-menu-item>
            </router-link>

            <router-link  to="/myproject/checkedProject" >
                <el-menu-item index="/myproject/checkedProject">
                    <i class="fa fa-table" aria-hidden="true"></i> 项目执行管理
                </el-menu-item>
            </router-link>

            <router-link  to="/userset/user">
                <el-menu-item index="/userset/user">
                    <i class="fa fa-cog" aria-hidden="true"></i>  用户设置
                </el-menu-item>
            </router-link>
        </template>    
    </div>
</template>

<script>
    //获取vuex中的菜单权限 进行菜单show展示。登陆成功时直接 返回菜单json 并包含帐户信息
    export default {
      name: 'SidebarItem',
      props: {
        routes: {
        }
      },
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .wscn-icon {
        margin-right: 10px;
    }
    .fa{
        margin-right: 10px;
        height: 100px;
    }
    .hideSidebar .menu-indent{
        display: block;
        text-indent: 10px;
    }
</style>

