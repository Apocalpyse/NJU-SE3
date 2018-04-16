package VO.userVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class ProfitVO {
    private String totalProfit;
    private ArrayList<String> dates;
    private ArrayList<String> datesProfit;//累积收益率

    public ProfitVO() {
    }

    public ProfitVO(String totalProfit, ArrayList<String> dates, ArrayList<String> datesProfit) {
        this.totalProfit = totalProfit;
        this.dates = dates;
        this.datesProfit = datesProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public ArrayList<String> getDatesProfit() {
        return datesProfit;
    }

    public void setDatesProfit(ArrayList<String> datesProfit) {
        this.datesProfit = datesProfit;
    }
}
