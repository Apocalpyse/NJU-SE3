package blSer;

import VO.stockVO.*;

import java.util.ArrayList;

/**
 * Created by A on 2017/6/2.
 */
public interface AddBlSer1 {
    /**
     * 根据股票代码，获取股票所属公司信息
     * @param code
     * @return
     */
    public CompanyVO getCompanyInfo(String code);

    /**
     * 根据股票代码，获取股票K线图的简易信息
     * @param code
     * @return
     */
    public KChartVO getSimpleKChartInfo(String code);

    /**
     * 根据行业名称与日期获取当天所有重大事项简单版的list
     * @param industryName
     * @param date 格式同迭代二的日期格式，如“2/10/17”，代表2017年2月10日
     * @return
     */
    public ArrayList<SimpleMainEventVO> getSimpleEventInfo(String industryName,String date);

    /**
     * 根据重大事项标题以及时间获取重大事项详细信息
     * @param title
     * @param time 指具体的时间，格式与从SimpleMainEventVO中获取的time格式相同
     * @return
     */
    public MainEventVO getDetialMainEventInfo(String title,String time);

    /**
     * 根据行业名称与日期获取当天所有行情走势简单版的list
     * @param industryName
     * @param date 格式同迭代二的日期格式，如“2/10/17”，代表2017年2月10日
     * @return
     */
    public ArrayList<SimpleTrendVO> getSimpleTrendInfo(String industryName,String date);

    /**
     * 根据行情走势标题以及时间获取行情走势详细信息
     * @param title
     * @param time 指具体的时间，格式与从SimpleTrendVO中获取的time格式相同
     * @return
     */
    public TrendVO getDetialTrendInfo(String title,String time);

    /**
     * 根据行业名称与日期获取当天所有投资新闻简单版的list
     * @param industryName
     * @param date 格式同迭代二的日期格式，如“2/10/17”，代表2017年2月10日
     * @return
     */
    public ArrayList<SimpleInvestNewsVO> getSimpleInvestInfo(String industryName,String date);

    /**
     * 根据行情走势标题以及时间获取投资新闻详细信息
     * @param title
     * @param time 指具体的时间，格式与从InvestNewsVO中获取的time格式相同
     * @return
     */
    public InvestNewsVO getDetialInvestNewsInfo(String title,String time);

}
