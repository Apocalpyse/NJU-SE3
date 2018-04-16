package bl.strategyBl;

import PO.StrategyStockPO;
import VO.InputStrategyVO;
import VO.StrategyType;
import VO.YieldRateDistributionVO;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/10.
 */
public class YieldRateDistribution {
    /**
     * 获取策略收益率分布直方图的数据
     * @param inputStrategyVO
     * @return
     * @throws Exception
     */
    public YieldRateDistributionVO getYieldRateDistribution(InputStrategyVO inputStrategyVO) throws Exception {
        //先获取数据
        GetStockInfo getStockInfo=new GetStockInfo();
        ArrayList<StrategyStockPO> strategyStockPOS=getStockInfo.getStrategyStockInfo(inputStrategyVO);
/*
        boolean isPlate=false;
        String start=inputStrategyVO.getStart();
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
        }

        //基准的每个持有期的收益率
        ArrayList<Double> benchmarkYield=new ArrayList<>();
        ArrayList<StrategyStockPO> benchmarkStocks=new ArrayList<StrategyStockPO>();
        if(isPlate){
            StrategyStockPO benchmarkStock=getStockInfo.getBenchmarkStockInfo(start,end,code);
            benchmarkStocks.add(benchmarkStock);
            Benchmark benchmark=new Benchmark();
            benchmarkYield=benchmark.getBenchmarkOneRelationData(benchmarkStocks,Integer.parseInt(inputStrategyVO.getFormationPeriod()),
                    Integer.parseInt(inputStrategyVO.getHoldingPeriod()),0);
        }else {
            Benchmark benchmark=new Benchmark();
            benchmarkYield=benchmark.getBenchmarkOneRelationData(strategyStockPOS,Integer.parseInt(inputStrategyVO.getFormationPeriod()),
                    Integer.parseInt(inputStrategyVO.getHoldingPeriod()),0);
        }
*/

        ArrayList<Double> strategyYield=new ArrayList<>();
        if(inputStrategyVO.getType()== StrategyType.MOMENT){
            MomentStrategy momentStrategy=new MomentStrategy();
            strategyYield=momentStrategy.getOneRelationData(strategyStockPOS, Integer.parseInt(inputStrategyVO.getFormationPeriod()),
                    Integer.parseInt(inputStrategyVO.getHoldingPeriod()),0);
        }else if(inputStrategyVO.getType()== StrategyType.AVERAGE){
            AverageStrategy averageStrategy=new AverageStrategy();
            strategyYield=averageStrategy.getOneRelationData(strategyStockPOS, Integer.parseInt(inputStrategyVO.getFormationPeriod()),
                    Integer.parseInt(inputStrategyVO.getHoldingPeriod()),0,strategyStockPOS.size()/5);

        }else if(inputStrategyVO.getType()==StrategyType.MA){
            MAStrategy maStrategy=new MAStrategy();
            strategyYield=maStrategy.getOneRelationData(strategyStockPOS, Integer.parseInt(inputStrategyVO.getFormationPeriod()),
                    Integer.parseInt(inputStrategyVO.getHoldingPeriod()),0);
        }

        if(strategyYield.size()==0){
            YieldRateDistributionVO res=new YieldRateDistributionVO();
            return res;
        }

        int positiveYieldNum=0;
        double max=strategyYield.get(0);
        double min=strategyYield.get(0);
        if(strategyYield.get(0)>=0){
            positiveYieldNum++;
        }
        for (int i = 1; i < strategyYield.size(); i++) {
            double temp=strategyYield.get(i);
            if(max<temp){
                max=temp;
            }
            if(min>temp){
                min=temp;
            }
            if(temp>=0){
                positiveYieldNum++;
            }
        }
        int negativeYieldNum=strategyYield.size()-positiveYieldNum;
        double winRate=(((double) positiveYieldNum)/((double) strategyYield.size()))*100.0;

        double maxAbsoluteValue=0.00;//是百分比形式的最大绝对值
        if(max+min>0){//如果是正收益率最大时，设置最大
            maxAbsoluteValue=max*100;
        }else {
            maxAbsoluteValue=-min*100;
        }

        int intervalRange=((int)((maxAbsoluteValue-1)/10))+1;//一个区间的收益率范围
        int intevalNum=((int)((maxAbsoluteValue+1)/intervalRange))+1;//区间数

        ArrayList<String> yieldRateStr=new ArrayList<>();//从最小到最大排列，如 4%, 8%，10%
        int[] positiveFrequency=new int[intevalNum];
        int[] negativeFrequency=new int[intevalNum];

        for (int i = 0; i < intevalNum; i++) {
            yieldRateStr.add(String.valueOf((i+1)*intervalRange));
            positiveFrequency[i]=0;
            negativeFrequency[i]=0;
            for (int j = 0; j < strategyYield.size(); j++) {
                double tempYield=strategyYield.get(j)*100;
                if((tempYield>=(i*intervalRange))&&(tempYield<(i+1)*intervalRange)){//要小边界，不要大边界
                    positiveFrequency[i]++;
                }
                if((tempYield>=(-((i+1)*intervalRange)))&&(tempYield<(-i*intervalRange))){
                    negativeFrequency[i]++;
                }
            }

        }
        //以下防止漏掉最大
        if((max*100)==(double)(intervalRange*intevalNum)){
            positiveFrequency[intevalNum-1]++;
        }

        ArrayList<String> positiveFrequencyStr=new ArrayList<>();//正收益频数
        ArrayList<String> negativeFrequencyStr=new ArrayList<>();//负收益频数
        for (int i = 0; i < intevalNum; i++){
            positiveFrequencyStr.add(String.valueOf(positiveFrequency[i]));
            negativeFrequencyStr.add(String.valueOf(negativeFrequency[i]));
        }

        YieldRateDistributionVO yieldRateDistributionVO=new YieldRateDistributionVO();
        yieldRateDistributionVO.setPositiveYieldNum(String.valueOf(positiveYieldNum));
        yieldRateDistributionVO.setNegativeYieldNum(String.valueOf(negativeYieldNum));
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        yieldRateDistributionVO.setWinRate(String.valueOf(decimalFormat.format(winRate))+"%");
        yieldRateDistributionVO.setYieldRate(yieldRateStr);
        yieldRateDistributionVO.setPositiveFrequency(positiveFrequencyStr);
        yieldRateDistributionVO.setNegativeFrequency(negativeFrequencyStr);

        return yieldRateDistributionVO;
    }
}
