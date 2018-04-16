package bl.strategyBl;

import PO.StrategyStockPO;
import bl.calculateBl.QuickSort;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/19.
 */
public class MAStrategy {
    /**
     * 获取MA择时策略数据
     * @param longEMADays 长期均线周期，顶替别的策略的formationPeriod
     * @param shortEMADays  短期均线周期，顶替别的策略的holdingPeriod
     * @param strategyStockPOS
     * @return
     */
    public ArrayList<String> getMAData(int longEMADays, int shortEMADays, ArrayList<StrategyStockPO> strategyStockPOS){
        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            ArrayList<String> res=new ArrayList<>();
            return res;
        }

        int temp=longEMADays;
        longEMADays=Math.max(longEMADays,shortEMADays);
        shortEMADays=Math.min(temp,shortEMADays);

        double[] sumOfEveryStockYield=new double[strategyStockPOS.get(0).getAdjClose().size()-longEMADays];
        for (int i = 0; i < sumOfEveryStockYield.length; i++) {
            sumOfEveryStockYield[i]=0.00;
        }

        for (int i = 0; i < strategyStockPOS.size(); i++) {
            //对于每一种股票，都要判定获利多少
            ArrayList<Double> longEMADataS=new ArrayList<>();//长期均线图的数据
            ArrayList<Double> shortEMADataS=new ArrayList<>();//短期均线图的数据
            ArrayList<String> adjCloseS=strategyStockPOS.get(i).getAdjClose();
            for (int j = longEMADays; j < adjCloseS.size(); j++) {
                double sumOfLongEMAData=0.00;
                for (int k = 0; k < longEMADays; k++) {
                    sumOfLongEMAData=sumOfLongEMAData+Double.parseDouble(adjCloseS.get(j-k));
               }
               double averageLongEMAData=sumOfLongEMAData/longEMADays;
                longEMADataS.add(averageLongEMAData);

                double sumOfShortEMAData=0.00;
                for (int k = 0; k < shortEMADays; k++) {
                    sumOfShortEMAData=sumOfShortEMAData+Double.parseDouble(adjCloseS.get(j-k));
                }
                double avarageShortEMAData=sumOfShortEMAData/shortEMADays;
                shortEMADataS.add(avarageShortEMAData);
            }

            //比较长期和短期均线图数据，确定这个股票的买入卖出点
            ArrayList<Integer> dealS=new ArrayList<>();
            ArrayList<Double> dealAdjClose=new ArrayList<>();
            ArrayList<Integer> isBuy=new ArrayList<>();//0时指买入，1时指卖出
            boolean isPositive=true;
            double tempDifference=longEMADataS.get(0)-shortEMADataS.get(0);
            if(tempDifference<0){
                isPositive=false;
            }
            for (int j = 1; j < Math.min(longEMADataS.size(), shortEMADataS.size()); j++) {
                double difference=longEMADataS.get(j)-shortEMADataS.get(j);
                if((difference>0)&&(!isPositive)){//此时出现死叉，卖出
                    dealS.add(j+longEMADays);
                    dealAdjClose.add(Double.parseDouble(adjCloseS.get(j+longEMADays)));
                    isBuy.add(1);
                }else if((difference<0)&&(isPositive)){//此时出现金叉，买入
                    dealS.add(j+longEMADays);
                    dealAdjClose.add(Double.parseDouble(adjCloseS.get(j+longEMADays)));
                    isBuy.add(0);
                }
                if(difference>=0){
                    isPositive=true;
                }else {
                    isPositive=false;
                }
            }
            //以下是确定了买入卖出点之后，计算这只股票每天的收益率
            ArrayList<Double> accumulateYield=new ArrayList<>();
            accumulateYield.add(0.00);
            int point=-1;//用来指明指向dealS和isBuy位置的指针
            for (int j = 0; j < isBuy.size(); j++) {
                if(isBuy.get(j)==0){
                    point=j;
                    break;
                }
            }
            if(point==-1){//如果没有买入的点，则直接算累计收益率为0
                break;
            }

            ArrayList<Double> oneStockYield=new ArrayList<>();
            oneStockYield.add(0.00);
            boolean isHolding=false;
            //如果有买入的点,计算
            for (int j = longEMADays; j < adjCloseS.size()-1; j++) {
                if((point<dealS.size())&&(j==dealS.get(point))){//如果是买入或卖出点
                    if(isBuy.get(point)==0){//买入
                        oneStockYield.add(oneStockYield.get(oneStockYield.size()-1));
                        isHolding=true;
                    }else {//卖出
                        double nextAdjClose= Double.parseDouble(adjCloseS.get(j+1));
                        double todayAdjClose=Double.parseDouble(adjCloseS.get(j));
                        double tempYield=(nextAdjClose-todayAdjClose)/todayAdjClose;
                        double tempaccuYield=(1+oneStockYield.get(oneStockYield.size()-1))*(1+tempYield)-1;
                        oneStockYield.add(tempaccuYield);
                        isHolding=false;
                    }
                    point++;
                }else {//如果不是买入或卖出点
                    if(isHolding){//如果持有
                        double nextAdjClose= Double.parseDouble(adjCloseS.get(j+1));
                        double todayAdjClose=Double.parseDouble(adjCloseS.get(j));
                        double tempYield=(nextAdjClose-todayAdjClose)/todayAdjClose;
                        double tempaccuYield=(1+oneStockYield.get(oneStockYield.size()-1))*(1+tempYield)-1;
                        oneStockYield.add(tempaccuYield);
                    }else {//如果不持有
                        oneStockYield.add(oneStockYield.get(oneStockYield.size()-1));
                    }
                }

            }

            for (int j = 0; j < oneStockYield.size(); j++) {
                sumOfEveryStockYield[j]=sumOfEveryStockYield[j]+oneStockYield.get(j);
            }

            //最后清空EMADataS，以便下一种股票的计算
            longEMADataS.clear();
            shortEMADataS.clear();
        }

