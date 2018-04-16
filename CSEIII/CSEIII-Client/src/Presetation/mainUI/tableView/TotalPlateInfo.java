package Presetation.mainUI.tableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by A on 2017/5/17.
 */
public class TotalPlateInfo {
    private final StringProperty first;
    private final StringProperty second;
    private final StringProperty third;
    private final StringProperty forth;

    public TotalPlateInfo() {
        this(null,null,null,null);
    }

    public TotalPlateInfo(String first, String second, String third, String forth) {
        this.first = new SimpleStringProperty(first);
        this.second = new SimpleStringProperty(second);
        this.third = new SimpleStringProperty(third);
        this.forth = new SimpleStringProperty(forth);
    }

    public String getFirst() {
        return first.get();
    }

    public StringProperty firstProperty() {
        return first;
    }

    public void setFirst(String first) {
        this.first.set(first);
    }

    public String getSecond() {
        return second.get();
    }

    public StringProperty secondProperty() {
        return second;
    }

    public void setSecond(String second) {
        this.second.set(second);
    }

    public String getThird() {
        return third.get();
    }

    public StringProperty thirdProperty() {
        return third;
    }

    public void setThird(String third) {
        this.third.set(third);
    }

    public String getForth() {
        return forth.get();
    }

    public StringProperty forthProperty() {
        return forth;
    }

    public void setForth(String forth) {
        this.forth.set(forth);
    }
}
