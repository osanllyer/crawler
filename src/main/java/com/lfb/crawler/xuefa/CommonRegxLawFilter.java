package com.lfb.crawler.xuefa;

/**
 * 利用正则表达式对内容进行替换
 * @author osanllyer
 *
 */
public class CommonRegxLawFilter implements LawFilter{

	private String regx;
	private String target;
	
	public CommonRegxLawFilter(String regx, String target){
		this.regx = regx;
		this.target = target;
	}
	
	@Override
	public String filte(String content) {
		String t = content.replaceAll(regx,  target);
//		if(!t.equalsIgnoreCase(content)){
//			System.out.println(content + "->" + t);
//		}
		return t;
	}
}
