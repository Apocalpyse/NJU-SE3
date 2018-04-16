package VO;

import bl.calculateBl.Translate;

/**
 * Created by Administrator on 2017/3/11.
 */
public class PieVO {
    private String name;//股票名称
    private String code;//股票代码
    private String start;//开始日期
    private String end;//结束日期
    private String increaseLessDays;//涨幅小于5%的天数，即0<=x<5的天数
    private String increaseMoreDays;//涨幅超过5%的天数，包括涨停的天数，即x>=5的天数
    private String decreaseLessDays;//跌幅小于5%的天数，即-5<x<0的天数
    private String decreaseMoredays;//跌幅超过-5%的天数，包括跌停的股票，即x<=-5的天数

    public PieVO() {
    }

    public PieVO(String name, String code, String start, String end, String increaseLessDays,
                 String increaseMoreDays, String decreaseLessDays, String decreaseMoredays) {
        this.name = name;
        this.code = code;
        Translate translate=new Translate();
        this.start = translate.toPreDate(start);
        this.end = translate.toPreDate(end);
        this.increaseLessDays = increaseLessDays;
        this.increaseMoreDays = increaseMoreDays;
        this.decreaseLessDays = decreaseLessDays;
        this.decreaseMoredays = decreaseMoredays;
    }

    //get and set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStart() {
        Translate translate=new Translate();
        String result=translate.toDataDate(start);
        return result;
    }
    public void setStart(String start) {
        Translate translate=new Translate();
        String preStart=translate.toPreDate(start);
        this.start = preStart;
    }
    public String getEnd() {
        Translate translate=new Translate();
        String result=translate.toDataDate(end);
        return result;
    }
    public void setEnd(String end) {
        Translate translate=new Translate();
        String preEnd=translate.toPreDate(end);
        this.end = preEnd;
    }

    public String getIncreaseLessDays() {
        return increaseLessDays;
    }

    public void setIncreaseLessDays(String increaseLessDays) {
        this.increaseLessDays = increaseLessDays;
    }

    public String getIncreaseMoreDays() {
        return increaseMoreDays;
    }

    public void setIncreaseMoreDays(String increaseMoreDays) {
        this.increaseMoreDays = increaseMoreDays;
    }

    public String getDecreaseLessDays() {
        return decreaseLessDays;
    }

    public void setDecreaseLessDays(String decreaseLessDays) {
        this.decreaseLessDays = decreaseLessDays;
    }

    public String getDecreaseMoredays() {
        return decreaseMoredays;
    }

    public void setDecreaseMoredays(String decreaseMoredays) {
        this.decreaseMoredays = decreaseMoredays;
    }
}
