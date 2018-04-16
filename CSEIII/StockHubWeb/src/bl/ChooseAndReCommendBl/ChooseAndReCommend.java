package bl.ChooseAndReCommendBl;

import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.stockVO.StockVO;
import VO.userVO.ChooseVO;
import data.industryData.IndustryData;
import data.plateData.FindPlateInfoData;
import data.stockData.FindStockInfoData;
import dataSer.FindPlateInfoDataSer;
import dataSer.IndustryDataSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class ChooseAndReCommend {
    public static void main(String[] args){
        ChooseAndReCommend chooseAndReCommend=new ChooseAndReCommend();
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("10");
        arrayList.add("15");
        ArrayList<String> arrayList1=new ArrayList<String>();
        arrayList1.add("empty");
        arrayList1.add("empty");
        ArrayList<String> arrayList2=new ArrayList<String>();
        arrayList2.add("empty");
        arrayList2.add("empty");
        ArrayList<String> arrayList3=new ArrayList<String>();
        arrayList3.add("empty");
        arrayList3.add("empty");
        ArrayList<StockVO> stockVOS=chooseAndReCommend.choose(new ChooseVO("6/7/17",StockPool.HUSHEN300,
        "empty",arrayList,arrayList1,arrayList2,arrayList3));
        for(int i=0;i<stockVOS.size();i++){
            System.out.println(stockVOS.get(i).getPlate());
        }

    }
    public ArrayList<StockVO> choose(ChooseVO chooseVO) {
        ArrayList<StockPO> stockPOS = new ArrayList<StockPO>();
        ArrayList<StockVO> stockVOS = new ArrayList<StockVO>();
        FindPlateInfoDataSer findPlateInfoDataSer = new FindPlateInfoData();
        FindStockInfoData findStockInfoData = new FindStockInfoData();
        IndustryDataSer industryDataSer = new IndustryData();
        if (chooseVO.getIndustryPool().equals("empty")) {
            if (chooseVO.getStockPool().equals(StockPool.ALL)) {
                stockPOS = findStockInfoData.findStockInfoOneday(chooseVO.getDate());
            } else if (chooseVO.getStockPool().equals(StockPool.SMALLMIDDLEPLATE)) {
                stockPOS = findPlateInfoDataSer.findOnePlateInfo(1, chooseVO.getDate());
            } else if (chooseVO.getStockPool().equals(StockPool.STARTUPPLATE)) {
                stockPOS = findPlateInfoDataSer.findOnePlateInfo(2, chooseVO.getDate());
            } else if (chooseVO.getStockPool().equals(StockPool.SHANGZHENG)) {
                stockPOS = findPlateInfoDataSer.findOnePlateInfo(3, chooseVO.getDate());
            } else if (chooseVO.getStockPool().equals(StockPool.SHANGZHENG)) {
                stockPOS = findPlateInfoDataSer.findOnePlateInfo(4, chooseVO.getDate());
            } else if (chooseVO.getStockPool().equals(StockPool.HUSHEN300)) {
                stockPOS = findPlateInfoDataSer.findOnePlateInfo(5, chooseVO.getDate());
            }
        } else {
            stockPOS = industryDataSer.findIndustryInfoOneday(chooseVO.getDate(), chooseVO.getIndustryPool());
        }
        ArrayList<StockPO> one = new ArrayList<StockPO>();
        ArrayList<StockPO> two = new ArrayList<StockPO>();
        ArrayList<StockPO> three = new ArrayList<StockPO>();
        ArrayList<StockPO> four = new ArrayList<StockPO>();


        if (!chooseVO.getOpen().get(0).equals("empty") && !chooseVO.getOpen().get(1).equals("empty")) {
            for (int i = 0; i < stockPOS.size(); i++) {
                if (Double.parseDouble(stockPOS.get(i).getOpen()) >= Double.parseDouble(chooseVO.getOpen().get(0))
                        && Double.parseDouble(stockPOS.get(i).getOpen()) <= Double.parseDouble(chooseVO.getOpen().get(1))) {
                    one.add(stockPOS.get(i));
                }
            }
        }
        else if (!chooseVO.getOpen().get(0).equals("empty") && chooseVO.getOpen().get(1).equals("empty")) {
            for (int i = 0; i < stockPOS.size(); i++) {
                if (Double.parseDouble(stockPOS.get(i).getOpen()) >= Double.parseDouble(chooseVO.getOpen().get(0))) {
                    one.add(stockPOS.get(i));
                }
            }
        }
        else if (chooseVO.getOpen().get(0).equals("empty") && !chooseVO.getOpen().get(1).equals("empty")) {
            for (int i = 0; i < stockPOS.size(); i++) {
                if (Double.parseDouble(stockPOS.get(i).getOpen()) <= Double.parseDouble(chooseVO.getOpen().get(1))) {
                    one.add(stockPOS.get(i));
                }
            }
        }
        else {
            one=stockPOS;
        }
        System.out.println(one.size());

        if (!chooseVO.getClose().get(0).equals("empty") && !chooseVO.getClose().get(1).equals("empty")) {
            for (int i = 0; i < one.size(); i++) {
                if (Double.parseDouble(one.get(i).getClose()) >= Double.parseDouble(chooseVO.getClose().get(0))
                        && Double.parseDouble(one.get(i).getClose()) <= Double.parseDouble(chooseVO.getClose().get(1))) {
                    two.add(one.get(i));
                }
            }
        }
        else if (!chooseVO.getClose().get(0).equals("empty") && chooseVO.getClose().get(1).equals("empty")) {
            for (int i = 0; i < one.size(); i++) {
                if (Double.parseDouble(one.get(i).getClose()) >= Double.parseDouble(chooseVO.getClose().get(0))) {
                    two.add(one.get(i));
                }
            }
        }
        else if (chooseVO.getClose().get(0).equals("empty") && !chooseVO.getClose().get(1).equals("empty")) {
            for (int i = 0; i < one.size(); i++) {
                if (Double.parseDouble(one.get(i).getClose()) <= Double.parseDouble(chooseVO.getClose().get(1))) {
                    two.add(one.get(i));
                }
            }
        }
        else {
            two=one;
        }
        System.out.println(two.size());

        if (!chooseVO.getInDecrease().get(0).equals("empty") && !chooseVO.getInDecrease().get(1).equals("empty")) {
            for (int i = 0; i < two.size(); i++) {
                if (Double.parseDouble(two.get(i).getIncreaseOrDecrease()) >= Double.parseDouble(chooseVO.getInDecrease().get(0))
                        && Double.parseDouble(two.get(i).getIncreaseOrDecrease()) <= Double.parseDouble(chooseVO.getInDecrease().get(1))) {
                    three.add(two.get(i));
                }
            }
        }
        else if (!chooseVO.getInDecrease().get(0).equals("empty") && chooseVO.getInDecrease().get(1).equals("empty")) {
            for (int i = 0; i < two.size(); i++) {
                if (Double.parseDouble(two.get(i).getIncreaseOrDecrease()) >= Double.parseDouble(chooseVO.getInDecrease().get(0))) {
                    three.add(two.get(i));
                }
            }
        }
        else if (chooseVO.getInDecrease().get(0).equals("empty") && !chooseVO.getInDecrease().get(1).equals("empty")) {
            for (int i = 0; i < one.size(); i++) {
                if (Double.parseDouble(two.get(i).getIncreaseOrDecrease()) <= Double.parseDouble(chooseVO.getInDecrease().get(1))) {
                    three.add(two.get(i));
                }
            }
        }
        else {
            three=two;
        }
        System.out.println(three.size());

        if (!chooseVO.getVolume().get(0).equals("empty") && !chooseVO.getVolume().get(1).equals("empty")) {
            for (int i = 0; i < three.size(); i++) {
                if (Double.parseDouble(three.get(i).getVolume()) >= Double.parseDouble(chooseVO.getVolume().get(0))
                        && Double.parseDouble(three.get(i).getVolume()) <= Double.parseDouble(chooseVO.getVolume().get(1))) {
                    four.add(three.get(i));
                }
            }
        }
        else if (!chooseVO.getVolume().get(0).equals("empty") && chooseVO.getVolume().get(1).equals("empty")) {
            for (int i = 0; i < three.size(); i++) {
                if (Double.parseDouble(three.get(i).getVolume()) >= Double.parseDouble(chooseVO.getVolume().get(0))) {
                    four.add(three.get(i));
                }
            }
        }
        else if (chooseVO.getVolume().get(0).equals("empty") && !chooseVO.getVolume().get(1).equals("empty")) {
            for (int i = 0; i < three.size(); i++) {
                if (Double.parseDouble(three.get(i).getVolume()) <= Double.parseDouble(chooseVO.getVolume().get(1))) {
                    four.add(three.get(i));
                }
            }
        }
        else {
            four=three;
        }
        System.out.println(four.size());


        for (int i = 0; i < four.size(); i++) {
            stockVOS.add(new StockVO(four.get(i).getDate(), four.get(i).getOpen(), four.get(i).getHigh(),
                    four.get(i).getLow(), four.get(i).getClose(), four.get(i).getVolume(), four.get(i).getAdjClose(),
                    four.get(i).getCode(), four.get(i).getName(), four.get(i).getMarket(), four.get(i).getIncreaseOrDecrease(),
                    four.get(i).getPreAdjClose(), four.get(i).getPlate(), four.get(i).getIndustry()));
        }

        return stockVOS;
    }
}
