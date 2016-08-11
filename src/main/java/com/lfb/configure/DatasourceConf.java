package com.lfb.configure;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.lfb.download.MapperDownloadItem;
import com.lfb.libman.LibManMapper;
import com.lfb.libman.SqliteMapper;
import com.lfb.user.dao.UserMapper;


@Configuration
@EnableConfigurationProperties
public class DatasourceConf {
	
	@Bean(name="mysql")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="sqlite")
	@ConfigurationProperties(prefix="datasource.sqlite")
	public DataSource secondaryDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="primarySession")
	@Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("mysql")DataSource mysql) throws Exception {
      SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
      sqlSessionFactory.setDataSource(mysql);
      return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
    
	@Bean(name="secondarySession")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("sqlite")DataSource sqlite) throws Exception {
      SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
      sqlSessionFactory.setDataSource(sqlite);
      return (SqlSessionFactory) sqlSessionFactory.getObject();
    }    
//
    @Bean
    public MapperFactoryBean<LibManMapper> libmanMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	MapperFactoryBean<LibManMapper> beanFactory = new MapperFactoryBean<LibManMapper>();
    	beanFactory.setMapperInterface(LibManMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }
    
    @Bean
    public MapperFactoryBean<MapperDownloadItem> downloadItemsMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	MapperFactoryBean<MapperDownloadItem> beanFactory = new MapperFactoryBean<MapperDownloadItem>();
    	beanFactory.setMapperInterface(MapperDownloadItem.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }    
    
    @Bean
    public MapperFactoryBean<UserMapper> userMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	MapperFactoryBean<UserMapper> beanFactory = new MapperFactoryBean<UserMapper>();
    	beanFactory.setMapperInterface(UserMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }
    
    @Bean
    public MapperFactoryBean<SqliteMapper> sqliteMapperFactory(@Qualifier("secondarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<SqliteMapper> beanFactory = new MapperFactoryBean<SqliteMapper>();
    	beanFactory.setMapperInterface(SqliteMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }
    
//    @Bean
//    public MapperScannerConfigurer primaryMapperScannerConfigurer() throws Exception {
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("com.lfb");
//        configurer.setAnnotationClass(PrimaryMapper.class);
//        configurer.setSqlSessionFactory(primarySqlSessionFactory());
//        return configurer;
//    }
//
//    @Bean
//    public MapperScannerConfigurer secondaryMapperScannerConfigurer() throws Exception {
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("com.lfb");
//        configurer.setAnnotationClass(SecondaryMapper.class);
//        configurer.setSqlSessionFactory(secondarySqlSessionFactory());
//        return configurer;
//    }
    
//    @Bean
//    public UserMapper userMapper(@Qualifier("primarySession") SqlSessionFactory ssf) throws Exception {
//      SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(ssf);
//      return sessionTemplate.getMapper(UserMapper.class);
//    }
//    
//    @Bean
//    public LibManMapper libManMapper(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception {
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(ssf);
//        return sessionTemplate.getMapper(LibManMapper.class);
//    }
//    
//    @Bean
//    public SqliteMapper sqliteMapper(@Qualifier("secondarySession")SqlSessionFactory ssf) throws Exception {
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(ssf);
//        return sessionTemplate.getMapper(SqliteMapper.class);
//    }    
}
