package com.blogsxxx.model;

public class TimeLine {
    private Integer id;

    private Integer timestr;

    private String descr;

    private Integer articlecount;

    public Integer getArticlecount() {
        return articlecount;
    }

    public String getDescr() {
        return descr;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTimestr() {
        return timestr;
    }

    public void setArticlecount(Integer articlecount) {
        this.articlecount = articlecount;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

	public void setId(Integer id) {
        this.id = id;
    }

    public void setTimestr(Integer timestr) {
        this.timestr = timestr;
    }

    @Override
	public String toString() {
		return "TimeLine [id=" + id + ", timestr=" + timestr + ", descr="
				+ descr + ", articlecount=" + articlecount + "]";
	}
}