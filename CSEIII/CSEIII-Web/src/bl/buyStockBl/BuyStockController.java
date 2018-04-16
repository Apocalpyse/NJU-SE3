package bl.buyStockBl;

import VO.stockVO.HoldVO;
import VO.userVO.BuyVO;
import VO.userVO.ProfitVO;
import blSer.BuyStockBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class BuyStockController implements BuyStockBlSer {
    @Override
    public boolean realBuy(String account, BuyVO buyVOS) {
        return false;
    }

    @Override
    public ArrayList<BuyVO> getRealBuy(String account, String start, String end) {
        return null;
    }

    @Override
    public ArrayList<HoldVO> getHoldBuy(String account, String date) {
        return null;
    }

    @Override
    public ProfitVO getRealBuyProfit(String account, String start, String end) {
        return null;
    }

    @Override
    public ProfitVO methodProfit(String start, String end) {
        return null;
    }
}
