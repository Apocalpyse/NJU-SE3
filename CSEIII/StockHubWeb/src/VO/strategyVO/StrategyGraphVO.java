package VO.strategyVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class StrategyGraphVO {
    private ArrayList<String> date;//日期
    private ArrayList<String> strategyYield;//策略累计收益率
    private ArrayList<String> benchmarkYield;//基准累计收益率
    private String maxWithdrawStart;//最大回撤开始日期
    private String maxWithdrawEnd;//最大回撤结束日期

    public StrategyGraphVO() {
    }

    public StrategyGraphVO(ArrayList<String> date, ArrayList<String> strategyYield, ArrayList<String> benchmarkYield,
                           String maxWithdrawStart, String maxWithdrawEnd) {
        this.date = date;
        this.strategyYield = strategyYield;
        this.benchmarkYield = benchmarkYield;
        this.maxWithdrawStart = maxWithdrawStart;
        this.maxWithdrawEnd = maxWithdrawEnd;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<String> getStrategyYield() {
        return strategyYield;
    }

    public void setStrategyYield(ArrayList<String> strategyYield) {
        this.strategyYield = strategyYield;
    }

    public ArrayList<String> getBenchmarkYield() {
        return benchmarkYield;
    }

    public void setBenchmarkYield(ArrayList<String> benchmarkYield) {
        this.benchmarkYield = benchmarkYield;
    }

    public String getMaxWithdrawStart() {
        return maxWithdrawStart;
    }

    public void setMaxWithdrawStart(String maxWithdrawStart) {
        this.maxWithdrawStart = maxWithdrawStart;
    }

    public String getMaxWithdrawEnd() {
        return maxWithdrawEnd;
    }

    public void setMaxWithdrawEnd(String maxWithdrawEnd) {
        this.maxWithdrawEnd = maxWithdrawEnd;
    }
}
