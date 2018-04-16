package bl.add1;

import PO.stockPO.*;
import VO.stockVO.*;
import data.Add1Data.Add1Data;
import data.industryData.IndustryData;
import data.stockData.FindStockInfoData;
import data.strategyData.StrategyData;
import dataSer.Add1DataSer;
import dataSer.FindStockInfoDataSer;
import dataSer.IndustryDataSer;
import dataSer.StrategyDataSer;


import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/8.
 */
public class AddOne {
	public static void main(String[] args){
		AddOne addOne=new AddOne();
		ArrayList<SimpleMainEventVO> arrayList=addOne.getSimpleEventInfo("银行","6/8/17");
for(int i=0;i<arrayList.size();i++){
	System.out.println(arrayList.get(i).getTitle());
}
		MainEventVO mainEventVO=addOne.getDetialMainEventInfo("英国大选重挫英镑 李嘉诚旗下公司股价下跌","6/8/17");
		System.out.println(mainEventVO.getContent());

	}

	public CompanyVO getCompanyInfo(String code){
		Add1DataSer add1DataSer=new Add1Data();
		CompanyPO companyPO=add1DataSer.getCompanyInfo(code);
		return new CompanyVO(companyPO.getCode(),companyPO.getStockName(),companyPO.getIndustry(),
				companyPO.getArea(),companyPO.getTotalassets(),companyPO.getLiquidassets(),
				companyPO.getFixedassets(),companyPO.getUptime(),companyPO.getHolders());
	}

