package bl.strategyBl;

import PO.StrategyStockPO;
import VO.InputStrategyVO;
import VO.StrategyEvaluateVO;
import VO.StrategyGraphVO;
import VO.StrategyType;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/12.
 */
public class StrategyGraph {
    /**
     * 获取策略的策略和基准的累计收益率比较图的数据
     * @param inputStrategyVO
     * @return
     * @throws Exception
     */
    public StrategyGraphVO getGraphData(InputStrategyVO inputStrategyVO) throws Exception {
        GetStockInfo getStockInfo=new GetStockInfo();
        ArrayList<StrategyStockPO> strategyStockPOS = getStockInfo.getStrategyStockInfo(inputStrategyVO);

        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            StrategyGraphVO res=new StrategyGraphVO();
            return res;
        }

        int formationPeriod=Integer.parseInt(inputStrategyVO.getFormationPeriod());
        int holdingPeriod=Integer.parseInt(inputStrategyVO.getHoldingPeriod());

        Benchmark benchmark=new Benchmark();
        ArrayList<String> benchmarkData=new ArrayList<String>();
        switch (inputStrategyVO.getStockPoolBl()){
            case MAINPLATE:
            case SMALLMIDDLEPLATE:
            case STARTUPPLATE:
                benchmarkData=benchmark.getPlateBenchmarkData(inputStrategyVO);
                break;
            case ALL:
            case SELECTPLATE:
                benchmarkData=benchmark.getSelfSelectBenchmarkData(strategyStockPOS,formationPeriod);
                break;
        }

        if(inputStrategyVO.getType()== StrategyType.MOMENT){//如果是动量策略
            //实现方法：
            // 方法一，在总体获得数据的类中
            // 1.先根据股票池、开始日期-形成期、结束日期获得股票信息（包括股票代码、股票名称、每日的赋权收盘指数）
            //方法二，在benchmark累类中：
            //1.根据数据计算基准累计收益率的数据(注意要排除形成期的股票信息)
            //方法三：动量策略
            // 1.根据获得的数据计算每种股票在各段时间的收益率
            // 2.分时间进行排序，选择用户每段时间买的前20%的股票
            // 3.计算用户的每天的策略累计收益率

            MomentStrategy momentStrategy=new MomentStrategy();
            ArrayList<String> momentData=momentStrategy.getMomentData(formationPeriod,holdingPeriod,strategyStockPOS);
            ArrayList<Integer> maxWithdraw=benchmark.getMaxWithdraw(momentData);

            StrategyGraphVO strategyGraphVO=new StrategyGraphVO();

            //then set date
            ArrayList<String> resultDate=strategyStockPOS.get(0).getDate();
            for (int i = 0; i < formationPeriod; i++) {
                resultDate.remove(0);
            }
            strategyGraphVO.setDate(resultDate);

            strategyGraphVO.setBenchmarkYield(benchmarkData);
            strategyGraphVO.setStrategyYield(momentData);
            if(!((maxWithdraw.get(0)==0)&&(maxWithdraw.get(1)==0))){//当累计收益率不是持续上升时（即当有最大回撤时），设置最大回撤
                strategyGraphVO.setMaxWithdrawStart(strategyStockPOS.get(0).getDate().get(maxWithdraw.get(0)));
                strategyGraphVO.setMaxWithdrawEnd(strategyStockPOS.get(0).getDate().get(maxWithdraw.get(1)));
            }

            return strategyGraphVO;

        }else if(inputStrategyVO.getType()==StrategyType.AVERAGE){//如果是均值回归策略
            AverageStrategy averageStrategy=new AverageStrategy();
            int holdStockNum=strategyStockPOS.size()/5;//持股数量，默认为股票数的20%
            ArrayList<String> averageData=averageStrategy.getAverageData(formationPeriod,holdingPeriod,strategyStockPOS,holdStockNum);
            ArrayList<Integer> maxWithdraw=benchmark.getMaxWithdraw(averageData);

            StrategyGraphVO strategyGraphVO=new StrategyGraphVO();

            //then set date
            ArrayList<String> resultDate=strategyStockPOS.get(0).getDate();
            for (int i = 0; i < formationPeriod; i++) {
                resultDate.remove(0);
            }
            strategyGraphVO.setDate(resultDate);

            strategyGraphVO.setBenchmarkYield(benchmarkData);
            strategyGraphVO.setStrategyYield(averageData);
            if(!((maxWithdraw.get(0)==0)&&(maxWithdraw.get(1)==0))){//当累计收益率不是持续上升时（即当有最大回撤时），设置最大回撤
                strategyGraphVO.setMaxWithdrawStart(strategyStockPOS.get(0).getDate().get(maxWithdraw.get(0)));
                strategyGraphVO.setMaxWithdrawEnd(strategyStockPOS.get(0).getDate().get(maxWithdraw.get(1)));
            }

            return strategyGraphVO;

        }else if(inputStrategyVO.getType()==StrategyType.MA){//如果是MA择时策略
            MAStrategy maStrategy=new MAStrategy();
            ArrayList<String> MAData=maStrategy.getMAData(formationPeriod,holdingPeriod,strategyStockPOS);
            //***
            ArrayList<Integer> maxWithdraw=benchmark.getMaxWithdraw(MAData);
            StrategyGraphVO strategyGraphVO=new StrategyGraphVO();

            //then set date
            ArrayList<String> resultDate=strategyStockPOS.get(0).getDate();
            for (int i = 0; i < formationPeriod; i++) {
                resultDate.remove(0);
            }
            strategyGraphVO.setDate(resultDate);

            strategyGraphVO.setBenchmarkYield(benchmarkData);
            strategyGraphVO.setStrategyYield(MAData);
            if(!((maxWithdraw.get(0)==0)&&(maxWithdraw.get(1)==0))){//当累计收益率不是持续上升时（即当有最大回撤时），设置最大回撤
                strategyGraphVO.setMaxWithdrawStart(strategyStockPOS.get(0).getDate().get(maxWithdraw.get(0)));
                strategyGraphVO.setMaxWithdrawEnd(strategyStockPOS.get(0).getDate().get(maxWithdraw.get(1)));
            }

            return strategyGraphVO;
            ///
        }

