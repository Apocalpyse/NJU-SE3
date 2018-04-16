package data.rootMethod;

import PO.marketPO.NewsPO;
import data.MySqlConnecter.Connecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delong chang on 2017/6/13.
 */
public class MethodForNews {

    private PreparedStatement preparedStatement;
    private Connection con ;

//    public static void main(String[] args){
//        MethodForNews methodForNews=new MethodForNews();
//        methodForNews.initNews();
//    }

    public ArrayList<NewsPO> dealWithResult(ResultSet rs){
        ArrayList<NewsPO> a=new ArrayList<>();
        NewsPO result;
        try{
            while (rs.next()){
               result=new NewsPO();
               result.setComment(new ArrayList<>());
               result.setCommentAccount(new ArrayList<>());
               result.setNewsID(rs.getNString(12));
               result.setTitle(rs.getNString(3));
               result.setClassify(rs.getNString(2));
               result.setTime(rs.getNString(4));
               result.setContent(rs.getNString(6));
               result.setPraise(rs.getInt(7));
               result.setCriticize(rs.getInt(8));
               result.setView(rs.getInt(9));
               String comment[]=rs.getNString(10).split(" ");
               String commentAccount[]=rs.getNString(11).split(" ");
               for(int i=0;i<comment.length;i++){
                   result.getComment().add(comment[i]);
                   result.getCommentAccount().add(commentAccount[i]);
               }
               a.add(result);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return a;
    }

    public void initNews(){
       String sql="SELECT * FROM invest";
       try{
           Connecter connecter=new Connecter();
           con=connecter.getConnection();
           preparedStatement=con.prepareStatement(sql);
           ResultSet rs=preparedStatement.executeQuery();
           int i=0;
           while (rs.next()){
               sql="UPDATE invest SET id='"+i+"' WHERE title='"+rs.getNString(2)+"'";
               preparedStatement=con.prepareStatement(sql);
               preparedStatement.execute();
               i++;
               System.out.println("init");
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

}
