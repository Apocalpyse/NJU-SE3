package bl.industryBl;

import PO.industryPO.IndustryPO;
import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import VO.industryVO.IndustryVO;
import VO.stockVO.GoalVO;
import VO.stockVO.StockPieVO;
import VO.stockVO.StockVO;
import VO.stockVO.TwentyStockVO;
import bl.stockBl.Stock;
import data.industryData.IndustryData;
import data.stockData.FindStockInfoData;
import data.strategyData.StrategyData;
import dataSer.FindStockInfoDataSer;
import dataSer.IndustryDataSer;
import dataSer.StrategyDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by A on 2017/5/21.
 */
public class Industry {
    public static void main(String[] args){


    }
    public ArrayList<IndustryVO> findIndustryInfoOneday(String date) {
        IndustryDataSer industryDataSer = new IndustryData();
        ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
        ArrayList<IndustryVO> arrayList1 = new ArrayList<IndustryVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
                    arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
                    arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume()));
        }
        return arrayList1;
    }


    public ArrayList<IndustryVO> findTotalIndustryMaxInRate(String date) {
        IndustryDataSer industryDataSer = new IndustryData();
        ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
        ArrayList<IndustryVO> arrayList1 = new ArrayList<IndustryVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
                    arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
                    arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume()));
        }
        Collections.sort(arrayList1, new Comparator<IndustryVO>() {
            @Override
            public int compare(IndustryVO o1, IndustryVO o2) {
                IndustryVO stu1=(IndustryVO) o1;
                IndustryVO stu2=(IndustryVO)o2;
                if(Double.parseDouble(stu1.getIncreaseOrDecreaseRate())<Double.parseDouble(stu2.getIncreaseOrDecreaseRate())){
                    return 1;
                }else if(Double.parseDouble(stu2.getIncreaseOrDecreaseRate()) == Double.parseDouble(stu1.getIncreaseOrDecreaseRate())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return arrayList1;
    }


    public ArrayList<IndustryVO> findTotalIndustryMaxDeRate(String date) {
        Industry industry = new Industry();
        ArrayList<IndustryVO> arrayList=industry.findTotalIndustryMaxInRate(date);
        ArrayList<IndustryVO> arrayList1 = new ArrayList<IndustryVO>();
        for(int i=arrayList.size()-1;i>=0;i--){
            arrayList1.add(arrayList.get(i));
        }
        return arrayList1;
    }


    public ArrayList<IndustryVO> findTotalIndustryMaxInVolume(String date) {
        IndustryDataSer industryDataSer =new IndustryData();
        ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
        ArrayList<IndustryVO> arrayList1 = new ArrayList<IndustryVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
                    arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
                    arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume()));
        }
        Collections.sort(arrayList1, new Comparator<IndustryVO>() {
            @Override
            public int compare(IndustryVO o1, IndustryVO o2) {
                IndustryVO stu1=(IndustryVO) o1;
                IndustryVO stu2=(IndustryVO)o2;
                if(Double.parseDouble(stu1.getTotalVolume())<Double.parseDouble(stu2.getTotalVolume())){
                    return 1;
                }else if(Double.parseDouble(stu2.getTotalVolume()) == Double.parseDouble(stu1.getTotalVolume())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return arrayList1;
    }


    public ArrayList<IndustryVO> findTotalIndustryPotential(String date)  {
        IndustryDataSer industryDataSer = new IndustryData();
        ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
        ArrayList<IndustryVO> arrayList1 = new ArrayList<IndustryVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
                    arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
                    arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume()));
        }

        Collections.sort(arrayList1, new Comparator<IndustryVO>() {
            @Override
            public int compare(IndustryVO o1, IndustryVO o2) {
                IndustryVO stu1=(IndustryVO) o1;
                IndustryVO stu2=(IndustryVO)o2;
                if(Integer.parseInt(stu1.getCompanyNum())<Integer.parseInt(stu1.getCompanyNum())){
                    return 1;
                }else if(Integer.parseInt(stu1.getCompanyNum())== Integer.parseInt(stu1.getCompanyNum())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        return arrayList1;
    }


    public StockPieVO findTotalIndustryPieVO(String date)  {
        FindStockInfoDataSer findStockInfoDataSer = new FindStockInfoData();
        ArrayList<StockPO> arrayList=findStockInfoDataSer.findStockInfoOneday(date);
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
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<=-10){
                one=one+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<-5){
                two=two+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>-5&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<0){
                three=three+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>0&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<5){
                four=four+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>5){
                five=five+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>=10){
                six=six+1;
            }
        }




        return new StockPieVO(date,one,two,three,four,five,six);

    }


    public ArrayList<StockVO> findIndustryMaxInRate(String date, String industryName) {
        IndustryDataSer industryDataSer = new IndustryData();
        ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date,industryName);
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


    public ArrayList<StockVO> findIndustryMaxDeRate(String date, String industryName)  {
        Industry industry=new Industry();
        ArrayList<StockVO> arrayList=industry.findIndustryMaxInRate(date,industryName);
        ArrayList<StockVO> arrayList1 = new ArrayList<StockVO>();
        for(int i=arrayList.size()-1;i>=0;i--){
            arrayList1.add(arrayList.get(i));
        }
        return arrayList1;
    }


    public ArrayList<StockVO> findIndustryMaxInVolume(String date, String industryName)  {
        IndustryDataSer industryDataSer = new IndustryData();
        ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date,industryName);
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


    public ArrayList<StockVO> findIndustryPotential(String date, String industryName) {
        Stock stock=new Stock();
        StrategyDataSer strategyDataSer = new StrategyData();
        String start=strategyDataSer.getPreviousTradeDate(date,30);
        FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
        TwentyStockVO twentyStockVO=new TwentyStockVO();
        try {
            twentyStockVO=stock.getStockGoalTwentyIn(start,date,industryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<StockPO> arrayList=new ArrayList<StockPO>();
        for(int i=0;i<twentyStockVO.getGoal().size();i++){
            arrayList.add(findStockInfoDataSer.findStockInfoByCode(date,date,twentyStockVO.getGoal().get(i).getCode()).get(0));
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



    public StockPieVO findIndustryPieVO(String date, String industryName)  {
        IndustryDataSer industryDataSer=new IndustryData();
        ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date, industryName);
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
        for(int i=0;i<arrayList1.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<=-10){
                one=one+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<-5){
                two=two+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>-5&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<0){
                three=three+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>0&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<5){
                four=four+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>5){
                five=five+1;
            }
            else if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>=10){
                six=six+1;
            }
        }




        return new StockPieVO(date,one,two,three,four,five,six);

    }

}
