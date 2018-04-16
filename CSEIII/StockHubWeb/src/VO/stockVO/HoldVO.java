package VO.stockVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class HoldVO {
    private String account;
    private String date;
    private String totalMoney;//持有总金额
    private ArrayList<String> holdCode;
    private ArrayList<String> holdMoney;
    private ArrayList<String> holdCopies;

    public HoldVO() {
    }

    public HoldVO(String account, String date,String totalMoney,ArrayList<String> holdCode, ArrayList<String> holdMoney, ArrayList<String> holdCopies) {
        this.account = account;
        this.date=date;
        this.totalMoney=totalMoney;
        this.holdCode = holdCode;
        this.holdMoney = holdMoney;
        this.holdCopies = holdCopies;
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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ArrayList<String> getHoldCode() {
        return holdCode;
    }

    public void setHoldCode(ArrayList<String> holdCode) {
        this.holdCode = holdCode;
    }

    public ArrayList<String> getHoldMoney() {
        return holdMoney;
    }

    public void setHoldMoney(ArrayList<String> holdMoney) {
        this.holdMoney = holdMoney;
    }

    public ArrayList<String> getHoldCopies() {
        return holdCopies;
    }

    public void setHoldCopies(ArrayList<String> holdCopies) {
        this.holdCopies = holdCopies;
    }
}
