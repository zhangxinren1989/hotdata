
package disappear.programmer.service.hotdata.handler.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.handler.Handler;
import disappear.programmer.service.hotdata.handler.hotwords.BaiduHotWordsHandler;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsTypeEnum;

@Component("jobHandler")
public class JobHandler implements Handler
{
	@Autowired
	@Qualifier("baiduHotWordsHandler")
	BaiduHotWordsHandler baiduHotWordsHandler;
	@Override
	public void handle(Enum type) {
		switch((HotWordsTypeEnum)type) {
			case REAL_TIME:
				baiduHotWordsHandler.scratchHotWords((HotWordsTypeEnum)type);
				break;
			case DAILY:
				baiduHotWordsHandler.scratchHotWords((HotWordsTypeEnum)type);
				break;
			case WEEKLY:
				baiduHotWordsHandler.scratchHotWords((HotWordsTypeEnum)type);
				break;
			case MONTHLY: 
				
				break;
			case ANNUAL:
				
				break;
			default:
		}
	}
}

