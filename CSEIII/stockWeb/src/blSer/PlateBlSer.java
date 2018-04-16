package blSer;

import PO.StockPool;
import Presetation.mainUI.KChart;
import VO.*;
import bl.plateBl.Plate;
import bl.stockBl.Stock;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface PlateBlSer {
    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”,没有日期，默认日期为2/14/14
     * @return
     * @throws Exception
     */
    public TotalPlateVO getTotalPlateInfo() throws Exception;

    /**
     * 获取整体的板块信息，如压缩包图“整体板块信息”，用户输入要查看的日期
     * @param date
     * @return
     * @throws Exception
     */
    public TotalPlateVO getTotalPlateInfo(String date) throws Exception;

    /**
     * 获取某板块的详细信息，如压缩包图“板块内信息”,没有日期，默认日期为2/14/14
     * @param stockPoolBl 板块类型
     * @return
     * @throws Exception
     */
    public OnePlateVO getOnePlateInfo(StockPoolBl stockPoolBl) throws Exception;

    /**
     * 获取某板块的详细信息，如压缩包图“板块内信息”，用户输入要查看的日期
     * @param stockPoolBl 板块类型，只能是三种板块或所有股票，不能是自选股
     * @param date
     * @return
     * @throws Exception
     */
    public OnePlateVO getOnePlateInfo(StockPoolBl stockPoolBl, String date) throws Exception;

   /**
     * 根据板块种类，起始日期，结束日期，获取这个板块这几天的KChart信息
     * @param startDate
     * @param endDate
    * @return
    */

   public KChartVO getOnePlateKChart(StockPoolBl stockPoolBl, String startDate, String endDate);

   /*
     * 获取优质股票推荐
     * @param stockPoolBl
     * @param date
     * @return
*/
    public ArrayList<StockVO> getRecommendStocks(StockPoolBl stockPoolBl, String date);

    /**
     * 某版块的涨幅榜（只挑前30只股票即可）
     * @param stockPoolBl
     * @param date
     * @return
     */
    public ArrayList<StockVO> getIncList(StockPoolBl stockPoolBl, String date) throws RemoteException;

    /**
     * 某版块的跌幅榜（只挑前30只股票即可）
     * @param stockPoolBl
     * @param date
     * @return
     */
    public ArrayList<StockVO> getDecList(StockPoolBl stockPoolBl, String date);

    /**
     * 某版块的成交量榜（只挑前30只股票即可）
     * @param stockPoolBl
     * @param date
     * @return
     */
    public ArrayList<StockVO> getVolumeList(StockPoolBl stockPoolBl, String date) throws RemoteException;

    /**
     * 某版块的潜力榜（只挑前30只股票即可）
     * @param stockPoolBl
     * @param date
     * @return
     */
    public ArrayList<StockVO> getPotentialList(StockPoolBl stockPoolBl, String date) throws RemoteException;

    /**
     * 获取某板块的饼状图需要的数据
     * @param stockPoolBl
     * @param date
     * @return
     */
    public StockPieVO getPlatePieVO(StockPoolBl stockPoolBl, String date) throws RemoteException;
}
