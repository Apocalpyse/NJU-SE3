package bl.calculateBl;

import java.util.Calendar;

/**
 * Created by A on 2017/5/21.
 */
public class Calculate {
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
        String result=t.myToString(c);
//        GetStockInfo getStockInfo=new GetStockInfo();
//        String result=getStockInfo.getPreviousTradeDate(date);
        return result;
    }

    /**
     * 获取当前交易日前somedays天的交易日，someday>0
     * @param date 当前日期
     * @param somedays
     * @return
     */
    public String getBeforeSomeTradeDate(String date, int somedays){
        String beforeDate=date;
        for (int i = 0; i < somedays; i++) {
            beforeDate=getPreviousTradeDate(beforeDate);
        }
//        GetStockInfo getStockInfo=new GetStockInfo();
//        String beforeDate=getStockInfo.getBeforeSomeTradeDate(date,somedays);
        return beforeDate;
    }

    /**
     * 获取起始日期和结束日期之间的所有的交易日
     * @param start 起始日期
     * @param end 结束日期
     * @return 起始日期和结束日期之间的所有的交易日
     */
    /*
    public ArrayList<String> getIntervalTradeDate(String start, String end){
        ArrayList<String> intervalTradeDate=new ArrayList<String>();
        Translate translate=new Translate();
        Calendar startCa=translate.myToCalendar(start);
        Calendar endCa=translate.myToCalendar(end);
        for(Calendar i=startCa; (i.after(endCa)==false); i.add(Calendar.DATE, 1)){
            int week=i.get(Calendar.DAY_OF_WEEK);
            if((2<=week)&&(week<=6)){//如果是交易日的话（即如果是周一至周五），则添加到结果中
                String tradeDate=translate.myToString(i);
                intervalTradeDate.add(tradeDate);
            }
        }
        return intervalTradeDate;
    }
*/

    /**
     * 获取两个日期之间相差的天数
     * @param start
     * @param end
     * @return
     */
    public int getIntervalDays(String start,String end){
        Translate translate=new Translate();
        Calendar startCa=translate.myToCalendar(start);
        Calendar endCa=translate.myToCalendar(end);
        long startTime=startCa.getTimeInMillis();
        long endTime=endCa.getTimeInMillis();
        int betweenDays=(int)((endTime-startTime)/(1000*3600*24));

        return betweenDays;
    }

    /**
     * 大致计算开始日期和结束日期之间有几个交易日（计算量大且只要求粗略估算，因此牺牲精确度而提高效率）
     * @param start
     * @param end
     * @return
     */
    public int getIntervalTradeDayNums(String start, String end){
        String[] startStr=new String[3];
        startStr=start.split("/");
        int startMonth=Integer.parseInt(startStr[0]);
        int startDate=Integer.parseInt(startStr[1]);
        int startYear=Integer.parseInt(startStr[2]);

        String[] endStr=new String[3];
        endStr=end.split("/");
        int endMonth=Integer.parseInt(endStr[0]);
        int endDate=Integer.parseInt(endStr[1]);
        int endYear=Integer.parseInt(endStr[2]);

        int days=(endYear-startYear)*365+(endMonth-startMonth)*30+(endDate-startDate);

        int tradeDays=days*5/7;

        return tradeDays;
    }

    /*
    public static void main(String args[]){
        Calculate calculate=new Calculate();
        int tradeDays=calculate.getIntervalTradeDayNums("2/20/13","1/5/14");
        System.out.println(tradeDays);
    }
    */

/*
    public static void main(String args[]){
        Calculate calculate=new Calculate();
//        ArrayList<String> result=calculate.getIntervalTradeDate("2/25/17","4/29/17");
//        for(int i=0;i<result.size();i++){
//            System.out.println(result.get(i));
//        }
        String a=calculate.getBeforeSomeTradeDate("2/22/17",8);
        System.out.println(a);
    }
*/


//    /**
//     * 计算某天的某只股票的跌涨幅，注意，若数据里不含前一日股票，则默认跌涨幅为0
//     * @param date
//     * @param code
//     * @return
//     */
//    public double increaseOrDecreaseCalc(String date, String code){
//        String previousTradeDate=getPreviousTradeDate(date);
//        Translate translate=new Translate();
//        String shortCode=translate.longToShort(code);
//        ArrayList<StockPO> stockPOs=new ArrayList<StockPO>();
//
//        FindStockInfoDataSer findStockInfoDataSer;
//        findStockInfoDataSer= RemoteHelper.getInstance().getFindStockInfoDataSer();
//        try {
//            stockPOs=findStockInfoDataSer.findStockInfoByCode(previousTradeDate,date,shortCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(stockPOs!=null){
//            if(stockPOs.size()==2){
//                String preClose=stockPOs.get(0).getAdjClose();
//                double previous=Double.parseDouble(preClose);
//                String todayClose=stockPOs.get(1).getAdjClose();
//                double today=Double.parseDouble(todayClose);
//                double increaseOrDecrease=((today-previous)/previous)*100;
//                return  increaseOrDecrease;
//            }
//        }
//
//        return 0.0;
//    }

//    /**
//     * 计算（开盘价-收盘价）/上一交易日的收盘价
//     * @param date
//     * @param code
//     * @return
//     */
//    public double openMinusCloseCalc(String date, String code){
//        String previousTradeDate=getPreviousTradeDate(date);
//        Translate translate=new Translate();
//        String shortCode=translate.longToShort(code);
//        ArrayList<StockPO> stockPOs=new ArrayList<StockPO>();
//
//        FindStockInfoDataSer findStockInfoDataSer;
//        findStockInfoDataSer= RemoteHelper.getInstance().getFindStockInfoDataSer();
//        try {
//            stockPOs=findStockInfoDataSer.findStockInfoByCode(previousTradeDate,date,shortCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(stockPOs!=null){
//            if(stockPOs.size()==2){
//                String preClose=stockPOs.get(0).getClose();
//                double previous=Double.parseDouble(preClose);
//                String todayOpen=stockPOs.get(1).getOpen();
//                String todayClose=stockPOs.get(1).getClose();
//                double open=Double.parseDouble(todayOpen);
//                double close=Double.parseDouble(todayClose);
//                double openMinusClose=((open-close)/previous)*100;
//                return  openMinusClose;
//            }
//        }
//
//        return 0.0;
//    }

    /**
     * 计算选定区间内对数收益率的方差，即个股的相对方差
     * @param adjCloses
     * @return
     */
    public double getLogarithmicYieldVariance(double adjCloses[]){
        if(adjCloses.length<=1){
            return 0.0;
        }
        double logarithmicYield[]=new double[adjCloses.length-1];//存放这段时间的对数收益率
        for (int i = 0; i < adjCloses.length-1; i++) {
            logarithmicYield[i]=Math.log(adjCloses[i+1]/adjCloses[i]);//对数收益率r=log(当日收盘价/前一日收盘价)
        }
        //以下计算对数收益率的方差
        double sum=0.0;//和
        double quadraticSum=0.0;//平方和
        int length=logarithmicYield.length;
        for (int i = 0; i < length; i++) {
            sum=sum+logarithmicYield[i];
            quadraticSum=quadraticSum+logarithmicYield[i]*logarithmicYield[i];
        }
        double logarithmicYieldVariance=(quadraticSum-sum*sum/length)/length;

        return logarithmicYieldVariance;
    }

}
