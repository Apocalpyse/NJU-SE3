package VO.marketVO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/8.
 */
public class InvestVO {
	private static final long serialVersionUID = 1L;
	private String investID;
	private String title;//标题
	private String time;//发表时间
	private String author;//发表作者
	private int view;//浏览数目
	private int praise;//点赞数目
	private int criticize;//点踩数目
	private String content;//新闻内容
	private ArrayList<String> comment;//评论
	private ArrayList<String> commentAccount;//评论对应account
	public InvestVO(){

	}

	public InvestVO(String investID, String title, String time, String author, int view, int praise, int criticize, String content, ArrayList<String> comment, ArrayList<String> commentAccount) {
		this.investID = investID;
		this.title = title;
		this.time = time;
		this.author=author;
		this.view = view;
		this.praise = praise;
		this.criticize = criticize;
		this.content = content;
		this.comment = comment;
		this.commentAccount = commentAccount;
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
