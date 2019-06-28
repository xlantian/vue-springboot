/*getter 是前端代码需要拿一些持久存储的数据时的方法。
  这里的 格式为： state.对象名。对象属性名
*/
const getters = {
  uid: state => state.user.uid,     //用户id
  token: state => state.user.token, //用户令牌
  userInfo: state =>state.user.userInfo,//用户所有信息

  sidebar: state => state.app.sidebar,
  visitedViews: state => state.app.visitedViews, //快速导航tab
  
  permission_routers: state => {
    // 对应permission.js中的state.routers
       return  state.permission.routers;
   }, 
  addRouters: state => state.permission.addRouters
};
export default getters
