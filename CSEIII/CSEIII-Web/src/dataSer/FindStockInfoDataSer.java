package dataSer;

import PO.stockPO.StockPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface FindStockInfoDataSer {
    /**
     * 用户输入开始日期，结束日期和股票编号,返回符合相关要求的Stock的list
     * @param start 开始日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @param end 结束日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @param code 股票编号，即数据中的股票代码
     * @return ArrayList<StockPO>  默认的返回的list按时间从早到晚排列
     * @throws Exception
     */
    public ArrayList<StockPO> findStockInfoByCode(String start, String end, String code);

    /**
     * 用户输入开始日期，结束日期和股票名称,返回符合相关要求的Stock的list
     * @param start 开始日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @param end 结束日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @param name 股票名称
     * @return ArrayList<StockPO> 默认的返回的list按时间从早到晚排列
     * @throws Exception
     */
    public ArrayList<StockPO> findStockInfoByName(String start, String end, String name);

    /**
     * 用户输入日期,返回当天所有的Stock的list
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<StockPO>
     * @throws Exception
     */
    public ArrayList<StockPO> findStockInfoOneday(String date);

    /**
     * 此方法实现拥护搜索功能，用户输入搜索信息，返回所有符合的股票信息
     * @param codeOrName 用户输入的搜索信息
     * @return ArrayList<StockPO>
     * @throws Exception
     */
    public ArrayList<StockPO> findAllStock(String codeOrName);


}
