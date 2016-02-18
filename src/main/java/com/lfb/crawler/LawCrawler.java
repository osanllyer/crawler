package com.lfb.crawler;

import java.util.List;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Type;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

@TargetUrl(value = { "http://www.chinalawedu.com/falvfagui/\\d+/\\w+.shtml" })
@HelpUrl("http://www.chinalawedu.com/falvfagui/21752/")
public class LawCrawler {

	@ExtractBy(value = "<strong>发文单位</strong>：(.*?)</p>", type = Type.Regex)
	private String publisher;
	
	@ExtractBy(value = "<strong>文  号</strong>：(.*?)</p>", type = Type.Regex)
	private String docnum;
//	
	@ExtractBy(value = "<strong>发布日期</strong>：(.*?)</p>", type = Type.Regex)
	private String publishDate;
//	
	@ExtractBy(value = "<strong>生效日期</strong>：(.*?)</p>", type = Type.Regex)
	private String validSince;

	@ExtractBy(value="//div[@class='hd tc']/h3/text()")
	private String title;

	@ExtractBy(value="//p[contains(@style,'TEXT-INDENT: 2em')]/text()")
	private List<String> content;

	public static void main(String[] args){
		
		OOSpider.create(Site.me().setSleepTime(1000),  new FilePageModelPipeline("./data/"), LawCrawler.class)
			.setScheduler(new FileCacheQueueScheduler("./monitor/"))
			.addUrl("http://www.chinalawedu.com/falvfagui/21752/").thread(1).run();
	}
	
}
