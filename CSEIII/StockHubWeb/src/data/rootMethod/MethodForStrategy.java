package data.rootMethod;

import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import PO.strategyPO.StrategyStockPO;
import data.MySqlConnecter.Connecter;
import data.stockData.FindStockInfoData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by delong chang on 2017/6/8.
 */
public class MethodForStrategy {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args){
        MethodForStrategy methodForStrategy=new MethodForStrategy();
        methodForStrategy.getStrategyData();
    }

    public String lastDay(String date){
        String result=null;
        String temp[]=date.split("-");
        if(temp[1].split("")[0]=="0"){
            temp[1]=temp[1].split("0")[0];
        }
        if(temp[2].split("")[0]=="0"){
            temp[2]=temp[2].split("0")[0];
        }
        if(Integer.parseInt(temp[2])-1==0){
            if(Integer.parseInt(temp[1])-1==0){
                temp[0]=Integer.toString(Integer.parseInt(temp[0])-1);
                result=temp[0]+"-12-31";
            }else {
                temp[1]=Integer.toString(Integer.parseInt(temp[1])-1);
                switch (temp[1]){
                    case "1":
                        result=temp[0]+"-0"+temp[1]+"-31";
                        break;
                    case "3":
                        result=temp[0]+"-0"+temp[1]+"-31";
                        break;
                    case "5":
                        result=temp[0]+"-0"+temp[1]+"-31";
                        break;
                    case "7":
                        result=temp[0]+"-0"+temp[1]+"-31";
                        break;
                    case "8":
                        result=temp[0]+"-0"+temp[1]+"-31";
                        break;
                    case "10":
                        result=temp[0]+"-"+temp[1]+"-31";
                        break;
                    case "12":
                        result=temp[0]+"-"+temp[1]+"-31";
                        break;
                    case "2":
                        result=temp[0]+"-0"+temp[1]+"-28";
                        break;
                    case "4":
                        result=temp[0]+"-0"+temp[1]+"-30";
                        break;
                    case "6":
                        result=temp[0]+"-0"+temp[1]+"-30";
                        break;
                    case "9":
                        result=temp[0]+"-0"+temp[1]+"-30";
                        break;
                    default:
                        result=temp[0]+"-"+temp[1]+"-30";
                        break;
                }
            }
        }else {
            temp[2]=Integer.toString(Integer.parseInt(temp[2])-1);
            if(Integer.parseInt(temp[2])<10){
                result=temp[0]+"-"+temp[1]+"-0"+temp[2];
            }else {
                result=temp[0]+"-"+temp[1]+"-"+temp[2];
            }
        }
        return result;
    }

    public ArrayList<StrategyStockPO> getStrategyData(StockPool stockPool, ArrayList<String> dates, String start, String end,ArrayList<String> codes){
        ArrayList<StrategyStockPO> result=new ArrayList<>();
        ArrayList<StrategyStockPO> temp=new ArrayList<>();
        String sql;
        MethodForStock methodForStock=new MethodForStock();
        start=methodForStock.convertDateBack(start);
        end=methodForStock.convertDateBack(end);
        StrategyStockPO po=new StrategyStockPO();
        po.setAdjClose(new ArrayList<>());
        po.setDate(new ArrayList<>());
        switch (stockPool){
            case ALL:
                sql="SELECT code,date,adjclose,name FROM strategy WHERE date BETWEEN '"+start+"' AND '"+end+"' GROUP BY code ORDER BY code DESC";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StrategyStockPO();
                        po.setDate(new ArrayList<>());
                        po.setAdjClose(new ArrayList<>());
                        po.setCode(rs.getNString(1));
                        po.setName(rs.getNString(4));
                        po.getDate().add(rs.getNString(2));
                        po.getAdjClose().add(rs.getNString(3));
                        System.out.println(rs.getNString(2));
                        temp.add(po);
                    }
                     result=temp;
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SHANGZHENG:
                sql="SELECT code,date,adjclose,name FROM strategy WHERE date BETWEEN '"+start+"' AND '"+end+"'AND plate='上证' GROUP BY code ORDER BY code DESC";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po=new StrategyStockPO();
                        po.setDate(new ArrayList<>());
                        po.setAdjClose(new ArrayList<>());
                        po.setCode(rs.getNString(1));
                        po.setName(rs.getNString(4));
                        po.getDate().add(rs.getNString(2));
                        po.getAdjClose().add(rs.getNString(3));
                        temp.add(po);
                    }
                    result=temp;
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SHENZHENG:
                sql="SELECT code,date,adjclose,name FROM strategy WHERE date BETWEEN '"+start+"' AND '"+end+"'AND plate='深证' GROUP BY code ORDER BY code DESC";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po.setCode(rs.getNString(1));
                        po.setName(rs.getNString(4));
                        po.getDate().add(rs.getNString(2));
                        po.getAdjClose().add(rs.getNString(3));
                        temp.add(po);
                    }
                    result=temp;
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case HUSHEN300:
                sql="SELECT code,date,adjclose,name FROM strategy WHERE date BETWEEN '"+start+"' AND '"+end+"'AND plate='沪深300' GROUP BY code ORDER BY code DESC";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po.setCode(rs.getNString(1));
                        po.setName(rs.getNString(4));
                        po.getDate().add(rs.getNString(2));
                        po.getAdjClose().add(rs.getNString(3));
                        temp.add(po);
                    }
                   result=temp;
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SMALLMIDDLEPLATE:
                sql="SELECT code,date,adjclose,name FROM strategy WHERE date BETWEEN '"+start+"' AND '"+end+"'AND plate='中小板' GROUP BY code ORDER BY code DESC";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po.setCode(rs.getNString(1));
                        po.setName(rs.getNString(4));
                        po.getDate().add(rs.getNString(2));
                        po.getAdjClose().add(rs.getNString(3));
                        temp.add(po);
                    }
                    result=temp;
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case STARTUPPLATE:
                sql="SELECT code,date,adjclose,name FROM strategy WHERE date BETWEEN '"+start+"' AND '"+end+"'AND plate='创业板' GROUP BY code ORDER BY code DESC";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    while (rs.next()){
                        po.setCode(rs.getNString(1));
                        po.setName(rs.getNString(4));
                        po.getDate().add(rs.getNString(2));
                        po.getAdjClose().add(rs.getNString(3));
                        temp.add(po);
                    }
                   result=temp;
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SELECTPLATE:
                for(int i=0;i<codes.size();i++){
                    sql="SELECT * FROM s"+codes.get(i)+" WHERE date BETWEEN '"+start+"' AND '"+end+"'";
                    try{
                        Connecter connecter=new Connecter();
                        con=connecter.getConnection();
                        preparedStatement=con.prepareStatement(sql);
                        ResultSet rs=preparedStatement.executeQuery();
                        while (rs.next()){
                            po.setCode(rs.getNString(19));
                            po.setName(rs.getNString(20));
                            po.getDate().add(rs.getNString(1));
                            po.getAdjClose().add(rs.getNString(4));
                            temp.add(po);
                        }
                        result=temp;
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
        return result;
    }

    public void getStrategyData(){
        ArrayList<StockPO> a=new ArrayList<>();
        FindStockInfoData b=new FindStockInfoData();
        MethodForStock methodForStock=new MethodForStock();
       try{
           String sql="SELECT date FROM s000001";
           Connecter connecter=new Connecter();
           con=connecter.getConnection();
           preparedStatement=con.prepareStatement(sql);
           ResultSet rs=preparedStatement.executeQuery();
           while (rs.next()){
               a=b.findStockInfoOneday(methodForStock.convertDateBack(rs.getNString(1)));
               for(int i=0;i<a.size();i++){
                   sql="INSERT INTO strategy VALUES ('"+a.get(i).getDate()+"', '"+a.get(i).getAdjClose()+"', '"+a.get(i).getCode()+"', '"+a.get(i).getName()+"', '"+a.get(i).getPlate()+"')";
                   preparedStatement=con.prepareStatement(sql);
                   preparedStatement.execute();
               }
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
}
