package pack.spring.project.common;

public class PageVO {
	private int totalRecord;
	private int nowPage ;
	private int totalPage;
	private int numPerPage;
	private int nowBlock;
	private int pagePerBlock;
	private int totalBlock;
	private int listSize;
	private int pageStart;
	private int pageEnd;
	
	public PageVO() {
		super();
	}
	
	public PageVO(int totalRecord, int nowPage, int totalPage, int numPerPage, int nowBlock, int pagePerBlock,
			int totalBlock, int listSize, int pageStart, int pageEnd) {
		super();
		this.totalRecord = totalRecord;
		this.nowPage = nowPage;
		this.totalPage = totalPage;
		this.numPerPage = numPerPage;
		this.nowBlock = nowBlock;
		this.pagePerBlock = pagePerBlock;
		this.totalBlock = totalBlock;
		this.listSize = listSize;
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	@Override
	public String toString() {
		return "PageVO [totalRecord=" + totalRecord + ", nowPage=" + nowPage + ", totalPage=" + totalPage
				+ ", numPerPage=" + numPerPage + ", nowBlock=" + nowBlock + ", pagePerBlock=" + pagePerBlock
				+ ", totalBlock=" + totalBlock + ", listSize=" + listSize + ", pageStart=" + pageStart + ", pageEnd="
				+ pageEnd + "]";
	}



}
