package VO.stockVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class KChartVO {
    private String stockCode;
    private String stockName;
    private String lastDatePrice;//最后一天的股票价格
    private String lastDateIncOrDecRate;//最后一天的股票涨跌幅
    private ArrayList<String> date;
    private ArrayList<Double> adjClose;//每日复权收盘指数
    private ArrayList<Double> inOrDeYield;//涨跌幅

    public KChartVO() {
    }

    public KChartVO(String stockCode, String stockName, String lastDatePrice, String lastDateIncOrDecRate, ArrayList<String> date, ArrayList<Double> adjClose, ArrayList<Double> inOrDeYield) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.lastDatePrice = lastDatePrice;
        this.lastDateIncOrDecRate = lastDateIncOrDecRate;
        this.date = date;
        this.adjClose = adjClose;
        this.inOrDeYield = inOrDeYield;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getLastDatePrice() {
        return lastDatePrice;
    }

    public void setLastDatePrice(String lastDatePrice) {
        this.lastDatePrice = lastDatePrice;
    }

    public String getLastDateIncOrDecRate() {
        return lastDateIncOrDecRate;
    }

    public void setLastDateIncOrDecRate(String lastDateIncOrDecRate) {
        this.lastDateIncOrDecRate = lastDateIncOrDecRate;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<Double> getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(ArrayList<Double> adjClose) {
        this.adjClose = adjClose;
    }

    public ArrayList<Double> getInOrDeYield() {
        return inOrDeYield;
    }

    public void setInOrDeYield(ArrayList<Double> inOrDeYield) {
        this.inOrDeYield = inOrDeYield;
    }
}
