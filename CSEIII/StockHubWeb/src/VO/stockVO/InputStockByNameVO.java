package VO.stockVO;

import bl.calculateBl.Translate;

/**
 * Created by A on 2017/5/21.
 */
public class InputStockByNameVO {
    private String start;//开始日期，格式如"4/29/14","4/2/14"（月/日/年）
    private String end;//结束日期，格式如"4/29/14","4/2/14"（月/日/年）
    private String name;//股票名称

    public InputStockByNameVO() {
    }

    public InputStockByNameVO(String start, String end, String name) {
        Translate translate=new Translate();
        this.start = translate.toPreDate(start);
        this.end = translate.toPreDate(end);
        this.name = name;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
