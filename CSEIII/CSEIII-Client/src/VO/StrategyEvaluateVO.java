package VO;

/**
 * Created by Administrator on 2017/4/19.
 */

/**
 * 用来评估策略分数的vo，在界面以雷达图的形式展示出来
 */
public class StrategyEvaluateVO {
    private int generalScore;//总评
    private int yield;//收益分数
    private int resistRisk;//抗风险分数
    private int stable;//稳定性分数
    private int riskYield;//风险收益分数
    private int yieldSpace;//收益空间分数

    public StrategyEvaluateVO() {
    }

    public StrategyEvaluateVO(int generalScore, int yield, int resistRisk, int stable, int riskYield, int yieldSpace) {
        this.generalScore = generalScore;
        this.yield = yield;
        this.resistRisk = resistRisk;
        this.stable = stable;
        this.riskYield = riskYield;
        this.yieldSpace = yieldSpace;
    }

    public int getGeneralScore() {
        return generalScore;
    }

    public void setGeneralScore(int generalScore) {
        this.generalScore = generalScore;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public int getResistRisk() {
        return resistRisk;
    }

    public void setResistRisk(int resistRisk) {
        this.resistRisk = resistRisk;
    }

    public int getStable() {
        return stable;
    }

    public void setStable(int stable) {
        this.stable = stable;
    }

    public int getRiskYield() {
        return riskYield;
    }

    public void setRiskYield(int riskYield) {
        this.riskYield = riskYield;
    }

    public int getYieldSpace() {
        return yieldSpace;
    }

    public void setYieldSpace(int yieldSpace) {
        this.yieldSpace = yieldSpace;
    }
}
