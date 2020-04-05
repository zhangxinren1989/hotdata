
package disappear.programmer.service.hotdata.handler.movie;

public enum MovieSourceEnum
{
	DOUBAN_MOVIE("douban", "https://movie.douban.com/chart");
	
	public String from;
	public String url;
	
	private MovieSourceEnum(String from, String url) {
		this.from = from;
		this.url = url;
	}
	
	public static MovieSourceEnum getSource(String from) {
		for(MovieSourceEnum source: MovieSourceEnum.values()) {
			if(source.from.equals(from)) {
				return source;
			}
		}
		
		return DOUBAN_MOVIE;
	}
}

