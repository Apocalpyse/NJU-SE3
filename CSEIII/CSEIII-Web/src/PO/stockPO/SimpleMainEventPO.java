package PO.stockPO;

/**
 * Created by A on 2017/6/2.
 */
public class SimpleMainEventPO {
    private String Industry;
    private String title;
    private String time;

    public SimpleMainEventPO() {
    }

    public SimpleMainEventPO(String industry, String title, String time) {
        Industry = industry;
        this.title = title;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
