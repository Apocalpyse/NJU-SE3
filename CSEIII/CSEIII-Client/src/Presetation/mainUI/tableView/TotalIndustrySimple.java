package Presetation.mainUI.tableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by A on 2017/5/16.
 */
public class TotalIndustrySimple {
    private final StringProperty industryName;
    private final StringProperty companyNum;
    private final StringProperty increaseOrDecreaseRate;
    private final StringProperty increaseOrDecreaseMoney;
    private final StringProperty totalVolume;
    private final StringProperty industryScore;

    public TotalIndustrySimple() {
        this(null,null,null,null,null,null);
    }

    public TotalIndustrySimple(String industryName, String companyNum, String increaseOrDecreaseRate,
                               String increaseOrDecreaseMoney, String totalVolume, String industryScore) {
        this.industryName = new SimpleStringProperty(industryName);;
        this.companyNum = new SimpleStringProperty(companyNum);
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
