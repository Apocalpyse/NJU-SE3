package data.userData;

import PO.userPO.BuyPO;
import PO.userPO.HoldPO;
import dataSer.BuyStockDataSer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 * 用户模拟交易信息的操作
 */
public class BuyStockData implements BuyStockDataSer {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/CSEIII-Data";
    String name="root";
    String password="2578";

    @Override
    public boolean realBuy(String account, BuyPO buyPOS) {
        return false;
    }

    @Override
    public ArrayList<BuyPO> getRealBuy(String account, String start, String end) {
        return null;
    }

    @Override
    public ArrayList<HoldPO> getHoldBuy(String account, String date) {
        return null;
    }
}
