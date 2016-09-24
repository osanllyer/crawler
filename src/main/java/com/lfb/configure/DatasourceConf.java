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
import com.lfb.law.express.ExpressMapper;
import com.lfb.law.sync.dao.ErrorProgressSyncMapper;
import com.lfb.law.sync.dao.ErrorsSyncMapper;
import com.lfb.law.sync.dao.FavProgressSyncMapper;
import com.lfb.law.sync.dao.FavSyncMapper;
import com.lfb.law.sync.dao.PracticeProgressSyncMapper;
import com.lfb.law.sync.dao.RealProgressSyncMapper;
import com.lfb.libman.LibManMapper;
import com.lfb.libman.SqliteMapper;
import com.lfb.libman.StatMapper;
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
    public MapperFactoryBean<StatMapper> statMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	MapperFactoryBean<StatMapper> beanFactory = new MapperFactoryBean<StatMapper>();
    	beanFactory.setMapperInterface(StatMapper.class);
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
    
    /**
     * 
     * @param ssf
     * @return
     * @throws Exception
     */
    @Bean
    public MapperFactoryBean<FavSyncMapper> favSyncMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<FavSyncMapper> beanFactory = new MapperFactoryBean<FavSyncMapper>();
    	beanFactory.setMapperInterface(FavSyncMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }    
    
    /**
     * 
     * @param ssf
     * @return
     * @throws Exception
     */
    @Bean
    public MapperFactoryBean<FavProgressSyncMapper> favProgressSyncMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<FavProgressSyncMapper> beanFactory = new MapperFactoryBean<FavProgressSyncMapper>();
    	beanFactory.setMapperInterface(FavProgressSyncMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }
    /**
     * 
     * @param ssf
     * @return
     * @throws Exception
     */
    @Bean
    public MapperFactoryBean<PracticeProgressSyncMapper> practiceProgressSyncMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<PracticeProgressSyncMapper> beanFactory = new MapperFactoryBean<PracticeProgressSyncMapper>();
    	beanFactory.setMapperInterface(PracticeProgressSyncMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }     
    
    /**
     * 
     * @param ssf
     * @return
     * @throws Exception
     */
    @Bean
    public MapperFactoryBean<ErrorProgressSyncMapper> errorProgressSyncMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<ErrorProgressSyncMapper> beanFactory = new MapperFactoryBean<ErrorProgressSyncMapper>();
    	beanFactory.setMapperInterface(ErrorProgressSyncMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }   
    
    @Bean
    public MapperFactoryBean<ErrorsSyncMapper> errorsSyncMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<ErrorsSyncMapper> beanFactory = new MapperFactoryBean<ErrorsSyncMapper>();
    	beanFactory.setMapperInterface(ErrorsSyncMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }       
    
    /**
     * 
     * @param ssf
     * @return
     * @throws Exception
     */
    @Bean
    public MapperFactoryBean<RealProgressSyncMapper> realProgressSyncMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<RealProgressSyncMapper> beanFactory = new MapperFactoryBean<RealProgressSyncMapper>();
    	beanFactory.setMapperInterface(RealProgressSyncMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }  
    
    /**
     * 
     * @param ssf
     * @return
     * @throws Exception
     */
    @Bean
    public MapperFactoryBean<ExpressMapper> expressMapperFactory(@Qualifier("primarySession")SqlSessionFactory ssf) throws Exception{
    	
    	MapperFactoryBean<ExpressMapper> beanFactory = new MapperFactoryBean<ExpressMapper>();
    	beanFactory.setMapperInterface(ExpressMapper.class);
    	beanFactory.setSqlSessionFactory(ssf);
    	
    	return beanFactory;
    }    
    
}
