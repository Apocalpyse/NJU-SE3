package PO.stockPO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/6/12.
 */
public class EvaluateStockS {
	private String code;
	private String name;
	private ArrayList<String> adjClose;
	private ArrayList<String> iOrD;//涨跌幅
	private ArrayList<String> volume;

	public EvaluateStockS() {
	}

	public EvaluateStockS(String code, String name, ArrayList<String> adjClose, ArrayList<String> iOrD, ArrayList<String> volume) {
		this.code = code;
		this.name = name;
		this.adjClose = adjClose;
		this.iOrD = iOrD;
		this.volume = volume;
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

	public ArrayList<String> getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(ArrayList<String> adjClose) {
		this.adjClose = adjClose;
	}

	public ArrayList<String> getiOrD() {
		return iOrD;
	}

	public void setiOrD(ArrayList<String> iOrD) {
		this.iOrD = iOrD;
	}

	public ArrayList<String> getVolume() {
		return volume;
	}

	public void setVolume(ArrayList<String> volume) {
		this.volume = volume;
	}
}
