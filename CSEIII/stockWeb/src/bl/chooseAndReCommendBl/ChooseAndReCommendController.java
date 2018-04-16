package bl.chooseAndReCommendBl;

import VO.ChooseVO;
import VO.StockVO;
import blSer.ChooseAndReCommendBlSer;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/16.
 */
public class ChooseAndReCommendController implements ChooseAndReCommendBlSer {
    @Override
    public ArrayList<StockVO> choose(ChooseVO chooseVO) throws RemoteException {
        ChooseAndReCommend chooseAndReCommend=new ChooseAndReCommend();
        ArrayList<StockVO> result=chooseAndReCommend.choose(chooseVO);
        return result;
    }
}
