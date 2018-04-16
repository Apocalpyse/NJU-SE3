package dataSer;

import PO.strategyPO.InputStrategyPO;
import PO.strategyPO.StrategyStockPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface StrategyDataSer {
    /**
     * 获取符合要求的相应数据
     * 根据开始日期、结束日期和股票的基准判断出这个股票所在板块的开盘日，如果板块开盘而股票没开盘，则不记录这只股票的信息
     * @param inputStrategyPO
     * @return
     */
    public ArrayList<StrategyStockPO> findStrategyStockInfo(InputStrategyPO inputStrategyPO) ;

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
     */
    public StrategyStockPO findBenchmarkStockInfo(String start, String end, String code) ;

    /**
     * 获取某交易日days个交易日之前的交易日
     * @param date 日期,返回格式月/日/年
     * @param days 之间间隔交易日天数
     * @return 日期，返回格式月/日/年
     */
    public String getPreviousTradeDate(String date, int days) ;

    /**
     * 判断某日期是否是交易日
     * @param date
     * @return 若是，返回true；若不是，返回false
     */
    public boolean isTradeDate(String date) ;
}
