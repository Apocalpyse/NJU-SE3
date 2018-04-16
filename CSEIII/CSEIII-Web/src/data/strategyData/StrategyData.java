package data.strategyData;

import PO.strategyPO.InputStrategyPO;
import PO.strategyPO.StrategyStockPO;
import data.stockData.FindStockInfoData;
import dataSer.StrategyDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取计算策略所需信息
 */
public class StrategyData implements StrategyDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    @Override
    public ArrayList<StrategyStockPO> findStrategyStockInfo(InputStrategyPO inputStrategyPO) {
        ArrayList<StrategyStockPO> result=new ArrayList<>();
        String start=inputStrategyPO.getStart();
        String end=inputStrategyPO.getEnd();
        FindStockInfoData a=new FindStockInfoData();
        start=a.convertData(start);
        end=a.convertData(end);
        switch (inputStrategyPO.getStockPool()){
            case HUSHEN300:

            case ALL:

            case MAINPLATE:

            case SHENZHENG:

            case SELECTPLATE:

            case SHANGZHENG:

            case STARTUPPLATE:

            case SMALLMIDDLEPLATE:
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public StrategyStockPO findBenchmarkStockInfo(String start, String end, String code) {
        StrategyStockPO result=new StrategyStockPO();
        ArrayList<String> date=new ArrayList<>();
        ArrayList<String> adjclose=new ArrayList<>();
        FindStockInfoData a=new FindStockInfoData();
        start=a.convertData(start);
        end=a.convertData(end);
        sql="SELECT * FROM plates WHERE date BETWEEN '"+start+"' AND '"+end+"' AND code='"+code+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result.setCode(code);
            result.setName(rs.getNString(3));
            while (rs.next()){
                date.add(rs.getNString(1));
                adjclose.add(rs.getNString(6));
            }
            result.setDate(date);
            result.setAdjClose(adjclose);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getPreviousTradeDate(String date, int days) {
        String result=null;
        ArrayList<String> dates=new ArrayList<>();
        sql="SELECT date FROM s000001";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                dates.add(rs.getNString(1));
            }
            for(int i=0;i<dates.size();i++){
                if(date==dates.get(i)){
                    result=dates.get(i-days);
                }
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isTradeDate(String date) {
        boolean result=false;
        sql="SELECT * FROM s000001 WHERE date='"+date+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=true;
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
