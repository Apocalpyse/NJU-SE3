package dataSer;

import PO.IndustryPO;
import PO.StockPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface IndustryDataSer extends Remote{
	/**
	 * 用户输入日期,返回当天所有的的IndustryPO
	 * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）
	 * @return ArrayList<IndustryPO>
	 * @throws Exception
	 */
	public ArrayList<IndustryPO> findIndustryInfoOneday(String date) throws RemoteException;
	/**
	 * 用户输入日期,行业名称，返回当天行业内所有StockPO
	 * @param date 日期，格式如"4/29/14","4/2/14"（月/日/年）,行业，如金融，房产等
	 * @return ArrayList<StockPO>
	 * @throws Exception
	 */
	public ArrayList<StockPO> findIndustryInfoOneday(String date, String industryName) throws RemoteException;
	/**
	 * 用户输入股票代码或者名字，返回行业名称
	 * @param codeOrName
	 * @return
	 * @throws Exception
	 */
	public String getIndustryName(String codeOrName) throws RemoteException;
	/**
	 * 用户输入日期和行业名称，返回当天行业整体信息
	 * @param date,industryName
	 * @return
	 * @throws Exception
	 */
	public IndustryPO getIndustryInfo(String date,String industryName) throws RemoteException;
	/**
	 *用户输入日期和天数n，返回日期前n个交易日的日期
	 * @param date,industryName
	 * @return
			 * @throws Exception
	 */
	public String getDayAgo(String date,String dayAgo) throws RemoteException;

}
