package com.lfb.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lfb.user.dao.UserMapper;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserMapper mapper;
	
	@Autowired
    protected AuthenticationManager authenticationManager;	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * 用户登陆
	 */
//	@RequestMapping(value="login")
//	public Object login(
//			@RequestParam("user") String user, 
//			@RequestParam("password") String password, 
//			@RequestParam("rememberMe")String rememberMe, 
//			HttpServletRequest request){
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password);
//        Authentication authenticatedUser = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
//		return authenticatedUser;
//	}
	
	@RequestMapping(value="auth")
	public Principal tokenLogin(Principal user){
		return user;
	}
	
	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	@RequestMapping("userinfo")
	public User info(@RequestParam("username")String username){
		User user = mapper.findUserByName(username);
		return user;
	}
	
	/**
	 * 根据用户id查找用户
	 * @return
	 */
	@RequestMapping(value="/id/{id}")
	public User findUserById(@PathVariable("id") int id){
		User user = mapper.findUserById(id);
		return user;
	}
	
	/**
	 * 更改密码
	 */
	
	/**
	 * 更新用户信息
	 */
	@RequestMapping(value="update", method=RequestMethod.GET)
	public Object updateInfo(@UserParam User user){
		mapper.updateUser(user);
		return null;
	}
	
	/**
	 * 用户注册
	 */
	@RequestMapping(value="register", method=RequestMethod.POST)
	public Object register(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest request){
		
		RegisterResult result = new RegisterResult();

		//有重复用户
		if(mapper.checkDuplicateUserName(account) != null){
			result.setRegisterResult(false);
			return result;
		}else{
			//添加到数据库
			User user = new User();
			user.setUsername(account);
			user.setPassword(passwordEncoder.encode(password));
			mapper.addUser(user);
			//添加默认权限
			Authority authority = new Authority();
			authority.setUsername(account);
			authority.setAuthority(Authority.ROLE_USER);
			mapper.saveAuthority(authority);
			
			result.setRegisterResult(true);
		}
		
		return result;
	}
	
	/**
	 * 检查是否可用
	 */
	@RequestMapping(value="check/{username}")
	public Boolean checkDuplicateUserName(@PathVariable("username")String username){
		Integer duplicated = mapper.checkDuplicateUserName(username);
		return Boolean.valueOf(duplicated != null);
	}
	
}
