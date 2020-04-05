
package disappear.programmer.service.hotdata.handler.translate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.handler.Handler;

@Component("translateHandler")
public class TranslateHandler implements Handler
{
	@Autowired
	@Qualifier("baiduTranslator")
	Translator baiduTranslator;
	
	public String handle(String source, Language from, Language to, String src) {
		TranslatorSourceEnum translatorSource = TranslatorSourceEnum.getSource(source);
		switch(translatorSource) {
			case BAIDU_TRANSLATOR: 
				return baiduTranslator.translate(from, to, src);
			default:
		}
		
		return null;
	}
	
	public String handle(Language from, Language to, String src) {
		return handle("baidu", from, to, src);
	}
}

