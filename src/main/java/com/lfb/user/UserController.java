package com.lfb.user;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lfb.sms.SmsContainer;
import com.lfb.user.ValidateResult.CHECKRESULT;
import com.lfb.user.dao.UserMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	SmsContainer smsContainer;
	
	@Autowired
	UserMapper mapper;
	
	@Autowired
    protected AuthenticationManager authenticationManager;	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Value("${sms.url}")
	String url;
	
	@Value("${sms.appkey}")
	String appkey;
	
	@Value("${sms.secret}")
	String secret;
	
	/**
	 * 用户登陆
	 */
	
	
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
	 * 验证验证码
	 */
	@RequestMapping(value="checkvalidatecode")
	public ResponseEntity<ValidateResult> checkValidateCode(@RequestParam("phone")String phone, @RequestParam("code")String code){
		ValidateResult res = new ValidateResult();
		boolean valRes = smsContainer.check(phone, code);
		if(valRes){
			//验证正确
			res.setResult(CHECKRESULT.correct);
			User user = mapper.findUserByName(phone);
			if(user != null){
				res.setUsername(phone);
				res.setPassword(user.getPlain_password());
			}
		}else{
			//验证错误
			res.setResult(CHECKRESULT.error);
		}
		return new ResponseEntity<ValidateResult>(res, HttpStatus.OK);
	}
	
	/**
	 * 获取验证码
	 * @return
	 */
	@RequestMapping(value="getvalcode")
	public ResponseEntity<String> getValidateCode(@RequestParam("recnum")String recNum){

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( "司考在线" );
		String validateCode = smsContainer.genValidateCode();
		req.setSmsParamString( "{number:'" + validateCode + "'}" );
		req.setRecNum( recNum );
		req.setSmsTemplateCode( "SMS_12480200" );
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			smsContainer.save(recNum, validateCode);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * 用户注册
	 */
	@RequestMapping(value="register", method=RequestMethod.GET)
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
			user.setPlain_password(password);
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
