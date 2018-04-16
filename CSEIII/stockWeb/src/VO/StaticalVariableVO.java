package VO;

/**
 * Created by Administrator on 2017/3/22.
 */
public class StaticalVariableVO {
    private String yearYieldRate;//年化收益率
    private String benchmarkYearYieldRate;//基准年化收益率
    private String alpha;//阿尔法
    private String beta;//贝塔
    private String sharpeRatio;//夏普比率
    private String maxWithdraw;//最大回撤

    public StaticalVariableVO() {
    }

    public StaticalVariableVO(String yearYieldRate, String benchmarkYearYieldRate, String alpha, String beta, String sharpeRatio, String maxWithdraw) {
        this.yearYieldRate = yearYieldRate;
        this.benchmarkYearYieldRate = benchmarkYearYieldRate;
        this.alpha = alpha;
        this.beta = beta;
        this.sharpeRatio = sharpeRatio;
        this.maxWithdraw = maxWithdraw;
    }

    public String getYearYieldRate() {
        return yearYieldRate;
    }

    public void setYearYieldRate(String yearYieldRate) {
        this.yearYieldRate = yearYieldRate;
    }

    public String getBenchmarkYearYieldRate() {
        return benchmarkYearYieldRate;
    }

    public void setBenchmarkYearYieldRate(String benchmarkYearYieldRate) {
        this.benchmarkYearYieldRate = benchmarkYearYieldRate;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getSharpeRatio() {
        return sharpeRatio;
    }

    public void setSharpeRatio(String sharpeRatio) {
        this.sharpeRatio = sharpeRatio;
    }

    public String getMaxWithdraw() {
        return maxWithdraw;
    }

    public void setMaxWithdraw(String maxWithdraw) {
        this.maxWithdraw = maxWithdraw;
    }
}
