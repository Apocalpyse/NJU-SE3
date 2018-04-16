package bl.searchBl;

import java.util.ArrayList;

import PO.StockPO;
import VO.OneStockInfoVO;
import VO.StockVO;
import bl.calculateBl.Translate;
import dataSer.FindStockInfoDataSer;
import rmi.RemoteHelper;

/**
 * Created by Administrator on 2017/3/9.
 */
public class Search {
    private FindStockInfoDataSer findStockInfoDataSer;

    public Search(){
        findStockInfoDataSer= RemoteHelper.getInstance().getFindStockInfoDataSer();
    }

    /**
     * 此方法实现拥护搜索功能，用户输入搜索信息，返回所有符合的股票信息
     * @param codeOrName 用户输入的搜索信息，股票代码或股票名称
     * @return ArrayList<StockVO> 所有符合的股票信息
     * @throws Exception
     */
    public OneStockInfoVO findAllStock(String codeOrName) throws Exception {
        /*
        ArrayList<StockVO> stockVOs=new ArrayList<StockVO>();
        if(codeOrName==""){
            return stockVOs;
        }
        char first=codeOrName.charAt(0);
        if(first=='0'){//当输入的信息是code时，需要进行处理
            Translate translate=new Translate();
            codeOrName=translate.longToShort(codeOrName);
        }

        ArrayList<StockPO> stockPOs=findStockInfoDataSer.findAllStock(codeOrName);
        Translate translate=new Translate();
        if(stockPOs!=null){
            stockVOs=translate.toVO(stockPOs);
        }
        return stockVOs;
        */
        return null;
    }
}
