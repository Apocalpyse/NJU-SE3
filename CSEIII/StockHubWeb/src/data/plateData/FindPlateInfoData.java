package data.plateData;

import PO.platePO.TotalPlatePO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForPlate;
import data.rootMethod.MethodForStock;
import data.stockData.FindStockInfoData;
import dataSer.FindPlateInfoDataSer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by A on 2017/5/21.
 * 获取板块信息
 * 实现
 */
public class FindPlateInfoData implements FindPlateInfoDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args){
        FindPlateInfoData findPlateInfoData=new FindPlateInfoData();

        ArrayList<StockPO> s=findPlateInfoData.findOnePlateInfo(5,"6/12/17");

    }

    @Override
    public TotalPlatePO findTotalPlateInfo(String date) {
        TotalPlatePO result=new TotalPlatePO();
        MethodForStock methodForStock=new MethodForStock();
        MethodForPlate methodForPlate=new MethodForPlate();
        result.setPlateName(new ArrayList<>());
        result.setCompanyNum(new ArrayList<>());
        result.setAverageAdjClose(new ArrayList<>());
        result.setAverageClose(new ArrayList<>());
        result.setAverageOpen(new ArrayList<>());
        result.setPreAverageAdjClose(new ArrayList<>());
        result.setIncreaseOrDecreaseMoney(new ArrayList<>());
        result.setIncreaseOrDecreaseRate(new ArrayList<>());
        result.setTotalVolume(new ArrayList<>());

        date=methodForStock.convertData(date);
        result=methodForPlate.getTotalPlate("sh",date,result);
        result=methodForPlate.getTotalPlate("sz",date,result);
        result=methodForPlate.getTotalPlate("hs",date,result);
        result=methodForPlate.getTotalPlate("zxb",date,result);
        result=methodForPlate.getTotalPlate("cyb",date,result);
        result.setDate(methodForStock.convertDateBack(date));
        return result;
    }

    @Override
    public ArrayList<StockPO> findOnePlateInfo(int type, String date) {
        ArrayList<StockPO> result=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        /*获取不同大盘的信息*/
        switch (type){
            case 1://中小版
                sql="SELECT * FROM recent WHERE date='"+date+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result=methodForStock.getRecentPO(rs,StockPool.SMALLMIDDLEPLATE);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case 2://创业板
                sql="SELECT * FROM recent WHERE date='"+date+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result=methodForStock.getRecentPO(rs,StockPool.STARTUPPLATE);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case 3://上证
                sql="SELECT * FROM recent WHERE date='"+date+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result=methodForStock.getRecentPO(rs,StockPool.SHANGZHENG);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case 4://深证
                sql="SELECT * FROM recent WHERE date='"+date+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result=methodForStock.getRecentPO(rs,StockPool.SHENZHENG);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case 5://沪深300
                sql="SELECT * FROM recent WHERE date='"+date+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result=methodForStock.getRecentPO(rs,StockPool.HUSHEN300);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public ArrayList<TotalPlatePO> findOnePlateInfo(String start, String end, StockPool stockPool) {
        ArrayList<TotalPlatePO> result=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        MethodForPlate methodForPlate=new MethodForPlate();
        start=methodForStock.convertData(start);
        end=methodForStock.convertData(end);
        result=methodForPlate.getTotalPlateDays(stockPool,start,end,result);
        return result;
    }

    @Override
    public ArrayList<StockPO> findSockPoolInfo(String start, String end, StockPool stockPool) {
        ArrayList<StockPO> result=new ArrayList<>();
        StockPO po;
        MethodForStock methodForStock=new MethodForStock();
        start=methodForStock.convertData(start);
        end=methodForStock.convertData(end);
        switch (stockPool){
            case SHANGZHENG:
                sql="SELECT * FROM sh WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StockPO();
                        po.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                        po.setOpen(rs.getNString(2));
                        po.setHigh(rs.getNString(3));
                        po.setClose(rs.getNString(4));
                        po.setLow(rs.getNString(5));
                        result.add(po);
                    }
                }catch (SQLException e){
                    e.getErrorCode();
                }
                break;
            case SHENZHENG:
                sql="SELECT * FROM sz WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StockPO();
                        po.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                        po.setOpen(rs.getNString(2));
                        po.setHigh(rs.getNString(3));
                        po.setClose(rs.getNString(4));
                        po.setLow(rs.getNString(5));
                        result.add(po);
                    }
                }catch (SQLException e){
                    e.getErrorCode();
                }
                break;
            case HUSHEN300:
                sql="SELECT * FROM hs WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StockPO();
                        po.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                        po.setOpen(rs.getNString(2));
                        po.setHigh(rs.getNString(3));
                        po.setClose(rs.getNString(4));
                        po.setLow(rs.getNString(5));
                        result.add(po);
                    }
                }catch (SQLException e){
                    e.getErrorCode();
                }
                break;
            case SMALLMIDDLEPLATE:
                sql="SELECT * FROM zxb WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StockPO();
                        po.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                        po.setOpen(rs.getNString(2));
                        po.setHigh(rs.getNString(3));
                        po.setClose(rs.getNString(4));
                        po.setLow(rs.getNString(5));
                        result.add(po);
                    }
                }catch (SQLException e){
                    e.getErrorCode();
                }
                break;
            case STARTUPPLATE:
                sql="SELECT * FROM cyb WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StockPO();
                        po.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                        po.setOpen(rs.getNString(2));
                        po.setHigh(rs.getNString(3));
                        po.setClose(rs.getNString(4));
                        po.setLow(rs.getNString(5));
                        result.add(po);
                    }
                }catch (SQLException e){
                    e.getErrorCode();
                }
                break;
        }
        return result;
    }
}
