package bl.KAndEMABl;

import PO.stockPO.StockPO;
import VO.stockVO.*;
import bl.calculateBl.Calculate;
import bl.calculateBl.Translate;
import dataSer.FindStockInfoDataSer;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class KAndEMA {
    private FindStockInfoDataSer findStockInfoDataSer;


    /**
     * 用户输入开始日期，结束日期和股票编号,返回符合相关要求的K线图和均线图需要的数据
     * @param stockvo 股票相关信息
     * @return 符合相关要求的K线图和均线图需要的数据
     * @throws Exception
     */
    public ArrayList<StockVO> getDataByCode(InputStockByCodeVO stockvo) throws Exception {
        String start=stockvo.getStart();
        String end=stockvo.getEnd();
        String longCode=stockvo.getCode();
        Translate t=new Translate();
        String shortCode=t.longToShort(longCode);
        ArrayList<StockPO> stockPOs= findStockInfoDataSer.findStockInfoByCode(start,end,shortCode);

        ArrayList<StockVO> stockVOs=new ArrayList<StockVO>();
        stockVOs=t.toVO(stockPOs);
        return stockVOs;
    }

    /**
     * 用户输入开始日期，结束日期和股票名称,返回符合相关要求的K线图和均线图需要的数据
     * @param stockvo 股票相关信息
     * @return 符合相关要求的K线图和均线图需要的数据
     * @throws Exception
     */
    public ArrayList<StockVO> getDataByName(InputStockByNameVO stockvo) throws Exception {
        String start=stockvo.getStart();
        String end=stockvo.getEnd();
        String name=stockvo.getName();
        ArrayList<StockPO> stockPOs= findStockInfoDataSer.findStockInfoByName(start, end, name);

        ArrayList<StockVO> stockVOs=new ArrayList<StockVO>();
        Translate t=new Translate();
        stockVOs=t.toVO(stockPOs);
        return stockVOs;
    }

    /**
     * 根据股票代码，获取均线图数据
     * @param stockvo
     * @param numOfEMA 几日均线图，如若是10日均线图，则numOfDate=10
     * @return
     * @throws Exception
     */
    public EMAVO getEMAByCode(InputStockByCodeVO stockvo, String numOfEMA) throws Exception {
        ArrayList<String> date=new ArrayList<>();//横坐标
        ArrayList<String> yield=new ArrayList<>();//纵坐标

        String inputStart=stockvo.getStart();
        int numOfEMAInt=Integer.parseInt(numOfEMA);
        Calculate calculate=new Calculate();
        String start=calculate.getBeforeSomeTradeDate(inputStart,numOfEMAInt-1);//实际应获取数据的开始日期
        String end=stockvo.getEnd();
        String code=stockvo.getCode();
        Translate translate=new Translate();
        ArrayList<StockPO> stockPOs= findStockInfoDataSer.findStockInfoByCode(start,end,translate.longToShort(code));
        String stockName="";//股票名称
        if((stockPOs!=null)&&(stockPOs.size()>0)){
            stockName=stockPOs.get(0).getName();
        }
        boolean isValid=false;//最大最小值是否是纵坐标中数值的有效位
        double max=0;
        double min=0;
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        for (int i = 0; i < stockPOs.size() - numOfEMAInt + 1; i++) {
            double sumOfAdjClose=0.00;//复权收盘价之和
            for (int j = 0; j < numOfEMAInt; j++) {
                sumOfAdjClose=sumOfAdjClose+Double.parseDouble(stockPOs.get(i+j).getAdjClose());
            }
            double emaAdjClose=sumOfAdjClose/numOfEMAInt;
            if(!isValid){//如果不是有效位
                max=emaAdjClose;
                min=emaAdjClose;
                isValid=true;
            }else {
                if(emaAdjClose>max){
                    max=emaAdjClose;
                }
                if(emaAdjClose<min){
                    min=emaAdjClose;
                }
            }
            yield.add(String.valueOf(decimalFormat.format(emaAdjClose)));
            date.add(stockPOs.get(i+numOfEMAInt-1).getDate());
        }

        String maxYield=String.valueOf(decimalFormat.format(max));
        String minYield=String.valueOf(decimalFormat.format(min));
        EMAVO emavo=new EMAVO(code,stockName,inputStart,end,numOfEMA,date,yield,maxYield,minYield);

        return emavo;
    }

    /**
     * 根据股票名称，获取均线图数据
     * @param stockvo
     * @param numOfEMA 几日均线图，如若是10日均线图，则numOfDate=10
     * @return
     * @throws Exception
     */
    public EMAVO getEMAByName(InputStockByNameVO stockvo, String numOfEMA) throws Exception {
        ArrayList<String> date=new ArrayList<>();//横坐标
        ArrayList<String> yield=new ArrayList<>();//纵坐标

        String inputStart=stockvo.getStart();
        int numOfEMAInt=Integer.parseInt(numOfEMA);
        Calculate calculate=new Calculate();
        String start=calculate.getBeforeSomeTradeDate(inputStart,numOfEMAInt-1);//实际应获取数据的开始日期
        String end=stockvo.getEnd();
        String name=stockvo.getName();
        ArrayList<StockPO> stockPOs= findStockInfoDataSer.findStockInfoByName(start,end,name);
        String stockCode="";//股票代码
        if((stockPOs!=null)&&(stockPOs.size()>0)){
            stockCode=stockPOs.get(0).getCode();
        }
        boolean isValid=false;//最大最小值是否是纵坐标中数值的有效位
        double max=0;
        double min=0;
        for (int i = 0; i < stockPOs.size() - numOfEMAInt + 1; i++) {
            double sumOfAdjClose=0.00;//复权收盘价之和
            for (int j = 0; j < numOfEMAInt; j++) {
                sumOfAdjClose=sumOfAdjClose+Double.parseDouble(stockPOs.get(i+j).getAdjClose());
            }
            double emaAdjClose=sumOfAdjClose/numOfEMAInt;
            if(!isValid){//如果不是有效位
                max=emaAdjClose;
                min=emaAdjClose;
                isValid=true;
            }else {
                if(emaAdjClose>max){
                    max=emaAdjClose;
                }
                if(emaAdjClose<min){
                    min=emaAdjClose;
                }
            }
            yield.add(String.valueOf(emaAdjClose));
            date.add(stockPOs.get(i+numOfEMAInt-1).getDate());
        }

        String maxYield=String.valueOf(max);
        String minYield=String.valueOf(min);
        EMAVO emavo=new EMAVO(stockCode,name,inputStart,end,numOfEMA,date,yield,maxYield,minYield);

        return emavo;
    }


    //以下陈进写的
    //
    public PieVO getPieDataByCode(InputStockByCodeVO stockvo) throws Exception{
        return null;
    };


    public PieVO getPieDataByName(InputStockByNameVO stockvo) throws Exception{
        return null;
    };
}
