package bl.strategyBl;

import VO.strategyVO.*;
import blSer.StockPoolBl;
import blSer.StrategyBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class StrategyController implements StrategyBlSer {

    public static void main(String[] args) throws Exception{
        StrategyController strategyController=new StrategyController();
        InputStrategyVO inputStrategyVO=new InputStrategyVO();
        inputStrategyVO.setType(StrategyType.MOMENT);
        inputStrategyVO.setFormationPeriod("10");
        inputStrategyVO.setHoldingPeriod("5");
        inputStrategyVO.setStart("6/14/16");
        inputStrategyVO.setEnd("6/14/17");
        inputStrategyVO.setStockPoolBl(StockPoolBl.HUSHEN300);
        inputStrategyVO.setStockNameOrCode(new ArrayList<>());
		try {
			StrategyVO strategyVO=strategyController.getStrategy(inputStrategyVO);
			RelationGraphVO relationGraphVO=strategyController.getRelationGraphData(inputStrategyVO,0);
			YieldRateDistributionVO yieldRateDistributionVO=strategyController.getYieldRateDistribution(inputStrategyVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取策略信息，包括策略的统计变量、策略的策略和基准的累计收益率比较图的数据
	 * @param inputStrategyVO
	 * @return
	 * @throws Exception
	 */
    @Override
    public StrategyVO getStrategy(InputStrategyVO inputStrategyVO) throws Exception {
        //获取strategyGraphVO
        StrategyGraph strategyGraph=new StrategyGraph();
        StrategyGraphVO strategyGraphVO=strategyGraph.getGraphData(inputStrategyVO);
        if((strategyGraphVO.getDate()==null)||(strategyGraphVO.getDate().size()==0)){
            return null;
        }

        //获取strategyGraphVO
        StaticalVariable staticalVariable=new StaticalVariable();
        StaticalVariableVO staticalVariableVO=staticalVariable.getStaticalVariable(strategyGraphVO,inputStrategyVO.getStart(),inputStrategyVO.getEnd());
        //获取策略评估雷达图的数据
        String strategyYearYield=staticalVariableVO.getYearYieldRate();
        String maxWithdraw=staticalVariableVO.getMaxWithdraw();
        String beta=staticalVariableVO.getBeta();
        String sharpeRatio=staticalVariableVO.getSharpeRatio();
        String alpha=staticalVariableVO.getAlpha();
        StrategyEvaluateVO strategyEvaluateVO=strategyGraph.getStrategyEvaluate(strategyYearYield,maxWithdraw,beta,sharpeRatio,alpha);

        //结果输出
        StrategyVO strategyVO=new StrategyVO();
        strategyVO.setStaticalVariableVO(staticalVariableVO);
        strategyVO.setStrategyGraphVO(strategyGraphVO);
        strategyVO.setStrategyEvaluateVO(strategyEvaluateVO);

        return strategyVO;
    }

    /**
     * 获取策略超额收益率与不同形成期/持有期的关系图以及策略胜率与不同形成期/持有期的关系图的数据
     * @param inputStrategyVO
     * @param type type可以为0或1，type=0时，代表用户选择的是形成期，type=1时，代表用户选择的是持有期
     * @return
     * @throws Exception
     */
    @Override
    public RelationGraphVO getRelationGraphData(InputStrategyVO inputStrategyVO, int type) throws Exception {
        RelationGraph relationGraph=new RelationGraph();
        RelationGraphVO relationGraphVO=relationGraph.getRelationGraphData(inputStrategyVO,type);
        if((relationGraph==null)||(relationGraphVO.getPeriod().size()==0)){
            return null;
        }
        return relationGraphVO;
    }

    /**
     * 获取策略收益率分布直方图的数据
     * @param inputStrategyVO
     * @return
     * @throws Exception
     */
    @Override
    public YieldRateDistributionVO getYieldRateDistribution(InputStrategyVO inputStrategyVO) throws Exception {
/*
        StrategyGraphVO strategyGraphVO=new StrategyGraphVO();
        if(inputStrategyVO==staticInputStrategyVO){
            strategyGraphVO=staticStrategyGraphVO;
        }
*/
        YieldRateDistribution yieldRateDistribution=new YieldRateDistribution();
        YieldRateDistributionVO yieldRateDistributionVO=yieldRateDistribution.getYieldRateDistribution(inputStrategyVO);
        if((yieldRateDistributionVO==null)&&(yieldRateDistributionVO.getYieldRate().size()==0)){
            return null;
        }
        return yieldRateDistributionVO;
    }
}
