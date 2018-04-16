package VO.marketVO;

/**
 * Created by A on 2017/5/21.
 */
public class SimpleNewsVO {
    private String newsID;
    private String title;
    private String author;

    public SimpleNewsVO() {
    }

    public SimpleNewsVO(String newsID, String title, String author) {
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
