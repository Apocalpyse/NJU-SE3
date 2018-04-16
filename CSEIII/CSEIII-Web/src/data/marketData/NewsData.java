package data.marketData;

import PO.marketPO.NewsPO;
import PO.marketPO.SimpleNewsPO;
import PO.userPO.SelfSelectStockPO;
import dataSer.NewsDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取新闻信息
 */
public class NewsData implements NewsDataSer {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";


    @Override
    public ArrayList<NewsPO> getNewsD(String date) {
        ArrayList<NewsPO> result=new ArrayList<>();
        sql="SELECT * FROM news WHERE time='"+date+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
               NewsPO newsPO=new NewsPO();
               newsPO.setNewsID(rs.getNString(1));
               newsPO.setClassify(rs.getNString(2));
               newsPO.setTitle(rs.getNString(3));
               newsPO.setTime(rs.getNString(4));
               newsPO.setContent(rs.getNString(6));
               newsPO.setPraise(rs.getInt(7));
               newsPO.setCriticize(rs.getInt(8));
               newsPO.getComment().add(rs.getNString(9));
               result.add(newsPO);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public NewsPO getNewsT(String newsID) {
        NewsPO result=new NewsPO();
        sql="SELECT * FROM news WHERE index="+newsID;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setTitle(rs.getNString(3));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NewsPO> getNewsM() {
        ArrayList<NewsPO> result=new ArrayList<>();
        sql="SELECT * FROM news";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                NewsPO newsPO=new NewsPO();
                newsPO.setTitle(rs.getNString(3));
                result.add(newsPO);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addPraise(String newsID) {
        boolean flag=false;
        sql="SELECT praise FROM news WHERE index='"+newsID+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int count=0;
            while (rs.next()){
                count=rs.getInt(1);
                count++;
            }
            sql="UPDATE news SET praise='"+count+"' WHERE index="+newsID;
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeQuery();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addCriticize(String newsID) {
        boolean flag=false;
        sql="SELECT criticize FROM news WHERE index='"+newsID+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int count=0;
            while (rs.next()){
                count=rs.getInt(1);
                count++;
            }
            sql="UPDATE news SET criticize='"+count+"' WHERE index="+newsID;
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeQuery();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addComment(String newsID, String com, String comAccount) {
        boolean flag=false;
        sql="SELECT comment FROM news WHERE index='"+newsID+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String comment=null;
            while (rs.next()){
                comment=rs.getString(1);
                comment=comment+" "+comAccount+" "+com;
            }
            sql="UPDATE news SET comment='"+comment+"' WHERE index="+newsID;
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeQuery();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addCollectionNews(String account, ArrayList<String> newsID) {
        boolean flag=false;
        sql="SELECT collectionnews FROM user WHERE account='"+account+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String comment=null;
            while (rs.next()){
                comment=rs.getString(1);
                for(int i=0;i<newsID.size();i++){
                    comment=comment+""+newsID.get(i);
                }
            }
            sql="UPDATE news SET comment='"+comment+"' WHERE index="+newsID;
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeQuery();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ArrayList<SimpleNewsPO> getCollectionNews(String account) {
        ArrayList<SimpleNewsPO> result=new ArrayList<>();
        NewsData newsData=new NewsData();
        sql="SELECT collectionnews FROM user WHERE account='"+account+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String newsid;
            while (rs.next()){
                newsid=rs.getNString(1);
                String ids[]=newsid.split(" ");
                for(int i=0;i<ids.length;i++){
                  NewsPO newsPO=new NewsPO();
                  newsPO=newsData.getNewsT(ids[i]);
                  result.add(new SimpleNewsPO(newsPO.getNewsID(),newsPO.getTitle(),newsPO.getClassify()));
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
    public boolean deleteCollectionNews(String account, ArrayList<String> newsID) {
        return false;
    }
}
