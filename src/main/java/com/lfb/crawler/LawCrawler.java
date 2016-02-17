package com.lfb.crawler;

import java.util.Date;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Type;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;

@TargetUrl(value = { "http://www.chinalawedu.com/falvfagui/\\d+/\\w.shtml" })
@HelpUrl("http://www.chinalawedu.com/falvfagui/21752/")
public class LawCrawler {

	@ExtractBy("//div[@id='fontzoom']/p[1]/text()")
	private String publisher;
	
	@ExtractBy("//div[@id='fontzoom']/p[2]/text()")
	private String docnum;
	
	@ExtractBy("//div[@id='fontzoom']/p[3]/text()")
	private Date publishDate;
	
	@ExtractBy("//div[@id='fontzoom']/p[4]/text()")
	private Date validSince;
	
	@ExtractBy(value="", type=Type.Css)
	private String title;
	
	@ExtractBy(value="", type=Type.Css)
	private String content;

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDocnum() {
		return docnum;
	}
	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getValidSince() {
		return validSince;
	}
	public void setValidSince(Date validSince) {
		this.validSince = validSince;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static void main(String[] args){
		
		OOSpider.create(Site.me().setSleepTime(1000),  new FilePageModelPipeline("./data/"), LawCrawler.class)
			.addUrl("http://www.chinalawedu.com/falvfagui/21752/").thread(1).run();
		
	}
	
}
