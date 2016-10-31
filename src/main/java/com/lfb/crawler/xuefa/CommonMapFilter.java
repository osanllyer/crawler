package com.lfb.crawler.xuefa;

import java.util.Map;

import com.google.common.collect.Maps;

public class CommonMapFilter implements LawFilter{

	Map<String, String> map = Maps.newHashMap();
	
	public void addMap(String org, String target){
		map.put(org, target);
	}
	
	@Override
	public String filte(String content) {
		
		if(map.get(content)!= null){
			return map.get(content);
		}
		
		return content;
	}
}
