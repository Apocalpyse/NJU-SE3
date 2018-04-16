package bl.marketSituationBl;

import PO.stockPO.StockPO;
import VO.marketVO.MarketSituationVO;
import VO.stockVO.GoalVO;
import VO.stockVO.StockVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.QuickSort;
import bl.calculateBl.Translate;
import bl.stockBl.Stock;
import data.stockData.FindStockInfoData;
import dataSer.FindStockInfoDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by A on 2017/5/21.
 */
public class MarketSituation {
    private FindStockInfoDataSer findStockInfoDataSer;

  public MarketSituation(){
      findStockInfoDataSer=new FindStockInfoData();
   }
   public static void main(String[] args){
      MarketSituation marketSituation=new MarketSituation();
       ArrayList<StockVO> arrayList=null;
       try {
           arrayList=marketSituation.getIncreaseList("2/15/16");
       } catch (Exception e) {
           e.printStackTrace();
       }
       System.out.println(arrayList.size());
   }

    /**
     * 系统可以显示用户查询日期或者某一日期的股票交易市场行情相关数据。
     * 相关数据包括：当日总交易量、涨停股票数、跌停股票数、涨幅超过5%的股票数，
     * 跌幅超过5%的股票数，开盘‐收盘大于5%*上一个交易日收盘价的股票个数、
     * 开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数。等信息
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return stockVO 当日股票市场行情相关数据
     * @throws Exception
     */
    public MarketSituationVO getMarketSituation(String date) throws Exception {
        ArrayList<StockPO> stockPOs = new ArrayList<StockPO>();
        stockPOs = findStockInfoDataSer.findStockInfoOneday(date);

        if(stockPOs==null){
            MarketSituationVO marketSituationVO=new MarketSituationVO();
            return marketSituationVO;
        }

        double totalVol = 0;//当日总交易量
        int increaseStopStock = 0;//涨停股票数
        int decreaseStopStock = 0;//跌停股票数
        int increaseMoreStock = 0;//涨幅超过5%的股票数
        int decreaseMoreStock = 0;//跌幅超过5%的股票数
        int openMinusCloseMore = 0;//开盘‐收盘大于5%*上一个交易日收盘价的股票个数
        int openMinusCloseLess = 0;//开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数
        Calculate calculate=new Calculate();
        int size = stockPOs.size();
        for (int i = 0; i < size; i++) {
            StockPO stockpo = stockPOs.get(i);

            double volume = Double.parseDouble(stockpo.getVolume());
            totalVol = totalVol + volume;

            double iOrD = Double.parseDouble(stockpo.getIncreaseOrDecrease());//
            if (iOrD >= 10) {
                increaseStopStock++;
                increaseMoreStock++;
            } else if (iOrD > 5) {
                increaseMoreStock++;
            } else if (iOrD <= -10) {
                decreaseStopStock++;
                decreaseMoreStock++;
            } else if (iOrD < -5) {
                decreaseMoreStock++;
            }

            double open=Double.parseDouble(stockpo.getOpen());
            double close=Double.parseDouble(stockpo.getAdjClose());
            double preClose=Double.parseDouble(stockpo.getPreAdjClose());
            double openMinusClose = ((open-close)/preClose)*100;
            if (openMinusClose > 5) {
                openMinusCloseMore++;
            } else if (openMinusClose < -5) {
                openMinusCloseLess++;
            }

        }

        String totalVolume = String.valueOf(totalVol);//当日总交易量
        String increaseStopStockNum = String.valueOf(increaseStopStock);//涨停股票数
        String decreaseStopStockNum = String.valueOf(decreaseStopStock);//跌停股票数
        String increaseMoreStockNum = String.valueOf(increaseMoreStock);//涨幅超过5%的股票数
        String decreaseMoreStockNum = String.valueOf(decreaseMoreStock);//跌幅超过5%的股票数
        String openMinusCloseMoreNum = String.valueOf(openMinusCloseMore);//开盘‐收盘大于5%*上一个交易日收盘价的股票个数
        String openMinusCloseLessNum = String.valueOf(openMinusCloseLess);//开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数

        MarketSituationVO marketSituationVO;
        marketSituationVO = new MarketSituationVO(date, totalVolume, increaseStopStockNum, decreaseStopStockNum,
                increaseMoreStockNum, decreaseMoreStockNum, openMinusCloseMoreNum, openMinusCloseLessNum);

        return marketSituationVO;
    }

    /**
     * 获取涨幅榜的数据
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockListVO> 涨幅榜
     * @throws Exception
     */
    public ArrayList<StockVO> getIncreaseList(String date) throws Exception {
        ArrayList<StockPO> stockPOs = new ArrayList<StockPO>();
        Translate translate=new Translate();
        String dataDate=translate.toDataDate(date);

        stockPOs = findStockInfoDataSer.findStockInfoOneday(dataDate);

        if(stockPOs==null){
            ArrayList<StockVO> stockVOS=new ArrayList<StockVO>();
            return  stockVOS;
        }
        Calculate calculate=new Calculate();
        ArrayList<Double> n=new ArrayList<Double>();
        for (int i = 0; i < stockPOs.size(); i++) {
            StockPO stockPO=stockPOs.get(i);
            double close=Double.parseDouble(stockPO.getAdjClose());
            double open=Double.parseDouble(stockPO.getOpen());
            if(close-open<=0){//如果是跌了的股票的话，则从list中剔除
                stockPOs.remove(i);
                i--;
            }else {
                double iOrD=Double.parseDouble(stockPO.getIncreaseOrDecrease());
                n.add(iOrD);
            }
        }

        int length=n.size();
        double n1[]=new double[length];
        for (int i = 0; i < length; i++) {
            n1[i]=n.get(i);
        }

        //接下来要做排序算法
        QuickSort quickSort=new QuickSort();
        int location[]=quickSort.getSort(n1);
        int length1=location.length;
        ArrayList<StockPO> resultpo=new ArrayList<StockPO>();
        for (int i = length1-1; i >=length1-20 ; i--) {
            int p=location[i];
            resultpo.add(stockPOs.get(p));
        }

        //接下来把po转化为vo
        ArrayList<StockVO> result=new ArrayList<StockVO>();
        result=translate.toVO(resultpo);

        return result;
    }

