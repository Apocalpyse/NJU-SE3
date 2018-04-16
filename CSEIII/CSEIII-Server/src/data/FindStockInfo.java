package data;

import java.io.*;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import PO.*;
import dataSer.FindStockInfoDataSer;
import java.sql.*;
import java.util.Date;

import static java.nio.charset.Charset.forName;

public class FindStockInfo implements FindStockInfoDataSer{
	private static FindStockInfo findStockInfo= null;

	private String sql;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Connection con ;
	String url = "jdbc:mysql://127.0.0.1:3306/DS_HRS";
	String name="root";
	String password="2578";

<<<<<<< HEAD
	public static void main(String[] args) {
		FindStockInfo findStockInfo1 = new FindStockInfo();
		ArrayList<StockPO> as1 = new ArrayList<StockPO>();

		try {
			as1=findStockInfo1.findStockInfoByCode("4/1/13","4/1/14","1");
		} catch (RemoteException e) {
			e.printStackTrace();
		}


		System.out.println(as1.size());
=======


>>>>>>> ef29f6926d8f275810fcf21b4260a9791e324744

	@Override
	public ArrayList<StockPO> findStockInfoByCode(String start, String end, String code) throws RemoteException{
		ArrayList<StockPO> as=new ArrayList<StockPO>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//
		FindStockInfo findStockInfo=new FindStockInfo();
		Date st=null,en=null;
		try {
			st=sdf.parse(findStockInfo.convertData(start));
			en=sdf.parse(findStockInfo.convertData(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sql="SELECT * FROM s"+code+"WHERE date BETWEEN '"+start+"' AND '"+end+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url,this.name,this.password);
			preparedStatement=con.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnum=rsmd.getColumnCount();
			while (rs.next()){
				for(int i=1;i<columnum;i++){
					as.get(i).setCode(code);
					as.get(i).setDate(rs.getString(1));
					as.get(i).setOpen(rs.getNString(2));
					as.get(i).setHigh(rs.getNString(3));
					as.get(i).setClose(rs.getNString(4));
					as.get(i).setLow(rs.getNString(5));
					as.get(i).setVolume(rs.getNString(6));
					as.get(i).setIncreaseOrDecrease(rs.getNString(8));
				}
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

<<<<<<< HEAD
*/


	@Override
	public ArrayList<StockPO> findStockInfoByCode(String start, String end, String code) throws RemoteException{
		ArrayList<StockPO> as=new ArrayList<StockPO>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//
		FindStockInfo findStockInfo=new FindStockInfo();
		Date st=null,en=null;
//		sql="SELECT * FROM s"+code+"WHERE date BETWEEN '"+start+"' AND '"+end+"'";
		try {
			st=sdf.parse(findStockInfo.convertData(start));
			en=sdf.parse(findStockInfo.convertData(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection(this.url,this.name,this.password);
//			preparedStatement=con.prepareStatement(sql);
//			ResultSet rs=preparedStatement.executeQuery();
//			ResultSetMetaData rsmd=rs.getMetaData();
//			int columnum=rsmd.getColumnCount();
//			while (rs.next()){
//				for(int i=1;i<columnum;i++){
//					as.get(i).setCode(code);
//					as.get(i).setDate(rs.getString(1));
//					as.get(i).setOpen(rs.getNString(2));
//					as.get(i).setHigh(rs.getNString(3));
//					as.get(i).setClose(rs.getNString(4));
//					as.get(i).setLow(rs.getNString(5));
//					as.get(i).setVolume(rs.getNString(6));
//					as.get(i).setIncreaseOrDecrease(rs.getNString(8));
//				}
//			}
//			con.close();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}



		String csvFilePath = "src/resourceCode/"+code+".csv";
		File file=new File(csvFilePath);
		if(file.exists()){
			CsvReader r = null;
			try {
				r = new CsvReader(csvFilePath, ',', forName("UTF-8"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				while (r.readRecord()){
					String str=r.getRawRecord();
					String [] arr = str.split("\t");
					Date date=sdf.parse(findStockInfo.convertData(arr[1]));
					if(date.after(st)&&date.before(en)||date.equals(st)||date.equals(en)){
						arr[9]=arr[9].replaceAll("Ａ","A");
						arr[9]=arr[9].replaceAll(" ","");
						as.add(new StockPO(arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9],arr[10],arr[11],arr[12],arr[13]));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
=======
>>>>>>> ef29f6926d8f275810fcf21b4260a9791e324744
		return as;
	}

	@Override
	public ArrayList<StockPO> findStockInfoByName(String start, String end, String name) throws RemoteException{
		ArrayList<StockPO> as=new ArrayList<StockPO>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		FindStockInfo findStockInfo=new FindStockInfo();
		Date st=null,en=null;
		String code;
		try {
			st=sdf.parse(findStockInfo.convertData(start));
			en=sdf.parse(findStockInfo.convertData(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sql="SELECT code FROM stocksbasic WHERE name="+name;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url,this.name,this.password);
			preparedStatement=con.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			code=rs.getNString(1);
			sql="SELECT * FROM s"+code+"WHERE date BETWEEN '"+start+"' AND '"+end+"'";
			preparedStatement=con.prepareStatement(sql);
			rs=preparedStatement.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnum=rsmd.getColumnCount();
			while (rs.next()){
				for(int i=1;i<columnum;i++){
					as.get(i).setCode(code);
					as.get(i).setDate(rs.getString(1));
					as.get(i).setOpen(rs.getNString(2));
					as.get(i).setHigh(rs.getNString(3));
					as.get(i).setClose(rs.getNString(4));
					as.get(i).setLow(rs.getNString(5));
					as.get(i).setVolume(rs.getNString(6));
					as.get(i).setIncreaseOrDecrease(rs.getNString(8));
				}
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return as;
	}

	@Override
	public ArrayList<StockPO> findStockInfoOneday(String date) throws RemoteException {
		ArrayList<StockPO> as=new ArrayList<StockPO>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		FindStockInfo findStockInfo=new FindStockInfo();
		Date da=null;
		try {
			da=sdf.parse(findStockInfo.convertData(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sql="SELECT code FROM stocksbasic WHERE date="+date;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url,this.name,this.password);
			preparedStatement=con.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columnum=rsmd.getColumnCount();
			while (rs.next()){
				for(int i=1;i<columnum;i++){
					as.get(i).setDate(rs.getString(1));
					as.get(i).setOpen(rs.getNString(2));
					as.get(i).setHigh(rs.getNString(3));
					as.get(i).setClose(rs.getNString(4));
					as.get(i).setLow(rs.getNString(5));
					as.get(i).setVolume(rs.getNString(6));
					as.get(i).setIncreaseOrDecrease(rs.getNString(8));
				}
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return as;
	}

	@Override
	public ArrayList<StockPO> findAllStock(String codeOrName) throws RemoteException{
		ArrayList<StockPO> as = new ArrayList<StockPO>();


		return as;
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




}
