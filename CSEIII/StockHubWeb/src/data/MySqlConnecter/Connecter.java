package data.MySqlConnecter;

import java.sql.*;

/**
 * Created by delong chang on 2017/6/9.
 */
public class Connecter {

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/CSEIII-Data";
    private static final String USER = "root";
    private static final String PASSWORD = "Wxf!151099149";
    public static Connection con=null;

    public Connecter(){
        if(con==null){
            try {
                con=DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载Mysql数据库驱动失败！");
        }
    }

//    public static void main(String[] args){
//        try {
//            Connecter.con=Connecter.getConnection();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    public Connection getConnection() throws SQLException {
        return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
