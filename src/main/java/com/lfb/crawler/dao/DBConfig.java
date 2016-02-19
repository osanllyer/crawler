package com.lfb.crawler.dao;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.mysql.jdbc.Connection;

public class DBConfig {

    private static DataSource ds;  
      
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();  //map  
    static{  
        try{  
            Properties prop = new Properties();  
            InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");  
            prop.load(in);  
            BasicDataSourceFactory factory = new BasicDataSourceFactory();  
            ds = factory.createDataSource(prop);  
        }catch (Exception e) {  
            throw new ExceptionInInitializerError(e);  
        }  
    }  
      
    public static DataSource getDataSource(){  
        return ds;  
    }  
      
    public static Connection getConnection() throws SQLException{  
        try{  
            //得到当前线程上绑定的连接  
            Connection conn = tl.get();  
            if(conn==null){  //代表线程上没有绑定连接  
                conn = (Connection) ds.getConnection();  
                tl.set(conn);  
            }  
            return conn;  
        }catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}
