package bl.strategyBl;

import PO.StockPool;
import PO.StrategyStockPO;
import VO.InputStrategyVO;
import VO.StrategyEvaluateVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.QuickSort;
import blSer.StockPoolBl;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/1.
 */
public class Benchmark {
    /**
     * 如果是板块，获取板块的基准股票信息，并计算
     * 股票名 股票代码分别可能是：
     * 沪深300    000300
     * 中小板指     399005
     * 创业板指    399006
     * @param inputStrategyVO
     * @return
     */
    public ArrayList<String> getPlateBenchmarkData(InputStrategyVO inputStrategyVO){
        int information=Integer.parseInt(inputStrategyVO.getFormationPeriod());
        String start=inputStrategyVO.getStart();
        Calculate calculate=new Calculate();
        start=calculate.getBeforeSomeTradeDate(start,information);
        String end=inputStrategyVO.getEnd();
        String code="";
        switch (inputStrategyVO.getStockPoolBl()){
            case MAINPLATE:
                code="000300";
                break;
            case SMALLMIDDLEPLATE:
                code="399005";
                break;
            case STARTUPPLATE:
                code="399006";
                break;
        }
        GetStockInfo getStockInfo=new GetStockInfo();
        StrategyStockPO strategyStockPO=getStockInfo.getBenchmarkStockInfo(start,end,code);

        ArrayList<StrategyStockPO> strategyStockPOS=new ArrayList<StrategyStockPO>();
        strategyStockPOS.add(strategyStockPO);
        int formationPeriod=Integer.parseInt(inputStrategyVO.getFormationPeriod());
        ArrayList<String> benchmarkData=getSelfSelectBenchmarkData(strategyStockPOS,formationPeriod);

        return benchmarkData;
    }

    /**
     * 根据参数，计算这些股票每日的基准累计收益率的数据
     * @param strategyStockPOS
     * @return
     */
    public ArrayList<String> getSelfSelectBenchmarkData(ArrayList<StrategyStockPO> strategyStockPOS, int formationPeriod){
        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            ArrayList<String> res=new ArrayList<>();
            return res;
        }

        //方法二，在benchmark累类中：
        //1.根据数据计算基准累计收益率的数据
        int length=strategyStockPOS.get(0).getAdjClose().size()-formationPeriod;
        double[] yield=new double[length];
        for (int i = 0; i < length; i++) {
            yield[i]=0.00;
        }

        for (int i = 0; i < strategyStockPOS.size(); i++) {
            StrategyStockPO strategyStockPO=strategyStockPOS.get(i);
            ArrayList<String> adjCloseS=strategyStockPO.getAdjClose();
            double firstAdjClose=Double.parseDouble(adjCloseS.get(formationPeriod));
            for(int j=1+formationPeriod; j<adjCloseS.size(); j++){
                double adjClose=Double.parseDouble(adjCloseS.get(j));
                yield[j-formationPeriod]=yield[j-formationPeriod]+(adjClose-firstAdjClose)/firstAdjClose*100;
            }
        }

        ArrayList<String> benchmarkData=new ArrayList<String>();
        int size=strategyStockPOS.size();
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        for (int i = 0; i < yield.length; i++) {
            yield[i]=yield[i]/size;
            benchmarkData.add(decimalFormat.format(yield[i]).toString()+"%");

        }

        return benchmarkData;
    }

    /**
     * 获取关系图的其中一个数据
     * @param strategyStockPOS
     * @param formationPeriod
     * @param holdingPeriod
     * @param valueless 无用的形成期数据有几个
     * @return 每个持有期末与其开始时比较的收益率，注意结果为方便之后的数据处理，并没有*100，即并没有换算成百分比形式
     */
    public ArrayList<Double> getBenchmarkOneRelationData(ArrayList<StrategyStockPO> strategyStockPOS,int formationPeriod,int holdingPeriod,int valueless){
        if((strategyStockPOS==null)||(strategyStockPOS.size()==0)){
            ArrayList<Double> res=new ArrayList<>();
            return res;
        }

        ArrayList<Double> accumulativeYield=new ArrayList<Double>();//每个持有期末与其开始时比较的收益率，size=stage，即=持有期期数

        int size=strategyStockPOS.get(0).getAdjClose().size()-valueless;
        int stage=(size-formationPeriod-1)/holdingPeriod+1;//共分成stage个阶段计算
        for (int i = 0; i < stage; i++) {
            double holdYield=0.00;//初始化这一阶段投资的前20%股票在这一持有期的累计收益率
            for (int j = 0; j < strategyStockPOS.size(); j++) {
                String startStr=strategyStockPOS.get(j).getAdjClose().get(i*holdingPeriod+formationPeriod+valueless);
                double start=Double.parseDouble(startStr);;
                String endStr=strategyStockPOS.get(j).getAdjClose().
                        get(Math.min((i+1)*holdingPeriod+formationPeriod+valueless, strategyStockPOS.get(j).getAdjClose().size()-1));
                double end=Double.parseDouble(endStr);
                holdYield=holdYield+((end-start)/start);
            }
            holdYield=holdYield/strategyStockPOS.size();
            accumulativeYield.add(holdYield);
        }

        return accumulativeYield;
    }

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

        ArrayList<StrategyStockPO> strategyStockPOS=new ArrayList<>();
        strategyStockPOS.add(strategyStockPO1);
        strategyStockPOS.add(strategyStockPO2);

        //run
        Benchmark benchmark=new Benchmark();
        ArrayList<String> result = benchmark.getSelfSelectBenchmarkData(strategyStockPOS, 2);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i+" is: "+result.get(i));
        }
    }
