package VO.stockVO;

import bl.calculateBl.Translate;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */

/**
 * 均线图的数据
 */
public class EMAVO {
    private String code;//股票代码
    private String name;
    private String start;
    private String end;
    private String numOfDate;//几日均线图，如若是10日均线图，则numOfDate=10
    private ArrayList<String> date;//横坐标
    private ArrayList<String> yield;//纵坐标
    private String maxYield;//最大纵坐标，方便界面定义图
    private String minYield;//最小纵坐标，方便界面定义图

    public EMAVO() {
    }

    public EMAVO(String code, String name, String start, String end, String numOfDate,
                 ArrayList<String> date, ArrayList<String> yield, String maxYield, String minYield) {
        this.code = code;
        this.name = name;
        this.start = start;
        this.end = end;
        this.numOfDate = numOfDate;
        this.date = date;
        this.yield = yield;
        this.maxYield = maxYield;
        this.minYield = minYield;
    }

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

    public String getStart() {
        Translate translate=new Translate();
        String dataStart=translate.toDataDate(start);
        return dataStart;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        Translate translate=new Translate();
        String dataEnd=translate.toDataDate(end);
        return dataEnd;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNumOfDate() {
        return numOfDate;
    }

    public void setNumOfDate(String numOfDate) {
        this.numOfDate = numOfDate;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<String> getYield() {
        return yield;
    }

    public void setYield(ArrayList<String> yield) {
        this.yield = yield;
    }

    public String getMaxYield() {
        return maxYield;
    }

    public void setMaxYield(String maxYield) {
        this.maxYield = maxYield;
    }

    public String getMinYield() {
        return minYield;
    }

    public void setMinYield(String minYield) {
        this.minYield = minYield;
    }
}
