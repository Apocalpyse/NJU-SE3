package rmi;

import dataSer.FindPlateInfoDataSer;
import dataSer.FindStockInfoDataSer;
import dataSer.StrategyDataSer;
import dataSer.UserDataSer;

import java.rmi.Remote;

/**
 * Created by Administrator on 2017/3/12.
 */
public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();
    public static RemoteHelper getInstance(){
        return remoteHelper;
    }

    private RemoteHelper() {	//Default constructor
    }

    public void setRemote(Remote remote){
        this.remote = remote;
    }

    public FindStockInfoDataSer getFindStockInfoDataSer(){
        return (FindStockInfoDataSer)remote;
    }

    public FindPlateInfoDataSer getFindPlateInfoDataSer(){
        return (FindPlateInfoDataSer)remote;
    }

    public UserDataSer getUserDataSer(){
        return  (UserDataSer)remote;
    }

    public StrategyDataSer getStrategyDataSer(){
        return (StrategyDataSer)remote;
    }

}
