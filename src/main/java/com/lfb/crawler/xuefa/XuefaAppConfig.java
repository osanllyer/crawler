package com.lfb.crawler.xuefa;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
//@MapperScan("com.lfb.crawler.xuefa")
public class XuefaAppConfig {

    @Bean(name="xuefaQuestionDao")
    public MapperFactoryBean<XuefaQuestionDao> xuefaQuestionDaoFactory(SqlSessionFactory ssf) throws Exception{
    	MapperFactoryBean<XuefaQuestionDao> beanFactory = new MapperFactoryBean<XuefaQuestionDao>();
    	beanFactory.setMapperInterface(XuefaQuestionDao.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }
	
	@Bean
	@DependsOn("xuefaQuestionDao")
	public ChapterMapper chapterMapper(){
		return new ChapterMapper();
	}
	
	@Bean
	public QuestionFormater formater(){
		return new QuestionFormater();
	}
	
	@Bean
	public Exporter exporter(){
		return new Exporter();
	}
	
	@Bean
	public XuefaQuestionsCrawler crawler(){
		return new XuefaQuestionsCrawler();
	}
	
	@Bean XuefaDBPageModal xuefaDBPageModal(){
		return new XuefaDBPageModal();
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");		
		dataSource.setUrl("jdbc:mysql://localhost/examination");
		dataSource.setUsername("osanllyer");
		dataSource.setPassword("12345qwert");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
	
}
