package test.bl.strategyBl; 

import VO.*;
import bl.strategyBl.StrategyController;
import blSer.StockPoolBl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

/** 
* StrategyController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 19, 2017</pre> 
* @version 1.0 
*/ 
public class StrategyControllerTest {

    StrategyController strategyController;

    public StrategyControllerTest(){
        ClientRunner cr=new ClientRunner();
        strategyController=new StrategyController();
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: getGraphData(InputStrategyVO inputStrategyVO)
     *
     */
    @Test
    public void testGetStrategy() throws Exception {
        ArrayList<String> inputStocks=new ArrayList<>();
        InputStrategyVO inputStrategyVO=new InputStrategyVO(StrategyType.MA,"5",
                "10","4/1/13","5/10/14", StockPoolBl.MAINPLATE,inputStocks);
//        ArrayList<String> inputStocks=new ArrayList<>();
//        InputStrategyVO inputStrategyVO=new InputStrategyVO(StrategyType.MOMENT,"5",
//                "10","4/4/13","4/19/14", StockPoolBl.MAINPLATE,inputStocks);
        StrategyVO strategyVO=strategyController.getStrategy(inputStrategyVO);
        System.out.println("first is strategyVO");
        StrategyGraphVO strategyGraphVO=strategyVO.getStrategyGraphVO();
        System.out.println(strategyGraphVO.getDate().size());
        System.out.println(strategyGraphVO.getStrategyYield().size());
        System.out.println(strategyGraphVO.getBenchmarkYield().size());
        System.out.println("first date is: "+strategyGraphVO.getDate().get(0));
        System.out.println("last date is: "+strategyGraphVO.getDate().get(strategyGraphVO.getDate().size()-1));
        System.out.println(strategyGraphVO.getMaxWithdrawStart());
        System.out.println(strategyGraphVO.getMaxWithdrawEnd());
        for (int i = 0; i < strategyGraphVO.getDate().size(); i++) {
            System.out.print(strategyGraphVO.getDate().get(i)+" ");
        }

        System.out.println();

        for (int i = 0; i < strategyGraphVO.getStrategyYield().size(); i++) {
            System.out.print(strategyGraphVO.getStrategyYield().get(i)+" ");
        }

        System.out.println();

        for (int i = 0; i < strategyGraphVO.getBenchmarkYield().size(); i++) {
            System.out.print(strategyGraphVO.getBenchmarkYield().get(i)+" ");
        }
        System.out.println();
        System.out.println("next is staticalVariableVO:");
        StaticalVariableVO staticalVariableVO=strategyVO.getStaticalVariableVO();
        System.out.println(staticalVariableVO.getYearYieldRate());
        System.out.println(staticalVariableVO.getBenchmarkYearYieldRate());
        System.out.println(staticalVariableVO.getAlpha());
        System.out.println(staticalVariableVO.getBeta());
        System.out.println(staticalVariableVO.getSharpeRatio());
        System.out.println(staticalVariableVO.getMaxWithdraw());


/**
 * private int generalScore;//总评
 private int yield;//收益分数
 private int resistRisk;//抗风险分数
 private int stable;//稳定性分数
 private int riskYield;//风险收益分数
 private int yieldSpace;//收益空间分数
 */
        StrategyEvaluateVO strategyEvaluateVO=strategyVO.getStrategyEvaluateVO();
        System.out.println("雷达图数据");
        System.out.println(strategyEvaluateVO.getGeneralScore());
        System.out.println(strategyEvaluateVO.getYield());
        System.out.println(strategyEvaluateVO.getResistRisk());
        System.out.println(strategyEvaluateVO.getStable());
        System.out.println(strategyEvaluateVO.getResistRisk());
        System.out.println(strategyEvaluateVO.getYieldSpace());
//TODO: Test goes here...
    }

    /**
     *
     * Method: getRelationGraphData(InputStrategyVO inputStrategyVO, int type)
     *
     */
    @Test
    public void testGetRelationGraphData() throws Exception {
//TODO: Test goes here...
        ArrayList<String> inputStocks=new ArrayList<>();
        InputStrategyVO inputStrategyVO=new InputStrategyVO(StrategyType.MA,"5",
                "9","4/1/13","4/19/14", StockPoolBl.MAINPLATE,inputStocks);
//        ArrayList<String> inputStocks=new ArrayList<>();
//        InputStrategyVO inputStrategyVO=new InputStrategyVO(StrategyType.AVERAGE,"5",
//                "10","4/4/13","4/19/14", StockPoolBl.ALL,inputStocks);
        RelationGraphVO relationGraphVO = strategyController.getRelationGraphData(inputStrategyVO,0);
        System.out.print(relationGraphVO.getPeriod().size()+" ");
        System.out.print(relationGraphVO.getOverYield().size()+" ");
        System.out.println(relationGraphVO.getWinRate().size());

        System.out.println("testGetRelationGraphData");
        ArrayList<String> periods=relationGraphVO.getPeriod();
        for (int i = 0; i < periods.size(); i++) {
            System.out.print(periods.get(i)+" ");
        }
        System.out.println();

        ArrayList<String> overYields=relationGraphVO.getOverYield();
        for (int i = 0; i < overYields.size(); i++) {
            System.out.print(overYields.get(i)+" ");
        }
        System.out.println();

        ArrayList<String> winRates=relationGraphVO.getWinRate();
        for (int i = 0; i < winRates.size(); i++) {
            System.out.print(winRates.get(i)+" ");
        }
        System.out.println();
    }

    /**
     *
     * Method: getYieldRateDistribution(InputStrategyVO inputStrategyVO)
     *
     */
    @Test
    public void testGetYieldRateDistribution() throws Exception {
//TODO: Test goes here...
        ArrayList<String> inputStocks=new ArrayList<>();
        InputStrategyVO inputStrategyVO=new InputStrategyVO(StrategyType.MA,"5",
                "19","4/1/13","4/19/14", StockPoolBl.STARTUPPLATE,inputStocks);
        YieldRateDistributionVO yieldRateDistributionVO=strategyController.getYieldRateDistribution(inputStrategyVO);
        /**
         * private String positiveYieldNum;//正收益周期数
         private String negativeYieldNum;//负收益周期数
         private String winRate;//赢率
         private ArrayList<String> yieldRate;//从最小到最大排列，如 1%,5%，6%
         private ArrayList<String> positiveFrequency;//正收益频数
         private ArrayList<String> negativeFrequency;//负收益频数
         */
        System.out.println(yieldRateDistributionVO.getPositiveYieldNum());
        System.out.println(yieldRateDistributionVO.getNegativeYieldNum());
        System.out.println(yieldRateDistributionVO.getWinRate());
        ArrayList<String> yieldRate=yieldRateDistributionVO.getYieldRate();
        System.out.println(yieldRate.size());
        for (int i = 0; i < yieldRateDistributionVO.getYieldRate().size(); i++) {
            System.out.print(yieldRate.get(i)+" ");
        }
        System.out.println();
        ArrayList<String> positiveFrequency=yieldRateDistributionVO.getPositiveFrequency();
        for (int i = 0; i < positiveFrequency.size(); i++) {
            System.out.print(positiveFrequency.get(i)+" ");
        }
        System.out.println();
        ArrayList<String> negativeFrequency=yieldRateDistributionVO.getNegativeFrequency();
        for (int i = 0; i < negativeFrequency.size(); i++) {
            System.out.print(negativeFrequency.get(i)+" ");
        }
        System.out.println();
    }



} 
