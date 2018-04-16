package bl.industryBl;

import VO.IndustryVO;
import VO.StockVO;
import VO.StockPieVO;
import blSer.IndustryBlSer;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by A on 2017/5/16.
 */
public class IndustryController implements IndustryBlSer {
    @Override
    public ArrayList<IndustryVO> findIndustryInfoOneday(String date) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<IndustryVO> result;
        result=industry.findIndustryInfoOneday(date);
        return result;
    }

    @Override
    public ArrayList<IndustryVO> findTotalIndustryMaxInRate(String date) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<IndustryVO> result;
        result=industry.findTotalIndustryMaxInRate(date);
        return result;
    }

    @Override
    public ArrayList<IndustryVO> findTotalIndustryMaxDeRate(String date) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<IndustryVO> result;
        result=industry.findTotalIndustryMaxDeRate(date);
        return result;
    }

    @Override
    public ArrayList<IndustryVO> findTotalIndustryMaxInVolume(String date) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<IndustryVO> result;
        result=industry.findTotalIndustryMaxInVolume(date);
        return result;
    }

    @Override
    public ArrayList<IndustryVO> findTotalIndustryPotential(String date) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<IndustryVO> result;
        result=industry.findTotalIndustryPotential(date);
        return result;
    }

    @Override
    public StockPieVO findTotalIndustryPieVO(String date) throws RemoteException {
        Industry industry=new Industry();
        StockPieVO stockPieVO;
        stockPieVO=industry.findTotalIndustryPieVO(date);
        return stockPieVO;
    }

    @Override
    public ArrayList<StockVO> findIndustryMaxInRate(String date, String industryName) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<StockVO> result;
        result=industry.findIndustryMaxInRate(date, industryName);
        return result;

    }

    @Override
    public ArrayList<StockVO> findIndustryMaxDeRate(String date, String industryName) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<StockVO> result;
        result=industry.findIndustryMaxDeRate(date, industryName);
        return result;
    }

    @Override
    public ArrayList<StockVO> findIndustryMaxInVolume(String date, String industryName) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<StockVO> result;
        result=industry.findIndustryMaxInVolume(date, industryName);
        return result;
    }

    @Override
    public ArrayList<StockVO> findIndustryPotential(String date, String industryName) throws RemoteException {
        Industry industry=new Industry();
        ArrayList<StockVO> result;
        result=industry.findIndustryPotential(date, industryName);
        return result;
    }

    @Override
    public StockPieVO findIndustryPieVO(String date, String industryName) throws RemoteException {
        Industry industry=new Industry();
        StockPieVO stockPieVO;
        stockPieVO=industry.findIndustryPieVO(date, industryName);
        return stockPieVO;
    }
}
