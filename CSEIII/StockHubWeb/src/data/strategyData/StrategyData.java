package data.strategyData;

import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPool;
import PO.strategyPO.InputStrategyPO;
import PO.strategyPO.StrategyStockPO;
import data.MySqlConnecter.Connecter;
import data.industryData.IndustryData;
import data.rootMethod.MethodForStock;
import data.rootMethod.MethodForStrategy;
import data.stockData.FindStockInfoData;
import dataSer.StrategyDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取计算策略所需信息
 * 实现
 */
public class StrategyData implements StrategyDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args){
        StrategyData strategyData=new StrategyData();
        InputStrategyPO inputStrategyPO=new InputStrategyPO();
        inputStrategyPO.setStart("6/14/16");
        inputStrategyPO.setEnd("6/14/17");
        inputStrategyPO.setStockPool(StockPool.HUSHEN300);
        ArrayList<StrategyStockPO> a=strategyData.findStrategyStockInfo(inputStrategyPO);
    }

    @Override
    public ArrayList<StrategyStockPO> findStrategyStockInfo(InputStrategyPO inputStrategyPO) {
        ArrayList<StrategyStockPO> result=new ArrayList<>();
        MethodForStrategy methodForStrategy=new MethodForStrategy();
        StrategyData strategyData=new StrategyData();
        ArrayList<String> dates=new ArrayList<>();
        StrategyStockPO po;
        IndustryData industryData=new IndustryData();
        ArrStockPO arrStockPO=new ArrStockPO();
        String start=inputStrategyPO.getStart();
        String end=inputStrategyPO.getEnd();
        MethodForStock a=new MethodForStock();
        start=a.convertData(start);
        end=a.convertData(end);


        sql="SELECT date FROM s000001 WHERE date BETWEEN '"+start+"' AND '"+end+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
               dates.add(rs.getNString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(dates.size());
        switch (inputStrategyPO.getStockPool()){
            case ALL:
                result=methodForStrategy.getStrategyData(StockPool.ALL,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
            case SHENZHENG:
                result=methodForStrategy.getStrategyData(StockPool.SHENZHENG,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
            case SHANGZHENG:
                result=methodForStrategy.getStrategyData(StockPool.SHANGZHENG,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
            case HUSHEN300:
                result=methodForStrategy.getStrategyData(StockPool.HUSHEN300,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
            case STARTUPPLATE:
                result=methodForStrategy.getStrategyData(StockPool.STARTUPPLATE,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
            case SMALLMIDDLEPLATE:
                result=methodForStrategy.getStrategyData(StockPool.SMALLMIDDLEPLATE,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
            case SELECTPLATE:
                result=methodForStrategy.getStrategyData(StockPool.SELECTPLATE,dates,start,end,inputStrategyPO.getStockNameOrCode());
                break;
        }
        return result;
    }

    @Override
    public StrategyStockPO findBenchmarkStockInfo(String start, String end, String code) {
        StrategyStockPO result=new StrategyStockPO();
        ArrayList<String> date=new ArrayList<>();
        ArrayList<String> adjclose=new ArrayList<>();
        MethodForStock a=new MethodForStock();
        start=a.convertData(start);
        end=a.convertData(end);
        switch (code){
            case "000300":
                sql="SELECT * FROM hs WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result.setCode(code);
                    while (rs.next()){
                        result.setName("沪深300");
                        date.add(a.convertDateBack(rs.getNString(1)));
                        adjclose.add(rs.getNString(4));
                    }
                    result.setDate(date);
                    result.setAdjClose(adjclose);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "399005":
                sql="SELECT * FROM zxb WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result.setCode(code);
                    while (rs.next()){
                        result.setName("中小板");
                        date.add(a.convertDateBack(rs.getNString(1)));
                        adjclose.add(rs.getNString(4));
                    }
                    result.setDate(date);
                    result.setAdjClose(adjclose);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "399006":
                sql="SELECT * FROM cyb WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result.setCode(code);
                    while (rs.next()){
                        result.setName("创业板");
                        date.add(a.convertDateBack(rs.getNString(1)));
                        adjclose.add(rs.getNString(4));
                    }
                    result.setDate(date);
                    result.setAdjClose(adjclose);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "000001":
                sql="SELECT * FROM sh WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result.setCode(code);
                    while (rs.next()){
                        result.setName("上证");
                        date.add(a.convertDateBack(rs.getNString(1)));
                        adjclose.add(rs.getNString(4));
                    }
                    result.setDate(date);
                    result.setAdjClose(adjclose);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "399001":
                sql="SELECT * FROM sz WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try {
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    result.setCode(code);
                    while (rs.next()){
                        result.setName("深证");
                        date.add(a.convertDateBack(rs.getNString(1)));
                        adjclose.add(rs.getNString(4));
                    }
                    result.setDate(date);
                    result.setAdjClose(adjclose);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
        }

        return result;
    }

    @Override
    public String getPreviousTradeDate(String date, int days) {
        String result=null;
        boolean find=false;
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        System.out.println(date);
        MethodForStrategy methodForStrategy=new MethodForStrategy();
        ArrayList<String> dates=new ArrayList<>();
        sql="SELECT date FROM s000006";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                dates.add(rs.getNString(1));
            }
            while (!find){
                date=methodForStrategy.lastDay(date);
                for(int i=0;i<dates.size();i++){
                    if(date.equals(dates.get(i))){
                        result=dates.get(i+days-1);
                        find=true;
                        break;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        result=methodForStock.convertDateBack(result);
        return result;
    }

    @Override
    public boolean isTradeDate(String date) {
        boolean result=false;
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        sql="SELECT * FROM s000001 WHERE date='"+date+"'";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
