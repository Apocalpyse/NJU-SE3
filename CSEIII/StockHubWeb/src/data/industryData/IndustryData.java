package data.industryData;

import PO.industryPO.IndustryPO;
import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import bl.industryBl.Industry;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForIndustry;
import data.rootMethod.MethodForStock;
import data.rootMethod.MethodForStrategy;
import dataSer.IndustryDataSer;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取行业信息
 * 实现
 */
public class IndustryData implements IndustryDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args) {
        IndustryData d = new IndustryData();
        ArrStockPO a=d.getIndustryInfo("元器件");
    }

    @Override
    public ArrayList<IndustryPO> findIndustryInfoOneday(String date) {
        ArrayList<IndustryPO> result=new ArrayList<>();
        IndustryPO industryPO;
        try{
            sql="SELECT * FROM industry WHERE date='"+date+"'";
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println("1");
                industryPO=new IndustryPO();
                industryPO.setDate(rs.getNString(1));
                industryPO.setIndustryName(rs.getNString(2));
                industryPO.setCompanyNum(rs.getNString(3));
                industryPO.setAverageOpen(rs.getNString(4));
                industryPO.setAverageClose(rs.getNString(5));
                industryPO.setIncreaseOrDecreaseMoney(rs.getNString(6));
                industryPO.setIncreaseOrDecreaseRate(rs.getNString(7));
                industryPO.setTotalVolume(rs.getNString(8));
                result.add(industryPO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<StockPO> findIndustryInfoOneday(String date, String industryName) {
        ArrayList<StockPO> result=new ArrayList<>();
        ArrayList<String> codes=new ArrayList<>();
        ArrayList<StockPO> temp=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        sql="SELECT code FROM codeList WHERE industry='"+industryName+"'";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                codes.add(rs.getNString(1));
            }
            for(int i=0;i<codes.size();i++){
                sql="SELECT * FROM s"+codes.get(i)+" WHERE date='"+date+"'";
                preparedStatement=con.prepareStatement(sql);
                ResultSet res=preparedStatement.executeQuery();
                temp=methodForStock.dealResultSet(res);
                result.addAll(temp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getIndustryName(String codeOrName) {
        String result=null;
        MethodForIndustry methodForIndustry=new MethodForIndustry();
        int i=methodForIndustry.iscodeOrName(codeOrName);
        if(i==0){
            sql="SELECT industry FROM codelist WHERE code="+codeOrName;
        }else {
            sql="SELECT industry FROM codelist WHERE name='"+codeOrName+"'";
        }
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                result=rs.getNString(1);
            }else {
                result="其他行业";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public IndustryPO getIndustryInfo(String date, String industryName) {
        IndustryPO result=new IndustryPO();
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        sql="SELECT code FROM codeList WHERE industry='"+industryName+"'";
        ArrayList<StockPO> stockPOS=new ArrayList<>();
        StockPO stockPO=new StockPO();
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                sql="SELECT * FROM s"+rs.getNString(1)+" WHERE date='"+date+"'";
                preparedStatement=con.prepareStatement(sql);
                ResultSet res=preparedStatement.executeQuery();
                stockPOS=methodForStock.dealResultSet(res);
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
            if(stockPOS.size()!=0){
                BigDecimal open2   =   new   BigDecimal(open/stockPOS.size());
                open=open2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal close2   =   new   BigDecimal(close/stockPOS.size());
                close=close2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal rate2   =   new   BigDecimal(rate/stockPOS.size());
                rate=rate2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal volume2   =   new   BigDecimal(volume/stockPOS.size());
                volume=volume2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal money2   =   new   BigDecimal(close-open);
                money=money2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                //计算出该行业的信息;
            }

            result=new IndustryPO();
            result.setDate(date);
            result.setAverageClose(Double.toString(close));
            result.setAverageOpen(Double.toString(open));
            result.setCompanyNum(Integer.toString(stockPOS.size()));
            result.setIncreaseOrDecreaseMoney(Double.toString(money));
            result.setIncreaseOrDecreaseRate(Double.toString(rate));
            result.setTotalVolume(Double.toString(volume));
            result.setIndustryName(industryName);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrStockPO getPoolInfo(StockPool stockPool) {
        ArrStockPO result=new ArrStockPO();
        MethodForIndustry methodForIndustry=new MethodForIndustry();
        result.setCode(new ArrayList<>());
        result.setName(new ArrayList<>());
        switch (stockPool){
            case ALL:
                sql="SELECT code,name FROM codelist";
                result=methodForIndustry.getPool(result,sql);
                break;
            case SHANGZHENG:
                sql="SELECT code,name FROM codelist";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        if(rs.getNString(1).split("")[0].equals("6")){
                             result.getCode().add(rs.getNString(1));
                             result.getName().add(rs.getNString(2));
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SHENZHENG:
                sql="SELECT code,name FROM codelist";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        if(rs.getNString(1).split("")[0].equals("0")||rs.getNString(1).split("")[0].equals("2")||rs.getNString(1).split("")[0].equals("3")){
                            result.getCode().add(rs.getNString(1));
                            result.getName().add(rs.getNString(2));
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case HUSHEN300:
                sql="SELECT code,name FROM hs300";
                result=methodForIndustry.getPool(result,sql);
                break;
            case SMALLMIDDLEPLATE:
                sql="SELECT code,name FROM middleandsmall";
                result=methodForIndustry.getPool(result,sql);
                break;
            case STARTUPPLATE:
                sql="SELECT code,name FROM startup";
                result=methodForIndustry.getPool(result,sql);
                break;
            case SELECTPLATE:
                sql="SELECT selfselectstocks FROM user ";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    String codes[]=rs.getNString(1).split(" ");
                    for(int i=0;i<codes.length;i++){
                        result.getCode().add(codes[i]);
                        sql="SELECT name FROM stocksbasic WHERE code='"+codes[i]+"'";
                        preparedStatement=con.prepareStatement(sql);
                        ResultSet resultSet=preparedStatement.executeQuery();
                        result.getName().add(resultSet.getNString(1));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
        }
        return result;
    }

    @Override
    public ArrStockPO getIndustryInfo(String industry) {
        ArrStockPO result=new ArrStockPO();
        result.setName(new ArrayList<>());
        result.setCode(new ArrayList<>());
        sql="SELECT code,name FROM codelist WHERE industry='"+industry+"'";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.getCode().add(rs.getNString(1));
                result.getName().add(rs.getNString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getName(String code) {
        String result=null;
        sql="SELECT name FROM stocksbasic WHERE code='"+code+"'";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=rs.getNString(1);
            }
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
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=rs.getNString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}
