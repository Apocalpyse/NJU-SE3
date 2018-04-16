package bl.QuantourCompare;

import PO.stockPO.StockPO;
import VO.stockVO.InputStockByCodeVO;
import VO.stockVO.InputStockByNameVO;
import VO.stockVO.StockCompareEverydayVO;
import VO.stockVO.StockCompareTotalVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.Translate;
import data.stockData.FindStockInfoData;
import dataSer.FindStockInfoDataSer;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class QuantourCompare {
    private FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
//
//    public QuantourCompare(){
//        findStockInfoDataSer= RemoteHelper.getInstance().getFindStockInfoDataSer();
//    }

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的总体的比较数据。
     * 数据包括这些股票这段时间的最低值、最高值、涨幅/跌幅、对数收益率方差。
     * @param stockvos 股票编号、开始日期和结束日期
     * @return ArrayList<StockCompareTotalVO> 这些股票的整数据
     * @throws Exception
     */
    public ArrayList<StockCompareTotalVO> quantourTableCompareByCode(ArrayList<InputStockByCodeVO> stockvos) throws Exception {
        if(stockvos==null){
            ArrayList<StockCompareTotalVO> stockCompareTotalVOS=new ArrayList<StockCompareTotalVO>();
            return stockCompareTotalVOS;
        }
        int length=stockvos.size();
        ArrayList<StockCompareTotalVO> stockCompareTotalVOs=new ArrayList<StockCompareTotalVO>();
        for(int i=0; i<length; i++){
            InputStockByCodeVO stockVO=stockvos.get(i);
            String start=stockVO.getStart();
            String end=stockVO.getEnd();
            String code=stockVO.getCode();
            Translate translate=new Translate();
            String shortCode=translate.longToShort(code);
            ArrayList<StockPO> stockPOs=findStockInfoDataSer.findStockInfoByCode(start,end,code);
            if(stockPOs!=null){
                if(stockPOs.size()>0){
                    String highStr=stockPOs.get(0).getHigh();
                    double high=Double.parseDouble(highStr);//股票这段时间的最高值
                    String lowStr=stockPOs.get(0).getLow();
                    double low=Double.parseDouble(lowStr);//股票这段时间的最低值

                    for(int j=1; j<stockPOs.size(); j++){
                        StockPO stockPO=stockPOs.get(j);
                        double tempMax=Double.parseDouble(stockPO.getHigh());
                        double tempMin=Double.parseDouble(stockPO.getLow());
                        if(tempMax>high){
                            high=tempMax;
                        }
                        if(tempMin<low){
                            low=tempMin;
                        }
                    }

                    double startPrice=Double.parseDouble(stockPOs.get(0).getAdjClose());
                    String preAdjCloseStr=stockPOs.get(0).getPreAdjClose();
                    if((preAdjCloseStr!=null)&&(preAdjCloseStr!="")){
                        startPrice=Double.parseDouble(preAdjCloseStr);//前一天的收盘价
                    }
                    int size=stockPOs.size();
                    double endPrice=Double.parseDouble(stockPOs.get(size-1).getAdjClose());//这段时间末的收盘价
                    double increaseOrDecrease=((endPrice-startPrice)/startPrice)*100;//股票这段时间的涨幅/跌幅

                    //以下计算这段时间股票的对数收益率方差，即个股的相对方差
                    double adjCloses[]=new double[stockPOs.size()+1];//存放这段时间的股票每天的复权收盘价
                    adjCloses[0]=startPrice;//前一天的收盘价
                    for (int j = 0; j < stockPOs.size(); j++) {
                        String adjCloseStr=stockPOs.get(j).getAdjClose();
                        adjCloses[j+1]=Double.parseDouble(adjCloseStr);
                    }
                    Calculate calculate=new Calculate();
                    double logarithmicYieldVariance=calculate.getLogarithmicYieldVariance(adjCloses);//股票这段时间的对数收益率方差，即个股的相对方差

                    StockCompareTotalVO stockCompareTotalVO=new StockCompareTotalVO();
                    stockCompareTotalVO.setCode(stockVO.getCode());
                    stockCompareTotalVO.setName(stockPOs.get(0).getName());
                    stockCompareTotalVO.setHigh(String.valueOf(high));
                    stockCompareTotalVO.setLow(String.valueOf(low));
                    DecimalFormat df1 = new DecimalFormat( "0.000");
                    String iOrD=String.valueOf(df1.format(increaseOrDecrease))+"%";//规范化的涨跌幅
                    DecimalFormat df2 = new DecimalFormat( "0.00000");
                    String lY2=String.valueOf(df2.format(logarithmicYieldVariance));//规范化的对数收益率
                    stockCompareTotalVO.setIncreaseOrDecrease(iOrD);
                    stockCompareTotalVO.setLogarithmicYieldVariance(lY2);

                    stockCompareTotalVOs.add(stockCompareTotalVO);
                }

            }

        }
        return stockCompareTotalVOs;
    }

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的比较图。
     * 图中显示的信息包括这些股票这段时间每天的收盘价和对数收益率。
     * @param stockvos 股票编号、开始日期和结束日期
     * @return ArrayList<ArrayList<StockCompareEverydayVO>> 这些股票的每天比较数据，
     * 								ArrayList<StockCompareEverydayVO>是每天的数据，外面的ArrayList是各只股票
     * @throws Exception
     */
    public ArrayList<ArrayList<StockCompareEverydayVO>> quantourChartCompareByCode(ArrayList<InputStockByCodeVO> stockvos) throws Exception {
        if(stockvos==null){
            ArrayList<ArrayList<StockCompareEverydayVO>> s=new ArrayList<ArrayList<StockCompareEverydayVO>>();
            return s;
        }

        ArrayList<ArrayList<StockCompareEverydayVO>> result=new ArrayList<ArrayList<StockCompareEverydayVO>>();

        int length=stockvos.size();
        for (int i = 0; i < length; i++) {
            InputStockByCodeVO inputStockByCodeVO=stockvos.get(i);
            String start=inputStockByCodeVO.getStart();
            String end=inputStockByCodeVO.getEnd();
            String code=inputStockByCodeVO.getCode();
            Translate translate=new Translate();
            String shortCode=translate.longToShort(code);
            ArrayList<StockPO> stockPOs=findStockInfoDataSer.findStockInfoByCode(start,end,code);
            if(stockPOs!=null){
                ArrayList<StockCompareEverydayVO> stockCompareEverydayVOs=new ArrayList<StockCompareEverydayVO>();
                for (int j = 0; j < stockPOs.size(); j++) {
                    StockPO stockPO=stockPOs.get(j);
                    StockCompareEverydayVO stockCompareEverydayVO=new StockCompareEverydayVO(translate.shortToLong(stockPO.getCode()),
                            stockPO.getName(),stockPO.getDate(), "");

                    double preAdjClose=Double.parseDouble(stockPO.getAdjClose());
                    String preAdjCloseStr=stockPO.getPreAdjClose();
                    if((preAdjCloseStr!=null)&&(preAdjCloseStr!="")){
                        preAdjClose=Double.parseDouble(preAdjCloseStr);
                    }
                    double todayAdjClose=Double.parseDouble(stockPO.getAdjClose());
                    double lY1=Math.log(todayAdjClose/preAdjClose);
                    DecimalFormat df = new DecimalFormat( "0.00000");
                    String lY2=String.valueOf(df.format(lY1));//规范化的对数收益率
                    stockCompareEverydayVO.setLogarithmicYield(lY2);

                    stockCompareEverydayVOs.add(stockCompareEverydayVO);
                }

                result.add(stockCompareEverydayVOs);

            }


        }

        return result;
    }

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的比较数据。
     * 数据包括这些股票这段时间的最低值、最高值、涨幅/跌幅、对数收益率方差。
     * @param stockvos 股票名称、开始日期和结束日期
     * @return ArrayList<StockCompareTotalVO> 这些股票的整数据
     * @throws Exception
     */
    public ArrayList<StockCompareTotalVO> quantourTableCompareByName(ArrayList<InputStockByNameVO> stockvos) throws Exception {
        if(stockvos==null){
            ArrayList<StockCompareTotalVO> stockCompareTotalVOS=new ArrayList<StockCompareTotalVO>();
            return stockCompareTotalVOS;
        }

        int length=stockvos.size();
        ArrayList<StockCompareTotalVO> stockCompareTotalVOs=new ArrayList<StockCompareTotalVO>();
        for(int i=0; i<length; i++){
            InputStockByNameVO stockVO=stockvos.get(i);
            String start=stockVO.getStart();
            String end=stockVO.getEnd();
            String name=stockVO.getName();
            ArrayList<StockPO> stockPOs=findStockInfoDataSer.findStockInfoByName(start,end,name);
            if((stockPOs!=null)&&(stockPOs.size()!=0)){
                String highStr=stockPOs.get(0).getHigh();
                double high=Double.parseDouble(highStr);//股票这段时间的最高值
                String lowStr=stockPOs.get(0).getLow();
                double low=Double.parseDouble(lowStr);//股票这段时间的最低值

                for(int j=1; j<stockPOs.size(); j++){
                    StockPO stockPO=stockPOs.get(j);
                    double tempMax=Double.parseDouble(stockPO.getHigh());
                    double tempMin=Double.parseDouble(stockPO.getLow());
                    if(tempMax>high){
                        high=tempMax;
                    }
                    if(tempMin<low){
                        low=tempMin;
                    }
                }


                double startPrice=Double.parseDouble(stockPOs.get(0).getAdjClose());
                String preAdjCloseStr=stockPOs.get(0).getPreAdjClose();
                if((preAdjCloseStr!=null)&&(preAdjCloseStr!="")){
                    startPrice=Double.parseDouble(preAdjCloseStr);//这段时间前一天的收盘价
                }

                int size=stockPOs.size();
                double endPrice=Double.parseDouble(stockPOs.get(size-1).getAdjClose());//这段时间末的收盘价
                double increaseOrDecrease=((endPrice-startPrice)/startPrice)*100;//股票这段时间的涨幅/跌幅

                //以下计算这段时间股票的对数收益率方差，即个股的相对方差
                double adjCloses[]=new double[stockPOs.size()+1];//存放这段时间的股票每天的复权收盘价
                adjCloses[0]=startPrice;
                for (int j = 0; j < stockPOs.size(); j++) {
                    String adjCloseStr=stockPOs.get(j).getAdjClose();
                    adjCloses[j+1]=Double.parseDouble(adjCloseStr);
                }
                Calculate calculate=new Calculate();
                double logarithmicYieldVariance=calculate.getLogarithmicYieldVariance(adjCloses);//股票这段时间的对数收益率方差，即个股的相对方差

                StockCompareTotalVO stockCompareTotalVO=new StockCompareTotalVO();
                Translate translate=new Translate();
                String longCode=translate.shortToLong(stockPOs.get(0).getCode());
                stockCompareTotalVO.setCode(longCode);
                stockCompareTotalVO.setName(stockVO.getName());
                stockCompareTotalVO.setHigh(String.valueOf(high));
                stockCompareTotalVO.setLow(String.valueOf(low));
                DecimalFormat df1 = new DecimalFormat( "0.000");
                String iOrD=String.valueOf(df1.format(increaseOrDecrease))+"%";//规范化的涨跌幅
                DecimalFormat df2 = new DecimalFormat( "0.00000");
                String lY2=String.valueOf(df2.format(logarithmicYieldVariance));//规范化的对数收益率的方差
                stockCompareTotalVO.setIncreaseOrDecrease(iOrD);
                stockCompareTotalVO.setLogarithmicYieldVariance(lY2);

                stockCompareTotalVOs.add(stockCompareTotalVO);
            }

        }
        return stockCompareTotalVOs;
    }

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的比较图。
     * 图中显示的信息包括这些股票这段时间每天的收盘价和对数收益率。
     * @param stockvos 股票名称、开始日期和结束日期
     * @return ArrayList<ArrayList<StockCompareEverydayVO>> 这些股票的每天比较数据，
     * 								ArrayList<StockCompareEverydayVO>是每天的数据，外面的ArrayList是各只股票
     * @throws Exception
     */
    public ArrayList<ArrayList<StockCompareEverydayVO>> quantourChartCompareByName(ArrayList<InputStockByNameVO> stockvos) throws Exception {
        if(stockvos==null){
            ArrayList<ArrayList<StockCompareEverydayVO>> s=new ArrayList<ArrayList<StockCompareEverydayVO>>();
            return s;
        }

        ArrayList<ArrayList<StockCompareEverydayVO>> result=new ArrayList<ArrayList<StockCompareEverydayVO>>();

        int length=stockvos.size();
        for (int i = 0; i < length; i++) {
            InputStockByNameVO inputStockByCodeVO=stockvos.get(i);
            String start=inputStockByCodeVO.getStart();
            String end=inputStockByCodeVO.getEnd();
            String name=inputStockByCodeVO.getName();
            ArrayList<StockPO> stockPOs=findStockInfoDataSer.findStockInfoByName(start,end,name);
            if(stockPOs!=null){
                ArrayList<StockCompareEverydayVO> stockCompareEverydayVOs=new ArrayList<StockCompareEverydayVO>();
                for (int j = 0; j < stockPOs.size(); j++) {
                    StockPO stockPO=stockPOs.get(j);
                    Translate translate=new Translate();
                    String longCode=translate.shortToLong(stockPO.getCode());
                    StockCompareEverydayVO stockCompareEverydayVO=new StockCompareEverydayVO(longCode,
                            stockPO.getName(), stockPO.getDate(),"");

                    double preAdjClose=Double.parseDouble(stockPO.getAdjClose());
                    String preAdjCloseStr=stockPO.getPreAdjClose();
                    if((preAdjCloseStr!=null)&&(preAdjCloseStr!="")){
                        preAdjClose=Double.parseDouble(preAdjCloseStr);
                    }
                    double todayAdjClose=Double.parseDouble(stockPO.getAdjClose());
                    double lY1=Math.log(todayAdjClose/preAdjClose);
                    DecimalFormat df = new DecimalFormat( "0.00000");
                    String lY2=String.valueOf(df.format(lY1));//规范化的对数收益率
                    stockCompareEverydayVO.setLogarithmicYield(lY2);

                    stockCompareEverydayVOs.add(stockCompareEverydayVO);

                }

                result.add(stockCompareEverydayVOs);

            }


        }

        return result;
    }


}
