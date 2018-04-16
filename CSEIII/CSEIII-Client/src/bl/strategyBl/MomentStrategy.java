package bl.strategyBl;


import PO.StockPO;
import PO.StrategyStockPO;
import VO.InputUserVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.QuickSort;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/31.
 */
public class MomentStrategy {
    /**
     * 获取动量策略数据
     * @param strategyStockPOS
     * @return
     */
    public ArrayList<String> getMomentData(int formationPeriod, int holdingPeriod,ArrayList<StrategyStockPO> strategyStockPOS){
        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            ArrayList<String> res=new ArrayList<>();
            return res;
        }

        //方法三：动量策略
        // 1.根据获得的数据计算每种股票在各段时间的收益率
        // 2.分时间进行排序，选择用户每段时间买的前20%的股票
        // 3.计算用户的每天的策略累计收益率
        ArrayList<String> accumulativeYield=new ArrayList<String>();//累计收益率
        accumulativeYield.add("0.00%");//先添加第一天的动量策略的收益率，一定是0%

        int size=strategyStockPOS.get(0).getAdjClose().size();
        for (int i = 1; i < strategyStockPOS.size(); i++) {
            size=Math.min(size,strategyStockPOS.get(i).getAdjClose().size());
        }
        int stage=(size-formationPeriod-1)/holdingPeriod+1;//共分成stage个阶段计算

        double[] stageYieldS=new double[strategyStockPOS.size()];
        int requiredStockNum=strategyStockPOS.size()/5;//各个阶段需要的股票数量
        for (int i = 0; i < stage; i++) {
            for (int j = 0; j < strategyStockPOS.size(); j++) {
                ArrayList<String> adjCloseS=strategyStockPOS.get(j).getAdjClose();
                String startStr=adjCloseS.get(i*holdingPeriod);
                String endStr=adjCloseS.get(i*holdingPeriod+formationPeriod-1);
                double start=Double.parseDouble(startStr);
                double end=Double.parseDouble(endStr);
                double stageYield=((end-start)/start)*100;
                stageYieldS[j]=stageYield;
            }
            double[] tempStageYieldS=new double[stageYieldS.length];
            for (int j = 0; j < tempStageYieldS.length; j++) {
                tempStageYieldS[j]=stageYieldS[j];
            }
            QuickSort quickSort=new QuickSort();
            int[] location=quickSort.getSort(tempStageYieldS);
            int locationLength=location.length;
            //以下获取这一阶段挑选出的前20%的股票每日复权收盘价信息
            ArrayList<StrategyStockPO> requiredStageStock=new ArrayList<StrategyStockPO>();//其中，股票从收益率最大到最小排序
            for (int j = 0; j < requiredStockNum; j++) {
                requiredStageStock.add(strategyStockPOS.get(location[locationLength-1-j]));
            }

            //以下计算这一阶段投资的前20%股票每日的累计收益率
            Benchmark benchmark=new Benchmark();
            double[] stageYield= benchmark.getYield(requiredStageStock,i*holdingPeriod+formationPeriod,
                    Math.min((i+1)*holdingPeriod+formationPeriod, requiredStageStock.get(0).getAdjClose().size()-1));

            DecimalFormat decimalFormat=new DecimalFormat("0.00");
            //以下增加累计收益率
            if(accumulativeYield.size()==1){
                for (int j = 0; j <stageYield.length ; j++) {
                    accumulativeYield.add(decimalFormat.format(stageYield[j])+"%");
                }
            }else {//当其之前有买股票时
                String tempLast=accumulativeYield.get(accumulativeYield.size()-1);
                tempLast=tempLast.substring(0, tempLast.length()-1);
                double temp=Double.parseDouble(tempLast);
                double preMoney=temp/100.0+1.00;
                for (int j = 0; j <stageYield.length ; j++){
                    double thisYield=stageYield[j]*preMoney;
                    accumulativeYield.add(decimalFormat.format(thisYield)+"%");
                }
            }

        }

