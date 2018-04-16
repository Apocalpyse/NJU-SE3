package PO;


/**
 * Created by chenjin on 2017/5/6.
 */
public  class SimpleNewsPO {
	private static final long serialVersionUID = 1L;
	private String newsID;
	private String title;
	private String author;

	public SimpleNewsPO() {
	}

	public SimpleNewsPO(String newsID, String title, String author) {
		this.newsID = newsID;
		this.title = title;
		this.author = author;
	}

	public String getNewsID() {
		return newsID;
	}

	public void setNewsID(String newsID) {
		this.newsID = newsID;
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