package bl.plateBl;

import PO.platePO.TotalPlatePO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.plateVO.OnePlateVO;
import VO.plateVO.TotalPlateVO;
import VO.stockVO.*;
import bl.add1.AddOne;
import bl.add2.AddTwo;
import bl.calculateBl.Translate;
import bl.stockBl.Stock;
import blSer.StockPoolBl;
import data.plateData.FindPlateInfoData;
import data.stockData.FindStockInfoData;
import data.strategyData.StrategyData;
import dataSer.Add2DataSer;
import dataSer.FindPlateInfoDataSer;
import dataSer.FindStockInfoDataSer;
import dataSer.StrategyDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by A on 2017/5/21.
 */
public class Plate {
    private FindPlateInfoDataSer findPlateInfoDataSer;

   public Plate(){
        findPlateInfoDataSer=new FindPlateInfoData();
   }
   public static void main(String[] args){
      Plate plate=new Plate();
      StockPieVO stockPieVO=plate.getPlatePieVO(StockPool.SMALLMIDDLEPLATE,"6/8/17");

       /*
       Plate plate=new Plate();
       ArrayList<StockVO> stockVOS=new ArrayList<StockVO>();
       try {
          stockVOS=plate.findSockPoolInfo("2/16/16","6/17/16",StockPool.SMALLMIDDLEPLATE);
       } catch (Exception e) {
           e.printStackTrace();
       }
       for(int i=0;i<stockVOS.size();i++){
           System.out.println(stockVOS.get(i).getDate());
           System.out.println(stockVOS.get(i).getOpen());
           System.out.println(stockVOS.get(i).getHigh());
           System.out.println(stockVOS.get(i).getLow());
           System.out.println(stockVOS.get(i).getClose());
       }
*/
       /*
       System.out.println(kChartVO.getStockCode());
       System.out.println(kChartVO.getStockName());
       System.out.println(kChartVO.getLastDatePrice());
       System.out.println(kChartVO.getLastDateIncOrDecRate());
       System.out.println(kChartVO.getDate().size());
       System.out.println(kChartVO.getAdjClose().size());
       System.out.println(kChartVO.getInOrDeYield().size());
*/
   }

    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”,没有日期，默认日期为2/14/14
     * @return
     * @throws Exception
     */


    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”，用户输入要查看的日期
     * @param date
     * @return
     * @throws Exception
     */
    public TotalPlateVO getTotalPlateInfo(String date) throws Exception {
        //改变date的形式为逻辑层的
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
        Translate translate=new Translate();
        date=translate.toDataDate(date);
        StrategyDataSer strategyDataSer=new StrategyData();
//        strategyDataSer=RemoteHelper.getInstance().getStrategyDataSer();
        boolean isTradeDate=strategyDataSer.isTradeDate(date);
        if(!isTradeDate){//如果不是交易日期，则返回空
            TotalPlateVO res=new TotalPlateVO();
            return res;
        }
        TotalPlateVO totalPlateVO=new TotalPlateVO();
        System.out.println(123);
        TotalPlatePO totalPlatePO=findPlateInfoDataSer.findTotalPlateInfo(date);
        System.out.println(123);
        System.out.println(totalPlatePO.getPlateName().size());
        if(totalPlatePO!=null){
            totalPlateVO=new TotalPlateVO(totalPlatePO.getDate(),totalPlatePO.getPlateName(),totalPlatePO.getCompanyNum(),
                    totalPlatePO.getAverageOpen(),totalPlatePO.getAverageClose(),totalPlatePO.getAverageAdjClose(),
                    totalPlatePO.getPreAverageAdjClose(),totalPlatePO.getIncreaseOrDecreaseMoney(),
                    totalPlatePO.getIncreaseOrDecreaseRate(),totalPlatePO.getTotalVolume());
        }
        return totalPlateVO;
    }

    /**
     * 获取某板块的详细信息，如压缩包图“板块内信息”,没有日期，默认日期为2/14/14
     * @param stockPoolBl 板块名称，如“创业板”
     * @return
     * @throws Exception
     */
    public OnePlateVO getOnePlateInfo(StockPoolBl stockPoolBl) throws Exception {
        OnePlateVO onePlateVO=getOnePlateInfo(stockPoolBl, "2/14/14");

        return onePlateVO;
    }

