package data.rootMethod;

import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import data.MySqlConnecter.Connecter;
import data.strategyData.StrategyData;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by delong chang on 2017/6/8.
 * StockData中调用的方法
 */
public class MethodForStock {

    private PreparedStatement preparedStatement;
    private Connection con ;

    public static void main(String[] args){
        MethodForStock methodForStock=new MethodForStock();
        methodForStock.getRecent();
    }

    public String convertData(String str){
        String[] sta = str.split("/");
        sta[2] = "20" + sta[2];
        if(Integer.parseInt(sta[0])<10){
            sta[0] = "0" + sta[0];
        }
        if(Integer.parseInt(sta[1])<10){
            sta[1] = "0" + sta[1];
        }
        String time = sta[2] + "-" + sta[0] + "-" + sta[1];
        return time;
    }

    public void insertColum(){
        String sql="SELECT code FROM codelist";
        ArrayList<String> codes=new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con ;
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                codes.add(rs.getNString(1));
            }
            for(int i=0;i<codes.size();i++){
                sql="ALTER TABLE s"+codes.get(i)+" ADD COLUMN Market VARCHAR(20) NOT NULL , ADD COLUMN Plate VARCHAR(20) NOT NULL , ADD COLUMN Industry VARCHAR(20) NOT NULL , ADD COLUMN code VARCHAR(20) NOT NULL , ADD COLUMN name VARCHAR(20) NOT NULL";
                preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getTables(){
        ArrayList<String> codes=new ArrayList<>();
        ArrayList<String> names=new ArrayList<>();
        ArrayList<String> industry=new ArrayList<>();
        ArrayList<String> market=new ArrayList<>();
        ArrayList<String> plate=new ArrayList<>();
        String sql="SELECT code,name,industry FROM codelist";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                codes.add(rs.getNString(1));
                names.add(rs.getNString(2));
                industry.add(rs.getNString(3));
                market.add("");
                plate.add("");
            }
            for(int i=0;i<codes.size();i++){
                if(codes.get(i).split("")[0].equals("6")){
                    market.set(i,"上证");
                    plate.set(i,"上证");
                }
                if(codes.get(i).split("")[2].equals("0")){
                    market.set(i,"深证");
                    plate.set(i,"深证");
                }
                if(codes.get(i).split("")[2].equals("2")){
                    market.set(i,"深证");
                    plate.set(i,"中小板");
                }
                if(codes.get(i).split("")[2].equals("3")){
                    market.set(i,"深证");
                    plate.set(i,"创业板");
                }
                sql="SELECT code FROM hs300";
                preparedStatement=con.prepareStatement(sql);
                ResultSet result=preparedStatement.executeQuery();
                while (result.next()){
                    if(codes.get(i).equals(result.getNString(1))){
                        plate.set(i,"沪深300");
                    }
                }
            }
            ArrayList<String> restCode=new ArrayList<>();
            int i=0;
            for(;i<codes.size();i++){
                restCode.add(codes.get(i));
            }
            for(int h=0;h<restCode.size();h++){
                sql="SELECT date FROM s"+restCode.get(h);
                ArrayList<String> dates=new ArrayList<>();
                try{
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                        dates.add(resultSet.getNString(1));
                    }
                    for(int j=0;j<dates.size();j++){
                        sql="UPDATE s"+restCode.get(h)+" SET name='"+names.get(h)+"', code='"+codes.get(h)+"', plate='"+plate.get(h)+"', market='"+market.get(h)+"', industry='"+industry.get(h)+"' WHERE date='"+dates.get(j)+"'";
                        try{
                            preparedStatement=con.prepareStatement(sql);
                            preparedStatement.execute();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getRecent(){
        ArrayList<StockPO> po=new ArrayList<>();
        ArrayList<String> code=new ArrayList<>();
        StockPO stockPO;
        String sql="SELECT code FROM codeList";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                code.add(rs.getNString(1));
            }
            for(int i=0;i<code.size();i++){
                sql="SELECT * FROM s"+code.get(i)+" WHERE date BETWEEN '2017-05-01' AND '2017-06-14'";
                preparedStatement=con.prepareStatement(sql);
                ResultSet result=preparedStatement.executeQuery();
                while (result.next()){
                   stockPO=new StockPO();
                   stockPO.setDate(result.getNString(1));
                   stockPO.setOpen(result.getNString(2));
                   stockPO.setHigh(result.getNString(3));
                   stockPO.setClose(result.getNString(4));
                   stockPO.setLow(result.getNString(5));
                   stockPO.setVolume(result.getNString(6));
                   stockPO.setIncreaseOrDecrease(result.getNString(8));
                   stockPO.setAdjClose(result.getNString(4));
                   stockPO.setPreAdjClose(result.getNString(2));
                   stockPO.setCode(result.getNString(19));
                   stockPO.setName(result.getNString(20));
                   stockPO.setIndustry(result.getNString(18));
                   stockPO.setMarket(result.getNString(16));
                   stockPO.setPlate(result.getNString(17));
                   po.add(stockPO);
                }
            }
            System.out.println("start");
            for(int i=0;i<po.size();i++){
                sql="INSERT INTO recent VALUES ('"+po.get(i).getDate()+"', '"+po.get(i).getOpen()+"', '"+po.get(i).getHigh()+"', '"+po.get(i).getClose()+"', '"+po.get(i).getLow()+"', '"+po.get(i).getVolume()+"', '"+po.get(i).getIncreaseOrDecrease()+"', '"+po.get(i).getAdjClose()+"', '"+po.get(i).getPreAdjClose()+"', '"+po.get(i).getCode()+"', '"+po.get(i).getName()+"', '"+po.get(i).getIndustry()+"', '"+po.get(i).getMarket()+"', '"+po.get(i).getPlate()+"')";
                preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
            }
            System.out.println("end");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String convertDateBack(String date){
        String result=null;
        String temp[]=date.split("-");
        temp[0]=temp[0].split("20")[1];
        if(temp[1].split("0")[0].equals("")){
            temp[1]=temp[1].split("0")[1];
        }
        if(temp[2].split("0")[0].equals("")){
            temp[2]=temp[2].split("0")[1];
        }
        result=temp[1]+"/"+temp[2]+"/"+temp[0];
        return result;
    }

    public ArrayList<StockPO> dealResultSet(ResultSet rs){
        ArrayList<StockPO> stockPOS=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        String sql;
        StockPO po;
        try{
            while (rs.next()){
                po=new StockPO();
                po.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                po.setOpen(rs.getNString(2));
                po.setHigh(rs.getNString(3));
                po.setClose(rs.getNString(4));
                po.setLow(rs.getNString(5));
                po.setVolume(rs.getNString(6));
                po.setIncreaseOrDecrease(rs.getNString(8));
                po.setAdjClose(rs.getNString(4));
                po.setPreAdjClose(rs.getNString(2));
                po.setPlate(rs.getNString(17));
                po.setMarket(rs.getNString(16));
                po.setIndustry(rs.getNString(18));
                po.setName(rs.getNString(20));
                po.setCode(rs.getNString(19));
                stockPOS.add(po);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return stockPOS;
    }

    public ArrayList<StockPO> getRecentPO(ResultSet rs, StockPool stockPool){
        ArrayList<StockPO> result=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        StockPO temp;
        try{
            while (rs.next()){
                temp=new StockPO();
                temp.setDate(methodForStock.convertDateBack(rs.getNString(1)));
                temp.setOpen(rs.getNString(2));
                temp.setHigh(rs.getNString(3));
                temp.setClose(rs.getNString(4));
                temp.setLow(rs.getNString(5));
                temp.setVolume(rs.getNString(6));
                temp.setIncreaseOrDecrease(rs.getNString(7));
                temp.setAdjClose(rs.getNString(8));
                temp.setPreAdjClose(rs.getNString(9));
                temp.setName(rs.getNString(11));
                temp.setCode(rs.getNString(10));
                temp.setIndustry(rs.getNString(12));
                temp.setMarket(rs.getNString(13));
                temp.setPlate(rs.getNString(14));
                switch (stockPool){
                    case ALL:
                        result.add(temp);
                        break;
                    case SHANGZHENG:
                        if(temp.getPlate().equals("上证")){
                            result.add(temp);
                        }
                        break;
                    case SHENZHENG:
                        if(temp.getPlate().equals("深证")){
                            result.add(temp);
                        }
                        break;
                    case HUSHEN300:
                       if(temp.getPlate().equals("沪深300")){
                           result.add(temp);
                       }
                       break;
                    case SMALLMIDDLEPLATE:
                        if(temp.getPlate().equals("中小板")){
                            result.add(temp);
                        }
                        break;
                    case STARTUPPLATE:
                        if(temp.getPlate().equals("创业板")){
                            result.add(temp);
                        }
                        break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
