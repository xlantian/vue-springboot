package com.sq.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sq.DAO.PlanDAO;
import com.sq.POJO.plan.Plan;
import com.sq.POJO.plan.Recode;


@RestController
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	PlanDAO pdao ;
	
	//获取分页的计划
	@CrossOrigin 
	@GetMapping("/planList")
	public List<Plan> planList(@RequestParam(value="page",required=false) int page) {
		List<Plan> p = pdao.queryPlan((page-1)*10);
		return p;
	}
	
	//获取查询计划,type默认为6，如果查询时没有选择type，则不对type做限制
	@CrossOrigin 
	@GetMapping("/planPart")
	public List<Plan> planFilter(@RequestParam(value="p",required=false) int page,
			@RequestParam(value="type",required=false,defaultValue="6") int type,
			@RequestParam(value="name",required=false) String name) {
		List<Plan> p;
		if(type == 6)
			p = pdao.filterPlan1(name,(page-1)*10);
		else
			p = pdao.filterPlan(type,name,(page-1)*10);
		return p;
	}
	
	//获取所有计划的记录
	@CrossOrigin 
	@GetMapping("/allRecorde")
	public List<Recode> allRecode(@RequestParam(value="pid",required=false) int pid) {
		List<Recode> r = pdao.queryRecode(pid);
		return r;
	}
	
	//获取一个计划
	@CrossOrigin 
	@GetMapping("/onePlan")
	public Plan onePlan(@RequestParam(value="id",required=false) int id) {
		Plan p = pdao.onePlan(id);
		return p;
	}
	
	//更新一个计划
	@CrossOrigin 
	@PostMapping("/updatePlan")
	public void updatePlan(@RequestBody Plan p) {
		pdao.setPlan(p);
	}
	
	//添加一个计划
	@CrossOrigin 
	@PostMapping("/addPlan")
	public void addPlan(@RequestBody Plan p) {
		pdao.insertPlan(p);
	}
	
	//添加一个计划
	@CrossOrigin 
	@PostMapping("/addPlanRecorde")
	public void addPlanRecode(@RequestBody Recode r) {
		pdao.insertPlanRecode(r);
	}
	
	//更新计划的审核信息
	@CrossOrigin 
	@PostMapping("/addPlanCheckInfo")
	public void addPlanCheckInfo(@RequestBody Plan ci) {
		pdao.setPlanCheckInfo(ci);
	}
	
	//获取计划表最大id
	@CrossOrigin 
	@GetMapping("/maxId")
	public Integer maxId() {
		return pdao.maxId()==null? -1:pdao.maxId();
	}
	
	//获取记录表最大id
	@CrossOrigin 
	@GetMapping("/maxRecordeId")
	public Integer maxRecodeId() {
		return pdao.maxRecodeId()==null? -1:pdao.maxRecodeId();
	}
	
	//删除计划
	@CrossOrigin 
	@GetMapping("/deletePlan")
	public boolean deletePlan(@RequestParam(value="id",required=false) int id) {
		return pdao.deletePlan(id);
	}
	
	//删除计划所有的记录
	@CrossOrigin 
	@GetMapping("/deletePlanRecorde")
	public boolean deletePlanRecode(@RequestParam(value="pid",required=false) int pid) {
		return pdao.deletePlanRecode(pid);
	}


}