    /**
     * 获取某板块的详细信息，如压缩包图“板块内信息”，用户输入要查看的日期
     * @param stockPoolBl
     * @param date
     * @return
     * @throws Exception
     */
    public OnePlateVO getOnePlateInfo(StockPoolBl stockPoolBl, String date) throws Exception {
        //改变date的形式为逻辑层的
        Translate translate=new Translate();
        date=translate.toDataDate(date);

        StrategyDataSer strategyDataSer=new StrategyData();
//        strategyDataSer=RemoteHelper.getInstance().getStrategyDataSer();
        boolean isTradeDate=strategyDataSer.isTradeDate(date);
        if(!isTradeDate){//如果不是交易日期，则返回空
            ArrayList<StockVO> resStockVO=new ArrayList<>();
            OnePlateVO res=new OnePlateVO(date,resStockVO);
            return res;
        }

        ArrayList<StockPO> stockPOS=new ArrayList<>();
        if(stockPoolBl==StockPoolBl.ALL){
            FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
//            FindStockInfoDataSer findStockInfoDataSer=RemoteHelper.getInstance().getFindStockInfoDataSer();
            stockPOS=findStockInfoDataSer.findStockInfoOneday(date);
        }else {
            int type=0;//先初始化默认是主板,只可能是三个板块,type=0时，代表主板；type=1时，代表中小板；type=2时，代表创业板
            switch (stockPoolBl){
                case MAINPLATE:
                    type=0;
                    break;
                case SMALLMIDDLEPLATE:
                    type=1;
                    break;
                case STARTUPPLATE:
                    type=2;
                    break;
            }
            stockPOS= findPlateInfoDataSer.findOnePlateInfo(type, date);
        }
        //**************
//        System.out.println("next is stockpos from data");
//        System.out.println("plate + code + name + iOD + market");
//        for (int i = 0; i < stockPOS.size(); i++) {
//            StockPO temp=stockPOS.get(i);
//            System.out.println(temp.getPlate()+" "+temp.getCode()+" "+temp.getName()+" "+temp.getIncreaseOrDecrease()+" "+temp.getMarket());
//        }
//       System.out.println("end for stockpos from data");
        //**************
        ArrayList<StockVO> stockVOS=translate.toVO(stockPOS);
        OnePlateVO onePlateVO=new OnePlateVO(date, stockVOS);
        return onePlateVO;
    }


