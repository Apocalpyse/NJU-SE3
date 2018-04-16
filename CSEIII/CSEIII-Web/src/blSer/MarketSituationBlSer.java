package blSer;

import VO.marketVO.MarketSituationVO;
import VO.stockVO.StockVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface MarketSituationBlSer {
    /**
     * 系统可以显示用户查询日期或者某一日期的股票交易市场行情相关数据。
     * 相关数据包括：当日总交易量、涨停股票数、跌停股票数、涨幅超过5%的股票数，
     * 跌幅超过5%的股票数，开盘‐收盘大于5%*上一个交易日收盘价的股票个数、
     * 开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数。等信息
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return stockVO 当日股票市场行情相关数据
     * @throws Exception
     */
    public MarketSituationVO getMarketSituation(String date) throws Exception;

    /**
     * 获取涨幅榜的数据
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockListVO> 涨幅榜
     * @throws Exception
     */
    public ArrayList<StockVO> getIncreaseList(String date) throws Exception;

    /**
     * 获取跌幅榜的数据
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockListVO> 跌幅榜
     * @throws Exception
     */
    public ArrayList<StockVO> getDecreaseList(String date) throws Exception;

    /**
     * 获取成交量从高到低的排行榜的数据
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockListVO> 成交量从高到低的排行榜
     * @throws Exception
     */
    public ArrayList<StockVO> getVolumeList(String date) throws Exception;

    /**
     * 获取潜力从高到低的股票数据
     * @param date
     * @return
     * @throws Exception
     */
    public ArrayList<StockVO> getPotentialList(String date) throws Exception;
}
