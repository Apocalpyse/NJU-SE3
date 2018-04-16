package PO.stockPO;

/**
 * Created by delong chang on 2017/6/1.
 */
public class MainEventPO {
    private String Industry;
    private String title;
    private String content;
    private String time;

    public MainEventPO(String industry, String title, String content, String time) {
        Industry = industry;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
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
}
