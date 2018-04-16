package blSer;


import VO.marketVO.NewsVO;
import VO.stockVO.StockPieVO;
import VO.stockVO.StockVO;


import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/6/1.
 */
// PO——InvestAdvance取名不规范，对应VO名暂时定义为InvestAdvanceVO
// 现有接口：根据日期与market获取对应股票池;根据日期和股票池获取饼状图;根据classify获取新闻;分别根据date、id获取投资参考,已经收藏，删除收藏以及获取收藏列表
public interface AddBlSer2 {
    /**
     * 根据date与market获取对应market当日的所有股票信息
     * @param date,stockPoolBl
     * @return
     * @throws Exception
     */
    public ArrayList<StockVO> getNewsC(String date, StockPoolBl stockPoolBl) throws Exception;
    /**
     * 获取某市场、板块的饼状图数据(数据的内容与形式与行业的一致,因为StockPoolBl包括market和plate，所以放在一起了)
     * @param date
     * @param stockPoolBl
     * @return
     */
    public StockPieVO findMarketOrPlatePieVO(String date, StockPoolBl stockPoolBl);
    /**
     * 根据Classfy获取新闻，并返回新闻
     * @param classify
     * @return
     * @throws Exception
     */
    public NewsVO getNewsC(String classify) throws Exception;
    /**
     * 根据日期获取投资参考，并返回投资参考
     * @param date
     * @return
     * @throws Exception
     */
    public ArrayList<InvestAdvanceVO> getInvestD(String date) throws Exception;
    /**
     * 根据id获取投资参考，并返回投资参考
     * @param newsID
     * @return
     * @throws Exception
     */
    public InvestAdvanceVO getInvestT(String newsID) throws Exception;


    /**
     * 收藏投资参考，并返回是否收藏成功
     * @param account,investID
     * @return
     * @throws Exception
     */
    public boolean addCollectionInvest(String account, ArrayList<String> investID) throws Exception;
    /**
     * 删除收藏，并返回是否删除成功
     * @param account,investID
     * @return
     * @throws Exception
     */
    public boolean deleteCollectionInvest(String account, ArrayList<String> investID) throws Exception;

    /**
     * 获取收藏，并返回收藏
     * @param invest
     * @return 标题
     * @throws Exception
     */
    public ArrayList<InvestAdvanceVO> getCollectionInvest(String account) throws Exception;












    //待定部分——注释
//    /**
//     * 增加点赞数目，并返回是否添加成功
//     * @param investID
//     * @return
//     * @throws Exception
//     */
//    public boolean addPraise(String newsID) throws Exception;
//    /**
//     * 增加点踩数目，并返回是否添加成功
//     * @param newsID
//     * @return
//     * @throws Exception
//     */
//    public boolean addCriticize(String newsID) throws Exception;
//    /**
//     * 增加评论，并返回是否添加成功
//     * @param newsID,com,comAccount
//     * @return
//     * @throws Exception
//     */
//    public boolean addComment(String newsID, String com,String comAccount) throws Exception;

}
