package com.lfb.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class LawCrawler2 implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	
	public void process(Page page) {

		if(page.getUrl().regex("^http://www\\.chinalawedu\\.com/falvfagui/\\d+/$").match()){
			page.addTargetRequests(page.getHtml().links().regex("http://www\\.chinalawedu\\.com/falvfagui/\\d+/\\w+\\.shtml").all());
			page.addTargetRequests(page.getHtml().links().regex("http://www\\.chinalawedu\\.com/falvfagui/\\d+/").all());
		}else{
			String rawTxt = page.getRawText();
			String txt = OriginXPathParser.parse(rawTxt, "//div[@id='fontzoom']/p[strong='发文单位']/text()");
			page.putField("publisher", txt);
//			page.putField("docnum", page.getHtml().xpath("//div[@id='fontzoom']/p[strong='文  号']/text()"));
//			page.putField("publishdate", page.getHtml().xpath("//div[@id='fontzoom']/p[strong='发布日期']/text()"));
//			page.putField("validdate", page.getHtml().xpath("//div[@id='fontzoom']/p[strong='生效日期']/text()"));

			if(page.getResultItems().get("publisher") == null){
				page.setSkip(true);
			}
		}
	}

	public Site getSite() {
		return site;
	}


	public static void main(String[] args){
		Spider.create(new LawCrawler2()).addUrl("http://www.chinalawedu.com/falvfagui/21752/").thread(1)
			.run();
	}
	
}
