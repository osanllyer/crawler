package com.lfb.crawler.xuefa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

import com.google.common.collect.Lists;

/**
 * 学法网题库爬虫
 * @author osanllyer
 *
 */
//@TargetUrl("http://bbs.xuefa.com/exerlist-16-0-1.html")
@TargetUrl("http://bbs.xuefa.com/exerlist-*.html")
@ExtractBy(value="//div[@class='exerrow']", multi=true)
public class XuefaQuestionsCrawler implements AfterExtractor{

	@Autowired
	XuefaDBPageModal pageModal;
	
	public static final String domain = ".xuefa.com";
	public static final String bbsDomain = "bbs.xuefa.com";
	public static final String uaChrome = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
	public static final String startUrl = "http://bbs.xuefa.com/tiku.html";
	public static final String fileSchedulerDir = "/Users/osanllyer/lawdata/xuefa";
	
	private String url;
	
	@ExtractBy(value="//div[@class=exertitle]/div/em[1]/text()", notNull=true)
	private String xuefaId;
	
	public String getXuefaId() {
		return xuefaId;
	}

	public void setXuefaId(String xuefaId) {
		this.xuefaId = xuefaId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@ExtractBy(value="//div[@class='exercontent']/text()", notNull=true)
	private String question;
	
	@ExtractBy(value="//*[@id='pt']/div/a[3]/text()", source=ExtractBy.Source.RawHtml)
	private String law;
	
	public String getLaw() {
		return law;
	}

	public void setLaw(String law) {
		this.law = law;
	}

	@ExtractBy(value="//div[@id=pt]/div/text()", source=ExtractBy.Source.RawHtml)
	private String chapter;

	@ExtractBy(value="//div[@class=exeroption]/ul/li[1]/text()")
	private String a;
	
	@ExtractBy(value="//div[@class=exeroption]/ul/li[2]/text()")
	private String b;
	
	@ExtractBy(value="//div[@class=exeroption]/ul/li[3]/text()")
	private String c;
	
	@ExtractBy(value="//div[@class=exeroption]/ul/li[4]/text()")
	private String d;
	
	@ExtractBy(value="//div[@class=exersarea]/div[1]/text(1)")
	private String type;
	
	@ExtractBy(value="//div[@class=exeranswer]/text(2)")
	private String answer;
	
	@ExtractBy(value="//div[@class=exeranswer]/text()")
	private String analysis;

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[id]:" + xuefaId + "\n");
		sb.append("[question]:" + question + "\n");
		sb.append("[A]:" + a + "\n");
		sb.append("[B]:" + b + "\n");
		sb.append("[C]:" + c + "\n");
		sb.append("[D]:" + d + "\n");
		sb.append("[type]:" + type + "\n");
		sb.append("[answer]:" + answer + "\n");
		sb.append("[law]:" + law + "\n");
		sb.append("[chapter]:" + chapter + "\n");
		sb.append("[analysis]:" + analysis + "\n");
		return sb.toString() + "\n";
	}
	
	public XuefaQuestionsCrawler(){
	}
	
