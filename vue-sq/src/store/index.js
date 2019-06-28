import Vue from 'vue';
import Vuex from 'vuex';
import app from './modules/app'; 
import user from './modules/user';
import permission from './modules/permission';
import getters from './getters';

Vue.use(Vuex);
/*store对象，modules是存储数据的几大模块，getter是获取这几大模块的一个通道。
  app：系统的布局模块
  user：当前登录的用户的状态，比如 token，uid，账号，身份。。。
  permission: 当前用户能访问的页面信息。本来可以从后端根据每个用户获取，这里直接设为所有都可以访问。

  getter：是获取这几个模块的通道。
 */
const store = new Vuex.Store({
  modules: {
    app, 			
    user,
    permission
  },
  getters :getters			
});

export default store
