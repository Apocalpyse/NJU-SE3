package blSer;

import VO.strategyVO.InputStrategyVO;
import VO.strategyVO.RelationGraphVO;
import VO.strategyVO.StrategyVO;
import VO.strategyVO.YieldRateDistributionVO;

/**
 * Created by A on 2017/5/21.
 */
public interface StrategyBlSer {
//    /**
//     * 获取策略的统计变量
//     * @param inputStrategyVO
//     * @return
//     * @throws Exception
//     */
//    //public StaticalVariableVO getStaticalVariable(InputStrategyVO inputStrategyVO) throws Exception;
//
//    /**
//     * 获取策略的策略和基准的累计收益率比较图的数据
//     * @param inputStrategyVO
//     * @return
//     * @throws Exception
//     */
//    //public StrategyGraphVO getGraphData(InputStrategyVO inputStrategyVO) throws Exception;

    /**
     * 获取策略信息，包括策略的统计变量、策略的策略和基准的累计收益率比较图的数据
     * @param inputStrategyVO
     * @return
     * @throws Exception
     */
    public StrategyVO getStrategy(InputStrategyVO inputStrategyVO) throws Exception;

    /**
     * 获取策略超额收益率与不同形成期/持有期的关系图以及策略胜率与不同形成期/持有期的关系图的数据
     * @param inputStrategyVO
     * @param type type可以为0或1，type=0时，代表用户选择的是形成期，type=1时，代表用户选择的是持有期
     * @return
     * @throws Exception
     */
    public RelationGraphVO getRelationGraphData(InputStrategyVO inputStrategyVO, int type) throws Exception;

    /**
     * 获取策略收益率分布直方图的数据
     * @param inputStrategyVO
     * @return
     * @throws Exception
     */
    public YieldRateDistributionVO getYieldRateDistribution(InputStrategyVO inputStrategyVO) throws Exception;

}
