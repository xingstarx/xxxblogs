package com.blogsxxx.model;

public class TimeLine {
    private Integer id;

    private Integer timestr;

    private String descr;

    private Integer articlecount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimestr() {
        return timestr;
    }

    public void setTimestr(Integer timestr) {
        this.timestr = timestr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Integer getArticlecount() {
        return articlecount;
    }

    public void setArticlecount(Integer articlecount) {
        this.articlecount = articlecount;
    }
}