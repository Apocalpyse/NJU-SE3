package PO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public class NewsPO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String newsID;
	private String title;//标题
	private String author;//作者
	private String time;//发表时间
	private String source;//来源
	private int view;//浏览数目
	private int praise;//点赞数目
	private int criticize;//点踩数目
	private String content;//新闻内容
	private ArrayList<String> comment;//评论
	private ArrayList<String> commentAccount;//评论对应account
	public NewsPO(){

	}

	public NewsPO(String newsID, String title, String author, String time, String source, int view, int praise, int criticize, String content, ArrayList<String> comment, ArrayList<String> commentAccount) {
		this.newsID = newsID;
		this.title = title;
		this.author = author;
		this.time = time;
		this.source = source;
		this.view = view;
		this.praise = praise;
		this.criticize = criticize;
		this.content = content;
		this.comment = comment;
		this.commentAccount = commentAccount;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public int getCriticize() {
		return criticize;
	}

	public void setCriticize(int criticize) {
		this.criticize = criticize;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getComment() {
		return comment;
	}

	public void setComment(ArrayList<String> comment) {
		this.comment = comment;
	}

	public ArrayList<String> getCommentAccount() {
		return commentAccount;
	}

	public void setCommentAccount(ArrayList<String> commentAccount) {
		this.commentAccount = commentAccount;
	}
}
