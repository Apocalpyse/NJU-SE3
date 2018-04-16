package bl.strategyBl;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class StrategyYieldData {
    private ArrayList<String> accumulativeYield;
    private ArrayList<String> yield;

    public StrategyYieldData() {
    }

    public StrategyYieldData(ArrayList<String> accumulativeYield, ArrayList<String> yield) {
        this.accumulativeYield = accumulativeYield;
        this.yield = yield;
    }

    public ArrayList<String> getAccumulativeYield() {
        return accumulativeYield;
    }

    public void setAccumulativeYield(ArrayList<String> accumulativeYield) {
        this.accumulativeYield = accumulativeYield;
    }

    public ArrayList<String> getYield() {
        return yield;
    }

    public void setYield(ArrayList<String> yield) {
        this.yield = yield;
    }

}
