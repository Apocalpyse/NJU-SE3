package blSer;




import VO.NewsVO;
import VO.SimpleNewsVO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface NewsBlSer {
	/**
	 * 根据来源获取新闻，并返回新闻
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NewsVO> getNewsS(String source) throws Exception;
	/**
	 * 根据日期获取新闻，并返回新闻
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NewsVO> getNewsD(String date) throws Exception;
	/**
	 * 根据标题获取新闻，并返回新闻
	 * @param newsID
	 * @return
	 * @throws Exception
	 */
	public NewsVO getNewsT(String newsID) throws Exception;
	/**
	 * 根据浏览量获取新闻，并返回新闻
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NewsVO> getNewsM() throws Exception;

	/**
	 * 增加点赞数目，并返回是否添加成功
	 * @param newsID,number
	 * @return
	 * @throws Exception
	 */
	public boolean addPraise(String newsID) throws Exception;
	/**
	 * 增加点踩数目，并返回是否添加成功
	 * @param newsID
	 * @return
	 * @throws Exception
	 */
	public boolean addCriticize(String newsID) throws Exception;
	/**
	 * 增加评论，并返回是否添加成功
	 * @param newsID,com,comAccount
	 * @return
	 * @throws Exception
	 */
	public boolean addComment(String newsID, String com,String comAccount) throws Exception;
	/**
	 * 收藏新闻，并返回是否收藏成功
	 * @param account,newsID
	 * @return
	 * @throws Exception
	 */
	public boolean addCollectionNews(String account, ArrayList<String> newsID) throws Exception;
	/**
	 * 获取收藏，并返回收藏
	 * @param account
	 * @return 标题
	 * @throws Exception
	 */
	public ArrayList<SimpleNewsVO> getCollectionNews(String account) throws Exception;
	/**
	 * 删除收藏，并返回是否删除成功
	 * @param account,titles
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCollectionNews(String account, ArrayList<String> newsID) throws Exception;

}
