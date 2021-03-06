package PO.userPO;

import VO.userVO.SelfSelectStockVO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class SelfSelectStockPO {
    private String account;//用户账户，登录时使用，每个用户的账户是唯一的。账户格式要求只能包含英文字母、数字，且必须以英文字母开头。
    private ArrayList<String> code;//股票代码，格式同数据库表中一样，如“123”，“123456”
    private ArrayList<String> name;//股票名称
    private ArrayList<String> market;//市场名称

    public SelfSelectStockPO() {
    }

    public SelfSelectStockPO(String account, ArrayList<String> code, ArrayList<String> name, ArrayList<String> market) {
        this.account = account;
        this.code = code;
        this.name = name;
        this.market = market;
    }

    public SelfSelectStockPO(SelfSelectStockVO selfSelectStockVO) {
        this.account = selfSelectStockVO.getAccount();
        this.code = selfSelectStockVO.getCode();
        this.name = selfSelectStockVO.getName();
        this.market = selfSelectStockVO.getMarket();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<String> getMarket() {
        return market;
    }

    public void setMarket(ArrayList<String> market) {
        this.market = market;
    }
}
