package bl.strategyBl;

import PO.stockPO.StockPool;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class InputStrategyData {
    private String start;//开始日期
    private String end;//结束日期
    private StockPool stockPool;//股票池的种类，若是SELECTPLATE，即用户自选古朴池，则需要填写下一个属性；若是其余的，则下一个设为空的ArrayList即可
    private ArrayList<String> stockNameOrCode;//股票池，可以是股票名称或代码

    public InputStrategyData() {
    }

    public InputStrategyData(String start, String end, StockPool stockPool, ArrayList<String> stockNameOrCode) {
        this.start = start;
        this.end = end;
        this.stockPool = stockPool;
        this.stockNameOrCode = stockNameOrCode;
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

    public StockPool getStockPool() {
        return stockPool;
    }

    public void setStockPool(StockPool stockPool) {
        this.stockPool = stockPool;
    }

    public ArrayList<String> getStockNameOrCode() {
        return stockNameOrCode;
    }

    public void setStockNameOrCode(ArrayList<String> stockNameOrCode) {
        this.stockNameOrCode = stockNameOrCode;
    }
}