    //以下陈进写的
    //
    public KChartVO getOnePlateKChart(StockPool stockPool, String startDate, String endDate){
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
        ArrayList<TotalPlatePO> totalPlatePOS=findPlateInfoDataSer.findOnePlateInfo(startDate,endDate,stockPool);
        String stockName=totalPlatePOS.get(0).getPlateName().get(0);
        String lastDatePrice=totalPlatePOS.get(totalPlatePOS.size()-1).getAverageAdjClose().get(0);
        String lastDateIncOrDecRate=totalPlatePOS.get(totalPlatePOS.size()-1).getIncreaseOrDecreaseRate().get(0);
        String zero=null;
        ArrayList<String> one=new ArrayList<String>();
        ArrayList<Double> two=new ArrayList<Double>();
        ArrayList<Double> three=new ArrayList<Double>();
        for(int i=0;i<totalPlatePOS.size();i++){
            one .add(totalPlatePOS.get(i).getDate());
        }
        for(int i=0;i<totalPlatePOS.size();i++){
            two.add(Double.parseDouble(totalPlatePOS.get(i).getAverageAdjClose().get(0)));
        }
        for(int i=0;i<totalPlatePOS.size();i++){
           three.add(Double.parseDouble(totalPlatePOS.get(i).getIncreaseOrDecreaseRate().get(0)));
        }
        return new KChartVO(zero,stockName,lastDatePrice,lastDateIncOrDecRate,one,two,three);
    }
    public ArrayList<StockVO> getRecommendStocks(StockPool stockPool, String date){
        Stock stock=new Stock();
        StrategyDataSer strategyDataSer = new StrategyData();
        String start=strategyDataSer.getPreviousTradeDate(date,30);
        FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
        TwentyStockVO twentyStockVO=new TwentyStockVO();
        try {

            twentyStockVO=stock.getStockGoalTwenty(start,date,stockPool,new ArrayList<String>());

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<StockPO> arrayList=new ArrayList<StockPO>();
        for(int i=0;i<twentyStockVO.getGoal().size();i++){

                if(findStockInfoDataSer.findStockInfoByCode(date, date, twentyStockVO.getGoal().get(i).getCode()).size()!=0){
                    arrayList.add(findStockInfoDataSer.findStockInfoByCode(date, date, twentyStockVO.getGoal().get(i).getCode()).get(0));
                }


        }
        ArrayList<StockVO> arrayList1=new ArrayList<StockVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate(),arrayList.get(i).getIndustry()));
        }
        return arrayList1;

    }
    public ArrayList<StockVO> getIncList(StockPool stockPool, String date) {
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
        ArrayList<StockPO> arrayList = new ArrayList<StockPO>();
        if(stockPool.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPool.equals(StockPool.SMALLMIDDLEPLATE)) {
            System.out.println(date);
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPool.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPool.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = new ArrayList<StockVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate(),arrayList.get(i).getIndustry()));
        }
        Collections.sort(arrayList1, new Comparator<StockVO>() {
            @Override
            public int compare(StockVO o1, StockVO o2) {
                StockVO stu1=(StockVO) o1;
                StockVO stu2=(StockVO) o2;
                if(Double.parseDouble(stu1.getIncreaseOrDecrease())<Double.parseDouble(stu2.getIncreaseOrDecrease())){
                    return 1;
                }else if(Double.parseDouble(stu2.getIncreaseOrDecrease()) == Double.parseDouble(stu1.getIncreaseOrDecrease())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return arrayList1;
    }


    public ArrayList<StockVO> getDecList(StockPool stockPool, String date) {
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
        ArrayList<StockPO> arrayList = new ArrayList<StockPO>();
        if(stockPool.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPool.equals(StockPool.SMALLMIDDLEPLATE)) {
            System.out.println(date);
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPool.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPool.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = new ArrayList<StockVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate(),arrayList.get(i).getIndustry()));
        }
        Collections.sort(arrayList1, new Comparator<StockVO>() {
            @Override
            public int compare(StockVO o1, StockVO o2) {
                StockVO stu1=(StockVO) o1;
                StockVO stu2=(StockVO) o2;
                if(Double.parseDouble(stu1.getIncreaseOrDecrease())>Double.parseDouble(stu2.getIncreaseOrDecrease())){
                    return 1;
                }else if(Double.parseDouble(stu2.getIncreaseOrDecrease()) == Double.parseDouble(stu1.getIncreaseOrDecrease())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return arrayList1;
    }


    public ArrayList<StockVO> getVolumeList(StockPool stockPool, String date)  {
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
        ArrayList<StockPO> arrayList = new ArrayList<StockPO>();
        if(stockPool.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPool.equals(StockPool.SMALLMIDDLEPLATE)) {
            System.out.println(date);
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPool.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPool.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = new ArrayList<StockVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate(),arrayList.get(i).getIndustry()));
        }
        Collections.sort(arrayList1, new Comparator<StockVO>() {
            @Override
            public int compare(StockVO o1, StockVO o2) {
                StockVO stu1=(StockVO) o1;
                StockVO stu2=(StockVO) o2;
                if(Double.parseDouble(stu1.getVolume())<Double.parseDouble(stu2.getVolume())){
                    return 1;
                }else if(Double.parseDouble(stu2.getVolume()) == Double.parseDouble(stu1.getVolume())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return arrayList1;
    }


    public ArrayList<StockVO> getPotentialList(StockPool stockPool, String date) {
       Plate plate=new Plate();
       return plate.getRecommendStocks(stockPool,date);
    }


    public StockPieVO getPlatePieVO(StockPool stockPool, String date) {
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
        ArrayList<StockPO> arrayList = new ArrayList<StockPO>();
        if(stockPool.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPool.equals(StockPool.SMALLMIDDLEPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPool.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPool.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPool.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = new ArrayList<StockVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate(),arrayList.get(i).getIndustry()));
        }
        double one = 0;
        double two = 0;
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        for(int i=0;i<arrayList1.size();i++) {
            if (Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) <= -10) {
                one = one + 1;
            } else if (Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) < -5) {
                two = two + 1;
            } else if (Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) > -5 && Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) < 0) {
                three = three + 1;
            } else if (Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) > 0 && Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) < 5) {
                four = four + 1;
            } else if (Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) > 5) {
                five = five + 1;
            } else if (Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()) >= 10) {
                six = six + 1;
            }
        }
        return new StockPieVO(date,one,two,three,four,five,six);
    }
    /**
     * 获取某一个板块的股票某一段时间的具体信息
     *
     */
    public ArrayList<StockVO> findSockPoolInfo(String start,String end,StockPool stockPool){
            FindPlateInfoDataSer findPlateInfoDataSer = new FindPlateInfoData();
            ArrayList<StockPO> arrayList = findPlateInfoDataSer.findSockPoolInfo(start, end, stockPool);
            ArrayList<StockVO> arrayList1 = new ArrayList<StockVO>();
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList1.add(new StockVO(arrayList.get(i).getDate(), arrayList.get(i).getOpen(), arrayList.get(i).getHigh(),
                        arrayList.get(i).getLow(), arrayList.get(i).getClose(),null, null,
                        null, null, null, null,
                        null, null, null));
            }
            return arrayList1;
    }
}
