package bl.plateBl;

import VO.*;
import bl.calculateBl.Translate;
import blSer.PlateBlSer;
import blSer.StockPoolBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/28.
 */
public class PlateController implements PlateBlSer {
    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”,没有日期，默认日期为2/14/14
     * @return
     * @throws Exception
     */
    @Override
    public TotalPlateVO getTotalPlateInfo() throws Exception {
        Plate plate=new Plate();
        TotalPlateVO totalPlateVO=plate.getTotalPlateInfo();
        return totalPlateVO;
    }

    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”，用户输入要查看的日期
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public TotalPlateVO getTotalPlateInfo(String date) throws Exception {
        Translate translate=new Translate();
        String dataDate=translate.toDataDate(date);
        Plate plate=new Plate();
        TotalPlateVO totalPlateVO=plate.getTotalPlateInfo(dataDate);
        return totalPlateVO;
    }

    /**
     * 获取某板块的详细信息，如压缩包图“板块内信息”,没有日期，默认日期为2/14/14
     * @param stockPoolBl 板块类型
     * @return
     * @throws Exception
     */
    @Override
    public OnePlateVO getOnePlateInfo(StockPoolBl stockPoolBl) throws Exception {
        Plate plate=new Plate();
        OnePlateVO onePlateVO=plate.getOnePlateInfo(stockPoolBl);
        return onePlateVO;
    }

    /**
     * 获取某板块的详细信息，如压缩包图“板块内信息”，用户输入要查看的日期
     * @param stockPoolBl 板块类型
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public OnePlateVO getOnePlateInfo(StockPoolBl stockPoolBl, String date) throws Exception {
        Translate translate=new Translate();
        String dataDate=translate.toDataDate(date);
        Plate plate=new Plate();
        OnePlateVO onePlateVO=plate.getOnePlateInfo(stockPoolBl, dataDate);
        return onePlateVO;
    }

    @Override
    public KChartVO getOnePlateKChart(StockPoolBl stockPoolBl, String startDate, String endDate) {
        return null;
    }

    @Override
    public ArrayList<StockVO> getRecommendStocks(StockPoolBl stockPoolBl, String date) {
        return null;
    }


    @Override
    public ArrayList<StockVO> getIncList(StockPoolBl stockPoolBl, String date) throws RemoteException {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getIncList(stockPoolBl,date);
        return result;
    }

    @Override
    public ArrayList<StockVO> getDecList(StockPoolBl stockPoolBl, String date) {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getDecList(stockPoolBl,date);
        return result;
    }

    @Override
    public ArrayList<StockVO> getVolumeList(StockPoolBl stockPoolBl, String date) throws RemoteException {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getVolumeList(stockPoolBl,date);
        return result;
    }

    @Override
    public ArrayList<StockVO> getPotentialList(StockPoolBl stockPoolBl, String date) throws RemoteException {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getPotentialList(stockPoolBl,date);
        return result;
    }

    @Override
    public StockPieVO getPlatePieVO(StockPoolBl stockPoolBl, String date) throws RemoteException {
        Plate plate=new Plate();
       StockPieVO stockPieVO=plate.getPlatePieVO(stockPoolBl,date);
        return stockPieVO;
    }
}
