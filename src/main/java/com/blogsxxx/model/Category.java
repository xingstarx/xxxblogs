package com.blogsxxx.model;

public class Category {
    private Integer id;

    private String categoryname;

	private Integer articlecount;

    public Integer getArticlecount() {
        return articlecount;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public Integer getId() {
        return id;
    }

    public void setArticlecount(Integer articlecount) {
        this.articlecount = articlecount;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
	public String toString() {
		return "Category [id=" + id + ", categoryname=" + categoryname
				+ ", articlecount=" + articlecount + "]";
	}
}