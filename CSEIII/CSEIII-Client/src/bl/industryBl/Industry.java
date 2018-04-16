package bl.industryBl;

import PO.IndustryPO;
import PO.StockPO;
import VO.IndustryVO;
import VO.StockPieVO;
import VO.StockVO;
import dataSer.FindStockInfoDataSer;
import dataSer.IndustryDataSer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chenjin on 2017/5/18.
 */
public class Industry {

	public ArrayList<IndustryVO> findIndustryInfoOneday(String date) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
		ArrayList<IndustryVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
					arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
					arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume(),arrayList.get(i).getIndustryScore()));
		}
		return arrayList1;
	}


	public ArrayList<IndustryVO> findTotalIndustryMaxInRate(String date) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
		ArrayList<IndustryVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
					arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
					arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume(),arrayList.get(i).getIndustryScore()));
		}
		Collections.sort(arrayList1, new Comparator<IndustryVO>() {
			@Override
			public int compare(IndustryVO o1, IndustryVO o2) {
				IndustryVO stu1=(IndustryVO) o1;
				IndustryVO stu2=(IndustryVO)o2;
				if(Double.parseDouble(stu1.getIncreaseOrDecreaseRate())>Double.parseDouble(stu2.getIncreaseOrDecreaseRate())){
					return 1;
				}else if(Double.parseDouble(stu2.getIncreaseOrDecreaseRate()) == Double.parseDouble(stu1.getIncreaseOrDecreaseRate())){
					return 0;
				}else{
					return -1;
				}
			}
		});
		return arrayList1;
	}


	public ArrayList<IndustryVO> findTotalIndustryMaxDeRate(String date) throws RemoteException {
		Industry industry = null;
		ArrayList<IndustryVO> arrayList=industry.findTotalIndustryMaxInRate(date);
		ArrayList<IndustryVO> arrayList1 = null;
		for(int i=arrayList.size()-1;i>=0;i--){
			arrayList1.add(arrayList.get(i));
		}
		return arrayList1;
	}


	public ArrayList<IndustryVO> findTotalIndustryMaxInVolume(String date) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
		ArrayList<IndustryVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
					arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
					arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume(),arrayList.get(i).getIndustryScore()));
		}
		Collections.sort(arrayList1, new Comparator<IndustryVO>() {
			@Override
			public int compare(IndustryVO o1, IndustryVO o2) {
				IndustryVO stu1=(IndustryVO) o1;
				IndustryVO stu2=(IndustryVO)o2;
				if(Double.parseDouble(stu1.getTotalVolume())>Double.parseDouble(stu2.getTotalVolume())){
					return 1;
				}else if(Double.parseDouble(stu2.getTotalVolume()) == Double.parseDouble(stu1.getTotalVolume())){
					return 0;
				}else{
					return -1;
				}
			}
		});
		return arrayList1;
	}


	public ArrayList<IndustryVO> findTotalIndustryPotential(String date) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<IndustryPO> arrayList=industryDataSer.findIndustryInfoOneday(date);
		ArrayList<IndustryVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new IndustryVO(arrayList.get(i).getDate(),arrayList.get(i).getIndustryName(),arrayList.get(i).getCompanyNum(),
					arrayList.get(i).getAverageOpen(),arrayList.get(i).getAverageClose(),arrayList.get(i).getIncreaseOrDecreaseMoney(),
					arrayList.get(i).getIncreaseOrDecreaseRate(),arrayList.get(i).getTotalVolume(),arrayList.get(i).getIndustryScore()));
		}
		return arrayList1;
	}


	public StockPieVO findTotalIndustryPieVO(String date) throws RemoteException {
		FindStockInfoDataSer findStockInfoDataSer = null;
		ArrayList<StockPO> arrayList=findStockInfoDataSer.findStockInfoOneday(date);
		ArrayList<StockVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
					arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
					arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
					arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
		}
		double one = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<=-10){
				one=one+1;
			}
		}
		one=one*100/arrayList.size();

		double two = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<-5){
				two=two+1;
			}
		}
		two=two*100/arrayList.size();

		double three = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>-5&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<0){
				three=three+1;
			}
		}
		three=three*100/arrayList.size();

		double four = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>0&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<5){
				four=four+1;
			}
		}
		four=four*100/arrayList.size();

		double five = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>5){
				five=five+1;
			}
		}
		five=five*100/arrayList.size();

		double six = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>=10){
				six=six+1;
			}
		}
		six=six*100/arrayList.size();
		return new StockPieVO(date,one,two,three,four,five,six);

	}


	public ArrayList<StockVO> findIndustryMaxInRate(String date, String industryName) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date,industryName);
		ArrayList<StockVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
					arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
					arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
					arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
		}
		Collections.sort(arrayList1, new Comparator<StockVO>() {
			@Override
			public int compare(StockVO o1, StockVO o2) {
				StockVO stu1=(StockVO) o1;
				StockVO stu2=(StockVO) o2;
				if(Double.parseDouble(stu1.getIncreaseOrDecrease())>Double.parseDouble(stu2.getIncreaseOrDecrease())){
					return 1;
				}else if(Double.parseDouble(stu2.getIncreaseOrDecrease()) == Double.parseDouble(stu1.getIncreaseOrDecrease())){
					return 0;
				}else{
					return -1;
				}
			}
		});
		return arrayList1;
	}


	public ArrayList<StockVO> findIndustryMaxDeRate(String date, String industryName) throws RemoteException {
		Industry industry = null;
		ArrayList<StockVO> arrayList=industry.findIndustryMaxInRate(date,industryName);
		ArrayList<StockVO> arrayList1 = null;
		for(int i=arrayList.size()-1;i>=0;i--){
			arrayList1.add(arrayList.get(i));
		}
		return arrayList1;
	}


	public ArrayList<StockVO> findIndustryMaxInVolume(String date, String industryName) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date,industryName);
		ArrayList<StockVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
					arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
					arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
					arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
		}
		Collections.sort(arrayList1, new Comparator<StockVO>() {
			@Override
			public int compare(StockVO o1, StockVO o2) {
				StockVO stu1=(StockVO) o1;
				StockVO stu2=(StockVO) o2;
				if(Double.parseDouble(stu1.getVolume())>Double.parseDouble(stu2.getVolume())){
					return 1;
				}else if(Double.parseDouble(stu2.getVolume()) == Double.parseDouble(stu1.getVolume())){
					return 0;
				}else{
					return -1;
				}
			}
		});
		return arrayList1;
	}


	public ArrayList<StockVO> findIndustryPotential(String date, String industryName) throws RemoteException {
		IndustryDataSer industryDataSer = null;
		ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date,industryName);
		ArrayList<StockVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
					arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
					arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
					arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
		}
		return arrayList1;
	}


	public StockPieVO findIndustryPieVO(String date, String industryName) throws RemoteException {
		IndustryDataSer industryDataSer=null;
		ArrayList<StockPO> arrayList=industryDataSer.findIndustryInfoOneday(date, industryName);
		ArrayList<StockVO> arrayList1 = null;
		for(int i=0;i<arrayList.size();i++){
			arrayList1.add(new StockVO(arrayList.get(i).getDate(),arrayList.get(i).getOpen(),arrayList.get(i).getHigh(),
					arrayList.get(i).getLow(),arrayList.get(i).getClose(),arrayList.get(i).getVolume(),arrayList.get(i).getAdjClose(),
					arrayList.get(i).getCode(),arrayList.get(i).getName(),arrayList.get(i).getMarket(),arrayList.get(i).getIncreaseOrDecrease(),
					arrayList.get(i).getPreAdjClose(),arrayList.get(i).getPlate()));
		}
		double one = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<=-10){
				one=one+1;
			}
		}
		one=one*100/arrayList.size();

		double two = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<-5){
				two=two+1;
			}
		}
		two=two*100/arrayList.size();

		double three = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>-5&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<0){
				three=three+1;
			}
		}
		three=three*100/arrayList.size();

		double four = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>0&&Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())<5){
				four=four+1;
			}
		}
		four=four*100/arrayList.size();

		double five = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>5){
				five=five+1;
			}
		}
		five=five*100/arrayList.size();

		double six = 0;
		for(int i=0;i<arrayList1.size();i++){
			if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>=10){
				six=six+1;
			}
		}
		six=six*100/arrayList.size();
		return new StockPieVO(date,one,two,three,four,five,six);
	}

}
