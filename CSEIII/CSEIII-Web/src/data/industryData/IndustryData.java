package data.industryData;

import PO.industryPO.IndustryPO;
import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import dataSer.IndustryDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取行业信息
 * 除接口待修改的都实现了
 */
public class IndustryData implements IndustryDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    public static void main(String[] args){
        IndustryData d=new IndustryData();
        d.findIndustryInfoOneday("");
    }

    @Override
    public ArrayList<IndustryPO> findIndustryInfoOneday(String date) {
        ArrayList<IndustryPO> result=new ArrayList<>();
        IndustryPO industryPO=new IndustryPO();
        ArrayList<String> cnames=new ArrayList<>();
        sql="SELECT c_name FROM industry GROUP BY c_name HAVING COUNT(*)>1 ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
               cnames.add(rs.getNString(1));
            }
            for(int i=0;i<cnames.size();i++){ //每个行业内计算;
                ArrayList<StockPO> stockPOS=new ArrayList<>();
                StockPO stockPO=new StockPO();
                sql="SELECT code FROM industry WHERE c_name='"+cnames.get(i)+"'";
                preparedStatement=con.prepareStatement(sql);
                rs=preparedStatement.executeQuery();
                while (rs.next()){
                    sql="SELECT * FROM s"+rs.getNString(1)+" WHERE date='"+date+"'";
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet res=preparedStatement.executeQuery();
                    stockPO.setOpen(res.getNString(2));
                    stockPO.setClose(res.getNString(4));
                    stockPO.setIncreaseOrDecrease(res.getNString(8));
                    stockPO.setVolume(res.getNString(6));
                    stockPOS.add(stockPO);
                }// 获取一个行业的所有个股信息;
                double open=0;
                double close=0;
                double rate=0;
                double money=0;
                double volume=0;
                for(int j=0;j<stockPOS.size();j++){
                    open+=Double.parseDouble(stockPOS.get(j).getOpen());
                    close+=Double.parseDouble(stockPOS.get(j).getClose());
                    rate+=Double.parseDouble(stockPOS.get(j).getIncreaseOrDecrease());
                    volume+=Double.parseDouble(stockPOS.get(j).getVolume());
                }
                open=open/stockPOS.size();
                close=close/stockPOS.size();
                rate=rate/stockPOS.size();
                volume=volume/stockPOS.size();
                money=close-open;//计算出该行业的信息;

                industryPO=new IndustryPO();
                industryPO.setDate(date);
                industryPO.setAverageClose(Double.toString(close));
                industryPO.setAverageOpen(Double.toString(open));
                industryPO.setCompanyNum(Integer.toString(stockPOS.size()));
                industryPO.setIncreaseOrDecreaseMoney(Double.toString(money));
                industryPO.setIncreaseOrDecreaseRate(Double.toString(rate));
                industryPO.setTotalVolume(Double.toString(volume));
                industryPO.setIndustryName(cnames.get(i));
                result.add(industryPO);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<StockPO> findIndustryInfoOneday(String date, String industryName) {
        ArrayList<StockPO> result=new ArrayList<>();
        ArrayList<String> codes=new ArrayList<>();
        sql="SELECT code FROM industry WHERE c_name='"+industryName+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                codes.add(rs.getNString(1));
            }
            for(int i=0;i<codes.size();i++){
                sql="SELECT * FROM '"+codes.get(i)+"' WHERE date='"+date+"'";
                preparedStatement=con.prepareStatement(sql);
                ResultSet res=preparedStatement.executeQuery();
                while (res.next()){
                    StockPO stockPO=new StockPO();
                    stockPO.setDate(res.getNString(1));
                    stockPO.setCode(codes.get(i));
                    stockPO.setOpen(res.getNString(2));
                    stockPO.setHigh(res.getNString(3));
                    stockPO.setClose(res.getNString(4));
                    stockPO.setLow(res.getNString(5));
                    stockPO.setVolume(res.getNString(6));
                    stockPO.setIncreaseOrDecrease(res.getNString(8));
                    stockPO.setIndustry(industryName);
                    result.add(stockPO);
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
    public String getIndustryName(String codeOrName) {
        String result=null;
        sql="SELECT c_name FROM industry WHERE code="+codeOrName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result=rs.getNString(1);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public IndustryPO getIndustryInfo(String date, String industryName) {
        IndustryPO result=new IndustryPO();
        sql="SELECT code FROM industry WHERE c_name='"+industryName+"'";
        ArrayList<StockPO> stockPOS=new ArrayList<>();
        StockPO stockPO=new StockPO();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                sql="SELECT * FROM s"+rs.getNString(1)+" WHERE date='"+date+"'";
                preparedStatement=con.prepareStatement(sql);
                ResultSet res=preparedStatement.executeQuery();
                stockPO.setOpen(res.getNString(2));
                stockPO.setClose(res.getNString(4));
                stockPO.setIncreaseOrDecrease(res.getNString(8));
                stockPO.setVolume(res.getNString(6));
                stockPOS.add(stockPO);
            }
            double open=0;
            double close=0;
            double rate=0;
            double money=0;
            double volume=0;
            for(int j=0;j<stockPOS.size();j++){
                open+=Double.parseDouble(stockPOS.get(j).getOpen());
                close+=Double.parseDouble(stockPOS.get(j).getClose());
                rate+=Double.parseDouble(stockPOS.get(j).getIncreaseOrDecrease());
                volume+=Double.parseDouble(stockPOS.get(j).getVolume());
            }
            open=open/stockPOS.size();
            close=close/stockPOS.size();
            rate=rate/stockPOS.size();
            volume=volume/stockPOS.size();
            money=close-open;//计算出该行业的信息;
            result=new IndustryPO();
            result.setDate(date);
            result.setAverageClose(Double.toString(close));
            result.setAverageOpen(Double.toString(open));
            result.setCompanyNum(Integer.toString(stockPOS.size()));
            result.setIncreaseOrDecreaseMoney(Double.toString(money));
            result.setIncreaseOrDecreaseRate(Double.toString(rate));
            result.setTotalVolume(Double.toString(volume));
            result.setIndustryName(industryName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrStockPO getPoolInfo(String start, String end, StockPool stockPool) {
        return null;
    }//接口待修改

    @Override
    public ArrStockPO getIndustryInfo(String start, String end, String industry) {
        return null;
    }//接口待修改

    @Override
    public String getName(String code) {
        String result=null;
        sql="SELECT name FROM stocksbasic WHERE code='"+code+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=rs.getNString(1);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getCode(String name) {
        String result=null;
        sql="SELECT code FROM stocksbasic WHERE name='"+name+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=rs.getNString(1);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
