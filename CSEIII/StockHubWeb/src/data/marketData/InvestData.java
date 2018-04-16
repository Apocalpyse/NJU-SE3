package data.marketData;

import PO.marketPO.InvestPO;
import PO.marketPO.SimpleInvestPO;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForNews;
import data.rootMethod.MethodForStock;
import dataSer.InvestDataSer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delong chang on 2017/6/10.
 */
public class InvestData implements InvestDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    @Override
    public ArrayList<InvestPO> getInvestD(String date) throws Exception {
        ArrayList<InvestPO> result=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        sql="SELECT * FROM invest WHERE date='"+date+"'";
        try{
            Connecter connecter=new Connecter();
            con= connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public InvestPO getInvestT(String investID) throws Exception {
        InvestPO result=new InvestPO();
        result.setComment(new ArrayList<>());
        result.setCommentAccount(new ArrayList<>());
        sql="SELECT * FROM investnews WHERE id='"+investID+"'";
        try{
            Connecter connecter=new Connecter();
            con= connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setInvestID(rs.getNString(1));
                result.setTitle(rs.getNString(3));
                result.setTime(rs.getNString(4));
                result.setContent(rs.getNString(6));
                result.setAuthor(rs.getNString(2));
                result.setPraise(rs.getInt(7));
                result.setCriticize(rs.getInt(8));
                result.setView(rs.getInt(9));
//                String comment[]=rs.getNString(10).split(" ");
//                String commentAccount[]=rs.getNString(11).split(" ");
//                for(int i=0;i<comment.length;i++){
//                    result.getComment().add(comment[i]);
//                    result.getCommentAccount().add(commentAccount[i]);
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<InvestPO> getInvestM() throws Exception {
        ArrayList<InvestPO> po=new ArrayList<>();
        InvestPO result=new InvestPO();
        sql="SELECT * FROM investnews";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.setInvestID(rs.getNString(1));
                result.setTitle(rs.getNString(3));
                result.setTime(rs.getNString(4));
                result.setContent(rs.getNString(6));
                result.setAuthor(rs.getNString(2));
                result.setPraise(rs.getInt(7));
                result.setCriticize(rs.getInt(8));
                result.setView(rs.getInt(9));
                String comment[]=rs.getNString(10).split(" ");
                String commentAccount[]=rs.getNString(11).split(" ");
                for(int i=0;i<comment.length;i++){
                    result.getComment().add(comment[i]);
                    result.getCommentAccount().add(commentAccount[i]);
                }
                po.add(result);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return po;
    }

    @Override
    public boolean addPraise(String investID) throws Exception {
        boolean flag=false;
        sql="SELECT parse FROM investnews WHERE id='"+investID+"'";
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
            sql="UPDATE investnews SET parse='"+count+"' WHERE id='"+investID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addCriticize(String investID) throws Exception {
        boolean flag=false;
        sql="SELECT criticize FROM investnews WHERE id='"+investID+"'";
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
            sql="UPDATE investnews SET criticize='"+count+"' WHERE id='"+investID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addView(String investID) throws Exception {
        boolean flag=false;
        sql="SELECT view FROM investnews WHERE id='"+investID+"'";
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
            sql="UPDATE investnews SET view='"+count+"' WHERE id='"+investID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addComment(String invsetID, String com, String comAccount) throws Exception {
        boolean flag=false;
        sql="SELECT * FROM investnews WHERE id='"+invsetID+"'";
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
            sql="UPDATE investnews SET comment='"+comment+"', commentAccount='"+commentAccount+"' WHERE id='"+invsetID+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addCollectionInvest(String account, ArrayList<String> investID) throws Exception {
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
                    for(int i=0;i<investID.size();i++){
                        newcol=newcol+investID.get(i)+" ";
                    }
                }else {
                    String temp[] = rs.getNString(1).split(" ");
                    for (int i = 0; i < temp.length; i++) {
                        col.add(temp[i]);
                    }
                    for (int j = 0; j < investID.size(); j++) {
                        col.add(investID.get(j));
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
    public ArrayList<SimpleInvestPO> getCollectionInvest(String account) throws Exception {
        ArrayList<SimpleInvestPO> result=new ArrayList<>();
        InvestData investData=new InvestData();
        sql="SELECT collectioninvest FROM user WHERE account='"+account+"'";
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
                    InvestPO investPO=investData.getInvestT(ids[i]);
                    result.add(new SimpleInvestPO(investPO.getInvestID(),investPO.getTitle(),investPO.getAuthor()));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteCollectionInvest(String account, ArrayList<String> investID) throws Exception {
        boolean flag=false;
        sql="SELECT collectionnews FROM user WHERE account='"+account+"'";
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
                for(int j=0;j<investID.size();j++){
                    for(int k=0;k<col.size();k++){
                        if(investID.get(j).equals(col.get(k))){
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
            sql="UPDATE user SET collectioninvest='"+newcol+"' WHERE account='"+account+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ArrayList<SimpleInvestPO> getAllInvestVO() throws Exception {
        ArrayList<SimpleInvestPO> re=new ArrayList<>();
        ArrayList<InvestPO> investPOS=new ArrayList<>();
        InvestPO result;
        MethodForNews methodForNews=new MethodForNews();
        sql="SELECT * FROM investnews";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result=new InvestPO();
                result.setComment(new ArrayList<>());
                result.setCommentAccount(new ArrayList<>());
                result.setInvestID(rs.getNString(1));
                result.setTitle(rs.getNString(3));
                result.setTime(rs.getNString(4));
                result.setContent(rs.getNString(6));
                result.setPraise(rs.getInt(7));
                result.setCriticize(rs.getInt(8));
                result.setView(rs.getInt(9));
//                String comment[]=rs.getNString(10).split(" ");
//                String commentAccount[]=rs.getNString(11).split(" ");
//                for(int i=0;i<comment.length;i++){
//                    result.getComment().add(comment[i]);
//                    result.getCommentAccount().add(commentAccount[i]);
//                }
                if(result.getContent()!=null){
                    re.add(new SimpleInvestPO(result.getInvestID(),result.getTitle(),result.getAuthor()));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(re.size());
        return re;
    }

    @Override
    public boolean isAlreadyCollection(String account, String investID) throws Exception {
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
                    if(collections.get(i).equals(investID)){
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
