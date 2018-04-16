package blSer;

import VO.stockVO.StockVO;
import VO.userVO.ChooseVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface ChooseAndReCommendBlSer {
    /**
     * 用户输入范围，返回对应股票信息
     * @param
     * @return ArrayList<StockVO>
     * @throws Exception
     */
    public ArrayList<StockVO> choose(ChooseVO chooseVO);
}
