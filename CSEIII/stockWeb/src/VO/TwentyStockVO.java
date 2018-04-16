package VO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public class TwentyStockVO {
	private String start;//开始日期
	private String end;//结束日期
	private ArrayList<String> name;//股票名字
	private ArrayList<String> code;//股票代码
	private ArrayList<GoalVO> goal;//股票得分
	public TwentyStockVO(){

	}

	public TwentyStockVO(String start, String end, ArrayList<String> name, ArrayList<String> code, ArrayList<GoalVO> goal) {
		this.start = start;
		this.end = end;
		this.name = name;
		this.code = code;
		this.goal = goal;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public ArrayList<String> getName() {
		return name;
	}

	public void setName(ArrayList<String> name) {
		this.name = name;
	}

	public ArrayList<String> getCode() {
		return code;
	}

	public void setCode(ArrayList<String> code) {
		this.code = code;
	}

	public ArrayList<GoalVO> getGoal() {
		return goal;
	}

	public void setGoal(ArrayList<GoalVO> goal) {
		this.goal = goal;
	}
}
