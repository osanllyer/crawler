package com.lfb.libman;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 题库管理接口
 * @author osanllyer
 *
 */
@RestController
@RequestMapping(value = "/lib/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LibManController {

	@Autowired
	LibManMapper mapper;
	
	@Autowired
	StatMapper statMapper;
	
	@Autowired
	SqliteMapper sqliteMapper;
	
	/**
	 * 检查题库是否有更新，返回题库版本号和updatelog
	 * @return json字符串
	 */
	@RequestMapping(value="libversion", method=RequestMethod.GET)
	public VersionLog libversion(){
		VersionLog vl = mapper.getVersionLog();
		return vl;
	}
	
	@RequestMapping(value="lawchapters", method=RequestMethod.GET)
	public List lawChapter(){
		List<LawChapter> list = mapper.lawChapters();
		return list;
	}
	
	/**
	 * 下载文件
	 * @return
	 */
	@RequestMapping(value = "resource", method=RequestMethod.GET)
	public Object libResource(){
		return null;
	}
	
	@RequestMapping(value="questions", method=RequestMethod.GET)
	public DTResponse questions(@RequestParam("start") int start, @RequestParam("size")int size){
		int count = mapper.questionCount();
		List<Question> qList = mapper.questions(start, size);
		DTResponse response = new DTResponse();
		response.setRecordsTotal(count);
		response.setData(qList);
		return response;
	}
	
	/**
	 * 保存修改
	 * @param question
	 * @return
	 */
	@RequestMapping(value="savequestion", method=RequestMethod.POST)
	public ResponseEntity<Question> saveQuestion(@RequestBody Question question){
		
		if(question != null){
			question.setLast_modified(new Timestamp(Calendar.getInstance().getTime().getTime()));
			mapper.saveQuestion(question);
		}
		
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}
	
	/**
	 * 和sqlite文件同步题库
	 */
	@RequestMapping(value="publish_to_sqlite", method=RequestMethod.GET)
	public ResponseEntity publishToSqlite(){
		sqliteMapper.delete();
		
		Timestamp ts = new Timestamp(1, 1, 1, 1, 1, 1, 1);
		
		//获取所有
		List<Question> qList = mapper.getUpdate(ts);
		
		//逐条插入
		for(Question q : qList){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date d = new Date(q.getLast_modified().getTime());
			q.setLast_modified_sqlite(df.format(d));
			sqliteMapper.insert(q);
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * 用户升级题库
	 */
	@RequestMapping(value="libupdate", method=RequestMethod.GET)
//	@Cacheable
	public ResponseEntity updatelib(@RequestParam("updatetime") String updateTime, @RequestParam("check")boolean checkOnly){
		
		if(checkOnly){
			Map result = mapper.checkUpdate(Timestamp.valueOf(updateTime));
			if((Long)result.get("count") == 0){
				result.put("newestTime", Timestamp.valueOf(updateTime));
			}
			
			int total = mapper.questionCount();
			result.put("total", total);
			
			List<Map> questionStat = mapper.getQuestionStat();
			result.put("qs", questionStat);
			return new ResponseEntity(result, HttpStatus.OK);
		}else{
			List<Question> qList = mapper.getUpdate(Timestamp.valueOf(updateTime));
			return new ResponseEntity<List<Question>>(qList, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="appversion", method=RequestMethod.GET)
	public ResponseEntity appVersion(){
		Map appVersionLog = mapper.appVersion();
		return new ResponseEntity(appVersionLog, HttpStatus.OK);		
	}
	
	@RequestMapping(value="exampaperlist", method=RequestMethod.GET)
	public ResponseEntity getExampaperStat(@RequestParam("papers") List<Integer> papers){
		return null;
	}
	
}
