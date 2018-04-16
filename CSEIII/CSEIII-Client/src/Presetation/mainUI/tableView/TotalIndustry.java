package Presetation.mainUI.tableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by A on 2017/5/16.
 */
public class TotalIndustry {
    private final StringProperty industryName;
    private final StringProperty companyNum;
    private final StringProperty averageOpen;//平均开盘价
    private final StringProperty averageClose;//平均收盘价
    private final StringProperty increaseOrDecreaseRate;
    private final StringProperty increaseOrDecreaseMoney;
    private final StringProperty totalVolume;
    private final StringProperty industryScore;

    public TotalIndustry() {
        this(null,null,null, null,null,null,null,null);
    }

    public TotalIndustry(String industryName, String companyNum, String averageOpen,
                         String averageClose, String increaseOrDecreaseRate, String increaseOrDecreaseMoney,
                         String totalVolume, String industryScore) {
        this.industryName = new SimpleStringProperty(industryName);
        this.companyNum = new SimpleStringProperty(companyNum);
        this.averageOpen = new SimpleStringProperty(averageOpen);
        this.averageClose = new SimpleStringProperty(averageClose);
        this.increaseOrDecreaseRate = new SimpleStringProperty(increaseOrDecreaseRate);
        this.increaseOrDecreaseMoney = new SimpleStringProperty(increaseOrDecreaseMoney);
        this.totalVolume = new SimpleStringProperty(totalVolume);
        this.industryScore = new SimpleStringProperty(industryScore);
    }

    public String getIndustryName() {
        return industryName.get();
    }

    public StringProperty industryNameProperty() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName.set(industryName);
    }

    public String getCompanyNum() {
        return companyNum.get();
    }

    public StringProperty companyNumProperty() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum.set(companyNum);
    }

    public String getAverageOpen() {
        return averageOpen.get();
    }

    public StringProperty averageOpenProperty() {
        return averageOpen;
    }

    public void setAverageOpen(String averageOpen) {
        this.averageOpen.set(averageOpen);
    }

    public String getAverageClose() {
        return averageClose.get();
    }

    public StringProperty averageCloseProperty() {
        return averageClose;
    }

    public void setAverageClose(String averageClose) {
        this.averageClose.set(averageClose);
    }

    public String getIncreaseOrDecreaseRate() {
        return increaseOrDecreaseRate.get();
    }

    public StringProperty increaseOrDecreaseRateProperty() {
        return increaseOrDecreaseRate;
    }

    public void setIncreaseOrDecreaseRate(String increaseOrDecreaseRate) {
        this.increaseOrDecreaseRate.set(increaseOrDecreaseRate);
    }

    public String getIncreaseOrDecreaseMoney() {
        return increaseOrDecreaseMoney.get();
    }

    public StringProperty increaseOrDecreaseMoneyProperty() {
        return increaseOrDecreaseMoney;
    }

    public void setIncreaseOrDecreaseMoney(String increaseOrDecreaseMoney) {
        this.increaseOrDecreaseMoney.set(increaseOrDecreaseMoney);
    }

    public String getTotalVolume() {
        return totalVolume.get();
    }

    public StringProperty totalVolumeProperty() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume.set(totalVolume);
    }

    public String getIndustryScore() {
        return industryScore.get();
    }

    public StringProperty industryScoreProperty() {
        return industryScore;
    }

    public void setIndustryScore(String industryScore) {
        this.industryScore.set(industryScore);
    }
}
