package PO.marketPO;

/**
 * Created by A on 2017/5/21.
 */
public class SimpleNewsPO {
    private String newsID;
    private String title;
    private String classify;

    public SimpleNewsPO() {
    }

    public SimpleNewsPO(String newsID, String title, String classify) {
        this.newsID = newsID;
        this.title = title;
        this.classify=classify;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
