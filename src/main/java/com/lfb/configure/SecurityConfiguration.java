package com.lfb.configure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.lfb.user.HttpAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//暂时允许所有
		
		http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().permitAll();
		
		
//		http.httpBasic()
//		.and()
//			.authorizeRequests()
//			.anyRequest()
//			.permitAll()
//		.and()
//			.rememberMe()
//			.useSecureCookie(false)
//			.rememberMeParameter("remember-me")
//			.tokenRepository(tokenRepository())
//			.tokenValiditySeconds(10000);
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
    	auth.authenticationProvider(rememberMeAuthProvider());
    }
	
    public RememberMeAuthenticationProvider rememberMeAuthProvider(){
    	RememberMeAuthenticationProvider provider = new RememberMeAuthenticationProvider("remember-me");
    	return provider;
    }
    
    @Bean
	public PersistentTokenRepository tokenRepository(){
		JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
		j.setDataSource(dataSource);
		return j;
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
