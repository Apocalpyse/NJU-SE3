package bl.strategyBl;

import PO.strategyPO.StrategyStockPO;
import VO.strategyVO.InputStrategyVO;
import VO.strategyVO.RelationGraphVO;
import VO.strategyVO.StrategyType;
import bl.calculateBl.Calculate;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class RelationGraph {
    MomentStrategy momentStrategy;
    AverageStrategy averageStrategy;
    MAStrategy maStrategy;
    Benchmark benchmark;

    public RelationGraph(){
        momentStrategy=new MomentStrategy();
        averageStrategy=new AverageStrategy();
        maStrategy=new MAStrategy();
        benchmark=new Benchmark();
    }

    /**
     * 获取策略超额收益率与不同形成期/持有期的关系图以及策略胜率与不同形成期/持有期的关系图的数据
     * @param inputStrategyVO
     * @param type type可以为0或1，type=0时，代表用户选择的是形成期，type=1时，代表用户选择的是持有期
     * @return
     */
    public RelationGraphVO getRelationGraphData(InputStrategyVO inputStrategyVO, int type){
        ArrayList<String> period=new ArrayList<>();//相对强弱计算周期
        ArrayList<String> overYield=new ArrayList<>();//超额收益
        ArrayList<String> winRate=new ArrayList<>();//胜率

//        if(type==0){//若用户选择形成期，需要计算用户持有期的间隔是多少
//            Calculate calculate=new Calculate();
//            int aboutTradeDays=calculate.getIntervalTradeDayNums(inputStrategyVO.getStart(),inputStrategyVO.getEnd());//大致交易天数，据此确定横坐标
//            if(aboutTradeDays>500){//如果交易日大于500才重新确定持有期，否则，就直接按照
//
//            }
//        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");

        if(type==0){//若用户选择形成期，需要计算用户不同持有期的两个数据
            //先获取数据
            GetStockInfo getStockInfo=new GetStockInfo();
            ArrayList<StrategyStockPO> strategyStockPOS=getStockInfo.getStrategyStockInfo(inputStrategyVO);//此时没有获取多余的数据

            boolean isPlate=false;
            Calculate calculate=new Calculate();
            String start=calculate.getBeforeSomeTradeDate(inputStrategyVO.getStart(),Integer.parseInt(inputStrategyVO.getFormationPeriod()));
            String end=inputStrategyVO.getEnd();
            String code="";
            switch (inputStrategyVO.getStockPoolBl()){
                case MAINPLATE:
                    code="000300";
                    isPlate=true;
                    break;
                case SMALLMIDDLEPLATE:
                    code="399005";
                    isPlate=true;
                    break;
                case STARTUPPLATE:
                    code="399006";
                    isPlate=true;
                    break;
                case SHANGZHENG:
                    code="000001";
                    isPlate=true;
                    break;
                case SHENZHENG:
                    code="399001";
                    isPlate=true;
                    break;
                case HUSHEN300:
                    code="000300";
                    isPlate=true;
                    break;
            }
            ArrayList<StrategyStockPO> benchmarkStocks=new ArrayList<StrategyStockPO>();
            if(isPlate){
                StrategyStockPO benchmarkStock=getStockInfo.getBenchmarkStockInfo(start,end,code);
                benchmarkStocks.add(benchmarkStock);
            }

//            Calculate calculate=new Calculate();
            int aboutTradeDays=calculate.getIntervalTradeDayNums(inputStrategyVO.getStart(),inputStrategyVO.getEnd());//大致交易天数
            for (int i = 2; (i <=60)&&(i<=aboutTradeDays) ; i=i+2) {//i是不同的持有期
                ArrayList<Double> strategyData=getOneRelationData(inputStrategyVO.getType(),strategyStockPOS,Integer.parseInt(inputStrategyVO.getFormationPeriod()),i,0);
                ArrayList<Double> benchmarkData=new ArrayList<>();
                if(isPlate){
                    if(inputStrategyVO.getType()!=StrategyType.MA){
                        benchmarkData=benchmark.getBenchmarkOneRelationData
                                (benchmarkStocks,Integer.parseInt(inputStrategyVO.getFormationPeriod()),i,0);
                    }else {
                        benchmarkData=benchmark.getBenchmarkOneRelationData(benchmarkStocks,1,i,0);
                    }

                }else {
                    if(inputStrategyVO.getType()!=StrategyType.MA){
                        benchmarkData=benchmark.getBenchmarkOneRelationData
                                (strategyStockPOS,Integer.parseInt(inputStrategyVO.getFormationPeriod()),i,0);
                    }else {
                        benchmarkData=benchmark.getBenchmarkOneRelationData(strategyStockPOS,1,i,0);
                    }

                }
                //以下进行比较
                int win=0;
                double strategyYield=1.00;
                double benchmarkYield=1.00;
                for (int j = 0; j < Math.min(strategyData.size(),benchmarkData.size()); j++) {
                    if(strategyData.get(j)>=benchmarkData.get(j)){
                        win++;
                    }
                    strategyYield=strategyYield*(1+strategyData.get(j));
                    benchmarkYield=benchmarkYield*(1+benchmarkData.get(j));
                }
                double winRateDou=((double) (win*100))/((double)strategyData.size());//已换算成百分比形式
                double overYieldDou=(strategyYield-benchmarkYield)*100;//超额收益率,已换算成百分比形式

                period.add(String.valueOf(i));
                winRate.add(String.valueOf(decimalFormat.format(winRateDou)+"%"));
                overYield.add(String.valueOf(decimalFormat.format(overYieldDou)+"%"));
            }
        }else if(type==1){
            //先获取数据
            inputStrategyVO.setFormationPeriod("60");
            GetStockInfo getStockInfo=new GetStockInfo();
            ArrayList<StrategyStockPO> strategyStockPOS=getStockInfo.getStrategyStockInfo(inputStrategyVO);//此时有多余的数据

            boolean isPlate=false;
            Calculate calculate=new Calculate();
            String start=calculate.getBeforeSomeTradeDate(inputStrategyVO.getStart(),60);
            String end=inputStrategyVO.getEnd();
            String code="";
            switch (inputStrategyVO.getStockPoolBl()){
                case MAINPLATE:
                    code="000300";
                    isPlate=true;
                    break;
                case SMALLMIDDLEPLATE:
                    code="399005";
                    isPlate=true;
                    break;
                case STARTUPPLATE:
                    code="399006";
                    isPlate=true;
                    break;
                case SHANGZHENG:
                    code="000001";
                    isPlate=true;
                    break;
                case SHENZHENG:
                    code="399001";
                    isPlate=true;
                    break;
                case HUSHEN300:
                    code="000300";
                    isPlate=true;
                    break;
            }
            ArrayList<StrategyStockPO> benchmarkStocks=new ArrayList<StrategyStockPO>();
            if(isPlate){
                StrategyStockPO benchmarkStock=getStockInfo.getBenchmarkStockInfo(start,end,code);
                benchmarkStocks.add(benchmarkStock);
            }

//            Calculate calculate=new Calculate();
            int aboutTradeDays=calculate.getIntervalTradeDayNums(inputStrategyVO.getStart(),inputStrategyVO.getEnd());//大致交易天数
            for (int i = 2; (i <=60)&&(i<=aboutTradeDays) ; i=i+2) {//形成期在变化
                int valuelessDays=60-i;
//                int valuelessDays=strategyStockPOS.get(0).getDate().size()-;
                ArrayList<Double> strategyData=getOneRelationData(inputStrategyVO.getType(),strategyStockPOS,i,Integer.parseInt(inputStrategyVO.getHoldingPeriod()),valuelessDays);
                ArrayList<Double> benchmarkData=new ArrayList<>();
                if(isPlate){
                    if(inputStrategyVO.getType()!=StrategyType.MA){
                        benchmarkData=benchmark.getBenchmarkOneRelationData
                                (benchmarkStocks,i,Integer.parseInt(inputStrategyVO.getHoldingPeriod()),valuelessDays);
                    }else {
                        benchmarkData=benchmark.getBenchmarkOneRelationData
                                (benchmarkStocks,i,1,valuelessDays);
                    }

                }else {
                    if(inputStrategyVO.getType()!=StrategyType.MA){
                        benchmarkData=benchmark.getBenchmarkOneRelationData
                                (strategyStockPOS,i,Integer.parseInt(inputStrategyVO.getHoldingPeriod()),valuelessDays);
                    }else {
                        benchmarkData=benchmark.getBenchmarkOneRelationData
                                (strategyStockPOS,i,1,valuelessDays);
                    }

                }
                //以下进行比较
                int win=0;
                double strategyYield=1.00;
                double benchmarkYield=1.00;
                for (int j = 0; j < Math.min(strategyData.size(),benchmarkData.size()); j++) {
                    if(strategyData.get(j)>=benchmarkData.get(j)){
                        win++;
                    }
                    strategyYield=strategyYield*(1+strategyData.get(j));
                    benchmarkYield=benchmarkYield*(1+benchmarkData.get(j));
                }
                double winRateDou=((double) (win*100))/((double)strategyData.size());//已换算成百分比形式
                double overYieldDou=(strategyYield-benchmarkYield)*100;//超额收益率,已换算成百分比形式

                period.add(String.valueOf(i));
                winRate.add(String.valueOf(decimalFormat.format(winRateDou)+"%"));
                overYield.add(String.valueOf(decimalFormat.format(overYieldDou)+"%"));
            }
        }

        RelationGraphVO relationGraphVO=new RelationGraphVO();
        relationGraphVO.setPeriod(period);
        relationGraphVO.setWinRate(winRate);
        relationGraphVO.setOverYield(overYield);

        return relationGraphVO;
    }

    /**
     * 获取关系图的其中一个数据
     * @param strategyStockPOS
     * @param formationPeriod 形成期
     * @param holdingPeriod 持有期
     * @param valueless 无用的形成期数据有几个
     * @return 每个持有期末与其开始时比较的收益率
     */
    public ArrayList<Double> getOneRelationData(StrategyType type,ArrayList<StrategyStockPO> strategyStockPOS, int formationPeriod, int holdingPeriod, int valueless){
        if(type==StrategyType.MOMENT){
            return momentStrategy.getOneRelationData(strategyStockPOS,formationPeriod,holdingPeriod,valueless);
        }else if(type==StrategyType.AVERAGE){
            return averageStrategy.getOneRelationData(strategyStockPOS,formationPeriod,holdingPeriod,valueless,strategyStockPOS.size()/5);
        }else if(type== StrategyType.MA){
            return maStrategy.getOneRelationData(strategyStockPOS,formationPeriod,holdingPeriod,valueless);
        }
        return null;
    }

}
