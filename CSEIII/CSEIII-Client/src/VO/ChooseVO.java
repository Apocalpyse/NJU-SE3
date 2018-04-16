package VO;

import PO.StockPool;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public class ChooseVO {
	private String date;
	private StockPool stockPool;
	private String industryPool;
	private ArrayList<String> open;//当日开盘价范围,第一个小值，第二个大值
	private ArrayList<String> close;//当日收盘价范围,第一个小值，第二个大值
	private ArrayList<String> inDecrease;//当日股价涨跌幅范围,第一个小值，第二个大值
	private ArrayList<String> volume;//当日总交易量范围,第一个小值，第二个大值

	public ChooseVO(){

	}

	public ChooseVO(String date, StockPool stockPool, String industryPool, ArrayList<String> open, ArrayList<String> close, ArrayList<String> inDecrease, ArrayList<String> volume) {
		this.date = date;
		this.stockPool = stockPool;
		this.industryPool = industryPool;
		this.open = open;
		this.close = close;
		this.inDecrease = inDecrease;
		this.volume = volume;
	}

	public String getIndustryPool() {
		return industryPool;
	}

	public void setIndustryPool(String industryPool) {
		this.industryPool = industryPool;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public StockPool getStockPool() {
		return stockPool;
	}

	public void setStockPool(StockPool stockPool) {
		this.stockPool = stockPool;
	}

	public ArrayList<String> getOpen() {
		return open;
	}

	public void setOpen(ArrayList<String> open) {
		this.open = open;
	}

	public ArrayList<String> getClose() {
		return close;
	}

	public void setClose(ArrayList<String> close) {
		this.close = close;
	}

	public ArrayList<String> getInDecrease() {
		return inDecrease;
	}

	public void setInDecrease(ArrayList<String> inDecrease) {
		this.inDecrease = inDecrease;
	}

	public ArrayList<String> getVolume() {
		return volume;
	}

	public void setVolume(ArrayList<String> volume) {
		this.volume = volume;
	}
}
