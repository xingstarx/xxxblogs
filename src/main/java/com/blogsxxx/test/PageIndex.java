package com.blogsxxx.test;

/**
 * 页面页码类
 * @author Administrator
 */
public class PageIndex {
	private long startindex;//开始页码
	private long endindex;//结束页码
	
	public PageIndex(long startindex, long endindex) {
		this.startindex = startindex;
		this.endindex = endindex;
	}
	public long getStartindex() {
		return startindex;
	}
	public void setStartindex(long startindex) {
		this.startindex = startindex;
	}
	public long getEndindex() {
		return endindex;
	}
	public void setEndindex(long endindex) {
		this.endindex = endindex;
	}
	/**
	 * 根据页码数量，当前页，总页数构建页码索引
	 * @param pagecode 页码数量
	 * @param currentPage 当前页
	 * @param totalpage 总页数
	 * @return
	 */
	public static PageIndex getPageIndex(long pagecode, int currentPage, long totalpage){
			long startpage = currentPage-(pagecode%2==0? pagecode/2-1 : pagecode/2);
			long endpage = currentPage+pagecode/2;
			if(startpage<1){
				startpage = 1;
				if(totalpage>=pagecode) endpage = pagecode;
				else endpage = totalpage;
			}
			if(endpage>totalpage){
				endpage = totalpage;
				if((endpage-pagecode)>0) startpage = endpage-pagecode+1;
				else startpage = 1;
			}
			return new PageIndex(startpage, endpage);		
	}
}
