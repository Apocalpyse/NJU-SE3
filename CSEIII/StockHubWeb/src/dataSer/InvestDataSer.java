package dataSer;

import PO.marketPO.InvestPO;
import PO.marketPO.SimpleInvestPO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/8.
 */
public interface InvestDataSer {
	/**
	 * 根据日期获取投资，并返回投资
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public ArrayList<InvestPO> getInvestD(String date) throws Exception;
	/**
	 * 根据id获取投资，并返回投资
	 * @param
	 * @return
	 * @throws Exception
	 */
	public InvestPO getInvestT(String investID) throws Exception;
	/**
	 * 根据浏览量获取投资，并返回投资
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<InvestPO> getInvestM() throws Exception;

	/**
	 * 增加点赞数目，并返回是否添加成功
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean addPraise(String investID) throws Exception;
	/**
	 * 增加点踩数目，并返回是否添加成功
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean addCriticize(String investID) throws Exception;
	/**
	 * 增加浏览数目，并返回是否添加成功
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean addView(String investID) throws Exception;
	/**
	 * 增加评论，并返回是否添加成功
	 * @param com,comAccount
	 * @return
	 * @throws Exception
	 */
	public boolean addComment(String invsetID, String com, String comAccount) throws Exception;
	/**
	 * 收藏新闻，并返回是否收藏成功
	 * @param account,newsID
	 * @return
	 * @throws Exception
	 */
	public boolean addCollectionInvest(String account, ArrayList<String> investID) throws Exception;
	/**
	 * 获取收藏，并返回收藏
	 * @param account
	 * @return 标题
	 * @throws Exception
	 */
	public ArrayList<SimpleInvestPO> getCollectionInvest(String account) throws Exception;
	/**
	 * 删除收藏，并返回是否删除成功
	 * @param account,titles
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCollectionInvest(String account, ArrayList<String> investID) throws Exception;
	/**
	 * 获取所有investNews的simpleVO
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SimpleInvestPO> getAllInvestVO()throws Exception;
	/**
	 * 判断是否是用户收藏的
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean isAlreadyCollection(String account,String investID)throws Exception;
}
