package data;

import PO.*;
import dataSer.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static java.nio.charset.Charset.forName;
import java.sql.*;

/**
 * Created by chenjin on 2017/4/10.
 */
public class FindPlateInfo implements FindPlateInfoDataSer{

	private static FindPlateInfo findPlateInfo=null;

	private String sql;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Connection con ;
	String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
	String name="root";
	String password="2578";

	@Override
	public TotalPlatePO findTotalPlateInfo(String date) throws RemoteException {
		TotalPlatePO totalPlatePO=null;

		sql="SELECT ";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url,this.name,this.password);
			preparedStatement=con.prepareStatement(sql);
			preparedStatement.executeUpdate();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return totalPlatePO;
	}

	@Override
	public ArrayList<StockPO> findOnePlateInfo(int type, String date) throws RemoteException {
        ArrayList<StockPO> stockPOS=new ArrayList<>();
        ResultSet resultSet;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url,this.name,this.password);

			switch (type){
				case 0: //主板
					sql="";
				case 1: //中小板
					sql="SELECT code FROM middleandsmall";
					preparedStatement=con.prepareStatement(sql);
					resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                        resultSet.getInt(0);
					}
				case 2: //创业板
					sql="SELECT code FROM startup";
				case 3: //上证指数
					sql="";
				case 4: //深证成指
					sql="";
				case 5: //沪深300
					sql="SELECT code FROM hs300";
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stockPOS;
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
