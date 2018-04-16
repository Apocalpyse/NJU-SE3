package bl.plateBl;

import PO.StockPO;
import PO.StockPool;
import PO.TotalPlatePO;
import VO.OnePlateVO;
import VO.StockPieVO;
import VO.StockVO;
import VO.TotalPlateVO;
import bl.calculateBl.Translate;
import blSer.StockPoolBl;
import dataSer.FindPlateInfoDataSer;
import dataSer.FindStockInfoDataSer;
import dataSer.StrategyDataSer;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2017/3/28.
 */
public class Plate {
    private FindPlateInfoDataSer findPlateInfoDataSer;

    public Plate(){
        findPlateInfoDataSer= RemoteHelper.getInstance().getFindPlateInfoDataSer();
    }

    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”,没有日期，默认日期为2/14/14
     * @return
     * @throws Exception
     */
    public TotalPlateVO getTotalPlateInfo() throws Exception {
        TotalPlateVO totalPlateVO=getTotalPlateInfo("2/14/14");

        return totalPlateVO;
    }

    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”，用户输入要查看的日期
     * @param date
     * @return
     * @throws Exception
     */
    public TotalPlateVO getTotalPlateInfo(String date) throws Exception {
        //改变date的形式为逻辑层的
        Translate translate=new Translate();
        date=translate.toDataDate(date);

        StrategyDataSer strategyDataSer;
        strategyDataSer=RemoteHelper.getInstance().getStrategyDataSer();
        boolean isTradeDate=strategyDataSer.isTradeDate(date);
        if(!isTradeDate){//如果不是交易日期，则返回空
            TotalPlateVO res=new TotalPlateVO();
            return res;
        }

        TotalPlateVO totalPlateVO=new TotalPlateVO();
        TotalPlatePO totalPlatePO=findPlateInfoDataSer.findTotalPlateInfo(date);
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

        StrategyDataSer strategyDataSer;
        strategyDataSer=RemoteHelper.getInstance().getStrategyDataSer();
        boolean isTradeDate=strategyDataSer.isTradeDate(date);
        if(!isTradeDate){//如果不是交易日期，则返回空
            ArrayList<StockVO> resStockVO=new ArrayList<>();
            OnePlateVO res=new OnePlateVO(date,resStockVO);
            return res;
        }

        ArrayList<StockPO> stockPOS=new ArrayList<>();
        if(stockPoolBl==StockPoolBl.ALL){
            FindStockInfoDataSer findStockInfoDataSer=RemoteHelper.getInstance().getFindStockInfoDataSer();
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


    public ArrayList<StockVO> getIncList(StockPoolBl stockPoolBl, String date) throws RemoteException {
        FindPlateInfoDataSer findPlateInfoDataSer=null;
        ArrayList<StockPO> arrayList = null;
        if(stockPoolBl.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPoolBl.equals(StockPool.SMALLMIDDLEPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPoolBl.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPoolBl.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = null;
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
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


    public ArrayList<StockVO> getDecList(StockPoolBl stockPoolBl, String date) {
        Plate plate=new Plate();
        ArrayList<StockVO> arrayList=plate.getDecList(stockPoolBl,date);
        ArrayList<StockVO> arrayList1 = null;
        for(int i=arrayList.size()-1;i>=0;i--){
            arrayList1.add(arrayList.get(i));
        }
        return arrayList1;
    }


    public ArrayList<StockVO> getVolumeList(StockPoolBl stockPoolBl, String date) throws RemoteException {
        FindPlateInfoDataSer findPlateInfoDataSer=null;
        ArrayList<StockPO> arrayList = null;
        if(stockPoolBl.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPoolBl.equals(StockPool.SMALLMIDDLEPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPoolBl.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPoolBl.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = null;
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
        }
        Collections.sort(arrayList1, new Comparator<StockVO>() {
            @Override
            public int compare(StockVO o1, StockVO o2) {
                StockVO stu1=(StockVO) o1;
                StockVO stu2=(StockVO) o2;
                if(Double.parseDouble(stu1.getVolume())>Double.parseDouble(stu2.getVolume())){
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


    public ArrayList<StockVO> getPotentialList(StockPoolBl stockPoolBl, String date) throws RemoteException {
        FindPlateInfoDataSer findPlateInfoDataSer=null;
        ArrayList<StockPO> arrayList = null;
        if(stockPoolBl.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPoolBl.equals(StockPool.SMALLMIDDLEPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPoolBl.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPoolBl.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = null;
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
        }
        return arrayList1;
    }


    public StockPieVO getPlatePieVO(StockPoolBl stockPoolBl, String date) throws RemoteException {
        FindPlateInfoDataSer findPlateInfoDataSer=null;
        ArrayList<StockPO> arrayList = null;
        if(stockPoolBl.equals(StockPool.MAINPLATE)){
            arrayList=findPlateInfoDataSer.findOnePlateInfo(0,date);
        }
        else if (stockPoolBl.equals(StockPool.SMALLMIDDLEPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(1,date);
        }
        else if (stockPoolBl.equals(StockPool.STARTUPPLATE)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(2,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(3,date);
        }
        else if (stockPoolBl.equals(StockPool.SHANGZHENG)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(4,date);
        }
        else if (stockPoolBl.equals(StockPool.HUSHEN300)) {
            arrayList=findPlateInfoDataSer.findOnePlateInfo(5,date);
        }
        ArrayList<StockVO> arrayList1 = null;
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
                    arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
                    arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
                    arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
        }
        double one = 0;
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<=-10){
                one=one+1;
            }
        }
        one=one*100/arrayList.size();

        double two = 0;
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<-5){
                two=two+1;
            }
        }
        two=two*100/arrayList.size();

        double three = 0;
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>-5&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<0){
                three=three+1;
            }
        }
        three=three*100/arrayList.size();

        double four = 0;
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>0&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<5){
                four=four+1;
            }
        }
        four=four*100/arrayList.size();

        double five = 0;
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>5){
                five=five+1;
            }
        }
        five=five*100/arrayList.size();

        double six = 0;
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>=10){
                six=six+1;
            }
        }
        six=six*100/arrayList.size();
        return new StockPieVO(date,one,two,three,four,five,six);
    }
}
