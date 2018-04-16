package dataSer;

import PO.BuyPO;
import PO.HoldPO;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface BuyDataSer extends Remote{
	/**
	 * 股票交易
	 * @param buyPOS
	 * @return
	 * @throws
	 */
	public boolean realBuy(String account, BuyPO buyPOS) throws RemoteException;
	/**
	 * 查看历史交易数据
	 * @param account
	 * @return
	 * @throws
	 */
	public ArrayList<BuyPO> getRealBuy(String account, String start, String end) throws RemoteException;
	/**
	 * 查看持有的数据
	 * @param account
	 * @return
	 * @throws
	 */
	public ArrayList<HoldPO> getHoldBuy(String account, String date) throws RemoteException;

}
