package bl.KAndEMABl;

import PO.stockPO.StockPO;
import VO.stockVO.InputStockByCodeVO;
import VO.stockVO.InputStockByNameVO;
import VO.stockVO.PieVO;
import bl.calculateBl.Calculate;
import bl.calculateBl.Translate;
import dataSer.FindStockInfoDataSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class Pie {
    private FindStockInfoDataSer findStockInfoDataSer;

    /**
     * 用户输入开始日期，结束日期和股票编号,返回饼状图需要的数据
     * @param stockvo
     * @return 返回饼状图需要的数据
     * @throws Exception
     */
    public PieVO getPieDataByCode(InputStockByCodeVO stockvo) throws Exception {
        String start=stockvo.getStart();
        String end=stockvo.getEnd();
        String code=stockvo.getCode();
        PieVO pieVO=new PieVO("",code,start,end,"0","0","0","0");
        Translate translate=new Translate();
        String shortCode=translate.longToShort(code);
        ArrayList<StockPO> stockPOs= findStockInfoDataSer.findStockInfoByCode(start,end,shortCode);
        if(stockPOs!=null){
            if(stockPOs.size()>0){
                int increaseLess=0;//涨幅小于5%的天数，即0<=x<5的天数
                int increaseMore=0;//涨幅超过5%的天数，包括涨停的天数，即x>=5的天数
                int decreaseLess=0;//跌幅小于5%的天数，即-5<x<0的天数
                int decreaseMore=0;//跌幅超过-5%的天数，包括跌停的股票，即x<=-5的天数
                StockPO stockPO=stockPOs.get(0);
                pieVO.setName(stockPO.getName());

                //以下计算第一天的涨幅
                Calculate calculate=new Calculate();
                String previousTradeDate=calculate.getPreviousTradeDate(start);
                ArrayList<StockPO> preStockPOs=findStockInfoDataSer.findStockInfoByCode(previousTradeDate,previousTradeDate,shortCode);
                if(preStockPOs!=null){
                    if(preStockPOs.size()==1){
                        StockPO preStockPO=preStockPOs.get(0);
                        double preAdjClose=Double.parseDouble(preStockPO.getAdjClose());
                        double todayAdjClose=Double.parseDouble(stockPO.getAdjClose());
                        double iOrD=((todayAdjClose-preAdjClose)/preAdjClose)*100;
                        if((0<=iOrD)&&(iOrD<5)){
                            increaseLess++;
                        }else if(iOrD>5){
                            increaseMore++;
                        }else if((-5<iOrD)&&(iOrD<0)){
                            decreaseLess++;
                        }else if(iOrD<=-5){
                            decreaseMore++;
                        }
                    }
                }

                //以下计算其余天的涨幅
                for (int i = 1; i < stockPOs.size(); i++) {
                    double preAdjClose=Double.parseDouble(stockPOs.get(i-1).getAdjClose());
                    double todayAdjClose=Double.parseDouble(stockPOs.get(i).getAdjClose());
                    double iOrD=((todayAdjClose-preAdjClose)/preAdjClose)*100;
                    if((0<=iOrD)&&(iOrD<5)){
                        increaseLess++;
                    }else if(iOrD>5){
                        increaseMore++;
                    }else if((-5<iOrD)&&(iOrD<0)){
                        decreaseLess++;
                    }else if(iOrD<=-5){
                        decreaseMore++;
                    }
                }
                pieVO.setIncreaseLessDays(String.valueOf(increaseLess));
                pieVO.setIncreaseMoreDays(String.valueOf(increaseMore));
                pieVO.setDecreaseLessDays(String.valueOf(decreaseLess));
                pieVO.setDecreaseMoredays(String.valueOf(decreaseMore));
            }
        }

        return pieVO;
    }

    /**
     * 用户输入开始日期，结束日期和股票名称,返回饼状图需要的数据
     * @param stockvo
     * @return 返回饼状图需要的数据
     * @throws Exception
     */
    public PieVO getPieDataByName(InputStockByNameVO stockvo) throws Exception {
        String start=stockvo.getStart();
        String end=stockvo.getEnd();
        String name=stockvo.getName();
        PieVO pieVO=new PieVO(name,"",start,end,"0","0","0","0");
        ArrayList<StockPO> stockPOs= findStockInfoDataSer.findStockInfoByName(start,end,name);
        if(stockPOs!=null){
            if(stockPOs.size()>0){
                int increaseLess=0;//涨幅小于5%的天数，即0<=x<5的天数
                int increaseMore=0;//涨幅超过5%的天数，包括涨停的天数，即x>=5的天数
                int decreaseLess=0;//跌幅小于5%的天数，即-5<x<0的天数
                int decreaseMore=0;//跌幅超过-5%的天数，包括跌停的股票，即x<=-5的天数
                StockPO stockPO=stockPOs.get(0);
                Translate translate=new Translate();
                String longCode=translate.shortToLong(stockPO.getCode());
                pieVO.setCode(longCode);

                //以下计算第一天的涨幅
                Calculate calculate=new Calculate();
                String previousTradeDate=calculate.getPreviousTradeDate(start);
                ArrayList<StockPO> preStockPOs=findStockInfoDataSer.findStockInfoByName(previousTradeDate,previousTradeDate,name);
                if(preStockPOs!=null){
                    if(preStockPOs.size()==1){
                        StockPO preStockPO=preStockPOs.get(0);
                        double preAdjClose=Double.parseDouble(preStockPO.getAdjClose());
                        double todayAdjClose=Double.parseDouble(stockPO.getAdjClose());
                        double iOrD=((todayAdjClose-preAdjClose)/preAdjClose)*100;
                        if((0<=iOrD)&&(iOrD<5)){
                            increaseLess++;
                        }else if(iOrD>5){
                            increaseMore++;
                        }else if((-5<iOrD)&&(iOrD<0)){
                            decreaseLess++;
                        }else if(iOrD<=-5){
                            decreaseMore++;
                        }
                    }
                }

                //以下计算其余天的涨幅
                for (int i = 1; i < stockPOs.size(); i++) {
                    double preAdjClose=Double.parseDouble(stockPOs.get(i-1).getAdjClose());
                    double todayAdjClose=Double.parseDouble(stockPOs.get(i).getAdjClose());
                    double iOrD=((todayAdjClose-preAdjClose)/preAdjClose)*100;
                    if((0<=iOrD)&&(iOrD<5)){
                        increaseLess++;
                    }else if(iOrD>5){
                        increaseMore++;
                    }else if((-5<iOrD)&&(iOrD<0)){
                        decreaseLess++;
                    }else if(iOrD<=-5){
                        decreaseMore++;
                    }
                }
                pieVO.setIncreaseLessDays(String.valueOf(increaseLess));
                pieVO.setIncreaseMoreDays(String.valueOf(increaseMore));
                pieVO.setDecreaseLessDays(String.valueOf(decreaseLess));
                pieVO.setDecreaseMoredays(String.valueOf(decreaseMore));
            }

        }

        return pieVO;
    }
}
