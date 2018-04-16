package bl.newsBl;

import PO.NewsPO;
import VO.NewsVO;
import VO.SimpleNewsVO;
import blSer.NewsBlSer;
import dataSer.NewsDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chenjin on 2017/5/17.
 */
public class News {
	public ArrayList<NewsVO> getNewsS(String source) throws Exception {
		NewsDataSer newsDataSer = null;
		ArrayList<NewsPO> arrayList=newsDataSer.getNewsS(source);
		ArrayList<NewsVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor(),
					arrayList.get(i).getTime(),arrayList.get(i).getSource(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
					arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;
	}


	public ArrayList<NewsVO> getNewsD(String date) throws Exception {
		NewsDataSer newsDataSer = null;
		ArrayList<NewsPO> arrayList=newsDataSer.getNewsD(date);
		ArrayList<NewsVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor(),
					arrayList.get(i).getTime(),arrayList.get(i).getSource(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
					arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;
	}


	public NewsVO getNewsT(String newsID) throws Exception {
		NewsDataSer newsDataSer = null;
		NewsPO newsPO=newsDataSer.getNewsT(newsID);
		return new NewsVO(newsPO.getNewsID(),newsPO.getTitle(),newsPO.getAuthor(),newsPO.getTime(),newsPO.getSource(),
				newsPO.getView(),newsPO.getPraise(),newsPO.getCriticize(),newsPO.getContent(),newsPO.getComment(),newsPO.getCommentAccount());
	}


	public ArrayList<NewsVO> getNewsM() throws Exception {
		NewsDataSer newsDataSer = null;
		ArrayList<NewsPO> arrayList=newsDataSer.getNewsM();
		ArrayList<NewsVO> arrayList1 = null;

		Collections.sort(arrayList, new Comparator<NewsPO>() {
			@Override
			public int compare(NewsPO o1, NewsPO o2) {
					NewsPO stu1=(NewsPO) o1;
					NewsPO stu2=(NewsPO)o2;
					if(stu1.getView()>stu2.getView()){
						return 1;
					}else if(stu1.getView()==stu2.getView()){
						return 0;
					}else{
						return -1;
					}
				}
		});

		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor(),
					arrayList.get(i).getTime(),arrayList.get(i).getSource(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
					arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;


	}


	public boolean addPraise(String newsID) throws Exception {
		NewsDataSer newsDataSer=null;
		boolean result=newsDataSer.addPraise(newsID);
		return result;

	}


	public boolean addCriticize(String newsID) throws Exception {
		NewsDataSer newsDataSer=null;
		boolean result=newsDataSer.addCriticize(newsID);
		return result;
	}


	public boolean addComment(String newsID, String com, String comAccount) throws Exception {
		NewsDataSer newsDataSer=null;
		boolean result=newsDataSer.addComment(newsID,com,comAccount);
		return result;
	}


	public boolean addCollectionNews(String account, ArrayList<String> newsID) throws Exception {
		return false;
	}


	public ArrayList<SimpleNewsVO> getCollectionNews(String account) throws Exception {
		return null;
	}


	public boolean deleteCollectionNews(String account, ArrayList<String> newsID) throws Exception {
		return false;
	}
}
