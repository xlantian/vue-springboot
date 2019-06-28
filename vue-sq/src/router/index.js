import Vue from 'vue';
import Router from 'vue-router';

const _import = require('./_import_' + process.env.NODE_ENV);

/* layout */
import Layout from '../views/layout/Layout';
//以下常量的定义 ：将 path 和 对应的 Vue对应起来
/* login */
const Login = _import('login/index');

/* error page */
const Err404 = _import('error/404');
const Err401 = _import('error/401');

const PersonalInfo = _import('index/personalInfo');
/* plan*/
const PlanList = _import('plan/planList');
const AddPlan = _import('plan/addPlan');
const UpdatePlan = _import('plan/updatePlan');
const PlanDetail = _import('plan/planDetail');

/*budget*/
const BudgetList = _import('budget/budgetList');
const AddBudget = _import('budget/addBudget');
const UpdateBudget = _import('budget/updateBudget');
const BudgetDetail = _import('budget/budgetDetail');

/*project*/
const ProjectList = _import('myproject/projectList');
const AddProject = _import('myproject/addProject');
const UpdateProject = _import('myproject/updateProject');
const ProjectDetail = _import('myproject/projectDetail');
const CheckedProject = _import('myproject/checkedProject');

/* 用户管理*/
const User = _import('userset/user');

Vue.use(Router);
 /**
  * icon : 菜单图标
  * hidden : true不显示在菜单栏
  * redirect : noredirect 为不重定向
  * noDropdown : true 不显示子菜单
  * meta : { role: ['admin'] }  will control the page role
  **/
const constantRouterMap = [
  { path: '/login', component: Login, hidden: true },
  { path: '/404', component: Err404, hidden: true },  //假地址时重定向
  { path: '/401', component: Err401, hidden: true },  //无权限时重定向

  {
    path: '/',
    redirect: '/index/personalInfo',  //重定向到默认首页
    hidden: true,
  },
  {
    path: '/index',
    component: Layout,           //父路由要显示的东西
    redirect: 'noredirect',
    name: '',
    noDropdown: true,
    children: [
        { path: 'personalInfo', component: PersonalInfo, name: '个人信息' }
    ]
  },
  
  {
    path: '/errorpage',
    component: Layout,
    redirect: 'noredirect',
    name: '错误页面',
    children: [
      { path: '401', component: Err401, name: '401' },
      { path: '404', component: Err404, name: '404' }
    ]
  },
  {
    path: '/userset',
    component: Layout,
    redirect: 'noredirect',
    name: '用户设置',
    children: [
      { path: 'user', component: User, name: '用户管理' },
    ]
  },
  {
    path: '/budget',
    component: Layout,
    redirect: 'noredirect',
    children: [
      { path: 'budgetList', component: BudgetList, name: '所有预算' },
      { path: 'addBudget', component: AddBudget, name: '添加预算' },
      { path: 'updateBudget', component: UpdateBudget, name: '修改预算' },
      { path: 'budgetDetail', component: BudgetDetail, name: '预算详情' },
    ]
  },
  {
    path: '/plan',
    component: Layout,
    redirect: 'noredirect',
    children: [
      { path: 'planList', component: PlanList, name: '所有计划' },
      { path: 'updatePlan', component: UpdatePlan, name: '修改内容' },
      { path: 'addPlan', component: AddPlan, name: '新建计划' },
      { path: 'planDetail', component: PlanDetail, name: '计划详情' },
    ]
  },
  {
    path: '/myproject',
    component: Layout,
    redirect: 'noredirect',
    children: [
      { path: 'projectList', component: ProjectList, name: '所有项目' },
      { path: 'projectDetail', component: ProjectDetail, name: '项目详情' },
      { path: 'addProject', component: AddProject, name: '添加项目' },
      { path: 'checkedProject', component: CheckedProject, name: '已立项项目' },
      { path: 'updateProject', component: UpdateProject, name: '更新未立项项目' },
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  scrollBehavior: () => ({ y: 0 }),//让所有页面滚动到顶部
  routes: constantRouterMap   //即上面定义的routes
});
