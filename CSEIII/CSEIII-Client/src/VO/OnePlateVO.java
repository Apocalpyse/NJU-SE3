package VO;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */
public class OnePlateVO {
    private String date;
    private ArrayList<StockVO> stockVOS;

    public OnePlateVO() {
        this.date = "2/14/14";//默认日期为14年2月14日
    }

    public OnePlateVO(String date, ArrayList<StockVO> stockVOS) {
        this.date = date;
        this.stockVOS = stockVOS;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<StockVO> getStockVOS() {
        return stockVOS;
    }

    public void setStockVOS(ArrayList<StockVO> stockVOS) {
        this.stockVOS = stockVOS;
    }
}
