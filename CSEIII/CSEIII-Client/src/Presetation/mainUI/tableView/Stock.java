package Presetation.mainUI.tableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by A on 2017/5/17.
 */
public class Stock {
    private final StringProperty code;
    private final StringProperty name;
    private final StringProperty open;//开盘指数
    private final StringProperty close;//收盘指数
    private final StringProperty increaseOrDecrease;//涨跌幅
    private final StringProperty volume;//成交量

    public Stock() {
        this(null,null,null,null,null,null);
    }

    public Stock(String code, String name, String open, String close, String increaseOrDecrease, String volume) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.open = new SimpleStringProperty(open);
        this.close = new SimpleStringProperty(close);
        this.increaseOrDecrease = new SimpleStringProperty(increaseOrDecrease);
        this.volume = new SimpleStringProperty(volume);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getOpen() {
        return open.get();
    }

    public StringProperty openProperty() {
        return open;
    }

    public void setOpen(String open) {
        this.open.set(open);
    }

    public String getClose() {
        return close.get();
    }

    public StringProperty closeProperty() {
        return close;
    }

    public void setClose(String close) {
        this.close.set(close);
    }

    public String getIncreaseOrDecrease() {
        return increaseOrDecrease.get();
    }

    public StringProperty increaseOrDecreaseProperty() {
        return increaseOrDecrease;
    }

    public void setIncreaseOrDecrease(String increaseOrDecrease) {
        this.increaseOrDecrease.set(increaseOrDecrease);
    }

    public String getVolume() {
        return volume.get();
    }

    public StringProperty volumeProperty() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }
}
