package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ClientRunner {

    private RemoteHelper remoteHelper;

    public ClientRunner() {
        linkToServer();
//		initGUI();
    }

    private void linkToServer() {
        try {
            remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/DataRemoteObject"));
            System.out.println("linked");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

//    public void test(){
//        InputStockByNameVO stockByCodeVO=new InputStockByNameVO("04/02/2014","04/05/2014","深发展A");
//
////        InputStockByCodeVO stockvo=new InputStockByCodeVO();
//        ArrayList<StockVO> result=new ArrayList<StockVO>();
//        KAndEMABlSer kAndEMABl=new KAndEMAEventController();
//        try {
//            result=kAndEMABl.getDataByName(stockByCodeVO);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(result.get(0).getCode());
//        System.out.println(result.get(1).getClose());
//        System.out.println(result.size());
//
//    }

//    public static void main(String[] args) throws RemoteException{
//		ClientRunner cr = new ClientRunner();
////		cr.test();
//	}


}
