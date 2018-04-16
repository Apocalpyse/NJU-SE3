package VO;

import bl.calculateBl.Translate;

public class StockCompareEverydayVO {
	private String code;//股票代码
	private String name;//股票名称
	private String date;
	private String logarithmicYield;//对数收益率

	public StockCompareEverydayVO() {
	}

	public StockCompareEverydayVO(String code, String name, String date, String logarithmicYield) {
		this.code = code;
		this.name = name;
		Translate translate=new Translate();
		this.date=translate.toPreDate(date);
		this.logarithmicYield = logarithmicYield;
	}

	//get and set
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		Translate translate=new Translate();
		String dataDate=translate.toDataDate(date);
		return dataDate;
	}

	public void setDate(String date) {
		Translate translate=new Translate();
		String preDate=translate.toPreDate(date);
		this.date = preDate;
	}
	public String getLogarithmicYield() {
		return logarithmicYield;
	}
	public void setLogarithmicYield(String logarithmicYield) {
		this.logarithmicYield = logarithmicYield;
	}

}
