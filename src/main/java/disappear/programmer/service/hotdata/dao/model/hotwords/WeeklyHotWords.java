package disappear.programmer.service.hotdata.dao.model.hotwords;

import java.util.Date;

public class WeeklyHotWords {
    private Long id;

    private String keyword;

    private String newsLink;

    private String videoLink;

    private String imageLink;

    private String link;

    private String src;

    private String keywordEnglish;

    private String srcName;

    private Date weekEnd;

    private String image;

    private String video;

    private Long index;

    private String description;
    
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink == null ? null : newsLink.trim();
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink == null ? null : videoLink.trim();
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink == null ? null : imageLink.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getKeywordEnglish() {
        return keywordEnglish;
    }

    public void setKeywordEnglish(String keywordEnglish) {
        this.keywordEnglish = keywordEnglish == null ? null : keywordEnglish.trim();
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName == null ? null : srcName.trim();
    }

    public Date getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(Date weekEnd) {
        this.weekEnd = weekEnd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public Date getModifyTime()
	{
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime)
	{
		this.modifyTime = modifyTime;
	}
}