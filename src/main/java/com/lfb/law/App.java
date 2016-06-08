package com.lfb.law;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.lfb.law.interceptor.QueryParamInterceptor;
import com.lfb.law.resolver.QueryArgumentResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@Configuration
@ComponentScan("com.lfb")
@MapperScan("com.lfb")
@EnableCaching
@EnableSolrRepositories(basePackages={"com.lfb.law"}, multicoreSupport=true)
@EnableSwagger2
public class App {

	final Logger logger = LoggerFactory.getLogger(App.class);
	private final String SOLR_HOST = "solr.host";
	
	@Autowired
	Environment env;
	
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	 @Bean
	  public SolrServer solrServer() {
	    String solrHost = env.getRequiredProperty(SOLR_HOST);
	    return new HttpSolrServer(solrHost);
	  }
}
