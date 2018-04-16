package data.userData;

import PO.userPO.InputLoginPO;
import PO.userPO.SelfSelectStockPO;
import PO.userPO.UserPO;
import dataSer.UserDataSer;

import java.sql.*;

/**
 * Created by A on 2017/5/21.
 * 对用户信息的操作
 * 全部实现
 */
public class UserData implements UserDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    @Override
    public boolean register(UserPO userPO) {
        boolean flag=false;
        sql="SELECT * FROM user WHERE account="+userPO.getAccount();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                flag=false;
            }else {
                flag=true;
                sql="INSERT INTO user(account,password,username,realname,mail,birth,phone) VALUE ('"+userPO.getAccount()+"','"+userPO.getPassword()+"','"+userPO.getUsername()+userPO.getPassword()+"','"+userPO.getRealName()+"','"+userPO.getMail()+"','"+userPO.getBirth()+"','"+userPO.getPhone()+"')";
                preparedStatement=con.prepareStatement(sql);
                preparedStatement.executeQuery();
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean login(InputLoginPO inputLoginPO) {
        boolean flag=false;
        sql="SELECT * FROM user WHERE account='"+inputLoginPO.getAccount()+"' AND password='"+inputLoginPO.getPassword()+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                flag=true;
            }else {
                flag=false;
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean changeUserInfo(UserPO userPO) {
        boolean flag=false;
        sql="UPDATE user SET username='"+userPO.getUsername()+"', realname='"+userPO.getRealName()+"', mail='"+userPO.getMail()+"', birth='"+userPO.getBirth()+"', phone='"+userPO.getPhone()+"' WHERE account="+userPO.getAccount();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public UserPO getUserInfo(String account) {
        UserPO userPO=new UserPO();
        sql="SELECT * FROM user WHERE account='"+account+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                userPO.setAccount(account);
                userPO.setPassword(rs.getNString(2));
                userPO.setUsername(rs.getNString(3));
                userPO.setRealName(rs.getNString(4));
                userPO.setMail(rs.getNString(5));
                userPO.setBirth(rs.getNString(6));
                userPO.setPhone(rs.getNString(7));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userPO;
    }

    @Override
    public SelfSelectStockPO getSelfSelectStock(String account) {
        SelfSelectStockPO selfSelectStockPO=new SelfSelectStockPO();
        sql="SELECT selfselectstock FROM user WHERE account='"+account+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String codes=rs.getNString(1);
            String code[]=codes.split(" ");
            while (rs.next()){
                selfSelectStockPO.setAccount(account);
                for(int i=0;i<code.length;i+=3){
                    selfSelectStockPO.getCode().add(code[i]);
                    selfSelectStockPO.getName().add(code[i+1]);
                    selfSelectStockPO.getMarket().add(code[i+2]);
                }
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return selfSelectStockPO;
    }

    @Override
    public boolean addSelfSelectStock(SelfSelectStockPO selfSelectStockPO) {
        boolean flag=false;
        sql="SELECT selfselectstock FROM user WHERE account='"+selfSelectStockPO.getAccount()+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String code=rs.getNString(1);
            for(int i=0;i<selfSelectStockPO.getCode().size();i++){
                code=code+" "+selfSelectStockPO.getCode().get(i)+" "+selfSelectStockPO.getName().get(i)+" "+selfSelectStockPO.getMarket().get(i);
            }
            sql="UPDATE user SET selfselectstock='"+code+"' WHERE account='"+selfSelectStockPO.getAccount()+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addOneSelfSelectStock(String account, String stockCodeOrName) {
        boolean flag=false;
        sql="SELECT selfselectstock FROM user WHERE account='"+account+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String code=rs.getNString(1);
            code=code+" "+stockCodeOrName;
            sql="UPDATE user SET selfselectstock='"+code+"' WHERE account='"+account+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteSelfSelectStock(SelfSelectStockPO selfSelectStockPO) {
        boolean flag=false;
        sql="SELECT selfselectstock FROM user WHERE account='"+selfSelectStockPO.getAccount()+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String code=rs.getNString(1);
            for(int i=0;i<selfSelectStockPO.getCode().size();i++){
                code=code+" "+selfSelectStockPO.getCode().get(i)+" "+selfSelectStockPO.getName().get(i)+" "+selfSelectStockPO.getMarket().get(i);
            }
            sql="UPDATE user SET selfselectstock='"+code+"' WHERE account='"+selfSelectStockPO.getAccount()+"'";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean isMySelfSelectStock(String stockCodeOrName, String account) {
        boolean flag=false;
        sql="SELECT selfselectstock FROM user WHERE account='"+account+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String code=rs.getNString(1);
            String codes[]=code.split(" ");
            for(int i=0;i<codes.length;i++){
                if(stockCodeOrName==codes[i]){
                   flag=true;
                }
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
}
