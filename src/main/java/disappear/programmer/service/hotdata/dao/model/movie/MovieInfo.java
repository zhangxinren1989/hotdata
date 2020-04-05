package disappear.programmer.service.hotdata.dao.model.movie;

import java.util.Date;

public class MovieInfo {
    private Long id;

    private Float score;

    private Integer rank;

    private String coverUrl;

    private String coverPath;

    private String types;

    private String regions;

    private String name;

    private String nameEn;

    private String nameOther;

    private String introduceUrl;

    private String releaseDate;

    private Integer actorCount;

    private Integer voteCount;

    private String actors;

    private String src;

    private String categoryEn;

    private String category;

    private Float boxOfficeYuan;

    private Float boxOfficeDollars;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath == null ? null : coverPath.trim();
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions == null ? null : regions.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getNameOther() {
        return nameOther;
    }

    public void setNameOther(String nameOther) {
        this.nameOther = nameOther == null ? null : nameOther.trim();
    }

    public String getIntroduceUrl() {
        return introduceUrl;
    }

    public void setIntroduceUrl(String introduceUrl) {
        this.introduceUrl = introduceUrl == null ? null : introduceUrl.trim();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate == null ? null : releaseDate.trim();
    }

    public Integer getActorCount() {
        return actorCount;
    }

    public void setActorCount(Integer actorCount) {
        this.actorCount = actorCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors == null ? null : actors.trim();
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getCategoryEn() {
        return categoryEn;
    }

    public void setCategoryEn(String categoryEn) {
        this.categoryEn = categoryEn == null ? null : categoryEn.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Float getBoxOfficeYuan() {
        return boxOfficeYuan;
    }

    public void setBoxOfficeYuan(Float boxOfficeYuan) {
        this.boxOfficeYuan = boxOfficeYuan;
    }

    public Float getBoxOfficeDollars() {
        return boxOfficeDollars;
    }

    public void setBoxOfficeDollars(Float boxOfficeDollars) {
        this.boxOfficeDollars = boxOfficeDollars;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}