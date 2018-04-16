package VO.strategyVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class RelationGraphVO {
    private ArrayList<String> period;//相对强弱计算周期
    private ArrayList<String> overYield;//超额收益
    private ArrayList<String> winRate;//胜率

    public RelationGraphVO() {
    }

    public RelationGraphVO(ArrayList<String> period, ArrayList<String> overYield, ArrayList<String> winRate) {
        this.period = period;
        this.overYield = overYield;
        this.winRate = winRate;
    }

    public ArrayList<String> getPeriod() {
        return period;
    }

    public void setPeriod(ArrayList<String> period) {
        this.period = period;
    }

    public ArrayList<String> getOverYield() {
        return overYield;
    }

    public void setOverYield(ArrayList<String> overYield) {
        this.overYield = overYield;
    }

    public ArrayList<String> getWinRate() {
        return winRate;
    }

    public void setWinRate(ArrayList<String> winRate) {
        this.winRate = winRate;
    }
}
