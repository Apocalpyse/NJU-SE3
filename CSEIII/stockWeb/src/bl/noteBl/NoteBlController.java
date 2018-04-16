package bl.noteBl;

import PO.NotePool;
import VO.NoteVO;
import VO.SimpleNoteVO;
import blSer.NoteBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/16.
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
    public NoteVO getNoteS(String NoteID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<NoteVO> getNoteD(String date) throws Exception {
        return null;
    }

    @Override
    public ArrayList<NoteVO> getNoteT(NotePool notePool, String StockCodeOrMethodID) throws Exception {
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
    public ArrayList<SimpleNoteVO> addOwnNote(String account) throws Exception {
        return null;
    }

    @Override
    public boolean deleteCollectionNote(String account, String NoteID) throws Exception {
        return false;
    }
}
