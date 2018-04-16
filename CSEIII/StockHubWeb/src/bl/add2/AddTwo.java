package bl.add2;

import PO.marketPO.NewsPO;
import PO.stockPO.StockPO;
import VO.marketVO.NewsVO;
import VO.stockVO.StockPieVO;
import VO.stockVO.StockVO;
import blSer.StockPoolBl;
import data.Add2Data.Add2Data;
import dataSer.Add2DataSer;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/8.
 */
public class AddTwo {
	public static void main(String[] args){
		AddTwo addTwo=new AddTwo();
		ArrayList<NewsVO> arrayList=new ArrayList<NewsVO>();
		try {
			arrayList=addTwo.getNewsCl("证券");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList.size());
	}

	/**
	 * 根据Classfy获取新闻，并返回新闻
	 * @param classify
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NewsVO> getNewsCl(String classify) throws Exception{
		Add2DataSer add2DataSer=new Add2Data();
		ArrayList<NewsPO> arrayList=add2DataSer.getNewsCl(classify);
		System.out.println(arrayList.size());
		ArrayList<NewsVO> arrayList1 = new ArrayList<NewsVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new NewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getClassify(),
					arrayList.get(i).getTime(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
					arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;

	}


}