	/**
	 * 根据股票代码，获取股票K线图的简易信息
	 * @param code
	 * @return
	 */
	public KChartVO getSimpleKChartInfo(String code, String start, String end){
		FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
		ArrayList<StockPO> arrayList=findStockInfoDataSer.findStockInfoByCode(start,end,code);
		ArrayList<String> one=new ArrayList<String>();
		ArrayList<Double> two=new ArrayList<Double>(),three=new ArrayList<Double>();
		for(int i=0;i<arrayList.size();i++){
			one.add(arrayList.get(i).getDate());
		}
		for(int i=0;i<arrayList.size();i++){
			two.add(Double.parseDouble(arrayList.get(i).getAdjClose()));
		}
		for(int i=0;i<arrayList.size();i++){
			three.add(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease()));
		}
		return new KChartVO(code,arrayList.get(0).getName(),arrayList.get(arrayList.size()-1).getAdjClose(),
				arrayList.get(arrayList.size()-1).getIncreaseOrDecrease(),one,two,three);
	}

	/**
	 * 根据行业名称与日期获取当天所有重大事项简单版的list
	 * @param industryName
	 * @param date 格式同迭代二的日期格式，如“2/10/17”，代表2017年2月10日
	 * @return
	 */
	public ArrayList<SimpleMainEventVO> getSimpleEventInfo(String industryName, String date){
		Add1DataSer add1DataSer=new Add1Data();
		ArrayList<SimpleMainEventPO> arrayList=add1DataSer.getSimpleEventInfo(industryName,date);
		ArrayList<SimpleMainEventVO> arrayList1=new ArrayList<SimpleMainEventVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleMainEventVO(arrayList.get(i).getIndustry(),arrayList.get(i).getTitle(),
					arrayList.get(i).getTime()));
		}
		return arrayList1;

	}

	/**
	 * 根据重大事项标题以及时间获取重大事项详细信息
	 * @param title
	 * @param time 指具体的时间，格式与从SimpleMainEventVO中获取的time格式相同
	 * @return
	 */
	public MainEventVO getDetialMainEventInfo(String title, String time){
		Add1DataSer add1DataSer=new Add1Data();
		MainEventPO mainEventPO=add1DataSer.getDetialMainEventInfo(title,time);
		return new MainEventVO(mainEventPO.getIndustry(),mainEventPO.getTitle(),
				mainEventPO.getContent(),mainEventPO.getTime());
	}

	/**
	 * 根据行业名称与日期获取当天所有行情走势简单版的list
	 * @param industryName
	 * @param date 格式同迭代二的日期格式，如“2/10/17”，代表2017年2月10日
	 * @return
	 */
	public ArrayList<SimpleTrendVO> getSimpleTrendInfo(String industryName, String date){
		Add1DataSer add1DataSer=new Add1Data();
		ArrayList<SimpleTrendPO> arrayList=add1DataSer.getSimpleTrendInfo(industryName,date);
		ArrayList<SimpleTrendVO> arrayList1=new ArrayList<SimpleTrendVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleTrendVO(arrayList.get(i).getIndustry(),
					arrayList.get(i).getTitle(),arrayList.get(i).getTime()));
		}
		return arrayList1;
	}

	/**
	 * 根据行情走势标题以及时间获取行情走势详细信息
	 * @param title
	 * @param time 指具体的时间，格式与从SimpleTrendVO中获取的time格式相同
	 * @return
	 */
	public TrendVO getDetialTrendInfo(String title, String time){
		Add1DataSer add1DataSer=new Add1Data();
		TrendPO trendPO=add1DataSer.getDetialTrendInfo(title,time);
		return new TrendVO(trendPO.getIndustry(),trendPO.getTitle(),trendPO.getContent(),
				trendPO.getTime());
	}

	/**
	 * 根据行业名称与日期获取当天所有投资新闻简单版的list
	 * @param industryName
	 * @param date 格式同迭代二的日期格式，如“2/10/17”，代表2017年2月10日
	 * @return
	 */
	public ArrayList<SimpleInvestNewsVO> getSimpleInvestInfo(String industryName, String date){
		Add1DataSer add1DataSer=new Add1Data();
		ArrayList<SimpleInvestNewsPO> arrayList=add1DataSer.getSimpleInvestInfo(industryName,date);
		ArrayList<SimpleInvestNewsVO> arrayList1=new ArrayList<SimpleInvestNewsVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleInvestNewsVO(arrayList.get(i).getIndustry(),
					arrayList.get(i).getTitle(),arrayList.get(i).getTime()));
		}
		return arrayList1;
	}

	/**
	 * 根据行情走势标题以及时间获取投资新闻详细信息
	 * @param title
	 * @param time 指具体的时间，格式与从InvestNewsVO中获取的time格式相同
	 * @return
	 */
	public InvestNewsVO getDetialInvestNewsInfo(String title, String time){
		Add1DataSer add1DataSer=new Add1Data();
		InvestNewsPO investNewsPO=add1DataSer.getDetialInvestNewsInfo(title,time);
		return new InvestNewsVO(investNewsPO.getIndustry(),investNewsPO.getTitle(),
				investNewsPO.getContent(),investNewsPO.getTime());
	}
	/**
	 * 获取所有的行业名称
	 * @return 所有行业名称的list
	 */
	public ArrayList<String> getAllIndustryName(){
		Add1DataSer add1DataSer=new Add1Data();
		return add1DataSer.getAllIndustryName();
	}

	/**
	 * 根据股票代码或名称，判断股票是否存在
	 * @param stockCodeOrName
	 * @return 若存在，true；不存在，false
	 */
	public boolean isStockExist(String stockCodeOrName){
		Add1DataSer add1DataSer=new Add1Data();
		return add1DataSer.isStockExist(stockCodeOrName);
	}
	/**
	 * 根据行业名称，获得行业所有的股票名称
	 * @param industryName
	 * @return
	 */
	public ArrayList<String> getIndustryAllStock(String industryName){
		IndustryDataSer industryDataSer=new IndustryData();
		ArrStockPO arrStockPO=industryDataSer.getIndustryInfo(industryName);
		ArrayList<String> arrayList=new ArrayList<String>();
		for(int i=0;i<arrStockPO.getName().size();i++){
			arrayList.add(arrStockPO.getName().get(i));
		}
		return arrayList;
	}
	/**
	 * 判断某日期是否是交易日
	 * @param date
	 * @return 若是，返回true；若不是，返回false
	 */
	public boolean isTradeDate(String date) {
		StrategyDataSer strategyDataSer=new StrategyData();
		return strategyDataSer.isTradeDate(date);
	}
}
