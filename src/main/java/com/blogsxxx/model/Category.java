package com.blogsxxx.model;

public class Category {
    private Integer id;

    private String categoryname;

    private Integer articlecount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public Integer getArticlecount() {
        return articlecount;
    }

    public void setArticlecount(Integer articlecount) {
        this.articlecount = articlecount;
    }
}