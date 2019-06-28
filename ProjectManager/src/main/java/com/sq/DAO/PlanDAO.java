package com.sq.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sq.POJO.plan.Plan;
import com.sq.POJO.plan.Recode;

public interface PlanDAO {

	//新建计划
	@Insert("insert into plan(id,name,filler,content,type) values(#{p.id},#{p.name},#{p.filler},#{p.content},#{p.type})")
	public void insertPlan( @Param(value = "p") Plan p);
	
	//新建一条计划的记录
	@Insert("insert into recorde(id,pid,content) values(#{r.id},#{r.pid},#{r.content})")
	public void insertPlanRecode( @Param(value = "r") Recode r);

	//更新审核信息
	@Update("update plan set checker=#{ci.checker},checkdecision=#{ci.checkdecision},checkadvice=#{ci.checkadvice} where id=#{ci.id}")
	public void setPlanCheckInfo(@Param(value = "ci") Plan ci);
	
	//更新计划信息
	@Update("update plan set content=#{p.content},name=#{p.name},type=#{p.type},excute=#{p.excute} where id=#{p.id}")
	public void setPlan(@Param(value = "p") Plan p);
	
	//最大计划id数
	@Select("select max(id) from plan")
	public Integer maxId();
	
	
	//最大的记录表id数
	@Select("select max(id) from recorde")
	public Integer maxRecodeId();
		
	//获取一个计划
	@Select("select name,checkadvice,content,excute,type from plan where id=#{id}")
	public Plan onePlan(@Param(value = "id") int id);
	
	//获取所有计划
	@Select("select * from plan limit #{p},10")
	public List<Plan> queryPlan(@Param(value = "p") int page);
	
	//获取所有属于这个计划的所有记录内容
	@Select("select content from recorde where pid=#{i}")
	public List<Recode> queryRecode(@Param(value = "i") int pid);
	
	//对名字的模糊查询同时限制type
	@Select("select * from plan where type=#{t} and name like CONCAT('%',#{n},'%') limit #{p},10")
	public List<Plan> filterPlan(@Param(value = "t") int type,@Param(value = "n") String name,@Param(value = "p") int page);
	
	//不做type限制，只针对名字模糊查询
	@Select("select * from plan where name like CONCAT('%',#{n},'%') limit #{p},10")
	public List<Plan> filterPlan1(@Param(value = "n") String name,@Param(value = "p") int page);
	
	//删除一个计划
	@Delete("delete from plan where id=#{i}")
	public boolean deletePlan(@Param(value = "i") int id);
	
	//删除计划的所有记录
	@Delete("delete from recorde where pid=#{i}")
	public boolean deletePlanRecode(@Param(value = "i") int pid);

}