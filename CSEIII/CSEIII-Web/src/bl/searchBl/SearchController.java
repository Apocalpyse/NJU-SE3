package bl.searchBl;

import VO.stockVO.OneStockInfoVO;
import blSer.SearchBlSer;

/**
 * Created by A on 2017/5/21.
 */
public class SearchController implements SearchBlSer {
    /**
     * 此方法实现拥护搜索功能，用户输入搜索信息，返回所有符合的股票信息
     * @param codeOrName 用户输入的搜索信息，股票代码或股票名称
     * @return ArrayList<StockVO> 所有符合的股票信息
     * @throws Exception
     */
    @Override
    public OneStockInfoVO findAllStock(String codeOrName) throws Exception {
        Search search=new Search();
        OneStockInfoVO result=search.findAllStock(codeOrName);
        return result;
    }
}
