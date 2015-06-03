package com.blogsxxx.test;


import java.util.List;
/**
 * 分页组件
 * @author Administrator
 * @param <T>
 */
public class PageView<T> {
	/** 分页数据 **/
	private List<T> records;
	/** 页码开始索引和结束索引 **/
	private PageIndex pageindex;
	/** 总页数 **/
	private long totalpage = 1;
	/** 每页显示记录数，默认12 **/
	private int recordPerPage=12;
	/** 当前页 **/
	private int currentpage = 1;
	/** 总记录数 **/
	private long totalrecord;
	/** 页码数量，默认10 **/
	private int pagecode = 10;
	/** 获取记录的开始索引 **/
	public int getStartRecordIndex() {
		return (this.currentpage-1)*this.recordPerPage;
	}
	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
	/**
	 * 构造函数
	 * @param recordPerPage 每页显示的记录数
	 * @param currentpage 当前页
	 */
	public PageView(int recordPerPage, int currentpage) {
		this.recordPerPage = recordPerPage;
		this.currentpage = currentpage;
	}
	/**
	 * 构造函数
	 * @param recordPerPage 每页显示的记录数
	 * @param currentpage 当前页
	 * @param pagecode 页码数量
	 */
	public PageView(int recordPerPage, int currentpage,int pagecode) {
		this.recordPerPage = recordPerPage;
		this.currentpage = currentpage;
		this.pagecode=pagecode;
	}
	
	public void setQueryResult(QueryResult<T> qr){
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}
	
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		//总记录数变化时，要重新计算总页码和页码开始，结束索引
		setTotalpage(this.totalrecord%this.recordPerPage==0? this.totalrecord/this.recordPerPage : this.totalrecord/this.recordPerPage+1);
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public PageIndex getPageindex() {
		return pageindex;
	}
	public long getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageindex = PageIndex.getPageIndex(pagecode, currentpage, totalpage);
	}
	public int getRecordPerPage() {
		return recordPerPage;
	}
	public int getCurrentpage() {
		return currentpage;
	}
}

