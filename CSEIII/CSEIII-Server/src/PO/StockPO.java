package PO;


import java.io.Serializable;

public class StockPO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String date;//日期，格式如"4/29/14","4/2/14"（月/日/年） 
	private String open;//开盘指数
	private String high;//最高指数
	private String low;//最低指数
	private String close;//收盘指数
	private String volume;//成交量
	private String adjClose;//复权后的收盘指数
	private String code;//股票代码，格式同数据库表中一样，如“123”，“123456”
	private String name;//股票名称
	private String market;//市场名称
	private String increaseOrDecrease;//涨跌幅
	private String preAdjClose;//上一日的复权收盘价
	private String plate;//股票所属板块

	public StockPO() {
	}

	public StockPO(String date, String open, String high, String low, String close, String volume,
				   String adjClose, String code, String name, String market, String increaseOrDecrease, String preAdjClose, String plate) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
		this.code = code;
		this.name = name;
		this.market = market;
		this.increaseOrDecrease = increaseOrDecrease;
		this.preAdjClose = preAdjClose;
		this.plate = plate;
	}

	//get and set
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}

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
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}

	public String getIncreaseOrDecrease() {
		return increaseOrDecrease;
	}

	public void setIncreaseOrDecrease(String increaseOrDecrease) {
		this.increaseOrDecrease = increaseOrDecrease;
	}

	public String getPreAdjClose() {
		return preAdjClose;
	}

	public void setPreAdjClose(String preAdjClose) {
		this.preAdjClose = preAdjClose;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
}