    /**
     * 获取跌幅榜的数据
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockListVO> 跌幅榜
     * @throws Exception
     */
    public ArrayList<StockVO> getDecreaseList(String date) throws Exception {
        ArrayList<StockPO> stockPOs = new ArrayList<StockPO>();
        Translate translate=new Translate();
        String dataDate=translate.toDataDate(date);
        stockPOs = findStockInfoDataSer.findStockInfoOneday(dataDate);
        if(stockPOs==null){
            ArrayList<StockVO> stockVOS=new ArrayList<StockVO>();
            return stockVOS;
        }
        Calculate calculate=new Calculate();
//        double[] n={};
        ArrayList<Double> n=new ArrayList<Double>();
        for (int i = 0; i < stockPOs.size(); i++) {
            StockPO stockPO=stockPOs.get(i);
            double close=Double.parseDouble(stockPO.getAdjClose());
            double open=Double.parseDouble(stockPO.getOpen());
            if(close-open>=0){//如果是涨了的股票的话，则从list中剔除
                stockPOs.remove(i);
                i--;
            }else {
                double iOrD=Double.parseDouble(stockPO.getIncreaseOrDecrease());
                n.add(iOrD);
            }
        }

        int length=n.size();
        double n1[]=new double[length];
        for (int i = 0; i < length; i++) {
            n1[i]=n.get(i);
        }

        //接下来要做排序算法
        QuickSort quickSort=new QuickSort();
        int location[]=quickSort.getSort(n1);
        int length1=location.length;
        ArrayList<StockPO> resultpo=new ArrayList<StockPO>();
        for (int i = 0; i <20 ; i++) {
            int p=location[i];
            resultpo.add(stockPOs.get(p));
        }

        //接下来把po转化为vo
        ArrayList<StockVO> result=new ArrayList<StockVO>();
        result=translate.toVO(resultpo);

        return result;
    }

    /**
     * 获取成交量从高到低的排行榜的数据
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockListVO> 成交量从高到低的排行榜
     * @throws Exception
     */
    public ArrayList<StockVO> getVolumeList(String date) throws Exception {
        ArrayList<StockPO> stockPOs = new ArrayList<StockPO>();
        Translate translate=new Translate();
        String dataDate=translate.toDataDate(date);
        stockPOs = findStockInfoDataSer.findStockInfoOneday(dataDate);
        if(stockPOs==null){
            ArrayList<StockVO> stockVOS=new ArrayList<StockVO>();
            return stockVOS;
        }
        Calculate calculate=new Calculate();
        ArrayList<Double> n=new ArrayList<Double>();
        if(stockPOs!=null){
            for (int i = 0; i < stockPOs.size(); i++) {
                String volumeStr=stockPOs.get(i).getVolume();
                n.add(Double.parseDouble(volumeStr));
            }

            int length=n.size();
            double n1[]=new double[length];
            for (int i = 0; i < length; i++) {
                n1[i]=n.get(i);
            }

            //接下来做排序算法
            QuickSort quickSort=new QuickSort();
            int location[]=quickSort.getSort(n1);
            int length1=location.length;
            ArrayList<StockPO> resultpo=new ArrayList<StockPO>();
            for (int i = length1-1; i >=length1-20 ; i--) {
                int p=location[i];
                resultpo.add(stockPOs.get(p));
            }

            //接下来把po转化为vo
            ArrayList<StockVO> result=new ArrayList<StockVO>();
            result=translate.toVO(resultpo);

            return result;
        }

        return null;
    }

    //陈进写的
    //
    public ArrayList<StockVO> getPotentialList(String date) throws Exception {
        Stock stock=new Stock();
        ArrayList<StockPO> arrayList=findStockInfoDataSer.findStockInfoOneday(date);
        ArrayList<StockVO> stockVOS=null;
        ArrayList<GoalVO> arrayList1=null,arrayList2=null;
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(stock.getStockGoalOne(date,arrayList.get(i).getCode()));
            arrayList2.add(stock.getStockGoalOne(date,arrayList.get(i).getCode()));
        }
        Collections.sort(arrayList1, new Comparator<GoalVO>() {
            @Override
            public int compare(GoalVO o1, GoalVO o2) {
                GoalVO stu1=(GoalVO) o1;
                GoalVO stu2=(GoalVO)o2;
                if(Double.parseDouble(stu1.getTotal())<Double.parseDouble(stu2.getTotal())){
                    return 1;
                }else if(Double.parseDouble(stu1.getTotal())==Double.parseDouble(stu2.getTotal())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        for(int i=0;i<20;i++){
            StockPO stockPO=arrayList.get(arrayList2.indexOf(arrayList1.get(i)));
            stockVOS.add(new StockVO(stockPO.getDate(),stockPO.getOpen(),stockPO.getHigh(),
                    stockPO.getLow(),stockPO.getClose(),stockPO.getVolume(),stockPO.getAdjClose(),
                    stockPO.getCode(),stockPO.getName(),stockPO.getMarket(),stockPO.getIncreaseOrDecrease(),
                    stockPO.getPreAdjClose(),stockPO.getPlate(),stockPO.getIndustry()));
        }
        return stockVOS;

    }
}
