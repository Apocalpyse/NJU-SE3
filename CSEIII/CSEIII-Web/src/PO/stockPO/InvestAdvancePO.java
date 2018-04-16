package PO.stockPO;

/**
 * Created by delong chang on 2017/6/1.
 */
public class InvestAdvancePO {
    private String title;//标题
    private String content;//内容
    private String time;//发表时间
    private String author;//作者

    public InvestAdvancePO(String title, String content, String time, String author) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
