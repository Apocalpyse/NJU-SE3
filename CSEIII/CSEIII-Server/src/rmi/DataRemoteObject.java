
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import PO.*;
import data.FindPlateInfo;
import data.FindStockInfo;
import data.Strategy;
import data.User;
import dataSer.FindPlateInfoDataSer;
import dataSer.FindStockInfoDataSer;
import dataSer.StrategyDataSer;
import dataSer.UserDataSer;


public class DataRemoteObject extends UnicastRemoteObject implements
		FindStockInfoDataSer, FindPlateInfoDataSer, StrategyDataSer, UserDataSer{

	private static final long serialVersionUID = 4029039744279087114L;
	private FindStockInfoDataSer findStockInfoDataSer;
	private FindPlateInfoDataSer findPlateInfoDataSer;
	private StrategyDataSer strategyDataSer;
	private UserDataSer userDataSer;


	protected DataRemoteObject() throws RemoteException {// ���췽������ʵ��Service�ľ��巽��
		findStockInfoDataSer=new FindStockInfo();
		findPlateInfoDataSer=new FindPlateInfo();
		strategyDataSer=new Strategy();
		userDataSer=new User();

	}


	//findStockInfoDataSer
	public ArrayList<StockPO> findStockInfoByCode(String start, String end, String code) throws RemoteException {
		// TODO Auto-generated method stub
		return findStockInfoDataSer.findStockInfoByCode(start, end, code);
	}
	public ArrayList<StockPO> findStockInfoByName(String start, String end, String name) throws RemoteException {
		// TODO Auto-generated method stub
		return findStockInfoDataSer.findStockInfoByName(start, end, name);
	}
	public ArrayList<StockPO> findStockInfoOneday(String date) throws RemoteException{
		// TODO Auto-generated method stub
		return findStockInfoDataSer.findStockInfoOneday(date);
	}
	public ArrayList<StockPO> findAllStock(String searchInfo) throws RemoteException{
		// TODO Auto-generated method stub
		return findStockInfoDataSer.findAllStock(searchInfo);
	}

	//findPlateInfoDataSer
	public TotalPlatePO findTotalPlateInfo(String date) throws RemoteException{
		return findPlateInfoDataSer.findTotalPlateInfo(date);
	}
	public ArrayList<StockPO> findOnePlateInfo(int type, String date) throws RemoteException{
		return findPlateInfoDataSer.findOnePlateInfo(type,date);
	}

	//strategyDataSer
	public ArrayList<StrategyStockPO> findStrategyStockInfo(InputStrategyPO inputStrategyPO) throws RemoteException{
		return strategyDataSer.findStrategyStockInfo(inputStrategyPO);
	}
	public StrategyStockPO findBenchmarkStockInfo(String start, String end, String code) throws RemoteException{
		return strategyDataSer.findBenchmarkStockInfo(start,end,code);
	}
	public String getPreviousTradeDate(String date, int days) throws RemoteException{
		return strategyDataSer.getPreviousTradeDate(date,days);
	}
	public boolean isTradeDate(String date) throws RemoteException{
		return strategyDataSer.isTradeDate(date);
	}

	//userDataSer
	public boolean register(UserPO userPO) throws Exception{
		return userDataSer.register(userPO);
	}
	public boolean login(InputLoginPO inputLoginPO) throws Exception{
		return userDataSer.login(inputLoginPO);
	}
	public boolean changeUserInfo(UserPO userPO) throws Exception{
		return userDataSer.changeUserInfo(userPO);
	}
	public UserPO getUserInfo(String account) throws Exception{
		return userDataSer.getUserInfo(account);
	}
	public SelfSelectStockPO getSelfSelectStock(String account) throws Exception{
		return userDataSer.getSelfSelectStock(account);
	}
	public boolean addSelfSelectStock(SelfSelectStockPO selfSelectStockPO) throws Exception{
		return userDataSer.addSelfSelectStock(selfSelectStockPO);
	}
	public boolean addOneSelfSelectStock(String account, String stockCodeOrName) throws Exception{
		return userDataSer.addOneSelfSelectStock(account,stockCodeOrName);
	}
	public boolean deleteSelfSelectStock(SelfSelectStockPO selfSelectStockPO) throws Exception{
		return userDataSer.deleteSelfSelectStock(selfSelectStockPO);
	}
	public boolean isMySelfSelectStock(String stockCodeOrName, String account) throws Exception{
		return userDataSer.isMySelfSelectStock(stockCodeOrName,account);
	}

}
