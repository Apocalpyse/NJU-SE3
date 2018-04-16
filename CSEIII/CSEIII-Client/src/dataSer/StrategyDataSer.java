package dataSer;

import PO.InputStrategyPO;
import PO.StrategyStockPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface StrategyDataSer extends Remote {
    /**
     * 获取符合要求的相应数据
     * @param inputStrategyPO
     * @return
     * @throws RemoteException
     */
    public ArrayList<StrategyStockPO> findStrategyStockInfo(InputStrategyPO inputStrategyPO) throws RemoteException;

    /**
     * 获取相应时间区间内的基准股票每日的信息
     * 股票名 股票代码分别可能是：
     * 沪深300    000300
     * 中小板指     399005
     * 创业板指    399006
     * @param start
     * @param end
     * @param code
     * @return
     * @throws RemoteException
     */
    public StrategyStockPO findBenchmarkStockInfo(String start ,String end, String code) throws RemoteException;

    /**
     * 获取某交易日days个交易日之前的交易日
     * @param date 日期,返回格式月/日/年
     * @param days 之间间隔交易日天数
     * @return 日期，返回格式月/日/年
     * @throws RemoteException
     */
    public String getPreviousTradeDate(String date, int days) throws RemoteException;

    /**
     * 判断某日期是否是交易日
     * @param date
     * @return 若是，返回true；若不是，返回false
     * @throws RemoteException
     */
    public boolean isTradeDate(String date) throws RemoteException;
}
