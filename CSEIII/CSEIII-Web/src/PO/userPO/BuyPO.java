package PO.userPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class BuyPO {
    private String account;
    private String date;//买的日期
    private ArrayList<String> buyCode;//买的股票代码
    private ArrayList<String> buyMoney;//买的股票金额
    private ArrayList<String> buyCopies;//买的股票份数
    private ArrayList<String> sellCode;//卖的股票代码
    private ArrayList<String> sellMoney;//卖的股票金额
    private ArrayList<String> sellCodpies;//卖的股票份数
    public BuyPO(){

    }

    public BuyPO(String account, String date, ArrayList<String> buyCode, ArrayList<String> buyMoney, ArrayList<String> buyCopies, ArrayList<String> sellCode, ArrayList<String> sellMoney, ArrayList<String> sellCodpies) {
        this.account = account;
        this.date = date;
        this.buyCode = buyCode;
        this.buyMoney = buyMoney;
        this.buyCopies = buyCopies;
        this.sellCode = sellCode;
        this.sellMoney = sellMoney;
        this.sellCodpies = sellCodpies;
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

    public ArrayList<String> getBuyCode() {
        return buyCode;
    }

    public void setBuyCode(ArrayList<String> buyCode) {
        this.buyCode = buyCode;
    }

    public ArrayList<String> getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(ArrayList<String> buyMoney) {
        this.buyMoney = buyMoney;
    }

    public ArrayList<String> getSellCode() {
        return sellCode;
    }

    public void setSellCode(ArrayList<String> sellCode) {
        this.sellCode = sellCode;
    }

    public ArrayList<String> getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(ArrayList<String> sellMoney) {
        this.sellMoney = sellMoney;
    }

    public ArrayList<String> getBuyCopies() {
        return buyCopies;
    }

    public void setBuyCopies(ArrayList<String> buyCopies) {
        this.buyCopies = buyCopies;
    }

    public ArrayList<String> getSellCodpies() {
        return sellCodpies;
    }

    public void setSellCodpies(ArrayList<String> sellCodpies) {
        this.sellCodpies = sellCodpies;
    }
}
