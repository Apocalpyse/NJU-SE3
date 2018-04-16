package VO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */
public class TotalPlateVO {
    private String date;//日期
    private ArrayList<String> plateName;//板块名称
    private ArrayList<String> companyNum;//公司家数
    private ArrayList<String> averageOpen;//平均开盘价
    private ArrayList<String> averageClose;//平均收盘价
    private ArrayList<String> averageAdjClose;//平均复权收盘价
    private ArrayList<String> preAverageAdjClose;//上一日的平均复权收盘价
    private ArrayList<String> increaseOrDecreaseMoney;//涨跌额
    private ArrayList<String> increaseOrDecreaseRate;//涨跌幅
    private ArrayList<String> totalVolume;//总成交量

    public TotalPlateVO() {
        this.date = "2/14/14";//默认日期为14年2月14日
    }

    public TotalPlateVO(String date, ArrayList<String> plateName, ArrayList<String> companyNum,
                        ArrayList<String> averageOpen, ArrayList<String> averageClose, ArrayList<String> averageAdjClose,
                        ArrayList<String> preAverageAdjClose, ArrayList<String> increaseOrDecreaseMoney,
                        ArrayList<String> increaseOrDecreaseRate, ArrayList<String> totalVolume) {
        this.date = date;
        this.plateName = plateName;
        this.companyNum = companyNum;
        this.averageOpen = averageOpen;
        this.averageClose = averageClose;
        this.averageAdjClose = averageAdjClose;
        this.preAverageAdjClose = preAverageAdjClose;
        this.increaseOrDecreaseMoney = increaseOrDecreaseMoney;
        this.increaseOrDecreaseRate = increaseOrDecreaseRate;
        this.totalVolume = totalVolume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getPlateName() {
        return plateName;
    }

    public void setPlateName(ArrayList<String> plateName) {
        this.plateName = plateName;
    }

    public ArrayList<String> getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(ArrayList<String> companyNum) {
        this.companyNum = companyNum;
    }

    public ArrayList<String> getAverageOpen() {
        return averageOpen;
    }

    public void setAverageOpen(ArrayList<String> averageOpen) {
        this.averageOpen = averageOpen;
    }

    public ArrayList<String> getAverageClose() {
        return averageClose;
    }

    public void setAverageClose(ArrayList<String> averageClose) {
        this.averageClose = averageClose;
    }

    public ArrayList<String> getAverageAdjClose() {
        return averageAdjClose;
    }

    public void setAverageAdjClose(ArrayList<String> averageAdjClose) {
        this.averageAdjClose = averageAdjClose;
    }

    public ArrayList<String> getPreAverageAdjClose() {
        return preAverageAdjClose;
    }

    public void setPreAverageAdjClose(ArrayList<String> preAverageAdjClose) {
        this.preAverageAdjClose = preAverageAdjClose;
    }

    public ArrayList<String> getIncreaseOrDecreaseMoney() {
        return increaseOrDecreaseMoney;
    }

    public void setIncreaseOrDecreaseMoney(ArrayList<String> increaseOrDecreaseMoney) {
        this.increaseOrDecreaseMoney = increaseOrDecreaseMoney;
    }

    public ArrayList<String> getIncreaseOrDecreaseRate() {
        return increaseOrDecreaseRate;
    }

    public void setIncreaseOrDecreaseRate(ArrayList<String> increaseOrDecreaseRate) {
        this.increaseOrDecreaseRate = increaseOrDecreaseRate;
    }

    public ArrayList<String> getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(ArrayList<String> totalVolume) {
        this.totalVolume = totalVolume;
    }
}
