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

import com.sq.DAO.ProjectDAO;
import com.sq.POJO.project.Event;
import com.sq.POJO.project.Project;


@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectDAO bdao ;
	
	//获取分页的项目
	@CrossOrigin 
	@GetMapping("/projectList")
	public List<Project> projectList(@RequestParam(value="page",required=false) int page,@RequestParam(value="state",required=false) int s) {
		List<Project> p = bdao.queryProject((page-1)*10,s);
		return p;
	}

	//获取一个项目
	@CrossOrigin 
	@GetMapping("/oneProject")
	public Project oneProject(@RequestParam(value="id",required=false) int id) {
		Project p = bdao.oneProject(id);
		return p;
	}
	
	//查询项目信息
	@CrossOrigin 
	@GetMapping("/projectPart")
	public List<Project> projectFilter( @RequestParam(value="p",required=false) int page,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="state",required=false) int s) {
		List<Project> b = bdao.filterProject(name,(page-1)*10,s);
		return b;
	}
	
	//更新一个项目
	@CrossOrigin 
	@PostMapping("/updateProject")
	public void updateProject(@RequestBody Project b) {
		bdao.setProject(b);
	}
	
	//添加一个项目
	@CrossOrigin 
	@PostMapping("/addProject")
	public void addProject(@RequestBody Project b) {
		bdao.insertProject(b);
	}
	
	//项目入库
	@CrossOrigin 
	@GetMapping("/projectIn")
	public void projectIn(@RequestParam(value="id",required=false) int id,@RequestParam(value="iner",required=false) String iner) {
		bdao.projectIn(id,iner);
	}
	
	//项目立项
	@CrossOrigin 
	@PostMapping("/addProjectCheckInfo")
	public void addProjectCheckInfo(@RequestBody Project bi) {
		bdao.setProjectCheckInfo(bi);
	}
	
	//获取项目表最大id
	@CrossOrigin 
	@GetMapping("/maxProjectId")
	public Integer maxProjectId() {
		return bdao.maxProjectId()==null? -1:bdao.maxProjectId();
	}
	
	//获取项目表最大id
	@CrossOrigin 
	@GetMapping("/maxEventId")
	public Integer maxEventId() {
		return bdao.maxEventId()==null? -1:bdao.maxEventId();
	}
	
	//给项目添加一个事件
	@CrossOrigin 
	@PostMapping("/addProjectEvent")
	public void insertProjectEvent(@RequestBody Event b) {
		bdao.insertProjectEvent(b);
	}
	
	//删除这个项目所有的事件
	@CrossOrigin 
	@GetMapping("/deleteProjectEvent")
	public boolean deleteProjectEvent(@RequestParam(value="pid",required=false) int pid) {
		return bdao.deleteProjectEvent(pid);
	}
	
	//获取所有项目的事件
	@CrossOrigin 
	@GetMapping("/allEvent")
	public List<Event> allEvent(@RequestParam(value="pid",required=false) int pid) {
		List<Event> r = bdao.queryEvent(pid);
		return r;
	}


	//删除计划
	@CrossOrigin 
	@GetMapping("/deleteProject")
	public boolean deleteBProject(@RequestParam(value="id",required=false) int id) {
		return bdao.deleteProject(id);
	}

}
