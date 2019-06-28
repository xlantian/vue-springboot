package com.sq.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sq.POJO.budget.Budget;

public interface BudgetDAO {

	//新建预算
	@Insert("insert into budget(id,pid,name,filler,content,type) values(#{p.id},#{p.pid},#{p.name},#{p.filler},#{p.content},#{p.type})")
	public void insertBudget( @Param(value = "p") Budget p);
	

	//更新审核信息
	@Update("update budget set checker=#{ci.checker},checkdecision=#{ci.checkdecision},checkadvice=#{ci.checkadvice} where id=#{ci.id}")
	public void setBudgetCheckInfo(@Param(value = "ci") Budget ci);
	
	//更新预算信息
	@Update("update budget set content=#{p.content},name=#{p.name},type=#{p.type},excute=#{p.excute} where id=#{p.id}")
	public void setBudget(@Param(value = "p") Budget p);
	
	//最大预算id数
	@Select("select max(id) from budget")
	public Integer maxBudgetId();
	
		
	//获取一个预算
	@Select("select name,content,checkadvice,excute,type from budget where id=#{id}")
	public Budget oneBudget(@Param(value = "id") int id);
	
	//获取所有预算
	@Select("select * from budget limit #{p},10")
	public List<Budget> queryBudget(@Param(value = "p") int page);
	
	//对名字的模糊查询同时限制type
	@Select("select * from budget where type=#{t} and name like CONCAT('%',#{n},'%') limit #{p},10")
	public List<Budget> filterBudget(@Param(value = "t") int type,@Param(value = "n") String name,@Param(value = "p") int page);
	
	//不做type限制，只针对名字模糊查询
	@Select("select * from budget where name like CONCAT('%',#{n},'%') limit #{p},10")
	public List<Budget> filterBudget1(@Param(value = "n") String name,@Param(value = "p") int page);
	
	//删除一个预算
	@Delete("delete from budget where id=#{i}")
	public boolean deleteBudget(@Param(value = "i") int id);

}