
package disappear.programmer.service.hotdata.handler.hotwords;

public enum HotWordsSourceEnum
{
	BAIDU_TOP("baidu", "http://top.baidu.com");
	
	public String from;
	public String url;
	
	private HotWordsSourceEnum(String from, String url) {
		this.from = from;
		this.url = url;
	}
	
	public static HotWordsSourceEnum getSource(String from) {
		for(HotWordsSourceEnum source: HotWordsSourceEnum.values()) {
			if(source.from.equals(from)) {
				return source;
			}
		}
		
		return BAIDU_TOP;
	}
}

