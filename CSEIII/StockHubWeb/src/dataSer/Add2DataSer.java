package dataSer;

import PO.marketPO.NewsPO;
import PO.stockPO.EvaluateStockS;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.marketVO.NewsVO;
import blSer.StockPoolBl;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/6.
 */
public interface Add2DataSer {

	/**
	 * 根据Classfy获取新闻，并返回新闻
	 * @param classify
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NewsPO> getNewsCl(String classify) throws Exception;

	/**
	 * 根据Classfy获取新闻，并返回新闻
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EvaluateStockS> getIndustryStockEva(String industryName, String start, String end) throws Exception;

	public ArrayList<EvaluateStockS> getPlateStockEva(StockPool stockPool, String start, String end) throws Exception;
}
