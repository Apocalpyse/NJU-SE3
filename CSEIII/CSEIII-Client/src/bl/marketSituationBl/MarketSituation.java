package bl.marketSituationBl;

import PO.StockPO;
import VO.MarketSituationVO;
import VO.StockVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.QuickSort;
import bl.calculateBl.Translate;
import dataSer.FindStockInfoDataSer;
import rmi.RemoteHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/9.
 */
public class MarketSituation {
    private FindStockInfoDataSer findStockInfoDataSer;

    public MarketSituation(){
        findStockInfoDataSer= RemoteHelper.getInstance().getFindStockInfoDataSer();
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

        int totalVol = 0;//当日总交易量
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

            int volume = Integer.parseInt(stockpo.getVolume());
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
        for (int i = length1-1; i >=0 ; i--) {
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
        for (int i = 0; i <length1 ; i++) {
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
            for (int i = length1-1; i >=0 ; i--) {
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

    public ArrayList<StockVO> getPotentialList(String date) throws Exception {
        FindStockInfoDataSer findStockInfoDataSer = null;
        ArrayList<StockPO> arrayList=findStockInfoDataSer.findStockInfoOneday(date);
        ArrayList<StockVO> arrayList1 = null;
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
        }
        return arrayList1;
    }
}
