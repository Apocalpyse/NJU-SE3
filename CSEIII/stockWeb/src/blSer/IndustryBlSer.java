package blSer;

import VO.IndustryVO;
import VO.StockVO;
import VO.StockPieVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface IndustryBlSer {
	/**
	 * 用户输入日期,返回当天所有的的IndustryO
	 * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
	 * @return ArrayList<IndustryPO>
	 * @throws Exception
	 */
	public ArrayList<IndustryVO> findIndustryInfoOneday(String date) throws RemoteException;

	/**
	 * 用户输入日期,返回各行业的涨幅榜
	 * @param date
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<IndustryVO> findTotalIndustryMaxInRate(String date) throws RemoteException;

	/**
	 * 用户输入日期，返回各行业的跌幅榜
	 * @param date
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<IndustryVO> findTotalIndustryMaxDeRate(String date) throws RemoteException;

	/**
	 * 用户输入日期，返回各行业的交易量排行榜
	 * @param date
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<IndustryVO> findTotalIndustryMaxInVolume(String date) throws RemoteException;

	/**
	 * 用户输入日期，返回各行业的潜力排行榜
	 * @param date
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<IndustryVO> findTotalIndustryPotential(String date) throws RemoteException;

	/**
	 * 用户输入日期，返回总体股票的饼状图需要的数据
	 * @param date
	 * @return
	 * @throws RemoteException
	 */
	public StockPieVO findTotalIndustryPieVO(String date) throws RemoteException;

	/**
	 * 用户输入日期,行业，或者板块名称，返回值按照涨幅从大到小继续排序
	 * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）,行业，如金融，房产等
	 * @return ArrayList<StockVO>
	 * @throws Exception
	 */
	public ArrayList<StockVO> findIndustryMaxInRate(String date, String industryName) throws RemoteException;
	/**
	 * 用户输入日期,行业，或者板块名称，返回值按照涨幅从小到大继续排序
	 * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）,行业，如金融，房产等
	 * @return ArrayList<StockPO>
	 * @throws Exception
	 */
	public ArrayList<StockVO> findIndustryMaxDeRate(String date, String industryName) throws RemoteException;
	/**
	 * 用户输入日期,行业或者板块名称，返回值按照总交易量大小继续排序
	 * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）,行业，如金融，房产等
	 * @return ArrayList<StockPO>
	 * @throws Exception
	 */
	public ArrayList<StockVO> findIndustryMaxInVolume(String date,  String industryName) throws RemoteException;

	/**
	 * 获取行业内的潜力榜
	 * @param date
	 * @param industryName
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<StockVO> findIndustryPotential(String date,  String industryName) throws RemoteException;

	/**
	 * 获取某行业的饼状图数据
	 * @param date
	 * @param industryName
	 * @return
	 */
	public StockPieVO findIndustryPieVO(String date,  String industryName) throws RemoteException;
}
