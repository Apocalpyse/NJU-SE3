package dataSer;

import PO.industryPO.IndustryPO;
import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface IndustryDataSer {
    /**
     * 用户输入日期,返回当天所有的的IndustryPO
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
     * @return ArrayList<IndustryPO>
     * @throws Exception
     */
    public ArrayList<IndustryPO> findIndustryInfoOneday(String date);
    /**
     * 用户输入日期,行业名称，返回当天行业内所有StockPO
     * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）,行业，如金融，房产等
     * @return ArrayList<StockPO>
     * @throws Exception
     */
    public ArrayList<StockPO> findIndustryInfoOneday(String date, String industryName);
    /**
     * 用户输入股票代码或者名字，返回行业名称
     * @param codeOrName
     * @return
     * @throws Exception
     */
    public String getIndustryName(String codeOrName) ;
    /**
     * 用户输入日期和行业名称，返回当天行业整体信息
     * @param date,industryName
     * @return
     * @throws Exception
     */
    public IndustryPO getIndustryInfo(String date, String industryName);
    /**
     * 用户输入输入起止日期，用户自选股、某板块、所有股票，返回各个股票对应的ArrstockPO
     * @param start
     * @return
     * @throws Exception
     */
    public ArrStockPO getPoolInfo(String start, String end,StockPool stockPool) ;
    /**
     * * 用户输入输入起止日期，行业，返回各个股票对应的
     * @param start
     * @return
     * @throws Exception
     */
    public ArrStockPO getIndustryInfo(String start, String end, String industry);
    /**
     * * 用户输入输入股票代码，返回名字
     * @param code
     * @return
     * @throws Exception
     */
    public String getName(String code) ;
    /**
     * * 用户输入输入股票名字，返回代码
     * @param name
     * @return
     * @throws Exception
     */
    public String getCode(String name) ;


}
