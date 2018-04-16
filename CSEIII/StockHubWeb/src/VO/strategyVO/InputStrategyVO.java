package VO.strategyVO;

import bl.calculateBl.Translate;
import blSer.StockPoolBl;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class InputStrategyVO {
    private StrategyType type;//策略的种类
    private String formationPeriod;//形成期
    private String holdingPeriod;//持有期
    private String start;//开始时间
    private String end;//结束时间
    private StockPoolBl stockPoolBl;//股票池的种类，若是SELECTPLATE，即用户自选古朴池，则需要填写下一个属性；若是其余的，则下一个设为空的ArrayList即可
    private ArrayList<String> stockNameOrCode;//股票池，可以是股票名称或代码

    public InputStrategyVO() {
        this.type = StrategyType.MOMENT;//默认的策略种类为动量策略
    }

    public InputStrategyVO(StrategyType type, String formationPeriod, String holdingPeriod,
						   String start, String end, StockPoolBl stockPoolBl, ArrayList<String> stockNameOrCode) {
        this.type = type;
        this.formationPeriod = formationPeriod;
        this.holdingPeriod = holdingPeriod;
        this.start = start;
        this.end = end;
        this.stockPoolBl = stockPoolBl;
        this.stockNameOrCode = stockNameOrCode;
    }

    public StrategyType getType() {
        return type;
    }

    public void setType(StrategyType type) {
        this.type = type;
    }

    public String getFormationPeriod() {
        return formationPeriod;
    }

    public void setFormationPeriod(String formationPeriod) {
        this.formationPeriod = formationPeriod;
    }

    public String getHoldingPeriod() {
        return holdingPeriod;
    }

    public void setHoldingPeriod(String holdingPeriod) {
        this.holdingPeriod = holdingPeriod;
    }

    public String getStart() {
        Translate translate=new Translate();
        String result=translate.toDataDate(start);
        return result;
    }

    public void setStart(String start) {
        Translate translate=new Translate();
        String result=translate.toPreDate(start);
        this.start = result;
    }

    public String getEnd() {
        Translate translate=new Translate();
        String result=translate.toDataDate(end);
        return result;
    }

    public void setEnd(String end) {
        Translate translate=new Translate();
        String result=translate.toPreDate(end);
        this.end = result;
    }

    public StockPoolBl getStockPoolBl() {
        return stockPoolBl;
    }

    public void setStockPoolBl(StockPoolBl stockPoolBl) {
        this.stockPoolBl = stockPoolBl;
    }

    public ArrayList<String> getStockNameOrCode() {
        return stockNameOrCode;
    }

    public void setStockNameOrCode(ArrayList<String> stockNameOrCode) {
        this.stockNameOrCode = stockNameOrCode;
    }
}
