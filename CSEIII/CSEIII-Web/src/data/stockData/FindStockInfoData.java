package data.stockData;

import PO.stockPO.StockPO;
import dataSer.FindStockInfoDataSer;
import java.sql.*;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取个股信息
 * 全部实现
 */
public class FindStockInfoData implements FindStockInfoDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    public static void main(String[] args){
        FindStockInfoData a=new FindStockInfoData();
        ArrayList<StockPO> b=new ArrayList<>();
        b=a.findStockInfoOneday("4/1/16");
    }

    @Override
    public ArrayList<StockPO> findStockInfoByCode(String start, String end, String code) {
        ArrayList<StockPO> as=new ArrayList<StockPO>();
        FindStockInfoData findStockInfo=new FindStockInfoData();
        start=findStockInfo.convertData(start);
        end=findStockInfo.convertData(end);
        sql="SELECT * FROM s"+code+" WHERE date BETWEEN '"+start+"' AND '"+end+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int rownum=0;
            while (rs.next()){
                rownum++;
                as.add(new StockPO());
                as.get(rownum-1).setCode(code);
                as.get(rownum-1).setDate(rs.getString(1));
                as.get(rownum-1).setOpen(rs.getNString(2));
                as.get(rownum-1).setHigh(rs.getNString(3));
                as.get(rownum-1).setClose(rs.getNString(4));
                as.get(rownum-1).setLow(rs.getNString(5));
                as.get(rownum-1).setVolume(rs.getNString(6));
                as.get(rownum-1).setIncreaseOrDecrease(rs.getNString(8));
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
    public ArrayList<StockPO> findStockInfoByName(String start, String end, String name) {
        ArrayList<StockPO> as=new ArrayList<StockPO>();
        FindStockInfoData findStockInfo=new FindStockInfoData();
        start=findStockInfo.convertData(start);
        end=findStockInfo.convertData(end);
        sql="SELECT code FROM stocksbasic WHERE name='"+name+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            ResultSet resultSet;
            ArrayList<String> codes=new ArrayList<>();
            while (rs.next()){
                codes.add(rs.getNString(1));
            }
            for(int i=0;i<codes.size();i++){
                sql="SELECT * FROM s"+codes.get(i)+" WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                preparedStatement=con.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                int rownum=0;
                while (resultSet.next()){
                    rownum++;
                    as.add(new StockPO());
                    as.get(rownum-1).setCode(codes.get(i));
                    as.get(rownum-1).setDate(resultSet.getString(1));
                    as.get(rownum-1).setOpen(resultSet.getNString(2));
                    as.get(rownum-1).setHigh(resultSet.getNString(3));
                    as.get(rownum-1).setClose(resultSet.getNString(4));
                    as.get(rownum-1).setLow(resultSet.getNString(5));
                    as.get(rownum-1).setVolume(resultSet.getNString(6));
                    as.get(rownum-1).setIncreaseOrDecrease(resultSet.getNString(8));
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
    public ArrayList<StockPO> findStockInfoOneday(String date) {
        ArrayList<StockPO> result=new ArrayList<>();
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        date=findStockInfoData.convertData(date);
        sql="SELECT code FROM stocksbasic";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            ArrayList<String> codes=new ArrayList<>();
            while (rs.next()){
                codes.add(rs.getNString(1));
            }
            int rownum = 0;
            for(int i=0;i<codes.size();i++) {
                sql = "SELECT * FROM s" + codes.get(i) + " WHERE date=" + date;
                preparedStatement = con.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    rownum++;
                    result.add(new StockPO());
                    result.get(rownum - 1).setCode(codes.get(i));
                    result.get(rownum - 1).setDate(resultSet.getString(1));
                    result.get(rownum - 1).setOpen(resultSet.getNString(2));
                    result.get(rownum - 1).setHigh(resultSet.getNString(3));
                    result.get(rownum - 1).setClose(resultSet.getNString(4));
                    result.get(rownum - 1).setLow(resultSet.getNString(5));
                    result.get(rownum - 1).setVolume(resultSet.getNString(6));
                    result.get(rownum - 1).setIncreaseOrDecrease(resultSet.getNString(8));
                    System.out.println(result.get(rownum-1).getClose());
                }
            }
        } catch (Exception e){
            System.out.println("找不到数据");
        }
        return result;
    }

    @Override
    public ArrayList<StockPO> findAllStock(String codeOrName) {
        return null;
    }//搜索功能暂未确定，暂不实现;

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
