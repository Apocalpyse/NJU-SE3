package VO.strategyVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class YieldRateDistributionVO {
    private String positiveYieldNum;//正收益周期数
    private String negativeYieldNum;//负收益周期数
    private String winRate;//赢率
    private ArrayList<String> yieldRate;//从最小到最大排列，如 1%,5%，6%
    private ArrayList<String> positiveFrequency;//正收益频数
    private ArrayList<String> negativeFrequency;//负收益频数

    public YieldRateDistributionVO() {
    }

    public YieldRateDistributionVO(String positiveYieldNum, String negativeYieldNum, String winRate,
                                   ArrayList<String> yieldRate, ArrayList<String> positiveFrequency, ArrayList<String> negativeFrequency) {
        this.positiveYieldNum = positiveYieldNum;
        this.negativeYieldNum = negativeYieldNum;
        this.winRate = winRate;
        this.yieldRate = yieldRate;
        this.positiveFrequency = positiveFrequency;
        this.negativeFrequency = negativeFrequency;
    }

    public String getPositiveYieldNum() {
        return positiveYieldNum;
    }

    public void setPositiveYieldNum(String positiveYieldNum) {
        this.positiveYieldNum = positiveYieldNum;
    }

    public String getNegativeYieldNum() {
        return negativeYieldNum;
    }

    public void setNegativeYieldNum(String negativeYieldNum) {
        this.negativeYieldNum = negativeYieldNum;
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public ArrayList<String> getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(ArrayList<String> yieldRate) {
        this.yieldRate = yieldRate;
    }

    public ArrayList<String> getPositiveFrequency() {
        return positiveFrequency;
    }

    public void setPositiveFrequency(ArrayList<String> positiveFrequency) {
        this.positiveFrequency = positiveFrequency;
    }

    public ArrayList<String> getNegativeFrequency() {
        return negativeFrequency;
    }

    public void setNegativeFrequency(ArrayList<String> negativeFrequency) {
        this.negativeFrequency = negativeFrequency;
    }
}
