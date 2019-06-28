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

import com.sq.DAO.BudgetDAO;
import com.sq.POJO.budget.Budget;


@RestController
@RequestMapping("/budget")
public class BudgetController {
	
	@Autowired
	BudgetDAO bdao ;
	
	//获取分页的预算
	@CrossOrigin 
	@GetMapping("/budgetList")
	public List<Budget> planList(@RequestParam(value="page",required=false) int page) {
		List<Budget> p = bdao.queryBudget((page-1)*10);
		return p;
	}

	//获取一个预算
	@CrossOrigin 
	@GetMapping("/oneBudget")
	public Budget oneBudget(@RequestParam(value="id",required=false) int id) {
		Budget p = bdao.oneBudget(id);
		return p;
	}
	
	//获取查询预算,type默认为6，如果查询时没有选择type，则不对type做限制
	@CrossOrigin 
	@GetMapping("/budgetPart")
	public List<Budget> planFilter(@RequestParam(value="type",required=false,defaultValue="6") int type,
			@RequestParam(value="p",required=false) int page,
	        @RequestParam(value="name",required=false) String name) {
		List<Budget> b;
		if(type == 6)
			b = bdao.filterBudget1(name,(page-1)*10);
		else
		     b = bdao.filterBudget(type,name,(page-1)*10);
		return b;
	}
	
	//更新一个预算
	@CrossOrigin 
	@PostMapping("/updateBudget")
	public void updateBudget(@RequestBody Budget b) {
		bdao.setBudget(b);
	}
	
	//添加一个预算
	@CrossOrigin 
	@PostMapping("/addBudget")
	public void addBudget(@RequestBody Budget b) {
		bdao.insertBudget(b);
	}
	
	//更新预算的审核信息
	@CrossOrigin 
	@PostMapping("/addBudgetCheckInfo")
	public void addBudgetCheckInfo(@RequestBody Budget bi) {
		bdao.setBudgetCheckInfo(bi);
	}
	
	//获取预算表最大id
	@CrossOrigin 
	@GetMapping("/maxBudgetId")
	public Integer maxBudgetId() {
		return bdao.maxBudgetId()==null? -1:bdao.maxBudgetId();
	}
	
	
	//删除预算
	@CrossOrigin 
	@GetMapping("/deleteBudget")
	public boolean deleteBudget(@RequestParam(value="id",required=false) int id) {
		return bdao.deleteBudget(id);
	}

}
