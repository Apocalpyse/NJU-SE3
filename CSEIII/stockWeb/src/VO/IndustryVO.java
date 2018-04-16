package VO;

/**
 * Created by chenjin on 2017/5/6.
 */
public class IndustryVO {
	private String date;//日期
	private String industryName;//行业名称
	private String companyNum;//公司家数
	private String averageOpen;//平均开盘价
	private String averageClose;//平均收盘价
	private String increaseOrDecreaseMoney;//涨跌额
	private String increaseOrDecreaseRate;//涨跌幅
	private String totalVolume;//总成交量
	private String industryScore;//行业综合评分
	public IndustryVO(){

	}

	public IndustryVO(String date, String industryName, String companyNum, String averageOpen,
					  String averageClose, String increaseOrDecreaseMoney,
					  String increaseOrDecreaseRate, String totalVolume, String industryScore) {
		this.date = date;
		this.industryName = industryName;
		this.companyNum = companyNum;
		this.averageOpen = averageOpen;
		this.averageClose = averageClose;
		this.increaseOrDecreaseMoney = increaseOrDecreaseMoney;
		this.increaseOrDecreaseRate = increaseOrDecreaseRate;
		this.totalVolume = totalVolume;
		this.industryScore = industryScore;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(String companyNum) {
		this.companyNum = companyNum;
	}

	public String getAverageOpen() {
		return averageOpen;
	}

	public void setAverageOpen(String averageOpen) {
		this.averageOpen = averageOpen;
	}

	public String getAverageClose() {
		return averageClose;
	}

	public void setAverageClose(String averageClose) {
		this.averageClose = averageClose;
	}

	public String getIncreaseOrDecreaseMoney() {
		return increaseOrDecreaseMoney;
	}

	public void setIncreaseOrDecreaseMoney(String increaseOrDecreaseMoney) {
		this.increaseOrDecreaseMoney = increaseOrDecreaseMoney;
	}

	public String getIncreaseOrDecreaseRate() {
		return increaseOrDecreaseRate;
	}

	public void setIncreaseOrDecreaseRate(String increaseOrDecreaseRate) {
		this.increaseOrDecreaseRate = increaseOrDecreaseRate;
	}

	public String getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(String totalVolume) {
		this.totalVolume = totalVolume;
	}

	public String getIndustryScore() {
		return industryScore;
	}

	public void setIndustryScore(String industryScore) {
		this.industryScore = industryScore;
	}
}
