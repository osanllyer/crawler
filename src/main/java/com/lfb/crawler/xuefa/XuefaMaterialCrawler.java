package com.lfb.crawler.xuefa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Type;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

import com.google.common.collect.Lists;

/**
 * 学法网题库爬虫
 * @author osanllyer
 * //*[@id="delform"]/table
 *
 */
//@TargetUrl("http://bbs.xuefa.com/exerlist-16-0-1.html")
@TargetUrl(value="http://bbs.xuefa.com/thread-*-1-1.html", sourceRegion="//div[@class=mn]")
//@HelpUrl(value="http://www.xuefa.com/home.php?mod=space&uid=1588328&do=thread&view=me&order=dateline&from=space&amp;page=*", sourceRegion="//div[@class=mn]")
@ExtractBy("//td[@class=t_f]")
public class XuefaMaterialCrawler implements AfterExtractor{

	@Autowired
	XuefaMaterialDBPageModal pageModal;
	
	public static final String domain = ".xuefa.com";
	public static final String bbsDomain = "www.xuefa.com";
	public static final String uaChrome = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
	public static final String startUrlPrefix = "http://www.xuefa.com/home.php?mod=space&uid=1588328&do=thread&view=me&order=dateline&from=space&page=";

	public static final String fileSchedulerDir = "/Users/osanllyer/lawdata/xuefaMertial";
		
//	@ExtractBy(value="//div[@class=exertitle]/div/em[1]/text()", notNull=true)
//	private String xuefaId;
	
	private List<String> urlList = Lists.newArrayList();
	private String[] urlArray;
	public XuefaMaterialCrawler(){
		for(int i=1; i<8; i++){
			urlList.add(startUrlPrefix + i);
		}
		
		urlArray = urlList.toArray(new String[urlList.size()]);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ExtractBy(type=Type.Regex, value="(2017年.*?】)")
	private String title;

	@ExtractBy(type=Type.Regex, value="(第.*?链接:.*?密码.*?)</td>")
	private String chapter;

	private String url;
	
	@ExtractBy("//*")	
	private String password;

	public String toString(){
		StringBuilder sb = new StringBuilder();
		return sb.toString() + "\n";
	}
	
	
	public void runCrawler(){
		Site site = Site.me()
				.setUserAgent(uaChrome)
				.setDomain(bbsDomain)
				.setRetryTimes(3)
				.setSleepTime(1000 * 5)
				.setTimeOut(10000)
				.setCharset("gbk")
				.addHeader("Refer", "http://www.xuefa.com/home.php?mod=space&uid=1588328&do=thread&view=me&order=dateline&from=space&page=1")
				.addHeader("Host", "www.xuefa.com")
				.addHeader("Accept-Encoding", "gzip, deflate, sdch")
				.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.addHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2")
				.addHeader("Upgrade-Insecure-Requests", "1")
				.addHeader("Connection", "keep-alive")
				.addHeader("Cache-Control", "max-age=0")
				.addHeader("Cookie", "_uab_collina=147558827099391809731202; CNZZDATA2596272=cnzz_eid%3D353416742-1475586728-https%253A%252F%252Fwww.google.com%252F%26ntime%3D1476072110; oYUG_3be2_pc_size_c=0; oYUG_3be2_saltkey=byGqQ1RQ; oYUG_3be2_lastvisit=1481126417; oYUG_3be2_sendmail=1; AJSTAT_ok_pages=13; AJSTAT_ok_times=7; _umdata=70CF403AFFD707DFC43DFB77B3213118D3B3A6CAB47918112A4A59207E77E5D82156AE2730AAAAD3EBCD0377F8A1417819D2DA95AC6A938C165FB50D9306EDE5C91E7816561998C985C16682921CEDD57C0CD4F3FEA2124CC41B291344EF8CD2; oYUG_3be2_ulastactivity=513fzns0wflcgJg1NKdvgJEutAAgIVzGHT8N8J9kY0YOPIBXwPs4; oYUG_3be2_lastcheckfeed=1599203%7C1481130034; oYUG_3be2_checkfollow=1; oYUG_3be2_lip=125.34.10.127%2C1481130016; oYUG_3be2_auth=c887dEdQIx7lDNPngMStsRUoiMJU3PkrGQE5MJINGbhZgBLeCRxWMhl%2FlXc2pfuKc1C5r155rOZlw2J9KbtGJI7nJmSb; oYUG_3be2_home_diymode=1; oYUG_3be2_sid=BsBtBe; oYUG_3be2_checkpm=1; oYUG_3be2_noticeTitle=1; oYUG_3be2_lastact=1481130038%09misc.php%09patch; oYUG_3be2_connect_is_bind=0");
		

		FileCacheQueueScheduler scheduler = new FileCacheQueueScheduler("./");
		scheduler.setDuplicateRemover(new HashSetDuplicateRemover());
		for(String u : urlArray){
			OOSpider.create(site, pageModal, XuefaMaterialCrawler.class)
			.addUrl(u)
			.thread(1)
			.run();
		}
	}
	
	public static void main(String[] args){
		ApplicationContext appContext = new AnnotationConfigApplicationContext(XuefaAppConfig.class);
		XuefaMaterialCrawler crawler = appContext.getBean(XuefaMaterialCrawler.class);
		crawler.runCrawler();
	}

	//对一些需要修改对数据进行处理
	@Override
	public void afterProcess(Page page) {
		format();
		System.out.println("title:" + title);
		this.url = page.getUrl().get();
	}
	
	private void format(){
		//抓取到了title
		if(this.title != null){
			this.title = parseTitle(this.title);
		}
		if(this.chapter != null){
			this.chapter = parseChapter(this.chapter);
		}
	}
	
	private String parseTitle(String title){
		//将title里面的链接删除
		title = title.replaceAll("<a .*?>", "");
		title = title.replaceAll("</a>", "");
		return title;
	}

	private String parseChapter(String chapter){
		chapter = chapter.replaceAll("<a .*?>", "");
		chapter = chapter.replaceAll("</a>", "");
		return chapter;
	}


	
}
