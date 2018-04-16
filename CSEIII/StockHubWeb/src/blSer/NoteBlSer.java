package blSer;

import PO.marketPO.NotePool;
import VO.marketVO.NoteVO;
import VO.marketVO.SimpleNoteVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public interface NoteBlSer {
    /**
     * 添加帖子，返回是否添加成功
     * @param noteVO
     * @return
     * @throws Exception
     */
    public boolean addNote(NoteVO noteVO) throws Exception;
    /**
     * 删除帖子，返回是否删除成功
     * @param account,titles
     * @return
     * @throws Exception
     */
    public boolean deleteNote(String account, String noteID) throws Exception;

    /**
     * 增加点赞数目，并返回是否添加成功
     * @param noteID
     * @return
     * @throws Exception
     */
    public boolean addPraise(String noteID) throws Exception;
    /**
     * 增加点踩数目，并返回是否添加成功
     * @param noteID
     * @return
     * @throws Exception
     */
    public boolean addCriticize(String noteID) throws Exception;
    /**
     * 增加浏览数目，并返回是否添加成功
     * @param
     * @return
     * @throws Exception
     */
    public boolean addView(String notetID) throws Exception;
    /**
     * 增加评论，并返回是否添加成功
     * @param noteID
     * @return
     * @throws Exception
     */
    public boolean addComment(String noteID, String com, String comAccount) throws Exception;
    /**
     * 根据id获取帖子，并返回帖子
     * @param noteID
     * @return
     * @throws Exception
     */
    public NoteVO getNoteT(String noteID) throws Exception;

    /**
     * 根据日期获取帖子，并返回帖子
     * @param date
     * @return
     * @throws Exception
     */
    public ArrayList<NoteVO> getNoteD(String date) throws Exception;
    /**
     * 根据StockCodeOrMethodID获取帖子，并返回帖子
     * @param
     * @return
     * @throws Exception
     */
    public ArrayList<NoteVO> getNoteSt(NotePool notePool, String stockCodeOrMethodID) throws Exception;
    /**
     * 根据浏览量获取帖子，并返回帖子
     * @param
     * @return
     * @throws Exception
     */
    public ArrayList<NoteVO> getNoteM() throws Exception;
    /**
     * 收藏帖子，并返回是否收藏成功
     * @param account,NoteID
     * @return
     * @throws Exception
     */
    public boolean addCollectionNote(String account, ArrayList<String> noteID) throws Exception;
    /**
     * 获取收藏，并返回收藏
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList<SimpleNoteVO> getCollectionNote(String account, NotePool notePool) throws Exception;
    /**
     * 获取自己发表的帖子，并返回收藏
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList<SimpleNoteVO> getOwnNote(String account) throws Exception;
    /*
     * 删除收藏,并返回是否删除成功
     * @param account,NoteID
     * @return
     * @throws Exception
     */
    public boolean deleteCollectionNote(String account, ArrayList<String> noteID) throws Exception;
}
