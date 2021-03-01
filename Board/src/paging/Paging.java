package paging;

public class Paging {
	int pageCount = 0; // 총 페이지 수
	int pageBlock = 10; // 한 페이지에 보여줄 페이지 블럭 수
	int pageSize = 10;  // 한 블럭에 보여줄 리스트 갯수

	String pageNum = null; // Null값을 검사하기 위한 pageNum
	int currentPage = 0; // 검사된 페이지 값을 계산하기 위한 int pageNum

	// 한 페이지에 보여줄 시작 번호 및 끝 번호
	int startPage = 0;
	int endPage = 0;

	// 하단 페이지 링크 시작 번호 및 끝 번호
	int startRow = 0;
	int endRow = 0;
	int totalRow = 0;

	// 생성자 : 초기 셋팅
	public Paging(String pageNum, int pageCount) {

		this.pageNum = pageNum;
		this.pageCount = pageCount;

		if (this.pageNum == null) {
			pageNum = "1";
		}

		currentPage = Integer.parseInt(pageNum);

		startRow = (currentPage - 1) * pageSize + 1;
		endRow = currentPage * pageSize;

		startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		endPage = startPage + pageBlock - 1;

		if (pageCount % pageSize > 0) {
			totalRow = (pageCount / pageSize) + 1;
		} else {
			totalRow = (pageCount / pageSize);
		}
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
}
