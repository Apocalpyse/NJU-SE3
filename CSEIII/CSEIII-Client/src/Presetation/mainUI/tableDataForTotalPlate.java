package Presetation.mainUI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 常德隆 on 2017/4/11.
 */
public class tableDataForTotalPlate {
    private SimpleStringProperty date;
    private SimpleStringProperty plateName;
    private SimpleStringProperty companyNum;
    private SimpleStringProperty averageOpen;
    private SimpleStringProperty averageClose;
    private SimpleStringProperty averageAdjClose;
    private SimpleStringProperty preAverageAdjClose;
    private SimpleStringProperty increaseOrDecreaseMoney;
    private SimpleStringProperty increaseOrDecreaseRate;
    private SimpleStringProperty totalVolume;

    public tableDataForTotalPlate(){}

    public tableDataForTotalPlate(String d, String p1, String c, String a1, String a2,String a3 ,String p2, String i1, String i2, String t){
        this.date=new SimpleStringProperty(d);
        this.plateName=new SimpleStringProperty(p1);
        this.companyNum=new SimpleStringProperty(c);
        this.averageOpen=new SimpleStringProperty(a1);
        this.averageClose=new SimpleStringProperty(a2);
        this.averageAdjClose=new SimpleStringProperty(a3);
        this.preAverageAdjClose=new SimpleStringProperty(p2);
        this.increaseOrDecreaseMoney=new SimpleStringProperty(i1);
        this.increaseOrDecreaseRate=new SimpleStringProperty(i2);
        this.totalVolume=new SimpleStringProperty(t);
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

    public String getPlateName() {
        return plateName.get();
    }

    public SimpleStringProperty plateNameProperty() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName.set(plateName);
    }

    public String getCompanyNum() {
        return companyNum.get();
    }

    public SimpleStringProperty companyNumProperty() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum.set(companyNum);
    }

    public String getAverageOpen() {
        return averageOpen.get();
    }

    public SimpleStringProperty averageOpenProperty() {
        return averageOpen;
    }

    public void setAverageOpen(String averageOpen) {
        this.averageOpen.set(averageOpen);
    }

    public String getAverageClose() {
        return averageClose.get();
    }

    public SimpleStringProperty averageCloseProperty() {
        return averageClose;
    }

    public void setAverageClose(String averageClose) {
        this.averageClose.set(averageClose);
    }

    public String getAverageAdjClose() {
        return averageAdjClose.get();
    }

    public SimpleStringProperty averageAdjCloseProperty() {
        return averageAdjClose;
    }

    public void setAverageAdjClose(String averageAdjClose) {
        this.averageAdjClose.set(averageAdjClose);
    }

    public String getPreAverageAdjClose() {
        return preAverageAdjClose.get();
    }

    public SimpleStringProperty preAverageAdjCloseProperty() {
        return preAverageAdjClose;
    }

    public void setPreAverageAdjClose(String preAverageAdjClose) {
        this.preAverageAdjClose.set(preAverageAdjClose);
    }

    public String getIncreaseOrDecreaseMoney() {
        return increaseOrDecreaseMoney.get();
    }

    public SimpleStringProperty increaseOrDecreaseMoneyProperty() {
        return increaseOrDecreaseMoney;
    }

    public void setIncreaseOrDecreaseMoney(String increaseOrDecreaseMoney) {
        this.increaseOrDecreaseMoney.set(increaseOrDecreaseMoney);
    }

    public String getIncreaseOrDecreaseRate() {
        return increaseOrDecreaseRate.get();
    }

    public SimpleStringProperty increaseOrDecreaseRateProperty() {
        return increaseOrDecreaseRate;
    }

    public void setIncreaseOrDecreaseRate(String increaseOrDecreaseRate) {
        this.increaseOrDecreaseRate.set(increaseOrDecreaseRate);
    }

    public String getTotalVolume() {
        return totalVolume.get();
    }

    public SimpleStringProperty totalVolumeProperty() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume.set(totalVolume);
    }
}
