package VO.stockVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class TwentyStockVO {
    private String start;//开始日期
    private String end;//结束日期
    private ArrayList<GoalVO> goal;//股票得分
    public TwentyStockVO(){

    }

    public TwentyStockVO(String start, String end, ArrayList<GoalVO> goal) {
        this.start = start;
        this.end = end;
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



    public ArrayList<GoalVO> getGoal() {
        return goal;
    }

    public void setGoal(ArrayList<GoalVO> goal) {
        this.goal = goal;
    }
}
