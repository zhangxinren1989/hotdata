
package disappear.programmer.service.hotdata.handler.movie;

public enum MovieTypeEnum
{
	CARTOON("cartoon"),LITERARY("literary"),COMEDY("comedy"),
	THRILLER("thriller"),TRAGEDY("tragedy"),SOWORDSMEN("sowordsmen"),
	DETECTIVE("detective"),ADVENTURE("adventure"),ROMANCE("romance"),
	DOCUMENTARY("documentary"),HORROR("horror"),NARRATIVE("narrative"),
	ACTION("action"),CRIME_GANGSTER("crime_gangster"),SCIENCE_FICTION("science_fiction"),
	MUSICAL("musical"),EPICS_HISTORICAL("epics_historical"),WAR("war"),
	ETHICAL("ethical"),TRAILER("Trailer"),WESTERN("western");
	
	public String hotWordsType;
	
	private MovieTypeEnum(String type) {
		this.hotWordsType = type;
	}
	
	public static MovieTypeEnum getHotWordsType(String type) {
		for(MovieTypeEnum hotWordsType: MovieTypeEnum.values()) {
			if(hotWordsType.hotWordsType.equals(type)) {
				return hotWordsType;
			}
		}
		
		return CARTOON;
	}
}

