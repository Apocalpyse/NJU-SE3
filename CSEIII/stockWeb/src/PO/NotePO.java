package PO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public class NotePO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String noteId;
	private String title;//帖子标题
	private String account;//作者
	private String time;//发表时间
	private NotePool source;//帖子所在，分个股，策略
	private String stockCodeOrMethodID;
	private int view;//浏览数目
	private int praise;//点赞数目
	private int criticize;//点踩数目
	private ArrayList<String> comment;//评论
	private ArrayList<String> commentAccount;//评论对应account
	public NotePO(){

	}

	public NotePO(String noteId, String title, String account, String time, NotePool source, String stockCodeOrMethodID, int view, int praise, int criticize, ArrayList<String> comment, ArrayList<String> commentAccount) {
		this.noteId = noteId;
		this.title = title;
		this.account = account;
		this.time = time;
		this.source = source;
		this.stockCodeOrMethodID = stockCodeOrMethodID;
		this.view = view;
		this.praise = praise;
		this.criticize = criticize;
		this.comment = comment;
		this.commentAccount = commentAccount;
	}

	public String getStockCodeOrMethodID() {
		return stockCodeOrMethodID;
	}

	public void setStockCodeOrMethodID(String stockCodeOrMethodID) {
		this.stockCodeOrMethodID = stockCodeOrMethodID;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public NotePool getSource() {
		return source;
	}

	public void setSource(NotePool source) {
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
