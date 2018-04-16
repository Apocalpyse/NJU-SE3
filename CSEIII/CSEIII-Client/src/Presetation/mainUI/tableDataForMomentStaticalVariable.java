package Presetation.mainUI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 常德隆 on 2017/4/9.
 */
public class tableDataForMomentStaticalVariable {
    private SimpleStringProperty yearYieldRate;
    private SimpleStringProperty benchmarkYearYieldRate;
    private SimpleStringProperty alpha;
    private SimpleStringProperty beta;
    private SimpleStringProperty sharpeRatio;
    private SimpleStringProperty maxWithdraw;


    tableDataForMomentStaticalVariable(){}

    tableDataForMomentStaticalVariable(String yearYieldRate, String benchmarkYearYieldRate, String alpha, String beta, String sharpeRatio, String maxWithdraw){
        this.yearYieldRate=new SimpleStringProperty(yearYieldRate);
        this.benchmarkYearYieldRate=new SimpleStringProperty(benchmarkYearYieldRate);
        this.alpha=new SimpleStringProperty(alpha);
        this.beta=new SimpleStringProperty(beta);
        this.sharpeRatio=new SimpleStringProperty(sharpeRatio);
        this.maxWithdraw=new SimpleStringProperty(maxWithdraw);
    }

    public String getYearYieldRate() {
        return yearYieldRate.get();
    }

    public SimpleStringProperty yearYieldRateProperty() {
        return yearYieldRate;
    }

    public String getBenchmarkYearYieldRate() {
        return benchmarkYearYieldRate.get();
    }

    public SimpleStringProperty benchmarkYearYieldRateProperty() {
        return benchmarkYearYieldRate;
    }

    public String getAlpha() {
        return alpha.get();
    }

    public SimpleStringProperty alphaProperty() {
        return alpha;
    }

    public String getBeta() {
        return beta.get();
    }

    public SimpleStringProperty betaProperty() {
        return beta;
    }

    public String getSharpeRatio() {
        return sharpeRatio.get();
    }

    public SimpleStringProperty sharpeRatioProperty() {
        return sharpeRatio;
    }

    public String getMaxWithdraw() {
        return maxWithdraw.get();
    }

    public SimpleStringProperty maxWithdrawProperty() {
        return maxWithdraw;
    }

    public void setMaxWithdraw(String maxWithdraw) {
        this.maxWithdraw.set(maxWithdraw);
    }

    public void setSharpeRatio(String sharpeRatio) {
        this.sharpeRatio.set(sharpeRatio);
    }

    public void setBeta(String beta) {
        this.beta.set(beta);
    }

    public void setAlpha(String alpha) {
        this.alpha.set(alpha);
    }

    public void setBenchmarkYearYieldRate(String benchmarkYearYieldRate) {
        this.benchmarkYearYieldRate.set(benchmarkYearYieldRate);
    }

    public void setYearYieldRate(String yearYieldRate) {
        this.yearYieldRate.set(yearYieldRate);
    }
}
