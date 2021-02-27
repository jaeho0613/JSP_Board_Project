package modle;

import java.sql.Date;

public class BoardDTO {
	int bdNum;
	String bdTitle;
	String bdAuthor;
	Date bdCreateDate;
	int bdViews;

	public int getBdNum() {
		return bdNum;
	}

	public BoardDTO setBdNum(int bdNum) {
		this.bdNum = bdNum;
		return this;
	}

	public String getBdTitle() {
		return bdTitle;
	}

	public BoardDTO setBdTitle(String bdTitle) {
		this.bdTitle = bdTitle;
		return this;
	}

	public String getBdAuthor() {
		return bdAuthor;
	}

	public BoardDTO setBdAuthor(String bdAuthor) {
		this.bdAuthor = bdAuthor;
		return this;
	}

	public Date getBdCreateDate() {
		return bdCreateDate;
	}

	public BoardDTO setBdCreateDate(Date bdCreateDate) {
		this.bdCreateDate = bdCreateDate;
		return this;
	}

	public int getBdViews() {
		return bdViews;
	}

	public BoardDTO setBdViews(int bdViews) {
		this.bdViews = bdViews;
		return this;
	}
}
