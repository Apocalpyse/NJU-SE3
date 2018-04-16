package bl.strategyBl;

import VO.strategyVO.StaticalVariableVO;
import VO.strategyVO.StrategyGraphVO;
import bl.calculateBl.Calculate;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class StaticalVariable {
    /**
     * 获取策略的统计变量
     * @param strategyGraphVO
     * @param start
     * @param end
     * @return
     */
    public StaticalVariableVO getStaticalVariable(StrategyGraphVO strategyGraphVO, String start, String end) {
        DecimalFormat decimalFormat=new DecimalFormat("0.00");

        ArrayList<String> strategyYieldStr=strategyGraphVO.getStrategyYield();
        ArrayList<String> benchmarkYieldStr=strategyGraphVO.getBenchmarkYield();

        if((strategyYieldStr==null)||(strategyYieldStr.size()==0)||(benchmarkYieldStr==null)||(benchmarkYieldStr.size()==0)){
            StaticalVariableVO res=new StaticalVariableVO();
            return res;
        }

        String lastStrategyYieldStr=strategyYieldStr.get(strategyYieldStr.size()-1);
        double lastStrategyYield=Double.parseDouble(lastStrategyYieldStr.substring(0,lastStrategyYieldStr.length()-1));
        String lastBenchmarkYieldStr=benchmarkYieldStr.get(benchmarkYieldStr.size()-1);
        double lastBenchmarkYield=Double.parseDouble(lastBenchmarkYieldStr.substring(0,lastBenchmarkYieldStr.length()-1));

        //以下计算策略年化收益率和基准年化收益率
        Calculate calculate=new Calculate();
        int days=calculate.getIntervalDays(start,end);
        double strategyYearYield=lastStrategyYield/days*365.0;//已经是乘以百分比之后的数据
        double benchmarkYearYield=lastBenchmarkYield/days*365.0;//已经是乘以百分比之后的数据
        String strategyYearYieldStr=String.valueOf(decimalFormat.format(strategyYearYield))+"%";//策略年化收益率
        String benchmarkYearYieldStr=String.valueOf(decimalFormat.format(benchmarkYearYield))+"%";//基准年化收益率

        //计算beta
        double sumOfStrategy=0.00;
        double sumOfBenchmark=0.00;
        double sumOfProduct=0.00;
        double sumOfStrategySquare=0.00;
        double sumOfBenchmarkSquare=0.00;
//        int size=strategyYieldStr.size();
        int size=Math.min(strategyYieldStr.size(),benchmarkYieldStr.size());
        for (int i = 0; i < size; i++) {
            String strategyStr=strategyYieldStr.get(i);
            String benchmarkStr=benchmarkYieldStr.get(i);
            double strategy=Double.parseDouble(strategyStr.substring(0,strategyStr.length()-1))/100;
            double benchmark=Double.parseDouble(benchmarkStr.substring(0,benchmarkStr.length()-1))/100;
            sumOfStrategy=sumOfStrategy+strategy;
            sumOfBenchmark=sumOfBenchmark+benchmark;
            sumOfProduct=sumOfProduct + strategy * benchmark;
            sumOfStrategySquare=sumOfStrategySquare + strategy * strategy;
            sumOfBenchmarkSquare=sumOfBenchmarkSquare + benchmark * benchmark;
        }
        double averageOfStrategy=sumOfStrategy/size;
        double averageOfBenchmark=sumOfBenchmark/size;
        double averageOfProduct=sumOfProduct/size;
        double cov=averageOfProduct-averageOfStrategy*averageOfBenchmark;//协方差

        double benchmarkVariance=sumOfBenchmarkSquare/size-averageOfBenchmark*averageOfBenchmark;//基准收益率的方差
        double beta=cov/benchmarkVariance;//beta，不需要变成百分比形式
        String betaStr=String.valueOf(decimalFormat.format(beta));

        //计算alpha
        double deposit=0.015;//中国一年期定期存款利率=1.5%
        double alpha=(strategyYearYield/100-deposit)-beta*(averageOfBenchmark-deposit);//没有乘100的数据
        String alphaStr=String.valueOf(decimalFormat.format(100*alpha))+"%";

        //计算夏普比率
        double strategyVariance=sumOfStrategySquare/size-averageOfStrategy*averageOfStrategy;//策略收益率的方差
        double strategyStandardDeviation=Math.sqrt(strategyVariance);//策略收益率的标准差
        double sharpeRatio=(averageOfStrategy-deposit)/strategyStandardDeviation;
        String sharpeRatioStr=String.valueOf(decimalFormat.format(sharpeRatio));

        //计算最大回撤
        Benchmark benchmark=new Benchmark();
        ArrayList<Integer> maxWithdraw=benchmark.getMaxWithdraw(strategyGraphVO.getStrategyYield());

        //最大回撤
        String maxWithdrawYieldStr="0.00%";
        if(!((maxWithdraw.get(0)==0)&&(maxWithdraw.get(1)==0))){//当累计收益率不是持续上升时（即当有最大回撤时），设置最大回撤
            String startYieldStr=strategyYieldStr.get(maxWithdraw.get(0));
            String endYieldStr=strategyYieldStr.get(maxWithdraw.get(1));
            double startYield=Double.parseDouble(startYieldStr.substring(0,startYieldStr.length()-1));
            double endYield=Double.parseDouble(endYieldStr.substring(0,endYieldStr.length()-1));
            double maxWithdrawYield=(startYield-endYield)/(startYield+100);
            maxWithdrawYieldStr=String.valueOf(decimalFormat.format(maxWithdrawYield*100))+"%";
        }

        //结果
        StaticalVariableVO staticalVariableVO=new StaticalVariableVO();
        staticalVariableVO.setYearYieldRate(strategyYearYieldStr);
        staticalVariableVO.setBenchmarkYearYieldRate(benchmarkYearYieldStr);
        staticalVariableVO.setAlpha(alphaStr);
        staticalVariableVO.setBeta(betaStr);
        staticalVariableVO.setSharpeRatio(sharpeRatioStr);
        staticalVariableVO.setMaxWithdraw(maxWithdrawYieldStr);

        return staticalVariableVO;
    }

    /**
     * 计算策略和基准的年化收益率
     * @param lastStrategyYieldStr 策略最后一天的累计收益率
     * @param lastBenchmarkYieldStr 基准最后一天的累计收益率
     * @param start
     * @param end
     * @return 长度为2 ，分别是策略、基准的年化收益率
     */
    /*
    public ArrayList<String> getYearYieldRate(String lastStrategyYieldStr,String lastBenchmarkYieldStr,String start,String end){
        Calculate calculate=new Calculate();
        int days=calculate.getIntervalDays(start,end);

        double strategy=Double.parseDouble(lastStrategyYieldStr.substring(0,lastStrategyYieldStr.length()-1));
        double benchmark=Double.parseDouble(lastBenchmarkYieldStr.substring(0,lastBenchmarkYieldStr.length()-1));

        double strategyYearYield=strategy/days*365.0;//已经是乘以百分比之后的数据
        double benchmarkYearYield=benchmark/days*365.0;//已经是乘以百分比之后的数据
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        String strategyStr=String.valueOf(decimalFormat.format(strategyYearYield))+"%";
        String benchmarkStr=String.valueOf(decimalFormat.format(benchmarkYearYield))+"%";

        ArrayList<String> result=new ArrayList<>();
        result.add(strategyStr);
        result.add(benchmarkStr);
        return result;
    }
*/

    /**
     *
     * @param strategyGraphVO
     * @return
     */
    /*
    public String getOthers(StrategyGraphVO strategyGraphVO){
        //计算beta
        DecimalFormat decimalFormat=new DecimalFormat("0.00");

        ArrayList<String> strategyYieldStr=strategyGraphVO.getStrategyYield();
        ArrayList<String> benchmarkYieldStr=strategyGraphVO.getBenchmarkYield();
        double sumOfStrategy=0.00;
        double sumOfBenchmark=0.00;
        double sumOfProduct=0.00;
        double sumOfStrategySquare=0.00;
        double sumOfBenchmarkSquare=0.00;
        int size=strategyYieldStr.size();
        for (int i = 0; i < size; i++) {
            String strategyStr=strategyYieldStr.get(i);
            String benchmarkStr=benchmarkYieldStr.get(i);
            double strategy=Double.parseDouble(strategyStr.substring(0,strategyStr.length()-1))/100;
            double benchmark=Double.parseDouble(benchmarkStr.substring(0,benchmarkStr.length()-1))/100;
            sumOfStrategy=sumOfStrategy+strategy;
            sumOfBenchmark=sumOfBenchmark+benchmark;
            sumOfProduct=sumOfProduct + strategy * benchmark;
            sumOfStrategySquare=sumOfStrategySquare + strategy * strategy;
            sumOfBenchmarkSquare=sumOfBenchmarkSquare + benchmark * benchmark;
        }
        double averageOfStrategy=sumOfStrategy/size;
        double averageOfBenchmark=sumOfBenchmark/size;
        double averageOfProduct=sumOfProduct/size;
        double cov=averageOfProduct-averageOfStrategy*averageOfBenchmark;//协方差

        double benchmarkVariance=sumOfBenchmarkSquare/size-averageOfBenchmark*averageOfBenchmark;//基准收益率的方差
        double beta=cov/benchmarkVariance;//beta，不需要变成百分比形式
        String betaStr=String.valueOf(decimalFormat.format(beta));

        //计算alpha
        double deposit=0.015;//中国一年期定期存款利率=1.5%
        String lastStrategyYieldStr=strategyYieldStr.get(strategyYieldStr.size()-1);
        double strategyYearYield=Double.parseDouble(lastStrategyYieldStr.substring(0,lastStrategyYieldStr.length()-1));
        double alpha=(strategyYearYield/100-deposit)-beta*(averageOfBenchmark-deposit);//没有乘100的数据
        String alphaStr=String.valueOf(decimalFormat.format(100*alpha))+"%";

        //计算夏普比率
        double strategyVariance=sumOfStrategySquare/size-averageOfStrategy*averageOfStrategy;//策略收益率的方差
        double strategyStandardDeviation=Math.sqrt(strategyVariance);//策略收益率的标准差
        double sharpeRatio=(averageOfStrategy-deposit)/strategyStandardDeviation;
        String sharpeRatioStr=String.valueOf(decimalFormat.format(sharpeRatio));

        return null;
    }
    */

}
