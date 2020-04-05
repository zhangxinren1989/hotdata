
package disappear.programmer.service.hotdata.handler.hotwords;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import disappear.programmer.service.hotdata.Application;
import disappear.programmer.service.hotdata.dao.mapper.hotwords.AnnualHotWordsMapper;
import disappear.programmer.service.hotdata.dao.mapper.hotwords.DailyHotWordsMapper;
import disappear.programmer.service.hotdata.dao.mapper.hotwords.MonthlyHotWordsMapper;
import disappear.programmer.service.hotdata.dao.mapper.hotwords.RealTimeHotWordsMapper;
import disappear.programmer.service.hotdata.dao.mapper.hotwords.WeeklyHotWordsMapper;
import disappear.programmer.service.hotdata.dao.model.hotwords.DailyHotWords;
import disappear.programmer.service.hotdata.dao.model.hotwords.RealTimeHotWords;
import disappear.programmer.service.hotdata.dao.model.hotwords.WeeklyHotWords;
import disappear.programmer.service.hotdata.handler.translate.Language;
import disappear.programmer.service.hotdata.handler.translate.TranslateHandler;

@Component("baiduHotWordsHandler")
public class BaiduHotWordsHandler extends HotWordsHandlerAdapter
{
	private final String TOP_URL = HotWordsSourceEnum.getSource("baidu").url;
	private String allTop_realTime;
	private String allTop_daily;
	private String allTop_weekly;
	private Document doc;
	
	@Autowired
	DailyHotWordsMapper dailyHotWordsMapper;
	
	@Autowired
	RealTimeHotWordsMapper realTimeHotWordsMapper;
	
	@Autowired
	WeeklyHotWordsMapper weeklyHotWordsMapper;
	
	@Autowired
	MonthlyHotWordsMapper monthlyHotWordsMapper;
	
	@Autowired
	AnnualHotWordsMapper annualHotWordsMapper;
	
	@Autowired
	@Qualifier("translateHandler")
	private TranslateHandler translateHandler;
	
