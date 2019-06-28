package com.sq.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.sq.POJO.project.Event;
import com.sq.POJO.project.Project;

public interface ProjectDAO {

	//新建项目
	@Insert("insert into project(id,name,state,filler,content) values(#{p.id},#{p.name},#{p.state},#{p.filler},#{p.content})")
	public void insertProject( @Param(value = "p") Project p);
	

	//立项
	@Update("update project set checker=#{ci.checker},state=2,chance=#{ci.chance},priority=#{ci.priority} where id=#{ci.id}")
	public void setProjectCheckInfo(@Param(value = "ci") Project ci);
	
	//项目入库
	@Update("update project set iner=#{in}, state=1 where id=#{i}")
	public void projectIn(@Param(value = "i") int id,@Param(value = "in") String iner);
	
	//更新项目信息
	@Update("update project set content=#{p.content},name=#{p.name},excute=#{p.excute} where id=#{p.id}")
	public void setProject(@Param(value = "p") Project p);
	
	//最大项目表id数
	@Select("select max(id) from project")
	public Integer maxProjectId();
	
		
	//获取一个项目
	@Select("select name,chance,content,excute,priority from project where id=#{id}")
	public Project oneProject(@Param(value = "id") int id);
	
	//获取一页项目
	@Select("select * from project where state=#{s} limit #{p},10")
	public List<Project> queryProject(@Param(value = "p") int page,@Param(value = "s") int s);
	
	//对名字模糊查询
	@Select("select * from project where state=#{s} and name like CONCAT('%',#{n},'%') limit #{p},10")
	public List<Project> filterProject(@Param(value = "n") String name,@Param(value = "p") int page,@Param(value = "s") int s);
	
	//删除一个项目
	@Delete("delete from project where id=#{i}")
	public boolean deleteProject(@Param(value = "i") int id);
	
	//删除项目的所有事件
	@Delete("delete from event where pid=#{i}")
	public boolean deleteProjectEvent(@Param(value = "i") int pid);
	
	//最大的事件表id数
	@Select("select max(id) from event")
	public Integer maxEventId();
		
	//新建一条事件记录
	@Insert("insert into event (id,pid,date,content,recorder) values(#{r.id},#{r.pid},#{r.date},#{r.content},#{r.recorder})")
	public void insertProjectEvent( @Param(value = "r") Event r);
	
	//获取所有属于这个计划的所有记录内容
	@Select("select date,content from event where pid=#{i}")
	public List<Event> queryEvent(@Param(value = "i") int pid);
	
}