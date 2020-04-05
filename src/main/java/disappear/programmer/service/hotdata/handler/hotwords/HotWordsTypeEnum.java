
package disappear.programmer.service.hotdata.handler.hotwords;

public enum HotWordsTypeEnum
{
	DAILY("daily_hot_words"),REAL_TIME("real_time_hot_words"),WEEKLY("weekly_hot_words"),
	MONTHLY("monthly_hot_words"),ANNUAL("annual_hot_words");
	
	public String hotWordsType;
	
	private HotWordsTypeEnum(String type) {
		this.hotWordsType = type;
	}
	
	public static HotWordsTypeEnum getHotWordsType(String type) {
		for(HotWordsTypeEnum hotWordsType: HotWordsTypeEnum.values()) {
			if(hotWordsType.hotWordsType.equals(type)) {
				return hotWordsType;
			}
		}
		
		return HotWordsTypeEnum.REAL_TIME;
	}
}

