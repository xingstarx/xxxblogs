package com.blogsxxx.model;
/**
 * @desc csdn上面的文章
 * @author xiongxingxing
 *
 */
public class CsdnCategory {
    private Integer id;

    private String url;

    private String categoryname;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

	@Override
	public String toString() {
		return "CsdnCategory [id=" + id + ", url=" + url + ", categoryname="
				+ categoryname + ", count=" + count + "]";
	}
    
}