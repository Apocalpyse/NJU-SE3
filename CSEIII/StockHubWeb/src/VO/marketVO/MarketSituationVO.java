package VO.marketVO;

import bl.calculateBl.Translate;

/**
 * Created by A on 2017/5/21.
 */
public class MarketSituationVO {
    /**
     *  用户查询日期或者某一日期的股票交易市场行情相关数据。
     */
    private String date;//日期，格式如"4/29/14","4/2/14"（月/日/年）
    private String totalVolume;//当日总交易量
    private String increaseStopStockNum;//涨停股票数
    private String decreaseStopStockNum;//跌停股票数
    private String increaseMoreStockNum;//涨幅超过5%的股票数
    private String decreaseMoreStockNum;//跌幅超过5%的股票数
    private String openMinusCloseMoreNum;//开盘‐收盘大于5%*上一个交易日收盘价的股票个数
    private String openMinusCloseLessNum;//开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数

    public MarketSituationVO() {
    }

    public MarketSituationVO(String date, String totalVolume, String increaseStopStockNum,
                             String decreaseStopStockNum, String increaseMoreStockNum, String decreaseMoreStockNum,
                             String openMinusCloseMoreNum, String openMinusCloseLessNum) {
        Translate translate=new Translate();
        this.date = translate.toPreDate(date);
        this.totalVolume = totalVolume;
        this.increaseStopStockNum = increaseStopStockNum;
        this.decreaseStopStockNum = decreaseStopStockNum;
        this.increaseMoreStockNum = increaseMoreStockNum;
        this.decreaseMoreStockNum = decreaseMoreStockNum;
        this.openMinusCloseMoreNum = openMinusCloseMoreNum;
        this.openMinusCloseLessNum = openMinusCloseLessNum;
    }

    //get and set
    public String getDate() {
        Translate translate=new Translate();
        String dataDate=translate.toDataDate(date);
        return dataDate;
    }

    public void setDate(String date) {
        Translate translate=new Translate();
        String preDate=translate.toPreDate(date);
        this.date = preDate;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getIncreaseStopStockNum() {
        return increaseStopStockNum;
    }

    public void setIncreaseStopStockNum(String increaseStopStockNum) {
        this.increaseStopStockNum = increaseStopStockNum;
    }

    public String getDecreaseStopStockNum() {
        return decreaseStopStockNum;
    }

    public void setDecreaseStopStockNum(String decreaseStopStockNum) {
        this.decreaseStopStockNum = decreaseStopStockNum;
    }

    public String getIncreaseMoreStockNum() {
        return increaseMoreStockNum;
    }

    public void setIncreaseMoreStockNum(String increaseMoreStockNum) {
        this.increaseMoreStockNum = increaseMoreStockNum;
    }

    public String getDecreaseMoreStockNum() {
        return decreaseMoreStockNum;
    }

    public void setDecreaseMoreStockNum(String decreaseMoreStockNum) {
        this.decreaseMoreStockNum = decreaseMoreStockNum;
    }

    public String getOpenMinusCloseMoreNum() {
        return openMinusCloseMoreNum;
    }

    public void setOpenMinusCloseMoreNum(String openMinusCloseMoreNum) {
        this.openMinusCloseMoreNum = openMinusCloseMoreNum;
    }

    public String getOpenMinusCloseLessNum() {
        return openMinusCloseLessNum;
    }

    public void setOpenMinusCloseLessNum(String openMinusCloseLessNum) {
        this.openMinusCloseLessNum = openMinusCloseLessNum;
    }
}
