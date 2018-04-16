package dataSer;


import PO.NotePO;
import PO.NotePool;
import PO.SimpleNotePO;


import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public interface NoteDataSer extends Remote{
	/**
	 * 添加帖子，返回是否添加成功
	 * @param noteVO
	 * @return
	 * @throws Exception
	 */
	public boolean addNote(NotePO noteVO) throws Exception;
	/**
	 * 删除帖子，返回是否删除成功
	 * @param account,titles
	 * @return
	 * @throws Exception
	 */
	public boolean deleteNote(String account,String NoteID) throws Exception;

	/**
	 * 增加点赞数目，并返回是否添加成功
	 * @param NoteID
	 * @return
	 * @throws Exception
	 */
	public boolean addPraise(String NoteID) throws Exception;
	/**
	 * 增加点踩数目，并返回是否添加成功
	 * @param NoteID
	 * @return
	 * @throws Exception
	 */
	public boolean addCriticize(String NoteID) throws Exception;
	/**
	 * 增加评论，并返回是否添加成功
	 * @param NoteID
	 * @return
	 * @throws Exception
	 */
	public boolean addComment(String NoteID, String com,String comAccount) throws Exception;
	/**
	 * 根据来源获取帖子，并返回帖子
	 * @param
	 * @return
	 * @throws Exception
	 */
	public NotePO getNoteS(String NoteID) throws Exception;
	/**
	 * 根据日期获取帖子，并返回帖子
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NotePO> getNoteD(String date) throws Exception;
	/**
	 * 根据标题获取帖子，并返回帖子
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NotePO> getNoteT(NotePool notePool,String StockCodeOrMethodID) throws Exception;
	/**
	 * 根据浏览量获取帖子，并返回帖子
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NotePO> getNoteM() throws Exception;
	/**
	 * 收藏帖子，并返回是否收藏成功
	 * @param account,NoteID
	 * @return
	 * @throws Exception
	 */
	public boolean addCollectionNote(String account,String NoteID) throws Exception;
	/**
	 * 获取收藏，并返回收藏
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SimpleNotePO> getCollectionNote(String account, NotePool notePool) throws Exception;
	/**
	 * 获取自己发表的帖子，并返回收藏
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SimpleNotePO> addOwnNote(String account) throws Exception;
	/**
	 * 删除收藏,并返回是否删除成功
	 * @param account,NoteID
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCollectionNote(String account,String NoteID) throws Exception;
}