        return null;
    }

    /**
     * 获取策略评估雷达图的数据
     * @param strategyYearYieldStr -8.57%
     * @param maxWithdrawStr 29.18%
     * @param betaStr 0.22
     * @param sharpeRatioStr -0.37
     * @param alphaStr -8.48%
     * @return
     */
    public StrategyEvaluateVO getStrategyEvaluate(String strategyYearYieldStr,String maxWithdrawStr,
                                                  String betaStr,String sharpeRatioStr,String alphaStr){
        double strategyYearYield=Double.parseDouble(strategyYearYieldStr.substring(0,strategyYearYieldStr.length()-1));
        double maxWithdraw=Double.parseDouble(maxWithdrawStr.substring(0,maxWithdrawStr.length()-1));
        double beta=Double.parseDouble(betaStr);
        double sharpeRatio=Double.parseDouble(sharpeRatioStr);
        double alpha=Double.parseDouble(alphaStr.substring(0,alphaStr.length()-1));

        int yield=100;//收益分数
        if(strategyYearYield>=50.00){
            yield=100;
        }else if(strategyYearYield<=-50.00){
            yield=0;
        }else if(strategyYearYield>=0.00){
            yield=60+((int)(strategyYearYield*4/5+0.5));
        }else {
            yield=60-((int)(-strategyYearYield*6/5+0.5));
        }

        int resistRisk=100;//抗风险分数
        double maxWithdrawScore=100.00;
        if(maxWithdraw<=20){
            maxWithdrawScore=100;
        }else if(maxWithdraw>=100){
            maxWithdrawScore=0;
        }else {
            maxWithdrawScore=(100-maxWithdraw)*5/4;
        }
        resistRisk=(int)(maxWithdrawScore+0.5);

        int stable=100;//稳定性分数
        if(beta<0){
            beta=-beta;
        }
        if(beta<=0.5){
            stable=100;
        }else if(beta>=3) {
            stable=0;
        }else {
            stable=(int)((3-beta)*40+0.5);
        }

        int riskYield=100;//风险收益分数
        if(sharpeRatio<=0.2){
            riskYield=100;
        }else if(sharpeRatio>=5){
            riskYield=0;
        }else if(sharpeRatio<=1){
            riskYield=70+(int)((1-sharpeRatio)/0.8*30+0.5);
        }else {
            riskYield=(int)((5-sharpeRatio)/4*70+0.5);
        }

        int yieldSpace=100;//收益空间分数
        if(alpha>=40){
            yieldSpace=100;
        }else if(alpha<=-40){
            yieldSpace=0;
        }else if(alpha>=0){
            yieldSpace=80+(int)(alpha/2+0.5);
        }else {
            yieldSpace=80-(int)(-alpha*2+0.5);
        }

        int generalScore=(int)((yield+resistRisk+stable+riskYield+yieldSpace)/5+0.5);//总评

        /**
         * private int generalScore;//总评
         private int yield;//收益分数
         private int resistRisk;//抗风险分数
         private int stable;//稳定性分数
         private int riskYield;//风险收益分数
         private int yieldSpace;//收益空间分数
         1.收益分数： 根据策略的年化收益打分。 100分表明策略年化收益大约是50%， 60
         分表明策略年化收益在0%, 0分是-50%。
         2.抗风险分数：根据策略的最大回撤率和赢率计算得出(最大回撤小于20%回撤部分为100分，
         为100%即以下为0分)。
         80分以上的策略表明抗风险的能力强， 60分以下表明抗风险能力一般。
         3.稳定性分数：根据贝塔系数得到，若beta=1则分数为80，beta=0.5为满分,beta=3时为0分
         4.风险收益。夏普比率,sharpe<=0.2时，满分；=1时，70分；=5时，0分。
         5.收益空间。阿尔法系数，看股票是否被低估，=0时分数为80,>=40为100分，<=-40为0分
         */
        StrategyEvaluateVO strategyEvaluateVO=new StrategyEvaluateVO(generalScore,yield,resistRisk,stable,riskYield,yieldSpace);
        return strategyEvaluateVO;
    }

}
