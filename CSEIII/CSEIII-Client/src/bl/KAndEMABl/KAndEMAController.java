package bl.KAndEMABl;

import VO.*;
import blSer.KAndEMABlSer;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/9.
 */
public class KAndEMAController implements KAndEMABlSer {
    /**
     * 用户输入开始日期，结束日期和股票编号,返回符合相关要求的K线图和均线图需要的数据
     * @param stockvo 股票相关信息
     * @return 符合相关要求的K线图和均线图需要的数据
     * @throws Exception
     */
    @Override
    public ArrayList<StockVO> getDataByCode(InputStockByCodeVO stockvo) throws Exception {
        KAndEMA kAndEMA=new KAndEMA();
        ArrayList<StockVO> result=kAndEMA.getDataByCode(stockvo);
        return result;
    }

    /**
     * 用户输入开始日期，结束日期和股票名称,返回符合相关要求的K线图和均线图需要的数据
     * @param stockvo 股票相关信息
     * @return 符合相关要求的K线图和均线图需要的数据
     * @throws Exception
     */
    @Override
    public ArrayList<StockVO> getDataByName(InputStockByNameVO stockvo) throws Exception {
        KAndEMA kAndEMA=new KAndEMA();
        ArrayList<StockVO> result=kAndEMA.getDataByName(stockvo);
        return result;
    }

    /**
     * 根据股票代码，获取均线图数据
     * @param stockvo
     * @param numOfEMA 几日均线图，如若是10日均线图，则numOfDate=10
     * @return
     * @throws Exception
     */
    @Override
    public EMAVO getEMAByCode(InputStockByCodeVO stockvo, String numOfEMA) throws Exception {
        KAndEMA kAndEMA=new KAndEMA();
        EMAVO result=kAndEMA.getEMAByCode(stockvo,numOfEMA);
        return result;
    }

    /**
     * 根据股票名称，获取均线图数据
     * @param stockvo
     * @param numOfEMA 几日均线图，如若是10日均线图，则numOfDate=10
     * @return
     * @throws Exception
     */
    @Override
    public EMAVO getEMAByName(InputStockByNameVO stockvo, String numOfEMA) throws Exception {
        KAndEMA kAndEMA=new KAndEMA();
        EMAVO result=kAndEMA.getEMAByName(stockvo,numOfEMA);
        return result;
    }

    /**
     * 用户输入开始日期，结束日期和股票编号,返回饼状图需要的数据
     * @param stockvo
     * @return 返回饼状图需要的数据
     * @throws Exception
     */
    @Override
    public PieVO getPieDataByCode(InputStockByCodeVO stockvo) throws Exception {
        Pie pie=new Pie();
        PieVO result=pie.getPieDataByCode(stockvo);
        return result;
    }

    /**
     * 用户输入开始日期，结束日期和股票名称,返回饼状图需要的数据
     * @param stockvo
     * @return 返回饼状图需要的数据
     * @throws Exception
     */
    @Override
    public PieVO getPieDataByName(InputStockByNameVO stockvo) throws Exception {
        Pie pie=new Pie();
        PieVO result=pie.getPieDataByName(stockvo);
        return result;
    }

}
