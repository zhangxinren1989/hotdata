
package disappear.programmer.service.hotdata.handler.translate;

public enum TranslatorSourceEnum
{
	BAIDU_TRANSLATOR("baidu", "https://fanyi-api.baidu.com");
	
	public String source;
	public String addr;
	
	private TranslatorSourceEnum(String source, String addr) {
		this.source = source;
		this.addr = addr;
	}
	
	public static TranslatorSourceEnum getSource(String source) {
		for(TranslatorSourceEnum translator: TranslatorSourceEnum.values()) {
			if(translator.source.equals(source)) {
				return translator;
			}
		}
		
		return TranslatorSourceEnum.BAIDU_TRANSLATOR;
	}
}

