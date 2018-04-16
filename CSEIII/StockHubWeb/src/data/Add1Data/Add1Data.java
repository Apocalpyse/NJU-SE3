package data.Add1Data;

import PO.marketPO.SimpleInvestPO;
import PO.stockPO.*;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForIndustry;
import data.rootMethod.MethodForStock;
import dataSer.Add1DataSer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by delong chang on 2017/6/8.
 */
public class Add1Data implements Add1DataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args){
        Add1Data a=new Add1Data();
    }

    @Override
    public CompanyPO getCompanyInfo(String code) {
        CompanyPO result=new CompanyPO();
        sql="SELECT * FROM stocksbasic WHERE code='"+code+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setCode(code);
                result.setStockName(rs.getNString(2));
                result.setIndustry(rs.getNString(3));
                result.setArea(rs.getNString(4));
                result.setTotalassets(rs.getNString(8));
                result.setLiquidassets(rs.getNString(9));
                result.setFixedassets(rs.getNString(10));
                result.setUptime(rs.getNString(16));
                result.setHolders(rs.getNString(23));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<SimpleMainEventPO> getSimpleEventInfo(String code, String date) {
        ArrayList<SimpleMainEventPO> result=new ArrayList<>();
        SimpleMainEventPO po;
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        Random random=new Random();
        for(int i=0;i<50;i++){
            sql="SELECT * FROM news WHERE id='"+random.nextInt(100)+"'";
            try{
                Connecter connecter=new Connecter();
                con=connecter.getConnection();
                preparedStatement=con.prepareStatement(sql);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    po=new SimpleMainEventPO();
                    po.setTitle(rs.getNString(3));
                    po.setIndustry(code);
                    po.setTime(rs.getNString(4));
                    result.add(po);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public MainEventPO getDetialMainEventInfo(String title, String time) {
        MainEventPO result=new MainEventPO();
        MethodForStock methodForStock=new MethodForStock();
        time=methodForStock.convertData(time);
        sql="SELECT * FROM news WHERE title='"+title+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setTime(rs.getNString(4));
                result.setContent(rs.getNString(6));
                result.setTitle(rs.getNString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<SimpleTrendPO> getSimpleTrendInfo(String code, String date) {
        ArrayList<SimpleTrendPO> result=new ArrayList<>();
        SimpleTrendPO po;
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        Random random=new Random();
        for(int i=0;i<50;i++){
            sql="SELECT * FROM news WHERE id='"+random.nextInt(100)+"'";
            try{
                Connecter connecter=new Connecter();
                con=connecter.getConnection();
                preparedStatement=con.prepareStatement(sql);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    po=new SimpleTrendPO();
                    po.setTitle(rs.getNString(3));
                    po.setIndustry(code);
                    po.setTime(rs.getNString(4));
                    result.add(po);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public TrendPO getDetialTrendInfo(String title, String time) {
        TrendPO result=new TrendPO();
        MethodForStock methodForStock=new MethodForStock();
        time=methodForStock.convertData(time);
        sql="SELECT * FROM news WHERE title='"+title+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setTime(rs.getNString(4));
                result.setContent(rs.getNString(6));
                result.setTitle(rs.getNString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<SimpleInvestNewsPO> getSimpleInvestInfo(String code, String date) {
        ArrayList<SimpleInvestNewsPO> result=new ArrayList<>();
        SimpleInvestNewsPO po;
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        Random random=new Random();
        for(int i=0;i<50;i++){
            sql="SELECT * FROM news WHERE id='"+random.nextInt(100)+"'";
            try{
                Connecter connecter=new Connecter();
                con=connecter.getConnection();
                preparedStatement=con.prepareStatement(sql);
                ResultSet rs=preparedStatement.executeQuery();
                while (rs.next()){
                    po=new SimpleInvestNewsPO();
                    po.setTitle(rs.getNString(3));
                    po.setTime(rs.getNString(4));
                    po.setIndustry(code);
                    result.add(po);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public InvestNewsPO getDetialInvestNewsInfo(String title, String time) {
        InvestNewsPO result=new InvestNewsPO();
        MethodForStock methodForStock=new MethodForStock();
        time=methodForStock.convertData(time);
        sql="SELECT * FROM news WHERE title='"+title+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setTime(rs.getNString(4));
                result.setContent(rs.getNString(6));
                result.setTitle(rs.getNString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<String> getAllIndustryName() {
        ArrayList<String> result=new ArrayList<>();
        sql="SELECT industry FROM codelist GROUP BY industry";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.add(rs.getNString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isStockExist(String stockCodeOrName) {
        boolean result=false;
        MethodForIndustry methodForIndustry=new MethodForIndustry();
        switch (methodForIndustry.iscodeOrName(stockCodeOrName)){
            case 0:
                sql="SELECT * FROM codelist WHERE code='"+stockCodeOrName+"'";
                break;
            case 1:
                sql="SELECT * FROM codelist WHERE name='"+stockCodeOrName+"'";
                break;
        }
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result=rs.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
