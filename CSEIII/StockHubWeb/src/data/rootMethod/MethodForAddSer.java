package data.rootMethod;

import PO.stockPO.EvaluateStockS;
import data.MySqlConnecter.Connecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delong chang on 2017/6/12.
 */
public class MethodForAddSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public EvaluateStockS getEvaluateStock(String code,String start,String end){
        EvaluateStockS result=new EvaluateStockS();
        result.setAdjClose(new ArrayList<>());
        result.setiOrD(new ArrayList<>());
        result.setVolume(new ArrayList<>());
        sql="SELECT * FROM s"+code+" WHERE date BETWEEN '"+start+"' AND '"+end+"'";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setCode(code);
                result.getAdjClose().add(rs.getNString(4));
                result.getiOrD().add(rs.getNString(8));
                result.getVolume().add(rs.getNString(6));
                result.setName(rs.getNString(20));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public String convertDateForNews(String date){
        String temp[]=date.split("/");
        if(Integer.parseInt(temp[0])<10){
            temp[0]="0"+temp[0];
        }
        if(Integer.parseInt(temp[1])<10){
            temp[1]="0"+temp[1];
        }
        date=temp[0]+"-"+temp[1];
        return date;
    }

}
