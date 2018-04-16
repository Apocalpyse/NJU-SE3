package data.stockData;

import PO.stockPO.StockPO;

import PO.stockPO.StockPool;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForStock;
import dataSer.FindStockInfoDataSer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by A on 2017/5/21.
 * 获取个股信息
 * 全部实现
 */
public class FindStockInfoData implements FindStockInfoDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;

    public static void main(String[] args){
        ArrayList<StockPO> a=new ArrayList<>();
        FindStockInfoData b=new FindStockInfoData();
        a=b.findStockInfoOneday("6/14/17");
    }

    @Override
    public ArrayList<StockPO> findStockInfoByCode(String start, String end, String code) {
        ArrayList<StockPO> as=new ArrayList<StockPO>();
        MethodForStock methodForStock=new MethodForStock();
        start=methodForStock.convertData(start);
        end=methodForStock.convertData(end);
        sql="SELECT * FROM s"+code+" WHERE date BETWEEN '"+start+"' AND '"+end+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            as=methodForStock.dealResultSet(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        Collections.reverse(as);
        return as;
    }

    @Override
    public ArrayList<StockPO> findStockInfoByName(String start, String end, String name) {
        ArrayList<StockPO> as=new ArrayList<StockPO>();
        MethodForStock methodForStock=new MethodForStock();
        start=methodForStock.convertData(start);
        end=methodForStock.convertData(end);
        sql="SELECT code FROM stocksbasic WHERE name='"+name+"'";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                sql="SELECT * FROM s"+rs.getNString(1)+" WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                preparedStatement=con.prepareStatement(sql);
                ResultSet res=preparedStatement.executeQuery();
                as=methodForStock.dealResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.reverse(as);
        return as;
    }

    @Override
    public ArrayList<StockPO> findStockInfoOneday(String date) {
        ArrayList<StockPO> result=new ArrayList<>();
        StockPO temp;
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        sql="SELECT * FROM recent WHERE date='"+date+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result=methodForStock.getRecentPO(rs, StockPool.ALL);
        } catch (NullPointerException e){
            System.out.println("找不到数据");
        }catch (SQLException e){
            e.printStackTrace();
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public ArrayList<StockPO> findAllStock(String codeOrName) {
        ArrayList<StockPO> as=new ArrayList<StockPO>();
        MethodForStock methodForStock=new MethodForStock();
        if(Character.isDigit(codeOrName.split("")[0].charAt(0))){
            sql="SELECT * FROM s"+codeOrName;
            try{
                Connecter connecter=new Connecter();
                con=connecter.getConnection();
                preparedStatement=con.prepareStatement(sql);
                ResultSet rs=preparedStatement.executeQuery();
                as=methodForStock.dealResultSet(rs);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            sql="SELECT code FROM stocksbasic WHERE name='"+codeOrName+"'";
            try{
                Connecter connecter=new Connecter();
                con=connecter.getConnection();
                preparedStatement=con.prepareStatement(sql);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    sql="SELECT * FROM s"+rs.getNString(1);
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    as=methodForStock.dealResultSet(resultSet);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        Collections.reverse(as);
        return as;
    }

}
