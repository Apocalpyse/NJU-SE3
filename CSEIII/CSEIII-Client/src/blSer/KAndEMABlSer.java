package blSer;

import VO.*;

import java.util.ArrayList;

public interface KAndEMABlSer {
    /**
     * 用户输入开始日期，结束日期和股票编号,返回符合相关要求的K线图和均线图需要的数据
     * @param stockvo 股票相关信息
     * @return 符合相关要求的K线图和均线图需要的数据
     * @throws Exception
     */
    public ArrayList<StockVO> getDataByCode(InputStockByCodeVO stockvo) throws Exception;

    /**
     * 用户输入开始日期，结束日期和股票名称,返回符合相关要求的K线图和均线图需要的数据
     * @param stockvo 股票相关信息
     * @return 符合相关要求的K线图和均线图需要的数据
     * @throws Exception
     */
    public ArrayList<StockVO> getDataByName(InputStockByNameVO stockvo) throws Exception;

    /**
     * 根据股票代码，获取均线图数据
     * @param stockvo
     * @param numOfEMA 几日均线图，如若是10日均线图，则numOfDate=10
     * @return
     * @throws Exception
     */
    public EMAVO getEMAByCode(InputStockByCodeVO stockvo, String numOfEMA) throws Exception;

    /**
     * 根据股票名称，获取均线图数据
     * @param stockvo
     * @param numOfEMA 几日均线图，如若是10日均线图，则numOfDate=10
     * @return
     * @throws Exception
     */
    public EMAVO getEMAByName(InputStockByNameVO stockvo, String numOfEMA) throws Exception;

    /**
     * 用户输入开始日期，结束日期和股票编号,返回饼状图需要的数据
     * @param stockvo
     * @return 返回饼状图需要的数据
     * @throws Exception
     */
    public PieVO getPieDataByCode(InputStockByCodeVO stockvo) throws Exception;

    /**
     * 用户输入开始日期，结束日期和股票名称,返回饼状图需要的数据
     * @param stockvo
     * @return 返回饼状图需要的数据
     * @throws Exception
     */
    public PieVO getPieDataByName(InputStockByNameVO stockvo) throws Exception;
}
