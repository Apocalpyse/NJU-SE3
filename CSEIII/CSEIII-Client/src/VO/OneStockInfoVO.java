package VO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */
public class OneStockInfoVO {
    private ArrayList<StockVO> stockVOS;//这部分做成K线图和均线图
    private PieVO pieVO;//这部分做成饼状图
    private AllStrategyVO momentStrategy;//这部分是动量策略
    private AllStrategyVO averageStrategy;//这部分是均值回归策略

    public OneStockInfoVO() {
    }

    public OneStockInfoVO(ArrayList<StockVO> stockVOS, PieVO pieVO, AllStrategyVO momentStrategy, AllStrategyVO averageStrategy) {
        this.stockVOS = stockVOS;
        this.pieVO = pieVO;
        this.momentStrategy = momentStrategy;
        this.averageStrategy = averageStrategy;
    }

    public ArrayList<StockVO> getStockVOS() {
        return stockVOS;
    }

    public void setStockVOS(ArrayList<StockVO> stockVOS) {
        this.stockVOS = stockVOS;
    }

    public PieVO getPieVO() {
        return pieVO;
    }

    public void setPieVO(PieVO pieVO) {
        this.pieVO = pieVO;
    }

    public AllStrategyVO getMomentStrategy() {
        return momentStrategy;
    }

    public void setMomentStrategy(AllStrategyVO momentStrategy) {
        this.momentStrategy = momentStrategy;
    }

    public AllStrategyVO getAverageStrategy() {
        return averageStrategy;
    }

    public void setAverageStrategy(AllStrategyVO averageStrategy) {
        this.averageStrategy = averageStrategy;
    }
}
