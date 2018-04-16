package VO.marketVO;

/**
 * Created by chenjin on 2017/6/8.
 */
public class SimpleInvestVO {
	private String investID;
	private String title;
	private String author;

	public SimpleInvestVO() {
	}

	public SimpleInvestVO(String investID, String title, String author) {
		this.investID = investID;
		this.title = title;
		this.author = author;
	}

	public String getInvestID() {
		return investID;
	}

	public void setInvestID(String investID) {
		this.investID = investID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
