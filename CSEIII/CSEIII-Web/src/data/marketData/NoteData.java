package data.marketData;

import PO.marketPO.NotePO;
import PO.marketPO.NotePool;
import PO.marketPO.SimpleNotePO;
import dataSer.NoteDataSer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 对论坛信息的操作
 */
public class NoteData implements NoteDataSer {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    @Override
    public boolean addNote(NotePO noteVO) {
        boolean flag=false;
        sql="INSERT INTO note(title,content,authoraccount,praise,criticize,comment) VALUE ('"+noteVO.getTitle()+"','"+"')";
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
            flag=true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteNote(String account, String NoteID) {
        return false;
    }

    @Override
    public boolean addPraise(String NoteID) {
        return false;
    }

    @Override
    public boolean addCriticize(String NoteID) {
        return false;
    }

    @Override
    public boolean addComment(String NoteID, String com, String comAccount) {
        return false;
    }

    @Override
    public NotePO getNoteT(String noteID){return null;}

    @Override
    public ArrayList<NotePO> getNoteD(String date) {
        return null;
    }

    @Override
    public ArrayList<NotePO> getNoteSt(NotePool notePool, String StockCodeOrMethodID) {
        return null;
    }

    @Override
    public ArrayList<NotePO> getNoteM() {
        return null;
    }

    @Override
    public boolean addCollectionNote(String account, ArrayList<String> NoteID) {
        return false;
    }

    @Override
    public ArrayList<SimpleNotePO> getCollectionNote(String account, NotePool notePool) {
        return null;
    }

    @Override
    public ArrayList<SimpleNotePO> getOwnNote(String account) {
        return null;
    }

    @Override
    public boolean deleteCollectionNote(String account, ArrayList<String> NoteID) {
        return false;
    }
}
