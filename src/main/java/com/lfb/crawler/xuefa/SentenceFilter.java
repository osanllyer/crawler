package com.lfb.crawler.xuefa;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将包含指定字词的句子删除
 * @author osanllyer
 *
 */
public class SentenceFilter implements LawFilter {

	private List<String> stopWords = new ArrayList<String>();
	
	public SentenceFilter(){
		stopWords.add("xuefa5");
		stopWords.add("http://.*\\.xuefa\\.com");
		stopWords.add("感谢学法网");	
		stopWords.add("学法网微信");	
		stopWords.add("学法网官方微信");
		stopWords.add("学法家园");
		stopWords.add("学法网会员");		
		stopWords.add("学法网");	
	}
	
	@Override
	public String filte(String content) {
		List<String> tobeDeleted = new ArrayList<String>();
		String[] subSentence = content.split("！|。|；|：");
		for(String s : subSentence){
			for(String stopword : stopWords){
				//如果包括了，需要将句子删除，先标志这个句子
				if(findSentence(s, stopword)){
					tobeDeleted.add(s);
				}
			}
		}
		
		for(String s : tobeDeleted){
			content = content.replace(s, "。");
		}
		
		content = content.replaceAll("！。", "");
		content = content.replaceAll("。！", "");		
		
		return content;
	}
	
	private boolean findSentence(String content, String regx){
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(content);
		return m.find();
	}

}
