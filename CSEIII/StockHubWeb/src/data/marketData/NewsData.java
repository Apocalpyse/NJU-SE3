package data.marketData;

import PO.marketPO.NewsPO;
import PO.marketPO.SimpleNewsPO;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForAddSer;
import data.rootMethod.MethodForNews;
import data.rootMethod.MethodForStock;
import dataSer.NewsDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 获取新闻信息
 * 完成
 */
public class NewsData implements NewsDataSer {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args){
        NewsData a=new NewsData();
        ArrayList<NewsPO> b=a.getNewsM();
    }

    @Override
    public ArrayList<NewsPO> getNewsD(String date){
        ArrayList<NewsPO> result=new ArrayList<>();
        MethodForAddSer methodForAddSer=new MethodForAddSer();
        MethodForNews methodForNews=new MethodForNews();
        date=methodForAddSer.convertDateForNews(date);
        sql="SELECT * FROM news WHERE time LIKE '%"+date+"%'";
        System.out.println(sql);
        try{
            Connecter connecter=new Connecter();
            con= connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result=methodForNews.dealWithResult(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public NewsPO getNewsT(String newsID) {
        NewsPO result=new NewsPO();
        ArrayList<NewsPO> temp;
        MethodForNews methodForNews=new MethodForNews();
        sql="SELECT * FROM news WHERE id='"+newsID+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            temp=new ArrayList<>();
            temp=methodForNews.dealWithResult(rs);
            result=temp.get(0);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NewsPO> getNewsM() {
        ArrayList<NewsPO> result=new ArrayList<>();
        MethodForNews methodForNews=new MethodForNews();
        sql="SELECT * FROM news";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result=methodForNews.dealWithResult(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addPraise(String newsID) {
        boolean flag=false;
        sql="SELECT parse FROM news WHERE id='"+newsID+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int count=0;
            while (rs.next()){
                count=rs.getInt(1);
                count++;
            }
            sql="UPDATE news SET parse='"+count+"' WHERE id='"+newsID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addCriticize(String newsID) {
        boolean flag=false;
        sql="SELECT criticize FROM news WHERE id='"+newsID+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int count=0;
            while (rs.next()){
                count=rs.getInt(1);
                count++;
            }
            sql="UPDATE news SET criticize='"+count+"' WHERE id='"+newsID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addView(String newsID) throws Exception {
        boolean flag=false;
        sql="SELECT view FROM news WHERE id='"+newsID+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            int count=0;
            while (rs.next()){
                count=rs.getInt(1);
                count++;
            }
            sql="UPDATE news SET view='"+count+"' WHERE id='"+newsID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addComment(String newsID, String com, String comAccount) {
        boolean flag=false;
        sql="SELECT * FROM news WHERE id='"+newsID+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String comment=null;
            String commentAccount=null;
            while (rs.next()){
                comment=rs.getNString(10);
                commentAccount=rs.getNString(11);
                if(!comment.equals("")&&!commentAccount.equals("")){
                    comment=comment+" "+com;
                    commentAccount=commentAccount+" "+comAccount;
                }else {
                    comment=com;
                    commentAccount=comAccount;
                }
            }
            sql="UPDATE news SET comment='"+comment+"', commentAccount='"+commentAccount+"' WHERE id='"+newsID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addCollectionNews(String account, ArrayList<String> newsID) {
        boolean flag=false;
        sql="SELECT collectionnew FROM user WHERE account='"+account+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            ArrayList<String> col=new ArrayList<>();
            String newcol="";
            while (rs.next()){
                if(rs.getNString(1)==null){
                   for(int i=0;i<newsID.size();i++){
                       newcol=newcol+newsID.get(i)+" ";
                   }
                }else {
                    String temp[] = rs.getNString(1).split(" ");
                    for (int i = 0; i < temp.length; i++) {
                        col.add(temp[i]);
                    }
                    for (int j = 0; j < newsID.size(); j++) {
                        col.add(newsID.get(j));
                    }
                    for (int k = 0; k < col.size(); k++) {
                        if (newcol.equals("")) {
                            newcol = col.get(k);
                        } else {
                            newcol = newcol + " " + col.get(k);
                        }
                    }
                }
            }
            sql="UPDATE user SET collectionnew='"+newcol+"' WHERE account='"+account+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ArrayList<SimpleNewsPO> getCollectionNews(String account) {
        ArrayList<SimpleNewsPO> result=new ArrayList<>();
        NewsData newsData=new NewsData();
        sql="SELECT collectionnew FROM user WHERE account='"+account+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
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
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteCollectionNews(String account, ArrayList<String> newsID) {
        boolean flag=false;
        sql="SELECT collectionnew FROM user WHERE account='"+account+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            ArrayList<String> col=new ArrayList<>();
            String newcol="";
            while (rs.next()){
                String temp[]=rs.getNString(1).split(" ");
                for(int i=0;i<temp.length;i++){
                    col.add(temp[i]);
                }
                for(int j=0;j<newsID.size();j++){
                    for(int k=0;k<col.size();k++){
                        if(newsID.get(j).equals(col.get(k))){
                            col.remove(k);
                        }
                    }
                }
                for(int k=0;k<col.size();k++){
                    if(newcol.equals("")){
                        newcol=col.get(k);
                    }else {
                        newcol=newcol+" "+col.get(k);
                    }
                }
            }
            sql="UPDATE user SET collectionnew='"+newcol+"' WHERE account='"+account+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ArrayList<SimpleNewsPO> getAllNewsVO() {
        ArrayList<SimpleNewsPO> result=new ArrayList<>();
        ArrayList<NewsPO> news=new ArrayList<>();
        MethodForNews methodForNews=new MethodForNews();
        sql="SELECT * FROM news";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            news=methodForNews.dealWithResult(rs);
            for(int i=0;i<news.size();i++){
                result.add(new SimpleNewsPO(news.get(i).getNewsID(),news.get(i).getTitle(),news.get(i).getClassify()));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isAlreadyCollection(String account, String newsID) throws Exception {
        boolean result=false;
        ArrayList<String> collections=new ArrayList<>();
        sql="SELECT collectionnew FROM user WHERE account='"+account+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                for(int i=0;i<rs.getNString(1).split(" ").length;i++){
                    collections.add(rs.getNString(1).split(" ")[i]);
                }
                for(int i=0;i<collections.size();i++){
                    if(collections.get(i).equals(newsID)){
                        result=true;
                        break;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
