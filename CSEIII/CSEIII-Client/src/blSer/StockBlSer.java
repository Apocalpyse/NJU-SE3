package blSer;

import PO.StockPool;
import VO.GoalVO;
import VO.TwentyStockVO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface StockBlSer {
	/**
	 * 此方法实现输入日期，股票代码或者编号，返回得分
	 * @param date,codeOrName
	 * @return String 雷达图5个+总评
	 * @throws Exception
	 */
	public GoalVO  getStockGoalOne(String date,String codeOrName) throws Exception;
	/**
	 * 此方法实现输入起止日期，股票代码或者编号，返回得分
	 * @param start,end,codeOrName
	 * @return String 得分
	 * @throws Exception
	 */
	public GoalVO getStockGoal(String start, String end, String codeOrName) throws Exception;

	//缺少获取公司简介的方法，缺少获取这支股票讨论区数据的方法

	/**
	 * 此方法实现输入起止日期，用户自选股、某板块、所有股票，返回得分前百分之二十的股票
	 * @param start,end,stockPool,stockCodeOrName
	 * @return ArrayList<TwentyStockVO>
	 * @throws Exception
	 */
	public TwentyStockVO getStockGoalTwenty(String start, String end, StockPool stockPool,ArrayList<String> codeOrName) throws Exception;
	/**
	 * 此方法实现输入起止日期，某行业，返回得分前百分之二十的股票
	 * @param start,end,industry(行业)
	 * @return ArrayList<TwentyStockVO>
	 * @throws Exception
	 */
	public TwentyStockVO getStockGoalTwenty(String start, String end,String industry) throws Exception;
	/**
	 * 此方法实现输入起止日期，用户自选股、某板块、所有股票，返回采用预测的最佳收益率
	 * @param start,end,codeOrName
	 * @return 累积收益率
	 * @throws Exception
	 */
	public ArrayList<String> getMaxProfit(String start, String end,StockPool stockPool,ArrayList<String> codeOrName) throws Exception;
	/**
	 * 此方法实现输入起止日期，行业，返回采用预测的最佳收益率
	 * @param start,end,industry
	 * @return 累积收益率
	 * @throws Exception
	 */
	public ArrayList<String> getMaxProfit(String start, String end,String industry) throws Exception;

}
