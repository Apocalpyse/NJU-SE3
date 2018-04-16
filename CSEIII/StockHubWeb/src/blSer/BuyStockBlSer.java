package blSer;

import VO.stockVO.HoldVO;
import VO.userVO.BuyVO;
import VO.userVO.ProfitVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface BuyStockBlSer {
    /**
     * 股票交易
     * @param buyVOS
     * @return
     * @throws
     */
    public boolean realBuy(BuyVO buyVOS) throws Exception;
    /**
     * 查看历史交易数据
     * @param account
     * @return
     * @throws
     */
    public ArrayList<BuyVO> getRealBuy(String account, String start, String end) throws Exception;
    /**
     * 查看持有的数据
     * @param account
     * @return
     * @throws
     */
    public HoldVO getHoldBuy(String account, String date) throws Exception;
    /**
     * 返回累积收益率
     * @param account
     * @return
     * @throws
     */
    public ProfitVO getRealBuyProfit(String account, String start, String end) throws Exception;
    /**
     * 最佳策略操作最后盈亏率
     * @param start,end
     * @return
     * @throws
     */
    public ProfitVO methodProfit(String start, String end) throws Exception;
}
