package bl.InvestBl;

import PO.industryPO.IndustryPO;
import PO.marketPO.InvestPO;
import PO.marketPO.SimpleInvestPO;
import VO.industryVO.IndustryVO;
import VO.marketVO.InvestVO;
import VO.marketVO.SimpleInvestVO;

import VO.stockVO.SimpleInvestNewsVO;
import data.industryData.IndustryData;
import data.marketData.InvestData;
import dataSer.IndustryDataSer;
import dataSer.InvestDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chenjin on 2017/6/8.
 */
public class Invest {
	/**
	 * 根据日期获取投资，并返回投资
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static void main(String[] args){
		Invest invest=new Invest();
		ArrayList<SimpleInvestVO>  arrayList=new ArrayList<SimpleInvestVO>();
		try {
			arrayList=invest.getAllInvestVO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList.get(0).getAuthor());

	}
	public ArrayList<InvestVO> getInvestD(String date) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		ArrayList<InvestPO> arrayList=investDataSer.getInvestD(date);
		ArrayList<InvestVO> arrayList1 = new ArrayList<InvestVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new InvestVO(arrayList.get(i).getInvestID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor(),
					arrayList.get(i).getTime(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
					arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;
	}
	/**
	 * 根据id获取投资，并返回投资
	 * @param
	 * @return
	 * @throws Exception
	 */
	public InvestVO getInvestT(String investID) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		InvestPO newsPO=investDataSer.getInvestT(investID);
		return new InvestVO(newsPO.getInvestID(),newsPO.getTitle(),newsPO.getAuthor(),newsPO.getTime(),
				newsPO.getView(),newsPO.getPraise(),newsPO.getCriticize(),newsPO.getContent(),newsPO.getComment(),newsPO.getCommentAccount());
	}
	/**
	 * 根据浏览量获取投资，并返回投资
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<InvestVO> getInvestM() throws Exception{
		InvestDataSer investDataSer = new InvestData();
		ArrayList<InvestPO> arrayList=investDataSer.getInvestM();
		ArrayList<InvestVO> arrayList1 = new ArrayList<InvestVO>();

		Collections.sort(arrayList, new Comparator<InvestPO>() {
			@Override
			public int compare(InvestPO o1, InvestPO o2) {
				InvestPO stu1=(InvestPO) o1;
				InvestPO stu2=(InvestPO)o2;
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
			arrayList1.add(new InvestVO(arrayList.get(i).getInvestID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor(),
					arrayList.get(i).getTime(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
					arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
		}
		return arrayList1;
	}

	/**
	 * 增加点赞数目，并返回是否添加成功
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean addPraise(String investID) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		boolean result=investDataSer.addPraise(investID);
		return result;
	}
	/**
	 * 增加点踩数目，并返回是否添加成功
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean addCriticize(String investID) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		boolean result=investDataSer.addCriticize(investID);
		return result;
	}
	public boolean addView(String investID) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		boolean result=investDataSer.addView(investID);
		return result;
	}
	/**
	 * 增加评论，并返回是否添加成功
	 * @param com,comAccount
	 * @return
	 * @throws Exception
	 */
	public boolean addComment(String invsetID, String com, String comAccount) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		boolean result=investDataSer.addComment(invsetID,com,comAccount);
		return result;
	}
	/**
	 * 收藏新闻，并返回是否收藏成功
	 * @param account,newsID
	 * @return
	 * @throws Exception
	 */
	public boolean addCollectionInvest(String account, ArrayList<String> investID) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		boolean result=investDataSer.addCollectionInvest(account,investID);
		return result;
	}
	/**
	 * 获取收藏，并返回收藏
	 * @param account
	 * @return 标题
	 * @throws Exception
	 */
	public ArrayList<SimpleInvestVO> getCollectionInvest(String account) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		ArrayList<SimpleInvestPO> arrayList=investDataSer.getCollectionInvest(account);
		ArrayList<SimpleInvestVO> arrayList1=new ArrayList<SimpleInvestVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleInvestVO(arrayList.get(i).getInvestID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor()));
		}
		return arrayList1;
	}
	/**
	 * 删除收藏，并返回是否删除成功
	 * @param account,titles
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCollectionInvest(String account, ArrayList<String> investID) throws Exception{
		InvestDataSer investDataSer = new InvestData();
		boolean result=investDataSer.deleteCollectionInvest(account,investID);
		return result;
	}
	/**
	 * 获取所有investNews的simpleVO
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<SimpleInvestVO> getAllInvestVO()throws Exception{
		InvestDataSer investDataSer=new InvestData();
		ArrayList<SimpleInvestPO> arrayList=investDataSer.getAllInvestVO();
		System.out.println(arrayList.size());
		ArrayList<SimpleInvestVO> arrayList1=new ArrayList<SimpleInvestVO>();
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new SimpleInvestVO(arrayList.get(i).getInvestID(),arrayList.get(i).getTitle(),arrayList.get(i).getAuthor()));
		}
		return arrayList1;
	}
	/**
	 * 判断是否是用户收藏的
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean isAlreadyCollection(String account,String investID)throws Exception{
		InvestDataSer investDataSer=new InvestData();
		boolean result=investDataSer.isAlreadyCollection(account,investID);
		return result;
	}
}
