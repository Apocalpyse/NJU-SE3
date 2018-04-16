package dataSer;

import PO.userPO.BuyPO;
import PO.userPO.HoldPO;
import VO.stockVO.HoldVO;

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
    public boolean realBuy(BuyPO buyPOS);
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
    public HoldPO getHoldBuy(String account, String date);
    /**
     * 查看下个交易日期
     * @param date
     * @return
     * @throws
     */
    public String getNextDay(String date);

}