	public void runCrawler(){
		Site site = Site.me()
				.setUserAgent(uaChrome)
				.setDomain(bbsDomain)
				.setRetryTimes(3)
				.setSleepTime(1000 * 5)
				.setTimeOut(10000)
				.setCharset("gbk")
				.addHeader("Refer", "http://bbs.xuefa.com/")
				.addHeader("Host", "bbs.xuefa.com")
				.addHeader("Accept-Encoding", "gzip, deflate, sdch")
				.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.addHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2")
				.addHeader("Upgrade-Insecure-Requests", "1")
				.addHeader("Connection", "keep-alive")
				.addHeader("Cache-Control", "max-age=0")
				.addHeader("Cookie", "_uab_collina=147607443127406622212447; oYUG_3be2_pc_size_c=0; oYUG_3be2_saltkey=q9IgN94g; oYUG_3be2_lastvisit=1476071491; oYUG_3be2_exertime=1476075093; oYUG_3be2_ulastactivity=ded4vo%2BW4eB6%2BqEj1IxF6XM6PdnankAVTIAdFQClW0IuMzANPViN; oYUG_3be2_auth=fff9yJcf7c7l4LsvltpvWFAJXMBsfgXfC27SeyweDeI08wAM4gKKPgZolqP5FvZT22%2BadQEMMcFLaTZgVyAdloX40Geo; oYUG_3be2_lastcheckfeed=1599203%7C1476075154; oYUG_3be2_lip=123.116.41.176%2C1476075154; PHPSESSID=qdkud5j9jblag11fef51heorp4; oYUG_3be2_sid=Fpd2He; oYUG_3be2_checkpm=1; oYUG_3be2_sendmail=1; AJSTAT_ok_pages=6; AJSTAT_ok_times=1; CNZZDATA2596272=cnzz_eid%3D1203218601-1476072110-null%26ntime%3D1476072110; oYUG_3be2_lastact=1476077046%09misc.php%09patch; oYUG_3be2_connect_is_bind=0")
				;

		
		
		List<String> startUrlList = Lists.newArrayList();
		startUrlList.add(startUrl);
		FileCacheQueueScheduler scheduler = new FileCacheQueueScheduler("./");
		scheduler.setDuplicateRemover(new HashSetDuplicateRemover());
		OOSpider.create(site, pageModal, XuefaQuestionsCrawler.class)
//		OOSpider.create(site, new ConsolePageModelPipeline(), XuefaQuestionsCrawler.class)
//		.setScheduler(scheduler)
//		.startUrls(startUrlList)
		.addUrl(startUrl)
		.thread(1)
		.run();
	}
	
	public static void main(String[] args){

		ApplicationContext appContext = new AnnotationConfigApplicationContext(XuefaAppConfig.class);
		XuefaQuestionsCrawler crawler = appContext.getBean(XuefaQuestionsCrawler.class);
		crawler.runCrawler();
		
	}

	//对一些需要修改对数据进行处理
	@Override
	public void afterProcess(Page page) {
		format();
		formatQuestion();
		formatChapter();
		fromatAnalysis();
		formatType();
		this.url = page.getUrl().get();
	}
	
	private void format(){
		if(this.question != null) this.question = question.trim();
		if(this.chapter != null) this.chapter = chapter.trim();
		if(this.analysis != null) this.analysis = analysis.trim();
		if(this.answer != null) this.answer = answer.trim();
		if(this.a != null) this.a = a.trim();
		if(this.b != null) this.b = b.trim();
		if(this.c != null) this.c = c.trim();
		if(this.d != null) this.d = d.trim();
		if(this.law != null) this.law = law.trim();
		if(this.type != null) this.type = this.type.trim();
		if(this.xuefaId != null) this.xuefaId = this.xuefaId.trim();
	}
	
	/**
	 * type格式为：单选题：A
	 * type中包括一个：，需要去除
	 */
	private void formatType(){
		if(this.type != null){
			String[] typeArr = this.type.split("：");
			if(typeArr.length == 2 && typeArr[0] != null){
				this.type = typeArr[0];
			}
		}
	}
	
	//对question进行格式化
	private void formatQuestion(){
		if(this.question != null){
			String[] questionArr = this.question.split(". ");
			if(questionArr.length == 2 && questionArr[1] != null){
				this.question = questionArr[1];
			}
		}
	}
	
	/**
	 * 对章节进行格式化
	 */
	private void formatChapter(){
		if(this.chapter != null){
			String[] chapterArr = this.chapter.trim().split(" ");
			if(chapterArr.length == 3 && chapterArr[1] != null){
				this.chapter = chapterArr[1];
			}
		}
	}
	
	/**
	 * 对分析进行格式化
	 */
	private void fromatAnalysis(){
		if(this.analysis == null){
			return;
		}
		String tobeRemoved = "有异议才有进步！此题有";
		this.analysis = this.analysis.replaceAll(tobeRemoved, "");
		this.analysis = this.analysis.trim();
		String[] analysisArr = this.analysis.split("       ");
		if(analysisArr.length == 2 && analysisArr[1] != null){
			this.analysis = analysisArr[1];
		}
	}
}
