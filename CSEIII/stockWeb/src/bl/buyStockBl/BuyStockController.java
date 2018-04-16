package bl.buyStockBl;

import VO.BuyVO;
import VO.HoldVO;
import VO.ProfitVO;
import blSer.BuyStockBlSer;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/16.
 */
public class BuyStockController implements BuyStockBlSer {
    @Override
    public boolean realBuy(String account, BuyVO buyVOS) throws RemoteException {
        return false;
    }

    @Override
    public ArrayList<BuyVO> getRealBuy(String account, String start, String end) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<HoldVO> getHoldBuy(String account, String date) throws RemoteException {
        return null;
    }

    @Override
    public ProfitVO getRealBuyProfit(String account, String start, String end) throws RemoteException {
        return null;
    }

    @Override
    public ProfitVO methodProfit(String start, String end) throws RemoteException {
        return null;
    }
}
