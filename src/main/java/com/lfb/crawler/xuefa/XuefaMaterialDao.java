package com.lfb.crawler.xuefa;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface XuefaMaterialDao {

	@Insert("REPLACE into xuefa_material(title, chapter, url, password) VALUES(#{title}, #{chapter}, #{url}, #{password})")
	void add(XuefaMaterialCrawler t);

}
