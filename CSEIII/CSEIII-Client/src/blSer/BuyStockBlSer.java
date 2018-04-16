package blSer;

import VO.BuyVO;
import VO.HoldVO;
import VO.ProfitVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface BuyStockBlSer extends Remote{
	/**
	 * 股票交易
	 * @param buyVOS
	 * @return
	 * @throws
	 */
	public boolean realBuy(String account, BuyVO buyVOS) throws RemoteException;
	/**
	 * 查看历史交易数据
	 * @param account
	 * @return
	 * @throws
	 */
	public ArrayList<BuyVO> getRealBuy(String account, String start, String end) throws RemoteException;
	/**
	 * 查看持有的数据
	 * @param account
	 * @return
	 * @throws
	 */
	public ArrayList<HoldVO> getHoldBuy(String account, String date) throws RemoteException;
	/**
	 * 返回累积收益率
	 * @param account
	 * @return
	 * @throws
	 */
	public ProfitVO getRealBuyProfit(String account, String start, String end) throws RemoteException;
	/**
	 * 最佳策略操作最后盈亏率
	 * @param start,end
	 * @return
	 * @throws
	 */
	public ProfitVO methodProfit(String start,String end) throws RemoteException;
}
