package bl.strategyBl;

import PO.stockPO.StockPool;
import PO.strategyPO.InputStrategyPO;
import PO.strategyPO.StrategyStockPO;
import VO.strategyVO.InputStrategyVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.Translate;
import data.strategyData.StrategyData;
import dataSer.StrategyDataSer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by A on 2017/5/21.
 */
public class GetStockInfo {
    static InputStrategyVO staticInputStrategyVO;
    static ArrayList<StrategyStockPO> staticStrategyStockPOS;

    private StrategyDataSer strategyDataSer=new StrategyData();

//    public GetStockInfo(){
//        strategyDataSer= RemoteHelper.getInstance().getStrategyDataSer();
//    }

    /**
     * 根据股票池、开始日期-形成期、结束日期获得股票信息（包括股票代码、股票名称、每日的赋权收盘指数）
     * @param inputStrategyVO
     * @return
     */
    public ArrayList<StrategyStockPO> getStrategyStockInfo(InputStrategyVO inputStrategyVO){
        // 方法一，在总体获得数据的类中
        //先根据股票池、开始日期-形成期、结束日期获得股票信息（包括股票代码、股票名称、每日的赋权收盘指数）
        if(inputStrategyVO==staticInputStrategyVO){
            return staticStrategyStockPOS;
        }
        String formationPeriodStr=inputStrategyVO.getFormationPeriod();
        int formationPeriod=Integer.parseInt(formationPeriodStr);
        String start=inputStrategyVO.getStart();
        Calculate calculate=new Calculate();
        start=calculate.getBeforeSomeTradeDate(start, formationPeriod);
        String end=inputStrategyVO.getEnd();

        StockPool stockPoolPO=StockPool.SELECTPLATE;//先初始化默认是自选股
        switch (inputStrategyVO.getStockPoolBl()){
            case ALL:
                stockPoolPO=StockPool.ALL;
                break;
            case MAINPLATE:
                stockPoolPO=StockPool.MAINPLATE;
                break;
            case SHANGZHENG:
                stockPoolPO=StockPool.SHANGZHENG;
                break;
            case SHENZHENG:
                stockPoolPO=StockPool.SHENZHENG;
                break;
            case HUSHEN300:
                stockPoolPO=StockPool.HUSHEN300;
                break;
            case SMALLMIDDLEPLATE:
                stockPoolPO=StockPool.SMALLMIDDLEPLATE;
                break;
            case STARTUPPLATE:
                stockPoolPO=StockPool.STARTUPPLATE;
                break;
            case SELECTPLATE:
                stockPoolPO=StockPool.SELECTPLATE;
                break;
        }

        InputStrategyPO inputStrategyPO=new InputStrategyPO(start,end,stockPoolPO,inputStrategyVO.getStockNameOrCode());
        ArrayList<StrategyStockPO> strategyStockPOS=new ArrayList<StrategyStockPO>();
        strategyStockPOS=strategyDataSer.findStrategyStockInfo(inputStrategyPO);

        //修改静态变量
        staticInputStrategyVO=inputStrategyVO;
        staticStrategyStockPOS=strategyStockPOS;

        //***************************
//        System.out.println("strategyStockPOS from getStockInfo()");
//        for (int i = 0; i < strategyStockPOS.size(); i++) {
//            StrategyStockPO strategyStockPO=strategyStockPOS.get(i);
//            System.out.print(strategyStockPO.getCode()+" "+strategyStockPO.getName()+" ");
//            System.out.println(strategyStockPO.getDate().size()+" "+strategyStockPO.getAdjClose().size());
//        }
//        System.out.println("getStockInfo end");
        //***************************

        return strategyStockPOS;
    }

    /**
     * 获取相应时间区间内的基准股票每日的信息
     * 股票名 股票代码分别可能是：
     * 沪深300    000300
     * 中小板指     399005
     * 创业板指    399006
     * @param start
     * @param end
     * @param code
     * @return
     */
    public StrategyStockPO getBenchmarkStockInfo(String start ,String end, String code){
        StrategyStockPO strategyStockPO=new StrategyStockPO();
        strategyStockPO=strategyDataSer.findBenchmarkStockInfo(start,end,code);
        return strategyStockPO;
    }

    /**
     * 输入某个交易日，返回这个交易日的上个交易日
     * @param date
     * @return
     */
    public String getPreviousTradeDate(String date){
        Translate t=new Translate();
        Calendar c=t.myToCalendar(date);
        int week=c.get(Calendar.DAY_OF_WEEK);
        if(week==2){//如果是周一，则返回到上个交易日需要提前3天，到上个周五
            c.add(Calendar.DATE, -3);
        }else {//若不是周一，则只需要提前一天即可转到上个交易日
            c.add(Calendar.DATE, -1);
        }
        String preDate=t.myToString(c);//若从数据层取不到数据，则先用自己定义的这个几乎正确的结论
        preDate=strategyDataSer.getPreviousTradeDate(date,1);
        return preDate;
    }

    /**
     * 获取当前交易日前somedays天的交易日，someday>0
     * @param date 当前日期
     * @param somedays
     * @return
     */
    public String getBeforeSomeTradeDate(String date, int somedays){
        String preDate=date;
        preDate=strategyDataSer.getPreviousTradeDate(date,somedays);
        return preDate;
    }

}
