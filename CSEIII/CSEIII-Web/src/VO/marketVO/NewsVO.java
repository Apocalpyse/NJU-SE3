package VO.marketVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class NewsVO {
    private String newsID;
    private String title;//标题
    private String classify;//作者
    private String time;//发表时间
    private int view;//浏览数目
    private int praise;//点赞数目
    private int criticize;//点踩数目
    private String content;//新闻内容
    private ArrayList<String> comment;//评论
    private ArrayList<String> commentAccount;//评论对应account
    public NewsVO(){

    }

    public NewsVO(String newsID, String title, String classify, String time,  int view, int praise, int criticize, String content, ArrayList<String> comment, ArrayList<String> commentAccount) {
        this.newsID = newsID;
        this.title = title;
        this.classify = classify;
        this.time = time;

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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
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
