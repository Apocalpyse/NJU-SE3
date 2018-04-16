package dataSer;

import PO.marketPO.NewsPO;
import PO.marketPO.SimpleNewsPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface NewsDataSer {

    /**
     * 根据日期获取新闻，并返回新闻
     * @param date
     * @return
     * @throws Exception
     */
    public ArrayList<NewsPO> getNewsD(String date);
    /**
     * 根据id获取新闻，并返回新闻
     * @param newsID
     * @return
     * @throws Exception
     */
    public NewsPO getNewsT(String newsID);
    /**
     * 获取所有新闻，并返回新闻
     * @param
     * @return
     * @throws Exception
     */
    public ArrayList<NewsPO> getNewsM() ;

    /**
     * 增加点赞数目，并返回是否添加成功
     * @param newsID
     * @return
     * @throws Exception
     */
    public boolean addPraise(String newsID);
    /**
     * 增加点踩数目，并返回是否添加成功
     * @param newsID
     * @return
     * @throws Exception
     */
    public boolean addCriticize(String newsID);
    /**
     * 增加评论，并返回是否添加成功
     * @param newsID,com,comAccount
     * @return
     * @throws Exception
     */
    public boolean addComment(String newsID, String com,String comAccount);
    /**
     * 收藏新闻，并返回是否收藏成功
     * @param account,newsID
     * @return
     * @throws Exception
     */
    public boolean addCollectionNews(String account, ArrayList<String> newsID);
    /**
     * 获取收藏，并返回收藏
     * @param account
     * @return 标题
     * @throws Exception
     */
    public ArrayList<SimpleNewsPO> getCollectionNews(String account);
    /**
     * 删除收藏，并返回是否删除成功
     * @param account,titles
     * @return
     * @throws Exception
     */
    public boolean deleteCollectionNews(String account, ArrayList<String> newsID) ;

}
