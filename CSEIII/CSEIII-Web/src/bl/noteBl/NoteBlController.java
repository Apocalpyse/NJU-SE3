package bl.noteBl;

import PO.marketPO.NotePool;
import VO.marketVO.NoteVO;
import VO.marketVO.SimpleNoteVO;
import blSer.NoteBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class NoteBlController implements NoteBlSer {
    @Override
    public boolean addNote(NoteVO noteVO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteNote(String account, String NoteID) throws Exception {
        return false;
    }

    @Override
    public boolean addPraise(String NoteID) throws Exception {
        return false;
    }

    @Override
    public boolean addCriticize(String NoteID) throws Exception {
        return false;
    }

    @Override
    public boolean addComment(String NoteID, String com, String comAccount) throws Exception {
        return false;
    }

    @Override
    public NoteVO getNoteT(String noteID) throws Exception {
        return null;
    }


    @Override
    public ArrayList<NoteVO> getNoteD(String date) throws Exception {
        return null;
    }

    @Override
    public ArrayList<NoteVO> getNoteSt(NotePool notePool, String StockCodeOrMethodID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<NoteVO> getNoteM() throws Exception {
        return null;
    }

    @Override
    public boolean addCollectionNote(String account, ArrayList<String> NoteID) throws Exception {
        return false;
    }

    @Override
    public ArrayList<SimpleNoteVO> getCollectionNote(String account, NotePool notePool) throws Exception {
        return null;
    }

    @Override
    public ArrayList<SimpleNoteVO> getOwnNote(String account) throws Exception {
        return null;
    }

    @Override
    public boolean deleteCollectionNote(String account, ArrayList<String> NoteID) throws Exception {
        return false;
    }
}
