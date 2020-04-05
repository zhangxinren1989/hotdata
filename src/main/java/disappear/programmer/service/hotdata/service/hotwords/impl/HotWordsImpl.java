package disappear.programmer.service.hotdata.service.hotwords.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import disappear.programmer.service.hotdata.handler.hotwords.HotWordsHandlerAdapter;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsSourceEnum;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsTypeEnum;
import disappear.programmer.service.hotdata.handler.result.Result;
import disappear.programmer.service.hotdata.handler.result.ResultHandler;
import disappear.programmer.service.hotdata.handler.result.Status;
import disappear.programmer.service.hotdata.service.hotwords.IHotWords;

@Service("hotWordsService")
public class HotWordsImpl implements IHotWords
{
	@Autowired
	@Qualifier("hotWordsHandlerAdapter")
	private HotWordsHandlerAdapter hotWordsHandlerAdapter;
	
	@Autowired
	@Qualifier("resultHandler")
	private ResultHandler resultHandler;
	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	public Result queryDailyHotWords(String from, String dateStr){
		HotWordsSourceEnum source = HotWordsSourceEnum.getSource(from);
		if(null == source) {
			source = HotWordsSourceEnum.BAIDU_TOP;
		}
		Date date = null;
		try
		{
			if(!StringUtils.isEmpty(dateStr)) {
				LocalDate localDate = LocalDate.parse(dateStr, dateFormatter);
				date = Date.from(localDate.atStartOfDay(ZoneOffset.systemDefault()).toInstant());
			}
		}
		catch(DateTimeParseException e)
		{
		}
		
		Object hotWords = hotWordsHandlerAdapter.handle(source, HotWordsTypeEnum.DAILY, date);
		
		Result result = null;
		if(null == hotWords || ((List)hotWords).size() == 0) {
			result = resultHandler.handle(Status.FAILED, hotWords);
		}else
		{
			result = resultHandler.handle(Status.OK, hotWords);
		}
		
		return result;
	}
	
	public Result queryRealTimeHotWords(String from){
		HotWordsSourceEnum source = HotWordsSourceEnum.getSource(from);
		if(null == source) {
			source = HotWordsSourceEnum.BAIDU_TOP;
		}
		
		Object hotWords = hotWordsHandlerAdapter.handle(source, HotWordsTypeEnum.REAL_TIME, null);
		
		Result result = null;
		if(null == hotWords || ((List)hotWords).size() == 0) {
			result = resultHandler.handle(Status.FAILED, hotWords);
		}else
		{
			result = resultHandler.handle(Status.OK, hotWords);
		}
		
		return result;
	}
	
	public Result queryWeeklyHotWords(String from, String weekEndStr){
		HotWordsSourceEnum source = HotWordsSourceEnum.getSource(from);
		if(null == source) {
			source = HotWordsSourceEnum.BAIDU_TOP;
		}
		
		Date weekEnd = null;
		try
		{
			if(!StringUtils.isEmpty(weekEndStr)) {
				LocalDate localDate = LocalDate.parse(weekEndStr, dateFormatter);
				weekEnd = Date.from(localDate.atStartOfDay(ZoneOffset.systemDefault()).toInstant());
			}
		}
		catch(DateTimeParseException e)
		{
		}
		
		Object hotWords = hotWordsHandlerAdapter.handle(source, HotWordsTypeEnum.WEEKLY, weekEnd);
		
		Result result = null;
		if(null == hotWords || ((List)hotWords).size() == 0) {
			result = resultHandler.handle(Status.FAILED, hotWords);
		}else
		{
			result = resultHandler.handle(Status.OK, hotWords);
		}
		
		return result;
	}
	
	public Result queryMonthlyHotWords(int month, int year, String from){
		return null;
	}
	
	public Result queryAnnualHotWords(int year, String from){
		return null;
	}
}

