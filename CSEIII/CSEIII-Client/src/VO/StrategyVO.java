package VO;

/**
 * Created by Administrator on 2017/4/12.
 */
public class StrategyVO {
    private StaticalVariableVO staticalVariableVO;
    private StrategyGraphVO strategyGraphVO;
    private StrategyEvaluateVO strategyEvaluateVO;

    public StrategyVO() {
    }

    public StrategyVO(StaticalVariableVO staticalVariableVO, StrategyGraphVO strategyGraphVO, StrategyEvaluateVO strategyEvaluateVO) {
        this.staticalVariableVO = staticalVariableVO;
        this.strategyGraphVO = strategyGraphVO;
        this.strategyEvaluateVO = strategyEvaluateVO;
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

    public StrategyEvaluateVO getStrategyEvaluateVO() {
        return strategyEvaluateVO;
    }

    public void setStrategyEvaluateVO(StrategyEvaluateVO strategyEvaluateVO) {
        this.strategyEvaluateVO = strategyEvaluateVO;
    }
}
