package dataSer;

import PO.platePO.TotalPlatePO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface FindPlateInfoDataSer {
    /**
     * 获取某一天整体的板块信息
     * @param date
     * @return
     */
    public TotalPlatePO findTotalPlateInfo(String date);

    /**
     * 获取某一个板块的股票某一天的具体信息
     * @param type 只可能是三个板块,type=0时，代表主板；type=1时，代表中小板；type=2时，代表创业板;type=3时，代表上证，type=4时，代表深证，type=5时，代表沪深300，，
     * @param date
     * @return 所有的这个板块股票、并且处于date这一天的股票信息
     */
    public ArrayList<StockPO> findOnePlateInfo(int type, String date);

    /**
     * 获取某一个板块的股票某一天的整体信息
     * To
     */
    public ArrayList<TotalPlatePO> findOnePlateInfo(String start, String end, StockPool stockPool);
    /**
     * 获取某一个板块的股票某一段时间的具体信息
     *
     */
    public ArrayList<StockPO> findSockPoolInfo(String start,String end,StockPool stockPool);
}
