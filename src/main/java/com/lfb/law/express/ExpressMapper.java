package com.lfb.law.express;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ExpressMapper {

	@Results
	@Select("SELECT id, title, `desc`, published FROM express ORDER BY published DESC LIMIT #{start}, #{size}")
	public List<Express> getExpressList(@Param("start")int start, @Param("size")int size);
	
	@Select("SELECT * FROM express WHERE id = #{id}")
	public Express getExpress(@Param("id")int id);

	
	@Select("SELECT count(1) FROM express WHERE id > #{id}")
	public Integer checkNew(@Param("id")Integer id);

	
	@Update("UPDATE express SET `read` = `read` + 1 WHERE id = #{id}")
	public void updateReaderNum(@Param("id")int id);
	
}