	private void init(){
		try
		{
			doc = Jsoup.parse(new URL(TOP_URL).openStream(), "gb2312", "https://top.baidu.com");
			Element main = doc.getElementById("main");
			Elements rows = main.getElementsByClass("row");
			Elements hots = rows.get(0).getElementsByClass("box-hot");
			Elements tabs = hots.get(0).getElementsByClass("tab-bd");
			Elements tabBox = tabs.get(0).getElementsByClass("tab-box");
			Element realTimeTop = tabBox.get(0).child(2);
			allTop_realTime = TOP_URL + realTimeTop.attr("href").substring(1);
			
			doc = Jsoup.parse(new URL(allTop_realTime).openStream(), "gb2312", "https://top.baidu.com");
			Element nav = doc.getElementById("nav");
			Element subNav = nav.getElementById("sub-nav");
			
			Elements dailyTop = subNav.child(1).getElementsByTag("A");
			allTop_daily = TOP_URL + dailyTop.attr("href").substring(1);
			
			Elements weeklyTop = subNav.child(2).getElementsByTag("A");
			allTop_weekly = TOP_URL + weeklyTop.attr("href").substring(1);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
		
	@Override
	public Object handle(Enum type, Date date) {
		switch((HotWordsTypeEnum)type) {
			case REAL_TIME: 
				return realTimeHotWordsMapper.selectRealTimeHotWords(50, "baidu");
			case DAILY:
				return dailyHotWordsMapper.selectDailyHotWords(50, date, "baidu");
			case WEEKLY:
				return weeklyHotWordsMapper.selectWeeklyHotWords(50, date, "baidu");
			default:
		}
			
		return null;
	}
	
	@Override
	public void scratchHotWords(HotWordsTypeEnum type)
	{
		init();
		switch(type) {
			case DAILY:
				scratchDailyHotWords();
				break;
			case REAL_TIME:
				scratchRealTimeHotWords();
				break;
			case WEEKLY:
				scratchWeeklyHotWords();
				break;
			default:
		}
		
	}
	
	public static void main(String[] args)
	{
		System.out.println(Date.from(LocalDate.now().atStartOfDay(ZoneOffset.systemDefault()).toInstant()));
		System.out.println(Date.from(LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toInstant()));
	}
	
	@Override
	public synchronized void scratchWeeklyHotWords() {
		try
		{
			doc = Jsoup.parse(new URL(allTop_weekly).openStream(), "gb2312", "https://top.baidu.com");
			List<Map<String, String>> topBoard = extractBoard(doc);
			Map<String, String> record = null;
			List<String> keyWords = new ArrayList<>();
			String keyWord = "";
			WeeklyHotWords hotWords = null;
			boolean updateHotWord;
			List<WeeklyHotWords> oldHotWords = new ArrayList<>(), dailyHotWords = new ArrayList<>();
			
			Date now = Date.from(LocalDate.now().atStartOfDay(ZoneOffset.systemDefault()).toInstant());
			for(int i = topBoard.size() - 1; i >= 0; i--) {
				record = topBoard.get(i);
				keyWord = record.get("keyWord");
				hotWords = weeklyHotWordsMapper.selectWeeklyByKeyWord(keyWord, now);
				if(null == hotWords || now.compareTo(hotWords.getWeekEnd()) > 0) {
					keyWords.add(0, keyWord);
				}else {
					topBoard.remove(i);
					updateHotWord = false;
					if(Long.parseLong(record.get("searchIndex")) > hotWords.getIndex()) {
						//topBoard.add(record);
						hotWords.setIndex(Long.parseLong(record.get("searchIndex")));
						updateHotWord = true;
					}
					if(!StringUtils.isEmpty(record.get("description")) && StringUtils.isEmpty(hotWords.getDescription())) {
						hotWords.setDescription(record.get("description"));
						updateHotWord = true;
					}
					if(updateHotWord) {
						hotWords.setModifyTime(Date.from(LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toInstant()));
						oldHotWords.add(hotWords);
					}
				}
			}
			
			if(keyWords.size() > 0) {
				StringBuilder translateWords = new StringBuilder();
				for(String word: keyWords) {
					translateWords.append(word).append(" ; ");
				}
				if(translateWords.length() >= 2) {
					translateWords.setLength(translateWords.length() - 2);
				}
				
				String translatedWords = translateHandler.handle(Language.CHINESE, Language.ENGLISH, translateWords.toString());
//				System.out.println("translatedWords:" + translatedWords);
				JSONObject jsonObject = JSON.parseObject(translatedWords);
				String data = jsonObject.getString("trans_result");
				JSONObject jsonData = JSON.parseArray(data).getJSONObject(0);
				String dst = jsonData.getString("dst");
				String[] dstWords = dst.split(" *; *");
				
				if(dstWords.length == keyWords.size()) {
					for(int i = 0; i < keyWords.size(); i++) {
						hotWords = new WeeklyHotWords();
						hotWords.setWeekEnd(now);
						hotWords.setKeyword(keyWords.get(i));
						hotWords.setKeywordEnglish(dstWords[i]);
						hotWords.setIndex(Long.parseLong(topBoard.get(i).get("searchIndex")));
						hotWords.setDescription(topBoard.get(i).get("description"));
						hotWords.setSrc("baidu");
						hotWords.setSrcName("百度风云榜");
						dailyHotWords.add(hotWords);
					}
					
					if(dailyHotWords.size() > 0) {
						weeklyHotWordsMapper.insertBatch(dailyHotWords);
					}
				}else {
					System.err.println("BaiduHotWordsHandler.scratchWeeklyHotWords  error, translate result amount not equals origin..."
							+ "translatedWords: "+ translatedWords + ", src: " + translateWords.toString());
				}
			}
			
			if(oldHotWords.size() > 0) {
				weeklyHotWordsMapper.updateBatch(oldHotWords);
			}
			 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void scratchDailyHotWords() {
		try
		{
			doc = Jsoup.parse(new URL(allTop_daily).openStream(), "gb2312", "https://top.baidu.com");
			List<Map<String, String>> topBoard = extractBoard(doc);
			Map<String, String> record = null;
			List<String> keyWords = new ArrayList<>();
			String keyWord = "";
			DailyHotWords hotWords = null;
			boolean updateHotWord;
			List<DailyHotWords> oldHotWords = new ArrayList<>(), dailyHotWords = new ArrayList<>();
			Date now = Date.from(LocalDate.now().atStartOfDay(ZoneOffset.systemDefault()).toInstant());
			
			for(int i = topBoard.size() - 1; i >= 0; i--) {
				record = topBoard.get(i);
				keyWord = record.get("keyWord");
				hotWords = dailyHotWordsMapper.selectDailyByKeyWord(keyWord, now);
				if(null == hotWords || now.compareTo(hotWords.getDate()) > 0) {
					keyWords.add(0, keyWord);
				}else {
					topBoard.remove(i);
					updateHotWord = false;
					if(Long.parseLong(record.get("searchIndex")) > hotWords.getIndex()) {
						//topBoard.add(record);
						hotWords.setIndex(Long.parseLong(record.get("searchIndex")));
						updateHotWord = true;
					}
					if(!StringUtils.isEmpty(record.get("description")) && StringUtils.isEmpty(hotWords.getDescription())) {
						hotWords.setDescription(record.get("description"));
						updateHotWord = true;
					}
					if(updateHotWord) {
						hotWords.setModifyTime(Date.from(LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toInstant()));
						oldHotWords.add(hotWords);
					}
				}
			}
			
			if(keyWords.size() > 0) {
				StringBuilder translateWords = new StringBuilder();
				for(String word: keyWords) {
					translateWords.append(word).append(" ; ");
				}
				if(translateWords.length() >= 2) {
					translateWords.setLength(translateWords.length() - 2);
				}
				
				String translatedWords = translateHandler.handle(Language.CHINESE, Language.ENGLISH, translateWords.toString());
//				System.out.println("translatedWords:" + translatedWords);
				JSONObject jsonObject = JSON.parseObject(translatedWords);
				String data = jsonObject.getString("trans_result");
				JSONObject jsonData = JSON.parseArray(data).getJSONObject(0);
				String dst = jsonData.getString("dst");
				String[] dstWords = dst.split(" *; *");
				
				if(dstWords.length == keyWords.size()) {
					for(int i = 0; i < keyWords.size(); i++) {
						hotWords = new DailyHotWords();
						hotWords.setDate(now);
						hotWords.setKeyword(keyWords.get(i));
						hotWords.setKeywordEnglish(dstWords[i]);
						hotWords.setIndex(Long.parseLong(topBoard.get(i).get("searchIndex")));
						hotWords.setDescription(topBoard.get(i).get("description"));
						hotWords.setSrc("baidu");
						hotWords.setSrcName("百度风云榜");
						dailyHotWords.add(hotWords);
					}
					
					if(dailyHotWords.size() > 0) {
						dailyHotWordsMapper.insertBatch(dailyHotWords);
					}
				}else {
					System.err.println("BaiduHotWordsHandler.scratchDailyHotWords  error, translate result amount not equals origin..."
							+ "translatedWords: "+ translatedWords + ", src: " + translateWords.toString());
				}
			}
			
			if(oldHotWords.size() > 0) {
				dailyHotWordsMapper.updateBatch(oldHotWords);
			}
			 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void scratchRealTimeHotWords() {
		try
		{
			doc = Jsoup.parse(new URL(allTop_realTime).openStream(), "gb2312", "https://top.baidu.com");
			List<Map<String, String>> topBoard = extractBoard(doc);
			Map<String, String> record = null;
			List<String> keyWords = new ArrayList<>();
			String keyWord = "";
			RealTimeHotWords hotWords = null;
			boolean updateHotWord;
			List<RealTimeHotWords> oldHotWords = new ArrayList<>(), realTimeHotWords = new ArrayList<>();
			Date now = Date.from(LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toInstant());
			
			for(int i = topBoard.size() - 1; i >= 0; i--) {
				record = topBoard.get(i);
				keyWord = record.get("keyWord");
				hotWords = realTimeHotWordsMapper.selectByKeyWord(keyWord);
				if(null == hotWords) {
					keyWords.add(0, keyWord);
				}else {
					topBoard.remove(i);
					updateHotWord = false;
					if(Long.parseLong(record.get("searchIndex")) > hotWords.getIndex()) {
						//topBoard.add(record);
						hotWords.setIndex(Long.parseLong(record.get("searchIndex")));
						updateHotWord = true;
					}
					if(!StringUtils.isEmpty(record.get("description")) && StringUtils.isEmpty(hotWords.getDescription())) {
						hotWords.setDescription(record.get("description"));
						updateHotWord = true;
					}
					if(updateHotWord) {
						hotWords.setModifyTime(now);
						oldHotWords.add(hotWords);
					}
				}
			}
			
			if(keyWords.size() > 0) {
				StringBuilder translateWords = new StringBuilder();
				for(String word: keyWords) {
					translateWords.append(word).append(" ; ");
				}
				if(translateWords.length() >= 2) {
					translateWords.setLength(translateWords.length() - 2);
				}
				
				System.out.println("translateWords:" + translateWords.toString());
				String translatedWords = translateHandler.handle(Language.CHINESE, Language.ENGLISH, translateWords.toString());
				System.out.println("translatedWords:" + translatedWords);
				JSONObject jsonObject = JSON.parseObject(translatedWords);
				String data = jsonObject.getString("trans_result");
				JSONObject jsonData = JSON.parseArray(data).getJSONObject(0);
				String dst = jsonData.getString("dst");
				String[] dstWords = dst.split(" *; *");
				
				if(dstWords.length == keyWords.size()) {
					for(int i = 0; i < keyWords.size(); i++) {
						hotWords = new RealTimeHotWords();
						hotWords.setDatetime(now);
						hotWords.setKeyword(keyWords.get(i));
						hotWords.setKeywordEnglish(dstWords[i]);
						hotWords.setIndex(Long.parseLong(topBoard.get(i).get("searchIndex")));
						hotWords.setDescription(topBoard.get(i).get("description"));
						hotWords.setSrc("baidu");
						hotWords.setSrcName("百度风云榜");
						realTimeHotWords.add(hotWords);
					}
					
					if(realTimeHotWords.size() > 0) {
						realTimeHotWordsMapper.insertBatch(realTimeHotWords);
					}
				}else {
					System.err.println("BaiduHotWordsHandler.scratchRealTimeHotWords  error, translate result amount not equals origin..."
							+ "translatedWords: "+ translatedWords + ", src: " + translateWords.toString());
				}
			}
			
			if(oldHotWords.size() > 0) {
				realTimeHotWordsMapper.updateBatch(oldHotWords);
			}
			 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private List<Map<String, String>> extractBoard(Element doc){
		Element main = doc.getElementById("main");
		Element list = main.getElementsByClass("mainBody").get(0).getElementsByClass("list-table").get(0);
		Elements trs = list.getElementsByTag("TR");
		
		List<Map<String, String>> result = new ArrayList<>();
		for(int i = 1; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.children();
			Map<String, String> map = new HashMap<>();
			
			Element numTop = tds.get(0);
			Element spanOrDiv = numTop.child(0);
			if(spanOrDiv.tagName().toUpperCase().equals("SPAN")) {
				map.put("numTop", spanOrDiv.text());
			}else {
				result.get(result.size() - 1).put("description", spanOrDiv.getElementsByClass("info-text").get(0).text());
				continue;
			}
			
			Element keyWord = tds.get(1);
			map.put("keyWord", keyWord.getElementsByTag("A").get(0).text());
			Element searchIndex = tds.get(3);
			map.put("searchIndex", searchIndex.getElementsByTag("SPAN").get(0).text());
			result.add(map);
		}
		
		return result;
	}


}

