import Cookies from 'js-cookie';
import {global} from 'src/global/global';
import {api} from 'src/global/api';

var permissions =  {
  "/index/readme":true,
  "/index/personalInfo":true,
  "/plan/planList":true,
  "/plan/addPlan":true,
  "/plan/updatePlan":true,
  "/plan/planDetail":true,
  "/errorpage/401":false,
  "/errorpage/404":false,
  "/budget/budgetList":true,
  "/budget/addBudget":true,
  "/budget/budgetDetail":true,
  "/budget/updateBudget":true,
  "/myproject/projectList":true,
  "/myproject/projectDetail":true,
  "/myproject/addProject":true,
  "/myproject/updateProject":true,
  "/myproject/checkedProject":true,
  "/myproject/updateCheckedProject":true,
  "/myproject/checkedProjectDetail":true,
  "/userset/user":true
}
const user = {
  state: {
    uid: Cookies.get('userId'),     //存储user对象的属性，通过cookie.get("属性名")获取。
    token: Cookies.get('userToken'),
    userInfo:null,
  },

  mutations: {                     //改变user对象属性的方法，可以通过cookie.set("属性名"，值)
    SET_UID: (state, uid) => {
      state.uid = uid;
    },
    SET_TOKEN: (state, token) => {
      state.token = token;
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    LOGIN_SUCCESS: () => {
      console.log('login success')
    },
  },
  actions: {
    // 工号登录
    Login({ commit }, number) {
      return new Promise((resolve, reject) => {
        Cookies.set('userToken', number,{ expires: 1/3}); //设置token,用工号代替了
        Cookies.set('userId',number,{ expires: 1/3}); //设置用户id，用工号代替了
        commit('SET_TOKEN', number);
        commit('SET_UID', number);
        resolve();
        return false;

      });
    },
   // 获取用户信息
    GetInfo({ dispatch,commit, state }) {
        return new Promise((resolve, reject) => {
            global.get( api.getUser,{params:{'number':state.uid}}, function(res){
                  console.log('-------根据已登录的工号获取用户信息：',JSON.stringify(res) )
                  commit('SET_TOKEN', res.body.number);
                  commit('SET_USERINFO', res.body);
                  dispatch('GenerateRoutes', permissions);
                  resolve();
            },function(res){
                reject(res);
            })
        });
    },
    // 退出 （头部登出）
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
          commit('SET_TOKEN', '');
          Cookies.remove('userToken');
          Cookies.remove('userId');
          resolve();
      });
    },
  }
};

export default user;
