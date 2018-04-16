package bl.ChooseAndReCommendBl;

import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.stockVO.StockVO;
import VO.userVO.ChooseVO;
import dataSer.FindPlateInfoDataSer;
import dataSer.IndustryDataSer;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class ChooseAndReCommend {
    public ArrayList<StockVO> choose(ChooseVO chooseVO) {
        ArrayList<StockPO> stockPOS;
        ArrayList<StockVO> stockVOS = null;
        FindPlateInfoDataSer findPlateInfoDataSer = null;
        IndustryDataSer industryDataSer=null;
        if(chooseVO.getStockPool().equals(StockPool.MAINPLATE)){
            stockPOS=findPlateInfoDataSer.findOnePlateInfo(0,chooseVO.getDate());
        }
        else if (chooseVO.getStockPool().equals(StockPool.SMALLMIDDLEPLATE)) {
            stockPOS=findPlateInfoDataSer.findOnePlateInfo(1,chooseVO.getDate());
        }
        else if (chooseVO.getStockPool().equals(StockPool.STARTUPPLATE)) {
            stockPOS=findPlateInfoDataSer.findOnePlateInfo(2,chooseVO.getDate());
        }
        else if (chooseVO.getStockPool().equals(StockPool.SHANGZHENG)) {
            stockPOS=findPlateInfoDataSer.findOnePlateInfo(3,chooseVO.getDate());
        }
        else if (chooseVO.getStockPool().equals(StockPool.SHANGZHENG)) {
            stockPOS=findPlateInfoDataSer.findOnePlateInfo(4,chooseVO.getDate());
        }
        else if (chooseVO.getStockPool().equals(StockPool.HUSHEN300)) {
            stockPOS=findPlateInfoDataSer.findOnePlateInfo(5,chooseVO.getDate());
        }
        else {
            stockPOS=industryDataSer.findIndustryInfoOneday(chooseVO.getDate(),chooseVO.getIndustryPool());
        }
        for(int i=0;i<stockPOS.size();i++){
            if(Double.parseDouble(stockPOS.get(i).getOpen())<Double.parseDouble(chooseVO.getOpen().get(0))||
                    Double.parseDouble(stockPOS.get(i).getOpen())>Double.parseDouble(chooseVO.getOpen().get(1))){
                stockPOS.remove(i);
            }
        }
        for(int i=0;i<stockPOS.size();i++){
            if(Double.parseDouble(stockPOS.get(i).getClose())<Double.parseDouble(chooseVO.getClose().get(0))||
                    Double.parseDouble(stockPOS.get(i).getClose())>Double.parseDouble(chooseVO.getClose().get(1))){
                stockPOS.remove(i);
            }
        }
        for(int i=0;i<stockPOS.size();i++){
            if(Double.parseDouble(stockPOS.get(i).getClose())<Double.parseDouble(chooseVO.getClose().get(0))||
                    Double.parseDouble(stockPOS.get(i).getClose())>Double.parseDouble(chooseVO.getClose().get(1))){
                stockPOS.remove(i);
            }
        }
        for(int i=0;i<stockPOS.size();i++){
            if(Double.parseDouble(stockPOS.get(i).getIncreaseOrDecrease())<Double.parseDouble(chooseVO.getInDecrease().get(0))||
                    Double.parseDouble(stockPOS.get(i).getIncreaseOrDecrease())>Double.parseDouble(chooseVO.getInDecrease().get(1))){
                stockPOS.remove(i);
            }
        }
        for(int i=0;i<stockPOS.size();i++){
            if(Double.parseDouble(stockPOS.get(i).getVolume())<Double.parseDouble(chooseVO.getVolume().get(0))||
                    Double.parseDouble(stockPOS.get(i).getVolume())>Double.parseDouble(chooseVO.getVolume().get(1))){
                stockPOS.remove(i);
            }
        }
        for(int i=0;i<stockPOS.size();i++){
            stockVOS.add(new StockVO(stockPOS.get(i).getDate(),stockPOS.get(i).getOpen(),stockPOS.get(i).getHigh(),
                    stockPOS.get(i).getLow(),stockPOS.get(i).getClose(),stockPOS.get(i).getVolume(),stockPOS.get(i).getAdjClose(),
                    stockPOS.get(i).getCode(),stockPOS.get(i).getName(),stockPOS.get(i).getMarket(),stockPOS.get(i).getIncreaseOrDecrease(),
                    stockPOS.get(i).getPreAdjClose(),stockPOS.get(i).getPlate(),stockPOS.get(i).getIndustry()));
        }
        return stockVOS;
    }
}
