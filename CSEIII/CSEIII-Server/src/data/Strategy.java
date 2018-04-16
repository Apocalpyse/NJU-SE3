package data;

import PO.InputStrategyPO;
import PO.SelfSelectStockPO;
import PO.StockPool;
import PO.StrategyStockPO;
import dataSer.StrategyDataSer;

import java.io.*;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.nio.charset.Charset.forName;

/**
 * Created by chenjin on 2017/4/11.
 */
public class Strategy implements StrategyDataSer {
	public static void main(String[] args){
		Strategy strategy=new Strategy();
		ArrayList<StrategyStockPO> strategyStockPO = null;
		
		
		User user=new User();
		SelfSelectStockPO selfSelectStockPO = null;
		try {
			selfSelectStockPO=user.getSelfSelectStock("user");
		} catch (Exception e) {
			e.printStackTrace();
		}
ArrayList<String> arrayList0=selfSelectStockPO.getCode();
		try {
			strategyStockPO=strategy.findStrategyStockInfo(new InputStrategyPO("4/1/13","4/1/14", StockPool.SELECTPLATE,arrayList0));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

			System.out.println(strategyStockPO.size());


		/*try {
			System.out.println(strategy.getPreviousTradeDate("2/14/05",2));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		*/

	}
	@Override
	public ArrayList<StrategyStockPO> findStrategyStockInfo(InputStrategyPO inputStrategyPO) throws RemoteException {
		int dayOfNumber=0;
		Strategy strategy=new Strategy();
		CsvReader r = null;
		String address="src/resourceStandard/000300.csv";
		try {
			r = new CsvReader(address, ',', forName("GBK"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//
		Date st=null,en=null,da=null;
		try {
			st=sdf.parse(strategy.convertData(inputStrategyPO.getStart()));
			en=sdf.parse(strategy.convertData(inputStrategyPO.getEnd()));
			da=sdf.parse("2014-4-29");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String string=inputStrategyPO.getEnd();
		if(en.after(da)){
			en=da;
			string="4/29/14";
		}

		try {
			r.readRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (r.readRecord()){
				String s=r.getRawRecord();
				String[] arr=s.split(",");
				Date date=null;
				try {
					date=sdf.parse(arr[0]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(date.after(st)&&date.before(en)||date.equals(st)||date.equals(en)){
					dayOfNumber=dayOfNumber+1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String st1=inputStrategyPO.getStart();
		String en1=string;
		if(!strategy.isTradeDate(st1)||!strategy.isTradeDate(en1)){
			while (!strategy.isTradeDate(st1)){
				st1=strategy.convertData(st1);
				st1=strategy.getSpecifiedDayAfter(st1);
				st1=strategy.conDate(st1);
				if(strategy.isTradeDate(st1)){
					break;
				}
			}
			while (!strategy.isTradeDate(en1)){
				en1=strategy.convertData(en1);
				en1=strategy.getSpecifiedDayBefore(en1);
				en1=strategy.conDate(en1);
				if(strategy.isTradeDate(en1)){
					break;
				}
			}
		}



		//以上判断两个日期间的开盘天数
		ArrayList<StrategyStockPO> arrayList=new ArrayList<StrategyStockPO>();
		if(inputStrategyPO.getStockPool().equals(StockPool.ALL)){
			CsvReader csvReader = null;
			String path="src/resourceTime/股票历史数据.csv";
			try {
				csvReader = new CsvReader(path, ',', forName("UTF-8"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				while (csvReader.readRecord()){
					String csvString=csvReader.getRawRecord();
					String[] csvArr=csvString.split("\t");
					if(csvArr[1].equals(st1)){
						ArrayList<String> arrayList1=new ArrayList<String>();
						ArrayList<String> arrayList2=new ArrayList<String>();
						arrayList1.add(csvArr[1]);
						arrayList2.add(csvArr[7]);
						while (csvReader.readRecord()){
							String csvString1=csvReader.getRawRecord();
							String[] csvArr1=csvString1.split("\t");
							if(!csvArr1[6].equals("0")){
								arrayList1.add(csvArr1[1]);
								arrayList2.add(csvArr1[7]);
								if(csvArr1[1].equals(en1)){
									if(arrayList1.size()==dayOfNumber){
										arrayList.add(new StrategyStockPO(csvArr1[8],csvArr1[9],arrayList1,arrayList2));
									}
									break;
								}
							}
							else if(arrayList1.size()>dayOfNumber){
								break;
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(inputStrategyPO.getStockPool().equals(StockPool.MAINPLATE)){
			CsvReader csvReader = null;
			String path="src/resourceTime/股票历史数据.csv";
			try {
				csvReader = new CsvReader(path, ',', forName("UTF-8"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				while (csvReader.readRecord()){
					String csvString=csvReader.getRawRecord();
					String[] csvArr=csvString.split("\t");
					if(csvArr[1].equals(st1)&&csvArr[13].equals("主板")){
						ArrayList<String> arrayList1=new ArrayList<String>();
						ArrayList<String> arrayList2=new ArrayList<String>();
						arrayList1.add(csvArr[1]);
						arrayList2.add(csvArr[7]);
						while (csvReader.readRecord()){
							String csvString1=csvReader.getRawRecord();
							String[] csvArr1=csvString1.split("\t");
							if(!csvArr1[6].equals("0")){
								arrayList1.add(csvArr1[1]);
								arrayList2.add(csvArr1[7]);
								if(csvArr1[1].equals(en1)){
									if(arrayList1.size()==dayOfNumber){
										arrayList.add(new StrategyStockPO(csvArr1[8],csvArr1[9],arrayList1,arrayList2));
									}
									break;
								}

							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(inputStrategyPO.getStockPool().equals(StockPool.SMALLMIDDLEPLATE)){
			CsvReader csvReader = null;
			String path="src/resourceTime/股票历史数据.csv";
			try {
				csvReader = new CsvReader(path, ',', forName("UTF-8"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				while (csvReader.readRecord()){
					String csvString=csvReader.getRawRecord();
					String[] csvArr=csvString.split("\t");
					if(csvArr[1].equals(st1)&&csvArr[13].equals("中小板")){
						ArrayList<String> arrayList1=new ArrayList<String>();
						ArrayList<String> arrayList2=new ArrayList<String>();
						arrayList1.add(csvArr[1]);
						arrayList2.add(csvArr[7]);
						while (csvReader.readRecord()){
							String csvString1=csvReader.getRawRecord();
							String[] csvArr1=csvString1.split("\t");
							if(!csvArr1[6].equals("0")){
								arrayList1.add(csvArr1[1]);
								arrayList2.add(csvArr1[7]);
								if(csvArr1[1].equals(en1)&&csvArr1[13].equals("中小板")){
									if(arrayList1.size()==dayOfNumber){
										arrayList.add(new StrategyStockPO(csvArr1[8],csvArr1[9],arrayList1,arrayList2));
									}
									break;
								}
							}
							else if(arrayList1.size()>dayOfNumber){
								break;
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if(inputStrategyPO.getStockPool().equals(StockPool.STARTUPPLATE)) {
			CsvReader csvReader = null;
			String path = "src/resourceTime/股票历史数据.csv";
			try {
				csvReader = new CsvReader(path, ',', forName("UTF-8"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				while (csvReader.readRecord()) {
					String csvString = csvReader.getRawRecord();
					String[] csvArr = csvString.split("\t");
					if (csvArr[1].equals(st1) && csvArr[13].equals("创业板")) {
						ArrayList<String> arrayList1 = new ArrayList<String>();
						ArrayList<String> arrayList2 = new ArrayList<String>();
						arrayList1.add(csvArr[1]);
						arrayList2.add(csvArr[7]);
						while (csvReader.readRecord()) {
							String csvString1 = csvReader.getRawRecord();
							String[] csvArr1 = csvString1.split("\t");
							if(!csvArr1[6].equals("0")){
								arrayList1.add(csvArr1[1]);
								arrayList2.add(csvArr1[7]);
								if (csvArr1[1].equals(en1) && csvArr1[13].equals("创业板")) {
									if (arrayList1.size() == dayOfNumber) {
										arrayList.add(new StrategyStockPO(csvArr1[8], csvArr1[9], arrayList1, arrayList2));
									}
									break;
								}
							}
							else if(arrayList1.size()>dayOfNumber){
								break;
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if(inputStrategyPO.getStockPool().equals(StockPool.SELECTPLATE)){
			Strategy strategy1=new Strategy();
			for(int i=0;i<inputStrategyPO.getStockNameOrCode().size();i++){
				CsvReader csvReader = null;
				String path;
				if(strategy1.isInt(inputStrategyPO.getStockNameOrCode().get(i))){
					path = "src/resourceCode/"+inputStrategyPO.getStockNameOrCode().get(i)+".csv";
				}
				else{
					String s1=inputStrategyPO.getStockNameOrCode().get(i).replace("*","1");
					String s2=s1.replace("A","Ａ");
					path = "src/resourceName/mainPlate/"+s2+".csv";
					File file=new File(path);
					if (!file.exists()){
						path = "src/resourceName/smallMiddlePlate/"+s2+".csv";
						File file1=new File(path);
						if(!file1.exists()){
							path = "src/resourceName/startupPlate/"+s2+".csv";
						}
					}
				}
				try {
					csvReader = new CsvReader(path, ',', forName("UTF-8"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				try {
					while (csvReader.readRecord()){
						String csvString = csvReader.getRawRecord();
						String[] csvArr = csvString.split("\t");
						if (csvArr[1].equals(st1)) {
							ArrayList<String> arrayList1 = new ArrayList<String>();
							ArrayList<String> arrayList2 = new ArrayList<String>();
							arrayList1.add(csvArr[1]);
							arrayList2.add(csvArr[7]);
							while (csvReader.readRecord()) {
								String csvString1 = csvReader.getRawRecord();
								String[] csvArr1 = csvString1.split("\t");
								if(!csvArr1[6].equals("0")){
									arrayList1.add(csvArr1[1]);
									arrayList2.add(csvArr1[7]);
									if (csvArr1[1].equals(en1)) {
										if (arrayList1.size() == dayOfNumber) {
											arrayList.add(new StrategyStockPO(csvArr1[8], csvArr1[9], arrayList1, arrayList2));
										}
										break;
									}
								}
								else if(arrayList1.size()>dayOfNumber){
									break;
								}

							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}


		return arrayList;
	}

	@Override
	public StrategyStockPO findBenchmarkStockInfo(String start, String end, String code) throws RemoteException {
		Strategy strategy=new Strategy();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//
		Date st=null,en=null;
		try {
			st=sdf.parse(strategy.convertData(start));
			en=sdf.parse(strategy.convertData(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String s0 = null;
		ArrayList<String> arrayList1=new ArrayList<String>();
		ArrayList<String> arrayList2=new ArrayList<String>();
		CsvReader r = null;
		String address="src/resourceStandard/"+code+".csv";
		try {
			r = new CsvReader(address, ',', forName("GBK"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			r.readRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (r.readRecord()){
				String s=r.getRawRecord();
				String[] arr=s.split(",");
				s0=arr[2];
				Date date=null;
				try {
					date=sdf.parse(arr[0]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(date.after(st)&&date.before(en)||date.equals(st)||date.equals(en)){
					arrayList1.add(arr[0]);
					arrayList2.add(arr[3]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<String> arrayList3=new ArrayList<String>();
		ArrayList<String> arrayList4=new ArrayList<String>();
		for(int i=arrayList1.size()-1;i>=0;i--){
			arrayList3.add(arrayList1.get(i));
		}
		for(int i=arrayList2.size()-1;i>=0;i--){
			arrayList4.add(arrayList2.get(i));
		}

		return new StrategyStockPO(code,s0,arrayList3,arrayList4);

	}

	@Override
	public String getPreviousTradeDate(String date, int days) throws RemoteException {
		Strategy strategy=new Strategy();
		String da=strategy.convertData(date);
		String result = null;
		int i=0;
		CsvReader r = null;
		String address="src/resourceStandard/000300.csv";
		try {
			r = new CsvReader(address, ',', forName("GBK"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//
		Date dday=null,dda=null;
		try {
			dday=sdf.parse(da);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			r.readRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (r.readRecord()){
				boolean sign=false;
				String s=r.getRawRecord();
				String[] arr=s.split(",");
				try {
					dda=sdf.parse(arr[0]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(arr[0].equals(da)){
					sign=true;
					while (r.readRecord()){
						String s1=r.getRawRecord();
						String[] arr1=s1.split(",");
						if(i==days){
							result=arr1[0];
							break;
						}
						i=i+1;
					}
				}
				else if(dday.after(dda)){
					sign=true;
					i=i+1;
					while (r.readRecord()){
						String s1=r.getRawRecord();
						String[] arr1=s1.split(",");
						if(i==days){
							result=arr1[0];
							break;
						}
						i=i+1;
					}
				}
				if(sign){
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] strings=result.split("-");
		String month,day,year;
		if(Integer.parseInt(strings[1])<10){
			String[] s1=strings[1].split("");
			month=s1[1]+"";
		}
		else {
			month=strings[1];
		}
		if(Integer.parseInt(strings[2])<10){
			String[] s2=strings[2].split("");
			day=s2[1]+"";
		}
		else {
			day=strings[2];
		}
		String[] s3=strings[0].split("");
		year=s3[2]+s3[3];
		result=month+"/"+day+"/"+year;


		return result;
	}

	@Override
	public boolean isTradeDate(String date) throws RemoteException {
		boolean sign=false;
		Strategy strategy=new Strategy();
		String da=strategy.convertData(date);
		CsvReader r = null;
		String address="src/resourceStandard/000300.csv";
		try {
			r = new CsvReader(address, ',', forName("GBK"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while (r.readRecord()){
				String s=r.getRawRecord();
				String[] arr=s.split(",");
				if(arr[0].equals(da)){
					sign=true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sign;
	}

	public String convertData(String str){
		String[] sta = str.split("/");
		sta[2] = "20" + sta[2];
		if(Integer.parseInt(sta[0])<10){
			sta[0] = "0" + sta[0];
		}
		if(Integer.parseInt(sta[1])<10){
			sta[1] = "0" + sta[1];
		}
		String time = sta[2] + "-" + sta[0] + "-" + sta[1];
		return time;
	}
	public boolean isInt(String string){
		try {
			int num=Integer.valueOf(string);//把字符串强制转换为数字
			return true;//如果是数字，返回True
		} catch (Exception e) {
			return false;//如果抛出异常，返回False
		}
	}

	public  String getSpecifiedDayAfter(String specifiedDay){
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	public  String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	public String conDate(String result){
		String[] strings=result.split("-");
		String month,day,year;
		if(Integer.parseInt(strings[1])<10){
			String[] s1=strings[1].split("");
			month=s1[1]+"";
		}
		else {
			month=strings[1];
		}
		if(Integer.parseInt(strings[2])<10){
			String[] s2=strings[2].split("");
			day=s2[1]+"";
		}
		else {
			day=strings[2];
		}
		String[] s3=strings[0].split("");
		year=s3[2]+s3[3];
		result=month+"/"+day+"/"+year;


		return result;
	}
}
