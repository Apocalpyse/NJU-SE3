package Presetation.mainUI;

import blSer.StrategyBlSer;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 常德隆 on 2017/4/11.
 */
public class tableDataForOnePlate {
    private SimpleStringProperty date;
    private SimpleStringProperty stockName;
    private SimpleStringProperty stockCode;
    private SimpleStringProperty increaseOrDecrease;
    private SimpleStringProperty open;
    private SimpleStringProperty high;
    private SimpleStringProperty low;
    private SimpleStringProperty close;
    private SimpleStringProperty volume;
    private SimpleStringProperty adjClose;
    private SimpleStringProperty market;
    private SimpleStringProperty preAdjClose;
    private SimpleStringProperty plate;

    public tableDataForOnePlate(){}

    public tableDataForOnePlate(String date,String stockName,String stockCode,String increaseOrDecrease,
                                String open,String high,String low,String close,String volume,String adjClose,String market,String preAdjClose,String plate){
        this.date=new SimpleStringProperty(date);
        this.stockName=new SimpleStringProperty(stockName);
        this.stockCode=new SimpleStringProperty(stockCode);
        this.increaseOrDecrease=new SimpleStringProperty(increaseOrDecrease);
        this.open=new SimpleStringProperty(open);
        this.high=new SimpleStringProperty(high);
        this.low=new SimpleStringProperty(low);
        this.close=new SimpleStringProperty(close);
        this.volume=new SimpleStringProperty(volume);
        this.adjClose=new SimpleStringProperty(adjClose);
        this.market=new SimpleStringProperty(market);
        this.preAdjClose=new SimpleStringProperty(preAdjClose);
        this.plate=new SimpleStringProperty(plate);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getStockName() {
        return stockName.get();
    }

    public SimpleStringProperty stockNameProperty() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName.set(stockName);
    }

    public String getStockCode() {
        return stockCode.get();
    }

    public SimpleStringProperty stockCodeProperty() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode.set(stockCode);
    }

    public String getIncreaseOrDecrease() {
        return increaseOrDecrease.get();
    }

    public SimpleStringProperty increaseOrDecreaseProperty() {
        return increaseOrDecrease;
    }

    public void setIncreaseOrDecrease(String increaseOrDecrease) {
        this.increaseOrDecrease.set(increaseOrDecrease);
    }

    public String getOpen() {
        return open.get();
    }

    public SimpleStringProperty openProperty() {
        return open;
    }

    public void setOpen(String open) {
        this.open.set(open);
    }

    public String getHigh() {
        return high.get();
    }

    public SimpleStringProperty highProperty() {
        return high;
    }

    public void setHigh(String high) {
        this.high.set(high);
    }

    public String getLow() {
        return low.get();
    }

    public SimpleStringProperty lowProperty() {
        return low;
    }

    public String getClose() {
        return close.get();
    }

    public SimpleStringProperty closeProperty() {
        return close;
    }

    public void setClose(String close) {
        this.close.set(close);
    }

    public String getVolume() {
        return volume.get();
    }

    public SimpleStringProperty volumeProperty() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }

    public String getAdjClose() {
        return adjClose.get();
    }

    public SimpleStringProperty adjCloseProperty() {
        return adjClose;
    }

    public void setAdjClose(String adjClose) {
        this.adjClose.set(adjClose);
    }

    public String getMarket() {
        return market.get();
    }

    public SimpleStringProperty marketProperty() {
        return market;
    }

    public void setMarket(String market) {
        this.market.set(market);
    }

    public String getPreAdjClose() {
        return preAdjClose.get();
    }

    public SimpleStringProperty preAdjCloseProperty() {
        return preAdjClose;
    }

    public void setPreAdjClose(String preAdjClose) {
        this.preAdjClose.set(preAdjClose);
    }

    public String getPlate() {
        return plate.get();
    }

    public SimpleStringProperty plateProperty() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate.set(plate);
    }

    public void setLow(String low) {
        this.low.set(low);
    }
}
