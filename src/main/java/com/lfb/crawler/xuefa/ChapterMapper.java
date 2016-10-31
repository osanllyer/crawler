package com.lfb.crawler.xuefa;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

public class ChapterMapper implements LawFilter{
	
	@Autowired
	private XuefaQuestionDao dao;
	
	private Map<String, String> map = Maps.newHashMap();
	public ChapterMapper(){
	}
	
	/**
	 * 对学法章节进行格式化
	 * @param s
	 * @return
	 */
	private String xuefaChapterFormater(String s){
		
		s = s.replaceAll("题目列表", "");
		s = s.replaceAll("【.*?】", "");
		s = s.replaceAll(" ", "");
		s = s.replaceAll("\\(", "");
		s = s.replaceAll("\\)", "");
		s = s.replaceAll("、", "");

		return s;
	}
	
	private String lawChapterFormater(String s){
		s = s.replaceAll("、", "");
		s = s.replaceAll("（", "");
		s = s.replaceAll("）", "");
		
		return s;
	}
	
	@PostConstruct
	private void init(){
		
		map.put("双务合同履行抗辩权", "第十七章 合同的订立和履行");
		map.put("期间、送达【新】", "第十章 期间、送达");
		map.put("期间、送达", "第九章 期间、送达");
		map.put("侵权行为", "第三十五章 各类侵权行为与责任");
		map.put("刑罚的体系", "第十章 刑罚种类");
		map.put("刑罚的执行", "第十二章 刑罚执行");
		map.put("刑罚的消灭", "第十三章 刑罚消灭");
		map.put("刑罚的裁量", "第十一章 刑罚裁量");
		map.put("合同的变更与解除", "第十八章 合同的变更和解除");
		map.put("合同的订立", "第十七章 合同的订立和履行");
		map.put("国际民商事法律适用", "第五章 国际民商事关系的法律适用");
		map.put("国际法主体", "第二章 国际法的主体与国际法律责任");
		map.put("国际法律责任", "第二章 国际法的主体与国际法律责任");
		map.put("我国的对外贸易管理制度", "第五章 对外贸易管理制度");
		map.put("排除犯罪的事由", "第四章 犯罪排除事由");
		map.put("政府采购", "第九章 行政程序与政府信息公开");
		map.put("故意犯罪形态", "第八章 罪数形态");
		map.put("检察官职业道德和职业责任", "第三章 检察制度与检察官职业道德");
		map.put("法官职业道德和职业责任", "第二章 审判制度与法官职业道德");
		map.put("律师职业道德和职业责任", "第四章 律师制度与律师职业道德");
		map.put("物与有价证券", "第七章 证券法");
		map.put("知识产权概述", "第二十三章 破坏社会主义市场经济秩序罪（7）侵犯知识产权罪");
		map.put("罪数", "第八章 罪数形态");
		map.put("行政合同", "第八章 行政合同与行政给付");
		map.put("行政应急", "第五章 行政许可");
		map.put("行政程序", "第十五章 行政诉讼程序");
		map.put("行政组织与国家公务员", "第二章 行政组织与公务员");
		map.put("诉讼费用", "第一章 民事诉讼与民事诉讼法");
		map.put("财产保全和先予执行", "第十一章 保全和先予执行");
		map.put("转移财产权利合同", "第二十章 转移财产权利的合同");
		map.put("遗嘱继承、遗赠和遗赠抚养协议", "第三十一章 遗嘱继承、遗赠和遗赠扶养协议");			
		
		List<String> lawChapter = dao.lawChapter();
		List<String> xuefaChapter = dao.xuefaChapter();
		
		for(String xc : xuefaChapter){
			String orgxc = xc;
			for(String s : lawChapter){
				String[] subChapter = s.split(" ");
				String realChapter = subChapter[1];
				realChapter = lawChapterFormater(realChapter);
				xc = xuefaChapterFormater(xc);
				if(xc.equalsIgnoreCase(realChapter)){
					if(map.get(orgxc) == null){
						System.out.println(orgxc + "->" + s);
						map.put(orgxc, s);
					}
				}
			}
			if(map.get(orgxc) == null){
				System.out.println("no match:");
				System.out.println(orgxc);
			}
		}
	}

	@Override
	public String filte(String content) {
		if( map.get(content) != null ){
			return map.get(content);
		}
		
		return content;
	}
	
}
