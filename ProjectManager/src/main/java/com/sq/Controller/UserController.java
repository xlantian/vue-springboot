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

import com.sq.DAO.UserDAO;
import com.sq.POJO.user.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserDAO udao;
	
	//获取分页的用户
	@CrossOrigin 
	@GetMapping("/userList")
	public List<User> userList(@RequestParam(value="page",required=false) int page) {
		List<User> u = udao.queryUser((page-1)*10);
		return u;
	}
	
	//获取一个用户
	@CrossOrigin 
	@GetMapping("/getUser")
	public User oneUser(@RequestParam(value="number",required=false) String n) {
		User u = udao.oneUser(n);
		return u;
	}
	
	//修改密码
	@CrossOrigin 
	@PostMapping("/updatePwd")
	public void updatePwd(@RequestBody User u) {
		udao.updatePwd(u);
	}
	
	//用户登录
	@CrossOrigin 
	@PostMapping("/loginUser")
	public String loginUser(@RequestBody User u) {
		String s = udao.loginUser(u.getNumber(),u.getPwd());
		return  s;
	}
	
	//新建用户
	@CrossOrigin 
	@PostMapping("/addUser")
	public void userList(@RequestBody User u) {
		udao.insertUser(u);
	}
	
	//更新用户权限
	@CrossOrigin 
	@PostMapping("/updateUser")
	public void setUser(@RequestBody User u) {
		udao.setUser(u);
	}
		
	//删除用户
	@CrossOrigin 
	@GetMapping("/deleteUser")
	public void deleteUser(@RequestParam(value="number",required=false) String number) {
		udao.deleteUser(number);
	}

}
