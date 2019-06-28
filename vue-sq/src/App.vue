<template>
    <div id="app22">
        <router-view></router-view> 
<!--router-view可以想成一个容器，这个容器放的组件由访问的路由的路径决定。是顶层页面，Layout层 -->
    </div>
</template>

<script>
/*将上面的模块以名为APP导出去*/
    export default{
      name: 'APP'
    }
</script>   

<style>
	/*全局覆盖默认的loading样式*/
	.el-loading-mask{
		background: rgba(0, 0, 0, 0.5);
	}
</style>
