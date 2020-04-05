
package disappear.programmer.service.hotdata.handler.translate;

public enum Language
{
	AUTO(0, ""), ENGLISH(1, "en"), CHINESE(2, "zh");
	
	public String language;
	public int code;
	
	private Language(int code, String language) {
		this.code = code;
		this.language = language;
	}
}

