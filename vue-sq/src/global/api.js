//把全体接口封装在的前端配置文件（序号与接口文档一一对应），多个环境配置时可快速切换整站式的全部接口，不用逐个页面去改。
const config = require('../../config');
let url = {
    apiUrl: "/ccp-web/api", 
    ctxPath: "/dist", 
    staticPath: process.env.NODE_ENV !== 'development' ?  config.build.staticPath: config.dev.staticPath
};
let ApiBaseUrl = 'http://localhost:8080';   //后端项目的运行端口
export const api = {
//plan
            "maxId": ApiBaseUrl+"/plan/maxId",       //获取计划表最大id
            "maxRecordeId": ApiBaseUrl+"/plan/maxRecordeId",       //获取记录表中最大id
            "allRecorde": ApiBaseUrl+"/plan/allRecorde",       //获取这个计划的所有记录内容
            'planList':ApiBaseUrl+'/plan/planList',  //获取所有计划
            'planPart':ApiBaseUrl+'/plan/planPart',   //获取部分计划
            "addPlan": ApiBaseUrl+"/plan/addPlan",       //添加计划
            "addPlanRecorde": ApiBaseUrl+"/plan/addPlanRecorde",       //添加计划记录
            "onePlan": ApiBaseUrl+"/plan/onePlan",       //获取一个计划
            "updatePlan": ApiBaseUrl+"/plan/updatePlan",       //更新计划
            "deletePlan": ApiBaseUrl+"/plan/deletePlan",       //删除计划
            "deletePlanRecorde": ApiBaseUrl+"/plan/deletePlanRecorde",       //删除计划所有的记录
            "addPlanCheckInfo": ApiBaseUrl+"/plan/addPlanCheckInfo",       //添加计划的审核信息 
 // budget     
            "maxBudgetId": ApiBaseUrl+"/budget/maxBudgetId",       
            'budgetList':ApiBaseUrl+'/budget/budgetList',  
            'budgetPart':ApiBaseUrl+'/budget/budgetPart',   
            "addBudget": ApiBaseUrl+"/budget/addBudget",      
            "oneBudget": ApiBaseUrl+"/budget/oneBudget",      
            "updateBudget": ApiBaseUrl+"/budget/updateBudget",       
            "deleteBudget": ApiBaseUrl+"/budget/deleteBudget",       
            "addBudgetCheckInfo": ApiBaseUrl+"/budget/addBudgetCheckInfo",    
// project
            "maxProjectId": ApiBaseUrl+"/project/maxProjectId", 
            "maxEventId": ApiBaseUrl+"/project/maxEventId",  
            "allEvent": ApiBaseUrl+"/project/allEvent",       
            'projectList':ApiBaseUrl+'/project/projectList', 
            'addProjectEvent': ApiBaseUrl+'/project/addProjectEvent', 
            'projectIn' : ApiBaseUrl+'/project/projectIn', 
            'projectPart':ApiBaseUrl+'/project/projectPart',   
            "addProject": ApiBaseUrl+"/project/addProject",      
            "oneProject": ApiBaseUrl+"/project/oneProject",      
            "updateProject": ApiBaseUrl+"/project/updateProject",       
            "deleteProject": ApiBaseUrl+"/project/deleteProject",   
            "deleteProjectEvent": ApiBaseUrl+"/project/deleteProjectEvent",    
            "addProjectCheckInfo": ApiBaseUrl+"/project/addProjectCheckInfo",  

 //user
            "addUser": ApiBaseUrl+"/user/addUser",
            "userList": ApiBaseUrl+"/user/userList",
            "updateUser": ApiBaseUrl+"/user/updateUser",
            "deleteUser": ApiBaseUrl+"/user/deleteUser",
            "getUser": ApiBaseUrl+"/user/getUser",
            "loginUser": ApiBaseUrl+"/user/loginUser",
            "updatePwd": ApiBaseUrl+"/user/updatePwd",
    }
