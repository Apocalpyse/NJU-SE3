package bl.noteBl;

import PO.marketPO.NotePO;
import PO.marketPO.NotePool;
import PO.marketPO.SimpleNotePO;
import VO.marketVO.NoteVO;
import VO.marketVO.SimpleNoteVO;
import dataSer.NoteDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chenjin on 2017/5/30.
 */
public class Note {
	public boolean addNote(NoteVO noteVO) throws Exception {
		NoteDataSer noteDataSer=null;
		NotePO notePO=new NotePO(noteVO.getNoteId(),noteVO.getTitle(),noteVO.getAccount(),noteVO.getTime(),noteVO.getSource(),
				noteVO.getStockCodeOrMethodID(),noteVO.getContent(),noteVO.getView(),noteVO.getPraise(),noteVO.getCriticize(),
				noteVO.getComment(),noteVO.getCommentAccount());
		boolean result=noteDataSer.addNote(notePO);
		return  result;
	}


	public boolean deleteNote(String account, String NoteID) throws Exception {
		NoteDataSer noteDataSer=null;
		NotePO notePO=noteDataSer.getNoteT(NoteID);
		if(notePO.getAccount().equals(account)){
			return  noteDataSer.deleteNote(account,NoteID);
		}
		else {
			return false;
		}
	}


	public boolean addPraise(String NoteID) throws Exception {
		NoteDataSer noteDataSer=null;
		boolean result=noteDataSer.addPraise(NoteID);
		return result;
	}


	public boolean addCriticize(String NoteID) throws Exception {
		NoteDataSer noteDataSer=null;
		boolean result=noteDataSer.addCriticize(NoteID);
		return result;
	}


	public boolean addComment(String NoteID, String com, String comAccount) throws Exception {
		NoteDataSer noteDataSer=null;
		boolean result=noteDataSer.addComment(NoteID,com,comAccount);
		return result;
	}


	public NoteVO getNoteT(String NoteID) throws Exception {
		NoteDataSer noteDataSer=null;
		NotePO notePO=noteDataSer.getNoteT(NoteID);
		return new NoteVO(notePO.getNoteId(),notePO.getTitle(),notePO.getAccount(),notePO.getTime(),
				notePO.getSource(),notePO.getStockCodeOrMethodID(),notePO.getContent(),notePO.getView(),notePO.getPraise(),
				notePO.getCriticize(),notePO.getComment(),notePO.getCommentAccount());
	}



	public ArrayList<NoteVO> getNoteD(String date) throws Exception {
		NoteDataSer noteDataSer=null;
		ArrayList<NotePO> arrayList=noteDataSer.getNoteD(date);
		ArrayList<NoteVO> arrayList1=new ArrayList<NoteVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NoteVO(arrayList.get(i).getNoteId(),arrayList.get(i).getTitle(),arrayList.get(i).getAccount(),
					arrayList.get(i).getTime(),arrayList.get(i).getSource(),arrayList.get(i).getStockCodeOrMethodID(),
					arrayList.get(i).getContent(),
					arrayList.get(i).getView(),arrayList.get(i).getPraise(),arrayList.get(i).getCriticize(),
					arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;
	}

	public ArrayList<NoteVO> getNoteSt(NotePool notePool, String StockCodeOrMethodID) throws Exception {
		NoteDataSer noteDataSer=null;
		ArrayList<NotePO> arrayList=noteDataSer.getNoteSt(notePool,StockCodeOrMethodID);
		ArrayList<NoteVO> arrayList1=new ArrayList<NoteVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NoteVO(arrayList.get(i).getNoteId(),arrayList.get(i).getTitle(),arrayList.get(i).getAccount(),
					arrayList.get(i).getTime(),arrayList.get(i).getSource(),arrayList.get(i).getStockCodeOrMethodID(),
					arrayList.get(i).getContent(),
					arrayList.get(i).getView(),arrayList.get(i).getPraise(),arrayList.get(i).getCriticize(),
					arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;
	}


	public ArrayList<NoteVO> getNoteM() throws Exception {
		NoteDataSer noteDataSer=null;
		ArrayList<NotePO> arrayList=noteDataSer.getNoteM();
		ArrayList<NoteVO> arrayList1=new ArrayList<NoteVO>();
		Collections.sort(arrayList, new Comparator<NotePO>() {
			@Override
			public int compare(NotePO o1,NotePO o2) {
				NotePO stu1=(NotePO) o1;
				NotePO stu2=(NotePO)o2;
				if(stu1.getView()<stu2.getView()){
					return 1;
				}else if(stu1.getView()==stu2.getView()){
					return 0;
				}else{
					return -1;
				}
			}
		});
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NoteVO(arrayList.get(i).getNoteId(),arrayList.get(i).getTitle(),arrayList.get(i).getAccount(),
					arrayList.get(i).getTime(),arrayList.get(i).getSource(),arrayList.get(i).getStockCodeOrMethodID(),
					arrayList.get(i).getContent(),
					arrayList.get(i).getView(),arrayList.get(i).getPraise(),arrayList.get(i).getCriticize(),
					arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}

		return arrayList1;
	}


	public boolean addCollectionNote(String account, ArrayList<String> NoteID) throws Exception {
		NoteDataSer noteDataSer=null;
		boolean result=noteDataSer.addCollectionNote(account,NoteID);
		return result;
	}


	public ArrayList<SimpleNoteVO> getCollectionNote(String account, NotePool notePool) throws Exception {
		NoteDataSer noteDataSer=null;
		ArrayList<SimpleNotePO> arrayList=noteDataSer.getCollectionNote(account,notePool);
		ArrayList<SimpleNoteVO> arrayList1=new ArrayList<SimpleNoteVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleNoteVO(arrayList.get(i).getNoteID(),arrayList.get(i).getTitle(),arrayList.get(i).getAccount()));
		}
		return arrayList1;
	}


	public ArrayList<SimpleNoteVO> getOwnNote(String account) throws Exception {
		NoteDataSer noteDataSer=null;
		ArrayList<SimpleNotePO> arrayList=noteDataSer.getOwnNote(account);
		ArrayList<SimpleNoteVO> arrayList1=new ArrayList<SimpleNoteVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleNoteVO(arrayList.get(i).getNoteID(),arrayList.get(i).getTitle(),arrayList.get(i).getAccount()));
		}
		return arrayList1;
	}


	public boolean deleteCollectionNote(String account, ArrayList<String> NoteID) throws Exception {
		NoteDataSer noteDataSer=null;
		NotePO notePO=noteDataSer.getNoteT(account);
		if(notePO.getAccount().equals(account)){
			return noteDataSer.deleteCollectionNote(account,NoteID);
		}
		else {
			return false;
		}
	}
}
