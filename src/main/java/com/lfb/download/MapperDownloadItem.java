package com.lfb.download;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapperDownloadItem {

	
	@Results
	@Select("SELECT * FROM download LIMIT #{start}, #{limit}")
	public List<DownloadItem> getDownloadItems(@Param("start")int start, @Param("limit")int limit);

	/**
	 * 获取下载资源总数
	 * @return
	 */
	@Select("SELECT count(1) FROM download")
	public Integer getTotal();
	
}
