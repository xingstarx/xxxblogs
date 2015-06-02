package com.blogsxxx.model;

import java.util.Date;
/**
 * @desc csdn上面的文章
 * @author xiongxingxing
 *
 */
public class CsdnArticle {
    private Integer id;

    private String title;

    private String pics;

	private String creater;

    private Date createtime;

    private String lastmodifier;

    private Date lastmodifytime;

    private String xxxpics;

    private String xxxrealpathpics;

    private String fromurl;

    private String content;
    
    private String categoryname;	//类别名称

	public String getCategoryname() {
		return categoryname;
	}

	public String getContent() {
        return content;
    }

    public String getCreater() {
        return creater;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public String getFromurl() {
        return fromurl;
    }

    public Integer getId() {
        return id;
    }

    public String getLastmodifier() {
        return lastmodifier;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public String getPics() {
        return pics;
    }

    public String getTitle() {
        return title;
    }

    public String getXxxpics() {
        return xxxpics;
    }

    public String getXxxrealpathpics() {
        return xxxrealpathpics;
    }

    public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setFromurl(String fromurl) {
        this.fromurl = fromurl == null ? null : fromurl.trim();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastmodifier(String lastmodifier) {
        this.lastmodifier = lastmodifier == null ? null : lastmodifier.trim();
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public void setPics(String pics) {
        this.pics = pics == null ? null : pics.trim();
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public void setXxxpics(String xxxpics) {
        this.xxxpics = xxxpics == null ? null : xxxpics.trim();
    }

    public void setXxxrealpathpics(String xxxrealpathpics) {
        this.xxxrealpathpics = xxxrealpathpics == null ? null : xxxrealpathpics.trim();
    }

    @Override
	public String toString() {
		return "CsdnArticle [id=" + id + ", title=" + title + ", pics=" + pics
				+ ", creater=" + creater + ", createtime=" + createtime
				+ ", lastmodifier=" + lastmodifier + ", lastmodifytime="
				+ lastmodifytime + ", xxxpics=" + xxxpics
				+ ", xxxrealpathpics=" + xxxrealpathpics + ", fromurl="
				+ fromurl + ", content=" + content + ", categoryname="
				+ categoryname + "]";
	}
}