package data.plateData;

import PO.platePO.TotalPlatePO;
import PO.stockPO.StockPO;
import data.stockData.FindStockInfoData;
import dataSer.FindPlateInfoDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取板块信息
 * 目前主板、上证、深证没有实现
 */
public class FindPlateInfoData implements FindPlateInfoDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    public static void main(String[] args){
        FindPlateInfoData findPlateInfoData=new FindPlateInfoData();
        ArrayList<StockPO> s=new ArrayList<>();
        s=findPlateInfoData.findOnePlateInfo(1,"4/1/16");
        for(int i=0;i<s.size();i++){
            System.out.println(s.get(i).getClose());
        }
    }

    @Override
    public TotalPlatePO findTotalPlateInfo(String date) {
        TotalPlatePO result=new TotalPlatePO();
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        date=findStockInfoData.convertData(date);
        sql="SELECT * FROM plates WHERE code IN ('000001','399001','399005','399006','000300')";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setDate(date);
                result.getPlateName().add(rs.getNString(3));
                result.getAverageOpen().add(rs.getNString(5));
                result.getPreAverageAdjClose().add(rs.getNString(6));
                result.getAverageClose().add(rs.getNString(7));
                result.getIncreaseOrDecreaseRate().add(rs.getNString(4));
                result.getTotalVolume().add(rs.getNString(10));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<StockPO> findOnePlateInfo(int type, String date) {
        ArrayList<StockPO> result=new ArrayList<>();
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        date=findStockInfoData.convertData(date);
        /*数据库连接 */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        /*获取不同大盘的信息*/
        switch (type){
            case 0://主板

            case 1://中小版
                sql="SELECT code FROM middleandsmall";
                try {
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
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            case 2://创业板
                sql="SELECT code FROM startup";
                try {
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
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            case 3://上证

            case 4://深证

            case 5://沪深300
                sql="SELECT code FROM hs300";
                try {
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
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

        }
        return result;
    }

}
