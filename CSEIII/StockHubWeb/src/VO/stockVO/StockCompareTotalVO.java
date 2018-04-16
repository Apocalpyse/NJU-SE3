package VO.stockVO;

/**
 * Created by A on 2017/5/21.
 */
public class StockCompareTotalVO {
    private String code;//股票代码
    private String name;//股票名称
    private String high;//股票这段时间的最高值
    private String low;//股票这段时间的最低值
    private String increaseOrDecrease;//股票这段时间的涨幅/跌幅
    private String logarithmicYieldVariance;//股票这段时间的对数收益率方差，即个股的相对方差

    public StockCompareTotalVO() {
    }

    public StockCompareTotalVO(String code, String name, String high, String low, String increaseOrDecrease, String logarithmicYieldVariance) {
        this.code = code;
        this.name = name;
        this.high = high;
        this.low = low;
        this.increaseOrDecrease = increaseOrDecrease;
        this.logarithmicYieldVariance = logarithmicYieldVariance;
    }

    //get and set
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
    public String getIncreaseOrDecrease() {
        return increaseOrDecrease;
    }
    public void setIncreaseOrDecrease(String increaseOrDecrease) {
        this.increaseOrDecrease = increaseOrDecrease;
    }
    public String getLogarithmicYieldVariance() {
        return logarithmicYieldVariance;
    }
    public void setLogarithmicYieldVariance(String logarithmicYieldVariance) {
        this.logarithmicYieldVariance = logarithmicYieldVariance;
    }

}
