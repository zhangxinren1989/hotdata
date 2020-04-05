
package disappear.programmer.service.hotdata.handler.hotwords;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.handler.Handler;

@Component("hotWordsHandlerAdapter")
public class HotWordsHandlerAdapter implements Handler
{
	@Autowired
	@Qualifier("baiduHotWordsHandler")
	private BaiduHotWordsHandler baiduHotWordsHandler;
	@Override
	public Object handle(Enum source, Enum type, Date date) {
		Object result = null;
		switch((HotWordsSourceEnum)source) {
			case BAIDU_TOP: 
				result = baiduHotWordsHandler.handle((HotWordsTypeEnum)type, date);
				break;
			default:
				result = baiduHotWordsHandler.handle((HotWordsTypeEnum)type, date);
		}
		return result;
	}
	
	@Override
	public Object handle(Enum type, Date date){return null;}
	
	public void scratchHotWords(HotWordsTypeEnum type){}
	
	public void scratchRealTimeHotWords() {};
	
	public void scratchDailyHotWords() {};
	
	public void scratchWeeklyHotWords() {};
	
	public void scratchMonthlyHotWords() {};
	
	public void scratchAnnualHotWords() {};

}

