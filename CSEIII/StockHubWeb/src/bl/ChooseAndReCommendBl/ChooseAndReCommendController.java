package bl.ChooseAndReCommendBl;

import VO.stockVO.StockVO;
import VO.userVO.ChooseVO;
import blSer.ChooseAndReCommendBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class ChooseAndReCommendController implements ChooseAndReCommendBlSer {
    @Override
    public ArrayList<StockVO> choose(ChooseVO chooseVO) {
        ChooseAndReCommend chooseAndReCommend=new ChooseAndReCommend();
        ArrayList<StockVO> result=chooseAndReCommend.choose(chooseVO);
        return result;
    }
}
