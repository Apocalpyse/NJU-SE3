package dataSer;

import PO.marketPO.NotePO;
import PO.marketPO.NotePool;
import PO.marketPO.SimpleNotePO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface NoteDataSer {
    /**
     * 添加帖子，返回是否添加成功
     * @param notePO
     * @return
     * @throws Exception
     */
    public boolean addNote(NotePO notePO);
    /**
     * 删除帖子，返回是否删除成功
     * @param account,titles
     * @return
     * @throws Exception
     */
    public boolean deleteNote(String account,String NoteID) ;

    /**
     * 增加点赞数目，并返回是否添加成功
     * @param NoteID
     * @return
     * @throws Exception
     */
    public boolean addPraise(String NoteID) ;
    /**
     * 增加点踩数目，并返回是否添加成功
     * @param NoteID
     * @return
     * @throws Exception
     */
    public boolean addCriticize(String NoteID) ;
    /**
     * 增加评论，并返回是否添加成功
     * @param NoteID
     * @return
     * @throws Exception
     */
    public boolean addComment(String NoteID, String com,String comAccount) ;
    /**
     * 根据id获取帖子，并返回帖子
     * @param noteID
     * @return
     * @throws Exception
     */
    public NotePO getNoteT(String noteID) throws Exception;
    /**
     * 根据日期获取帖子，并返回帖子
     * @param date
     * @return
     * @throws Exception
     */
    public ArrayList<NotePO> getNoteD(String date) ;
    /**
     * 根据贴子所属范围获取帖子，并返回帖子
     * @param
     * @return
     * @throws Exception
     */
    public ArrayList<NotePO> getNoteSt(NotePool notePool, String stockCodeOrMethodID);
    /**
     * 获取所有帖子，并返回帖子
     * @param
     * @return
     * @throws Exception
     */
    public ArrayList<NotePO> getNoteM() ;
    /**
     * 收藏帖子，并返回是否收藏成功
     * @param account,NoteID
     * @return
     * @throws Exception
     */
    public boolean addCollectionNote(String account,ArrayList<String> noteID) ;
    /**
     * 获取收藏，并返回收藏
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList<SimpleNotePO> getCollectionNote(String account, NotePool notePool) ;
    /**
     * 获取自己发表的帖子，并返回收藏
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList<SimpleNotePO> getOwnNote(String account);
    /**
     * 删除收藏,并返回是否删除成功
     * @param account,NoteID
     * @return
     * @throws Exception
     */
    public boolean deleteCollectionNote(String account,ArrayList<String> noteID);
}
