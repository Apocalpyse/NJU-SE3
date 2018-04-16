package data.userData;

import PO.userPO.BuyPO;
import PO.userPO.HoldPO;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForStock;
import data.rootMethod.MethodForUser;
import dataSer.BuyStockDataSer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by A on 2017/5/21.
 * 用户模拟交易信息的操作
 */
public class BuyStockData implements BuyStockDataSer {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;


    @Override
    public boolean realBuy(BuyPO buyPOS) {
        boolean result=false;
        MethodForUser methodForUser=new MethodForUser();
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();

            /**
             *记录交易信息
             */

            String buy=null;
            if(buyPOS.isBuy()){
                buy="买";
            }else {
                buy="卖";
            }

            sql="INSERT INTO buy(account,date,code,volume,bors) VALUE ('"+buyPOS.getAccount()+"' '"+buyPOS.getDate()+"' '"+buyPOS.getCode()+"' '"+buyPOS.getVolume()+"' '"+buy+"')";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();

            /**
             *更新用户持有 股票信息
             */
             methodForUser.updateHold(buyPOS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        result=true;
        return result;
    }

    @Override
    public ArrayList<BuyPO> getRealBuy(String account, String start, String end) {
        ArrayList<BuyPO> result=new ArrayList<>();
        BuyPO po;
        sql="SELECT * FROM buy WHERE account='"+account+"' AND date BETWEEN '"+start+"' AND '"+end+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                po=new BuyPO();
                po.setAccount(rs.getNString(1));
                po.setDate(rs.getNString(2));
                po.setCode(rs.getNString(3));
                po.setVolume(rs.getNString(4));
                switch (rs.getNString(5)){
                    case "买":
                        po.setBuy(true);
                        break;
                    case "卖":
                        po.setBuy(false);
                        break;
                }
                result.add(po);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public HoldPO getHoldBuy(String account, String date) {
        HoldPO result=new HoldPO();
        result.setHoldCode(new ArrayList<>());
        result.setHoldCopies(new ArrayList<>());
        result.setHoldMoney(new ArrayList<>());
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            sql="SELECT * FROM hold WHERE account='"+account+"' AND date='"+date+"'";
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setDate(methodForStock.convertDateBack(date));
                result.setAccount(account);
                result.setTotalMoney(rs.getNString(3));
                result.getHoldCode().add(rs.getNString(4));
                result.getHoldMoney().add(rs.getNString(5));
                result.getHoldCopies().add(rs.getNString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getNextDay(String date) {
        String result=null;
        boolean find=false;
        ArrayList<String> dates=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        sql="SELECT date FROM s000006";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                dates.add(rs.getNString(1));
            }
            for(int i=0;i<dates.size();i++){
                Date d1=df.parse(date);
                dates.set(i,methodForStock.convertData(dates.get(i)));
                Date d2=df.parse(dates.get(i));
                if(d1.getTime()>=d2.getTime()){
                    result=dates.get(i-1);
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
