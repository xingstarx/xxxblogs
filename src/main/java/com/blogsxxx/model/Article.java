package com.blogsxxx.model;

import java.util.Date;

import org.jsoup.Jsoup;

import com.blogsxxx.util.HtmlUtils;

public class Article {
    private Integer id;//主键

    private String title;//文章标题

    private String pics;//ueditor上传的图片路径

    private String creater;//创建人

    private Date createtime;//创建时间

    private String lastmodifier;//最后修改人

    private Date lastmodifytime;//最后修改时间

    private String content;//文章内容

    private String xxxpics;//content里面存放的图片路径
    
    private String xxxrealpathpics;//content里面存放的图片路径 ,在服务器上的图片路径
    
    private String fromurl;//来源
    private String categoryname;//类别名称
    private Integer categoryid;//类别id
    
    public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public Integer getCategoryid() {
		return categoryid;
	}

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
		this.fromurl = fromurl;
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
		this.xxxpics = xxxpics;
	}

    public void setXxxrealpathpics(String xxxrealpathpics) {
		this.xxxrealpathpics = xxxrealpathpics;
	}

    @Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", pics=" + pics
				+ ", creater=" + creater + ", createtime=" + createtime
				+ ", lastmodifier=" + lastmodifier + ", lastmodifytime="
				+ lastmodifytime + ", content=" + content + ", xxxpics="
				+ xxxpics + ", xxxrealpathpics=" + xxxrealpathpics
				+ ", fromurl=" + fromurl + ", categoryname=" + categoryname
				+ ", categoryid=" + categoryid + "]";
	}
    /**
     * @desc 只取出文本元素
     * @author xiongxingxing
     */
    public void setTextContent(){
    	String content = getContent();
    	content=HtmlUtils.htmltoText(content);
    	if (content != null) {
			content = content.length() > 40 ? content.substring(0, 40)
					: content;
			
			setContent(content);
		}
    }
}