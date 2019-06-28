package com.sq.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sq.POJO.user.User;

public interface UserDAO {

	//新建用户,初始密码为工号
	@Insert("insert into user(number,name,pwd,id) values(#{u.number},#{u.name},#{u.number},#{u.id})")
	public void insertUser( @Param(value = "u") User u);
	
	//更新用户身份权限
	@Update("update user set id=#{u.id} where number=#{u.number}")
	public void setUser(@Param(value = "u") User u);
		
	//获取所有用户
	@Select("select number,name,id from user limit #{p},10")
	public List<User> queryUser(@Param(value = "p") int page);
	
	//修改密码
	@Select("update user set pwd=#{u.npwd} where number=#{u.number} and pwd=#{u.pwd}")
	public void updatePwd(@Param(value = "u") User u);
	
	//用户登录
	@Select("select number from user where number=#{n} and pwd=#{p}")
	public String loginUser(@Param(value = "n") String n,@Param(value = "p") String p);
	
	//获取一个用户
	@Select("select number,name,id from user where number=#{n}")
	public User oneUser(@Param(value = "n") String n);
	
	//删除一个用户
	@Delete("delete from user where number=#{n}")
	public boolean deleteUser(@Param(value = "n") String number);

}