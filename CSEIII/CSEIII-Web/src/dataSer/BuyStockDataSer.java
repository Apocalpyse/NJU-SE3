package dataSer;

import PO.userPO.BuyPO;
import PO.userPO.HoldPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface BuyStockDataSer {
    /**
     * 股票交易
     * @param buyPOS
     * @return
     * @throws
     */
    public boolean realBuy(String account, BuyPO buyPOS);
    /**
     * 查看历史交易数据
     * @param account
     * @return
     * @throws
     */
    public ArrayList<BuyPO> getRealBuy(String account, String start, String end);
    /**
     * 查看持有的数据
     * @param account
     * @return
     * @throws
     */
    public ArrayList<HoldPO> getHoldBuy(String account, String date);

}
