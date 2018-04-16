package PO.userPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class BuyPO {
    private String account;
    private String date;//买的日期
    private String code;//买的股票代码
    private String volume;//买的股票分数
    private boolean isBuy;//true代表买，false代表卖

    public BuyPO(){

    }

    public BuyPO(String account, String date, String code, String volume, boolean isBuy) {
        this.account = account;
        this.date = date;
        this.code = code;
        this.volume = volume;
        this.isBuy = isBuy;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }
}