        return accumulativeYield;
    }

    /**
     * 获取关系图的其中一个数据
     * @param strategyStockPOS
     * @param formationPeriod 形成期
     * @param holdingPeriod 持有期
     * @param valueless 无用的形成期数据有几个
     * @return 每个持有期末与其开始时比较的收益率，注意结果为方便之后的数据处理，并没有*100，即并没有换算成百分比形式
     */
    public ArrayList<Double> getOneRelationData(ArrayList<StrategyStockPO> strategyStockPOS,int formationPeriod,int holdingPeriod,int valueless){
        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            ArrayList<Double> res=new ArrayList<>();
            return res;
        }

        ArrayList<Double> accumulativeYield=new ArrayList<Double>();//每个持有期末与其开始时比较的收益率，size=stage，即=持有期期数

        int size=strategyStockPOS.get(0).getAdjClose().size()-valueless;
        int stage=(size-formationPeriod-1)/holdingPeriod+1;//共分成stage个阶段计算

        double[] stageYieldS=new double[strategyStockPOS.size()];
        int requiredStockNum=strategyStockPOS.size()/5;//各个阶段需要的股票数量
        for (int i = 0; i < stage; i++) {
            for (int j = 0; j < strategyStockPOS.size(); j++) {
                ArrayList<String> adjCloseS=strategyStockPOS.get(j).getAdjClose();
                String startStr=adjCloseS.get(i*holdingPeriod+valueless);
                String endStr=adjCloseS.get(i*holdingPeriod+formationPeriod-1+valueless);
                double start=Double.parseDouble(startStr);
                double end=Double.parseDouble(endStr);
                double stageYield=((end-start)/start)*100;
                stageYieldS[j]=stageYield;
            }
            double[] tempStageYieldS=new double[stageYieldS.length];
            for (int j = 0; j < tempStageYieldS.length; j++) {
                tempStageYieldS[j]=stageYieldS[j];
            }
            QuickSort quickSort=new QuickSort();
            int[] location=quickSort.getSort(tempStageYieldS);
            int locationLength=location.length;

            //以下获取这一阶段挑选出的前20%的股票每日复权收盘价信息
            double holdYield=0.00;//初始化这一阶段投资的前20%股票在这一持有期的累计收益率
            StrategyStockPO requiredStageStock=new StrategyStockPO();//其中，股票从收益率最大到最小排序
            for (int j = 0; j < requiredStockNum; j++) {
                requiredStageStock=strategyStockPOS.get(location[locationLength-1-j]);
                String requireStartStr=requiredStageStock.getAdjClose().get(i*holdingPeriod+formationPeriod+valueless);
                double requireStart=Double.parseDouble(requireStartStr);;
                String requireEndStr=requiredStageStock.getAdjClose().
                        get(Math.min((i+1)*holdingPeriod+formationPeriod+valueless, requiredStageStock.getAdjClose().size()-1));
                double requireEnd=Double.parseDouble(requireEndStr);
                holdYield=holdYield+((requireEnd-requireStart)/requireStart);
            }
            holdYield=holdYield/requiredStockNum;//已计算出这一阶段投资的前20%股票在这一持有期的累计收益率
            accumulativeYield.add(holdYield);
        }
        return accumulativeYield;
    }


    //以下测试getMomentData方法
    /*
    public static void main(String [] args){
        ArrayList<String> adjClose1=new ArrayList<>();
        adjClose1.add("1");
        adjClose1.add("2");
        adjClose1.add("1.5");
        adjClose1.add("3.2");
        adjClose1.add("2.6");
        adjClose1.add("0.8");
        StrategyStockPO strategyStockPO1=new StrategyStockPO("","",null,adjClose1,"");

        ArrayList<String> adjClose2=new ArrayList<>();
        adjClose2.add("0.65");
        adjClose2.add("1.8");
        adjClose2.add("2.5");
        adjClose2.add("3.8");
        adjClose2.add("1.2");
        adjClose2.add("0.6");
        StrategyStockPO strategyStockPO2=new StrategyStockPO("","",null,adjClose2,"");

        ArrayList<String> adjClose3=new ArrayList<>();
        adjClose3.add("0.56");
        adjClose3.add("5");
        adjClose3.add("4");
        adjClose3.add("3.8");
        adjClose3.add("5.2");
        adjClose3.add("1.6");
        StrategyStockPO strategyStockPO3=new StrategyStockPO("","",null,adjClose3,"");

        ArrayList<String> adjClose4=new ArrayList<>();
        adjClose4.add("5");
        adjClose4.add("4.2");
        adjClose4.add("3.6");
        adjClose4.add("3.3");
        adjClose4.add("2");
        adjClose4.add("2.6");
        StrategyStockPO strategyStockPO4=new StrategyStockPO("","",null,adjClose4,"");

        ArrayList<String> adjClose5=new ArrayList<>();
        adjClose5.add("4.2");
        adjClose5.add("3.9");
        adjClose5.add("2");
        adjClose5.add("4.8");
        adjClose5.add("3.2");
        adjClose5.add("4.6");
        StrategyStockPO strategyStockPO5=new StrategyStockPO("","",null,adjClose5,"");

        ArrayList<String> adjClose6=new ArrayList<>();
        adjClose6.add("3.6");
        adjClose6.add("2.0");
        adjClose6.add("2.4");
        adjClose6.add("3.4");
        adjClose6.add("1.2");
        adjClose6.add("6.0");
        StrategyStockPO strategyStockPO6=new StrategyStockPO("","",null,adjClose6,"");

        ArrayList<String> adjClose7=new ArrayList<>();
        adjClose7.add("15");
        adjClose7.add("14.8");
        adjClose7.add("20.5");
        adjClose7.add("30.8");
        adjClose7.add("18.2");
        adjClose7.add("20.6");
        StrategyStockPO strategyStockPO7=new StrategyStockPO("","",null,adjClose7,"");

        ArrayList<String> adjClose8=new ArrayList<>();
        adjClose8.add("20");
        adjClose8.add("21.8");
        adjClose8.add("22.5");
        adjClose8.add("23.8");
        adjClose8.add("21.2");
        adjClose8.add("20.6");
        StrategyStockPO strategyStockPO8=new StrategyStockPO("","",null,adjClose8,"");

        ArrayList<String> adjClose9=new ArrayList<>();
        adjClose9.add("5.4");
        adjClose9.add("5");
        adjClose9.add("4.5");
        adjClose9.add("3.8");
        adjClose9.add("4.2");
        adjClose9.add("3.6");
        StrategyStockPO strategyStockPO9=new StrategyStockPO("","",null,adjClose9,"");

        ArrayList<String> adjClose10=new ArrayList<>();
        adjClose10.add("1.6");
        adjClose10.add("2.4");
        adjClose10.add("1.5");
        adjClose10.add("3.1");
        adjClose10.add("2.6");
        adjClose10.add("0.9");
        StrategyStockPO strategyStockPO10=new StrategyStockPO("","",null,adjClose10,"");

        ArrayList<String> adjClose11=new ArrayList<>();
        adjClose11.add("7");
        adjClose11.add("7.2");
        adjClose11.add("7.5");
        adjClose11.add("6.2");
        adjClose11.add("5.6");
        adjClose11.add("6.8");
        StrategyStockPO strategyStockPO11=new StrategyStockPO("","",null,adjClose11,"");

        ArrayList<StrategyStockPO> strategyStockPOS=new ArrayList<>();
        strategyStockPOS.add(strategyStockPO1);
        strategyStockPOS.add(strategyStockPO2);
        strategyStockPOS.add(strategyStockPO3);
        strategyStockPOS.add(strategyStockPO4);
        strategyStockPOS.add(strategyStockPO5);
        strategyStockPOS.add(strategyStockPO6);
        strategyStockPOS.add(strategyStockPO7);
        strategyStockPOS.add(strategyStockPO8);
        strategyStockPOS.add(strategyStockPO9);
        strategyStockPOS.add(strategyStockPO10);
        strategyStockPOS.add(strategyStockPO11);

        //run
        MomentStrategy momentStrategy=new MomentStrategy();
        ArrayList<String> momentData=momentStrategy.getMomentData(1,3,strategyStockPOS);

        for (int i = 0; i < momentData.size(); i++) {
            System.out.println(momentData.get(i));
        }

    }
    */

}