        ArrayList<String> accuYield=new ArrayList<>();
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        int size=strategyStockPOS.size();
        for (int i = 0; i < sumOfEveryStockYield.length; i++) {
            sumOfEveryStockYield[i]=sumOfEveryStockYield[i]/size;
            accuYield.add(String.valueOf(decimalFormat.format(sumOfEveryStockYield[i]*100))+"%");
        }

        return accuYield;
    }


    /**
     * 获取关系图的其中一个数据
     * @param strategyStockPOS
     * @param longEMADays
     * @return 注意结果为方便之后的数据处理，并没有*100，，即并没有换算成百分比形式
     */
    public ArrayList<Double> getOneRelationData(ArrayList<StrategyStockPO> strategyStockPOS,int longEMADays,int shortEMADays,int valueless){
        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            ArrayList<Double> res=new ArrayList<>();
            return res;
        }
        int temp=longEMADays;
        longEMADays=Math.max(longEMADays,shortEMADays);
        shortEMADays=Math.min(temp,shortEMADays);

        double[] sumOfEveryStockYield=new double[strategyStockPOS.get(0).getAdjClose().size()-longEMADays-valueless];
        for (int i = 0; i < sumOfEveryStockYield.length; i++) {
            sumOfEveryStockYield[i]=0.00;
        }

        for (int i = 0; i < strategyStockPOS.size(); i++) {
            //对于每一种股票，都要判定获利多少
            ArrayList<Double> longEMADataS=new ArrayList<>();//长期均线图的数据
            ArrayList<Double> shortEMADataS=new ArrayList<>();//短期均线图的数据
            ArrayList<String> adjCloseS=strategyStockPOS.get(i).getAdjClose();
            for (int j = longEMADays+valueless; j < adjCloseS.size(); j++) {
                double sumOfLongEMAData=0.00;
                for (int k = 0; k < longEMADays; k++) {
                    sumOfLongEMAData=sumOfLongEMAData+Double.parseDouble(adjCloseS.get(j-k));
                }
                double averageLongEMAData=sumOfLongEMAData/longEMADays;
                longEMADataS.add(averageLongEMAData);

                double sumOfShortEMAData=0.00;
                for (int k = 0; k < shortEMADays; k++) {
                    sumOfShortEMAData=sumOfShortEMAData+Double.parseDouble(adjCloseS.get(j-k));
                }
                double avarageShortEMAData=sumOfShortEMAData/shortEMADays;
                shortEMADataS.add(avarageShortEMAData);
            }

            //比较长期和短期均线图数据，确定这个股票的买入卖出点
            ArrayList<Integer> dealS=new ArrayList<>();
            ArrayList<Double> dealAdjClose=new ArrayList<>();
            ArrayList<Integer> isBuy=new ArrayList<>();//0时指买入，1时指卖出
            boolean isPositive=true;
            double tempDifference=longEMADataS.get(0)-shortEMADataS.get(0);
            if(tempDifference<0){
                isPositive=false;
            }
            for (int j = 1; j < Math.min(longEMADataS.size(), shortEMADataS.size()); j++) {
                double difference=longEMADataS.get(j)-shortEMADataS.get(j);
                if((difference>0)&&(!isPositive)){//此时出现死叉，卖出
                    dealS.add(j+longEMADays+valueless);
                    dealAdjClose.add(Double.parseDouble(adjCloseS.get(j+longEMADays+valueless)));
                    isBuy.add(1);
                }else if((difference<0)&&(isPositive)){//此时出现金叉，买入
                    dealS.add(j+longEMADays+valueless);
                    dealAdjClose.add(Double.parseDouble(adjCloseS.get(j+longEMADays+valueless)));
                    isBuy.add(0);
                }
                if(difference>=0){
                    isPositive=true;
                }else {
                    isPositive=false;
                }
            }
            //以下是确定了买入卖出点之后，计算这只股票每天的收益率
            ArrayList<Double> accumulateYield=new ArrayList<>();
            accumulateYield.add(0.00);
            int point=-1;//用来指明指向dealS和isBuy位置的指针
            for (int j = 0; j < isBuy.size(); j++) {
                if(isBuy.get(j)==0){
                    point=j;
                    break;
                }
            }
            if(point==-1){//如果没有买入的点，则直接算累计收益率为0
                break;
            }

            ArrayList<Double> oneStockYield=new ArrayList<>();
            oneStockYield.add(0.00);
            boolean isHolding=false;
            //如果有买入的点,计算
            for (int j = longEMADays+valueless; j < adjCloseS.size()-1; j++) {
                if((point<dealS.size())&&(j==dealS.get(point))){//如果是买入或卖出点
                    if(isBuy.get(point)==0){//买入
                        oneStockYield.add(0.00);
                        isHolding=true;
                    }else {//卖出
                        double nextAdjClose= Double.parseDouble(adjCloseS.get(j+1));
                        double todayAdjClose=Double.parseDouble(adjCloseS.get(j));
                        double tempYield=(nextAdjClose-todayAdjClose)/todayAdjClose;
                        oneStockYield.add(tempYield);
                        isHolding=false;
                    }
                    point++;
                }else {//如果不是买入或卖出点
                    if(isHolding){//如果持有
                        double nextAdjClose= Double.parseDouble(adjCloseS.get(j+1));
                        double todayAdjClose=Double.parseDouble(adjCloseS.get(j));
                        double tempYield=(nextAdjClose-todayAdjClose)/todayAdjClose;
                        oneStockYield.add(tempYield);
                    }else {//如果不持有
                        oneStockYield.add(0.00);
                    }
                }

            }

            for (int j = 0; j < oneStockYield.size(); j++) {
                sumOfEveryStockYield[j]=sumOfEveryStockYield[j]+oneStockYield.get(j);
            }

            //最后清空EMADataS，以便下一种股票的计算
            longEMADataS.clear();
            shortEMADataS.clear();
        }

        ArrayList<Double> result=new ArrayList<>();
        int size=strategyStockPOS.size();
        for (int i = 0; i < sumOfEveryStockYield.length; i++) {
            sumOfEveryStockYield[i]=sumOfEveryStockYield[i]/size;
            result.add(sumOfEveryStockYield[i]);
        }

        return result;
    }

}