*/

    /**
     * 计算在strategyStockPOS中每一支股票的getAdjClose()的第start到end的每一日的累计收益率
     * @param strategyStockPOS
     * @param start
     * @param end
     * @return 每日的累计收益率，其length=end-start，不算第一天的收益率
     */
    public double[] getYield(ArrayList<StrategyStockPO> strategyStockPOS,int start,int end){
        int length=end-start;
        double[] yield=new double[length];
        for (int i = 0; i < length; i++) {
            yield[i]=0.00;
        }

        for (int i = 0; i < strategyStockPOS.size(); i++) {
            StrategyStockPO strategyStockPO=strategyStockPOS.get(i);
            ArrayList<String> adjCloseS=strategyStockPO.getAdjClose();
            double preClose=Double.parseDouble(adjCloseS.get(start));
            for (int j = start; j < end; j++) {
                double close=Double.parseDouble(adjCloseS.get(j+1));
                double tempYield=((close-preClose)/preClose)*100;
                yield[j-start]=yield[j-start]+tempYield;
            }
        }

        int stockNum=strategyStockPOS.size();
        for (int i = 0; i < yield.length; i++) {
            yield[i]=yield[i]/stockNum;
        }

        return yield;
    }

    /**
     * 获取最大回撤的开始日期和结束日期
     * @param momentData
     * @return 分别返回开始日期和结束日期
     */
    public ArrayList<Integer> getMaxWithdraw(ArrayList<String> momentData){
        ArrayList<Integer> maxWithDraw=new ArrayList<Integer>(2);

        double momentDataDou[]=new double[momentData.size()];
        for (int i = 0; i < momentDataDou.length; i++) {
            String momentTempData=momentData.get(i);
            momentDataDou[i]=Double.parseDouble(momentTempData.substring(0, momentTempData.length()-1));
        }
        QuickSort quickSort=new QuickSort();
        int location[]=quickSort.getSort(momentDataDou);

        int sign=0;
        double drawBackData[]=new double[2];
        int loc[]=new int[4];//分别记录第一个回撤区间的开始、结束、第二个的开始、结束
        for (int i = 0; i < location.length; i++) {
            double minLoc=location[i];
            for (int j = location.length-1; j > i ; j--) {
                if(location[j]<minLoc){
                    drawBackData[sign]=momentDataDou[j]-momentDataDou[i];
                    loc[sign*2]=location[j];
                    loc[sign*2+1]=location[i];
                    sign++;
                    break;
                }
            }
            if(sign==2){
                break;
            }
        }

        if(drawBackData[0]>=drawBackData[1]){
            maxWithDraw.add(loc[0]);
            maxWithDraw.add(loc[1]);
        }else {
            maxWithDraw.add(loc[2]);
            maxWithDraw.add(loc[3]);
        }

        return maxWithDraw;
    }

    //以下测试getMaxWithdraw方法
    /*
    public static void main(String[] args){
        ArrayList<String> momentData=new ArrayList<String>();
        momentData.add("0.25%");
        momentData.add("0.72%");
        momentData.add("0.64%");
        momentData.add("6.23%");
        momentData.add("5.12%");
        momentData.add("3.26%");
        momentData.add("4.63%");
        Benchmark benchmark=new Benchmark();
        ArrayList<Integer> result=benchmark.getMaxWithdraw(momentData);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }
    */


}
