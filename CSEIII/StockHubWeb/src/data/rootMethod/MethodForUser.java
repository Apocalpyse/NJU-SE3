package data.rootMethod;

import PO.userPO.BuyPO;
import PO.userPO.HoldPO;
import data.MySqlConnecter.Connecter;
import data.stockData.FindStockInfoData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delong chang on 2017/6/8.
 */
public class MethodForUser {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public void updateHold(BuyPO buyPO){
        HoldPO holdPO;
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        ArrayList<String> code=new ArrayList<>();
        ArrayList<String> money=new ArrayList<>();
        ArrayList<String> copies=new ArrayList<>();
        sql="SELECT * FROM hold WHERE account='"+buyPO.getAccount()+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String total=null;
                holdPO=new HoldPO();
                holdPO.setHoldMoney(new ArrayList<>());
                holdPO.setHoldCopies(new ArrayList<>());
                holdPO.setHoldCode(new ArrayList<>());
                holdPO.setAccount(rs.getNString(1));
                holdPO.setDate(rs.getNString(2));
                holdPO.setTotalMoney(rs.getNString(3));
                for(int i=0;i<rs.getNString(4).split(" ").length;i++){
                    code.add(rs.getNString(4).split(" ")[i]);
                    money.add(rs.getNString(5).split(" ")[i]);
                    copies.add(rs.getNString(6).split(" ")[i]);
                }
                for(int i=0;i<code.size();i++){
                    if(code.get(i).equals(buyPO.getCode())&&buyPO.isBuy()){
                        money.set(i,findStockInfoData.findStockInfoByCode(buyPO.getDate(),buyPO.getDate(),buyPO.getCode()).get(0).getClose());
                        copies.set(i,Double.toString(Double.parseDouble(copies.get(i))+Double.parseDouble(buyPO.getVolume())));
                    }else if(code.get(i).equals(buyPO.getCode())){
                        money.set(i,findStockInfoData.findStockInfoByCode(buyPO.getDate(),buyPO.getDate(),buyPO.getCode()).get(0).getClose());
                        copies.set(i,Double.toString(Double.parseDouble(copies.get(i))-Double.parseDouble(buyPO.getVolume())));
                    }
                    total=Double.toString(Double.parseDouble(total)+Double.parseDouble(money.get(i))*Double.parseDouble(copies.get(i)));
                }
                String c=null;
                String m=null;
                String p=null;
                for(int i=0;i<code.size();i++){
                    if(i==0){
                        c=code.get(i);
                        m=money.get(i);
                        p=copies.get(i);
                    }else {
                        c=c+" "+code.get(i);
                        m=m+" "+money.get(i);
                        p=p+" "+copies.get(i);
                    }
                }
                sql="UPDATE hold SET holdcode='"+c+"', holdmoney='"+m+"', holdcopies='"+p+"', totalmoney='"+total+"', date='"+buyPO.getDate()+"' WHERE account='"+buyPO.getAccount()+"'";
                preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
