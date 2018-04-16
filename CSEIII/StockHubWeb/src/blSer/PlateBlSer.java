package blSer;

import PO.stockPO.StockPool;
import VO.plateVO.OnePlateVO;
import VO.plateVO.TotalPlateVO;
import VO.stockVO.KChartVO;
import VO.stockVO.StockPieVO;
import VO.stockVO.StockVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface PlateBlSer {

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

    public KChartVO getOnePlateKChart(StockPool stockPool, String startDate, String endDate);

    /*
      * 获取优质股票推荐
      * @param stockPoolBl
      * @param date
      * @return
 */
    public ArrayList<StockVO> getRecommendStocks(StockPool stockPool, String date);

    /**
     * 某版块的涨幅榜（只挑前30只股票即可）
     * @param
     * @param date
     * @return
     */
    public ArrayList<StockVO> getIncList(StockPool stockPool, String date);

    /**
     * 某版块的跌幅榜（只挑前30只股票即可）
     * @param
     * @param date
     * @return
     */
    public ArrayList<StockVO> getDecList(StockPool stockPool, String date);

    /**
     * 某版块的成交量榜（只挑前30只股票即可）
     * @param
     * @param date
     * @return
     */
    public ArrayList<StockVO> getVolumeList(StockPool stockPool, String date);

    /**
     * 某版块的潜力榜（只挑前30只股票即可）
     * @param
     * @param date
     * @return
     */
    public ArrayList<StockVO> getPotentialList(StockPool stockPool, String date) ;

    /**
     * 获取某板块的饼状图需要的数据
     * @param
     * @param date
     * @return
     */
    public StockPieVO getPlatePieVO(StockPool stockPool, String date);
    /**
     * 获取某一个板块的股票某一段时间的具体信息
     *
     */
    public ArrayList<StockVO> findSockPoolInfo(String start,String end,StockPool stockPool);

}
