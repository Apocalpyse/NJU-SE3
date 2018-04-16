package data.rootMethod;

import PO.industryPO.IndustryPO;
import PO.platePO.TotalPlatePO;
import PO.stockPO.StockPool;
import data.MySqlConnecter.Connecter;
import data.industryData.IndustryData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by delong chang on 2017/6/8.
 */
public class MethodForPlate {

    private PreparedStatement preparedStatement;
    private Connection con ;

    public TotalPlatePO getTotalPlate(String plate,String date,TotalPlatePO result){
        IndustryData industryData=new IndustryData();
        MethodForPlate methodForPlate=new MethodForPlate();
        String sql="SELECT * FROM "+plate+" WHERE date='"+date+"'";
        switch (plate){
            case "zxb":
                result.getPlateName().add("中小板");
                result.getCompanyNum().add(methodForPlate.getCompanyNum("middleandsmall"));
                break;
            case "cyb":
                result.getPlateName().add("创业板");
                result.getCompanyNum().add(methodForPlate.getCompanyNum("startup"));
                break;
            case "sh":
                result.getPlateName().add("上证");
                result.getCompanyNum().add(Integer.toString(industryData.getPoolInfo(StockPool.SHANGZHENG).getCode().size()));
                break;
            case "sz":
                result.getPlateName().add("深证");
                result.getCompanyNum().add(Integer.toString(industryData.getPoolInfo(StockPool.SHENZHENG).getCode().size()));
                break;
            case "hs":
                result.getPlateName().add("沪深300");
                result.getCompanyNum().add(methodForPlate.getCompanyNum("hs300"));
                break;
            default:
                System.out.println("找不到数据");
                break;
        }
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
               result.getAverageOpen().add(rs.getNString(2));
               result.getAverageClose().add(rs.getNString(4));
               result.getAverageAdjClose().add(rs.getNString(4));
               result.getPreAverageAdjClose().add(rs.getNString(2));
               result.getIncreaseOrDecreaseMoney().add(rs.getNString(7));
               result.getIncreaseOrDecreaseRate().add(rs.getNString(8));
               result.getTotalVolume().add(rs.getNString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public String getCompanyNum(String plate){
        String result=null;
        String sql="SELECT code FROM "+plate;
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int count=0;
            while (rs.next()){
                count++;
            }
            result=Integer.toString(count);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<TotalPlatePO> getTotalPlateDays(StockPool stockPool,String start,String end,ArrayList<TotalPlatePO> result){
        String sql=null;
        MethodForPlate methodForPlate=new MethodForPlate();
        TotalPlatePO po;
        IndustryData industryData=new IndustryData();

        switch (stockPool){
            case SHANGZHENG:
                sql="SELECT * FROM sh WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new TotalPlatePO();
                        po.setTotalVolume(new ArrayList<>());
                        po.setIncreaseOrDecreaseRate(new ArrayList<>());
                        po.setIncreaseOrDecreaseMoney(new ArrayList<>());
                        po.setPreAverageAdjClose(new ArrayList<>());
                        po.setAverageOpen(new ArrayList<>());
                        po.setAverageClose(new ArrayList<>());
                        po.setAverageAdjClose(new ArrayList<>());
                        po.setCompanyNum(new ArrayList<>());
                        po.setPlateName(new ArrayList<>());

                        po.setDate(rs.getNString(1));
                        po.getAverageOpen().add(rs.getNString(2));
                        po.getAverageClose().add(rs.getNString(4));
                        po.getAverageAdjClose().add(rs.getNString(4));
                        po.getPreAverageAdjClose().add(rs.getNString(2));
                        po.getIncreaseOrDecreaseMoney().add(rs.getNString(7));
                        po.getIncreaseOrDecreaseRate().add(rs.getNString(8));
                        po.getTotalVolume().add(rs.getNString(6));
                        result.add(po);
                    }
                    String platenum=Integer.toString(industryData.getPoolInfo(StockPool.SHANGZHENG).getCode().size());
                    for(int i=0;i<result.size();i++){
                        result.get(i).getPlateName().add("上证");
                        result.get(i).getCompanyNum().add(platenum);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SHENZHENG:
                sql="SELECT * FROM sz WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new TotalPlatePO();
                        po.setTotalVolume(new ArrayList<>());
                        po.setIncreaseOrDecreaseRate(new ArrayList<>());
                        po.setIncreaseOrDecreaseMoney(new ArrayList<>());
                        po.setPreAverageAdjClose(new ArrayList<>());
                        po.setAverageOpen(new ArrayList<>());
                        po.setAverageClose(new ArrayList<>());
                        po.setAverageAdjClose(new ArrayList<>());
                        po.setCompanyNum(new ArrayList<>());
                        po.setPlateName(new ArrayList<>());

                        po.setDate(rs.getNString(1));
                        po.getAverageOpen().add(rs.getNString(2));
                        po.getAverageClose().add(rs.getNString(4));
                        po.getAverageAdjClose().add(rs.getNString(4));
                        po.getPreAverageAdjClose().add(rs.getNString(2));
                        po.getIncreaseOrDecreaseMoney().add(rs.getNString(7));
                        po.getIncreaseOrDecreaseRate().add(rs.getNString(8));
                        po.getTotalVolume().add(rs.getNString(6));
                        result.add(po);
                    }
                    String platenum=Integer.toString(industryData.getPoolInfo(StockPool.SHENZHENG).getCode().size());
                    for(int i=0;i<result.size();i++){
                        result.get(i).getPlateName().add("深证");
                        result.get(i).getCompanyNum().add(platenum);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case HUSHEN300:
                sql="SELECT * FROM hs WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new TotalPlatePO();
                        po.setTotalVolume(new ArrayList<>());
                        po.setIncreaseOrDecreaseRate(new ArrayList<>());
                        po.setIncreaseOrDecreaseMoney(new ArrayList<>());
                        po.setPreAverageAdjClose(new ArrayList<>());
                        po.setAverageOpen(new ArrayList<>());
                        po.setAverageClose(new ArrayList<>());
                        po.setAverageAdjClose(new ArrayList<>());
                        po.setCompanyNum(new ArrayList<>());
                        po.setPlateName(new ArrayList<>());

                        po.setDate(rs.getNString(1));
                        po.getAverageOpen().add(rs.getNString(2));
                        po.getAverageClose().add(rs.getNString(4));
                        po.getAverageAdjClose().add(rs.getNString(4));
                        po.getPreAverageAdjClose().add(rs.getNString(2));
                        po.getIncreaseOrDecreaseMoney().add(rs.getNString(7));
                        po.getIncreaseOrDecreaseRate().add(rs.getNString(8));
                        po.getTotalVolume().add(rs.getNString(6));
                        result.add(po);
                    }
                    for(int i=0;i<result.size();i++){
                        result.get(i).getPlateName().add("沪深300");
                        result.get(i).getCompanyNum().add("300");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SMALLMIDDLEPLATE:
                sql="SELECT * FROM zxb WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new TotalPlatePO();
                        po.setTotalVolume(new ArrayList<>());
                        po.setIncreaseOrDecreaseRate(new ArrayList<>());
                        po.setIncreaseOrDecreaseMoney(new ArrayList<>());
                        po.setPreAverageAdjClose(new ArrayList<>());
                        po.setAverageOpen(new ArrayList<>());
                        po.setAverageClose(new ArrayList<>());
                        po.setAverageAdjClose(new ArrayList<>());
                        po.setCompanyNum(new ArrayList<>());
                        po.setPlateName(new ArrayList<>());

                        po.setDate(rs.getNString(1));
                        po.getAverageOpen().add(rs.getNString(2));
                        po.getAverageClose().add(rs.getNString(4));
                        po.getAverageAdjClose().add(rs.getNString(4));
                        po.getPreAverageAdjClose().add(rs.getNString(2));
                        po.getIncreaseOrDecreaseMoney().add(rs.getNString(7));
                        po.getIncreaseOrDecreaseRate().add(rs.getNString(8));
                        po.getTotalVolume().add(rs.getNString(6));
                        result.add(po);
                    }
                    String platenum=methodForPlate.getCompanyNum("middleandsmall");
                    for(int i=0;i<result.size();i++){
                        result.get(i).getPlateName().add("中小板");
                        result.get(i).getCompanyNum().add(platenum);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case STARTUPPLATE:
                sql="SELECT * FROM cyb WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new TotalPlatePO();
                        po.setTotalVolume(new ArrayList<>());
                        po.setIncreaseOrDecreaseRate(new ArrayList<>());
                        po.setIncreaseOrDecreaseMoney(new ArrayList<>());
                        po.setPreAverageAdjClose(new ArrayList<>());
                        po.setAverageOpen(new ArrayList<>());
                        po.setAverageClose(new ArrayList<>());
                        po.setAverageAdjClose(new ArrayList<>());
                        po.setCompanyNum(new ArrayList<>());
                        po.setPlateName(new ArrayList<>());

                        po.setDate(rs.getNString(1));
                        po.getAverageOpen().add(rs.getNString(2));
                        po.getAverageClose().add(rs.getNString(4));
                        po.getAverageAdjClose().add(rs.getNString(4));
                        po.getPreAverageAdjClose().add(rs.getNString(2));
                        po.getIncreaseOrDecreaseMoney().add(rs.getNString(7));
                        po.getIncreaseOrDecreaseRate().add(rs.getNString(8));
                        po.getTotalVolume().add(rs.getNString(6));
                        result.add(po);
                    }
                    String platenum=methodForPlate.getCompanyNum("startup");
                    for(int i=0;i<result.size();i++){
                        result.get(i).getPlateName().add("创业板");
                        result.get(i).getCompanyNum().add(platenum);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
        }
        Collections.reverse(result);
        return result;
    }

}
