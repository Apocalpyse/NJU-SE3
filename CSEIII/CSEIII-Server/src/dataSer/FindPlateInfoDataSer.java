package dataSer;

import PO.HoldPO;
import PO.StockPO;
import PO.StockPool;
import PO.TotalPlatePO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/26.
 */
public interface FindPlateInfoDataSer extends Remote {

    /**
     * 获取某一天整体的板块信息
     * @param date
     * @return
     * @throws RemoteException
     */
    public TotalPlatePO findTotalPlateInfo(String date) throws RemoteException;

    /**
     * 获取某一个板块的股票某一天的具体信息
     * @param type 只可能是三个板块,type=0时，代表主板；type=1时，代表中小板；type=2时，代表创业板;type=3时，代表上证
     * @param date
     * @return 所有的这个板块股票、并且处于date这一天的股票信息
     * @throws RemoteException
     */
    public ArrayList<StockPO> findOnePlateInfo(int type, String date) throws RemoteException;

}
