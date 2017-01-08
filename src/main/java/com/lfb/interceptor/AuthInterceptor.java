package com.lfb.interceptor;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lfb.rank.Scorer;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	Scorer scorer;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//记录用户登录得分
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//从authentication头部获取用户名和密码
        Integer status = response.getStatus();
		if (status != HttpStatus.OK.value()){
			return;
		}
	    String authHeader = request.getHeader("Authorization");
	    if (authHeader != null) {
	      StringTokenizer st = new StringTokenizer(authHeader);
	      if (st.hasMoreTokens()) {
	        String basic = st.nextToken();
	        if (basic.equalsIgnoreCase("Basic")) {
	          try {
	            String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
	            int p = credentials.indexOf(":");
	            if (p != -1) {
	              String _username = credentials.substring(0, p).trim();
				  scorer.loginScore(_username);
	            }
	          }catch(Exception e){
	        	  e.printStackTrace();
	          }
	        }
	      }
	    }
		
	}

}
