package VO.stockVO;

/**
 * Created by A on 2017/5/21.
 */
public class StockPieVO {
    private String date;//日期
    private double decStop;//跌停的股票数
    private double rateLessNeg5;//跌幅大于5%的股票数
    private double rateFromNeg5ToZero;//跌幅在-5%到0之间的股票数
    private double rateFromZeroTo5;//涨幅在0%到5%之间的股票数
    private double rateMore5;//涨幅大于5%的股票数
    private double incStop;//涨停的股票数

    public StockPieVO() {
    }

    public StockPieVO(String date, double decStop, double rateLessNeg5,
                      double rateFromNeg5ToZero, double rateFromZeroTo5,
                      double rateMore5, double incStop) {
        this.date = date;
        this.decStop = decStop;
        this.rateLessNeg5 = rateLessNeg5;
        this.rateFromNeg5ToZero = rateFromNeg5ToZero;
        this.rateFromZeroTo5 = rateFromZeroTo5;
        this.rateMore5 = rateMore5;
        this.incStop = incStop;
    }

    public double getDecStop() {
        return decStop;
    }

    public void setDecStop(double decStop) {
        this.decStop = decStop;
    }

    public double getIncStop() {
        return incStop;
    }

    public void setIncStop(double incStop) {
        this.incStop = incStop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRateLessNeg5() {
        return rateLessNeg5;
    }

    public void setRateLessNeg5(double rateLessNeg5) {
        this.rateLessNeg5 = rateLessNeg5;
    }

    public double getRateFromNeg5ToZero() {
        return rateFromNeg5ToZero;
    }

    public void setRateFromNeg5ToZero(double rateFromNeg5ToZero) {
        this.rateFromNeg5ToZero = rateFromNeg5ToZero;
    }

    public double getRateFromZeroTo5() {
        return rateFromZeroTo5;
    }

    public void setRateFromZeroTo5(double rateFromZeroTo5) {
        this.rateFromZeroTo5 = rateFromZeroTo5;
    }

    public double getRateMore5() {
        return rateMore5;
    }

    public void setRateMore5(double rateMore5) {
        this.rateMore5 = rateMore5;
    }
}
