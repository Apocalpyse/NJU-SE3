package bl.buyStockBl;

import PO.stockPO.StockPool;
import PO.userPO.BuyPO;
import PO.userPO.HoldPO;
import VO.stockVO.HoldVO;
import VO.userVO.BuyVO;
import VO.userVO.ProfitVO;
import bl.stockBl.Stock;
import data.userData.BuyStockData;
import dataSer.BuyStockDataSer;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/8.
 */
public class BuyStock {
	public static void main(String[] args){
		System.out.println(123);
	}
	/**
	 * 股票交易
	 * @param buyVOS
	 * @return
	 * @throws
	 */
	public boolean realBuy(BuyVO buyVOS) throws Exception{
		BuyStockDataSer buyStockDataSer=new BuyStockData();
		return buyStockDataSer.realBuy(new BuyPO(buyVOS.getAccount(),buyVOS.getDate(),buyVOS.getCode(),buyVOS.getVolume(),buyVOS.isBuy()));
	}
	/**
	 * 查看历史交易数据
	 * @param account
	 * @return
	 * @throws
	 */
	public ArrayList<BuyVO> getRealBuy(String account, String start, String end) throws Exception{
		BuyStockDataSer buyStockDataSer=new BuyStockData();
		ArrayList<BuyPO> arrayList=buyStockDataSer.getRealBuy(account,start,end);
		ArrayList<BuyVO> arrayList1=new ArrayList<BuyVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new BuyVO(arrayList.get(i).getAccount(),arrayList.get(i).getDate(),arrayList.get(i).getCode(),
					arrayList.get(i).getVolume(),arrayList.get(i).isBuy()));
		}
		return arrayList1;
	}
	/**
	 * 查看持有的数据
	 * @param account
	 * @return
	 * @throws
	 */
	public HoldVO getHoldBuy(String account, String date) throws Exception{
		BuyStockDataSer buyStockDataSer=new BuyStockData();
		HoldPO holdPO=buyStockDataSer.getHoldBuy(account,date);
		return new HoldVO(holdPO.getAccount(),holdPO.getDate(),holdPO.getTotalMoney(),holdPO.getHoldCode(),holdPO.getHoldMoney(),holdPO.getHoldCopies());

	}
	/**
	 * 返回累积收益率
	 * @param account
	 * @return
	 * @throws
	 */
	public ProfitVO getRealBuyProfit(String account, String start, String end) throws Exception{
		BuyStockDataSer buyStockDataSer=new BuyStockData();
		BuyStock buyStock=new BuyStock();
		ArrayList<HoldVO> arrayList=new ArrayList<HoldVO>();
		arrayList.add(buyStock.getHoldBuy(account,start));
		while (!start.equals(end)){
			start=buyStockDataSer.getNextDay(start);
			arrayList.add(buyStock.getHoldBuy(account,start));
		}
		String one=(Double.parseDouble(arrayList.get(arrayList.size()-1).getTotalMoney())-Double.parseDouble(arrayList.get(0).getTotalMoney()))/
				Double.parseDouble(arrayList.get(0).getTotalMoney())+"";
		ArrayList<String> two=new ArrayList<String>(),three=new ArrayList<String>();
		for(int i=0;i<arrayList.size();i++){
			two.add(arrayList.get(i).getDate());
		}
		three.add("0");
		for(int i=1;i<arrayList.size();i++){
			three.add((Double.parseDouble(arrayList.get(i).getTotalMoney())-Double.parseDouble(arrayList.get(0).getTotalMoney()))/
					Double.parseDouble(arrayList.get(0).getTotalMoney())+"");
		}
		return new ProfitVO(one,two,three);

	}
	/**
	 * 最佳策略操作最后盈亏率
	 * @param start,end
	 * @return
	 * @throws
	 */
	public ProfitVO methodProfit(String start, String end) throws Exception{
		Stock stock=new Stock();
		BuyStockDataSer buyStockDataSer=new BuyStockData();
		ArrayList<String> three=stock.getMaxProfit(start,end, StockPool.ALL,new ArrayList<String>());
		ArrayList<String> dates=new ArrayList<String>();
		dates.add(start);
		while (!start.equals(end)){
			start=buyStockDataSer.getNextDay(start);
			dates.add(start);
		}
		return new ProfitVO(three.get(three.size()-1),dates,three);
	}
}
