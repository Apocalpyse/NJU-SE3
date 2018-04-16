package VO;

/**
 * Created by chenjin on 2017/5/6.
 */
public class GoalVO {
	private String total;
	private String stability;//稳定性，通过股价的方差来求
	private String prof;//收益性，起止股价的增长率
	private String mobility;//流动性
	/*
	1, 用Amihud（2002）提出的流动性比率作为股票市场的流动性指标：Lt=Vt/|lnPt-lnPt-1|
其中Vt表示第t 日的交易额，Pt为第t 日的收盘价。则流动性指标Lt表示的是当天的交易额除上当天对数收益率的绝对值。
	 */
	private String safety;//安全性，股票跑赢大盘的概率
	private String development;//成长性，股票所在行业的增长率

	public GoalVO(String total, String stability, String prof, String mobility, String safety, String development) {
		this.total = total;
		this.stability = stability;
		this.prof = prof;
		this.mobility = mobility;
		this.safety = safety;
		this.development = development;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStability() {
		return stability;
	}

	public void setStability(String stability) {
		this.stability = stability;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public String getMobility() {
		return mobility;
	}

	public void setMobility(String mobility) {
		this.mobility = mobility;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	public String getDevelopment() {
		return development;
	}

	public void setDevelopment(String development) {
		this.development = development;
	}
}
