package VO;

/**
 * Created by Administrator on 2017/3/23.
 */
public class AllStrategyVO {
    private StaticalVariableVO staticalVariableVO;//统计变量
    private StrategyGraphVO strategyGraphVO;//策略和基准的累计收益率比较图
    private RelationGraphVO relationGraphVO;//超额收益率与不同形成期/持有期的关系图以及策略胜率与不同形成期/持有期的关系图
    private YieldRateDistributionVO yieldRateDistributionVO;//收益率分布直方图

    public AllStrategyVO() {
    }

    public AllStrategyVO(StaticalVariableVO staticalVariableVO, StrategyGraphVO strategyGraphVO,
                         RelationGraphVO relationGraphVO, YieldRateDistributionVO yieldRateDistributionVO) {
        this.staticalVariableVO = staticalVariableVO;
        this.strategyGraphVO = strategyGraphVO;
        this.relationGraphVO = relationGraphVO;
        this.yieldRateDistributionVO = yieldRateDistributionVO;
    }

    public StaticalVariableVO getStaticalVariableVO() {
        return staticalVariableVO;
    }

    public void setStaticalVariableVO(StaticalVariableVO staticalVariableVO) {
        this.staticalVariableVO = staticalVariableVO;
    }

    public StrategyGraphVO getStrategyGraphVO() {
        return strategyGraphVO;
    }

    public void setStrategyGraphVO(StrategyGraphVO strategyGraphVO) {
        this.strategyGraphVO = strategyGraphVO;
    }

    public RelationGraphVO getRelationGraphVO() {
        return relationGraphVO;
    }

    public void setRelationGraphVO(RelationGraphVO relationGraphVO) {
        this.relationGraphVO = relationGraphVO;
    }

    public YieldRateDistributionVO getYieldRateDistributionVO() {
        return yieldRateDistributionVO;
    }

    public void setYieldRateDistributionVO(YieldRateDistributionVO yieldRateDistributionVO) {
        this.yieldRateDistributionVO = yieldRateDistributionVO;
    }
}
