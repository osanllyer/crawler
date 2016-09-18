package com.lfb.libman;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import com.lfb.configure.PrimaryMapper;

@PrimaryMapper
@Mapper
public interface StatMapper {

	@Results
	@SelectProvider(type=ExampaperStatSQLBuilder.class, method="exampaperStatSQLBuilder")
	public List<ExampaerStat> exampaerStat(List<Integer> paperList);
}
