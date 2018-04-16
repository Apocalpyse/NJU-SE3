package blSer;

import VO.stockVO.InputStockByCodeVO;
import VO.stockVO.InputStockByNameVO;
import VO.stockVO.StockCompareEverydayVO;
import VO.stockVO.StockCompareTotalVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface QuantourCompareBlSer {
    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的总体的比较数据。
     * 数据包括这些股票这段时间的最低值、最高值、涨幅/跌幅、对数收益率方差。
     * @param stockvo 股票编号、开始日期和结束日期
     * @return ArrayList<StockCompareTotalVO> 这些股票的整数据
     * @throws Exception
     */
    public ArrayList<StockCompareTotalVO> quantourTableCompareByCode(ArrayList<InputStockByCodeVO> stockvo) throws Exception;

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的比较图。
     * 图中显示的信息包括这些股票这段时间每天的收盘价和对数收益率。StockCompareEverydayVO里主要有对数收益率
     * @param stockvo 股票编号、开始日期和结束日期
     * @return ArrayList<ArrayList<StockCompareEverydayVO>> 这些股票的每天比较数据，
     * 								ArrayList<StockCompareEverydayVO>是每天的数据，外面的ArrayList是各只股票
     * @throws Exception
     */
    public ArrayList<ArrayList<StockCompareEverydayVO>> quantourChartCompareByCode(ArrayList<InputStockByCodeVO> stockvo) throws Exception;

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的比较数据。
     * 数据包括这些股票这段时间的最低值、最高值、涨幅/跌幅、对数收益率方差。
     * @param stockvo 股票名称、开始日期和结束日期
     * @return ArrayList<StockCompareTotalVO> 这些股票的整数据
     * @throws Exception
     */
    public ArrayList<StockCompareTotalVO> quantourTableCompareByName(ArrayList<InputStockByNameVO> stockvo) throws Exception;

    /**
     * 用户可以查看一段时间内不同的两只或多只股票具体行情表现差异。
     * 输入待比较的两只或多只股票信息后，系统输出这段时间内这些股票之间的比较图。
     * 图中显示的信息包括这些股票这段时间每天的收盘价和对数收益率。
     * @param stockvo 股票名称、开始日期和结束日期
     * @return ArrayList<ArrayList<StockCompareEverydayVO>> 这些股票的每天比较数据，
     * 								ArrayList<StockCompareEverydayVO>是每天的数据，外面的ArrayList是各只股票
     * @throws Exception
     */
    public ArrayList<ArrayList<StockCompareEverydayVO>> quantourChartCompareByName(ArrayList<InputStockByNameVO> stockvo) throws Exception;

}
