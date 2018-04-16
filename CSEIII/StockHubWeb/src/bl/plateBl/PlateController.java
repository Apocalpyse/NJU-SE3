package bl.plateBl;

import PO.stockPO.StockPool;
import VO.plateVO.OnePlateVO;
import VO.plateVO.TotalPlateVO;
import VO.stockVO.KChartVO;
import VO.stockVO.StockPieVO;
import VO.stockVO.StockVO;
import bl.calculateBl.Translate;
import blSer.PlateBlSer;
import blSer.StockPoolBl;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class PlateController implements PlateBlSer {
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
    public KChartVO getOnePlateKChart(StockPool stockPool, String startDate, String endDate) {
        return null;
    }

    @Override
    public ArrayList<StockVO> getRecommendStocks(StockPool stockPool, String date) {
        return null;
    }


    @Override
    public ArrayList<StockVO> getIncList(StockPool stockPool, String date) {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getIncList(stockPool,date);
        return result;
    }

    @Override
    public ArrayList<StockVO> getDecList(StockPool stockPool, String date) {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getDecList(stockPool,date);
        return result;
    }

    @Override
    public ArrayList<StockVO> getVolumeList(StockPool stockPool, String date) {
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getVolumeList(stockPool,date);
        return result;
    }

    @Override
    public ArrayList<StockVO> getPotentialList(StockPool stockPool, String date){
        Plate plate=new Plate();
        ArrayList<StockVO> result=plate.getPotentialList(stockPool,date);
        return result;
    }

    @Override
    public StockPieVO getPlatePieVO(StockPool stockPool, String date) {
        Plate plate=new Plate();
        StockPieVO stockPieVO=plate.getPlatePieVO(stockPool,date);
        return stockPieVO;
    }

    @Override
    public ArrayList<StockVO> findSockPoolInfo(String start, String end, StockPool stockPool) {
        return null;
    }
}
