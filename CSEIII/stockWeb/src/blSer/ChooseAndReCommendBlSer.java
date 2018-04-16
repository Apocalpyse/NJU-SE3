package blSer;

import PO.StockPO;
import VO.ChooseVO;
import VO.StockVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface ChooseAndReCommendBlSer {
	/**
	 * 用户输入范围，返回对应股票信息
	 * @param
	 * @return ArrayList<StockVO>
	 * @throws Exception
	 */
	public ArrayList<StockVO> choose(ChooseVO chooseVO) throws RemoteException;
}
