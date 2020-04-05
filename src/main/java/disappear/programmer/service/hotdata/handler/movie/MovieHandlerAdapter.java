
package disappear.programmer.service.hotdata.handler.movie;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import disappear.programmer.service.hotdata.handler.Handler;
import disappear.programmer.service.hotdata.handler.hotwords.BaiduHotWordsHandler;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsSourceEnum;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsTypeEnum;

public class MovieHandlerAdapter implements Handler
{
	@Autowired
	@Qualifier("baiduHotWordsHandler")
	BaiduHotWordsHandler baiduHotWordsHandler;
	
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
}

