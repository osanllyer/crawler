package com.lfb.crawler.dao;

import java.math.BigInteger;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class LawDao {

	/**
	 * sql to insert into law, content useless
	 */
	private final String INSERT_LAW = "insert into law(publisher, docnum, publish_date, valid_since, title) values(?,?,?,?,?)";
	private final String INSERT_LAW_ITEM = "insert into law_item(law_id, content) values(?,?)";
	private final String INSERT_LAST_ID = "SELECT LAST_INSERT_ID()";
	
	
	public void insert(Law law){
		
		QueryRunner runner = new QueryRunner(DBConfig.getDataSource());
		try {
			
			runner.update(INSERT_LAW, 
					law.getPublisher(),
					law.getDocNum(),
					law.getPublishDate(),
					law.getValidSince(),
					law.getTitle()
					);
			//get id
			BigInteger id = (BigInteger)runner.query(INSERT_LAST_ID,  new ScalarHandler(1));
			for(String item : law.getContent()){
				runner.update(INSERT_LAW_ITEM, id.intValue(), item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
